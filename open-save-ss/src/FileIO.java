// FileIOBase.java -- template for FileIO.java.
//
// this file should be modified to implement
// open, save, and save as... commands for SpreadSheet.java
// 
// Do not modify the signatures of these methods.
import com.csvreader.*;


import java.io.File;
import java.io.IOException;

public class FileIO {
    public static boolean open(SpreadSheet ss, File file) {
        try {
            CsvReader r = new CsvReader(file.getAbsolutePath());
            int rowI = 0;
            
            while (r.readRecord()) {
                for (int colI = 0; colI < r.getColumnCount(); colI++) {
                    if (r.get(colI) != "" || r.get(colI) != null) {
                        ss.setCell(rowI, colI, r.get(colI));
                    }
                }
                rowI++;
            }
            r.close();
            
            for (int i = rowI; i < ss.maxRows; i++) {
                for (int j = 0; j < ss.maxCols; j++) {
                    ss.setCell(i, j, "");
                }
            }
            
            ss.evaluate();
            return true;
        } catch (IOException e) {
        }
        return false;
    }
	
    public static boolean saveAs(SpreadSheet ss, File file) {
        	System.out.println("SaveAs " + file);
        	try {
        	    CsvWriter w = new CsvWriter(file.getAbsolutePath());
        	    for (int i = 0; i <= getLastRowIndex(ss); i++) {
        	        for (int j = 0; j < ss.maxCols; j++) {
        	            if (ss.getCellFormula(i, j).length() != 0 || ss.getCellFormula(i, j) != "") {
        	                w.write(ss.getCellFormula(i, j));
        	            } else if (ss.getCellText(i, j).length() != 0 || ss.getCellText(i, j) != "") {
        	                w.write(ss.getCellText(i, j));
        	            } else {
        	                w.write("");
        	            }
        	        }
        	        w.endRecord();
        	    }
        	    w.close();
        	    return true;
        	} catch (IOException e) {
        	}
        	return false;
    }
    
    // returns the last row index that contains a value/formulas
    private static int getLastRowIndex(SpreadSheet ss) {
        int rowIndex = ss.maxRows - 1;
        boolean endLoop = false;
 
        for (int i = ss.maxRows - 1; i >= 0; i--) {
            if (endLoop) break;
            for (int j = ss.maxCols - 1; j >= 0; j--) {
                if (ss.getCellFormula(i, j).length() != 0 || ss.getCellText(i, j).length() != 0 ||
                    ss.getCellFormula(i, j) != null || ss.getCellText(i, j) != null) {
                    rowIndex = i;
                    endLoop = true;
                    break;
                }
            }
        }
        return rowIndex;
    }
}
