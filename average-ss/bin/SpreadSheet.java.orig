import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.StringTokenizer;

/* SpreadSheet implements an array of cells within a graphical
 * user interface. Each cell is represented by a JTextField that
 * implements the visual aspect of the cell and holds the cell
 * value (a string). Each cell also has an associated Cell object,
 * which contains a formula (string) and some additional information.
 * A SpreadSheet also has a text input area at the top for editing
 * formulas and a status output line at the bottom.
 * 
 * Some general notes about the SpreadSheet and its user interface:
 * 
 * The spreadsheet size is fixed, and the number of columns must be
 * less than or equal to 26 because columns are named A-Z.
 * 
 * Formulas begin with "=" and consist of references to cells 
 * separated by operators (+, -, /, or *). A cell reference is
 * an upper-case letter (A-Z) followed by a decimal integer.
 * Spaces are not allowed in formulas.
 */
public class SpreadSheet extends JFrame {
    private static final long serialVersionUID = 1L;
    final int maxCols = 10; // the size of the spreadsheet is fixed
    	// maxCols <= 26 because columns are named by letters A-Z
    final int maxRows = 10;
    // GridLayout is used to organize cells into a matrix
    GridLayout spreadSheetLayout = new GridLayout(0, maxCols + 1);
    // setText() raises an action event that we want to ignore
    //  (the "good" action events are those raised by human interaction)
    //  Use a flag to indicate when setText() is due to evaluating 
    //  formulas:
    private Boolean ignoreTextFieldAction = false;
    // Data structures: 
    // There are two "spreadsheet" matrices:
    // The first, cells, is an array of objects containing cell data 
    //   including formulas
    // The second, cellsTF, is an array of JTextFields which are what
    //   the user types into and also what is displayed (not formulas)
    // An alternate approach would be to subclass JTextField and add
    //   an instance variable to contain what is now in cells
    // A possible drawback of the current approach is that when the
    //   user types into a JTextField, we have to search to find the
    //   coordinates in the matrix in order to locate the corresponding
    //   data in cells[][].
    private class Cell {
        String formula; // the formula from which the value is computed
        Boolean valid; // cellsTF has correct current value
        Boolean bottom; // value is undefined
        
        Cell() {
            init();
        }
        
        public void init() {
        	formula = "";
        	valid = false;
        	bottom = false;
        }
    }
    private Cell cells[][] = new Cell[maxRows][maxCols];
    private JTextField cellsTF[][] = new JTextField[maxRows][maxCols];
    
    // The formula is editable text at the top of the spreadsheet
    private JTextField formula;
    // what field is in the "formula edit bar" at the top of the spreadsheet
    private int editRow = 0;
    private int editCol = 0;

    
    // look up a formula
    public String getCellFormula(int row, int col) {
    	return cells[row][col].formula;
    }
    
    // set a cell to a value or a formula (during evaluate(), the
    // field will be evaluated and copied to the displayed text field.
	public void setCell(int row, int col, String field) {
		cells[row][col].formula = field;
	}
    
    // look up text for a cell
    public String getCellText(int row, int col) {
    	return cellsTF[row][col].getText();
    }
    

    // evaluate a token, which may be a reference to another cell or
    // simply a string. To avoid circular dependencies, the depth
    // parameter keeps track of the length of the dependency chain.
    // Returns a string if value is valid, otherwise null.
    public String evaluateToken(String tok, int depth) {
        if (tok.length() >= 2 && tok.charAt(0) >= 'A' && 
            tok.charAt(0) < (char) ('A' + maxCols)) {
            int col = tok.charAt(0) - 'A';
            int row = Integer.parseInt(tok.substring(1)) - 1;
            if (row >= 0 && row < maxRows) {
                if (!cells[row][col].valid) {
                    evaluate(row, col, depth + 1);
                }
                if (cells[row][col].bottom) return null;
                return cellsTF[row][col].getText();
            }
        }
        return null;
    }

    /* Functions to implement formula operations -- input/output are strings
     */
    protected String add(String x, String y) throws NumberFormatException {
        return Double.toString(Double.parseDouble(x.trim()) + 
                               Double.parseDouble(y.trim()));
    }

    protected String multiply(String x, String y) throws NumberFormatException {
        return Double.toString(Double.parseDouble(x.trim()) * 
                               Double.parseDouble(y.trim()));
    }

    protected String divide(String x, String y) throws NumberFormatException {
        return Double.toString(Double.parseDouble(x.trim()) / 
                               Double.parseDouble(y.trim()));
    }

    protected String subtract(String x, String y) throws NumberFormatException {
        return Double.toString(Double.parseDouble(x.trim()) - 
                               Double.parseDouble(y.trim()));
    }
    
    // parse and evaluate formula after it has been broken into tokens
    // formulas are tokens containing either
    // 1. references to cells of the form Lnn, where
    //    L is an upper-case letter and nn is one or two decimal digits, or
    // 2. strings (including decimal integers and floats)
    // formulas can have any number of tokens separated by operators, which
    //    can be *, +, /, or -. Operations are performed left to right, with
    //    no regard for operator precedence.
    public String parseFormula(StringTokenizer tokens, int depth) 
            throws NumberFormatException {
        if (tokens.hasMoreTokens()) {
            String tok = tokens.nextToken();
            tok = evaluateToken(tok, depth);
            if (tok == null) return null;
            while (tokens.hasMoreTokens()) {
                String tok2 = tokens.nextToken();
                if (tok2 == null) return null;
                if (!tokens.hasMoreTokens()) return null;
                String tok3 = tokens.nextToken();
                tok3 = evaluateToken(tok3, depth);
                if (tok3 == null) return null;
                if (tok2.equals("+")) {
                    tok = add(tok, tok3);
                } else if (tok2.equals("*")) {
                	tok = multiply(tok, tok3);
                } else if (tok2.equals("/")) {
                	tok = divide(tok, tok3);
                } else if (tok2.equals("-")) {
                	tok = subtract(tok, tok3);
                } else return null; // invalid operator
            }
            return tok;
        }
        return null;
    }
    
    // evaluate a given cell. Cells can depend on other cells. To prevent
    // infinite recursion in the case of cycles, depth keeps track of the
    // length of the dependency chain. The longest chain involves all cells
    // so if depth exceeds this amount, or any other error occurs, the
    // value is marked as undefined.
    public void evaluate(int r, int c, int depth) {
        String formula = cells[r][c].formula;
        if (formula.length() > 0 && formula.charAt(0) == '=') {
            try {
                if (depth <= maxRows * maxCols) {
                    StringTokenizer tokens = 
                            new StringTokenizer(formula, "=+*/-", true);
                    if (tokens.hasMoreTokens() && 
                        (tokens.nextToken().equals("="))) {
                        String val = parseFormula(tokens, depth);
                        if (val != null) {
                            cellsTF[r][c].setText(val);
                            cells[r][c].valid = true;
                            return;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                // fall through and handle error
            }
            cells[r][c].bottom = true;
            cells[r][c].valid = true;
            cellsTF[r][c].setText("!!!");
        } else { // in case edits were made to cells, need to copy to cellsTF
            cellsTF[r][c].setText(formula);
        }
    }
    
    
    // evaluate every cell in the spreadsheet
    public void evaluate() {
        // do not copy text fields generated from formulas
        // back to the formula strings
        ignoreTextFieldAction = true;
        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                cells[r][c].valid = false;
                cells[r][c].bottom = false;
            }
        }
        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                if (!cells[r][c].valid) {
                    evaluate(r, c, 0);
                }
            }
        }
        ignoreTextFieldAction = false;
    }
    
    // find the coordinates of a cell that has changed
    public Point findCellTF(JTextField cell) {
        // find the cell that was changed
        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                if (cell == cellsTF[r][c]) {
                    return new Point(r, c);
                }
            }
        }
        return null;
    }
    
    
    // called when user types enter (action event) or 
    // changes focus (focus event)
    public void cellChanged(JTextField cell) {
        Point loc = findCellTF(cell);
        int r = loc.x;
        int c = loc.y;
        String f = cells[r][c].formula;
        // update the formula if it is just a value (non-'=' prefix)
        if (f.length() <= 0 || f.charAt(0) != '=') {
            cells[r][c].formula = cell.getText();
            if (!ignoreTextFieldAction) {
                formula.setText(cells[r][c].formula);
            }
        }
        evaluate();
    }

    // when cell changes, use an ActionListener to invoke cellChanged
    private class CellActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //if (ignoreTextFieldAction) 
            //    return;
            cellChanged((JTextField) e.getSource());
        }
    }
    
    private ActionListener cellActionListener = new CellActionListener();


    // when focus changes, use a FocusListener to invoke cellChanged
    private class CellFocusListener implements FocusListener {
        public void focusGained(FocusEvent e) {
            Point loc = findCellTF((JTextField) e.getSource());
            editRow = loc.x;
            editCol = loc.y;
            formula.setText(cells[editRow][editCol].formula);
        }
        public void focusLost(FocusEvent e) {
            cellChanged((JTextField) e.getSource());
        }
    }

    private FocusListener cellFocusListener = new CellFocusListener();

    
    // when the user types Enter into formula text, set the formula
    private class FormulaActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cells[editRow][editCol].formula = formula.getText();
            evaluate();
        }
    }

    private ActionListener formulaActionListener = new FormulaActionListener();

    public SpreadSheet(String name) {
        super(name);
        setResizable(false);
    }


    // build the graphical interface
    public void addComponentsToPane(final Container pane) {
        final JPanel spreadSheetGrid = new JPanel();
        spreadSheetGrid.setLayout(spreadSheetLayout);
        
        //Set up components preferred size
        JTextField tf = new JTextField("example text");
        Dimension cellSize = tf.getPreferredSize();
        spreadSheetGrid.setPreferredSize(
                new Dimension((int)(cellSize.getWidth() * maxCols),
                              (int)(cellSize.getHeight() * maxRows)));
        
        // First Row/Col is unused
        spreadSheetGrid.add(new JTextArea(""));
        
        // Add column headings
        for (int c = 0; c < maxCols; c++) {
            JTextArea label = 
                    new JTextArea(Character.toString((char) ('A' + c)));
            label.setEditable(false);
            spreadSheetGrid.add(label);
                    
        }
        // Add text fields as cells
        for (int r = 0; r < maxRows; r++) {
            // use first column as row labels
            JTextArea label = new JTextArea(Integer.toString(r + 1));
            label.setEditable(false);
            spreadSheetGrid.add(label);
            // remaining columns are for cells
            for (int c = 0; c < maxCols; c++) {
                JTextField cell = new JTextField("");
                cells[r][c] = new Cell();
                cell.addActionListener(cellActionListener);
                cell.addFocusListener(cellFocusListener);
                cellsTF[r][c] = cell;
                spreadSheetGrid.add(cell);
            }
        }
        // add text field at top for equation entry
        formula = new JTextField("");
        formula.addActionListener(formulaActionListener);
        pane.add(formula, BorderLayout.NORTH);
        // add the grid in the middle
        pane.add(spreadSheetGrid, BorderLayout.CENTER);
        // add text field at the bottom for messages
        pane.add(new JTextArea(), BorderLayout.SOUTH);
    }

    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        SpreadSheet frame = new SpreadSheet("Spreadsheet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
