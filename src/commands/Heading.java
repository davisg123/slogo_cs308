package commands;


public class Heading extends Command {

    public Heading (double degrees) {
        setAngle(degrees);
    }

    @Override
    public void executeCommand () {
        sendAbsoluteAngleToTurtle();
    }

}
