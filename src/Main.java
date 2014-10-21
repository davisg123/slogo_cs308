
import java.util.ArrayList;
import commands.CommandsFactory;
import commands.ICommand;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.Pen;
import MovementAndImageAPI.src.PenHandler;
import MovementAndImageAPI.src.Turtle;
import MovementAndImageAPI.src.TurtleHandler;
import parser.*;
import input.*;
import java.util.ArrayDeque;
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
    private Canvas myTurtleCanvas;
    private Canvas myLineCanvas;
    private Button BGColorButton;
    private Button RefGridButton;
    private Button HelpPageButton;
    private Button PenColorButton;
    private Button TCButton;
    private GraphicsContext gcBack;
    private String userInput;
    private boolean validInput;
    private InputExecutor inputExecutor = null;
    private CommandsFactory commandsFactory = null;
    private Parser parser = null;
    private ArrayDeque<ICommand> commands = new ArrayDeque<>();
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
            
            //Add line display canvas
            myLineCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            pane.getChildren().add(myLineCanvas);
            
            //Add turtle display canvas
            myTurtleCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            pane.getChildren().add(myTurtleCanvas);

            //Setting display positions
            myBackDisplay.toBack();
            myTurtleCanvas.toFront();

            //Setting pane(containing the displays) to the center of the borderpane.
            bpane.setCenter(pane);
            
            //making penHandler
            PenHandler mainPenHandler = new PenHandler();
            
            //adding imageUpdater
            ImageUpdater frontImageUpdater = new ImageUpdater(myTurtleCanvas, myLineCanvas, mainPenHandler);
            
            //adding my turtle
            TurtleHandler testTurtle = new TurtleHandler(frontImageUpdater);
            testTurtle.updateImage(new Image(getClass().getResourceAsStream("/images/turtle.png")));
            
            // Add previousCommands box
            TextArea prevCommandBox = new TextArea("Previous Commands: ");
            prevCommandBox.setPrefSize(PREV_COMMANDBOX_WIDTH, PREV_COMMANDBOX_HEIGHT);
            prevCommandBox.editableProperty().set(false);
            bpane.setRight(prevCommandBox);
            prevCommandList = new ArrayList<String>();
            

            // Add textbox at bottom (temporary)
            TextField textBox = new TextField("");
            commandsFactory = new CommandsFactory();
            commandsFactory.setTurtleHandler(testTurtle);
            parser = new Parser(commandsFactory);
            parser.createLogoParser();
            bpane.setBottom(textBox);
            sendUserInput(textBox, prevCommandBox);
           



            //adding my penHandler and pen
            mainPenHandler.setPenColor(Color.RED);
            


            
            // Add Feature buttons on top
            bpane.setTop(addFeatureButtons(bpane, primaryStage, pane, mainPenHandler, testTurtle, root, frontImageUpdater));
            
            
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
    public Node addFeatureButtons (BorderPane bpane, Stage primaryStage, Pane pane, 
                                   PenHandler penHandler, TurtleHandler turtleHandler, Group root, ImageUpdater iu) {
        HBox featureButtons = new HBox();        
        
        BGColorFeature BGColor = new BGColorFeature();        
        ChoiceBox BGColorChoices = BGColor.makeColorChoices(gcBack,DISPLAY_WIDTH,DISPLAY_HEIGHT);
        BGColorButton = BGColor.makeButton("Background Color", event->BGColorChoices.show());
        
        PenColorFeature PenColor = new PenColorFeature();
        ChoiceBox PenColorChoices = PenColor.makeColorChoices(penHandler);
        PenColorButton = PenColor.makeButton("Pen Color", event->PenColorChoices.show());
        bpane.getChildren().addAll(BGColorChoices, PenColorChoices);
        
        RefGridFeature RefGrid = new RefGridFeature();
        RefGridButton =
                RefGrid.makeButton("RefGrid On/Off",
                                   event -> RefGrid.showReferenceGrid(RefGridButton, pane, DISPLAY_WIDTH, DISPLAY_HEIGHT));

        HelpPageFeature HelpPage = new HelpPageFeature();
        HelpPageButton =
                HelpPage.makeButton("Help Page",
                                    event -> HelpPage.openHelpPage(HelpPageButton, bpane));
        
        TurtleChooserFeature TurtleChooser = new TurtleChooserFeature();
        TCButton = TurtleChooser.makeButton("TC", event-> TurtleChooser.openTurtleChooser(TCButton, root, iu, turtleHandler));
        featureButtons.getChildren().addAll(BGColorButton, PenColorButton, RefGridButton, HelpPageButton, TCButton);

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
                 ICommand command = null;
                 if (key.getCode() == KeyCode.ENTER) {
                     userInput = textBox.getText();
                     //i need to send this userInput to the parser.
//                     userInput += "\r\n";
                     System.out.println("userInput: " + userInput);
                     try {

//                         commandsFactory.turtleGoForward(500);
                         command = parser.parse(userInput);
                         command.execute();
                         System.out.println("i should've moved forward");
                        validInput = true;
                        prevCommandList.add("\n" + userInput);
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
