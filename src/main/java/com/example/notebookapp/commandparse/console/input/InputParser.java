package com.example.notebookapp.commandparse.console.input;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static String[] parseUserInput(String input) {

        boolean insideQuotes = false;   // boolean to check whether the input is inside doublequotes
        final char DOUBLE_QUOTE = '"';
        final char SPACE = ' ';

        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        //transform the input parameter into a character array.
        for(char character: input.toCharArray()){
            //if the character is a ", switch the boolean value
            if(character == DOUBLE_QUOTE){
                if(insideQuotes){
                    insideQuotes = false;
                } else {
                    insideQuotes = true;
                }
            }
            //if the character is a SPACE, and we are NOT inside of a
            // double quoted area of a string, and that currentToken
            // is of a length greater than 0, ADD to the tokens arrayList.
            else if(character == SPACE && !insideQuotes){
                if(currentToken.length() > 0){
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
            }
            //if the current character is neither " nor an empty space,
            // append it to the currentToken stringbuilder.
            else {
                currentToken.append(character);
            }
        }

        if(currentToken.length() > 0){
            tokens.add(currentToken.toString());
        }

        return tokens.toArray(new String[0]);
    }
}
