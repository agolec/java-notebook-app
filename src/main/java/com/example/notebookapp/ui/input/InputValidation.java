package com.example.notebookapp.ui.input;

import com.example.notebookapp.repository.NoteRepository;

import java.util.Scanner;

public class InputValidation {
    public static boolean isNotDigit(String input) {
        return Character.isDigit(input.charAt(0)) == false;
    }
    public static boolean getUserConfirmation(Scanner kb) {
        while(true){
            System.out.print("Are you sure? (y/n) > ");
            String confirmationInput = kb.nextLine().trim().toLowerCase();

            switch(confirmationInput){
                case "y","yes":
                    return true;
                case "n","no":
                    return false;
                default:
                    System.out.println("Please enter y or n for yes/no.");
            }
        }
    }
    public static boolean folderRemovedSuccessfully(String folderInput, NoteRepository repository) {
        return repository.removeFolder(repository.getFolder(Integer.parseInt(folderInput) - 1).getName());
    }
}