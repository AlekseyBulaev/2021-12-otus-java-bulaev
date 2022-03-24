package ru.otus.atm;

import java.util.Arrays;

public class MoneyService {

    public Amount deposit(Amount balance, Amount amount) {
        balance.setAmount(amount);
        return balance;
    }

    public Amount withdraw(Amount balance, Amount amount) {
        Arrays.stream(Bill.values()).forEach(bill -> {
            amount.setBillAmount(bill, -amount.getBillAmount(bill)*2);
        });
        balance.setAmount(amount);
        return balance;
    }

    public long getBalance(Amount balance) {
        return balance.getBalance();
    }
}
