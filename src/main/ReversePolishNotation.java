package main;
import java.lang.*;

/**
 * Preparation of the Polish notation the mathematical expression
 * and the calculation of the value of this expression.
 * <p>
 * #Created by Nelly#
 */
public class ReversePolishNotation {
    //The calculation of the value of expression.
    //The method takes the expression as a string and returns the result
    public Integer calculate(String input) {
        try {
            String value = new ExpressionParser().getExpression(input);
            Integer result = new CountingExpression().counting(value);
            return result;
        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }



}
