package com.example.notebookapp.command;

import java.util.ArrayList;
import java.util.List;

public class CommandDefinitions {
    public static List<CommandDefinition> getDefinitions(){
        final String HELP = "help";
        final String QUIT = "quit";

        final String EDIT = "edit";
        final String CREATE = "create";
        final String DELETE = "delete";
        final String LIST = "list";

        final String FOLDER = "folder";
        final String NOTE = "note";

        final String TITLE = "title";
        final String BODY = "body";

        final List<CommandDefinition> commands = new ArrayList<>();

        commands.add(new CommandDefinition(CommandType.HELP,HELP));
        commands.add(new CommandDefinition(CommandType.EXIT,QUIT));
        //commands.add(new CommandDefinition(CommandType.BACK,BACK));


        commands.add(new CommandDefinition(CommandType.CREATE_FOLDER,CREATE, FOLDER));
        commands.add(new CommandDefinition(CommandType.EDIT_FOLDER_TITLE,EDIT,FOLDER,TITLE));
        commands.add(new CommandDefinition(CommandType.EDIT_FOLDER_BODY,EDIT,FOLDER,BODY));
        commands.add(new CommandDefinition(CommandType.LIST_FOLDER,LIST,FOLDER));
        commands.add(new CommandDefinition(CommandType.DELETE_FOLDER,DELETE,FOLDER));


        commands.add(new CommandDefinition(CommandType.CREATE_NOTE,CREATE,NOTE));
        commands.add(new CommandDefinition(CommandType.EDIT_NOTE_TITLE,EDIT,NOTE,TITLE));
        commands.add(new CommandDefinition(CommandType.EDIT_NOTE_BODY,EDIT,NOTE,BODY));
        commands.add(new CommandDefinition(CommandType.LIST_NOTE,LIST,NOTE));
        commands.add(new CommandDefinition(CommandType.DELETE_NOTE,DELETE,NOTE));

        return List.copyOf(commands);
    }
}
