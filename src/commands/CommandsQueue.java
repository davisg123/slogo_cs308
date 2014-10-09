package commands;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandsQueue implements ICommand {

   CommandsQueue() { 

   }
    
  private Queue<ICommand> commandsQueue = new LinkedBlockingQueue<>();

  @Override
  public void execute() {
      for(ICommand command : commandsQueue) {
          command.execute();
      }
  }
   
   public void addCommand(ICommand command) { 
       if (command != null) {
           commandsQueue.add(command);
       }
   }
    
}
