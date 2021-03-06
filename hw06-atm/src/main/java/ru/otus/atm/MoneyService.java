package ru.otus.atm;

public class MoneyService {

    public Amount deposit(Amount balance, Amount amount) {
        balance.setAmount(amount);
        return balance;
    }

    public Amount withdraw(Amount balance, Amount amount) {
       balance.getBillsAmount().forEach((bill, value) ->
               balance.setBillAmount(bill, -amount.getBillAmount(bill)));
        return balance;
    }

    public long getBalance(Amount balance) {
        return balance.getBalance();
    }
}
