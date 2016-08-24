package test.TestDDT;
import main.ReversePolishNotation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nelly
 */
public class DataDrivenTest {
    ReversePolishNotation processor = new ReversePolishNotation();

    @Test
    public void testZero() throws Exception {
        String ZERO = "ноль";
        System.out.println("Test Programm: test 1 - zero");
        System.out.println(ZERO + " = " + processor.getConvertExpression(ZERO));
        assertEquals("Ошибка, нет нуля", null, processor.getConvertExpression(ZERO));
    }

    @Test
    public void testFailExpression() throws Exception {
        String[] expressions = {"+", "2 +", "a 2 -", "2 2 +-", "2+a", "2+2a", "*2", " 2/", "2+2(", "(2+2)+("};
        System.out.println("Test Programm: test 2 - valid expression");

        for (String i : expressions) {
            System.out.println(i + " = " + processor.getConvertExpression(i));
            assertEquals("Ошибка, валидности выражения", null, processor.getConvertExpression(i));
        }
    }

    @Test
    public void testFailPolishNotation() throws Exception {
        String[] polishNotation = {"a 2 -", "/2 2 +-", "2+a", "(2+2a", "*2", "-+(2+2"};
        System.out.println("Test Programm: test 3 - valid polish notation");

        for (String i : polishNotation) {
            System.out.println(i + " = " + processor.getConvertExpression(i));
            assertEquals("Ошибка, валидности польской нотации", null, processor.getConvertExpression(i));
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
        assertEquals("Ошибка в выражении: " + inExpression, inResult, processor.getConvertExpression(inExpression));
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
        assertEquals("Ошибка в подсчете польского выражения: " + inPolishExpression, inResult, (long) processor.getCalculate(inPolishExpression));
    }
}
