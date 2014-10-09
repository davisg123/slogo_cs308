package MovementAndImageAPI.src;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurtleTest {

	@Test
	public void testChangingTurtleImage() {
		Turtle testTurtle = new Turtle();
		testTurtle.updateImage("notARealFileLocation.gif");
		if (!(testTurtle.getImage().getImage() == null)) {
			fail("Should not have updated an image because the image location doesn't exist.");
		}
	}
}
