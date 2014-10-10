
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.Turtle;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;

    private static final int DISPLAY_WIDTH = 1000;
    private static final int DISPLAY_HEIGHT = 600;
    private Scene myScene;
    private Canvas myBackDisplay;
    private Canvas myFrontDisplay;
    private Button BGColorButton;
    private Button RefGridButton;
    private Button HelpPageButton;
    private GraphicsContext gcBack;
    private GraphicsContext gcFront;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
        try {
            Group root = new Group();
            Pane pane = new Pane();
            BorderPane bpane = new BorderPane();
            myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            
            //Add back display canvas
            myBackDisplay = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            gcBack = myBackDisplay.getGraphicsContext2D();
            pane.getChildren().add(myBackDisplay);
            
            //Add front display canvas
            myFrontDisplay = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            gcFront = myFrontDisplay.getGraphicsContext2D();
            pane.getChildren().add(myFrontDisplay);

            
            //Setting display positions
            myBackDisplay.toBack();
            myFrontDisplay.toFront();

            //Setting pane(containing the displays) to the center of the borderpane.
            bpane.setCenter(pane);
            
            // Add Feature buttons on top
            bpane.setTop(addFeatureButtons(bpane, primaryStage, pane));
               
            // Add textbox at bottom (temporary)
            TextField textBox = new TextField(">");
            bpane.setBottom(textBox);
            
            //adding imageUpdater
            ImageUpdater frontImageUpdater = new ImageUpdater(myFrontDisplay);
            Point2D testP = new Point2D(0,0);

            //adding my turtle
            Turtle testTurtle = new Turtle();
            testTurtle.updateAbsoluteLocation(testP);
            testTurtle.updateImage("/images/turtle.png");
            
            // turtle image updating
            frontImageUpdater.updateTurtleImage(testTurtle.getPoint(), testTurtle.getImage());
            
            
            // Setting up layers
            root.getChildren().add(bpane);
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
    public Node addFeatureButtons (BorderPane bpane, Stage primaryStage, Pane pane) {
        HBox featureButtons = new HBox();

        BGColorFeature BGColor = new BGColorFeature();        
        ChoiceBox BGColorChoices = BGColor.makeColorChoices(gcBack,DISPLAY_WIDTH,DISPLAY_HEIGHT);
        bpane.getChildren().add(BGColorChoices);
        BGColorButton = BGColor.makeButton("Show Background Color Options", event->BGColorChoices.show());
        

        //should fix this for canvas
        RefGridFeature RefGrid = new RefGridFeature();
        RefGridButton =
                RefGrid.makeButton("RefGrid On/Off",
                                   event -> RefGrid.showReferenceGrid(RefGridButton, pane, DISPLAY_WIDTH, DISPLAY_HEIGHT));

        HelpPageFeature HelpPage = new HelpPageFeature();
        HelpPageButton =
                HelpPage.makeButton("Help Page",
                                    event -> HelpPage.openHelpPage(HelpPageButton, bpane));

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
