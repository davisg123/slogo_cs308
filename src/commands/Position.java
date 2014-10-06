package commands;

import com.sun.javafx.geom.Point2D;

public class Position extends Command {

    public Position (Point2D position) {
        setPosition(position);
    }

    @Override
    public void executeCommand () {
        sendAbsolutePositionToTurtle();
    }

}
