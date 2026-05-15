
import javafx.geometry.Point2D;

public class Cellular extends Equipo {
    public Cellular(String owner, double x, double y, double r, double theta, double dt) {
        super(owner, x, y, r, theta, dt);
    }

    public void reportSelfLocation (ETNube nube){
        Point2D position =new Point2D(super.x.get(), super.y.get());
        nube.registrar(super.getOwnerName(), "Celular", position);
    }


    public void reportTagLocation (ETNube nube, EloTelTag tag){
        Point2D position =new Point2D(super.x.get(), super.y.get());
        nube.registrar(tag.getOwnerName(), tag.getName(), position);
    }

    public void reportTabletLocation (ETNube nube, Tablet tablet){
        Point2D position =new Point2D(super.x.get(), super.y.get());
        nube.registrar(tablet.getOwnerName(), "Tablet", position);
    }

}
