package parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import commands.Command;


public class CommandFactory {

    private static ResourceBundle ourCommandClasses;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String DOT = ".";
    private List<Command> myCommandList;

    public CommandFactory () {
        myCommandList = new ArrayList<Command>();
        ourCommandClasses = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ClassConversion");
    }

    /**
     * parses command with getCommand method to get type of command
     * 
     * @param command
     * @return parsed command
     */
    public Command getCommand (Command command) {
        return command;
    }

    /**
     * adds a command to the queue for processing
     * 
     * @param command
     */
    public void addCommand (String command) {
        // command should be in form 'Forward 50'
        String[] commandParams = command.split(" ");
        String commandBundleName = ourCommandClasses.getString("command_package");
        String commandClassName = ourCommandClasses.getString(commandParams[0]);
        Command commandInstance =
                instantiateClassFromString(commandBundleName + DOT + commandClassName,
                                           Double.parseDouble(commandParams[1]));
        myCommandList.add(commandInstance);
    }

    /**
     * cycles through the queue of command objects,
     * and determines the order for processing
     */
    public void processQueuedCommands () {

    }

    private Command instantiateClassFromString (String commandClassName, double arg) {
        try {
            Class<?> commandClass = Class.forName(commandClassName);
            return (Command)commandClass.getConstructors()[0].newInstance(arg);
        }
        catch (ClassNotFoundException | IllegalArgumentException | SecurityException
                | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
