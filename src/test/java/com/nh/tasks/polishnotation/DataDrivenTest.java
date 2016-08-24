package com.nh.tasks.polishnotation;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nelly
 */
public class DataDrivenTest {
    PolishNotation processor = new PolishNotation();

    @Test
    public void testZero() throws Exception {
        String ZERO = "null";
        System.out.println("Test Programm: test 1 - zero");
        System.out.println(ZERO + " = " + processor.convertExpression(ZERO));
        assertEquals("Expression, not null", null, processor.convertExpression(ZERO));
    }

    @Test
    public void testFailExpression() throws Exception {
        String[] expressions = {"+", "2 +", "a 2 -", "2 2 +-", "2+a", "2+2a", "*2", " 2/", "2+2(", "(2+2)+("};
        System.out.println("Test Programm: test 2 - valid expression");

        for (String i : expressions) {
            System.out.println(i + " = " + processor.convertExpression(i));
            assertEquals("Expression, valid expression", null, processor.convertExpression(i));
        }
    }

    @Test
    public void testFailPolishNotation() throws Exception {
        String[] polishNotation = {"a 2 -", "/2 2 +-", "2+a", "(2+2a", "*2", "-+(2+2"};
        System.out.println("Test Programm: test 3 - valid polish notation");

        for (String i : polishNotation) {
            System.out.println(i + " = " + processor.convertExpression(i));
            assertEquals("Expression, valid polish notation expression", null, processor.convertExpression(i));
        }
    }

    @Test
    public void testGetExpressionAllTable() throws Exception {

        System.out.println("Test Programm: test 4 - Different expressions");

        // Open the Excel file
        FileInputStream expressions = new FileInputStream("src/test/resources/DataExcel.xls");
        // Access the required test data sheet
        HSSFWorkbook wb = new HSSFWorkbook(expressions);
        String inExpression = null;
        String inResult = null;
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            System.out.print((inExpression = row.getCell(0).getStringCellValue()) + " = ");
            System.out.print((inResult = row.getCell(1).getStringCellValue()));
            System.out.println();
        }
        System.out.println();
        assertEquals("Exception expression : " + inExpression, inResult, processor.convertExpression(inExpression));
    }

    @Test
    public void testGetNotationAllTable() throws Exception {

        System.out.println("Test Programm: test 5 - Different expressions");

        // Open the Excel file
        FileInputStream expressions = new FileInputStream("src/test/resources/DataExcel.xls");
        // Access the required test data sheet
        HSSFWorkbook wb = new HSSFWorkbook(expressions);
        String inPolishExpression = null;
        long inResult = 0;
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            System.out.print((inPolishExpression = row.getCell(1).getStringCellValue()) + " = ");
            System.out.print(inResult = (long) (row.getCell(2).getNumericCellValue()));
            System.out.println();
        }
        System.out.println();
        assertEquals("Expression, nor true calculate polish notation: " + inPolishExpression, inResult, (long) processor.calculate(inPolishExpression));
    }
}
