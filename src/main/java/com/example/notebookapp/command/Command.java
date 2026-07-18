package com.example.notebookapp.command;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    private CommandType commandType;

    private final List<String> arguments;

    public Command(CommandType commandType, String... tokens){
        this.commandType = commandType;
        this.arguments = List.copyOf(Arrays.asList(tokens));
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }
    public String[] getArguments() {
        return arguments.toArray(new String[0]);
    }

}
