package commands;

public class CommandMake implements ICommand {
    
    private String name;
    private ICommand command;

    public CommandMake(String name, ICommand command) {
        this.name = name;
        this.command = command;
    }

    @Override
    public void execute () {
       command.execute(); 
    }
    
}
