package HelpPage;

import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * Builds the JavaFX stage and scene for the HTML-formatted Help page.
 * 
 * @author Yoonhyung
 *
 */
public class HelpPage extends Application {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    private int BROWSER_WIDTH = 800;
    private int BROWSER_HEIGHT = 500;
    private Scene myScene;

    /**
     * Creates the JavaFX Stage
     */
    @Override
    public void start (Stage stage){
        stage.setTitle("HELP PAGE");
        myScene = new Scene(new Browser(), BROWSER_WIDTH, BROWSER_HEIGHT, Color.web("#666970"));
        stage.setScene(myScene);
        stage.show();
    }
}

/**
 * Creates Browser for the help page.
 * 
 * @author Yoonhyung
 *
 */
class Browser extends Region {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    public Browser () {
                webEngine
                        .load("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");
//                    webEngine
//                    .load("http://www.cs.dukddu/courses/compsci308/current/assign/03_slogo/commands.php");
                getChildren().add(browser);
        
    }
}
