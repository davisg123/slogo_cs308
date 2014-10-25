import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import commands.CommandsFactory;
import commands.ICommand;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.PenHandler;
import MovementAndImageAPI.src.TurtleHandler;
import parser.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 700;
    private static final int DISPLAY_WIDTH = 700;
    private static final int DISPLAY_HEIGHT = 600;
    private Scene myScene;
    private Canvas myBackDisplay;
    private Canvas myTurtleCanvas;
    private Canvas myLineCanvas;
    private Button RefGridButton;
    private Button HelpPageButton;
    private Button TCButton;
    private Button TurtleDataButton;
    private GraphicsContext gcBack;
    private String userInput;
    private boolean validInput;
    private CommandsFactory commandsFactory = null;
    private Parser parser = null;
    private ArrayList<Text> prevCommandList;
    private ObservableList<Text> prevCommandObsvList;
    private Map<Text, ICommand> prevCommandMap;

    /**
     * the JavaFX thread entry point. Creates the Stage and scene.
     */
    @Override
    public void start (Stage primaryStage) {
        try {
            Group root = new Group();
            // Pane pane = new Pane();
            // BorderPane bpane = new BorderPane();
            BorderPane mainbpane = new BorderPane();
            myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

            TabPane tabPane = new TabPane();
            
            Tab workspace = createWorkspace(primaryStage, root, tabPane);
            tabPane.getTabs().add(workspace);
                  
            mainbpane.setCenter(tabPane);

            root.getChildren().add(mainbpane);
            primaryStage.setScene(myScene);
            primaryStage.show();

            // // Testing Turtle rotation/moving
            // testTurtle.updateTurtleOrientation(90);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tab createWorkspace (Stage primaryStage, Group root, TabPane tabPane) {
        // Create Tab
        Tab tab = new Tab();
        tab.setText("Tab");

        Pane pane = new Pane();
        BorderPane bpane = new BorderPane();

        // Add back display canvas
        myBackDisplay = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        gcBack = myBackDisplay.getGraphicsContext2D();
        pane.getChildren().add(myBackDisplay);

        // Add line display canvas
        myLineCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        pane.getChildren().add(myLineCanvas);

        // Add turtle display canvas
        myTurtleCanvas = new Canvas(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        pane.getChildren().add(myTurtleCanvas);

        // Setting display positions
        myBackDisplay.toBack();
        myTurtleCanvas.toFront();

        // Setting pane(containing the displays) to the center of the borderpane.
        bpane.setCenter(pane);

        // making penHandler
        PenHandler mainPenHandler = new PenHandler();

        // adding imageUpdater
        ImageUpdater frontImageUpdater =
                new ImageUpdater(myTurtleCanvas, myLineCanvas, mainPenHandler);

        // adding my turtle
        TurtleHandler testTurtle = new TurtleHandler(frontImageUpdater);
        testTurtle.updateImage(new
                Image(getClass().getResourceAsStream("/images/turtle.png")));

        // Add textbox at bottom (temporary)
        TextField textBox = new TextField("");
        commandsFactory = new CommandsFactory();
        commandsFactory.setTurtleHandler(testTurtle);
        parser = new Parser(commandsFactory);
        parser.createLogoParser();
        bpane.setBottom(textBox);

        // Add previousCommands box
        ListView<Text> prevCommandListView = new ListView<Text>();
        prevCommandList = new ArrayList<Text>();
        prevCommandMap = new HashMap<Text, ICommand>();
        prevCommandList.add(new Text("Previous Commands: "));
        prevCommandObsvList = FXCollections.observableArrayList(prevCommandList);
        prevCommandListView.setItems(prevCommandObsvList);
        bpane.setRight(prevCommandListView);
        sendUserInput(textBox, prevCommandListView);

        // Add Feature buttons on top
        bpane.setTop(addFeatureButtons(bpane, primaryStage, pane, mainPenHandler, testTurtle,
                                       root, frontImageUpdater));

        // final step
        tab.setContent(bpane);
        return tab;
    }

    /**
     * Adds features.
     */
    public Node addFeatureButtons (BorderPane bpane,
                                   Stage primaryStage,
                                   Pane pane,
                                   PenHandler penHandler,
                                   TurtleHandler turtleHandler,
                                   Group root,
                                   ImageUpdater iu) {

        HBox featureButtons = new HBox();

        // pen color chooser and canvas color chooser
        ColorFeature ColorOptions = new ColorFeature();
        featureButtons.getChildren().addAll(new Text(" Pen:"),
                                            ColorOptions.changePenColor(penHandler),
                                            new Text(" Canvas:"),
                                            ColorOptions.changeBGColor(gcBack, DISPLAY_WIDTH,
                                                                       DISPLAY_HEIGHT));

        RefGridFeature RefGrid = new RefGridFeature();
        RefGridButton =
                RefGrid.makeButton("RefGrid On/Off",
                                   event -> RefGrid
                                           .showReferenceGrid(RefGridButton, pane, DISPLAY_WIDTH,
                                                              DISPLAY_HEIGHT));

        HelpPageFeature HelpPage = new HelpPageFeature();
        HelpPageButton = HelpPage.makeButton("Help Page",
                                             event -> HelpPage.openHelpPage(HelpPageButton, bpane));

        TurtleChooserFeature TurtleChooser = new TurtleChooserFeature();
        TCButton =
                TurtleChooser.makeButton("Turtle Image",
                                         event -> TurtleChooser
                                                 .openTurtleChooser(TCButton, root, iu,
                                                                    turtleHandler));

        TurtleDataFeature TurtleData = new TurtleDataFeature();
        TurtleDataButton =
                TurtleData.makeButton("Turtle Data",
                                      event -> TurtleData.openTurtleDataPage(TurtleDataButton,
                                                                             turtleHandler));

        featureButtons.getChildren().addAll(RefGridButton, HelpPageButton, TCButton,
                                            TurtleDataButton);
        return featureButtons;
    }

    /**
     * Tells the parser to parse the userInput String
     * (determined by whatever was typed in the TextField)
     * 
     * @param userInput the user input
     * @return returns true if XMLparser can parse the userInput (which means the userInput is
     *         valid)
     *         returns false otherwise.
     */

    public boolean sendUserInput (TextField textBox, ListView<Text> prevCommandListView) {
        validInput = false;
        textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                ICommand command = null;
                if (key.getCode() == KeyCode.ENTER) {
                    userInput = textBox.getText();
                    try {
                        command = parser.parse(userInput);
                        command.execute();
                        validInput = true;
                        Text userInputText = new Text(userInput);
                        prevCommandList.add(userInputText);
                        prevCommandMap.put(userInputText, command);
                        setUpPreviousCommands(prevCommandListView);
                    }
                    catch (Exception e) {
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
     * 
     * @param userInput the user input
     */
    public void setUpPreviousCommands (ListView<Text> prevCommandListView) {
        prevCommandObsvList = FXCollections.observableArrayList(prevCommandList);
        prevCommandListView.setItems(prevCommandObsvList);
        for (int i = 0; i < prevCommandObsvList.size(); i++) {
            Text text = prevCommandObsvList.get(i);
            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent arg0) {
                    // TODO Auto-generated method stub
                    ICommand command = prevCommandMap.get(text);
                    command.execute();
                }
            });
        }
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
