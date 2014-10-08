import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import Feature.Feature;

public class RefGridFeature extends Feature {
   
private ArrayList<Line> lineList;
boolean gridOn = true;
    public void showReferenceGrid(Button button,Pane pane, int displayWidth, int displayHeight){
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if (gridOn){
                    drawRefGrid(pane, displayWidth, displayHeight);
                }
                else{
                    removeRefGrid();
                }
            }
    });
    }
            
//    //Need to clean this code...but code works for now.
     public void drawRefGrid(Pane pane, int displayWidth, int displayHeight){
         lineList = new ArrayList<Line>();
         //vertical lines
         for (int i=0; i<=displayWidth; i=i+10){
             Line line = new Line(i, 0, i, displayHeight);
             line.setOpacity(0.1);
             pane.getChildren().add(line);
             lineList.add(line);
         }
         //horizontal lines
         for (int i=0; i<=displayHeight; i=i+10) {
             Line line = new Line(0,i,displayWidth,i);
             line.setOpacity(0.1);
             pane.getChildren().add(line);
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


