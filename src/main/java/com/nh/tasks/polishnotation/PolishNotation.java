package com.nh.tasks.polishnotation;

import static com.nh.tasks.polishnotation.ExpressionUtil.checkFallPast;
import static com.nh.tasks.polishnotation.ExpressionUtil.checkFallPastPolichNotition;

/**
 * Preparation of the Polish notation the mathematical expression
 * and the calculation of the value of this expression.
 * #Created by Nelly#
 */
public class PolishNotation {

    //The method takes the expression as a string and returns the result
    public String convertExpression(String input) {
        try {
            if (!checkFallPast(input)) return null;
            return new Converter().convert(input);
        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }

    //The calculation of the value of expression.
    public Long calculate(String value) {
        try {
            if (!checkFallPastPolichNotition(value)) return null;
            return new Calculator().calculatePolishNotation(value);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
