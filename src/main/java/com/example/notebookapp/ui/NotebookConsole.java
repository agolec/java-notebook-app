package com.example.notebookapp.ui;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.persistence.RepositoryStorage;
import com.example.notebookapp.repository.NoteRepository;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotebookConsole {

    private final Scanner kb;
    private final String DEFAULT_REPOSITORY = "notebook";
    private NoteRepository repository;
    private RepositoryStorage storage;
    private Folder currentFolder;
    private boolean running;

    public NotebookConsole() {
        this.kb = new Scanner(System.in);
        this.storage = new RepositoryStorage();
        this.repository = storage.load(DEFAULT_REPOSITORY);

        if(this.repository == null){
            repository = new NoteRepository();
        }
        this.running = true;
    }

    private void saveRepo(){
        this.storage.save(this.repository,this.DEFAULT_REPOSITORY);
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

                case "4":
                    deleteFolder();
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
        System.out.println("4. Delete Folder");
        System.out.println("q. Exit");
        System.out.print("> ");
    }

    private void printNoteMenu() {

        System.out.println();
        System.out.println("=== Notebook ===");
        System.out.println("1. Create Note");
        System.out.println("2. List Notes");
        System.out.println("3. View Note");
        System.out.println("4. Edit Note");
        System.out.println("B. Back");
        System.out.print("> ");

    }

    private void printEditNoteMenu(){
        System.out.println();
        System.out.println("=== EDIT Note ===");
        System.out.println("1. rename note");
        System.out.println("2. remove body");
        System.out.println("B: Back");
    }

    private void printFolderMenu(){
        System.out.println();
        System.out.println("=== Folder ===");
        System.out.println("1. Create Note");
        System.out.println("2. List Notes (enter --v for metadata output)");
        System.out.println("3. Rename Folder");
        System.out.println("4. Open Note (enter --v for metadata output)");
        System.out.println("5. Edit Note");
        System.out.println("B. Back");
        System.out.println("\n");
        System.out.println("Enter the number of your choice, and optional metadata tag");
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
                    createNote();
                    break;

                case "2":
                    listNotes(verbose);
                    break;

                case "3":
                    renameFolder();
                    break;
                case "4":
                    Note note = selectNote();
                    openNote(note,verbose);
                    break;
                case "5":

                    //printEditNoteMenu();
                    Note noteForEditing = selectNote();
                    if(noteForEditing == null){
                        System.out.println("Error: no notes.");
                        break;
                    }
                    editNote(noteForEditing);
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

    private void listNotes(boolean verbose){

        int i = 0;

        if(this.currentFolder.getNotes().isEmpty()){
            System.out.println("no notes.");
            return;
        }

        if(verbose){
            for(Note note: this.currentFolder.getNotes()){
                System.out.println("----");
                System.out.println(note);
                System.out.println("----");
            }
            return;
        }
        System.out.println("\n");
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
            System.out.println("Enter a folder name: ");
            if(kb.hasNext()){
                return kb.nextLine();
            }
        }
    }
    private Note selectNote(){
        String index;
        Note note = null;
        if(this.currentFolder.getNotes() == null || this.currentFolder.getNotes().isEmpty()){
            System.out.println("no notes in folder.");
            return null;
        }
        do{
            listNotes(false);
            System.out.println("Select a note by list number");
            System.out.print("> ");
            index = kb.nextLine();
            if(inputNotDigit(index)){
                continue;
            }
            int i = Integer.parseInt(index);
            note = this.currentFolder.getNote(i - 1);
        }while(note == null);

        return note;
    }
    private void openNote(Note note, boolean verbose){

        if(note == null){
            System.out.println("Error: note is null.");
            return;
        }

        System.out.println(note.getTitle());
        System.out.println();
        System.out.println(note.getBody());
        System.out.println();

        if(verbose){

            System.out.println();
            System.out.println(note.getCreatedDate().truncatedTo(ChronoUnit.SECONDS));
            System.out.println();
            System.out.println(note.getModifiedDate().truncatedTo(ChronoUnit.SECONDS));
            System.out.println();
        }
    }

    private static boolean inputNotDigit(String folder) {
        return Character.isDigit(folder.charAt(0)) == false;
    }

    private void deleteFolder(){
        if(this.repository.getFolders().isEmpty()){
            System.out.println("No folders in repository.");
            return;
        }
        String folderInput = enterFolderNameForDeletion();



        if(folderInput.equalsIgnoreCase("q")){
            return;
        }

        boolean confirmation = getUserConfirmation();

        if(!confirmation){
            return;
        }
        if(folderRemovedSuccessfully(folderInput)){
            System.out.println("Folder removed.");
        } else {
            System.out.println("Folder not removed.");
        }
    }

    private String enterFolderNameForDeletion() {
        String folderInput;
        do{
            listFolders();
            System.out.println("Select a folder for deletion or q to quit");
            System.out.print("> ");
            folderInput = kb.nextLine();
        }while(inputNotDigit(folderInput) && !folderInput.equalsIgnoreCase("q"));
        return folderInput;
    }

    private boolean getUserConfirmation() {
        while(true){
            System.out.print("Are you sure? (y/n) > ");
            String confirmationInput = kb.nextLine().trim().toLowerCase();

            switch(confirmationInput){
                case "y","yes":
                    return true;
                case "n","no":
                    return false;
                default:
                    System.out.println("Please enter y or n for yes/no.");
            }
        }
    }

    private boolean folderRemovedSuccessfully(String folderInput) {
        return this.repository.removeFolder(this.repository.getFolder(Integer.parseInt(folderInput) - 1).getName());
    }

    private void renameNote(Note note){
        String newName;
        System.out.println("Enter new name for note.");
        System.out.print("> ");
        newName = kb.nextLine();
        String renameSuccess = note.renameTitle(newName) ? "Rename successful" : "Rename unsuccessful";
        System.out.println(renameSuccess);
    }
    private void removeBody(Note note){
        String input;
        do{
            System.out.println("Remove body to '" + note.getTitle() + "'?");
            input = kb.nextLine().trim().toLowerCase();

        }while(input.charAt(0) != 'y' && input.charAt(0) != 'n');
        if(input.charAt(0) == 'y'){
            note.setBody("");
        }
        if(note.getBody().isEmpty()){
            String newBody = this.enterNoteBody();
            note.setBody(newBody);
            System.out.println("New Body set.");
        }

    }
    private void editNote(Note note){

        String input;

        do{

            printEditNoteMenu();
            System.out.println("Select an operation or b to back out.");
            input = kb.nextLine();


        } while(inputNotDigit(input) && !input.equalsIgnoreCase("b"));

        switch(input){
            case "1":
                renameNote(note);
                break;
            case "2":
                removeBody(note);
                break;
            case "b":
                break;
            case"default":
                System.out.println("Not a valid option.");
                break;

        }
    }

    private void exit() {
        saveRepo();
        running = false;
        kb.close();
        System.out.println("Closing program. . .");

    }
}