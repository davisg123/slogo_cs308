import Feature.Feature;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BGColorFeature extends Feature {

    private static final int NUM_BG_COLORS = 3;
    /**
     * Change background color
     * @param button
     * @param scene
     */
    public void changeBGColor(Button button, Scene scene, Rectangle display){
        
        button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if (event.getClickCount()%NUM_BG_COLORS == 0){
                    display.setFill(Color.WHITE);
                }
                if (event.getClickCount()%NUM_BG_COLORS == 1){
                    display.setFill(Color.LAVENDER);
                }
                if (event.getClickCount()%NUM_BG_COLORS == 2){
                    display.setFill(Color.LIGHTGREEN);
                }
            }
    });
    }

}
