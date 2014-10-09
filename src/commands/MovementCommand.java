package commands;

import MovementAndImageAPI.*;

public class MovementCommand extends CommandsTurtle { 
    public enum CommandType {ROTATE, GO_FORWARD, GO_BACK};
    private CommandType command;
    private double value = 0.0;
    
    public MovementCommand(Turtle turtle, CommandType command, double value) {
        super(turtle);
        this.command = command;
        this.value = value;
    }
            
    @Override
    public void execute() {
        switch(command) {
            case ROTATE:
                turtle().rotate(value);
                break;
            case GO_FORWARD:
                turtle().goForward(value);
                break;
            case GO_BACK:
                turtle().goBack(value);
                break;
        }
    }
}
