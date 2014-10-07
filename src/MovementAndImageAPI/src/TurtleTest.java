package MovementAndImageAPI.src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurtleTest {

	@Test
   	public void testChangingTurtleImage() {
         	Turtle testTurtle = new Turtle();
         	try {
                	testTurtle.updateImage("notARealFileLocation.gif");
                	fail("Should have thrown an ImageNotFoundException because that file location isn't real.");
         	} catch (ImageNotFoundException e) {
         	}
   	}
}
