package commands;

import MovementAndImageAPI.src.GeneralTurtleHandler;

/**
 * executes commands related to turtle activities
 * 
 * @author Davis, Keng
 *
 */

public class ActivityCommand extends CommandsTurtle {

    public enum CommandType { HIDE, SHOW, PICK_UP, PUT };
    private CommandType myCommand;
    
    public ActivityCommand (GeneralTurtleHandler turtleHandler, CommandType command) {
        super(turtleHandler);
        myCommand = command;
    }
    
    @Override
    public void execute () {
        switch(myCommand) {
            case HIDE:
                turtleHandler().showTurtle(0);
                break;
            case SHOW:
                turtleHandler().showTurtle(1);
                break;
            case PICK_UP:
                turtleHandler().setPenPosition(0);
                break;
            case PUT:
                turtleHandler().setPenPosition(1);
                break;
            default:
                break;
        }
    }   
}
