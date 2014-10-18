
import Feature.Feature;
import HelpPage.HelpPage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class HelpPageFeature extends Feature {

    
    public void openHelpPage(Button button, BorderPane root){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                HelpPage myHelpPage = new HelpPage();
                myHelpPage.start(new Stage());
                
            }
    });
    }
}
