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
                turtleHandler().hide();
                break;
            case SHOW:
                turtleHandler().show();
                break;
            case PICK_UP:
                turtleHandler().pickUp();
                break;
            case PUT:
                turtleHandler().put();
                break;
        }
    }   
}
