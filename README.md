1. Avg SS
  * Should return error when there is no matching parenthesis
  * Should return error when it's not a cell reference
  - Derived features / conditions 
    * Same cell is ok (i.e A1:A1)
    * AVG(A1:B1) acts as a single token meaning AVG(A1:B1) + B1 + AVG(A1:A1) is possible

2. Exp Have Numbers
  * Pretty easy, create a method that checks if its double, then return token
  * Using try/catch is pretty importanttt duuude

3. Flexible Read Display
  * Best performance to memory ratio is achieved using a normal Array
  * Calculate the rowCount and colCount first and then instantiate the Array

4. Number as Expressions
  * Use try/catch when using Double.parseDouble()
  * Use evaluateToken when a non symbol token is found (ie cell or value)
  * Check if evaluateToken() returns null
  * Create isDouble with try/catch when parsing String to Double

5. Open Save SS
  * Create local variable lastRowIndex and lastColIndex
  * Compute fileDemnsions (run a compute function) on Save / Save as
  * Loop through all the cells on file open, if null write cellText to = ""
  * On computeFileDimensions(), remember to always reset lastRowIndex to 0 and lastColIndex to 0;

6. Parenthesis ss
  * Use shunting yard
  * Always check for null when using evaluateToken
  * Check for stack size before poppin'

7. Simple Read Display
  * Use MAXCOL and MAXROW for loops instead of sheet length.

8. Stats Greatest value
  * Use for loops instead of while loops (weird bug)
  * Return greatestValue as Integer (not as a Double)

9. Stat Median 
  * Use Arrays.sort() instead of implementing your own sort
  * Math.ceil to round off median to +infinity
  * Convert double to int first, then convert to String
  
10. Sum SS
  * Store lowerCell, higherCell and sameCell for easier looping
  * Always check if evaluateToken result is null - if it does, return null
  * Check if there is a parenthesis matches

11. Whitespace
  * String.replaceAll()

12. Write HTML
  * Use readwritefile as reference ;)
  * Maybe use CsvWriter instead of BufferedWriter (?)