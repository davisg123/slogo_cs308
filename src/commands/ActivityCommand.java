package commands;

import MovementAndImageAPI.*;
import MovementAndImageAPI.src.Turtle;
import MovementAndImageAPI.src.TurtleHandler;

public class ActivityCommand extends CommandsTurtle {

    public enum CommandType {HIDE, SHOW, PICK_UP, PUT};
    private CommandType command;
    
    public ActivityCommand(TurtleHandler turtleHandler, CommandType command) {
        super(turtleHandler);
        this.command = command;
    }
    
    @Override
    public void execute() {
        switch(command) {
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
        }
    }   
}
