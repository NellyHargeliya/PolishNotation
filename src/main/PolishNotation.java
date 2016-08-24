package main;

import static main.ExpressionUtil.checkFallPast;
import static main.ExpressionUtil.checkFallPastPolichNotition;

/**
 * Preparation of the Polish notation the mathematical expression
 * and the calculation of the value of this expression.
 * #Created by Nelly#
 */
public class PolishNotation {

    //The method takes the expression as a string and returns the result
    public String getConvertExpression(String input) {
        try {
            if (!checkFallPast(input)) return null;
            String value = new ConverterPolishNotation().convert(input);
            return value;
        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }

    //The calculation of the value of expression.
    public Long getCalculate(String value) {
        try {
            if (!checkFallPastPolichNotition(value)) return null;
            Long result = new CalculateExpression().calculatePolishNotation(value);
            return result;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }


}
