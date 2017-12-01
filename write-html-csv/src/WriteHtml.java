import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Vector;
import com.csvreader.*;

public class WriteHtml {
    public static final String inFileName = "input.csv";
    public static final String outFileName = "output.html";

    public static void main(String[] args) {
        // ... insert code here ...
    	//  Do not change the signature of this method.
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        try {
            reader = new BufferedReader(new FileReader(inFileName));    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            writer = new BufferedWriter(new FileWriter(outFileName));    
            int val;
            writer.write("<html>\n" + 
                    "  <head>\n" + 
                    "    <title>test.csv</title>\n" + 
                    "  </head>\n" + 
                    "  <body>\n" + 
                    "    <table style=\" text-align: left;\" border=\"1\"\n" + 
                    "      cellpadding=\"2\" cellspacing=\"2\">\n" + 
                    "      <tbody>");
            writer.write("<tr>");
            
            while ((val = reader.read()) != -1) {
                if ((char) val == ' ') continue;
                if ((char) val == ',') continue;
                if (val == '\n') {
                    writer.write("</tr>");
                    val = reader.read();
                    if (val == - 1) break;
                    writer.write("<tr>");
                }
                writer.write("<td>");
                writer.write(EscapeHTML.stringToHTMLString(String.valueOf((char) val)));
                writer.write("</td>");      
            }
            
            writer.write("      </tbody>\n" + 
                    "    </table>\n" + 
                    "    <br>\n" + 
                    "  </body>\n" + 
                    "</html>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
