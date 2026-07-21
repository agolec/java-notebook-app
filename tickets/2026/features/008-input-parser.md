Title: Input Parser
======

Type: Feature
=====

Priority: High
====

Estimate: 3
===

Description
==
As a user of a console application, I would like the program
to be able to interpret my input commands I type
so that requested actions can be executed.

Acceptance Criteria
==
- User input is taken in as a single line of input
- Input is separated into Commands and Arguments
- Arguments between two double quotes are treates as one
token/one input
- A recognized command/action is converted into a Command object.
- Unrecognized commands are returned with an Unknown command type.
- Unrecognized commands output an "unknown command" message
- Empty input does not cause the application to crash
- Parser returns enough information for commands to be executed

Technical Notes
=
- Introduce an InputParser responsible for converting raw text into a Command.
- Tokenize input on whitespace.
- First token represents the command.
- Remaining tokens become command arguments.
- Map command text to a CommandType enum.
- Unknown commands map to CommandType.UNKNOWN.

Testing Notes
=
- Parse command with no arguments (help)
- Parse command with one argument (open Notes)
- Parse command with multiple arguments (create note shopping list)
- Parse unknown command (kickRocks)
- Parse empty string
- Parse input containing extra whitespace