package com.example.notebookapp.operations;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.NoteDisplay;
import com.example.notebookapp.ui.input.ConsoleInput;
import com.example.notebookapp.ui.input.InputValidation;
import com.example.notebookapp.ui.menu.MenuPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FolderOperations {
    public static void createFolder(NoteRepository repository, Scanner kb) {

        System.out.print("Folder Name: ");

        String name = ConsoleInput.askForFolderName(kb);

        if (repository.addFolder(name)) {
            System.out.println("Folder created.");
        } else {
            System.out.println("Unable to create folder.");
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
