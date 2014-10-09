package commands;

import parser.Input;
import com.sun.javafx.geom.Point2D;

public abstract class Command implements Input {
    
    private double myDistance;
    private double myAngle;
    private Point2D myPosition;

    public Command () {
    }
    
    
    @Override
    public void setDistance(double pixels){
        myDistance = pixels;
    }
    
    @Override
    public void setAngle(double degrees){
        myAngle = degrees;
    }
    
    public void setPosition(Point2D position){
        myPosition = position;
    }
    
    public abstract void executeCommand();
    
    protected void sendDistanceToTurtle(){
        if (myDistance != 0){
            //turtle.updateLocation(myDistance);
        }
    }
    
    protected void sendAngleToTurtle(){
        if (myAngle != 0){
            //turtle.updateOrientation(myAngle);
        }
    }
    
    protected void sendAbsoluteAngleToTurtle(){
        //turtle.getOrientation();
        //calculate offset
    }
    
    protected void sendAbsolutePositionToTurtle(){
        if (myPosition != null){
            //Point2D turtlePoint = turtle.getPoint();
            //double x = turtlePoint.getX()-myPosition.getX();
            //double y = turtlePoint.getY()-myPosition.getY();
        }
    }
    
}
