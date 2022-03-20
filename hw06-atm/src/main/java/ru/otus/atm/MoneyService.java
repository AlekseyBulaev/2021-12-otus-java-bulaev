package ru.otus.atm;

public class MoneyService {

    public long deposit(Amount balance, Amount amount) {
        balance.setFiveThousand(amount.getFiveThousand());
        balance.setTwoThousand(amount.getTwoThousand());
        balance.setOneThousand(amount.getOneThousand());
        balance.setFiveHundred(amount.getFiveHundred());
        balance.setTwoHundred(amount.getTwoHundred());
        balance.setOneHundred(amount.getOneHundred());
        return balance.getBalance();
    }

    public long withdraw(Amount balance, Amount amount) {
        balance.setFiveThousand(-amount.getFiveThousand());
        balance.setTwoThousand(-amount.getTwoThousand());
        balance.setOneThousand(-amount.getOneThousand());
        balance.setFiveHundred(-amount.getFiveHundred());
        balance.setTwoHundred(-amount.getTwoHundred());
        balance.setOneHundred(-amount.getOneHundred());
        return balance.getBalance();
    }

    public long getBalance(Amount balance) {
        return balance.getBalance();
    }
}
