// This entire file is part of my masterpiece.
// Davis Gossage
package parser;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * 
 * @author Davis
 * 
 * translates input into a language according to the user preference
 * looks at properties file to translate tokens into english
 *
 */

public class ParserTranslation {
    
    private static ResourceBundle ourLanguageBundle;
    private static final String RESOURCELOCATION = "resources.languages";
    private static final String DOT = ".";
    
    

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
    
    /**
     * translate a given input by looking at words and checking for matches in the resource bundle
     * @param input
     * string to translate
     */
    public String translateInput (String input) {
        if (ourLanguageBundle != null){
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
        //no bundle, probably english.  return the input back.
        return input;
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
    
}
