package parser;

import com.sun.javafx.geom.Point2D;

public interface Input {

	void setDistance(double pixels);

	void setAngle(double degrees);

	void setPosition(Point2D position);
	
	void executeCommand();
	
}
