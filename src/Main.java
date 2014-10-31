// This entire file is part of my masterpiece.
// Yoon Choi

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for Slogo. 
 * @author Yoonhyung Choi
 *
 */
public class Main extends Application {
    /**
     * Launches the Slogo user interface.
     */
    @Override
    public void start (Stage primaryStage) {
        new SlogoUserInterface(primaryStage);
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
