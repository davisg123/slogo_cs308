import java.util.List;
import Feature.Feature;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BGColorFeature extends Feature {

    private static final int NUM_BG_COLORS = 3;
    /**
     * Change background color
     * @param button
     * @param scene
     */
    
    public ChoiceBox makeColorChoices(Rectangle myDisplay){
        Color[] colorList = {Color.WHITE, Color.RED, Color.BLUE};
        ChoiceBox colorChoices = new ChoiceBox(FXCollections.observableArrayList("WHITE", "RED", "BLUE"));
        changeBGColor(myDisplay, colorList, colorChoices);
        return colorChoices;
    }
    
    public void changeBGColor(Rectangle myDisplay, Color[] colorList, ChoiceBox colorChoices){
        colorChoices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number new_value) {
//                GraphicsContext gc = myDisplay.getGraphicsContext2D();
//                gc.setFill(colorList[new_value.intValue()]);
                myDisplay.setFill(colorList[new_value.intValue()]);
            }
        });
    }
}
