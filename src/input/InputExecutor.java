package input;

import commands.CommandsFactory;
import commands.ICommand;
import parser.Parser;

public class InputExecutor {
    
    private CommandsFactory commandsFactory = null;
    
    private Parser parser = null;
    
    public InputExecutor() {
        parser = new Parser(commandsFactory);
        parser.createLogoParser();
    }
    
    public boolean processInput(String input) {
        ICommand command = null;
        try {
            command = parser.parse(input);
        }
        catch( Exception e ) {
        }
        if(command != null) {
        	return true;
        } else {
        	return false;
        }
    }
}
