package ru.otus.atm;

public interface Atm {
    Amount deposit(Amount amount);

    Amount withdraw(long amount);

    long getBalance();
}
