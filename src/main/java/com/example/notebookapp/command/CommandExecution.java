package com.example.notebookapp.command;

import com.example.notebookapp.HelpText;

public class CommandExecution {
    public static void execute(CommandType commandType){
        switch(commandType){
            case UNKNOWN -> System.out.println("Unknown command. Type 'help' for help.");
            case HELP -> System.out.println((HelpText.printHelpMenu()));
            case EXIT -> System.exit(0);
        }
    }
}
