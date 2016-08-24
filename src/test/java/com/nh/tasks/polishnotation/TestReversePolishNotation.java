package com.nh.tasks.polishnotation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestReversePolishNotation {

    @Test
    public void testGetExpression1() {
        PolishNotation rpn = new PolishNotation();
        assertTrue((rpn.convertExpression("(1+2)*4+3")).equals("1 2 + 4 * 3 + "));
    }

    @Test
    public void testGetExpression2() {
        PolishNotation rpn = new PolishNotation();
        assertTrue(rpn.convertExpression("(8+2*5)/(1+3*2-4)").equals("8 2 5 * + 1 3 2 * 4 - + / "));
    }

    @Test
    public void testGetExpression3() {
        PolishNotation rpn = new PolishNotation();
        assertTrue(rpn.convertExpression("(15/3+11-3*5)/3*(6-10)").equals("15 3 / 11 3 5 * - + 3 / 6 10 - * "));
    }

    @Test
    public void testCalculate1() {
        PolishNotation rpn = new PolishNotation();
        assertTrue(rpn.calculate(rpn.convertExpression("(1+2)*4+3")) == 15);
    }

    @Test
    public void testCalculate2() {
        PolishNotation rpn = new PolishNotation();
        assertTrue(rpn.calculate(rpn.convertExpression("(8+2*5)/(1+3*2-4)")) == 6);
    }

    @Test
    public void testCalculate3() {
        PolishNotation rpn = new PolishNotation();
        assertTrue(rpn.calculate(rpn.convertExpression("(15/3+11-3*5)/3*(6-10)")) == 18);
    }

}
