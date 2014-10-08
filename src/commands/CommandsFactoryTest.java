package commands;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandsFactoryTest {

    @Test
    public void testForward () {
        CommandsFactory commandFactory = new CommandsFactory();
        commandFactory.addCommand("Forward 50");
        //assert that turtle position is forward 50 from start
    }
    
    @Test
    public void testBack () {
        CommandsFactory commandFactory = new CommandsFactory();
        commandFactory.addCommand("Backward 40");
        //assert that turtle position is back 50 from start
    }
    
    @Test
    public void testChainedCommand () {
        fail();
        CommandsFactory commandFactory = new CommandsFactory();
        commandFactory.addCommand("fd sum 10 sum 10 sum 10 sum 20 20");
        //assert that turtle position is correct
    }

}
