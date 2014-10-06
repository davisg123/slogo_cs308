
import resources.BGColorFeature;
import Feature.Feature;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    
    private static final int DISPLAY_WIDTH = 1000;
    private static final int DISPLAY_HEIGHT = 600;
    private Scene myScene;
    private Rectangle myDisplay;
    private Button BGColorButton;
    
    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start(Stage primaryStage) {
            try {
                    BorderPane root = new BorderPane();
                    myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
                    
                    //Add textbox at bottom
                    TextField textBox = new TextField("Type command here...");   
                    root.setBottom(textBox);

                    //Add display box in center. (Using a rectangle for now...)
                    myDisplay = new Rectangle(DISPLAY_WIDTH,DISPLAY_HEIGHT,Color.WHITE);
                    root.setCenter(myDisplay);
                    
                    //Add Feature buttons on top
                    root.setTop(addFeatureButtons(root));
          
                    primaryStage.setScene(myScene);
                    primaryStage.show();

            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    /**
     * Adds features.
     */
    public Node addFeatureButtons(BorderPane root){        
        HBox featureButtons = new HBox();
        BGColorFeature BGColor = new BGColorFeature();
        BGColorButton = BGColor.makeButton("Background Color", event -> BGColor.changeBGColor(BGColorButton, myScene, myDisplay));
        featureButtons.getChildren().add(BGColorButton);
        return featureButtons;
    }
    
//    /**
//     * Returns a button that launches the HTML Help Page
//     * @param stage the primary stage
//     * @param root the root to have the added button
//     * @returns a button that launches the HTML help page.
//     */
//    public launchHelpPage (Stage stage, BorderPane root){
//    }
   
    
    
//    /**
//    * The main animation loop. Updates one total frame.
//    * @param root the root to have the updated display
//    */
//    public void advanceOneFrame (BorderPane root){
//        
//    }
      
//    /**
//     * Tells the parser to parse the userInput String 
//     * (determined by whatever was typed in the TextField)
//     * @param userInput the user input
//     * @return returns true if XMLparser can parse the userInput (which means the userInput is valid)
//     * returns false otherwise. 
//     */
//    public boolean sendUserInput(String userInput){
//    }
    
//    /** 
//     * Displays a list of valid commands (valid userInputs that the XMLparser could parse)
//     * @param userInput the user input
//     */
//    public void showPreviousCommands(String userInput){
//        
//    }
     
    
    /**
     * the main entry point for the program.
     * @param args
     */
    public static void main(String[] args) {
            launch(args);
    }
}
