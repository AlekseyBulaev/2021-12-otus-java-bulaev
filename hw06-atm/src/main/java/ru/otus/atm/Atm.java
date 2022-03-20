package ru.otus.atm;

public class Atm {
    private final Amount balance;
    private final MoneyService moneyService;
    private final AmountService amountService;

    public Atm(Amount balance, MoneyService moneyService, AmountService amountService) {
        this.balance = balance;
        this.moneyService = moneyService;
        this.amountService = amountService;
    }

    public long deposit(long amount) {
        Amount depositAmount = amountService.depositAmount(amount);
        if (depositAmount.isValid()) {
            return moneyService.deposit(balance, depositAmount);
        }
        throw new IllegalArgumentException("неверная сумма внесения");
    }

    public long withdraw(long amount) {
        if (amount > 0) {
            Amount withdrawAmount = amountService.withdrawAmount(balance.copyAmount(), amount);

            return moneyService.withdraw(balance, withdrawAmount);
        }
        throw new IllegalArgumentException("неверная сумма снятия");
    }

    public long getBalance() {
        return moneyService.getBalance(balance);
    }
}
