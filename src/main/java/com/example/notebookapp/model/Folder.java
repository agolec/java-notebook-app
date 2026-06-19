package com.example.notebookapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Folder implements Serializable {
    private String name;
    private List<Note> notes;
    private boolean readOnly;
    final LocalDateTime createdDate;
    LocalDateTime accessedDate;
    LocalDateTime modifiedDate;
    public Folder(String name, boolean readOnly){
        this.notes = new ArrayList<Note>();
        setName(name);
        this.createdDate = LocalDateTime.now();
        setReadOnly(readOnly);
    }
    public Folder(Folder source,boolean readOnly){
        this(source.getName(),readOnly);
        this.notes = source.getNotes();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        ensureWriteable();
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Folder title is empty.");
        }
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
    public boolean removeNote(Note note){
        ensureWriteable();
        if(note == null) return false;
        return this.notes.remove(note);
    }

    private void ensureWriteable() {
        if(this.readOnly){
            throw new IllegalStateException("Folder '" + this.getName() + "' is read only.");
        }
    }

    public boolean addNote(Note note){
        ensureWriteable();
        if(note == null) return false;
        return this.notes.add(note);
    }


    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    public boolean getReadOnly(){
        return this.readOnly;
    }

    public Note findNoteByTitle(String title){
        for(Note n: this.notes){
            if(title.equals(n.getTitle())){
                return n;
            }
        }
        return null;
    }
    public boolean removeNoteByTitle(String title){
        ensureWriteable();
        Note note = this.findNoteByTitle(title);

        return removeNote(note);
    }
    public boolean renameFolder(String name){
        ensureWriteable();
        if(name == null || name.isEmpty()){
            return false;
        }
        this.name = name;
        return true;
    }
    public Note copyNote(String title){
        Note original = findNoteByTitle(title);
        return new Note(original);
    }
    public void setModifiedDate(){
        this.modifiedDate = LocalDateTime.now();
    }
    public LocalDateTime getModifiedDate(){
        return this.modifiedDate;
    }
    public void setAccessedDate(){
        this.accessedDate = LocalDateTime.now();
    }
    public LocalDateTime getAccessedDate(){
        return this.accessedDate;
    }



}
