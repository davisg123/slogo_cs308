// This entire file is part of my masterpiece.
// Yoon Choi

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Builds the Slogo User Interface
 * @author Yoonhyung Choi
 *
 */
public class SlogoUserInterface {
    private static final int NUMBER_OF_WORKSPACES = 3;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    private Stage myStage;
    
    
    /**
     * Builds Slogo User Interface on the JavaFX stage
     * @param primaryStage
     */
    public SlogoUserInterface (Stage primaryStage) {
        myStage = primaryStage;
        buildUserInterface(myStage);
    }
    
    
    /**
     * Builds User Interface
     * @param primaryStage Stage where the user interface is built
     */
    private void buildUserInterface (Stage primaryStage) {
        Group root = new Group();
        BorderPane mainbpane = new BorderPane();
        TabPane tabPane = new TabPane();
        Scene myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        Map<String, Workspace> workspaceMap = new HashMap<String, Workspace>();
        
        //Adding workspaces and corresponding tabs
        for (int i = 0; i < NUMBER_OF_WORKSPACES; i++) {
            Workspace workspace = new Workspace();
            Tab tab = buildTab(primaryStage, root, tabPane, workspace, i);
            workspaceMap.put(tab.getText(), workspace);
        }
        
        //Adding keyboard control
        KeyboardControl keyboardControl = new KeyboardControl();
        keyboardControl.controlActiveTurtle(tabPane, workspaceMap);
        
        mainbpane.setCenter(tabPane);
        root.getChildren().add(mainbpane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    
    
    /**
     * Builds the tab that corresponds to the workspace
     * @param primaryStage Stage where the user interface is built
     * @param root Group where the user interface is built
     * @param tabPane TabPane where the tabs are built
     * @param workspace Workspace
     * @param workspaceNum Number of this workspace
     * @return the tab that corresponds to the workspace
     */
    private Tab buildTab (Stage primaryStage, Group root, TabPane tabPane, 
                          Workspace workspace, int workspaceNum) {
        Tab tab = workspace.createWorkspace(primaryStage, root);
        String tabLabel = "Workspace " + (workspaceNum + 1);
        tab.setText(tabLabel);
        tabPane.getTabs().add(tab);
        return tab;
    }
}
