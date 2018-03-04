// StatsCsv.java -- add greatest values to spreadsheet
//
// This is NOT a working program. This is a "skeleton" that
// should be modified and extended to meet the specifications.

import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


public class StatsCsv {
    
    private static final int MAXROW = 16;
    private static final int MAXCOL = 16;
    private int rowsUsed = 0;
    private int colsUsed = 0;
    private static final String inFile = "Data01.csv";
    private static final String outFile = "Data02.csv"; 
   
    public String[][] sheet = new String[MAXROW][MAXCOL];
    
    public static void main(String[] args) throws IOException {

        StatsCsv a = new StatsCsv();
        a.readSheet();
        // a.getRows() is the row count before adding medians
        Stats s = new Stats(a.getRows(), a.getCols(), a.getData());
        a.setRows(a.getRows() + 1); // because we added a row of medians
        a.writeSheet();
    }
    
    public void writeSheet() throws IOException {
        // 
        // to be completed
        //  Do not change the signature of this method.
        CsvWriter writer = new CsvWriter(outFile);
        
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                writer.write(sheet[i][j]);
            }
            writer.endRecord();
        }
        
        writer.close();
    }

    public void readSheet( ) throws IOException {
        // 
        // to be completed
        //  Do not change the signature of this method.
        CsvReader reader = new CsvReader(inFile);
        
        int row = 0;
        int col = 0;
        
        while (reader.readRecord()) {
            for (col = 0; col < reader.getColumnCount(); col++) {
                sheet[row][col] = reader.get(col);
            }
            row++;
        }
        
        setRows(row);
        setCols(col);
        reader.close();
    }

    public int getRows(){
    	    return rowsUsed;
    }
    
    public int setRows(int r) {
        rowsUsed = r;
        return rowsUsed;
    }

    public int getCols() {
        return colsUsed;
    }
    
    public int setCols(int c) {
        colsUsed = c;
        return colsUsed;
    }
    
    public String[][] getData() {
    	    return sheet;
    }
}
