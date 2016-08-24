package main;

import java.util.regex.Pattern;

/**
 * Created by Nelly on 24.08.2016.
 */
public class ExpressionUtil {
    private static String regex = "[(]?\\d[0-9[+/*-[()]]]*\\d[)]?";
    private static String regPolNotation = "\\d[0-9[+/*-][ ]]*";

    private ExpressionUtil() {
    }

    //Checking whether a character is a delimeter
    public static boolean isDelimeter(char ch) {
        return ch == ' ' || ch == '=';
    }

    //Checking whether a character is a operator
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')' || ch == '(';
    }

    //Check operator priority
    public static byte getPriority(char s) {
        switch (s) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
                return 2;
            case '-':
                return 3;
            case '*':
                return 4;
            case '/':
                return 4;
            default:
                return 5;
        }
    }

    //Check the validity of expression
    public static boolean validExpression(char firstValue, char lastValue) {
        if ((Character.isDigit(firstValue) & Character.isDigit(lastValue))) {
            return true;
        }
        return false;
    }

    //The expression checks fall  has not valid
    public static boolean checkFallPast(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    //The expression checks fall  has not polish notation
    public static boolean checkFallPastPolichNotition(String input) {
        Pattern pattern = Pattern.compile(regPolNotation);
        return pattern.matcher(input).matches();
    }


}
