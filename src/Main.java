import MovementAndImageAPI.src.TurtleHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    private Scene myScene;
    private TurtleHandler myActiveTurtle;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
            Group root = new Group();
            BorderPane mainbpane = new BorderPane();
            myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            
            TabPane tabPane = new TabPane();
            
            for (int i=0; i<3; i++){
                Workspace workspace = new Workspace();
                Tab tab = workspace.createWorkspace(primaryStage, root);
                String tabLabel = "Workspace " + (i+1);
                tab.setText(tabLabel);
                tabPane.getTabs().add(tab);
                
            }
            
//            myActiveTurtle = workspace1.getTurtleHandler();
            
            // using arrow keys to move my turtle 
            tabPane.setOnKeyPressed(new EventHandler<KeyEvent>(){
                @Override
                public void handle (KeyEvent key) {
                    // TODO Auto-generated method stub
                    if (key.getCode() == KeyCode.LEFT){
                        myActiveTurtle.updateTurtleOrientation(-45);
                    }
                    if (key.getCode() == KeyCode.RIGHT){
                        myActiveTurtle.updateTurtleOrientation(45);
                    }
                    if (key.getCode() == KeyCode.UP){
                        myActiveTurtle.updateTurtleLocation(10);
                    }
                    if (key.getCode() == KeyCode.DOWN){
                        myActiveTurtle.updateTurtleLocation(-10);
                    }
                }
            });
              
            mainbpane.setCenter(tabPane);
            root.getChildren().add(mainbpane);
            primaryStage.setScene(myScene);
            primaryStage.show();
    }


    /**
     * the main entry point for the program.
     * 
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }

}
