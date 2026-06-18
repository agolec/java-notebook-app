package com.example.notebookapp.ui;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.repository.NoteRepository;

import java.util.Scanner;

public class NotebookConsole {

    private final Scanner kb;
    private final NoteRepository repository;
    private Folder currentFolder;
    private boolean running;

    public NotebookConsole() {
        this.kb = new Scanner(System.in);
        this.repository = new NoteRepository();
        this.running = true;
    }

    public void run() {

        while (running) {

            printMainMenu();

            String input = kb.nextLine();

            switch (input) {

                case "1":
                    createFolder();
                    break;

                case "2":
                    listFolders();
                    break;

                case "3":
                    openFolder();
                    break;

                case "quit":
                case "q":
                    exit();
                    break;


                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printMainMenu() {

        System.out.println();
        System.out.println("=== Folder ===");
        System.out.println("1. Create Folder");
        System.out.println("2. List Folders");
        System.out.println("3. Open Folder");
        System.out.println("quit. Exit");
        System.out.print("> ");
    }

    private void printNoteMenu() {

        System.out.println();
        System.out.println("=== Notebook ===");
        System.out.println("1. Create Note");
        System.out.println("2. List Notes");
        System.out.println("3. View Note");
        System.out.println("B. Back");
        System.out.print("> ");

    }

    private void printFolderMenu(){
        System.out.println();
        System.out.println("=== Folder ===");
        System.out.println("1. Create Note");
        System.out.println("2. List Notes");
        System.out.println("3. Rename Folder");
        System.out.println("B. Back");
        System.out.print("> ");
    }

    private void createFolder() {

        System.out.print("Folder Name: ");

        String name = kb.nextLine();

        if (repository.addFolder(name)) {
            System.out.println("Folder created.");
        } else {
            System.out.println("Unable to create folder.");
        }
    }

    private void listFolders() {

        if (repository.getFolders().isEmpty()) {
            System.out.println("No folders exist.");
            return;
        }

        for (int i = 0; i < repository.getFolders().size(); i++) {
            System.out.println((i + 1) + ". " + repository.getFolders().get(i).getName());
        }

    }

    private void openFolder() {
        System.out.println("==OPEN FOLDER==");
        System.out.print("Enter the folder name.");
        System.out.print("> ");

        String folderName = kb.nextLine();

        if (repository.folderExists(folderName)) {
            System.out.println("folder opening");
            this.currentFolder = repository.getFolder(folderName);
        } else {
            System.out.println("Folder not found.");
            return;
        }
        folderMenu();
    }

    private void folderMenu() {
        boolean inFolder = true;

        while(inFolder) {

            printFolderMenu();

            String input = kb.nextLine();

            switch(input) {

                case "1":
                    createNote();
                    break;

                case "2":
                    listNotes();
                    break;

                case "3":
                    renameFolder();
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
    private void createNote() {
        String title = enterNoteTitle();
        String body = enterNoteBody();

        Note note = new Note(title, body);

        currentFolder.addNote(note);

        System.out.println("Note created.");
    }

    private String enterNoteTitle(){
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

    private String enterNoteBody(){

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

    private void listNotes(){

        int i = 0;

        if(this.currentFolder.getNotes().isEmpty()){
            System.out.println("no notes.");
            return;
        }

        for(Note note: this.currentFolder.getNotes()){
            System.out.println((i+1)+". " + note.getTitle());
            i++;
        }

    }

    /**
     * todo
     */
    private void renameFolder(){
        boolean renaming = true;
        while(renaming){
            if(this.repository.getFolders().isEmpty()){
                System.out.println("No folders to rename.");
                return;
            }

            System.out.println("===RENAME FOLDER===");
            System.out.println("Select a folder to rename: ");

            Folder folder = selectFolder();

            if(folder == null){
                return;
            }

            String newName = askForFolderName();
            if(repository.renameFolder(folder.getName(),newName)){
                System.out.println("Folder renamed");
                renaming = false;
            } else {
                System.out.println("Rename unsuccessful.");
            }
        }

    }
    private Folder selectFolder(){
        String folder;
        do{
            listFolders();
            System.out.println("Select a folder.");
            System.out.print("> ");
            folder = kb.nextLine();
        }while(inputNotDigit(folder));
        return this.repository.getFolder(Integer.parseInt(folder) - 1);
    }
    private String askForFolderName(){
        while(true){
            System.out.println("Enter a new folder name: ");
            if(kb.hasNext()){
                return kb.nextLine();
            }
        }
    }

    private static boolean inputNotDigit(String folder) {
        return Character.isDigit(folder.charAt(0)) == false;
    }

    private void deleteFolder(){

    }
    private void renameNote(){

    }

    private void exit() {

        running = false;
        kb.close();
        System.out.println("Closing program. . .");

    }
}