Title: Add Note command
======

Type: feature
=====

Priority: 1
====

Estimate: 5
===

Description
==
As a user making a command based note application, I want to be able to
add or create a note, so that I can create content.



Acceptance Criteria
==
- User must be inside of a folder to add a note
- Typing "add note <note Name>" of an existing note should show its metadata.

- When user presses enter on the command, a success or failure message should appear.

## Failure Messages
- If user is not in a folder, display the appropriate error message
- If the note already exists in the folder, display the appropriate error message.

## Success 
- Successfully adding a note title should create a note with no body
- Successfully adding a note should inform the user of its creation.


Technical Notes
=
-   New CommandType and Command Definition
    to be created to support operations.

Testing Notes
=
- Try typing the name of a note that already exists from the menu driven program
- Type the name of a note that does not exist
- Delete an existing note, and use the add note command for that note
- Try adding the name of an existing note from another directory.