import Feature.Feature;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Button RefGridButton;
    private Button HelpPageButton;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
        try {
            
            BorderPane root = new BorderPane();
            myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            
            // Add textbox at bottom (temporary)
            TextField textBox = new TextField("Type command here...");
            root.setBottom(textBox); 

//            // Add display box in center. (Using a rectangle for now...)
            myDisplay = new Rectangle(DISPLAY_WIDTH, DISPLAY_HEIGHT, Color.WHITE);
            root.setCenter(myDisplay);

            // Add Feature buttons on top
            root.setTop(addFeatureButtons(root, primaryStage));
            root.setCenter(myDisplay);
            
            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds features.
     */
    public Node addFeatureButtons (BorderPane root, Stage primaryStage) {
        HBox featureButtons = new HBox();

        BGColorFeature BGColor = new BGColorFeature();        
        ChoiceBox BGColorChoices = BGColor.makeColorChoices(myDisplay);
        root.getChildren().add(BGColorChoices);
        BGColorButton = BGColor.makeButton("Show Background Color Options", event->BGColorChoices.show());
        

        // ? this doesn't work yet.
        RefGridFeature RefGrid = new RefGridFeature();
        RefGridButton =
                RefGrid.makeButton("RefGrid On/Off",
                                   event -> RefGrid.showReferenceGrid(RefGridButton, root, DISPLAY_WIDTH, DISPLAY_HEIGHT));

        HelpPageFeature HelpPage = new HelpPageFeature();
        HelpPageButton =
                HelpPage.makeButton("Help Page",
                                    event -> HelpPage.openHelpPage(HelpPageButton, root));

        featureButtons.getChildren().addAll(BGColorButton, RefGridButton, HelpPageButton);

        return featureButtons;
    }

    // /**
    // * The main animation loop. Updates one total frame.
    // * @param root the root to have the updated display
    // */
    // public void advanceOneFrame (BorderPane root){
    //
    // }

    // /**
    // * Tells the parser to parse the userInput String
    // * (determined by whatever was typed in the TextField)
    // * @param userInput the user input
    // * @return returns true if XMLparser can parse the userInput (which means the userInput is
    // valid)
    // * returns false otherwise.
    // */
    // public boolean sendUserInput(String userInput){
    // }

    // /**
    // * Displays a list of valid commands (valid userInputs that the XMLparser could parse)
    // * @param userInput the user input
    // */
    // public void showPreviousCommands(String userInput){
    //
    // }

    /**
     * the main entry point for the program.
     * 
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
    
    
    //make imageupdater
    //when i make turtle handler, pass in imageupdater
    //when i make imageupdater pass scene to imageupdater
    //make turtle handler 
    
    
}
