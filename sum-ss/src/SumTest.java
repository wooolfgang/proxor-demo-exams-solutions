
import java.lang.reflect.InvocationTargetException;
import junit.framework.TestCase;

public class SumTest extends TestCase {
	
	protected SpreadSheet spreadSheetInit() {
    	SpreadSheet spreadsheet = new SpreadSheet("SpreadSheet");
        //Set up the content pane.
        spreadsheet.addComponentsToPane(spreadsheet.getContentPane());
        //Display the window.
        spreadsheet.pack();
        spreadsheet.setVisible(true);
        spreadsheet.setCell(0, 0, "2"); // A1
        spreadsheet.setCell(0, 1, "3");
        spreadsheet.setCell(0, 2, "4");
        
        // test1 summing a row
        spreadsheet.setCell(0, 3, "=SUM(A1:C1)");
        
        spreadsheet.evaluate();
        return spreadsheet;
    }
	
	   // initialize and run spreadsheet, then test row, col for a value
    // which can be either a string (expected), or if expected == null, the
    // spreadsheet value converted to a double should equal expectedDouble
    protected void runOneTest(String msg, int row, int col, 
                              String expected, double expectedDouble) {
        final String fmsg = msg;
        final int frow = row;
        final int fcol = col;
        final String fexpected = expected;
        final double fexpectedDouble = expectedDouble;
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    SpreadSheet ss = spreadSheetInit();
                    String cellText = ss.getCellText(frow, fcol);
                    if (fexpected != null) { // expecting a string
                        assertTrue(fmsg, cellText.equals(fexpected));
                    } else { // expecting a double so string test is no good, 
                    		 // e.g. "1.0" != "1.00" but 1.0 == 1.00
                        assertTrue(fmsg, 
                            Double.valueOf(cellText) == fexpectedDouble);
                    }
                }
            });
        } catch (InvocationTargetException e) {
            fail(e.getCause().getMessage());
        } catch (Exception e) {
            fail("invokeAndWait (Swing task) raised exception");
        }        
    }
    
	public final void test1() {
        runOneTest("failed to evaluate formula =SUM(A1:C1)",
                0, 3, null, 9.0); }

}
