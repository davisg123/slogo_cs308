package parser;

import java.awt.Point;

public abstract class Command {

    public Command () {
    }
    
    public void setDistance(int pixels){
        
    }
    
    public void setAngle(int degrees){
        
    }
    
    public void setPosition(Point position){
        
    }
    
    public abstract void executeCommand();
}
