readme.txt -- for sum-ss

THE TASK

Modify SpreadSheet.java so that a formula can sum either a row or a column.

Syntax: =SUM(C1:C2) where C1 and C2 are cells at the ends of the summation.

Semantics:
     1. If C1 and C2 are within either a row or column of numbers compute
        and return the sum.
        Note: Do this regardless of the direction in which the sum runs.
        Note: The sum can run right to left, left to right, top to bottom, or
              bottom to top.
     2. If an error occurs while evaluating a formula, display "!!!" in the
        cell. Errors include the following:
        A. C1 and C2 fail to fall in the same row or column.
        B. One or more cells in the range are not numbers (or are not
           formulas that evaluate without error to numbers).
        C. The SUM formula depends either directly or indirectly on its
           own value; that is, a cycle is detected.

Video demonstrations of a solution for this task are available.
    1. During the exam: as sum-ss.html in the "Solution Demonstration Videos"
       folder on the desktop in the exam delivery environment.
    2. During exam preparation, outside the exam environment.
       https://s3-us-west-2.amazonaws.com/proxor-video/video/sum-ss-transcoded.mp4
