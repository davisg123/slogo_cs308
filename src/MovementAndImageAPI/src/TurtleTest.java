package MovementAndImageAPI.src;

import static org.junit.Assert.*;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import org.junit.Test;

public class TurtleTest {

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
		Canvas testLineCanvas = new Canvas(100, 100);
		ImageUpdater testUpdater = new ImageUpdater(testCanvas, testLineCanvas, new PenHandler());
		assertTrue(testUpdater.isValidPoint(new Point2D(50, 50)));
		assertTrue(!testUpdater.isValidPoint(new Point2D(400, 0)));
	}

	
}
