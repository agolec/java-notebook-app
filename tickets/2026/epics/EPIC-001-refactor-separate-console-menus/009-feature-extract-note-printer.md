Title
======
Extract Note Printer

Type
=====
refactor

Priority
====
2

Estimate
===
2

Description
==
As we have NotebookConsole loaded with functions for menus, it
would be better to refactor them by splitting up menus.

Acceptance Criteria
==
Code for printing note menu should be utilized in the menu
printing class.

Technical Notes
=
Code should be extracted from where it exists inside the
notebok console

Testing Notes
=
check that output for the note menu is unchanged from before this
refactor went into the code.