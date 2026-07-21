Title: List Folders (for commands)
======

Type (Refactor/TechDebt)
=====

Priority Low
====

Estimate 1
===

Description
==
As a user of the command driven implementation
of the note taking app, I want to see a list of folder
names without numbers in front of them, so that I can
see the titles without having numbers displayed.

Acceptance Criteria
==
Folders are listed with the 'list folders' command
Numbers do not display with the folder list

Technical Notes
=
Overload the existing listFolders method, or modify 
the existing method so it can conditionally display
either numbers or no numbers.

NUMBERS ARE STILL USED IN THE MENU DRIVEN VERSION

Testing Notes
=