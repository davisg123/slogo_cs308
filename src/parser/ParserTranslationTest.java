package parser;

import static org.junit.Assert.*;
import org.junit.Test;

public class ParserTranslationTest {

    @Test
    public void testFrench () {
        ParserTranslation parser = new ParserTranslation();
        parser.setForeignLanguage("French");
        String right = parser.translateInput("droite");
        assertEquals(right,"Right");
    }
    
    @Test
    public void testRussian () {
        ParserTranslation parser = new ParserTranslation();
        parser.setForeignLanguage("Russian");
        String home = parser.translateInput("glavnaya");
        assertEquals(home,"Home");
    }

}
