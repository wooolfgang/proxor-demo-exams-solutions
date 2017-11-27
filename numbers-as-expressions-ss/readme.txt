readme.txt -- for numbers-as-expressions-ss

THE TASK

The spreadsheet implements formulas that begin with equal (=). A
formula can be either references to cells of the form
=<letter><digits>, such as =C8, or a sequence of cell references separated
by binary operators (+, -, *, or /), such as =C8+B2*A3. (Note that
operators are evaluated left to right without regard for operator
precedence; e.g. the previous example is evaluated as (C8 + B2) * A3.)

To complete this task, modify SpreadSheet.java to allow the use of
numbers (floating point or integer) in formulas. You should also
accept unary minus. For example, =-A3*4.5+B2 should be a valid
formula, evaluated as (((-(A3))*(4.5))+(B2)).

For this task, you may assume that:
  1. Numbers do not contain operators; for example, 1.3e-6 and 2e+5 are
     not valid numbers.
  2. SignedNonNumbers such as NaN and Infinity are not present.

Note that unary minus can be applied to an expression containing a
unary minus; for example, =---5+--A3 is equivalent to =-5+A3.

Video demonstrations of a solution for this task are available.
    1. During the exam: as numbers-as-expressions-ss.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/numbers-as-expressions-ss-transcoded.mp4
