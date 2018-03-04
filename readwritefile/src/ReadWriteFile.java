import java.io.*;

/** 
Read a file and write the data to another file.  
*/
public class ReadWriteFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    String inFileName = args[0];
	    String outFileName = args[1];
	    BufferedReader input;
	    BufferedWriter output;
	    
	    try {
	        //use buffering, reading one line at a time
	        input =  new BufferedReader(new FileReader(inFileName));
	    } catch (FileNotFoundException ex) {
	    	System.out.println("Could not open " + inFileName);
	    	ex.printStackTrace();
	    	return;
	    }
	    try {
	        output = new BufferedWriter(new FileWriter(outFileName));
	    } catch (IOException ex) {
	    	System.out.println("File IO Error opening " + outFileName);
	    	ex.printStackTrace();
	    	return;
	    }	    
	    try {
         	int c;
	        while ((c = input.read()) != -1) {
	        	output.write(c);
	        }
	        System.out.println("Copied " + inFileName + " to " + outFileName);
	    } catch (IOException ex) {
	    	System.out.println("File IO Error encountered.");
	    	ex.printStackTrace();
	    }
	    try {
	        input.close();
	    } catch (IOException ex) {
	    	// assume an earlier error caused this and was already reported
	    }
	    try {
	       	output.close();
	    } catch (IOException ex) {
	    	// assume an earlier error caused this and was already reported
	    }
	}

}
