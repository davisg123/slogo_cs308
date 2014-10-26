import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import Feature.Feature;

public class RefGridFeature extends Feature {
   
private static final double REFERENCE_LINE_OPACITY = 0.1;
private static final int REFERENCE_LINE_GAP = 10;
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
    
     public void drawRefGrid(Pane pane, int displayWidth, int displayHeight){
         lineList = new ArrayList<Line>();
         for (int i=0; i<=displayWidth; i=i+REFERENCE_LINE_GAP){
             lineList.add(new Line(i, 0, i, displayHeight));
             lineList.add(new Line(0,i,displayWidth,i));
         } 
         for (Line line:lineList){
             line.setOpacity(REFERENCE_LINE_OPACITY);
             pane.getChildren().add(line);
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


