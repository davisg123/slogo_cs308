package commands;

import MovementAndImageAPI.*;

abstract class CommandsTurtle implements ICommand{

   Turtle turtle = null; 
   
   public CommandsTurtle(Turtle turtle) { 
       this.turtle = turtle;
   }
   
   protected Turtle turtle() {
       return turtle;
   }
   
}
