import Feature.Feature;
import MovementAndImageAPI.src.PenHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * Implements ColorPicker to let the user choose pen color and background color.
 * @author Yoonhyung
 *
 */
public class ColorFeature extends Feature {
    final ColorPicker penColorPicker = new ColorPicker();
    final ColorPicker BGColorPicker = new ColorPicker();

    public ColorPicker changePenColor (PenHandler penHandler) {
        penColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent arg0) {
                penHandler.setPenColor(penColorPicker.getValue());
            }
        });
        penColorPicker.setValue(Color.BLACK);
        return penColorPicker;
    }

    public ColorPicker changeBGColor (GraphicsContext gc, int displayWidth, int displayHeight) {
        BGColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent arg0) {
                gc.setFill(BGColorPicker.getValue());
                gc.fillRect(0, 0, displayWidth, displayHeight);  // 0 and 0 here are x,y
            }
        });
        return BGColorPicker;
    }
}
