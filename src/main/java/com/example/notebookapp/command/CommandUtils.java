package com.example.notebookapp.command;

public class CommandUtils {
    public static String getFirstArgument(Command command, String errorMessage){
        String argumentString;
        try{
            argumentString = command.getArguments()[0];
        } catch(ArrayIndexOutOfBoundsException e){
            if(errorMessage != null){
                System.out.println(errorMessage);
            }
            return "";
        }
        return argumentString;
    }
}
