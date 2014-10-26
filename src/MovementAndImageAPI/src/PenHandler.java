package MovementAndImageAPI.src;
import javafx.scene.paint.Color;

/**
 * 
 * @author Eirika Sawh This class receives commands from the backend about
 *         whether or not to update the Pen
 *
 */
public class PenHandler {
	private Pen mainPen = new Pen();
	/**
	 * 
	 * @param newPenColor
	 *            the color to set the Pen's color to.
	 */
	public void setPenColor(Color newPenColor) {
		mainPen.setPenColor(newPenColor);
	}

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public void setPenPosition(int penPosition) {
		mainPen.setPenPosition(penPosition);
	}
	
	/**
	 * 
	 * @return the Pen's color
	 */
	public Color getPenColor(){
	    return mainPen.getPenColor();
	}
	
	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 */
	public int getPenPosition(){
	    return mainPen.getPenPosition();
	}
	
	/**
	 * 
	 * @param width the width of the pen in pixels
	 */
	public void setLineWidth(double width){
		mainPen.setLineWidth(width);
	}
	
	/**
	 * 
	 * @return the width of the pen in pixels
	 */
	public double getLineWidth(){
		return mainPen.getLineWidth();
	}
}
