import static org.junit.Assert.*;
import org.junit.Test;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.TurtleHandler;


public class TestCode {
    
       public static TurtleHandler testTurtle;
       
       @Test
       public void testTurtleMovement() {
           Canvas myCanvas = new Canvas(800, 800);
           testTurtle = new TurtleHandler(new ImageUpdater(myCanvas, myCanvas));
           testTurtle.updateImage(new Image(getClass().getResourceAsStream("/images/turtle.png")));
           testTurtle.updateTurtleAbsoluteLocation(new Point2D(0,0));
           
           testTurtle.updateTurtleLocation(100);
           assertTrue("Turtle is at location (0,100)", 
                      testTurtle.getTurtleLocation().getX() == 100 
                      && testTurtle.getTurtleLocation().getY() == 0);
       }
       
       @Test
       public void testTurtleRotation() {
           Canvas myCanvas = new Canvas(800, 800);
           testTurtle = new TurtleHandler(new ImageUpdater(myCanvas, myCanvas));
           testTurtle.updateImage(new Image(getClass().getResourceAsStream("/images/turtle.png")));
           testTurtle.updateTurtleAbsoluteLocation(new Point2D(0,0));
           
           testTurtle.updateTurtleOrientation(45);
           assertTrue("Turtle's orientation is 45", testTurtle.getOrientation() == 45);
       }
}
