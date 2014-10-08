package commands;

public class CommandMake {
    
    private String name;
    private ICommand command;

    public CommandMake(String name, ICommand command) {
        this.name = name;
        this.command = command;
    }
    
}
