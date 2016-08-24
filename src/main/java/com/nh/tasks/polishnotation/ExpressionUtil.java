package com.nh.tasks.polishnotation;

import java.util.regex.Pattern;

public class ExpressionUtil {
    private static final String REGEX = "[(]?\\d[0-9[+/*-[()]]]*\\d[)]?";
    private static final Pattern GENERAL_PATTERN = Pattern.compile(REGEX);
    private static String REGPOLNOTATION = "\\d[0-9[+/*-][ ]]*";
    private static final Pattern POLISH_NOTATION_PATTTERN = Pattern.compile(REGPOLNOTATION);

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

    //The expression checks fall  has not valid
    public static boolean checkFallPast(String input) {
        return GENERAL_PATTERN.matcher(input).matches();
    }

    //The expression checks fall  has not polish notation
    public static boolean checkFallPastPolichNotition(String input) {
        return POLISH_NOTATION_PATTTERN.matcher(input).matches();
    }
}
