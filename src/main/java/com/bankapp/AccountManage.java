package com.bankapp;

public class AccountManage {
    double accountValue;
    double changeAmount;

    public double withdraw(String account, String amount) {
        accountValue = Double.parseDouble(account);
        changeAmount = Double.parseDouble(amount);
        if(accountValue < changeAmount) {
            System.out.println("Insufficient funds");
            return accountValue;
        } else {
            return accountValue - changeAmount;
        }
    }

    public double deposit(String account, String amount) {
        accountValue = Double.parseDouble(account);
        changeAmount = Double.parseDouble(amount);
        return accountValue + changeAmount;
    }
}