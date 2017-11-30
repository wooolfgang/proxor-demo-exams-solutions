
public class Stats {
    public Stats(int rows, int cols, String[][] data) {
        computeMedians(rows, cols, data);
    }
    
    public void computeMedians(int rows, int cols, String[][] data) {
        String values[] = new String[rows - 1];
        int x;
        for (int i = 2; i < cols; i++) {
            x = 0;
            for (int j = 1; j < rows; j++) {
                values[x] = data[j][i];
                x++;
            }
            data[rows][i] = getMedian(sortByAscending(values));
        }
        data[rows][0] = "";
        data[rows][1] = "Median";
    }
    
    private String getMedian(String[] values) {
        if (values.length % 2 == 0) {
            String a = values[(values.length / 2) - 1];
            String b = values[values.length / 2];
            double average = (Double.parseDouble(a) + Double.parseDouble(b)) / 2;
            return String.valueOf((int) Math.ceil(average));
        }
        return values[((values.length - 1) / 2) + 1];
    }
    
    private String[] sortByAscending(String[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (Double.parseDouble(values[i]) < Double.parseDouble(values[j])) {
                    String temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                }
            }
        }
        return values;
    }
}
