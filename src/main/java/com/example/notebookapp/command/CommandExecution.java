package com.example.notebookapp.command;

import com.example.notebookapp.HelpText;
import com.example.notebookapp.operations.folder.FolderOperations;
import com.example.notebookapp.operations.NoteOperations;
import com.example.notebookapp.persistence.ApplicationContext;
import com.example.notebookapp.ui.display.FolderDisplay;

public class CommandExecution {

    public static ExecutionResult execute(Command command, ApplicationContext context){
        switch(command.getCommandType()){
            case EXIT -> {
                return ExecutionResult.EXIT;
            }
            case UNKNOWN -> {
                System.out.println("Unknown command. Type 'help' for help.");
                return ExecutionResult.CONTINUE;
            }

            case HELP -> {
                System.out.println((HelpText.printHelpMenu()));
                return ExecutionResult.CONTINUE;
            }
            case CREATE_FOLDER ->{
                FolderOperations.createFolder(command, context);
                return ExecutionResult.CONTINUE;
            }
            case LIST_FOLDER -> {

                FolderDisplay.listFolders(context.getRepository(),false);
                return ExecutionResult.CONTINUE;
            }
            case OPEN_FOLDER -> {
                FolderOperations.openFolder(context,command);
                return ExecutionResult.CONTINUE;
            }
            case DELETE_FOLDER -> {
                FolderOperations.deleteFolder(context.getRepository(),command);
                return ExecutionResult.CONTINUE;
            }
            case LIST_NOTE -> {
                NoteOperations.displayNotes(context.getCurrentFolder());
                return ExecutionResult.CONTINUE;
            }
            case VIEW_NOTE -> {
                NoteOperations.viewNote(context.getCurrentFolder(),command);
                return ExecutionResult.CONTINUE;
            }
            case VIEW_NOTES -> {
                NoteOperations.viewNotes(context.getCurrentFolder());
                return ExecutionResult.CONTINUE;
            }
            case CREATE_NOTE -> {
                NoteOperations.createNote(context.getCurrentFolder(),command);
                NoteOperations.createNoteBody(context.getCurrentFolder());
                return ExecutionResult.CONTINUE;
            }
            default -> {
                System.out.println("Switch case not implemented for command type" +
                        " yet. Please make a case");
                return ExecutionResult.CONTINUE;
            }

        }
    }
}
