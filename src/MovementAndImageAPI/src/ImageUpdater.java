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

/**
 * 
 * @author Eirika Sawh This class updates the actual image placement in the
 *         Scene.
 */
public class ImageUpdater {
	private Canvas myTurtleCanvas, myLineCanvas;
	private GraphicsContext turtleGC, lineGC;
	private PenHandler mainPenHandler;

	public ImageUpdater(Canvas turtleCanvas, Canvas lineCanvas, PenHandler penHandler) {
		myTurtleCanvas = turtleCanvas;
		turtleGC = myTurtleCanvas.getGraphicsContext2D();
		myLineCanvas = lineCanvas;
		lineGC = myLineCanvas.getGraphicsContext2D();
		mainPenHandler = penHandler;
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
	    turtleGC.clearRect(0, 0, myTurtleCanvas.getWidth(), myTurtleCanvas.getHeight());    
	    turtleGC.drawImage(turtleImage.getImage(), newLocation.getX(), newLocation.getY());
	}
	

	/**
	 * 
	 * @param from
	 *            the starting point of the line
	 * @param to
	 *            the ending point of the line
	 */
	public void drawLine(Point2D from, Point2D to) {
		if (isValidPoint(to)) {
//			Line toDraw = new Line(from.getX(), from.getY(), to.getX(),
//					to.getY());
//			toDraw.setStroke(mainPen.getPenColor());
			lineGC.setStroke(mainPenHandler.getPenColor());
			lineGC.strokeLine(from.getX(), from.getY(), to.getX(),
					to.getY());
			
		}
//		else{
//			double angleMoving = from.angle(to);
//			Point2D newEndPoint = findValidEndPoint(to);
//			Point2D newStartPoint = findNewStartPoint(to);
//		}
	}

	/**
	 * 
	 * @param newLocation
	 *            The point which is being checked to be within the scene's
	 *            limits
	 * @return true if the point is within the scene
	 */
	public boolean isValidPoint(Point2D newLocation) {
		return (newLocation.getX() <= myTurtleCanvas.getWidth() && newLocation.getY() <= myTurtleCanvas.getHeight());
	}

}
