import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private final Circle circle;
    private final Text label;

    public EloTelTagView(EloTelTag tag)  {
        this.tag = tag;
        double ratio = 5;
        circle = new Circle(ratio);
        circle.setFill(Color.DODGERBLUE);
  
        label = new Text(tag.getName());



        // Centrar el rectángulo en (x, y) del modelo
        circle.centerXProperty().bind(tag.xProperty());
        circle.centerYProperty().bind(tag.yProperty());

        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(tag.xProperty());
        label.yProperty().bind(tag.yProperty().add(ratio));

        getChildren().addAll(circle, label);
    }


}
 