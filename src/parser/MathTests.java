package parser;

import static org.junit.Assert.*;
import java.lang.reflect.Array;
import org.junit.Test;

public class MathTests {

    @Test
    /**
     * TODO: extend this to test each math command subclass
     * doesn't work with the abstract class
     */
    public void testMathCommand () {
        double[] arguments = new double[0];
        MathCommand mathCommand = new MathCommand(arguments);
        assert(mathCommand.getResult() == 0.0);
    }

}
