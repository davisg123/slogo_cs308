package commands;

public class Left extends Command {

    public Left (double degrees) {
        setAngle(-degrees);
    }

    @Override
    public void executeCommand () {
        sendAngleToTurtle();
    }

}
