package parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.ResourceBundle;
import commands.*;

public class Parser {

    private CommandsFactory commandsFactory = null;
    private LogoParser logoParser;
    private static ResourceBundle ourLanguageBundle;
    private static String RESOURCELOCATION = "resources.languages";
    private static String DOT = ".";

    public Parser(CommandsFactory commandsFactory) {
        this.commandsFactory = commandsFactory;
    }
    
    public void setForeignLanguage(String language){
        if (language.equals("English")){
            //don't bother translating, the parser handles english
            ourLanguageBundle = null;
        }
        else{
            ourLanguageBundle = ResourceBundle.getBundle(RESOURCELOCATION+DOT+language);
        }
    }
   
    public void createLogoParser() {
        logoParser = new LogoParser(System.in);
    }
    
    public ICommand parse(String input) throws Exception {
        if (ourLanguageBundle != null){
            input = translateInput(input);
        }
        //now drop case
        input = input.toLowerCase();
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        logoParser.ReInit(inputStream);
        return logoParser.parse(commandsFactory);
    }
    
    private String translateInput(String input){
        String[] splitInput = input.split("\\s+");
        for (int i=0; i<splitInput.length; i++){
            //do we have a matching entry in the resource bundle?
            // get the keys
            Enumeration<String> enumeration = ourLanguageBundle.getKeys();
            // check keys for matches
            while (enumeration.hasMoreElements()) {
               String nextKey = enumeration.nextElement();
               String csValues = ourLanguageBundle.getString(nextKey);
               String[] separatedValues = csValues.split("\\s*,\\s*");
               for (int j=0; j<separatedValues.length; j++){
                   if (splitInput[i].equals(separatedValues[j])){
                       //replace the foreign value with the matching key
                       splitInput[i] = nextKey;
                   }
               }
            }
        }
        //space separated string has been processed
        return buildSpaceSeparatedString(splitInput);
    }
    
    private String buildSpaceSeparatedString(String[] splitString){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<splitString.length; i++) {
            builder.append(splitString[i]);
            if (i != splitString.length-1){
                builder.append(" ");
            }
        }
        return builder.toString();
    }
    
}
