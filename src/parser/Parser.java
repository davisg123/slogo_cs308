package parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.ResourceBundle;
import commands.*;

/**
 * 
 * Main parser class called by the front-end
 * Able to take a file or string and translate it if needed, then send it to the logoParser class
 * 
 * @author Davis
 *
 */

public class Parser {
    
    private static final String RESOURCELOCATION = "resources.languages";
    private static final String DOT = ".";
    private CommandsFactory commandsFactory = null;
    private LogoParser myLogoParser;
    private ParserTranslation myParserTranslation;


    public Parser (CommandsFactory commandsFactory) {
        myParserTranslation = new ParserTranslation();
        this.commandsFactory = commandsFactory;
    }
    
    /**
     * set parser to translate a file from another language to english
     * @param language
     * language string of a matching resource bundle
     */
    public void setForeignLanguage (String language) {
        myParserTranslation.setForeignLanguage(language);
    }
   
    public void createLogoParser () {
        myLogoParser = new LogoParser(System.in);
    }
    
    /**
     * parse a file by converting it to a string and parsing it as such
     * @param input
     * file to parse
     */
    public ICommand parseFile (File input) throws Exception {
        String fileAsString = readFile(input.getAbsolutePath(), StandardCharsets.UTF_8);
        return parse(fileAsString);
    }
    
    /**
     * parse an input string by sending it to the logoParser
     * @param input
     * string to parse
     */
    public ICommand parse (String input) throws Exception {
        String finalInput = input;
        finalInput = myParserTranslation.translateInput(finalInput);
        //now drop case
        finalInput = finalInput.toLowerCase();
        InputStream inputStream = new ByteArrayInputStream(finalInput.getBytes());
        myLogoParser.ReInit(inputStream);
        return myLogoParser.parse(commandsFactory);
    }
    
    /**
     * helper method for turning a file into a string
     * @param path
     * file path
     * @param encoding
     * character encoding
     */
    static String readFile (String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
}
