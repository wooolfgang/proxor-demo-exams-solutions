import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class StatsCsvTest extends TestCase {
	
	public final void testMain() {

        try {
            StatsCsv a = new StatsCsv();
            a.readSheet();
            
			// read in data set and compute medians			
			// test some of the fields
			assertTrue(
					"Failed to read data to sheet",
					a.sheet[0][0].equals("1st Name") && a.sheet[0][1].equals("2nd Name")
							&& Double.parseDouble(a.sheet[3][3]) == 0.0
							&& a.sheet[4][1].equals("Reagan"));
            // a.getRows() is the row count before adding medians
            Stats s = new Stats(a.getRows(), a.getCols(), a.getData());
            a.setRows(a.getRows() + 1); // because we added a row of medians
			
			assertTrue("Failed to compute median", Double.parseDouble(a.sheet[5][5]) == 7.0);
			
            a.writeSheet();
            
            // read Data02.csv
            String inFileName = "Data02.csv";
            BufferedReader input;
    	    try {
    	        // use buffering, reading one line at a time
    	        input =  new BufferedReader(new FileReader(inFileName));
    	    } catch (FileNotFoundException ex) {
    	    	System.out.println("Could not open " + inFileName);
    	    	fail("Failed to open Data02.csv");
    	    	return;
    	    }
    	    ArrayList<String> data = new ArrayList<String>();
    	    ArrayList<String> good = new ArrayList<String>();
    	    good.add("1st Name,2nd Name,Task 1,Task 2,Task 3,Task 4");
    	    good.add("Andrew,Andrews,10,0,0,0");
			good.add("MadDog,Miller,10,5,8,7");
			good.add("Kevin,Olson,10,0,1,7");
			good.add("Ronald,Reagan,10,5,0,7");
			good.add("\"\",Median,10,3,1,7");;
    	    try {
             	String inp;
    	        while ((inp = input.readLine()) != null) {
    	        	data.add(inp);
    	        }
    	    } catch (IOException e) {
    	    	e.printStackTrace();
    	    } finally {
    	    	try {
    	    		if (input != null) input.close();
    	    	} catch (IOException e) {
    	    		e.printStackTrace();
    	    	}
    	    }
    	    for (int i = 0; i < 6; i++) {
    	    	System.out.println(data.get(i) + "===" + good.get(i));
    	    	assertTrue("Incorrect output file", data.get(i).equals(good.get(i)));
    	    }

		} catch (IOException e) {
			fail("IOException encountered establishing in.csv");
		}
	}
}
