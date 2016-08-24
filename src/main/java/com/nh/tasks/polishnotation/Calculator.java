package com.nh.tasks.polishnotation;

import java.util.LinkedList;

import static com.nh.tasks.polishnotation.ExpressionUtil.*;

public class Calculator {
    LinkedList<Long> numbers = new LinkedList<Long>();
    LinkedList<Character> opers = new LinkedList<Character>();

    //The method calculates the value of the expression, already converted to polish notation
    public Long calculatePolishNotation(String value) {

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (isDelimeter(c))
                continue;
            addOpers(c);
            if (c == ')') {
                while (opers.getLast() != '(') opers.removeLast();
            } else if (isOperator(c)) {
                while (!opers.isEmpty() && getPriority(opers.getLast()) >= getPriority(c))
                    processOperator(numbers, opers.removeLast());
                opers.add(c);
            } else {
                String operand = "";
                while (i < value.length() && Character.isDigit(value.charAt(i)))
                    operand += value.charAt(i++);
                --i;
                numbers.add(Long.parseLong(operand));
            }
        }
        while (!opers.isEmpty())
            processOperator(numbers, opers.removeLast());
        return numbers.get(0);
    }


    private void addOpers(char c) {
        if (c == '(') opers.add('(');
    }

    private void processOperator(LinkedList<Long> numbers, char operator) {
        long oneElement = numbers.removeLast();
        long twoElement = numbers.removeLast();
        switch (operator) {
            case '+':
                numbers.add(twoElement + oneElement);
                break;
            case '-':
                numbers.add(twoElement - oneElement);
                break;
            case '*':
                numbers.add(twoElement * oneElement);
                break;
            case '/':
                numbers.add(twoElement / oneElement);
                break;
        }
    }
}
