package commands;

public class CommandExecute implements ICommand {
    
    private String commandName; 
    private ICommand command;
    
    public CommandExecute(String name) {
        commandName = name;
    }
    
    @Override
    public void execute() {
        if(command != null) {
           command.execute(); 
        }
    }

}
