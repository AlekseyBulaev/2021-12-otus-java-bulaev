package ru.otus.atm;

import java.util.concurrent.atomic.AtomicLong;

public class AmountService {

    public Amount withdrawAmount(Amount balance, long amount) {
        if (balance.getBalance() < amount) {
            throw new IllegalArgumentException("недостаточно средств");
        }

        Amount result = new Amount();

        increment(balance, result, amount - result.getBalance());
        return result;
    }

    private void increment(Amount balance, Amount amount, long delta) {
        AtomicLong finalDelta = new AtomicLong(delta);
            balance.getBillsAmount().forEach((bill, value) -> {
                while (value > 0 && finalDelta.get() >= bill.getOrdinal()) {
                    value--;
                    finalDelta.addAndGet(-bill.getOrdinal());
                    amount.setBillAmount(bill, 1);
                }
            });
            if(finalDelta.get() != 0) {
                throw new IllegalArgumentException(String.format("невозможно выдать остаток= %s", delta));
            }

    }
}
