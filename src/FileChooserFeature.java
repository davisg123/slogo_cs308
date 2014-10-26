import java.io.File;
import parser.Parser;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import Feature.Feature;



public class FileChooserFeature extends Feature {
    private File loadedFile;
    
    public void openFileChooser(Button fileChooserButton, Parser parser){
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml", "*.logo");
        fileChooser.getExtensionFilters().add(extFilter);
       
        //Show open file dialog
        loadedFile = fileChooser.showOpenDialog(null);
        try {
            if (loadedFile != null){
                parser.parseFile(loadedFile);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
