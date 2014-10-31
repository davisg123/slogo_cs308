// This entire file is part of my masterpiece.
// Eirika Sawh
package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class GeneralTurtleHandler {

	/**
	 * 
	 * @return the Turtle's current orientation angle.
	 */
	public abstract double getOrientation();

	/**
	 * 
	 * @return the Point2D associated with the Turtle's actual location,
	 *         regardless of the bounds of the canvas it's in.
	 */
	public abstract Point2D getTurtleLocation();

	/**
	 * 
	 * @param translocation
	 *            the double amount of how many pixels to move forwards.
	 *            Positive = move forwards, negative = move backwards
	 */
	public abstract void updateTurtleLocation(double translocation);

	/**
	 * 
	 * @param newLocation
	 *            the location to instantly move to.
	 */
	public abstract void updateTurtleAbsoluteLocation(Point2D newLocation);

	/**
	 * 
	 * @param rotation
	 *            the amount of degrees to rotate the Turtle (clockwise)
	 */
	public abstract void updateTurtleOrientation(double rotation);

	/**
	 * 
	 * @param newAngle
	 *            the orientation angle to immediately change to.
	 */
	public abstract void updateTurtleAbsoluteOrientation(double newAngle);

	/**
	 * 
	 * @param show
	 *            1 if should show the turtle, 0 if should hide the turtle
	 */
	public abstract void showTurtle(int show);

	/**
	 * 
	 * @param newImage
	 *            The new image to be drawn on the turtle's canvas
	 */
	public abstract void updateImage(Image newImage);

	/**
	 * Clears the lines associated with the Turtle.
	 */
	public abstract void clearLines();

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public abstract void setPenPosition(int penPosition);

	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 */
	public abstract int getPenPosition();

	/**
	 * 
	 * @return 1 if the turtle is showing (visible), 0 if hiding (invisible)
	 */
	public abstract int getShowing();

	/**
	 * 
	 * @return the PenHandler associated with the Turtle
	 */
	public abstract PenHandler getPenHandler();

	/**
	 * 
	 * @param width
	 *            the line width in pixels
	 */
	public abstract void setLineWidth(double width);

	/**
	 * 
	 * @param location
	 *            the point to be looking towards. If the location is outside
	 *            the canvas, the turtle faces where that point would lie
	 *            outside of the canvas (the destination doesn't loop to fit
	 *            within the canvas).
	 */
	public abstract void towards(Point2D location);
}
