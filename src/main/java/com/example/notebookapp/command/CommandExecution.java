package com.example.notebookapp.command;

import com.example.notebookapp.HelpText;
import com.example.notebookapp.operations.FolderOperations;
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
                String folderName = command.getArguments()[0];
                boolean created = context.getRepository().addFolder(folderName);

                if(created){
                    System.out.println("folder created");
                } else {
                    System.out.println("Folder already exists");
                }
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
                FolderOperations.deleteFolder(context.getRepository(),command.getArguments());
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
