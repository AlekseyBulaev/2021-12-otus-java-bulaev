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

//    private void decrement(Amount amount, long delta) {
//        if (delta < 100) {
//            throw new IllegalArgumentException("сумма должна быть кратна 100");
//        }
//        amount.getBillsAmount().forEach((bill, value) -> {
//            if(delta < bill.getOrdinal()) {
//                amount.setBillAmount(bill,-1);
//                return;
//            }
//        });
//    }


    private void increment(Amount balance, Amount amount, long delta) {
        AtomicLong finalDelta = new AtomicLong(delta);
            balance.getBillsAmount().entrySet().stream()
                    .forEach(entry-> {
                var value = entry.getValue();
                var bill = entry.getKey();
                while( value > 0 && finalDelta.longValue() > bill.getOrdinal()) {

                    value--;
                    finalDelta.getAndAdd(-bill.getOrdinal());
                    amount.setBillAmount(bill, 1);
                }
            });

            throw new IllegalArgumentException(String.format("невозможно выдать остаток= %s", delta));

    }
}
