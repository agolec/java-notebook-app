package com.example.notebookapp.ui.display;

import com.example.notebookapp.repository.NoteRepository;

public class FolderDisplay {
    public static void listFolders(NoteRepository repository) {

        if (repository.getFolders().isEmpty()) {
            System.out.println("No folders exist.");
            return;
        }

        for (int i = 0; i < repository.getFolders().size(); i++) {
            System.out.println((i + 1) + ". " + repository.getFolders().get(i).getName());
        }

    }
}
