import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Feature.Feature;
import MovementAndImageAPI.src.TurtleHandler;


public class TurtleDataFeature extends Feature {
    private static final int DISPLAY_HEIGHT = 300;
    private static final int DISPLAY_WIDTH = 300;

    public void openTurtleDataPage(Button button, TurtleHandler myTurtleHandler){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                showStage(myTurtleHandler);
            }
    });
    }
    
    public void showStage(TurtleHandler myTurtleHandler){
        Stage turtleDataStage = new Stage();
        turtleDataStage.setTitle("Turtle Data");
        TextArea textArea = new TextArea();
        String turtleX = "\n Turtle's X-Position: " + (int) myTurtleHandler.getTurtleLocation().getX();
        String turtleY = "\n Turtle's Y-Position: " + (int) myTurtleHandler.getTurtleLocation().getY();
        String turtleRotation = "\n Turtle's Rotation: " + (int) myTurtleHandler.getOrientation() + " degrees";
        textArea.setText(turtleX + turtleY + turtleRotation);
        Scene scene = new Scene(textArea, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        turtleDataStage.setScene(scene);
        turtleDataStage.show();
        }
}
