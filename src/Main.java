
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    
    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start(Stage primaryStage) {
            try {
                    BorderPane root = new BorderPane();
                    Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
//                    scene.setFill(Color.BLUE);
                    primaryStage.setScene(scene);
                    primaryStage.show();
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    
    /**
     * the main entry point for the program.
     * @param args
     */
    public static void main(String[] args) {
            launch(args);
    }

}
