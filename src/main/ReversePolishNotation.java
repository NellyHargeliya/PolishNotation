package main;

import java.lang.*;
import java.util.regex.Pattern;

/**
 * Preparation of the Polish notation the mathematical expression
 * and the calculation of the value of this expression.
 * #Created by Nelly#
 */
public class ReversePolishNotation {
    private static String regex = "[(]?\\d[0-9[+/*-[()]]]*\\d[)]?";
    private static String regPolNotation = "\\d[0-9[+/*-][ ]]*";

    //The calculation of the value of expression.
    //The method takes the expression as a string and returns the result
    public String reverseExpression(String input) {
        try {
            if (!checkFallPast(input)) return null;
            String value = new ExpressionParser().getExpression(input);
            return value;
        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }

    public Long calculatePolishNotation(String value) {
        try {
            if (!checkFallPastPolichNotition(value)) return null;
            Long result = new CountingExpression().counting(value);
            return result;
        } catch (Exception e) {
            //   System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }

    //The expression checks fall  has not valid
    private boolean checkFallPast(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    //The expression checks fall  has not polish notation
    private boolean checkFallPastPolichNotition(String input) {
        Pattern pattern = Pattern.compile(regPolNotation);
        return pattern.matcher(input).matches();
    }


}
