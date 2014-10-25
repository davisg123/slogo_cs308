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
		Point2D endLocation = new Point2D(ensurePositive(
				(newLocation.getX() + X_OFFSET) % myTurtleCanvas.getWidth(),
				myTurtleCanvas.getWidth()), ensurePositive(
				(newLocation.getY() + Y_OFFSET) % myTurtleCanvas.getHeight(),
				myTurtleCanvas.getHeight()));
		drawRotatedImage(turtleImage, endLocation);
	}

	private double ensurePositive(double numToCheck, double maxSize) {
		if (numToCheck < 0) {
			numToCheck = maxSize - (-1 * numToCheck);
		}
		return numToCheck;
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
		if (mainPenHandler.getPenPosition() == 1) {
			if (from != null) {
				lineGC.setStroke(mainPenHandler.getPenColor());
				Point2D fromInCanvas = new Point2D(ensurePositive(
						(from.getX() + X_OFFSET) % myLineCanvas.getWidth(),
						myLineCanvas.getWidth()), ensurePositive(
						(from.getY() + Y_OFFSET) % myLineCanvas.getHeight(),
						myLineCanvas.getHeight()));
				Point2D distanceMoved = new Point2D(to.getX() - from.getX(),
						to.getY() - from.getY());
				Point2D endPoint = new Point2D(fromInCanvas.getX()
						+ (to.getX() - from.getX()), fromInCanvas.getY()
						+ (to.getY() - from.getY()));
				lineGC.strokeLine(fromInCanvas.getX(), fromInCanvas.getY(),
						endPoint.getX(), endPoint.getY());
				
				Point2D beginning = fromInCanvas;

				while (xOutOfBounds(endPoint) || yOutOfBounds(endPoint)) {
					Point2D boundary = findBoundaryPoint(beginning, endPoint);
					Point2D distanceRemaining = new Point2D(endPoint.getX()
							- boundary.getX(), endPoint.getY()
							- boundary.getY());
					Point2D newStartPoint = findNewStartPoint(boundary);
					endPoint = new Point2D(newStartPoint.getX()
							+ distanceRemaining.getX(), newStartPoint.getY()
							+ distanceRemaining.getY());
					lineGC.strokeLine(newStartPoint.getX(),
							newStartPoint.getY(), endPoint.getX(),
							endPoint.getY());
				}
			}
		}
	}

	private Point2D findNewStartPoint(Point2D boundary) {
		double newX;
		double newY;
		if (boundary.getX() == myLineCanvas.getWidth())
			newX = 0;
		else if (boundary.getX() == 0)
			newX = myLineCanvas.getWidth();
		else
			newX = boundary.getX();
		if (boundary.getY() == myLineCanvas.getHeight())
			newY = 0;
		else if (boundary.getY() == 0)
			newY = myLineCanvas.getHeight();
		else
			newY = boundary.getY();
		return new Point2D(newX, newY);
	}

	private Point2D findBoundaryPoint(Point2D startPoint, Point2D endPoint) {
		double slope = -1 * (endPoint.getY() - startPoint.getY())
				/ (endPoint.getX() - startPoint.getX());
		if (xOutOfBounds(endPoint) && !yOutOfBounds(endPoint)) {
			return xBoundary(startPoint, endPoint, slope);
		} else if (!xOutOfBounds(endPoint) && yOutOfBounds(endPoint)) {
			return yBoundary(startPoint, endPoint, slope);
		} else if (xOutOfBounds(endPoint) && yOutOfBounds(endPoint)) {
			double yPointAtXBoundary;
			if (endPoint.getX() > myLineCanvas.getWidth()) {
				yPointAtXBoundary = (myLineCanvas.getWidth() - startPoint
						.getX() * slope)
						+ startPoint.getY();
			} else {
				yPointAtXBoundary = (0 - startPoint.getX() * slope)
						+ startPoint.getY();
			}
			if (yPointAtXBoundary < 0
					|| yPointAtXBoundary > myLineCanvas.getHeight())
				return yBoundary(startPoint, endPoint, slope);
			else
				return xBoundary(startPoint, endPoint, slope);
		}
		return endPoint;
	}

	private Point2D yBoundary(Point2D startPoint, Point2D endPoint, double slope) {
		double newX;
		double newY;
		if (endPoint.getY() > myLineCanvas.getHeight()) {
			newY = myLineCanvas.getHeight();
		} else {
			newY = 0;
		}
		newX = startPoint.getX() + ((newY - startPoint.getY()) / slope);
		return new Point2D(newX, newY);
	}

	private Point2D xBoundary(Point2D startPoint, Point2D endPoint, double slope) {
		double newX;
		double newY;
		if (endPoint.getX() > myLineCanvas.getWidth()) {
			newX = myLineCanvas.getWidth();
		} else {
			newX = 0;
		}
		newY = startPoint.getY() - (slope * (newX - startPoint.getX()));
		return new Point2D(newX, newY);
	}

	private boolean xOutOfBounds(Point2D endPoint) {
		return endPoint.getX() < 0 || endPoint.getX() > myLineCanvas.getWidth();
	}

	private boolean yOutOfBounds(Point2D endPoint) {
		return endPoint.getY() < 0
				|| endPoint.getY() > myLineCanvas.getHeight();
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
