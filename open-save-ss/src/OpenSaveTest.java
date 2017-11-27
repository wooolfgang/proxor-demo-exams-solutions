// OpenSaveTest -- a simple, incomplete test of SpreadSheet,
// modified to implement the open-save task.

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class OpenSaveTest extends TestCase {

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
					spreadsheet.setCell(0, 1, "=A1");
					spreadsheet.evaluate();
					File temp = new File("temp-ostest.csv");
					FileIO.saveAs(spreadsheet, temp);
					spreadsheet.setCell(0, 0, "0"); // modify a cell
					spreadsheet.setCell(0, 2, "oops"); // modify another cell
					spreadsheet.evaluate();
					FileIO.open(spreadsheet, temp);
					String cellText0 = spreadsheet.getCellText(0, 0);
					String cellText1 = spreadsheet.getCellText(0, 1);
					String cellText2 = spreadsheet.getCellText(0, 2);
					assertTrue("corrupted a cell", cellText0.equals("123"));
					assertTrue("failed to restore/evaluate formula",
							Double.valueOf(cellText1) == 123);
					assertTrue("failed to restore original cells",
							cellText2.equals(""));
				}
			});
		} catch (InvocationTargetException e) {
			Assert.fail(e.getCause().getMessage());
		} catch (Exception e) {
			Assert.fail("invokeAndWait (Swing task) raised exception");
		}
	}
}
