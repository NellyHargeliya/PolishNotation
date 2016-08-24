package test;

import main.ConverterPolishNotation;
import main.ReversePolishNotation;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestReversePolishNotation {
    @Test
    public void testGetExpression1() {
       ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue((rpn.getConvertExpression("(1+2)*4+3")).equals("1 2 + 4 * 3 + "));
    }

    @Test
    public void testGetExpression2() {
        ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue(rpn.getConvertExpression("(8+2*5)/(1+3*2-4)").equals("8 2 5 * + 1 3 2 * 4 - + / "));
    }

    @Test
    public void testGetExpression3() {
        ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue(rpn.getConvertExpression("(15/3+11-3*5)/3*(6-10)").equals("15 3 / 11 3 5 * - + 3 / 6 10 - * "));
    }

    @Test
    public void testCalculate1() {
        ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue(rpn.getCalculate(rpn.getConvertExpression("(1+2)*4+3")) == 15);
    }

    @Test
    public void testCalculate2() {
        ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue(rpn.getCalculate(rpn.getConvertExpression("(8+2*5)/(1+3*2-4)")) == 6);
    }

    @Test
    public void testCalculate3() {
        ReversePolishNotation rpn = new ReversePolishNotation();
        assertTrue(rpn.getCalculate(rpn.getConvertExpression("(15/3+11-3*5)/3*(6-10)")) == 18);
    }

}
