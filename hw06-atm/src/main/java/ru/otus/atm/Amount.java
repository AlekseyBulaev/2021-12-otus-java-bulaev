package ru.otus.atm;

public class Amount {
    private long FIVE_THOUSAND;
    private long TWO_THOUSAND;
    private long ONE_THOUSAND;
    private long FIVE_HUNDRED;
    private long TWO_HUNDRED;
    private long ONE_HUNDRED;

    public Amount(long FIVE_THOUSAND, long TWO_THOUSAND, long ONE_THOUSAND, long FIVE_HUNDRED, long TWO_HUNDRED, long ONE_HUNDRED) {
        this.FIVE_THOUSAND = FIVE_THOUSAND;
        this.TWO_THOUSAND = TWO_THOUSAND;
        this.ONE_THOUSAND = ONE_THOUSAND;
        this.FIVE_HUNDRED = FIVE_HUNDRED;
        this.TWO_HUNDRED = TWO_HUNDRED;
        this.ONE_HUNDRED = ONE_HUNDRED;
    }

    public Amount copyAmount() {
        return new Amount(
                FIVE_THOUSAND,
                TWO_THOUSAND,
                ONE_THOUSAND,
                FIVE_HUNDRED,
                TWO_HUNDRED,
                ONE_HUNDRED
        );
    }

    public long getFiveThousand() {
        return FIVE_THOUSAND;
    }

    public long getTwoThousand() {
        return TWO_THOUSAND;
    }

    public long getOneThousand() {
        return ONE_THOUSAND;
    }

    public long getFiveHundred() {
        return FIVE_HUNDRED;
    }

    public long getTwoHundred() {
        return TWO_HUNDRED;
    }

    public long getOneHundred() {
        return ONE_HUNDRED;
    }

    public long setFiveThousand(long amount) {
        FIVE_THOUSAND = setAmount(FIVE_THOUSAND, amount);
        return FIVE_THOUSAND;
    }

    public long setTwoThousand(long amount) {
        TWO_THOUSAND = setAmount(TWO_THOUSAND, amount);
        return TWO_THOUSAND;
    }

    public long setOneThousand(long amount) {
        ONE_THOUSAND = setAmount(ONE_THOUSAND, amount);
        return ONE_THOUSAND;
    }

    public long setFiveHundred(long amount) {
        FIVE_HUNDRED = setAmount(FIVE_HUNDRED, amount);
        return FIVE_HUNDRED;
    }

    public long setTwoHundred(long amount) {
        TWO_HUNDRED = setAmount(TWO_HUNDRED, amount);
        return TWO_HUNDRED;
    }

    public long setOneHundred(long amount) {
        ONE_HUNDRED = setAmount(ONE_HUNDRED, amount);
        return ONE_HUNDRED;
    }

    public boolean isValid() {
        return FIVE_THOUSAND + TWO_THOUSAND + ONE_HUNDRED + FIVE_HUNDRED + TWO_HUNDRED + ONE_HUNDRED > 0;
    }

    public long getBalance() {
        return FIVE_THOUSAND * 5000 +
                TWO_THOUSAND * 2000 +
                ONE_THOUSAND * 1000 +
                FIVE_HUNDRED * 500 +
                TWO_HUNDRED * 200 +
                ONE_HUNDRED * 100;
    }

    private long setAmount(long amount, long newAmount) {
        long result = amount + newAmount;
        if (result >= 0) {
            return amount + newAmount;
        }
        throw new IllegalArgumentException("недостаточно средств");
    }
}
