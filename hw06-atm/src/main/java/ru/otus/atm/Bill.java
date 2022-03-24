package ru.otus.atm;

public enum Bill {
    FIVE_THOUSAND(5000),
    TWO_THOUSAND(2000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    ONE_HUNDRED(100);

    private long count;

    Bill(long count) {
        this.count = count;
    }

    public long getCount() {
        return this.count;
    }
}
