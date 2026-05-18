import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

import javafx.scene.media.AudioClip;
public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private final Circle circle;
    private final Text label;
    private AudioClip bipSound;
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
        try {
            bipSound = new AudioClip(getClass().getResource("bip.wav").toExternalForm());
        } catch (Exception e) {
            bipSound = null;
        }


        tag.bippingProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                mostrarRadar();
                tag.setBipping(false);
            }
        });
    }


    private void mostrarRadar() {
        Circle radar = new Circle(0);
        radar.setFill(Color.TRANSPARENT);
        radar.setStroke(Color.rgb(0, 120, 255, 0.4));
        radar.setStrokeWidth(2);
        radar.centerXProperty().bind(tag.xProperty());
        radar.centerYProperty().bind(tag.yProperty());
        getChildren().add(radar);
        if (bipSound != null) {
            bipSound.play();
        }
        Timeline animation = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(radar.radiusProperty(), 0)),
            new KeyFrame(Duration.seconds(1), new KeyValue(radar.radiusProperty(), 50))
        );
        animation.setOnFinished(e -> getChildren().remove(radar));
        animation.play();
    }


}
 