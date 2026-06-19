package com.example.notebookapp.persistence;

import com.example.notebookapp.repository.NoteRepository;
import com.example.notebookapp.ui.NotebookConsole;

import java.io.*;

public class RepositoryStorage {
    private final String filename = "hub.dat";
    public void save(NoteRepository repository){


        try{
            FileOutputStream file = new FileOutputStream(filename);
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
    public NoteRepository load(){
        NoteRepository nr = new NoteRepository();
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            nr = (NoteRepository) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been successfully deserialized.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nr;
    }

}
