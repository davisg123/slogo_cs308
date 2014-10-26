package commands;

import MovementAndImageAPI.src.GeneralTurtleHandler;


/**
 * the factory responsible for creating and executing commands
 * 
 * @author Davis
 *
 */

public class CommandsFactory {

    private GeneralTurtleHandler myTurtleHandler;

    public CommandsFactory () {
    }

    public void setTurtleHandler (GeneralTurtleHandler turtleHandler) {
        myTurtleHandler = turtleHandler;
    }

    public CommandsQueue CommandsQueue () {
        return new CommandsQueue();
    }

    public ICommand CommandsRepeat (int times, ICommand command) {
        return new CommandsRepeat(times, command);
    }

    public ICommand CommandMake (String name, ICommand command) {
        return new CommandMake(name, command);
    }

    public ICommand CommandExecute (String commandName) {
        return new CommandExecute(commandName);
    }

    /**
     * creates an activity command to pick up the turtle
     */
    public ICommand pickUpTurtle () {
        return new ActivityCommand(myTurtleHandler, ActivityCommand.CommandType.PICK_UP);
    }
    
    /**
     * creates an activity command to put down the turtle
     */
    public ICommand putTurtle () {
        return new ActivityCommand(myTurtleHandler, ActivityCommand.CommandType.PUT);
    }
    
    /**
     * creates an activity command to show the turtle
     */
    public ICommand showTurtle () {
        return new ActivityCommand(myTurtleHandler, ActivityCommand.CommandType.SHOW);
    }
    
    /**
     * creates an activity command to hide the turtle
     */
    public ICommand hideTurtle () {
        return new ActivityCommand(myTurtleHandler, ActivityCommand.CommandType.HIDE);
    }

    /**
     * creates a movement command to rotate the turtle
     */
    public ICommand rotateTurtle (double angle) {
        double[] val = { angle };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.ROTATE, val);
    }

    public ICommand rotateTurtleLeft (double angle) {
        double[] val = { -1.0 * angle };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.ROTATE, val);
    }

    public ICommand rotateTurtleRight (double angle) {
        double[] val = { angle };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.ROTATE, val);
    }

    /**
     * creates an activity command to move the turtle
     */
    public ICommand turtleGoForward (double distance) {
        double[] val = { distance };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.GO_FORWARD, val);
    }

    public ICommand turtleGoBack (double distance) {
        double[] val = { distance };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.GO_BACK, val);
    }

    public ICommand setTurtlePosition (double positionX, double positionY) {
        double[] vals = { positionX, positionY };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.SET_POSITION, vals);
    }

    /**
     * creates a movement command to set turtle's orientation
     */
    public ICommand setTurtleHeading (double degrees) {
        double[] val = { degrees };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.SET_HEADING, val);
    }

    public ICommand setTurtleTowards (double positionX, double positionY) {
        double[] vals = { positionX, positionY };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.SET_TOWARDS, vals);
    }

    /**
     * creates a movement command to set the pen size
     */
    public ICommand setPenSize (double pixels) {
        double[] val = { pixels };
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.SET_PEN_SIZE, val);
    }

    public ICommand clearScreen () {
        return new MovementCommand(myTurtleHandler, MovementCommand.CommandType.CLEAR_SCREEN, null);
    }

}
