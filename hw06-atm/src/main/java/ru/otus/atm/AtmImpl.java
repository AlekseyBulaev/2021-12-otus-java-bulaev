package ru.otus.atm;

public class AtmImpl implements Atm {
    private final Amount balance;
    private final MoneyService moneyService;
    private final AmountService amountService;

    public AtmImpl(Amount balance, MoneyService moneyService, AmountService amountService) {
        this.balance = balance;
        this.moneyService = moneyService;
        this.amountService = amountService;
    }

    @Override
    public Amount deposit(Amount amount) {
        if (amount.isValid()) {
            return moneyService.deposit(balance, amount);
        }
        throw new IllegalArgumentException("неверная сумма внесения");
    }

    @Override
    public Amount withdraw(long amount) {
        if (amount > 0) {
            Amount withdrawAmount = amountService.withdrawAmount(balance.copyAmount(), amount);

            return moneyService.withdraw(balance, withdrawAmount);
        }
        throw new IllegalArgumentException("неверная сумма снятия");
    }

    @Override
    public long getBalance() {
        return moneyService.getBalance(balance);
    }
}
