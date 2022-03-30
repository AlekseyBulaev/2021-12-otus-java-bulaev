package ru.otus.atm;

import ru.otus.atm.model.Bill;

import java.util.Arrays;

public class MoneyService {

    public Amount deposit(Amount balance, Amount amount) {
        balance.setAmount(amount);
        return balance;
    }

    public Amount withdraw(Amount balance, Amount amount) {
       balance.getBillsAmount().forEach((bill, value) -> {
            balance.setBillAmount(bill, -amount.getBillAmount(bill)*2);
        });
        return balance;
    }

    public long getBalance(Amount balance) {
        return balance.getBalance();
    }
}
