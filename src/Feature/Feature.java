package Feature;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Used for adding different UI features.
 * (such as setting background color, setting pen color, loading, turning on/off reference grid)
 * @author Yoonhyung
 *
 */
public abstract class Feature{
    
    /**
     * Builds a JavaFX Button for the feature
     */
    public Button makeButton (String label, EventHandler<ActionEvent> event) {
       Button result = new Button(label);
       result.setOnAction(event);
       return result;
    }
}
