import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Feature.Feature;


//not complete...

public class RefGridFeature extends Feature {
    
    public void showReferenceGrid(Button button, BorderPane root){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
//                if (event.getClickCount()%2 == 0){
                    root.setCenter(addGridPane());
//                }
                
//                if (event.getClickCount()%2 == 1){
//                    gridpane.gridLinesVisibleProperty(false);
//                }
            }
    });
    }
    
    public GridPane addGridPane(){
      GridPane gridpane = new GridPane();
      gridpane.setPrefSize(100,100);
      gridpane.setGridLinesVisible(false);
    return gridpane;
    }

    
}
