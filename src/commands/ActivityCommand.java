package commands;

import MovementAndImageAPI.*;
import MovementAndImageAPI.src.Turtle;

public class ActivityCommand extends CommandsTurtle {

    public enum CommandType {HIDE, SHOW, PICK_UP, PUT};
    private CommandType command;
    
    public ActivityCommand(Turtle turtle, CommandType command) {
        super(turtle);
        this.command = command;
    }
    
    @Override
    public void execute() {
        switch(command) {
            case HIDE:
                turtle().hide();
                break;
            case SHOW:
                turtle().show();
                break;
            case PICK_UP:
                turtle().pickUp();
                break;
            case PUT:
                turtle().put();
                break;
        }
    }   
}
