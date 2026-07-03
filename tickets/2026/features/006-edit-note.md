Title
======
Edit Notes

Type
=====
Feature

Priority
====
High

Estimate
===
5

Description
==
As a designer of a program, I want a means to edit notes.<br>
Title can be changed<br>
Body can be replaced<br>

Acceptance Criteria
==
- If the FOLDER contains no notes, the user should not be able to edit a note
- 
- Canceling the edit operation at any menu level should leave the note unchanged.
- 
- user should be able to modify the title of a note successfully if the title is not the same as the current title.
- If user gives new title with the same name as the note's current title, rename should not succeed.
- If the new title is empty, the note's title should not update.
- User should be able to remove a body and re-enter it.
- If the body entered by the user is empty, it should not update.
- For each successful title change, the modified date should change.
- For each successful title change, the created date should not change.
- For each successful body change, the modified date should change.
- For each successful body change, the created by date should stay the same.

Technical Notes
=
- Note.renameTitle() - returns a boolean indicating successful rename operation.
- Note.setTitle() performs validation and updates the modified timestamp.
- Existing note body is replaced rather than edited in place due to console limitations.
- UI validates note selection before entering the edit workflow.

Testing Notes
=
- Rename a note with a valid title.
- Attempt to rename to the existing title.
- Attempt to rename to an empty title.
- Replace a note body with valid content.
- Attempt to replace a body with no content.
- Verify created date is unchanged after edits.
- Verify modified date changes only after successful edits.
- Attempt to edit when the folder contains no notes.
- Cancel editing and verify the note is unchanged.

## Status
DONE