package com.bankapp;

import static org.junit.Assert.*;

import org.junit.Test;
import com.bankapp.*;

public class BankTest {
    AccountManage accountManager = new AccountManage();

    @Test
    public final void withDrawTest() {
        double balance  = accountManager.withdraw("1000", "500");
        assertEquals(500.0, balance, 0.0001);
    }

    @Test
    public final void depositTest() {
        double balance = accountManager.withdraw("500","0");
        assertEquals(500.0, balance, 0.0001);
        balance = accountManager.withdraw("100", "250");
        assertEquals(100.0, balance, 0.0001);
        balance = accountManager.withdraw("1000.50", "0.25");
        assertEquals(1000.25, balance, 0.0001);

    }
}