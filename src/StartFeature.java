import parser.FilePicker;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Feature.Feature;


public class StartFeature extends Feature {
    FilePicker filePicker = new FilePicker();
    public void startLogo(Button button,Stage primaryStage){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                    filePicker.start(primaryStage);
            }
    });
    } 
}
