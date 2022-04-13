package ru.otus.atm;

import ru.otus.atm.model.Bill;

import java.util.Map;
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
        for (Map.Entry<Bill, Long> entry : balance.getBillsAmount().entrySet()) {
            while (entry.getValue() > 0 && delta >= entry.getKey().getOrdinal()) {
                entry.setValue(entry.getValue()-1);
                delta -= entry.getKey().getOrdinal();
                amount.setBillAmount(entry.getKey(), 1);
            }
        }
        balance.getBillsAmount().forEach((bill, value) -> {
        });
        if (delta != 0) {
            throw new IllegalArgumentException(String.format("невозможно выдать остаток= %s", delta));
        }

    }
}
