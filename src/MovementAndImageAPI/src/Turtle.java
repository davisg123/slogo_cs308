package MovementAndImageAPI.src;

import java.io.File;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class is the actual visual representation of the
 *         Turtle.
 */
public class Turtle {

	private static final double TOTAL_NUMBER_OF_DEGREES = 360;
	private Point2D myLocation;
	private double myOrientation;
	private ImageView myImage;

	public Turtle() {
		myLocation = new Point2D(0, 0);
		myOrientation = 0;
		myImage = new ImageView();
	}

	/**
	 * 
	 * @param orientation
	 *            The amount to add to the current orientation/angle
	 */
	public void updateOrientation(double orientation) {
		myOrientation = (myOrientation + orientation) % TOTAL_NUMBER_OF_DEGREES;
		finishRotation();
	}

	/**
	 * Ensures a positive orientation angle (ex: 359 degrees instead of -1
	 * degree) for clarity
	 */
	private void ensurePositiveOrientation() {
		if (myOrientation < 0) {
			myOrientation += TOTAL_NUMBER_OF_DEGREES;
		}
	}

	/**
	 * 
	 * @param newAngle
	 *            The angle to immediately change to.
	 */
	public void updateAbsoluteOrientation(double newAngle) {
		myOrientation = newAngle % TOTAL_NUMBER_OF_DEGREES;
		finishRotation();
	}

	/**
	 * Makes sure the orientation is a positive angle, then rotates the
	 * imageview accordingly.
	 */
	private void finishRotation() {
		ensurePositiveOrientation();
		myImage.setRotate(myOrientation);
	}

	/**
	 * 
	 * @param translocation
	 *            How many pixels to move in that direction. Uses TurtleMath and
	 *            orientation to figure out how many pixels that is in the X and
	 *            Y directions.
	 */
	public void updateLocation(double translocation) {
		myLocation = new Point2D(
				(myLocation.getX() + (translocation * Math.cos(Math.toRadians(myOrientation)))),
				(myLocation.getY() + (translocation * Math.sin(Math.toRadians(myOrientation)))));
	}

	/**
	 * 
	 * @param newLocation
	 *            The location to instantly move to.
	 */
	public void updateAbsoluteLocation(Point2D newLocation) {
		myLocation = newLocation;
	}

	/**
	 * 
	 * @param newImageLocation
	 *            The location of the image to be used as myImage
	 * @throws ImageNotFoundException
	 *             The location couldn't be found, so it doesn't update the
	 *             image.
	 */
	public void updateImage(String newImageLocation) {
//		try {
//			File turtleImageFile = new File(newImageLocation);
//			if (!turtleImageFile.exists())
//				throw new ImageNotFoundException();
			myImage.setImage(new Image(getClass().getResourceAsStream(
					newImageLocation)));
//		} catch (ImageNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
	}

	/**
	 * 
	 * @return the Turtle's current point
	 */
	public Point2D getPoint() {
		return myLocation;
	}

	/**
	 * 
	 * @return the Turtle's orientation angle
	 */
	public double getOrientation() {
		return myOrientation;
	}

	/**
	 * 
	 * @return myImage
	 */
	public ImageView getImage() {
		return myImage;
	}
}
