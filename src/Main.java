
import java.util.ArrayList;
import commands.CommandsFactory;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.Turtle;
import MovementAndImageAPI.src.TurtleHandler;
import parser.Parser;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int PREV_COMMANDBOX_HEIGHT = 200;
    private static final int PREV_COMMANDBOX_WIDTH = 200;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;

    private static final int DISPLAY_WIDTH = 700;
    private static final int DISPLAY_HEIGHT = 600;
    private Scene myScene;
    private Canvas myBackDisplay;
    private Canvas myFrontDisplay;
    private Button BGColorButton;
    private Button RefGridButton;
    private Button HelpPageButton;
    private GraphicsContext gcBack;
    private GraphicsContext gcFront;
    private String userInput;
    private boolean validInput;
    private Parser myParser;
    private Button StartButton;
    private ArrayList<String> prevCommandList;

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
               
            //adding parser? not sure now this works...!
            myParser = new Parser(new CommandsFactory());
            myParser.createLogoParser();
            
            // Add previousCommands box
            TextArea prevCommandBox = new TextArea("Previous Commands: ");
            prevCommandBox.setPrefSize(PREV_COMMANDBOX_WIDTH, PREV_COMMANDBOX_HEIGHT);
            prevCommandBox.editableProperty().set(false);
            bpane.setRight(prevCommandBox);
            prevCommandList = new ArrayList<String>();
            
            // Add textbox at bottom (temporary)
            TextField inputBox = new TextField("");
            bpane.setBottom(inputBox);
            sendUserInput(inputBox, prevCommandBox);
            
            //adding imageUpdater
            ImageUpdater frontImageUpdater = new ImageUpdater(myFrontDisplay);

            //adding my turtle
            TurtleHandler testTurtle = new TurtleHandler(frontImageUpdater);
            testTurtle.updateImage("/images/turtle.png");

            //(test) turtle knows how to move -- YESSS
//            testTurtle.updateTurtleAbsoluteLocation(new Point2D(50,0));
            
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

     /**
     * Tells the parser to parse the userInput String
     * (determined by whatever was typed in the TextField)
     * @param userInput the user input
     * @return returns true if XMLparser can parse the userInput (which means the userInput is
     valid)
     * returns false otherwise.
     */
     public boolean sendUserInput(TextField textBox, TextArea prevCommandBox){
         validInput = false;      
         textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
             @Override
             public void handle (KeyEvent key) {
                 if (key.getCode() == KeyCode.ENTER) {
                     userInput = textBox.getText();
                     //i need to send this userInput to the parser.
                     System.out.println("userInput: " + userInput);
                     try {
                        myParser.parse(userInput);
                        prevCommandList.add("\n"+userInput);
                        validInput = true;
                        showPreviousCommands(prevCommandBox);
                    }
                    catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }          
                     textBox.clear();
                 }
             }
         });
         return validInput;
     }

     /**
     * Displays a list of valid commands (that the parser could parse)
     * @param userInput the user input
     */
     public void showPreviousCommands(TextArea prevCommandBox){
         String text = "Previous Commands: ";
         for (int i=0; i<prevCommandList.size(); i++){
             text = text + prevCommandList.get(i);
         }
         prevCommandBox.setText(text);
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
