import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class ReadFileWriteToConsoleTest extends TestCase {

	public final void testMain() {
		// make sure in.csv is as expected
		BufferedWriter output;
		ReadFileWriteToConsole a;
		try {
			output = new BufferedWriter(new FileWriter("in.csv"));
			output.write("1,2,3");
			output.newLine();
			output.write("Hello World,=A1+B1*C1,foo");
			output.newLine();
			output.write(",,perfect");
			output.close();
			// read in.csv
			a = new ReadFileWriteToConsole();
			a.readSheet();

			// test it
			assertTrue(
					"Failed to read in.csv to sheet",
					a.sheet[0][0].equals("1") && a.sheet[0][1].equals("2")
							&& a.sheet[0][2].equals("3")
							&& a.sheet[1][0].equals("Hello World")
							&& a.sheet[1][1].equals("=A1+B1*C1")
							&& a.sheet[1][2].equals("foo")
							&& a.sheet[2][0].equals("")
							&& a.sheet[2][1].equals("")
							&& a.sheet[2][2].equals("perfect"));

			// now, capture the output to console and check it
			ByteArrayOutputStream allOutput = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(allOutput);
			PrintStream console = System.out;
			System.setOut(out);
			// write to console
			a.writeSheet();
			String got = allOutput.toString();
			System.setOut(console);
			System.out.println("Got: ");
			System.out.print(got);
			String expected = "[1][2][3]\n[Hello World][=A1+B1*C1][foo]\n[][][perfect]";
			System.out.println("Expected: ");
			System.out.print(expected);
			expected = expected.replace("\n",
					System.getProperty("line.separator"));
			assertTrue("Output does not match expected string",
					got.startsWith(expected));
		} catch (IOException e) {
			fail("IOException encountered establishing in.csv");
		}
	}
}
