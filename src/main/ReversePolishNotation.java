package main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.*;
import java.lang.*;

/**
 * Preparation of the Polish notation the mathematical expression
 * and the calculation of the value of this expression.
 *
 * #Created by Nelly#
 */
public class ReversePolishNotation {
    private static String regex = "[0-9[+/*-[()]]]*";

    //The method converts the input string expression to Polish notation
    public String getExpression(String input) {
        Deque<Character> operStack = new LinkedList<>();
        String output = "";
        if (!checkFallPast(input)) {
            System.err.println("The expression has symbols that not numbers and operators!");
        } else {
            char[] tempInput = input.toCharArray();
            for (int i = 0; i < tempInput.length; i++) {
                if (isDelimeter(tempInput[i])) continue;
                if (isDigit(tempInput[i])) {
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
                        while (!(s=='(')) {
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
        }
        return output;
    }
    //The calculation of the value of expression.
    //The method takes the expression as a string and returns the result
    public Integer calculate(String input) {
        String value = getExpression(input);
        Integer result = counting(value);
        return result;
    }
    //The method calculates the value of the expression, already converted to polish notation
    private Integer counting(String value) {
        LinkedList<Integer> numbers = new LinkedList<>();
        LinkedList<Character> opers = new LinkedList<>();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (isDelimeter(c))
                continue;
            if (c == '(')
                opers.add('(');
            else if (c == ')') {
                while (opers.getLast() != '(')
                    opers.removeLast();

            } else if (isOperator(c)) {
                while (!opers.isEmpty() && getPriority(opers.getLast()) >= getPriority(c))
                    processOperator(numbers, opers.removeLast());
                opers.add(c);
            } else {
                String operand = "";
                while (i < value.length() && Character.isDigit(value.charAt(i)))
                    operand += value.charAt(i++);
                --i;
                numbers.add(Integer.parseInt(operand));
            }
        }
        while (!opers.isEmpty())
            processOperator(numbers, opers.removeLast());
        return numbers.get(0);
    }

    private void processOperator(LinkedList<Integer> numbers, char operator) {
        int oneElement = numbers.removeLast();
        int twoElement = numbers.removeLast();
        switch (operator) {
            case '+': numbers.add(twoElement + oneElement);  break;
            case '-': numbers.add(twoElement - oneElement);  break;
            case '*': numbers.add(twoElement * oneElement);  break;
            case '/': numbers.add(twoElement / oneElement);  break;
        }
    }

    //The expression checks fall  has not numbers and operators
    private boolean checkFallPast(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    //Checking whether a character is a delimeter
    private boolean isDelimeter(char ch) {
        return ch == ' ' || ch == '=';
    }

    //Checking whether a character is a number
    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    //Checking whether a character is a operator
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ')' || ch == '(';
    }

    //Check operator priority
    private byte getPriority(char s) {
        switch (s) {
            case '(': return 0;
            case ')': return 1;
            case '+': return 2;
            case '-': return 3;
            case '*': return 4;
            case '/': return 4;
            default:  return 5;
        }
    }

}
