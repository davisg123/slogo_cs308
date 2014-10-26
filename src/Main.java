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
    private static final int BACK_INCREMENT = -10;
    private static final int FORWARD_INCREMENT = 10;
    private static final int RIGHT_ROTATION = 45;
    private static final int LEFT_ROTATION = -45;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    private Scene myScene;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
        Group root = new Group();
        BorderPane mainbpane = new BorderPane();
        myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        TabPane tabPane = new TabPane();

        Workspace workspace1 = new Workspace();
        Tab tab1 = workspace1.createWorkspace(primaryStage, root);
        tab1.setText("Workspace 1");
        tabPane.getTabs().add(tab1);

        // Workspace workspace2 = new Workspace();
        // Tab tab2 = workspace2.createWorkspace(primaryStage, root, tabPane);
        // tabPane.getTabs().add(tab2);

        TurtleHandler myActiveTurtle = workspace1.getTurtleHandler();
        addKeyboardControls(myActiveTurtle, tabPane);

        mainbpane.setCenter(tabPane);
        root.getChildren().add(mainbpane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void addKeyboardControls (TurtleHandler myActiveTurtle, TabPane tabPane) {
        // using arrow keys to move my turtle
        tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                if (key.getCode() == KeyCode.LEFT) {
                    myActiveTurtle.updateTurtleOrientation(LEFT_ROTATION);
                }
                if (key.getCode() == KeyCode.RIGHT) {
                    myActiveTurtle.updateTurtleOrientation(RIGHT_ROTATION);
                }
                if (key.getCode() == KeyCode.UP) {
                    myActiveTurtle.updateTurtleLocation(FORWARD_INCREMENT);
                }
                if (key.getCode() == KeyCode.DOWN) {
                    myActiveTurtle.updateTurtleLocation(BACK_INCREMENT);
                }
            }

        });
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
