
public class Stats {
    public Stats(int rows, int cols, String[][] data) {
        greatestValue(rows, cols, data);
    }
    
    public void writeData(int rows, int cols, String[][] data) {
        for (int x = 0; x < rows; x++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[x][j]);
            }
            System.out.println();
        }
    }
    
    private void greatestValue(int rows, int cols, String[][] data) {
        String [] values;
        int i;
        
        for (int colI = 2; colI < cols; colI++) {
            values = new String[rows - 1];
            i = 0;
            for (int rowI = 1; rowI < rows; rowI++) {
                values[i] = data[rowI][colI];
                i++;
            }
            data[rows][colI] = findGreatestValue(values);
        }
        
        data[rows][0] = "Greatest";
        data[rows][1] = "Value";
    }
    
    private String findGreatestValue (String[] values) {
        String greatestValue = values[0];
        try {
            for (int i = 0; i < values.length - 1; i++) {
                if (Double.parseDouble(greatestValue) < Double.parseDouble(values[i + 1])) {
                    greatestValue = values[i + 1];
                }
            }
            return greatestValue;
        } catch (Exception e) {   
        }
        return null;
    }
}
