import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Feature.Feature;
import MovementAndImageAPI.src.TurtleHandler;

/**
 * Allows the user to access the turtle information (x position, y position, rotation)
 * @author Yoonhyung Choi
 *
 */
public class TurtleDataFeature extends Feature {
    private static final int PAGE_HEIGHT = 300;
    private static final int PAGE_WIDTH = 300;

    /**
     * Opens the turtle data page when the button is clicked.
     * @param button Associated button
     * @param myTurtleHandler TurtleHandler that controls the turtle
     */
    public void openTurtleDataPage(Button button, TurtleHandler myTurtleHandler){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                showStage(myTurtleHandler);
            }
    });
    }
    
    /**
     * Displays the turtle data
     * @param myTurtleHandler TurtleHandler that controls the turtle
     */
    public void showStage(TurtleHandler myTurtleHandler){
        Stage turtleDataStage = new Stage();
        turtleDataStage.setTitle("Turtle Data");
        TextArea textArea = new TextArea();
        String turtleX = "\n Turtle's X-Position: " + (int) myTurtleHandler.getTurtleLocation().getX();
        String turtleY = "\n Turtle's Y-Position: " + (int) myTurtleHandler.getTurtleLocation().getY();
        String turtleRotation = "\n Turtle's Rotation: " + (int) myTurtleHandler.getOrientation() + " degrees";
        textArea.setText(turtleX + turtleY + turtleRotation);
        Scene scene = new Scene(textArea, PAGE_WIDTH, PAGE_HEIGHT);
        turtleDataStage.setScene(scene);
        turtleDataStage.show();
        }
}
