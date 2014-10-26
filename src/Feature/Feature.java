package Feature;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Used for adding different UI features.
 * @author Yoonhyung Choi
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
