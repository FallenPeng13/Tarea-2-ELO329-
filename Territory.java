import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Territory {  // Piece of land where cellulars, tags, and tablets are located and moved.
    private ArrayList<Equipo> equipment = new ArrayList<>();
    private ETNube nube;

    private double counterTag = 0;
    private double counterTablet = 0;
    private double counterCell = 0;




    public Territory (ETNube cloud){
        nube = cloud;

    }


    public void addEquipment(Equipo eq) {
        equipment.add(eq);
    }



    public void updateSystem(double timeStep){

        for (Equipo eq: equipment){
            eq.move(timeStep);
        }

        counterCell += timeStep; 
        counterTablet+=timeStep;
        counterTag+=timeStep;
        
        boolean tagBip = false;
        boolean tabletBip = false;


        if (counterTag >= 4){
            tagBip = true;
            counterTag = 0;
        } 
        
        if (counterTablet >= 5){
            tabletBip = true;
            counterTablet = 0;
        } 

        if(counterCell >= 4){
            counterCell = 0;

            for (Equipo c: equipment){
            if (c instanceof Cellular){
                Cellular celu = (Cellular) c;
                celu.reportSelfLocation(nube);
                }
            }
        }


        


        for (Equipo eq: equipment){ //revisar todos los equipos
            for (Equipo eq1: equipment){
                if (eq1 instanceof Cellular && !(eq instanceof Cellular)){
                    double x1 = eq.xProperty().get();
                    double y1 = eq.yProperty().get();
                    double x2 = eq1.xProperty().get();
                    double y2 = eq1.yProperty().get();

                    double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
                    
                    if (distance <= 50) {
                        Cellular cel = (Cellular) eq1;
                        if (eq instanceof Tablet && tabletBip){
                            cel.reportTabletLocation(nube, (Tablet) eq);
                            ((Tablet) eq).setBipping(true);
                        }
                        if (eq instanceof EloTelTag && tagBip){
                            cel.reportTagLocation(nube, (EloTelTag) eq);
                            ((EloTelTag) eq).setBipping(true);
                     }
                    }
                }
            }
        }
    }
}




