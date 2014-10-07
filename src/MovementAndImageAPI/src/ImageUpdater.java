package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class updates the actual image placement in the
 *         Scene.
 */
public class ImageUpdater {

	/**
	 * 
	 * @param newLocation
	 *            the location to move the Turtle's image to.
	 * @param turtleImage
	 *            the actual image to move. Doesn't throw an OutOfSceneException
	 *            because that should be handled by the TurtleHandler.
	 */
	public void updateTurtleImage(Point2D newLocation, ImageView turtleImage) {
	};

	/**
	 * 
	 * @param newColor
	 *            The new color for the background of the scene.
	 */
	public void setBackgroundColor(Color newColor) {
	};

	/**
	 * 
	 * @param from
	 *            the starting point of the line
	 * @param to
	 *            the ending point of the line. If the ending point will end up
	 *            being outside of the view, it will recursively call itself
	 *            again to draw as far as possible, then to draw another line.
	 */
	public void drawLine(Point2D from, Point2D to) {
	}

}
