package com.example.notebookapp.ui.display;

import com.example.notebookapp.repository.NoteRepository;

public class FolderDisplay {
    /**
     *
     * @param repository
     * @param numberedList - True displays folders as a numbered list. False displays titles of folders only.
     */
    public static void listFolders(NoteRepository repository,boolean numberedList) {

        if (repository.getFolders().isEmpty()) {
            System.out.println("No folders exist.");
            return;
        }

        if(numberedList) {
            for (int i = 0; i < repository.getFolders().size(); i++) {
                System.out.println((i + 1) + ". " + repository.getFolders().get(i).getName());
            }
            return;
        }
        for (int i = 0; i < repository.getFolders().size(); i++) {
            System.out.println(repository.getFolders().get(i).getName());
        }


    }
}
