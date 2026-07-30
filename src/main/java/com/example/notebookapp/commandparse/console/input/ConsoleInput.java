package com.example.notebookapp.commandparse.console.input;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;
    public ConsoleInput(Scanner scan){
        this.scanner = scan;
    }

    public String getLine(String prompt){
        String input;
        do{
            System.out.println(prompt);
            input = this.scanner.nextLine();

        } while(input == null || input.isBlank());
        return input;
    }
    public String getLinePrint(String prompt){
        String input;
        do{
            System.out.print(prompt);
            input = this.scanner.nextLine();

        } while(input == null || input.isBlank());
        return input;
    }
    public String getMultiLineUntil(String prompt, String terminator){
        final String END = terminator;
        StringBuilder sb = new StringBuilder();

        System.out.println(prompt);
        String line;
        while(!(line = this.scanner.nextLine()).equals(END)){
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}