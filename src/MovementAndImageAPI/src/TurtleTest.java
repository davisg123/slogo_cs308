// This entire file is part of my masterpiece.
// Eirika Sawh
package MovementAndImageAPI.src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TurtleTest {
	public static TurtleHandler singleTurtle;
	public static TurtleGroup groupOfTurtles;

	@BeforeClass
	public static void setup() {
		singleTurtle = new TurtleHandler(new ImageUpdater(new Canvas(500, 500),
				new Canvas(500, 500)));
		groupOfTurtles = new TurtleGroup();
		for (int i = 0; i < 3; i++) {
			groupOfTurtles.addNewTurtle(new TurtleHandler(new ImageUpdater(
					new Canvas(500, 500), new Canvas(500, 500))));
		}
	}

	@Test
	public void testChangingMultipleTurtles() {
		singleTurtle.updateImage(new Image(getClass().getResourceAsStream(
				"/images/turtle.png")));
		groupOfTurtles.updateImage(new Image(getClass().getResourceAsStream(
				"/images/turtle.png")));
		singleTurtle.updateTurtleAbsoluteLocation(new Point2D(100, 0));
		groupOfTurtles.updateTurtleAbsoluteLocation(new Point2D(100, 0));
		Map<Integer, TurtleHandler> activeTurtles = groupOfTurtles
				.getActiveTurtles();
		assertTrue("All the turtles should have the same point", singleTurtle
				.getTurtleLocation().getX() == activeTurtles.get(2)
				.getTurtleLocation().getX()
				&& singleTurtle.getTurtleLocation().getX() == activeTurtles
						.get(3).getTurtleLocation().getX()
				&& singleTurtle.getTurtleLocation().getX() == activeTurtles
						.get(4).getTurtleLocation().getX());
	}

	@Test
	public void testChangingActiveTurtles() {
		TurtleGroup newGroupOfTurtles = new TurtleGroup();
		for (int i = 0; i < 3; i++) {
			newGroupOfTurtles.addNewTurtle(new TurtleHandler(new ImageUpdater(
					new Canvas(500, 500), new Canvas(500, 500))));
		}
		newGroupOfTurtles.updateImage(new Image(getClass().getResourceAsStream(
				"/images/turtle.png")));
		newGroupOfTurtles.updateTurtleAbsoluteLocation(new Point2D(100, 0));

		List<Integer> newActiveTurtles = new ArrayList<Integer>();
		newActiveTurtles.add(5);
		newActiveTurtles.add(6);
		newGroupOfTurtles.setActiveTurtles(newActiveTurtles);
		newGroupOfTurtles.updateTurtleAbsoluteLocation(new Point2D(200, 0));
		Map<Integer, TurtleHandler> allTurtles = newGroupOfTurtles
				.getAllTurtles();
		assertTrue(
				"The 5 and 6 turtles should have changed their points to 200, whereas the 7 turtle should have kept the 100 point",
				allTurtles.get(5).getTurtleLocation().getX() == allTurtles
						.get(6).getTurtleLocation().getX()
						&& allTurtles.get(5).getTurtleLocation().getX() != allTurtles
								.get(7).getTurtleLocation().getX());
	}
}
