import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import Feature.Feature;
import MovementAndImageAPI.src.PenHandler;


public class PenColorFeature extends Feature {
    public ChoiceBox makeColorChoices(PenHandler penHandler){
        Color[] colorList = {Color.WHITE, Color.RED, Color.BLUE};
        ChoiceBox colorChoices = new ChoiceBox(FXCollections.observableArrayList("WHITE", "RED", "BLUE"));
        changePenColor(penHandler, colorList, colorChoices);
        return colorChoices;
    }
    
    public void changePenColor(PenHandler penHandler, Color[] colorList, ChoiceBox colorChoices){
        colorChoices.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number new_value) {
                penHandler.setPenColor(colorList[new_value.intValue()]);
            }
        });
    }
}
