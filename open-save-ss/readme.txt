readme.txt -- for open-save-ss

THE TASK

Modify FileIO.java to implement Open, Save, and Save As ... commands
for SpreadSheet.java. Note that SpreadSheet.java and FileIO.java
already implement Open, Save, and Save As ... menu items, but the
commands do not do anything. See the methods in FileIO.java.

Spreadsheets should be opened and saved using the javacsv project (see
CsvReader.java and CsvWriter.java). If a cell has both a formula and a
value, you should save the formula, not the value.

The saved file should have only as many rows and columns as
necessary. Therefore, the last row in the file should have a
non-empty column. You may make all rows the same size (in which
case the last column of at least one row will be non-empty), or
you may make rows variable-sized (in which case every row will
either be completely empty or will end in a non-empty column).

The default value for all cells not saved is the empty string.

Video demonstrations of a solution for this task are available.
    1. During the exam: as open-save-solution-demo.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/open-save-solution-demo-transcoded.mp4
