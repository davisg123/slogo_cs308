package parser;

import java.awt.Point;

public interface Input {

	void setDistance(int pixels);

	void setAngle(int degrees);

	void setPosition(Point position);
	
	void executeCommand();
	
}
