
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Feature.Feature;
import HelpPage.HelpPage;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.TurtleHandler;
import java.util.logging.Logger;


public class TurtleChooserFeature extends Feature {
    
    public void openTurtleChooser(Button button, Group root, ImageUpdater imageUpdater, TurtleHandler turtleHandler){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                
                //Set extension filter
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                
                File file = fileChooser.showOpenDialog(null);
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(file);
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                
                ImageView iv = new ImageView();
                iv.setImage(image);
                
                imageUpdater.updateTurtleImage(turtleHandler.getTurtleLocation(), iv);
                
                
            }
        });
    }
}
