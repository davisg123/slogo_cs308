package parser;

public class CommandFactory {
    
    public CommandFactory() {
        super();
    }

    
    /**
     * parses command with getCommand method to get type of command  
     * @param command
     * @return parsed command
     */
    public Command getCommand(Command command){
		return command;
    }
    
    /**
     * adds a command to the queue for processing
     * 
     * @param command
     */
    public void addCommand(String command){
        
    }
    
    /**
     * cycles through the queue of command objects, 
     * and determines the order for processing
     */
    public void processQueuedCommands(){
        
    }
    
}
