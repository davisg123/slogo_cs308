package MovementAndImageAPI.src;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class GeneralTurtleHandler {

	public abstract double getOrientation();
	public abstract Point2D getTurtleLocation();
	public abstract void updateTurtleLocation(double translocation);
	public abstract void updateTurtleAbsoluteLocation(Point2D newLocation);
	public abstract void updateTurtleOrientation(double rotation);
	public abstract void updateTurtleAbsoluteOrientation(double newAngle);
	public abstract void showTurtle(int show);
	public abstract void updateImage(Image newImage);
	public abstract void clearLines();
	public abstract void setPenPosition(int penPosition);
	public abstract int getPenPosition();
	public abstract int getShowing();
	public abstract PenHandler getPenHandler();
	public abstract void setLineWidth(double width);
}
