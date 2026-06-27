package com.example.notebookapp.persistence;

import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.NotebookConsole;

import java.io.*;

public class RepositoryStorage {
    private String fileName;
    private final String FILE_EXTENSION = ".dat";
    public void save(NoteRepository repository,String fileName){
        this.fileName = fileName + FILE_EXTENSION;

        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(repository);
            out.close();
            file.close();
            System.out.println("Repository successfully saved.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public NoteRepository load(String fileName){
        this.fileName = fileName + FILE_EXTENSION;
        NoteRepository nr;
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            nr = (NoteRepository) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been successfully deserialized.");
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nr;
    }

}
