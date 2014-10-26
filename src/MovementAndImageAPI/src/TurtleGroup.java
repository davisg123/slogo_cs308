package MovementAndImageAPI.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TurtleGroup extends GeneralTurtleHandler{
	
	private Map<Integer, TurtleHandler> allTurtles;
	private Map<Integer, TurtleHandler> activeTurtles;

	
	public TurtleGroup(){
		allTurtles = new HashMap<Integer, TurtleHandler>();
		activeTurtles = new HashMap<Integer, TurtleHandler>();
	}
	/**
	 * 
	 * @return the Turtle's current orientation angle.
	 * Returns the value of the last active Turtle.
	 */
	public double getOrientation() {
		double lastOrientation = 0;
		for(TurtleHandler turtle : activeTurtles.values())
			lastOrientation = turtle.getOrientation();
		return lastOrientation;
	}

	/**
	 * 
	 * @return the Point2D associated with the Turtle's actual location,
	 *         regardless of the bounds of the canvas it's in.
	 *         Returns the value of the last active Turtle.
	 */
	public Point2D getTurtleLocation() {
		Point2D lastPosition = new Point2D(0, 0);
		for(TurtleHandler turtle : activeTurtles.values())
			lastPosition = turtle.getTurtleLocation();
		return lastPosition;
	}


	/**
	 * 
	 * @param translocation
	 *            the double amount of how many pixels to move forwards.
	 *            Positive = move forwards, negative = move backwards
	 */
	public void updateTurtleLocation(double translocation) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleLocation(translocation);
	}

	/**
	 * 
	 * @param newLocation
	 *            the location to instantly move to.
	 */
	public void updateTurtleAbsoluteLocation(Point2D newLocation) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleAbsoluteLocation(newLocation);
	}

	/**
	 * 
	 * @param rotation
	 *            the amount of degrees to rotate the Turtle (clockwise)
	 */
	public void updateTurtleOrientation(double rotation) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleOrientation(rotation);
	}

	/**
	 * 
	 * @param newAngle
	 *            the orientation angle to immediately change to.
	 */
	public void updateTurtleAbsoluteOrientation(double newAngle) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleAbsoluteOrientation(newAngle);
	};

	/**
	 * 
	 * @param show
	 *            1 if should show the turtle, 0 if should hide the turtle
	 */
	public void showTurtle(int show) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.showTurtle(show);
	}

	/**
	 * 
	 * @param newImage
	 *            The new image to be drawn on the turtle's canvas
	 */
	public void updateImage(Image newImage) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.updateImage(newImage);
	}

	/**
	 * Clears the lines associated with this specific turtle.
	 */
	public void clearLines() {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.clearLines();
	}

	/**
	 * 
	 * @param penPosition
	 *            0 if pen is up, 1 if pen is down
	 */
	public void setPenPosition(int penPosition) {
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.setPenPosition(penPosition);
	}

	/**
	 * 
	 * @return 0 if pen is up, 1 if pen is down
	 * Returns the value of the last active Turtle.
	 */
	public int getPenPosition() {
		int lastPosition = 0;
		for(TurtleHandler turtle : activeTurtles.values())
			lastPosition = turtle.getPenPosition();
		return lastPosition;
	}

	/**
	 * 
	 * @return 1 if the turtle is showing (visible), 0 if hiding (invisible)
	 * Returns the value of the last active Turtle.
	 */
	public int getShowing() {
		int lastShowing = 0;
		for(TurtleHandler turtle : activeTurtles.values())
			lastShowing = turtle.getShowing();
		return lastShowing;
	}
	
	/**
	 * 
	 * @return the PenHandler associated with the Turtle
	 * Returns the value of the last active Turtle.
	 */
	public PenHandler getPenHandler(){
		PenHandler lastPenHandler = new PenHandler();
		for(TurtleHandler turtle : activeTurtles.values())
			lastPenHandler = turtle.getPenHandler();
		return lastPenHandler;
	}
	
	public void setLineWidth(double width){
		for(TurtleHandler turtle : activeTurtles.values())
			turtle.setLineWidth(width);
	}
	
	public void addNewTurtle(TurtleHandler newTurtle){
		allTurtles.put(newTurtle.getID(), newTurtle);
		activeTurtles.put(newTurtle.getID(), newTurtle);
	}
	
	public void setActiveTurtles(List<Integer> actives){
		Map<Integer, TurtleHandler> tempActiveTurtleList = new HashMap<Integer, TurtleHandler>();
		for(Integer turtleID : actives){
			if(allTurtles.containsKey(turtleID))
				tempActiveTurtleList.put(turtleID, allTurtles.get(turtleID));
		}
		if(tempActiveTurtleList.size() > 0)
			activeTurtles = tempActiveTurtleList;
		else
		{
			System.out.println("Please select at least one valid turtle to make active.");
			System.out.println("Valid Turtle IDs: " + Arrays.toString(allTurtles.keySet().toArray()));
		}
	}
}
