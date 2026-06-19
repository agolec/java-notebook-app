package com.example.notebookapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The note class is all the code meant to make up a note.
 */
public class Note implements Serializable {
    private String title;
    private String body;

    private Set<String> tags = new HashSet<>();

    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    public Note(){
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = this.createdDate;
    }
    public Note(String title, String body){
        this();
        this.setTitle(title);
        this.setBody(body);
        this.modifiedDate = null;
    }
    public Note(Note note){
        this(note.title, note.body);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Note title cannot be empty");
        }
        this.title = title;
        touch();
    }
    public void rename(String title){
        touch();
        this.setTitle(title);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        touch();
    }
    public void touch(){
        this.modifiedDate = LocalDateTime.now();
    }
    public int getWordCount(){
        return calculateWordCount();
    }
    private int calculateWordCount(){
        final int EMPTY_BODY = 0;
        if(this.body == null || this.body.isBlank()){
            return EMPTY_BODY;
        }

        Pattern pattern = Pattern.compile("\\b\\p{L}+\\b");
        Matcher matcher = pattern.matcher(this.body);

        int count = 0;
        while(matcher.find()){
            count++;
        }

        return count;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;

    }public void addTag(String tag){
        this.tags.add(tag);
    }

    public boolean noTags() {
        return this.tags.isEmpty();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public Note makeCopy(){
        return new Note(this);
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Title: " + this.getTitle() + "\n")
                .append("Body: " + this.getBody() + "\n")
                .append("Word Count:" + this.calculateWordCount() + "\n")
                .append("Date Created: " + this.getCreatedDate() + "\n")
                .append("Date Modified: " + this.getModifiedDate() + "\n");
        return sb.toString();
    }


}
