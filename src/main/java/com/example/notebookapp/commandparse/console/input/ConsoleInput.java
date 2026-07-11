package com.example.notebookapp.commandparse.console.input;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;
    private static ConsoleInput instance;
    private ConsoleInput(){
        this.scanner = new Scanner(System.in);
    }
    public static ConsoleInput getInstance(){
        if(instance == null){
            instance = new ConsoleInput();
        }
        return instance;
    }
    public String getLine(String prompt){
        String input;
        do{
            System.out.println(prompt);
            input = this.scanner.nextLine();

        } while(input == null || input.isEmpty());
        return input;
    }
}
