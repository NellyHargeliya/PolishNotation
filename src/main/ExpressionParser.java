package main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Created by Nelly
 */
public class ExpressionParser extends ReversePolishNotation {
    private static String regex = "[0-9[+/*-[()]]]*";

    //The method converts the input string expression to Polish notation

    public String getExpression(String input) {
        Deque<Character> operStack = new LinkedList<>();
        String output = "";
        try {
            checkFallPast(input);
            char[] tempInput = input.toCharArray();
            for (int i = 0; i < tempInput.length; i++) {
                if (isDelimeter(tempInput[i])) continue;
                if (Character.isDigit(tempInput[i])) {
                    while (!isDelimeter(tempInput[i]) && !isOperator(tempInput[i])) {
                        output += tempInput[i];
                        i++;
                        if (i == tempInput.length) break;
                    }
                    output += " ";
                    i--;
                }
                if (isOperator(tempInput[i])) {
                    if (tempInput[i] == '(') {
                        operStack.push(tempInput[i]);
                    } else if (tempInput[i] == ')') {
                        char s = operStack.pop();
                        while (!(s == '(')) {
                            output += s + " ";
                            s = operStack.pop();
                        }
                    } else {
                        if (operStack.size() > 0)
                            if (getPriority(tempInput[i]) <= getPriority(operStack.peek()))
                                output += operStack.pop() + " ";
                        operStack.push(input.charAt(i));
                    }
                }
            }
            while (operStack.size() > 0)
                output += operStack.pop() + " ";

        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }

        return output;
    }

    //The expression checks fall  has not numbers and operators
    private boolean checkFallPast(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    //Checking whether a character is a delimeter
    protected boolean isDelimeter(char ch) {
        return ch == ' ' || ch == '=';
    }

    //Checking whether a character is a operator
    protected boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')' || ch == '(';
    }

    //Check operator priority
    protected byte getPriority(char s) {
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

}