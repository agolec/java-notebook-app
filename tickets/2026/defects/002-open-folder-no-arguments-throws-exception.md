## Open Folder Prompt with no arguments crashes program

## priority
- low


## environment
Development

## Pre-Conditions
None

## steps to reproduce
- Step Action Launch the program from the main method for command execution
        Expected Outcome: Should have the program booted.
- Step Action: Type "Open Folder" command but do not give any argument
        Expected Outcome: User should get error message that they must enter a folder name
  -      Actual Outcome: Exception is thrown in program execution, stopping program.



## actual results

An Exception is thrown and the user cannot continue running the program.
## expected results
User should be instructed to enter a folder name as an argument when they type the command.