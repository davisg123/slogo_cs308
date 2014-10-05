package parser;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandFactoryTest {

    @Test
    public void testForward () {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("FD 50");
        commandFactory.addCommand("FD SUM 50 20");
        //assert that turtle position is forward 120 from start
    }
    
    @Test
    public void testBack () {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("BK 40");
        commandFactory.addCommand("BK 10");
        //assert that turtle position is back 50 from start
    }
    
    @Test
    public void testChainedCommand () {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("fd sum 10 sum 10 sum 10 sum 20 20");
        //assert that turtle position is correct
    }

}
