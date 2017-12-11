package com.bankapp;

import java.util.Scanner;

public class Parser {
    private CommandWords commands;
    private Scanner scanner;

    public Parser() {
        commands = new CommandWords();
        scanner = new Scanner(System.in);
    }
   
    public Command parseString(int option) {
        String input;
        String word1 = null;
        String word2 = null;

        if(option == 0) {
            System.out.println("Enter Account Name");
            System.out.print(">> ");
        } else if (option == 1) {
            System.out.println("Account Not Found!");
            System.out.println("Create new account?");
            System.out.print(">> ");
        } else if (option == 2) {
            System.out.println("Select account option by typing [option] [value]");
            System.out.print(">> ");
        } else if (option == 3) {
            System.out.println("Would you like to make another transaction?");
            System.out.print(">> ");
        }

        input = scanner.nextLine();  

        Scanner tokenizer = new Scanner(input);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if(option == 0) {
            return new Command(word1, word2);
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }       
    }
}