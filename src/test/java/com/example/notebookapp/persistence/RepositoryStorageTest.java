package com.example.notebookapp.persistence;

import com.example.notebookapp.model.Folder;
import com.example.notebookapp.model.Note;
import com.example.notebookapp.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepositoryStorageTest {
    @Test
    void createNotebook() {

        final String EXPECTED_FOLDER_TITLE = "Title";
        final String EXPECTED_NOTE_TITLE = "Note Titelrino.";
        final String EXPECTED_NOTE_BODY = "Body\nBody\nBody";
        final String FILE_NAME = "test_notes";
        NoteRepository repo = new NoteRepository();
        repo.addFolder(EXPECTED_FOLDER_TITLE);
        Folder folder = repo.getFolder(EXPECTED_FOLDER_TITLE);

        Note note = new Note();
        note.setTitle(EXPECTED_NOTE_TITLE);
        note.setBody(EXPECTED_NOTE_BODY);

        folder.addNote(note);

        RepositoryStorage store = new RepositoryStorage();
        store.save(repo,FILE_NAME);

        NoteRepository loadedRepo = store.load(FILE_NAME);
        Note loadedNote = loadedRepo.getFolder(EXPECTED_FOLDER_TITLE).findNoteByTitle(EXPECTED_NOTE_TITLE);
        Assertions.assertEquals(EXPECTED_FOLDER_TITLE, loadedRepo.getFolder(EXPECTED_FOLDER_TITLE).getName());
        Assertions.assertEquals(EXPECTED_NOTE_TITLE, loadedNote.getTitle());
        Assertions.assertEquals(EXPECTED_NOTE_BODY, loadedNote.getBody());
    }
}