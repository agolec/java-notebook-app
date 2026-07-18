package com.example.notebookapp;

public class HelpText {
    public static String printHelpMenu(){
        return "======= Commands =======\n" +
                "Available Commands:\n" +
                "Folders\n" +
                "\tcreate folder <name>\n" +
                "\tlist folders\n" +
                "\topen folder <name>\n" +
                "\tdelete folder <name>\n" +
                "\n" +
                "Notes\n"+
                "       create note <name>\n" +
                "       list notes\n" +
                "       view note <title>\n" +
                "\n" +
                "General\n" +
                "\thelp\n" +
                "\tquit\n";
    }
}
