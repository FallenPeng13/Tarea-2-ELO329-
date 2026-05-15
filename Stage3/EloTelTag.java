import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class EloTelTag extends Equipo {
    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
        name=n;
    }
    public String getName(){
        return name;
    }

    public BooleanProperty bippingProperty() { 
        return bipping; 
    }
    
    public void setBipping(boolean value) { 
        bipping.set(value); 
    }


    private final String name;
    private BooleanProperty bipping = new SimpleBooleanProperty(false);
}
