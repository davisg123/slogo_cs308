package commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CommandsFactory {

    private static ResourceBundle ourCommandClasses;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String DOT = ".";
    private List<Command> myCommandList;

    public CommandsFactory () {
        myCommandList = new ArrayList<Command>();
        ourCommandClasses = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ClassConversion");
    }

   public CommandsQueue CommandsQueue() { 
       return new CommandsQueue();
   }
    
   public ICommand CommandsRepeat(int times, ICommand command) { 
       return new CommandsRepeat(times, command);
   }
    
  public ICommand CommandMake(String name, ICommand command) { 
      return new CommandMake(name, command);
  }
  
  public ICommand CommandExecute(String commandName) {
      return new CommandExecute(commandName);
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

    public ICommand pickUpTurtle() {
        return new ActivityCommand(turtle, ActivityCommand.CommandType.PICK_UP);
    }
    
    public ICommand putTurtle() {
        return new ActivityCommand(turtle, ActivityCommand.CommandType.PUT);
    }
    
    public ICommand showTurtle() {
        return new ActivityCommand(turtle, ActivityCommand.CommandType.SHOW);
    }
    
    public ICommand hideTurtle() {
        return new ActivityCommand(turtle, ActivityCommand.CommandType.HIDE);
    }
    
    public ICommand rotateTurtle(double angle) {
        return new MovementCommand(turtle, MovementCommand.CommandType.ROTATE, angle);
    }
    
    public ICommand rotateTurtleLeft(double angle) {
        return new MovementCommand(turtle, MovementCommand.CommandType.ROTATE, angle);
    }
    
    public ICommand rotateTurtleRight(double angle) {
        return new MovementCommand(turtle, MovementCommand.CommandType.ROTATE, -1.0 * angle);
    }

    public ICommand turtleGoForward(double distance) {
        return new MovementCommand(turtle, MovementCommand.CommandType.GO_FORWARD, distance);
    }
    
    public ICommand turtleGoBack(double distance) {
        return new MovementCommand(turtle, MovementCommand.CommandType.GO_BACK, distance);
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
