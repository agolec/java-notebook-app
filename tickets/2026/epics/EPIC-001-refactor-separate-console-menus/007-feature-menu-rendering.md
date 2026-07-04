Title
======
Menu Rendering Class

Type
=====
Feature

Priority
====
2

Estimate
===
5

Description
==
The Menus of the console class should become their own separate class.

As a creator of the note repository app, I want a separation of the menu
from the UI that displays it, so that it reduces code in the NotebookConsole class

Acceptance Criteria
==
- MainMenu output is produced by MenuPrinter
- FolderMenu output is produced by MenuPrinter
- EditNoteMenu output is produced by MenuPrinter
- NotebookConsole no longer contains the menu printing methods.

Technical Notes
=


Testing Notes
=

Tasks
==

- Create the MenuPrinter class.
- Move printMainMenu()
- Move printFolderMenu()
- Move printEditNoteMenu()
- update NotebookConsole to call MenuPrinter
- Verify all menus continue to print as they did before this feature
