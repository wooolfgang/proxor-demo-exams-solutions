import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import com.csvreader.CsvWriter;

import junit.framework.TestCase;

public class ReadFileWriteToConsoleTest extends TestCase {

	public final void testMain() {
		testStaticVariable();
		// make sure in.csv is as expected
		BufferedWriter output;
		ReadFileWriteToConsole a;
		try {
			output = new BufferedWriter(new FileWriter("in.csv"));
			output.write("1,2,3,4,5,1\nHello World,7,foo,,2,\n,,perfect,3,,\n,,4,,= A1 + (B3 /16),\n,5,,,,\n6,,,,,\n"
					.replace("\n", System.getProperty("line.separator")));
			output.close();
			// read in.csv
			a = new ReadFileWriteToConsole();
			a.makeSheet();

			// test it
			assertTrue("Failed to read in.csv to sheet (row count)",
					a.getRowCount() == 6);
			assertTrue("Failed to read in.csv to sheet (col count)",
						a.getColCount() == 6);
			assertTrue(
					"Failed to read in.csv to sheet (data)",
					a.getCell(0, 0).equals("1") && a.getCell(0, 1).equals("2")
							&& a.getCell(0, 2).equals("3")
							&& a.getCell(0, 3).equals("4")
							&& a.getCell(0, 4).equals("5")
							&& a.getCell(0, 5).equals("1") &&

							a.getCell(1, 0).equals("Hello World")
							&& a.getCell(1, 1).equals("7")
							&& a.getCell(1, 2).equals("foo")
							&& a.getCell(1, 3).equals("")
							&& a.getCell(1, 4).equals("2")
							&& a.getCell(1, 5).equals("") &&

							a.getCell(2, 0).equals("")
							&& a.getCell(2, 1).equals("")
							&& a.getCell(2, 2).equals("perfect")
							&& a.getCell(2, 3).equals("3")
							&& a.getCell(2, 4).equals("")
							&& a.getCell(2, 5).equals("") &&

							a.getCell(3, 0).equals("")
							&& a.getCell(3, 1).equals("")
							&& a.getCell(3, 2).equals("4")
							&& a.getCell(3, 3).equals("")
							&& a.getCell(3, 4).equals("= A1 + (B3 /16)")
							&& a.getCell(3, 5).equals("") &&

							a.getCell(4, 0).equals("")
							&& a.getCell(4, 1).equals("5")
							&& a.getCell(4, 2).equals("")
							&& a.getCell(4, 3).equals("")
							&& a.getCell(4, 4).equals("")
							&& a.getCell(4, 5).equals("") &&

							a.getCell(5, 0).equals("6")
							&& a.getCell(5, 1).equals("")
							&& a.getCell(5, 2).equals("")
							&& a.getCell(5, 3).equals("")
							&& a.getCell(5, 4).equals("")
							&& a.getCell(5, 5).equals(""));

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
			String expected = "[1][2][3][4][5][1]\n[Hello World][7][foo][][2][]\n[][][perfect][3][][]\n[][][4][][= A1 + (B3 /16)][]\n[][5][][][][]\n[6][][][][][]\n";
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
	
	public final boolean testStaticVariable(){
		//checks if data structure used was static, which it should not be
		Runtime runtime = Runtime.getRuntime();
        long used = runtime.totalMemory() - runtime.freeMemory();
        ReadFileWriteToConsole a = new ReadFileWriteToConsole();
        try { // make the sheet to force an allocation
			a.makeSheet();
		} catch (IOException e) {
			fail("IOException from makeSheet()!");
		}
        long usedAfter = runtime.totalMemory() - runtime.freeMemory();
        long objBytes = usedAfter - used;
        long dim = (long) (Math.sqrt((double) objBytes / 8) + 0.5);
       
    	File file = new File("in.csv");
    	
        try {
            FileWriter fstream = new FileWriter(file.getAbsolutePath());
            BufferedWriter out = new BufferedWriter(fstream);
            for (long i = 0; i < dim - 1; i++) {
                out.write(",");
            }
            out.newLine();
            out.close();
        } catch (Exception e) {
            fail("file write exception");
        }
       
        try {
			a.makeSheet();
		} catch (IOException e) {
			fail("IOException from makeSheet()!");
		}
        
        int cols=a.getColCount();
      
        assertTrue("Failed to read many columns because static variable was used", dim == a.getColCount());

        // do the same for many rows
        try {
            FileWriter fstream = new FileWriter(file.getAbsolutePath());
            BufferedWriter out = new BufferedWriter(fstream);
            for (long i = 0; i < dim; i++) {
                // not positive empty lines are parsed as a row, 
                // so write some content
                out.write("x");
                out.newLine();
            }
            out.close();
        } catch (Exception e) {
            fail("file write exception");
        }
        
        a = new ReadFileWriteToConsole();
        try {
			a.makeSheet();
		} catch (IOException e) {
			fail("IOException from makeSheet()!");
		}
        assertTrue("Failed to read many rows because static variable was used", dim == a.getRowCount());
		
		return true;
	}
	
	
	
}
