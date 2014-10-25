import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import parser.Parser;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.PenHandler;
import MovementAndImageAPI.src.TurtleHandler;
import commands.CommandsFactory;
import commands.ICommand;


public class Workspace {
    private TurtleHandler myTurtleHandler; 
    private Canvas myBackDisplay;
    private Canvas myLineCanvas;
    private Canvas myTurtleCanvas;
    private CommandsFactory commandsFactory;
    private Parser parser;

    public Workspace(){
        
        
    }
    
    public Tab createWorkspace (Stage primaryStage, Group root, TabPane tabPane) {
        // Create Tab
        Tab tab = new Tab();
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

//        // adding my turtle
//        TurtleHandler testTurtle = new TurtleHandler(frontImageUpdater);
//        testTurtle.updateImage(new
//                Image(getClass().getResourceAsStream("/images/turtle.png")));


        // setting textbox settings
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
    
    
}
