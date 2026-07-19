package com.example.notebookapp.command;

import com.example.notebookapp.HelpText;
import com.example.notebookapp.model.Folder;
import com.example.notebookapp.operations.FolderOperations;
import com.example.notebookapp.persistence.ApplicationContext;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.FolderDisplay;

public class CommandExecution {

    public static void execute(Command command, ApplicationContext context){
        switch(command.getCommandType()){
            case UNKNOWN -> System.out.println("Unknown command. Type 'help' for help.");
            case HELP -> System.out.println((HelpText.printHelpMenu()));
            case CREATE_FOLDER ->{
                String folderName = command.getArguments()[0];
                boolean created = context.getRepository().addFolder(folderName);

                if(created){
                    System.out.println("folder created");
                } else {
                    System.out.println("Folder already exists");
                }
            }
            case LIST_FOLDER -> {
                FolderDisplay.listFolders(context.getRepository());
            }
            case OPEN_FOLDER -> {
                FolderOperations.openFolder(context,command);
            }
            case DELETE_FOLDER -> {
                FolderOperations.deleteFolder(context.getRepository(),command.getArguments());
            }

        }
    }
}
