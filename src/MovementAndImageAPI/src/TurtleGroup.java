// This entire file is part of my masterpiece.
// Eirika Sawh
package MovementAndImageAPI.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TurtleGroup extends GeneralTurtleHandler {

	private Map<Integer, TurtleHandler> allTurtles;
	private Map<Integer, TurtleHandler> activeTurtles;

	public TurtleGroup() {
		allTurtles = new HashMap<Integer, TurtleHandler>();
		activeTurtles = new HashMap<Integer, TurtleHandler>();
	}

	/**
	 * Returns the value of the last active Turtle.
	 */
	public double getOrientation() {
		double lastOrientation = 0;
		for (TurtleHandler turtle : activeTurtles.values())
			lastOrientation = turtle.getOrientation();
		return lastOrientation;
	}

	/**
	 * Returns the value of the last active Turtle.
	 */
	public Point2D getTurtleLocation() {
		Point2D lastPosition = new Point2D(0, 0);
		for (TurtleHandler turtle : activeTurtles.values())
			lastPosition = turtle.getTurtleLocation();
		return lastPosition;
	}

	public void updateTurtleLocation(double translocation) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleLocation(translocation);
	}

	public void updateTurtleAbsoluteLocation(Point2D newLocation) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleAbsoluteLocation(newLocation);
	}

	public void updateTurtleOrientation(double rotation) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleOrientation(rotation);
	}

	public void updateTurtleAbsoluteOrientation(double newAngle) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.updateTurtleAbsoluteOrientation(newAngle);
	};

	public void showTurtle(int show) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.showTurtle(show);
	}

	public void updateImage(Image newImage) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.updateImage(newImage);
	}

	public void clearLines() {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.clearLines();
	}

	public void setPenPosition(int penPosition) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.setPenPosition(penPosition);
	}

	/**
	 * Returns the value of the last active Turtle.
	 */
	public int getPenPosition() {
		int lastPosition = 0;
		for (TurtleHandler turtle : activeTurtles.values())
			lastPosition = turtle.getPenPosition();
		return lastPosition;
	}

	/**
	 * Returns the value of the last active Turtle.
	 */
	public int getShowing() {
		int lastShowing = 0;
		for (TurtleHandler turtle : activeTurtles.values())
			lastShowing = turtle.getShowing();
		return lastShowing;
	}

	/**
	 * Returns the PenHandler of the last active Turtle.
	 */
	public PenHandler getPenHandler() {
		PenHandler lastPenHandler = new PenHandler();
		for (TurtleHandler turtle : activeTurtles.values())
			lastPenHandler = turtle.getPenHandler();
		return lastPenHandler;
	}

	public void setLineWidth(double width) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.setLineWidth(width);
	}

	public void towards(Point2D location) {
		for (TurtleHandler turtle : activeTurtles.values())
			turtle.towards(location);
	}

	/**
	 * 
	 * @param newTurtle
	 *            The TurtleHandler containing the new Turtle to add to the list
	 *            of allTurtles as well as the activeTurtles.
	 */
	public void addNewTurtle(TurtleHandler newTurtle) {
		allTurtles.put(newTurtle.getID(), newTurtle);
		activeTurtles.put(newTurtle.getID(), newTurtle);
	}

	/**
	 * 
	 * @param actives
	 *            the list of IDs of turtles to look for to set active. If no
	 *            valid IDs were chosen, maintain the previous set of active
	 *            Turtles and notify the user.
	 */
	public void setActiveTurtles(List<Integer> actives) {
		Map<Integer, TurtleHandler> tempActiveTurtleList = new HashMap<Integer, TurtleHandler>();
		for (Integer turtleID : actives) {
			if (allTurtles.containsKey(turtleID))
				tempActiveTurtleList.put(turtleID, allTurtles.get(turtleID));
		}
		if (tempActiveTurtleList.size() > 0)
			activeTurtles = tempActiveTurtleList;
		else {
			System.out
					.println("Please select at least one valid turtle to make active.");
			System.out.println("Valid Turtle IDs: "
					+ Arrays.toString(allTurtles.keySet().toArray()));
		}
	}
	
	public Map<Integer, TurtleHandler> getAllTurtles(){
		return allTurtles;
	}
	
	public Map<Integer, TurtleHandler> getActiveTurtles(){
		return activeTurtles;
	}
}
