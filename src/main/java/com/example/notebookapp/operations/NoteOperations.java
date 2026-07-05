package com.example.notebookapp.operations;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.ui.input.ConsoleInput;
import com.example.notebookapp.ui.input.InputValidation;
import com.example.notebookapp.ui.menu.MenuPrinter;

import java.util.Scanner;

public class NoteOperations {
    public static void createNote(Folder currentFolder , Scanner kb) {
        String title = ConsoleInput.enterNoteTitle(kb);
        String body = ConsoleInput.enterNoteBody(kb);

        Note note = new Note(title, body);

        currentFolder.addNote(note);

        System.out.println("Note created.");
    }


    public static void renameNote(Note note,Scanner kb){
        String newName;
        System.out.println("Enter new name for note.");
        System.out.print("> ");
        newName = kb.nextLine();
        String renameSuccess = note.renameTitle(newName) ? "Rename successful" : "Rename unsuccessful";
        System.out.println(renameSuccess);
    }

    public static void editNote(Note note,Scanner kb){

        String input;

        do{

            MenuPrinter.printEditNoteMenu();
            System.out.println("Select an operation or b to back out.");
            input = kb.nextLine();


        } while(InputValidation.isNotDigit(input) && !input.equalsIgnoreCase("b"));

        switch(input){
            case "1":
                renameNote(note,kb);
                break;
            case "2":
                removeBody(note,kb);
                break;
            case "b":
                break;
            case"default":
                System.out.println("Not a valid option.");
                break;

        }
    }
    public static void removeBody(Note note,Scanner kb){
        String input;
        do{
            System.out.println("Remove body to '" + note.getTitle() + "'?");
            input = kb.nextLine().trim().toLowerCase();

        }while(input.charAt(0) != 'y' && input.charAt(0) != 'n');
        if(input.charAt(0) == 'y'){
            note.setBody("");
        }
        if(note.getBody().isEmpty()){
            String newBody = ConsoleInput.enterNoteBody(kb);
            note.setBody(newBody);
            System.out.println("New Body set.");
        }

    }
}
