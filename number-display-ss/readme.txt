readme.txt -- for number-display-ss

THE TASK

The spreadsheet displays numbers at full resolution, but this means a
number with more than 8 or 9 digits is not fully visible in the text
box. Modify the spreadsheet program so that numbers are displayed in cells
with at most 8 characters. Use scientific notation and rounding as necessary.
Hint: The 'g' conversion in the Java Formatter class might be helpful.

Even if rounding is needed to display a number in a cell, all
computation should use the full precision of every formula and value.
Since the displayed values will be truncated, you will need a new
instance variable in the Cell class to hold the full-precision value
of the cell in case a formula references it. When a cell holds a
formula, it is displayed in the formula bar. If the cell does not hold
a formula, the full-precision value of the cell should be displayed
and editable in the formula bar, even if the spreadsheet table shows a
truncated version of the value.

If a number is entered with 8 or fewer characters, it should not be
reformatted; for example, if the user enters 123, the spreadsheet should not
change the display to 123.0000.

Spreadsheet cells can also display text that cannot be parsed as a number.
Nonnumber data should appear in cells without modification, even though
not all characters will be visible at once.

Video demonstrations of a solution for this task are available.
    1. During the exam: as number-display-solution-demo.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/number-display-solution-demo-transcoded.mp4
