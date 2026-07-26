package com.example.notebookapp.ui;

import com.example.notebookapp.operations.folder.FolderOperations;
import com.example.notebookapp.persistence.RepositoryStorage;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.display.FolderDisplay;
import com.example.notebookapp.ui.menu.MenuPrinter;

import java.util.Scanner;

public class NotebookConsole {

    private final Scanner kb;
    private final String DEFAULT_REPOSITORY = "notebook";
    private NoteRepository repository;
    private RepositoryStorage storage;
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

            MenuPrinter.printMainMenu();

            String input = kb.nextLine();

            switch (input) {

                case "1":
                    FolderOperations.createFolder(this.repository,this.kb);
                    break;

                case "2":
                    FolderDisplay.listFolders(this.repository,true);
                    break;

                case "3":
                    FolderOperations.openFolder(this.repository,this.kb);
                    break;

                case "4":
                    FolderOperations.deleteFolder(this.repository,this.kb);
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

    private void exit() {
        saveRepo();
        running = false;
        kb.close();
        System.out.println("Closing program. . .");

    }
}