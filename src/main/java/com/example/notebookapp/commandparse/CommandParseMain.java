package com.example.notebookapp.commandparse;

import com.example.notebookapp.commandparse.console.input.ConsoleInput;

public class CommandParseMain {
    public static void main(String[] args){
        ConsoleInput in = ConsoleInput.getInstance();

        String input = in.getLine("Enter your string: ");
        System.out.println("You entered '" + input + "'");

    }
}
