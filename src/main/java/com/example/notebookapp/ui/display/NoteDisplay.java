package com.example.notebookapp.ui.display;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;

public class NoteDisplay {
    public static void listNotes(Folder currentFolder, boolean verbose){

        int i = 0;

        if(currentFolder.getNotes().isEmpty()){
            System.out.println("no notes.");
            return;
        }

        if(verbose){
            for(Note note: currentFolder.getNotes()){
                System.out.println("----");
                System.out.println(note);
                System.out.println("----");
            }
            return;
        }
        System.out.println("\n");
        for(Note note: currentFolder.getNotes()){
            System.out.println((i+1)+". " + note.getTitle());
            i++;
        }

    }
    public static void openNote(Note note){
        if(note == null){
            System.out.println("Error: note is null.");
            return;
        }
        System.out.println(note.getTitle());
        System.out.println(note.getBody());
    }
}
