package com.nh.tasks.polishnotation;

/**
 * Created by Nelly on 25.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        String input = "(1+2)*4+3";

        System.out.println("Expression: " + input);

        PolishNotation expression = new PolishNotation();
        String value = expression.convertExpression(input);

        System.out.println("Polish Notation: " + value);
        Long result = expression.calculate(value);

        System.out.println("Calculate = " + result);

    }
}
