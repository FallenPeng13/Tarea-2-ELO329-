import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TabletView extends Group {
    private final Tablet tablet;
    private final Rectangle rect;
    private final Text label;

    public TabletView(Tablet tablet) {

        this.tablet = tablet;
        double width = 16;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.PURPLE);
        rect.setArcWidth(4);   
        rect.setArcHeight(4);
        label = new Text(tablet.getOwnerName());
        // Centrar el rectángulo en (x, y) del modelo
        rect.xProperty().bind(tablet.xProperty().subtract(width / 2));
        rect.yProperty().bind(tablet.yProperty().subtract(height / 2));

        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(tablet.xProperty().add(width / 2 + 4));
        label.yProperty().bind(tablet.yProperty().add(height / 2));

        getChildren().addAll(rect, label);
    }
}