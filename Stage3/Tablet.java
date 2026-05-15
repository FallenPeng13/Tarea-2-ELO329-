import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Tablet extends Equipo {
    BooleanProperty bipping = new SimpleBooleanProperty(false);;
    
    public Tablet(String owner, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
    }


    public BooleanProperty bippingProperty() { 
        return bipping; 
    }
    
    public void setBipping(boolean value) { 
        bipping.set(value); 
    }
}

