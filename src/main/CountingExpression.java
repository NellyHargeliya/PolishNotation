package main;

import java.util.LinkedList;

/**
 * Created by Nelly
 */
public class CountingExpression extends ExpressionParser {
    //The method calculates the value of the expression, already converted to polish notation
    protected Integer counting(String value) {
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
