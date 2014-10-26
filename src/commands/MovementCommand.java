package commands;

import javafx.geometry.Point2D;
import MovementAndImageAPI.src.GeneralTurtleHandler;


/**
 * executed commands related to turtle movement
 * 
 * @author Davis, Keng
 *
 */

public class MovementCommand extends CommandsTurtle {
    public enum CommandType {
        ROTATE,
        GO_FORWARD,
        GO_BACK,
        SET_POSITION,
        CLEAR_SCREEN,
        SET_HEADING,
        SET_TOWARDS,
        SET_PEN_SIZE
    };

    private CommandType myCommand;
    private double[] myValue = { 0.0 };

    public MovementCommand (GeneralTurtleHandler turtleHandler, CommandType command, double[] value) {
        super(turtleHandler);
        myCommand = command;
        myValue = value;
    }

    @Override
    public void execute () {
        switch (myCommand) {
            case ROTATE:
                turtleHandler.updateTurtleOrientation(myValue[0]);
                break;
            case GO_FORWARD:
                turtleHandler.updateTurtleLocation(myValue[0]);
                break;
            case GO_BACK:
                turtleHandler.updateTurtleLocation(-myValue[0]);
                break;
            case SET_POSITION:
                turtleHandler.updateTurtleAbsoluteLocation(new Point2D(myValue[0], myValue[1]));
                break;
            case CLEAR_SCREEN:
                turtleHandler.clearLines();
                turtleHandler.setPenPosition(0);
                turtleHandler.updateTurtleAbsoluteLocation(new Point2D(0, 0));
                turtleHandler.setPenPosition(1);
                break;
            case SET_HEADING:
                turtleHandler.updateTurtleAbsoluteOrientation(myValue[0]);
                break;
            case SET_TOWARDS:
                Point2D towardsPos = new Point2D(myValue[0], myValue[1]);
                turtleHandler.towards(towardsPos);
                break;
            case SET_PEN_SIZE:
                turtleHandler.setLineWidth(myValue[0]);
                break;
            default:
                break;
        }
    }
}
