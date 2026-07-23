Title: open notes command displays no error message if user enters folder name containing no notes
======

Type: defect
=====

Priority: 5
====

Estimate: 1
===

Description
==
When the user has a folder open that does not have any notes,
and they use the 'list notes' command, the program does not display
any output to the user that the folder is empty or has no notes.

## pre-conditions
user has a folder created with no notes contained in it.

## steps to reproduce
- user has typed "open folder <folder name>" and presses enter for an existing folder in order to get into a folder.
  - expected result: user should see message that they are in the folder.
  - actual result: as expected
- User types "display notes" command and presses enter.
  - expected result: the user should see an error message displaying that "there are no notes in the folder"
  - actual result: the user does not see anything in this case.


## actual results

## expected results