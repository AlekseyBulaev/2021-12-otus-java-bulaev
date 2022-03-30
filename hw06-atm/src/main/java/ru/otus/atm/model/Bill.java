package ru.otus.atm.model;

public interface Bill extends Comparable {
    long getOrdinal();
    long calculate(long amount);
}
