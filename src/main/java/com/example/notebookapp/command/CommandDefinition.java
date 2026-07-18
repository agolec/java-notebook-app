package com.example.notebookapp.command;

public class CommandDefinition {

    private CommandType type;

    private String[] keywords;

    public String[] getKeywords() {
        return keywords;
    }

    CommandDefinition(CommandType type,String... keywords){

        setType(type);
        setKeywords(keywords);
    }

    public void setKeywords(String[] keywords) {

        this.keywords = new String[keywords.length];

        for(int i = 0; i < this.keywords.length;i++){
            this.keywords[i] = keywords[i].toLowerCase();
        }
    }



    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

}
