package com.example.notebookapp.commandparse;

import com.example.notebookapp.command.CommandExecution;
import com.example.notebookapp.command.Command;
import com.example.notebookapp.command.CommandType;
import com.example.notebookapp.command.ExecutionResult;
import com.example.notebookapp.commandparse.console.input.ConsoleInput;
import com.example.notebookapp.parser.InputParser;
import com.example.notebookapp.persistence.ApplicationContext;
import com.example.notebookapp.persistence.RepositoryStorage;
import com.example.notebookapp.repository.NoteRepository;

import java.util.Scanner;

public class CommandParseMain {
    public static void main(String[] args){

        RepositoryStorage storage = new RepositoryStorage();
        NoteRepository repository = storage.load("notebook");

        if(repository == null){
            repository = new NoteRepository();
        }
        ApplicationContext context = new ApplicationContext(repository);
        Scanner in = new Scanner(System.in);
        ConsoleInput conIn = new ConsoleInput(in);

        ExecutionResult executionResult;

        while(true){
            String input = conIn.getLinePrint("> ");

            Command command = InputParser.parseCommand(input);


            executionResult = CommandExecution.execute(command,context);

            if (exitOptionSelected(executionResult, storage, repository)) break;
        }
    }

    private static boolean exitOptionSelected(ExecutionResult executionResult, RepositoryStorage storage, NoteRepository repository) {
        if(executionResult == ExecutionResult.EXIT){
            storage.save(repository,"notebook");
            return true;
        }
        return false;
    }
}
