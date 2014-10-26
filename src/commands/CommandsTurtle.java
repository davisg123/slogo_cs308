package commands;

import MovementAndImageAPI.src.GeneralTurtleHandler;

abstract class CommandsTurtle implements ICommand{

   GeneralTurtleHandler turtleHandler = null; 
   
   public CommandsTurtle(GeneralTurtleHandler turtleHandler) { 
       this.turtleHandler = turtleHandler;
   }
   
   protected GeneralTurtleHandler turtleHandler() {
       return turtleHandler;
   }
   
}
