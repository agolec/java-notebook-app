package com.example.notebookapp.commandparse;

import com.example.notebookapp.command.CommandExecution;
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

        while(true){
            String input = conIn.getLine("Enter your string: ");

            CommandType type = InputParser.parseCommand(input);

            System.out.println("your command type is: " + type.name());

            CommandExecution.execute(type);

        }
    }
}
