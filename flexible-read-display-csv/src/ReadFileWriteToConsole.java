import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.csvreader.CsvReader;


public class ReadFileWriteToConsole {

    private static final String inFile = "in.csv";
    private CsvReader c;

    public static void main(String args[]) throws IOException {
    	// (add code to implement the following)
    	// create a ReadFileWriteToConsole object
    	// call method below to read the data from inFile
    	// call method below to write the data to the console
    	// Do not change the signature of this method.
    		ReadFileWriteToConsole rf = new ReadFileWriteToConsole();
    		rf.makeSheet();
    		rf.writeSheet();
    }
	
    public String getCell(int row, int col) {
	// return the value of the spreadsheet at the given row and column
	// Do not change the signature of this method.
    		if (row >= getRowCount() || col >= getColCount()) {
    			return null;
    		}
    		try {
    			int rowCount = 0;
    			while (rowCount < row) {
        			c.readRecord();
        			rowCount++;
        		}
    			return c.get(col);
    		} catch (IOException e) {
    			e.printStackTrace();
    			return null;
    		}
    }
	
    public int getRowCount() {
	// return the number of rows in the spreadsheet
	// Do not change the signature of this method.
    		try {
    			makeSheet();
    			int rowCount = 0;
    			while (c.readRecord()) {
    				rowCount++;
    			}
    			return rowCount;
    		} catch (IOException e) {
    			e.printStackTrace();
    			return 0;
    		}
    }
    
    public int getColCount() {
	// return the number of columns in row
	// Do not change the signature of this method.
    		try {
    			makeSheet();
    			c.readRecord();
    			return c.getColumnCount();
    		} catch (IOException e) {
    			e.printStackTrace();
    			return 0;
    		}
    }
	  
    public void makeSheet() throws IOException {
	// read the data from inFile
	// Do not change the signature of this method.
    		c = new CsvReader(inFile);
    }	
	   
    public void writeSheet() {
	// format data to console
	// Do not change the signature of this method.
    		for (int i = 0; i < getRowCount(); i++) {
    			for (int j = 0; j < getColCount(); j++) {
    				System.out.print("[" + getCell(i,j) + "]");
    			}
    			System.out.println();
    		}
    }
}
