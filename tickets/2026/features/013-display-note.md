Title: Display / List Note
======

Type: Feature
=====

Priority: P3
====

Estimate: 3
===

Description
==
As someone that is using a console command controlled application,
I would like a way to display the notes inside of a folder with commands,
so that I can see the contents of the folder I'm currently inside.

Acceptance Criteria
==
- Command should fail if the user is not inside a folder
- If no notes are in the folder, display a message stating there are no notes.
- If there are notes contained inside the folder, display them all.
- No pagination until test data gets too large.

Technical Notes
=

Testing Notes
=