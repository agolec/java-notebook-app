package com.example.notebookapp.operations.folder;

import com.example.notebookapp.command.Command;
import com.example.notebookapp.command.CommandUtils;
import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.operations.NoteOperations;
import com.example.notebookapp.persistence.ApplicationContext;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.NoteDisplay;
import com.example.notebookapp.ui.input.ConsoleInput;
import com.example.notebookapp.ui.input.InputValidation;
import com.example.notebookapp.ui.menu.MenuPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FolderOperations {
    public static void viewFolder(Command command,ApplicationContext context){
        if(CommandUtils.getFirstArgument(command,null).isEmpty()){
            if(context.getCurrentFolder() == null){
                System.out.println("No folder selected.");
            } else {
                Folder folder = context.getCurrentFolder();
                System.out.println("Name: " + folder.getName());
                System.out.println("Created date: " + folder.getCreatedDate());
                System.out.println("Accessed date: " + folder.getAccessedDate());
                System.out.println("Modified date: " + folder.getModifiedDate());
                System.out.println("Read Only: " + folder.getReadOnly());
            }
        }
    }
    public static void createFolder(NoteRepository repository, Scanner kb) {

        String name = ConsoleInput.askForFolderName(kb);

        if (repository.addFolder(name)) {
            System.out.println("Folder created.");
        } else {
            System.out.println("Unable to create folder.");
        }
    }
    public static void createFolder(Command command, ApplicationContext context) {
        String folderName;
        folderName = CommandUtils.getFirstArgument(command,"Please enter a title as an argument");

        if(folderName.isEmpty()){
            return;
        }
        if(folderName.isBlank()){
            System.out.println("folder name cannot be blank.");
            return;
        }

        boolean created = context.getRepository().addFolder(folderName);

        if(created){
            System.out.println("folder created");
        } else {
            System.out.println("Folder already exists");
        }
    }

    public static void openFolder(NoteRepository repository, Scanner kb) {
        Folder currentFolder = null;
        System.out.println("==OPEN FOLDER==");
        System.out.print("Enter the folder name.");
        System.out.print("> ");

        String folderName = kb.nextLine();

        if (repository.folderExists(folderName)) {
            System.out.println("folder opening");
            currentFolder = repository.getFolder(folderName);
        } else {
            System.out.println("Folder not found.");
            return;
        }
        folderMenu(repository,currentFolder,kb);
    }
    public static void openFolder(ApplicationContext context, Command command){
        String folderName = CommandUtils.getFirstArgument(command,"Please enter a title as an argument");
        Folder folder = context.getRepository().getFolder(folderName);

        if(folder == null){
            System.out.println("folder doesn't exist");
        }
        else {
            context.setCurrentFolder(folder);
            System.out.println("Folder opened :" + context.getCurrentFolder().getName());
        }
    }

    public static void folderMenu(NoteRepository repository, Folder currentFolder ,Scanner kb) {
        boolean inFolder = true;

        while(inFolder) {

            MenuPrinter.printFolderMenu();

            String input = kb.nextLine();
            String[] tokens = input.split("\\s+");
            String choice = tokens[0];
            List<String> flags = new ArrayList<>();

            for(int i = 1; i < tokens.length;i++){
                flags.add(tokens[i]);
            }
            boolean verbose = false;
            for(int i = 0; i < flags.size();i++){
                if(flags.get(i).contains("--v")){
                    verbose = true;
                }
            }

            switch(choice) {

                case "1":
                    NoteOperations.createNote(currentFolder,kb);
                    break;

                case "2":
                    NoteDisplay.listNotes(currentFolder,verbose);
                    break;

                case "3":
                    renameFolder(repository,kb);
                    break;
                case "4":
                    Note note = ConsoleInput.selectNote(currentFolder,kb);
                    NoteDisplay.openNote(note);
                    break;
                case "5":
                    Note noteForEditing = ConsoleInput.selectNote(currentFolder,kb);
                    if(noteForEditing == null){
                        System.out.println("Error: no notes.");
                        break;
                    }
                    NoteOperations.editNote(noteForEditing,kb);
                    break;
                case "B":
                case "b":
                    inFolder = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    public static void deleteFolder(NoteRepository repository,Scanner kb){
        if(repository.getFolders().isEmpty()){
            System.out.println("No folders in repository.");
            return;
        }
        String folderInput = ConsoleInput.enterFolderNameForDeletion(kb,repository);

        if(folderInput.equalsIgnoreCase("q")){
            return;
        }

        boolean confirmation = InputValidation.getUserConfirmation(kb);

        if(!confirmation){
            return;
        }
        if(InputValidation.folderRemovedSuccessfully(folderInput,repository)){
            System.out.println("Folder removed.");
        } else {
            System.out.println("Folder not removed.");
        }

    }

    /**
     *      Method for use in the terminal implementation of this
     * @param repository
     */
    public static void deleteFolder(NoteRepository repository,Command command){
        boolean removed;
        if(repository.getFolders().isEmpty()){
            System.out.println("No folders to delete");
        }
        String folder = CommandUtils.getFirstArgument(command,"Please enter a title as an argument");
        //I assume a null check is needed to see if the folder exists at all to output the appropriate message?????
        if(repository.getFolder(folder) == null){
            return;
        }
            removed = repository.removeFolder(folder);

        if(removed){
            System.out.println("folder removed successfully");
            return;
        }
        System.out.println("Folder not removed.");
    }
    public static void renameFolder(NoteRepository repository, Scanner kb){
        boolean renaming = true;
        while(renaming){
            if(repository.getFolders().isEmpty()){
                System.out.println("No folders to rename.");
                return;
            }

            System.out.println("===RENAME FOLDER===");
            System.out.println("Select a folder to rename: ");

            Folder folder = ConsoleInput.selectFolder(kb,repository);

            if(folder == null){
                return;
            }

            String newName = ConsoleInput.askForFolderName(kb);
            if(repository.renameFolder(folder.getName(),newName)){
                System.out.println("Folder renamed");
                renaming = false;
            } else {
                System.out.println("Rename unsuccessful.");
            }
        }

    }
}
