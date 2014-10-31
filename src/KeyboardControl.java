// This entire file is part of my masterpiece.
// Yoon Choi

import java.util.Map;
import MovementAndImageAPI.src.TurtleHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Adds keyboard control for the turtle
 * @author Yoonhyung Choi
 *
 */
public class KeyboardControl {
    private static final int MOVE_DISTANCE = 10;
    private static final int ROTATE_DEGREE = 45;
    private static final String DEFAULT_TAB_LABEL = "Workspace 1";
    private TurtleHandler myActiveTurtle;
    
    
    /**
     * Adds keyboard control for the turtles.
     * @param tabPane TabPane where the tabs for the workspaces are located.
     */
    public void controlActiveTurtle (TabPane tabPane, Map<String, Workspace> workspaceMap) {
        findActiveTurtle(tabPane, workspaceMap);
        tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                if (key.getCode() == KeyCode.A) {
                    myActiveTurtle.updateTurtleOrientation(-ROTATE_DEGREE);
                }
                if (key.getCode() == KeyCode.D) {
                    myActiveTurtle.updateTurtleOrientation(ROTATE_DEGREE);
                }
                if (key.getCode() == KeyCode.W) {
                    myActiveTurtle.updateTurtleLocation(MOVE_DISTANCE);
                }
                if (key.getCode() == KeyCode.S) {
                    myActiveTurtle.updateTurtleLocation(-MOVE_DISTANCE);
                }
            }
        });
    }
    
    
    /**
     * Finds active turtles in the program
     * @param tabPane TabPane where the workspaces are located
     * @param workspaceMap Map that maps the tab label to the workspace
     */
    public void findActiveTurtle (TabPane tabPane, Map<String, Workspace> workspaceMap) {
        myActiveTurtle = workspaceMap.get(DEFAULT_TAB_LABEL).getTurtleHandler();
        for (Tab tab: tabPane.getTabs()) {
            tab.setOnSelectionChanged(new EventHandler<Event>() {
                @Override
                public void handle (Event e) {
                    Workspace currentWorkspace = workspaceMap.get(tab.getText());
                    myActiveTurtle = currentWorkspace.getTurtleHandler();
                }
            });
        }
    }
}
