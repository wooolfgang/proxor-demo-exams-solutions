import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

import com.csvreader.*;

public class WriteHtml {
    public static final String inFileName = "input.csv";
    public static final String outFileName = "output.html";

    public static void main(String[] args) {
        // ... insert code here ...
    	//  Do not change the signature of this method.
        
        CsvReader input;
        BufferedWriter output;
        
        try {
            //use buffering, reading one line at a time
            input =  new CsvReader(inFileName);
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
            output.write("<html>\n" + 
                    "  <head>\n" + 
                    "    <title>" + inFileName + "</title>\n" + 
                    "  </head>\n" + 
                    "  <body>\n" + 
                    "    <table style=\" text-align: left;\" border=\"1\"\n" + 
                    "      cellpadding=\"2\" cellspacing=\"2\">\n" + 
                    "      <tbody>");
            
            while (input.readRecord()) {
                output.write("<tr>");
                for (int i = 0; i < input.getColumnCount(); i++) {
                    output.write("<td>");
                    output.write(EscapeHTML.stringToHTMLString(input.get(i)));
                    output.write("</td>");
                }
                output.write("</tr>");
            }
            
            output.write("</tbody>\n" + 
                    "    </table>\n" + 
                    "    <br>\n" + 
                    "  </body>\n" + 
                    "</html>");
            
            System.out.println("Copied " + inFileName + " to " + outFileName);
        } catch (IOException ex) {
            System.out.println("File IO Error encountered.");
            ex.printStackTrace();
        }
        try {
            input.close();
        } catch (Exception ex) {
            // assume an earlier error caused this and was already reported
        }
        try {
            output.close();
        } catch (IOException ex) {
            // assume an earlier error caused this and was already reported
        }
    }
}
