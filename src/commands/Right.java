package commands;

public class Right extends Command {

    public Right (double degrees) {
        setAngle(degrees);
    }

    @Override
    public void executeCommand () {
        sendAngleToTurtle();
    }

}
