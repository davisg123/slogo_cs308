package commands;


public class Back extends Command {
    
    
    public Back (double distance) {
        setDistance(-distance);
    }

    @Override
    public void executeCommand () {
        sendDistanceToTurtle();
    }

}
