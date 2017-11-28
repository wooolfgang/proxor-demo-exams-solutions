import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;

public class ReadFileWriteToConsole {
	
	private static final int MAXROW = 3;
	private static final int MAXCOL = 3;
    private static final String inFile = "in.csv";
	   
    public String[][] sheet = new String[MAXROW][MAXCOL];
	   
    public static void main(String args[]) throws IOException {
    	// create a ReadWriteToConsole object
    	//  Do not change the signature of this method.
    	// ... insert code here ...
    	// invoke readSheet()
    	// ... insert code here ...
    	// invoke writeSheet()
    	// ... insert code here ...
        ReadFileWriteToConsole rf = new ReadFileWriteToConsole();
        rf.readSheet();
        rf.writeSheet();
    }	
	   
    public void readSheet() throws IOException {
    	// ... insert code here ...
    	//  Do not change the signature of this method.
        try {
            CsvReader r = new CsvReader(inFile);
            int rowI = 0;
            while (r.readRecord() && rowI < MAXROW) {
                for (int colI = 0; colI < MAXCOL; colI++) {
                    sheet[rowI][colI] = r.get(colI);
                }
                rowI++;
            }
            r.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	   
	public void writeSheet(){
		// ... insert code here ...
    	//  Do not change the signature of this method.
	    for (int i = 0; i < MAXROW; i++) {
	        for (int j = 0; j < MAXCOL; j++) {
	            System.out.print("[" + sheet[i][j] + "]");
	        }
	        System.out.println();
	    }
	}
}
