package com.example.notebookapp.command;

public class CommandUtils {
    public static String getFirstArgument(Command command, String errorMessage){
        String argumentString;
        try{
            argumentString = command.getArguments()[0];
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(errorMessage);
            return null;
        }
        return argumentString;
    }
}
