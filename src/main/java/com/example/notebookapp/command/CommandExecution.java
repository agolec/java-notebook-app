package com.example.notebookapp.command;

import com.example.notebookapp.HelpText;
import com.example.notebookapp.operations.FolderOperations;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.FolderDisplay;

public class CommandExecution {

    public static void execute(Command command, NoteRepository repository){
        switch(command.getCommandType()){
            case UNKNOWN -> System.out.println("Unknown command. Type 'help' for help.");
            case HELP -> System.out.println((HelpText.printHelpMenu()));
            case CREATE_FOLDER ->{
                String folderName = command.getArguments()[0];
                boolean created = repository.addFolder(folderName);

                if(created){
                    System.out.println("folder created");
                } else {
                    System.out.println("Folder already exists");
                }
            }
            case LIST_FOLDER -> {
                FolderDisplay.listFolders(repository,false);
            }
            case DELETE_FOLDER -> {
                FolderOperations.deleteFolder(repository,command.getArguments());
            }

        }
    }
}
