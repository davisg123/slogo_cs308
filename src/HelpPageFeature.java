
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
                try {
                    myHelpPage.start(new Stage());
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
    });
    }
    
    
    
    
    
    
    
    
    
   
    
}
