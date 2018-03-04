import java.util.Arrays;

public class Stats {
    private int rows;
    private int cols;
    private String[][] data;
    
    public Stats(int rows, int cols, String[][] data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
        greatestValue();
    }
    
    public void greatestValue() {
        for (int j = 2; j < cols; j++) {
            String[] values = new String[rows - 1];
            for (int i = 1; i < rows; i++) {
                values[i - 1] = data[i][j];
            }
            data[rows][j] = computeGreatestVal(values);
        }
        data[rows][0] = "Greatest";
        data[rows][1] = "Value";
    }

    private String computeGreatestVal(String[] values) {
        // TODO Auto-generated method stub
        try {
            // Initially assign first value as greatestVal
            Double greatestVal = Double.parseDouble(values[0]);
            
            for (int i = 1; i < values.length; i++) {
                if (Double.parseDouble(values[i]) > greatestVal) {
                    greatestVal = Double.parseDouble(values[i]);
                }
            }
            return "" + greatestVal.intValue(); 
        } catch (Exception e) {
            return null;
        }
    }
    
    public void writeData(int rows, int cols, String[][] data) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println("");
        }
    }
}
