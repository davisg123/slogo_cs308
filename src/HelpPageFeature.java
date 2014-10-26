
import Feature.Feature;
import HelpPage.HelpPage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Allows user to access the HTML formatted help page.
 * @author Yoonhyung Choi
 *
 */
public class HelpPageFeature extends Feature {

    /**
     * Opens up HTML formatted help page.
     * @param button Button associated with the help page
     */
    public void openHelpPage(Button button){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                HelpPage myHelpPage = new HelpPage();
                myHelpPage.start(new Stage());
            }
    });
    }
}
