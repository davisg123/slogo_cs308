package commands;

import MovementAndImageAPI.*;
import MovementAndImageAPI.src.TurtleHandler;

abstract class CommandsTurtle implements ICommand{

   TurtleHandler turtleHandler = null; 
   
   public CommandsTurtle(TurtleHandler turtleHandler) { 
       this.turtleHandler = turtleHandler;
   }
   
   protected TurtleHandler turtleHandler() {
       return turtleHandler;
   }
   
}
