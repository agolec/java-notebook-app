package com.example.notebookapp.commandparse;

import com.example.notebookapp.commandparse.console.input.ConsoleInput;
import com.example.notebookapp.parser.Tokenizer;
import com.example.notebookapp.command.CommandType;
import com.example.notebookapp.parser.InputParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandParseMain {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ConsoleInput conIn = new ConsoleInput(in);
        //InputParser parseMeDaddy = new InputParser();

        String input = conIn.getLine("Enter your string: ");
        System.out.println("You entered '" + input + "'");

        List<String> tokens = Arrays.asList(Tokenizer.parseUserInput(input));

        System.out.println("Your tokens are: ");
        System.out.println();
        for(String token: tokens){
            System.out.println(token);
        }

        CommandType type = InputParser.parseCommand(input);

        System.out.println("your command type is: " + type.name());

        System.out.println();
        System.out.println("end");





    }
}
