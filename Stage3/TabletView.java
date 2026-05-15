import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javafx.scene.shape.Circle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;


public class TabletView extends Group {
    private final Tablet tablet;
    private final Rectangle rect;
    private final Text label;

    private ContextMenu contextMenu;

    private ETNube nube;


    public TabletView(Tablet tablet, ETNube cloud) {
        nube = cloud;

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

        contextMenu = new ContextMenu();
        MenuItem findMy = new MenuItem("FindMy");
        contextMenu.getItems().addAll(findMy);

        this.setOnMouseClicked(e -> {contextMenu.show(this, e.getScreenX(), e.getScreenY());});

        findMy.setOnAction(event -> {
            Stage findMyStage = new Stage();
             VBox vbox = new VBox(5);
    
            String owner = tablet.getOwnerName();
            vbox.getChildren().add(new Text("Bienes de " + owner));
    
            HashMap<String, Point2D> items = nube.getHashMap(owner);
            if (items != null) {
               for (String device : items.keySet()) {
                   Point2D pos = items.get(device);
                    vbox.getChildren().add(new Text(device + ": " + (int)pos.getX() + ", " + (int)pos.getY()));
                }
            }
    
            Scene scene = new Scene(vbox, 200, 150);
            findMyStage.setTitle("Find My");
            findMyStage.setScene(scene);
            findMyStage.show();

        });

        tablet.bippingProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
            mostrarRadar();
                tablet.setBipping(false);
            }
        });



    }


    private void mostrarRadar() {
        Circle radar = new Circle(0);
        radar.setFill(Color.TRANSPARENT);
        radar.setStroke(Color.rgb(0, 120, 255, 0.4));
        radar.setStrokeWidth(2);
        radar.centerXProperty().bind(tablet.xProperty());
        radar.centerYProperty().bind(tablet.yProperty());
        getChildren().add(radar);

        Timeline animation = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(radar.radiusProperty(), 0)),
            new KeyFrame(Duration.seconds(1), new KeyValue(radar.radiusProperty(), 50))
        );
        animation.setOnFinished(e -> getChildren().remove(radar));
        animation.play();
}




}