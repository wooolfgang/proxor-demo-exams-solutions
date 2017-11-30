// WhiteSpaceTest -- a simple, incomplete test of SpreadSheet, 
// modified to implement the spreadsheet-white-space task

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class WhiteSpaceTest extends TestCase {

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
					spreadsheet.setCell(0, 0, "123");
					spreadsheet.setCell(0, 1, "7");
					spreadsheet.setCell(0, 2, "= A1 + B1");
					spreadsheet.evaluate();
					String cellText0 = spreadsheet.getCellText(0, 0);
					String cellText1 = spreadsheet.getCellText(0, 1);
					String cellText2 = spreadsheet.getCellText(0, 2);
					assertTrue("corrupted a cell", cellText0.equals("123"));
					assertTrue("corrupted a cell", cellText1.equals("7"));
					System.out.println("testEvaluate: = A1 + B1 -> "
							+ cellText2);
					assertTrue("failed to evaluate prefix formula = A1 + B1",
							Double.valueOf(cellText2) == 130.0);
				}
			});
		} catch (InvocationTargetException e) {
			Assert.fail(e.getCause().getMessage());
		} catch (Exception e) {
			Assert.fail("invokeAndWait (Swing task) raised exception");
		}
	}
}
