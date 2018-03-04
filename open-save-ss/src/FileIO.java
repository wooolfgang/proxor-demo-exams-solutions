// FileIOBase.java -- template for FileIO.java.
//
// this file should be modified to implement
// open, save, and save as... commands for SpreadSheet.java
// 
// Do not modify the signatures of these methods.


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvWriter;
import com.csvreader.CsvReader;

public class FileIO {
    private static int lastRowIndex;
    private static int lastColIndex;
    
    public static boolean open(SpreadSheet ss, File file) {
    	    System.out.println("Open " + file);
    	    try {
    	        CsvReader reader = new CsvReader(file.getAbsolutePath());
    	        
    	        for (int i = 0; i < ss.maxRows; i++) {
    	            reader.readRecord();
    	            for (int j = 0; j < ss.maxCols; j++) { 
    	                if (reader.get(j) != null || reader.get(j) != "") {
    	                    ss.setCell(i, j, reader.get(j));
    	                } else {
    	                    ss.setCell(i, j, "");
    	                }
    	            }
    	        }
    	        
    	        ss.evaluate();
    	        return true;
    	    } catch (Exception e) {
                // TODO Auto-generated catch block
    	        e.printStackTrace();
    	        return false;
    	    }
    }
	
    public static boolean saveAs(SpreadSheet ss, File file) {
        System.out.println("SaveAs " + file);
        
        computeFileDimensions(ss);
        CsvWriter writer = new CsvWriter(file.getAbsolutePath());
        
        try {
            for (int i = 0; i <= lastRowIndex; i++) {
                for (int j = 0; j <= lastColIndex; j++) {
                    if (ss.getCellFormula(i,j).length() != 0) {
                        writer.write(ss.getCellFormula(i,j));
                    } else if (ss.getCellText(i, j).length() != 0) {
                        writer.write(ss.getCellText(i, j));
                    } else {
                        writer.write("");
                    }
                }
                writer.endRecord();
            }
            
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }     
    }
    
    private static void computeFileDimensions(SpreadSheet ss) {
        lastRowIndex = 0;
        lastColIndex = 0;
        
        for (int i = 0; i < ss.maxRows; i++) {
            for (int j = 0; j < ss.maxCols; j++) {
                if (ss.getCellFormula(i, j).length() != 0 || ss.getCellText(i, j).length() != 0) {
                    if (lastRowIndex < i) lastRowIndex = i;
                    if (lastColIndex < j) lastColIndex = j;
                }
            }
        }
    }
    
}
