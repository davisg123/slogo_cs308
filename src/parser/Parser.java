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
    
    private static ResourceBundle ourLanguageBundle;
    private static final String RESOURCELOCATION = "resources.languages";
    private static final String DOT = ".";
    private CommandsFactory commandsFactory = null;
    private LogoParser myLogoParser;


    public Parser (CommandsFactory commandsFactory) {
        this.commandsFactory = commandsFactory;
    }
    
    /**
     * set parser to translate a file from another language to english
     * @param language
     * language string of a matching resource bundle
     */
    public void setForeignLanguage (String language) {
        if (language.equals("English")) {
            //don't bother translating, the parser handles english
            ourLanguageBundle = null;
        }
        else {
            ourLanguageBundle = ResourceBundle.getBundle(RESOURCELOCATION + DOT + language);
        }
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
        if (ourLanguageBundle != null) {
            finalInput = translateInput(finalInput);
        }
        //now drop case
        finalInput = finalInput.toLowerCase();
        InputStream inputStream = new ByteArrayInputStream(finalInput.getBytes());
        myLogoParser.ReInit(inputStream);
        return myLogoParser.parse(commandsFactory);
    }
    
    /**
     * translate a given input by looking at words and checking for matches in the resource bundle
     * @param input
     * string to translate
     */
    private String translateInput (String input) {
        String[] splitInput = input.split("\\s+");
        for (int i = 0; i < splitInput.length; i++) {
            //do we have a matching entry in the resource bundle?
            // get the keys
            Enumeration<String> enumeration = ourLanguageBundle.getKeys();
            // check keys for matches
            while (enumeration.hasMoreElements()) {
               String nextKey = enumeration.nextElement();
               String csValues = ourLanguageBundle.getString(nextKey);
               String[] separatedValues = csValues.split("\\s*,\\s*");
               for (int j = 0; j < separatedValues.length; j++) {
                   if (splitInput[i].equals(separatedValues[j])) {
                       //replace the foreign value with the matching key
                       splitInput[i] = nextKey;
                   }
               }
            }
        }
        //space separated string has been processed
        return buildSpaceSeparatedString(splitInput);
    }
    
    /**
     * helper method to turn an array of strings into a space-seperated string
     * @param splitString
     * string array
     * @return
     * joined string
     */
    private String buildSpaceSeparatedString (String[] splitString) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < splitString.length; i++) {
            builder.append(splitString[i]);
            if (i != splitString.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
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
