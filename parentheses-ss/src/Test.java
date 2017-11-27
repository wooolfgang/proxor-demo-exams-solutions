
public class Test {
    public static void main (String[] args) {
        String formula = "23)";
        if (formula.endsWith("3)")) {
            formula = formula.substring(0, formula.length() - 1);
        }
        System.out.println(formula);
    }
}
