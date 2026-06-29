package com.example.notebookapp.repository;

import com.example.notebookapp.model.Folder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository implements Serializable {
    private final static long serialVersionUID = 1L;
    private List<Folder> folders;

    public NoteRepository(){
        folders = new ArrayList<Folder>();
    }
    public NoteRepository(List<Folder> folders){
        this();
        this.setFolders(folders);
    }
    public List<Folder> getFolders() {

        return folders;

    }
    public void setFolders(List<Folder> folders) {

        this.folders = folders;

    }
    public Folder getFolder(String name){

        for(Folder folder: this.folders){

            if(folder.getName().equals(name)){
                return folder;
            }

        }

        return null;

    }
    public Folder getFolder(int i){
        if(i < 0 || i >= this.folders.size()){
            return null;
        }
        return this.folders.get(i);
    }
    public boolean addFolder(String name){

        return addFolder(new Folder(name,false));

    }
    public boolean renameFolder(String oldName, String newName){

        Folder folder = getFolder(oldName);

        if(folder == null){
            System.out.println("folder null");
            return false;
        }
        //name of intended folder to be renamed is being given a new name that is the same as it's prior name.
        if(oldName.equals(newName)){
            System.out.println("old folder name and new folder name are the same");
            return false;
        }
        //collision against all existing folders in the repository.
        if(folderExists(newName)){
            System.out.println("folder already exists with new name.");
            return false;
        }

        folder.setName(newName);

        return true;
    }

    public boolean addFolder(Folder folder){
        if(folder == null){
            return false;
        }
        if(getFolder(folder.getName()) != null){
            return false;
        }
        return this.folders.add(folder);
    }
    public boolean removeFolder(String name){
        Folder folder = getFolder(name);
        if(folder == null){
            return false;
        }
        return this.folders.remove(folder);
    }

    public boolean folderExists(String name){
        return getFolder(name) != null;
    }





}
