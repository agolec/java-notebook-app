Title
======
Extract Input Helper

Type
=====
Refactor

Priority
====
3

Estimate
===
3

Description
==
As someone making a console application, to simplify code,
there should be helper methods for collecting user input.

Acceptance Criteria
==
User input collection should look the same from the user end of things.
- An InputHelper class exists to encapsulate console input operations.
- NotebookConsole delegates supported input operations to the 
InputHelper instead of implementing the logic directly.
- Existing validation behavior such as rejecting invalid menu selections or
blank input  continues to function the same.
- The application compliles and all existing functionality
works as before

Technical Notes
=

Testing Notes
=

Tasks
==
- Create InputHelper.
- Move yes/no confirmation logic.
- Move integer/menu selection validation.
- Move non-blank string input.
- Update NotebookConsole to use InputHelper.
- Test all affected menus.