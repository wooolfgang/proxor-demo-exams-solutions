// NumberDisplayTest -- a simple, incomplete test of 
// SpreadSheet modified to implement the number-display task

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumberDisplayTest extends TestCase {

    // this method is designed to run on the AWT thread
    public void reportError(String msg) {
        throw new RuntimeException(msg);
    }

    /**
     * Test method for {@link SpreadSheet#evaluate()}.
     */
    public final void testEvaluate() {
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        SpreadSheet spreadsheet = new SpreadSheet("SpreadSheet");
                        // Set up the content pane.
                        spreadsheet.addComponentsToPane(spreadsheet
							.getContentPane());
                        // Display the window.
                        spreadsheet.pack();
                        spreadsheet.setVisible(true);
                        spreadsheet.setCell(0, 0, "1.23456");
                        spreadsheet.setCell(0, 1, "1.234567");
                        spreadsheet.setCell(0, 2, "1.234567890"); // lower case
                         
                        spreadsheet.evaluate();
                        String cellText0 = spreadsheet.getCellText(0, 0);
                        String cellText1 = spreadsheet.getCellText(0, 1);
                        String cellText2 = spreadsheet.getCellText(0, 2);
                         
                        assertTrue("corrupted a short number",
                                   cellText0.equals("1.23456"));
                        assertTrue("corrupted an 8-character number",
                                   cellText1.equals("1.234567"));
                        assertTrue("failed to shorten an 11-character number",
                                   cellText2.equals("1.234568"));
                     }
                });
        } catch (InvocationTargetException e) {
            Assert.fail(e.getCause().getMessage());
        } catch (Exception e) {
            Assert.fail("invokeAndWait (Swing task) raised exception");
        }
    }
}
