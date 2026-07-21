package com.example.notebookapp.ui.input;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.FolderDisplay;
import com.example.notebookapp.ui.display.NoteDisplay;

import java.util.Scanner;

public class ConsoleInput {

    public static String enterFolderNameForDeletion(Scanner kb, NoteRepository repository) {
        String folderInput;
        do{
            FolderDisplay.listFolders(repository,true);
            System.out.println("Select a folder for deletion or q to quit");
            System.out.print("> ");
            folderInput = kb.nextLine();
        }while(InputValidation.isNotDigit(folderInput) && !folderInput.equalsIgnoreCase("q"));
        return folderInput;
    }



    public static Folder selectFolder(Scanner kb, NoteRepository repository){
        String folder;
        do{
            FolderDisplay.listFolders(repository,true);
            System.out.println("Select a folder.");
            System.out.print("> ");
            folder = kb.nextLine();
        }while(InputValidation.isNotDigit(folder));
        return repository.getFolder(Integer.parseInt(folder) - 1);
    }


    public static String askForFolderName(Scanner kb){
        while(true){
            System.out.println("Enter a folder name: ");
            if(kb.hasNext()){
                return kb.nextLine();
            }
        }
    }


    /*

                    NOTE RELATED METHODS

                    TO BE PLACED BELOW

     */


    public static Note selectNote(Folder currentFolder,Scanner kb){
        String index;
        Note note = null;
        if(currentFolder.getNotes() == null || currentFolder.getNotes().isEmpty()){
            System.out.println("no notes in folder.");
            return null;
        }
        do{
            NoteDisplay.listNotes(currentFolder,false);
            System.out.println("Select a note by list number");
            System.out.print("> ");
            index = kb.nextLine();
            if(InputValidation.isNotDigit(index)){
                continue;
            }
            int i = Integer.parseInt(index);
            note = currentFolder.getNote(i - 1);
        }while(note == null);

        return note;
    }

    public static String enterNoteTitle(Scanner kb){
        String inputPrompt = "Enter the title of the note: ";

        System.out.print(inputPrompt);

        String title = kb.nextLine();

        while(title.isBlank()) {
            System.out.println("Title cannot be empty.");
            System.out.print(inputPrompt);

            title = kb.nextLine();
        }

        return title;
    }

    public static String enterNoteBody(Scanner kb){

        final String END = ":end";
        StringBuilder sb = new StringBuilder();

        System.out.println("Enter the note body. Enter ':end' on a new line to finish.");

        while(true) {

            String line = kb.nextLine();

            if(line.equals(END)) {
                break;
            }

            sb.append(line).append("\n");
        }

        return sb.toString();
    }

}
