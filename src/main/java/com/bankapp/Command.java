package com.bankapp;

public class Command {
    private String command;
    private String word2;

    public Command(String word1, String word2) {
        command = word1;
        this.word2 = word2;
    }

    public String getCommandWord() {
        return command;
    }

    public String getSecondWord() {
        return word2;
    }

    public boolean isUnknown() {
        return (command == null);
    }

    public boolean hasSecondWord() {
        return (word2 != null);
    }
}