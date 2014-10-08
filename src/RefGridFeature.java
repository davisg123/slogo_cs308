import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import Feature.Feature;

public class RefGridFeature extends Feature {
   
private ArrayList<Line> lineList;
 
    boolean gridOn = true;
    public void showReferenceGrid(Button button, BorderPane root, int displayWidth, int displayHeight){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if (gridOn){
                    drawRefGrid(root, displayWidth, displayHeight);
                }
                else{
                    removeRefGrid();
                }
            }
    });
    }
        
    //Need to clean this code...but code works for now.
     public void drawRefGrid(BorderPane root, int displayWidth, int displayHeight){
         lineList = new ArrayList<Line>();
         int vert_difference = (700-displayHeight)/2;   //700=screenheight
         int horiz_difference = (1000-displayWidth)/2;
         for (int i=0; i<=displayWidth; i=i+10){
             //vertical lines
             Line line = new Line();
             line.setStartX(i);
             line.setStartY(vert_difference);
             line.setEndX(i); //x stays same as start
             line.setEndY(displayHeight+vert_difference); //y is different
             line.setOpacity(0.1);
             root.getChildren().add(line);
             lineList.add(line);
         }
         for (int i=0; i<=displayHeight; i=i+10) {
             //horizontal lines
             Line line = new Line();
             line.setStartX(horiz_difference);
             line.setStartY(i+vert_difference);
             line.setEndX(displayWidth+horiz_difference); //x is different
             line.setEndY(i+vert_difference); //y stays same as start
             line.setOpacity(0.1);
             root.getChildren().add(line);
             lineList.add(line);
         }
         gridOn = false;
     }
     
     public void removeRefGrid() {
         for (Line line:lineList){
             line.setOpacity(0);
         }
         gridOn = true;
     }
}


