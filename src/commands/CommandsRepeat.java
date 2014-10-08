package commands;

public class CommandsRepeat implements ICommand {
    private int times;
    private ICommand command;
    
   CommandsRepeat(int times, ICommand command) { 
       this.times = times;
       this.command = command;
   }
   
   @Override
   public void execute() {
       for(int i = 0; i < times; i++) {
           command.execute();
       }
   }
    
}
