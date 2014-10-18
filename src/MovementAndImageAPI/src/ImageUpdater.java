package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 * 
 * @author Eirika Sawh This class updates the actual image placement in the
 *         Scene.
 */
public class ImageUpdater {
	private Canvas myTurtleCanvas, myLineCanvas;
	private GraphicsContext turtleGC, lineGC;
	private PenHandler mainPenHandler;
	private static double X_OFFSET, Y_OFFSET;

	public ImageUpdater(Canvas turtleCanvas, Canvas lineCanvas,
			PenHandler penHandler) {
		myTurtleCanvas = turtleCanvas;
		turtleGC = myTurtleCanvas.getGraphicsContext2D();
		myLineCanvas = lineCanvas;
		lineGC = myLineCanvas.getGraphicsContext2D();
		mainPenHandler = penHandler;
		X_OFFSET = turtleCanvas.getWidth() / 2;
		Y_OFFSET = turtleCanvas.getHeight() / 2;
	}

	/**
	 * 
	 * @param newLocation
	 *            the location to move the Turtle's image to.
	 * @param turtleImage
	 *            the actual image to move. Doesn't throw an OutOfSceneException
	 *            because that should be handled by the TurtleHandler.
	 */
	public void updateTurtleImage(Point2D newLocation, ImageView turtleImage) {
		turtleGC.clearRect(0, 0, myTurtleCanvas.getWidth(),
				myTurtleCanvas.getHeight());
		Point2D endLocation = new Point2D((newLocation.getX() + X_OFFSET)
				% myTurtleCanvas.getWidth(), (newLocation.getY() + Y_OFFSET)
				% myTurtleCanvas.getHeight());
		drawRotatedImage(turtleImage, endLocation);
	}

	private void drawRotatedImage(ImageView turtleImage, Point2D destination) {
		turtleGC.save();
		rotate(turtleImage.getRotate(), destination.getX()
				+ turtleImage.getImage().getWidth() / 2, destination.getY()
				+ turtleImage.getImage().getHeight() / 2);
		turtleGC.drawImage(turtleImage.getImage(), destination.getX(),
				destination.getY());
		turtleGC.restore();
	}

	private void rotate(double rotationAngle, double xPivot, double yPivot) {
		Rotate r = new Rotate(rotationAngle, xPivot, yPivot);
		turtleGC.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(),
				r.getTx(), r.getTy());
	}

	/**
	 * 
	 * @param from
	 *            the starting point of the line
	 * @param to
	 *            the ending point of the line
	 */
	public void drawLine(Point2D from, Point2D to) {
		if (isValidPoint(to) && mainPenHandler.getPenPosition() == 1) {
			// Line toDraw = new Line(from.getX(), from.getY(), to.getX(),
			// to.getY());
			// toDraw.setStroke(mainPen.getPenColor());
			lineGC.setStroke(mainPenHandler.getPenColor());
			Point2D fromInCanvas = new Point2D((from.getX() + X_OFFSET)
					% myLineCanvas.getWidth(), (from.getY() + Y_OFFSET)
					% myLineCanvas.getHeight());
			Point2D distanceMoved = new Point2D(to.getX() - from.getX(), to.getY() - from.getY());
			lineGC.strokeLine(fromInCanvas.getX(), fromInCanvas.getY(), fromInCanvas.getX() + distanceMoved.getX(), fromInCanvas.getY() + distanceMoved.getY());

		}
		// else{
		// double angleMoving = from.angle(to);
		// Point2D newEndPoint = findValidEndPoint(to);
		// Point2D newStartPoint = findNewStartPoint(to);
		// }
	}

	/**
	 * 
	 * @param newLocation
	 *            The point which is being checked to be within the scene's
	 *            limits
	 * @return true if the point is within the scene
	 */
	public boolean isValidPoint(Point2D newLocation) {
		return (newLocation.getX() <= myTurtleCanvas.getWidth() && newLocation
				.getY() <= myTurtleCanvas.getHeight());
	}

}
