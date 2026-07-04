## Epic ID: EPIC_001
## Title: Separate Menu Concerns
### Date: July 3 2026
### Status: Backlog
### Priority: high

***
### Objective
Refactor the console layer so menu rendering, input handling,
navigation, and command execution are separated into dedicated classes.

## Motivation
***
the NotebookConsole currently performs many unrelated responsibilities,
making it difficult to maintain and extend.

## Scope
***
Included:
- Extract menu rendering
- separate the input handling
- introduce command dispatching
- simplify console navigation

## NOT INCLUDED
- GUI Implementation
- New Note Features
- Persistence Changes

### Success Criteria
***
- NotebookConsole no longer contains menu rendering logic.
- User input parsing is handled by dedicated classes
- existing functionality continues to work
- all existing tests pass

### Child Features
***
NT-007 Extract menu rendering
NT-008 Separate Input Handling
NT-009 Command Dispatcher
NT-010 Navigation Refactor

### NOTES
***
Future GUI development should be able to reuse the command layer.