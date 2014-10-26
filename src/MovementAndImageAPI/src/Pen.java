package MovementAndImageAPI.src;
import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class represents the Pen which has a color and an up
 *         or down position, used for checking how and whether or not lines
 *         should be drawn.
 *
 */
public class Pen {
	private Color myPenColor;
	private int myPenPosition;
	private double myLineWidth;
	
	public Pen(){
		myPenColor = Color.BLACK;
		myPenPosition = 1;
		myLineWidth = 1;
	}
	
	/**
	 * 
	 * @param newPenColor
	 *            the new color for the Pen
	 */
	public void setPenColor(Color newPenColor) {
		myPenColor = newPenColor;
	}

	/**
	 * 
	 * @return myPenColor (the current color)
	 */
	public Color getPenColor() {
		return myPenColor;
	}

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public void setPenPosition(int penPosition) {
		myPenPosition = penPosition;
	}

	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 */
	public int getPenPosition() {
		return myPenPosition;
	}
	
	public void setLineWidth(double width){
		myLineWidth = width;
	}

	public double getLineWidth() {
		return myLineWidth;
	}

}
