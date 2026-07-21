package com.example.notebookapp.persistence;

import com.example.notebookapp.model.Note;
import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.model.Folder;

public class ApplicationContext {
    private final NoteRepository repository;

    private Folder currentFolder;

    private Note currentNote;

    public ApplicationContext(NoteRepository repository){
        this.repository = repository;
    }

    public NoteRepository getRepository() {
        return this.repository;
    }

    public Folder getCurrentFolder() {
        return this.currentFolder;
    }
    public void setCurrentFolder(Folder currentFolder) {
        this.currentFolder = currentFolder;
    }

    public Note getCurrentNote() {
        return this.currentNote;
    }

    public void setCurrentNote(Note currentNote) {
        this.currentNote = currentNote;
    }
}
