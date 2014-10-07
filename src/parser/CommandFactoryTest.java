package parser;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandFactoryTest {

    @Test
    public void testForward () {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("Forward 50");
        //assert that turtle position is forward 50 from start
    }
    
    @Test
    public void testBack () {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("Backward 40");
        //assert that turtle position is back 50 from start
    }
    
    @Test
    public void testChainedCommand () {
        fail();
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.addCommand("fd sum 10 sum 10 sum 10 sum 20 20");
        //assert that turtle position is correct
    }

}
