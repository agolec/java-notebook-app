002-Detailed note ouput
======

Type: Feature
=====

Priority: Low  
====

Estimate : 3
===

Description
==
A command or set of commands to allow the user to state the level of
output they want to see for any given note or set of notes.

Acceptance Criteria
==
- [x] The user can give extra inputs to state whether a note or set of notes will print metadata
- [x] The user can give extra inputs to state whether a folder or list of folders will print metadata
- [x] The program will interpret the flags entered by the user
- [x] The program will output all metadata of a folder or file.

Technical Notes
=

Testing Notes
=
- Entering just the name or number of the folder outputs the name/body only as normal
- Entering the name or number of the note outputs only the name/body of the note as normally
- Entering the prompted flag for opening a folder will display metadata of the folder
- Entering the flag when performing an "Open Note" operation will display metadata of the note.