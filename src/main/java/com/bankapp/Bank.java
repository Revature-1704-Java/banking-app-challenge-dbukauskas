package com.bankapp;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Bank {
    private boolean finished = false;
    private Parser parser = new Parser();
    private String accountName = "";
    private String accountInfo = "";
    private AccountManage accountManager = new AccountManage();
    private String pathName = "";
    private boolean selectedOption = false;

    public static void main(String args[]) {
        Bank bank = new Bank();
        bank.processRequest();
    }

    private void processRequest() {
        while (accountName.equalsIgnoreCase("") && !accountName.equalsIgnoreCase("quit")){
            Command command = parser.parseString(0);
            accountName = getAccountName(command);
        }
        if(!accountName.equalsIgnoreCase("quit")) {
            getBalance();
            while(!finished) {
                Command option = parser.parseString(2);
                finished = checkCommand(option);
            }
        }
        System.out.println("Have a nice day!");
    }

    private boolean checkCommand(Command command) {
        boolean wantToQuit = false;
        CommandWords commandWords = new CommandWords();
        double newBalance;

        if(command.isUnknown()) {
            System.out.println("\nI'm sorry I don't understand that command");
            System.out.println("For a list of commands type 'help' ");
            return false;
        }

        String cmdWord = command.getCommandWord();
        if(cmdWord.equals("help")) {
            System.out.println(commandWords.toString());
        } else if(cmdWord.equalsIgnoreCase("withdraw")) {
            if(command.hasSecondWord()) {
                //System.out.println(command.getSecondWord());
                newBalance = accountManager.withdraw(accountInfo, command.getSecondWord());
                System.out.println("Your new balance is $" + newBalance);
                wantToQuit = writeToAccount(newBalance);
                return wantToQuit;
            } else {
                System.out.println("Please enter an amount to withdraw");
                return false;
            }
        } else if (cmdWord.equalsIgnoreCase("deposit")) {
            if(command.hasSecondWord()) {
                newBalance = accountManager.deposit(accountInfo, command.getSecondWord());
                System.out.println("Your new balance is $" + newBalance);
                wantToQuit = writeToAccount(newBalance);
                return wantToQuit;
            } else {
                System.out.println("Please enter an amount to deposit");
                return false;
            }
        } else if (cmdWord.equalsIgnoreCase("quit")) {
            wantToQuit = quit(command);
        } else if (cmdWord.equalsIgnoreCase("yes")) {
            return false;
        } else if (cmdWord.equalsIgnoreCase("no")) {
            return false;
        }
        return wantToQuit;
    }

    private boolean quit(Command command) {
        return true;
    }

    private String getAccountName(Command command) {
        boolean newAccount = false;
        String name = command.getCommandWord();
        if(name.equalsIgnoreCase("quit")) {
            finished = true;
            return name;
        }
        String fileName = name + ".txt";
        pathName = "C:\\Users\\lefty\\Core\\BankApp\\src\\main\\java\\com\\bankApp\\AccountInfo\\" + fileName;

        int accountOption = checkIfExists(pathName);
        if(accountOption == 0) {
            System.out.println("New Account Created");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
                writer.write("1000.00");
                writer.close();
            } catch(IOException e) {
                System.out.println("Error writing initial funds");
            }
            return name;
        } else if(accountOption == 1) {
            System.out.println("Did not create or find account");
            return "";
        } else if(accountOption == 2) {
            System.out.println("Found your account");
            return name;
        } else {
            System.out.println("Something went wrong");
            return "";
        }
    }

    private void getBalance() {
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(pathName);
            bufferedReader = new BufferedReader(fileReader); 
            while((accountInfo = bufferedReader.readLine()) != null) {
                System.out.println("Displaying " + accountName + "'s Account Details");
                System.out.println("Account Balance: $" + accountInfo);
                break;
            }
        } catch(FileNotFoundException e) {
            System.out.println("Could not find account file");
        } catch(IOException e) {
            System.out.println("Error reading from file");
        }
    }

    private int checkIfExists(String pathName) {
        File file = new File(pathName);
        boolean exists = false;
        if(!file.exists()) {
            Command option = parser.parseString(1);
            String optionSelect = option.getCommandWord();
            if(optionSelect.equalsIgnoreCase("yes")) {
                try {
                exists = file.createNewFile();
                } catch(IOException e){
                    System.out.println("Could not create file");
                }
                return 0;
            } 
            return 1;
        }
        return 2;
    }

    private boolean writeToAccount(double balance) {
        String currentBalance = Double.toString(balance);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
            writer.write(currentBalance);
            writer.close();
        } catch(IOException e) {
            System.out.println("Error writing new balance");
        }

        selectedOption = false;
        Command wantToContinue = null;
        while(!selectedOption) {
            wantToContinue = parser.parseString(3);
            selectedOption = checkContinue(wantToContinue);
        }
        if(wantToContinue.getCommandWord().equalsIgnoreCase("yes")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkContinue(Command command) {
        boolean wantToQuit = false;
        CommandWords commandWords = new CommandWords();
        double newBalance;

        if(command.isUnknown()) {
            System.out.println("\nI'm sorry I don't understand that command");
            System.out.println("For a list of commands type 'help' ");
            return false;
        }

        String cmdWord = command.getCommandWord();
        if (cmdWord.equalsIgnoreCase("yes")) {
            return true;
        } else if (cmdWord.equalsIgnoreCase("no")) {
            return true;
        }
        return false;
    }
}