package com.nh.tasks.polishnotation;

import java.util.Deque;
import java.util.LinkedList;

import static com.nh.tasks.polishnotation.ExpressionUtil.*;

public class Converter {
    private Deque<Character> operStack = new LinkedList<Character>();

    //The method converts the input string expression to Polish notation
    public String convert(String input) {
        String output = "";
        char[] tempInput = input.toCharArray();
        try {
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
            return output;
        } catch (Exception e) {
            System.err.println("The expression has symbols that not numbers and operators!");
        }
        return null;
    }


}