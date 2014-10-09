package parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import commands.*;

public class Parser {

    private CommandsFactory commandsFactory = null;
    private LogoParser logoParser;

    public Parser(CommandsFactory commandsFactory) {
        this.commandsFactory = commandsFactory;
    }
   
    public void createLogoParser() {
        logoParser = new LogoParser(System.in);
    }
    
    public ICommand parse(String input) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        logoParser.ReInit(inputStream);
        return logoParser.parse(commandsFactory);
    }
    
}
