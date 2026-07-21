## Epic ID: EPIC_002
## Title: Command Parser Framework
### Date: July 20 2026
### Status: Backlog
### Priority: high

***
### Objective
Establish classes that will allow input gathering, tokenizing,
parsing,  and executing commands through the console.
## Motivation
***
Transition the application from a menu-driven interface to a 
command-line architecture by introducing a reusable command 
parsing and execution framework.
## Scope
***
Included:
- Console Input
- Command Definitions
- Command Execution

Excluded:

Repository operations
Folder operations
Note operations
Business logic for individual commands


### Success Criteria
***
- User input can be gathered
- Input is broken into tokens
- Tokens are matched against defined commands
- The matched command is executed
- Unknown commands display output to the user, and allow user input to continue
- Unimplemented commands display output to the user and allow user input to continue

### Child Features
***
012-console-input-for-command-driving
013-custom-tokenizer
014-input-parser
015-command-definitions
016-command-execution

### NOTES
***
