import java.util.Arrays;

public class Stats {
    private int cols;
    private int rows;
    private String[][] data;

    public Stats(int rows, int cols, String[][] data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
        computeMedians();
    }

    private void computeMedians() {
        // TODO Auto-generated method stub
        for (int j = 2; j < cols; j++) {
            String[] values = new String[rows - 1];
            for (int i = 1; i < rows; i++) {
                values[i - 1] = data[i][j];
            }
            data[rows][j] = computeMedian(values);
        }
        data[rows][0] = "";
        data[rows][1] = "Median";
    }

    private String computeMedian(String[] values) {
        // TODO Auto-generated method stub
        try {
            Double[] newValues = new Double[values.length];
            
            for (int i = 0; i < values.length; i++) {
                newValues[i] = Double.parseDouble(values[i]);
            }
            
            Arrays.sort(newValues);
            
            if (newValues.length % 2 == 0) {
                Double m1 = newValues[(newValues.length / 2) - 1];
                Double m2 = newValues[newValues.length / 2];
                Double median = (m1 + m2) / 2;
                return "" + (int) Math.ceil(median);
            } else {
                Double median = newValues[(int) Math.ceil(newValues.length / 2)];
                return "" + median;
            }
        } catch (Exception e) {
            return null;
        }   
    }
}
