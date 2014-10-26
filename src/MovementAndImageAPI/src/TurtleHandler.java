package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * 
 * @author Eirika Sawh This class handles the actual position of the Turtle, but
 *         not updating the image.
 */
public class TurtleHandler {

	private Turtle mainTurtle = new Turtle();
	private ImageUpdater mainImageUpdater;
	private static int turtleID = 1;
	private int myID;
	private static double RADIAL_CONVERT = 180/Math.PI;

	public TurtleHandler(ImageUpdater imageUpdater) {
		mainImageUpdater = imageUpdater;
		myID = turtleID;
		turtleID++;
	}

	/**
	 * 
	 * @return the Turtle's current orientation angle.
	 */
	public double getOrientation() {
		return mainTurtle.getOrientation();
	}

	/**
	 * 
	 * @return the Point2D associated with the Turtle's actual location,
	 *         regardless of the bounds of the canvas it's in.
	 */
	public Point2D getTurtleLocation() {
		return mainTurtle.getPoint();
	}

	/**
	 * 
	 * @return the Point2D where the Turtle would be in the canvas, as opposed
	 *         to its actual location (although the two might be the same).
	 */
	public Point2D getTurtleLocationInCanvas() {
		Point2D turtlePoint = mainTurtle.getPoint();
		Point2D canvasSize = mainImageUpdater.getTurtleCanvasSize();
		return new Point2D(turtlePoint.getX() % canvasSize.getX(),
				turtlePoint.getY() % canvasSize.getY());
	}

	/**
	 * 
	 * @param translocation
	 *            the double amount of how many pixels to move forwards.
	 *            Positive = move forwards, negative = move backwards
	 */
	public void updateTurtleLocation(double translocation) {
		Point2D from = mainTurtle.getPoint();
		mainTurtle.updateLocation(translocation);
		Point2D to = mainTurtle.getPoint();
		moveAndDraw(from, to);
	}

	/**
	 * 
	 * @param newLocation
	 *            the location to instantly move to.
	 */
	public void updateTurtleAbsoluteLocation(Point2D newLocation) {
		Point2D from = mainTurtle.getPoint();
		mainTurtle.updateAbsoluteLocation(newLocation);
		Point2D to = mainTurtle.getPoint();
		moveAndDraw(from, to);
	}

	/**
	 * 
	 * @param from
	 *            The start point of the line
	 * @param to
	 *            The end point of the line, also where the Turtle image should
	 *            be moved to
	 */
	private void moveAndDraw(Point2D from, Point2D to) {
		mainImageUpdater.updateTurtleImage(to, mainTurtle.getImage());
		if (mainTurtle.getPenPosition() == 1)
			mainImageUpdater.drawLine(from, to, mainTurtle.getPenHandler());
	};

	/**
	 * 
	 * @param rotation
	 *            the amount of degrees to rotate the Turtle (clockwise)
	 */
	public void updateTurtleOrientation(double rotation) {
		mainTurtle.updateOrientation(rotation);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	}

	/**
	 * 
	 * @param newAngle
	 *            the orientation angle to immediately change to.
	 */
	public void updateTurtleAbsoluteOrientation(double newAngle) {
		mainTurtle.updateAbsoluteOrientation(newAngle);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	};

	/**
	 * 
	 * @param show
	 *            1 if should show the turtle, 0 if should hide the turtle
	 */
	public void showTurtle(int show) {
		mainTurtle.show(show);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	}

	/**
	 * 
	 * @param newImage
	 *            The new image to be drawn on the turtle's canvas
	 */
	public void updateImage(Image newImage) {
		mainTurtle.updateImage(newImage);
		mainImageUpdater.updateTurtleImage(mainTurtle.getPoint(),
				mainTurtle.getImage());
	}

	/**
	 * Clears the lines associated with this specific turtle.
	 */
	public void clearLines() {
		mainImageUpdater.clearLines();
	}

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public void setPenPosition(int penPosition) {
		mainTurtle.setPenPosition(penPosition);
	}

	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 */
	public int getPenPosition() {
		return mainTurtle.getPenPosition();
	}

	/**
	 * 
	 * @return 1 if the turtle is showing (visible), 0 if hiding (invisible)
	 */
	public int getShowing() {
		if (mainTurtle.getImage().isVisible())
			return 1;
		return 0;
	}
	
	/**
	 * 
	 * @return the PenHandler associated with the Turtle
	 */
	public PenHandler getPenHandler(){
		return mainTurtle.getPenHandler();
	}
	
	/**
	 * 
	 * @param width the line width in pixels
	 */
	public void setLineWidth(double width){
		mainTurtle.getPenHandler().setLineWidth(width);
	}
	
	/**
	 * 
	 * @return the ID associated with this turtle.  Making a new TurtleHandler increments the static ID counter by 1.
	 */
	public int getID(){
		return myID;
	}
	
	public void towards(Point2D location){
            Point2D canvasLocation = getTurtleLocationInCanvas();
            double deltaY = location.getY() - canvasLocation.getY();
            double deltaX = location.getX() - canvasLocation.getX();
            double angle = Math.atan2(deltaY, deltaX) * RADIAL_CONVERT;
            updateTurtleAbsoluteOrientation(angle);
	}
}
