package commands;

import MovementAndImageAPI.*;
import MovementAndImageAPI.src.TurtleHandler;

public class MovementCommand extends CommandsTurtle { 
    public enum CommandType {ROTATE, GO_FORWARD, GO_BACK};
    private CommandType command;
    private double value = 0.0;
    
    public MovementCommand(TurtleHandler turtleHandler, CommandType command, double value) {
        super(turtleHandler);
        this.command = command;
        this.value = value;
    }
            
    @Override
    public void execute() {
        switch(command) {
            case ROTATE:
                turtleHandler.updateTurtleOrientation(value);
                break;
            case GO_FORWARD:
                turtleHandler.updateTurtleLocation(value);
                break;
            case GO_BACK:
                turtleHandler.updateTurtleLocation(-value);
                break;
        }
    }
}
