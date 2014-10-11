package MovementAndImageAPI.src;

import static org.junit.Assert.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import org.junit.Test;

public class TurtleTest {

//	@Test
//	public void testChangingTurtleImage() {
//		Turtle testTurtle = new Turtle();
//		testTurtle.updateImage("notARealFileLocation.gif");
//		if (!(testTurtle.getImage().getImage() == null)) {
//			fail("Should not have updated an image because the image location doesn't exist.");
//		}
//		testTurtle.updateImage("/caterpie.png");
//		assertNotNull(testTurtle.getImage().getImage());
//	}

	@Test
	public void testMovingDirections() {
		Turtle testTurtle1 = new Turtle();
		Turtle testTurtle2 = new Turtle();
		testTurtle1.updateAbsoluteLocation(new Point2D(0, 0));
		testTurtle1.updateAbsoluteOrientation(30);
		testTurtle2.updateAbsoluteLocation(new Point2D(0, 0));
		testTurtle2.updateAbsoluteOrientation(150);

		testTurtle1.updateLocation(10);
		testTurtle2.updateLocation(10);

		assertTrue(testTurtle1.getPoint().getX() == -1
				* testTurtle2.getPoint().getX()
				&& testTurtle1.getPoint().getY() == testTurtle2.getPoint()
						.getY());
	}
	
	@Test
	public void testValidLocations(){
		Canvas testCanvas = new Canvas(100, 100);
		ImageUpdater testUpdater = new ImageUpdater(testCanvas, new PenHandler());
		assertTrue(testUpdater.isValidPoint(new Point2D(50, 50)));
		assertTrue(!testUpdater.isValidPoint(new Point2D(400, 0)));
	}
	
	@Test
	public void testTurtleMoving(){
		
	}
	
}
