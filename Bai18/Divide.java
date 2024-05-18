
/**
 * Write a description of class Divide here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Divide implements Expression
{
    private final Expression leftExpression;
    private final Expression rightExpression;

    public Divide(Expression leftExpression,Expression rightExpression ){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    
    @Override
    public double interpret() {
        return leftExpression.interpret() / rightExpression.interpret();
    }
}
