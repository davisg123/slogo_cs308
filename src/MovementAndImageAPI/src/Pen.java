import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class represents the Pen which has a color and an up
 *         or down position, used for checking how and whether or not lines
 *         should be drawn.
 *
 */
public class Pen {
	/**
	 * 
	 * @param newPenColor
	 *            the new color for the Pen
	 */
	public void setPenColor(Color newPenColor) {
	};

	/**
	 * 
	 * @return myPenColor (the current color)
	 */
	public Color getPenColor() {
		return null;
	};

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public void setPenPosition(int penPosition) {
	};

	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 */
	public int getPenPosition() {
		return 0;
	};

}
