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

        findMy.setOnAction(event -> {
            Stage findMyStage = new Stage();
             VBox vbox = new VBox(5);
    
            String owner = cellular.getOwnerName();
            vbox.getChildren().add(new Text("Bienes de " + owner));
    
            HashMap<String, Point2D> items = nube.getHashMap(owner);
            if (items != null) {
               for (String device : items.keySet()) {
                   Point2D pos = items.get(device);
                    vbox.getChildren().add(new Text(device + ": " + (int)pos.getX() + ", " + (int)pos.getY()));
                }
            }
    
            Scene scene = new Scene(vbox, 200, 150);
            findMyStage.setTitle("Find My f...");
            findMyStage.setScene(scene);
            findMyStage.show();
        });

    }
}