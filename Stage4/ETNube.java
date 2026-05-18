
import javafx.geometry.Point2D;
import java.util.HashMap;

public class ETNube {

    HashMap<String, HashMap<String, Point2D>> ubicaciones; 


    public ETNube(){
        this.ubicaciones = new HashMap<>();
    }

    public void registrar(String dueño, String dispositivo, Point2D pos){

        HashMap <String, Point2D> agregando =ubicaciones.getOrDefault(dueño, new HashMap<>());
        agregando.put(dispositivo, pos);

        ubicaciones.put(dueño, agregando);
    }

    public HashMap<String, Point2D> getHashMap(String dueño){
        
        return ubicaciones.get(dueño);
    }

}
