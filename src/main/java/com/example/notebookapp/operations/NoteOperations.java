package com.example.notebookapp.operations;

import com.example.notebookapp.command.Command;
import com.example.notebookapp.command.CommandUtils;
import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.ui.input.ConsoleInput;
import com.example.notebookapp.ui.input.InputValidation;
import com.example.notebookapp.ui.menu.MenuPrinter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    public static void displayNotes(Folder folder){
        if(folder == null){
            System.out.println("Error: No folder selected.");
            System.out.println("Choose your folder first.");
            return;
        }
        else if(folder.getNotes().isEmpty()){
            System.out.println("No notes in folder.");
            return;
        }
        for(Note note: folder.getNotes()){
            System.out.println(note.getTitle());
        }
    }

    public static void viewNotes(Folder currentFolder) {
        if(currentFolder.getNotes() == null){
            System.out.println("no notes");
        }
        else if(currentFolder.getNotes().isEmpty()){
            System.out.println("no notes");
        } else{
            printNotesInCurrentFolder(currentFolder);
        }

    }
    public static void viewNote(Folder currentFolder, Command command){
        if(currentFolder == null){
            System.out.println("please select a folder to view a note");
        }
        String noteName = CommandUtils.getFirstArgument(command,"Please enter a note title with this command.");
        if(noteName == null){
            return;
        }
        else{
            printNote(currentFolder.getNote(noteName));
        }
    }

    private static void printNotesInCurrentFolder(Folder currentFolder) {
        List<Note> notes = currentFolder.getNotes();
        for(Note note: notes){
            displayNoteMetadata(note);
        }
    }
    private static void printNote(Note note){
        displayNoteMetadata(note);
    }
    private static void displayNoteMetadata(Note note){
        System.out.println();
        System.out.println("Title: " + note.getTitle());
        System.out.println("Created on " + trimDate(note.getCreatedDate()));
        System.out.println("Last Modified: " + trimDate(note.getModifiedDate()));
        System.out.println("Word Count: " + note.getWordCount());
        System.out.println();
    }
        private static String trimDate(LocalDateTime date){
        if(date == null){
            return "null";
        }

            LocalDateTime trimmed = date;

            DateTimeFormatter friendly = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:SS a");

            return trimmed.format(friendly);
        }

    public static void createNote(Folder currentFolder,Command command) {
        if(currentFolder == null){
            System.out.println("Please enter a folder name to add a note.");
            return;
        }

        String noteName = CommandUtils.getFirstArgument(command,"Error: Must enter a note name.");
        Note existing = currentFolder.getNote(noteName);

        if(existing != null){
            System.out.println("Note already exists. Please use a different title.");
            return;
        }

        Note noteToAdd = new Note(noteName,"");
        boolean added = currentFolder.addNote(noteToAdd);
        System.out.println(added ? "Note successfully added." : "Note not added.");

    }
    public static void createNoteBody(Folder currentFolder, Command command, com.example.notebookapp.commandparse.console.input.ConsoleInput input){
        if(currentFolder == null){
            System.out.println("Current folder is null.");
            return;
        }

        String noteName = CommandUtils.getFirstArgument(command,"Error: Must enter a note name.");
        Note existing = currentFolder.getNote(noteName);

        if(existing == null){
            System.out.println("To add a body, enter the title of an existing note.");
            return;
        }

        final String TERMINATOR = ":end";
        String body = input.getMultiLineUntil("Enter a note body. Enter a new line and type " + TERMINATOR + " to end.",TERMINATOR);
        System.out.println("Exited body creator.");
        existing.setBody(body);
    }
}
