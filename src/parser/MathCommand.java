package parser;

public abstract class MathCommand {

    /**
     * accepts how every many expressions are needed, depending on the subclass
     * ex. SUM would accept expr1 and expr2
     * LOG would accept expr1
     * 
     * @param expressions
     * the expressions
     */
    public MathCommand (double[] expressions) {
    }
    
    /**
     * get the result of the math command
     * @return
     * the result
     */
    public abstract double getResult();
    
}
