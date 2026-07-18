package com.example.notebookapp.parser;

import com.example.notebookapp.command.Command;
import com.example.notebookapp.command.CommandDefinition;
import com.example.notebookapp.command.CommandDefinitions;
import com.example.notebookapp.command.CommandType;

public class InputParser {

    public static Command parseCommand(String input){
        //CommandDefinition definition;
        String[] tokens = Tokenizer.parseUserInput(input);
        for(CommandDefinition definition: CommandDefinitions.getDefinitions()){
            if(matches(tokens, definition)){
                int keyWordCount = definition.getKeywords().length;
                String[] arguments = new String[tokens.length - keyWordCount];

                for(int i = 0; i < arguments.length;i++){
                    arguments[i] = tokens[i + keyWordCount];
                }
                return new Command(definition.getType(),arguments);
            }
        }
        //fall through. No matching definition will return UNKNOWN
        return new Command(CommandType.UNKNOWN,tokens);
    }
    private static boolean matches(String[] tokens,CommandDefinition definition){

        String[] keywords = definition.getKeywords();
         if(tokens.length < keywords.length){
            return false;
        }
         for(int i = 0; i < keywords.length;i++){
             if(!tokens[i].equals(keywords[i])){
                 return false;
             }
         }
        return true;
    }
}
