package com.bankapp;

public class CommandWords {
    private String[] commandWords = {"withdraw", "deposit", "view", "quit", "help", "yes", "no"};
    
    //checks if a word is a command
    public boolean isCommand(String command) {
        for(int i = 0; i < commandWords.length; i++) {
            if(commandWords[i].equalsIgnoreCase(command))
                return true;
        }
        return false;
    }

    //provides string of commands for printing
    public String toString() {
        return "\nThe commands are " + commandWords[0] + ", " + commandWords[1] + ", " + commandWords[2] + ", and " + commandWords[3];
    }
}