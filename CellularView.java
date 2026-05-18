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


import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class CellularView extends Group {
    private final Cellular cellular;
    private final Rectangle rect;
    private final Text label;

    private ContextMenu contextMenu;

    private ETNube nube;

    public CellularView(Cellular cellular, ETNube cloud) {
        
        nube=cloud;
        
        this.cellular = cellular;
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);   
        rect.setArcHeight(4);
        label = new Text(cellular.getOwnerName());
        // Centrar el rectángulo en (x, y) del modelo
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));

        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty().add(height / 2));

        getChildren().addAll(rect, label);

        contextMenu = new ContextMenu();
        MenuItem findMy = new MenuItem("FindMy");
        contextMenu.getItems().addAll(findMy);

        this.setOnMouseClicked(e -> {contextMenu.show(this, e.getScreenX(), e.getScreenY());});

        findMy.setOnAction(event -> openFindMyWindow());
    }
    

       private void openFindMyWindow() {
        Stage findMyStage = new Stage();
        VBox vbox = new VBox(5);
        String owner = cellular.getOwnerName();

        findMyStage.setTitle("Find My f...");
        updateFindMyContent(vbox, owner);

        Scene scene = new Scene(vbox, 250, 200);
        findMyStage.setScene(scene);
        findMyStage.show();
        //Timeline que actualiza la ventana Findmy cada 1 segundo
        Timeline updateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            updateFindMyContent(vbox, owner);
        }));
        updateTimeline.setCycleCount(Timeline.INDEFINITE);
        updateTimeline.play();

        findMyStage.setOnCloseRequest(e -> updateTimeline.stop());
    }

private void updateFindMyContent(VBox vbox, String owner) {
    vbox.getChildren().clear();
    vbox.getChildren().add(new Text("Bienes de " + owner));
    vbox.getChildren().add(new Text("Items:"));

    HashMap<String, Point2D> items = nube.getHashMap(owner);
    if (items != null) {
        for (String device : items.keySet()) {
            Point2D pos = items.get(device);
            if (!device.equals("Celular") && !device.equals("Tablet")) {
                vbox.getChildren().add(new Text("  " + device + ": " + (int) pos.getX() + ", " + (int) pos.getY()));
            }
        }
        vbox.getChildren().add(new Text("Dispositivos:"));
        for (String device : items.keySet()) {
            Point2D pos = items.get(device);
            if (device.equals("Celular") || device.equals("Tablet")) {
                vbox.getChildren().add(new Text("  " + device + ": " + (int) pos.getX() + ", " + (int) pos.getY()));
            }
        }
    }

    }
}