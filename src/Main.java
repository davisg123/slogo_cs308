import java.util.HashMap;
import java.util.Map;
import MovementAndImageAPI.src.TurtleHandler;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main entry point for Slogo. 
 * Creates the Scene and adds Slogo workspaces.
 * @author Yoonhyung Choi
 *
 */
public class Main extends Application {
    private static final int NUMBER_OF_WORKSPACES = 3;
    private static final int BACK_INCREMENT = -10;
    private static final int FORWARD_INCREMENT = 10;
    private static final int RIGHT_ROTATION = 45;
    private static final int LEFT_ROTATION = -45;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    private Scene myScene;
    private TurtleHandler myActiveTurtle;
    private Map<String,Workspace> workspaceMap;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
            Group root = new Group();
            BorderPane mainbpane = new BorderPane();
            myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            
            TabPane tabPane = new TabPane();
            workspaceMap = new HashMap<String,Workspace>();
            
            for (int i=0; i<NUMBER_OF_WORKSPACES; i++){
                Workspace workspace = new Workspace();
                Tab tab = workspace.createWorkspace(primaryStage, root);
                String tabLabel = "Workspace " + (i+1);
                tab.setText(tabLabel);
                tabPane.getTabs().add(tab);
                workspaceMap.put(tab.getText(), workspace);
            }

            myActiveTurtle = workspaceMap.get("Workspace 1").getTurtleHandler();
            addKeyboardControl(tabPane);
            for (Tab tab: tabPane.getTabs()){
                tab.setOnSelectionChanged(new EventHandler<Event>(){
                    @Override
                    public void handle (Event e) {
                        Workspace currentWorkspace = workspaceMap.get(tab.getText());
                        myActiveTurtle = currentWorkspace.getTurtleHandler();
                    }
                    
                });
            }
              
            mainbpane.setCenter(tabPane);
            root.getChildren().add(mainbpane);
            primaryStage.setScene(myScene);
            primaryStage.show();
    }
    
    /**
     * Adds keyboard control for the turtles.
     * @param tabPane TabPane where the tabs for the workspaces are located.
     */
    public void addKeyboardControl(TabPane tabPane){
        tabPane.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle (KeyEvent key) {
                // TODO Auto-generated method stub
                if (key.getCode() == KeyCode.A){
                    myActiveTurtle.updateTurtleOrientation(LEFT_ROTATION);
                }
                if (key.getCode() == KeyCode.D){
                    myActiveTurtle.updateTurtleOrientation(RIGHT_ROTATION);
                }
                if (key.getCode() == KeyCode.W){
                    myActiveTurtle.updateTurtleLocation(FORWARD_INCREMENT);
                }
                if (key.getCode() == KeyCode.S){
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
