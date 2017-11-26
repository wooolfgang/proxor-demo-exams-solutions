readme.txt -- for flexible-read-display-csv

THE TASK
Create a class named "ReadFileWriteToConsole" that imports
com.csvreader.CsvReader (found in the "javacsv" project
or directory) and uses CsvReader to read a csv file into a
flexible data structure and then write the contents out to
Console with square brackets enclosing each value.

CsvReader.java is in the javacsv project in this workspace.

The input file name is "in.csv" and should be read from the
current working directory.

The ReadFileWriteToConsole maintains a private data structure
that contains the csv file data. The data structure must use
very little memory when there are few cells, and the data
structure must be able to handle a reasonably large number
(tens of thousands) of cells. Clients of the class can
access the data structure by calling the following methods:
    getCell(int row, int col) returns a String at the given
        location
    getRowCount() returns the number of rows
    getColCount() returns the number of columns

You may assume that all rows of the input files have the same
number of columns.

SAMPLE INPUT FILE
1,2,3,4,5,1
Hello World,7,foo,,2,
,,perfect,3,,
,,4,,= A1 + (B3 /16),
,5,,,,
6,,,,,

SAMPLE OUTPUT TO CONSOLE
[1][2][3][4][5][1]
[Hello World][7][foo][][2][]
[][][perfect][3][][]
[][][4][][= A1 + (B3 /16)][]
[][5][][][][]
[6][][][][][]

A skeleton of the program is provided. You should add code
to implement the behavior and interface described above.
Do not rename methods.

Video demonstrations of a solution for this task are available.
    1. During the exam: as flexible-read-display-csv.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/flexible-read-display-csv-transcoded.mp4
