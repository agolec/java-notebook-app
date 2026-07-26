Title: View Note command
======

Type: feature
=====

Priority: 1
====

Estimate: 3
===

Description
==
As a user making a command based note application, I want to be able to 
view a note, so that I can see metadata information about it.



Acceptance Criteria
==
- User must be inside of a folder to view a note
- Typing "view note <note Name>" of an existing note should show its metadata.
    - Metadata is the following:
      - Created Date
      - Modified Date
      - Word Count
- If user is not in a folder, display the appropriate error message
- If the note does not exist, display the appropriate error message.
- The note is not modified by the operation
  - Therefore modified date must not be assigned a 
    new value as a result of printing metadata


Technical Notes
=
-   New CommandType and Command Definition
    to be created to support operations.
-   New execution enum to handle viewing notes.

Testing Notes
=