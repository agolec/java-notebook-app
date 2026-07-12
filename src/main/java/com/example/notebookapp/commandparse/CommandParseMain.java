package com.example.notebookapp.commandparse;

import com.example.notebookapp.commandparse.console.input.ConsoleInput;
import com.example.notebookapp.commandparse.console.input.InputParser;

import java.util.Arrays;
import java.util.List;

public class CommandParseMain {
    public static void main(String[] args){
        ConsoleInput in = ConsoleInput.getInstance();

        String input = in.getLine("Enter your string: ");
        System.out.println("You entered '" + input + "'");

        List<String> tokens = Arrays.asList(InputParser.parseUserInput(input));

        System.out.println("Your tokens are: ");
        System.out.println();
        for(String token: tokens){
            System.out.println(token);
        }
        System.out.println();
        System.out.println("end");;

    }
}
