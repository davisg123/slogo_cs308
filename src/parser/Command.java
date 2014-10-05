package parser;

import java.awt.Point;

public abstract class Command implements Input {

    public Command () {
    }
    
    
    @Override
    public void setDistance(int pixels){
        
    }
    
    @Override
    public void setAngle(int degrees){
        
    }
    
    public void setPosition(Point position){
        
    }
    
    public void executeCommand() {
	}
}
