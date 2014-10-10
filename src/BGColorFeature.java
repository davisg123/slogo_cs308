import Feature.Feature;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;

public class BGColorFeature extends Feature {

    /**
     * Change background color
     * @param button
     * @param scene
     */
    
    public ChoiceBox makeColorChoices(GraphicsContext gc, int displayWidth, int displayHeight){
        Color[] colorList = {Color.WHITE, Color.RED, Color.BLUE};
        ChoiceBox colorChoices = new ChoiceBox(FXCollections.observableArrayList("WHITE", "RED", "BLUE"));
        changeBGColor(gc, colorList, colorChoices, displayWidth, displayHeight);
        return colorChoices;
    }
    
    public void changeBGColor(GraphicsContext gc, Color[] colorList, ChoiceBox colorChoices, int displayWidth, int displayHeight){
        colorChoices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number new_value) {
                gc.setFill(colorList[new_value.intValue()]);
                gc.fillRect(0,0,displayWidth,displayHeight);  // 0 and 0 here are x,y
            }
        });
    }
    
}
