package commands;

public class Forward extends Command {
    
    public Forward (double distance) {
        setDistance(distance);
    }

    @Override
    public void executeCommand () {
        sendDistanceToTurtle();
    }
    
}
