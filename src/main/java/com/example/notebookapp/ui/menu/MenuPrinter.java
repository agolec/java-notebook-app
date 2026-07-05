package com.example.notebookapp.ui.menu;

public class MenuPrinter {
    public static void printMainMenu() {

        System.out.println();
        System.out.println("=== Folder ===");
        System.out.println("1. Create Folder");
        System.out.println("2. List Folders");
        System.out.println("3. Open Folder");
        System.out.println("4. Delete Folder");
        System.out.println("q. Exit");
        System.out.print("> ");
    }

    public static void printFolderMenu(){
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
    public static void printEditNoteMenu(){
        System.out.println();
        System.out.println("=== EDIT Note ===");
        System.out.println("1. rename note");
        System.out.println("2. remove body");
        System.out.println("B: Back");
    }
    public static void printNoteMenu() {

        System.out.println();
        System.out.println("=== Notebook ===");
        System.out.println("1. Create Note");
        System.out.println("2. List Notes");
        System.out.println("3. View Note");
        System.out.println("4. Edit Note");
        System.out.println("B. Back");
        System.out.print("> ");

    }
}
