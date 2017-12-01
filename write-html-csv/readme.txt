readme.txt -- for write-html-csv

THE TASK

Write a program to read a csv file and generate an HTML table in a
web page.

The program should run from the commandline, taking no arguments.
The input file name is "input.csv", and the output file name is
"output.html". Read the input file from the current working
directory using the default settings for an input file in
javacsv. Write the output file to the current working directory
as a web page containing a table.

IMPORTANT: The class name of the program containing main() must be WriteHtml.

The table should have the same number of rows as there are records
in the csv file. The number of columns in the table should be the
length of the record in the csv file with the most fields. In other
words, make the table just big enough to contain all the data from
the csv file.

The title of the web page should be the name of the input file.

An example input file is given in src/input.csv.

An example output file is given in src/example.html.

An example program that reads and writes text is given in project
readwritefile.

Note that HTML treats certain characters as special; for example, "<"
and ">" surround tags. If a special HTML character appears as ordinary
text, it may result in invalid HTML syntax. Therefore, you must write
"&LT;" to display "<" in HTML. To convert a normal text string to HTML
text, you can use the method in EscapeHTML.java, which is provided
already in the project.

Video demonstrations of a solution for this task are available.
    1. During the exam: as write-html-solution-demo.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/write-html-solution-demo-transcoded.mp4
