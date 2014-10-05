import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class is the actual visual representation of the
 *         Turtle.
 */
public class Turtle {

	/**
	 * 
	 * @param orientation
	 *            The amount to add to the current orientation/angle
	 */
	public void updateOrientation(double orientation) {
	};

	/**
	 * 
	 * @param translocation
	 *            How many pixels to move in that direction. Uses TurtleMath and
	 *            orientation to figure out how many pixels that is in the X and
	 *            Y directions.
	 */
	public void updateLocation(double translocation) {
	};

	/**
	 * 
	 * @param newImageLocation
	 *            The location of the image to be used as myImage
	 * @throws ImageNotFoundException
	 *             The location couldn't be found, so it doesn't update the
	 *             image.
	 */
	public void updateImage(String newImageLocation)
			throws ImageNotFoundException {
	};

	/**
	 * 
	 * @return the Turtle's current point
	 */
	public Point2D getPoint() {
		return null;
	};

	/**
	 * 
	 * @return the Turtle's orientation angle
	 */
	public double getOrientation() {
		return 0;
	};

	/**
	 * 
	 * @return myImage
	 */
	public ImageView getImage() {
		return null;
	};
}
