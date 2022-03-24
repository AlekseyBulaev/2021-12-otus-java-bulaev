package ru.otus.atm;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.atm.Bill.*;

public class Amount {
    private Map<Bill, Long> billsAmount;

    public Amount(long fiveThousand, long twoThousand, long oneThousand, long fiveHundred, long twoHundred, long oneHundred) {
        billsAmount = new HashMap<Bill, Long>();
        billsAmount.put(FIVE_THOUSAND, fiveThousand);
        billsAmount.put(TWO_THOUSAND, twoThousand);
        billsAmount.put(ONE_THOUSAND, oneThousand);
        billsAmount.put(FIVE_HUNDRED, fiveHundred);
        billsAmount.put(TWO_HUNDRED, twoHundred);
        billsAmount.put(ONE_HUNDRED, oneHundred);
    }

    public long getBillAmount(Bill bill) {
        return billsAmount.get(bill);
    }

    public void setBillAmount(Bill bill, long amount) {
        var result = billsAmount.get(bill) + amount;
        billsAmount.put(bill, result);
    }

    public Amount copyAmount() {
        return new Amount(
                billsAmount.get(FIVE_THOUSAND),
                billsAmount.get(TWO_THOUSAND),
                billsAmount.get(ONE_THOUSAND),
                billsAmount.get(FIVE_HUNDRED),
                billsAmount.get(TWO_HUNDRED),
                billsAmount.get(ONE_HUNDRED)
        );
    }

    public boolean isValid() {
        return (long) billsAmount.values().size() > 0;
    }

    public long getBalance() {
        return billsAmount.keySet().stream().mapToLong(x -> x.getCount() * billsAmount.get(x)).sum();
    }

    boolean setAmount(Amount addAmount) {
        billsAmount.keySet().forEach(bill -> {
            var result = billsAmount.get(bill) + addAmount.getBillAmount(bill);
            if (result >= 0) {
                billsAmount.put(bill, result);
            } else {
                throw new IllegalArgumentException("недостаточно средств");
            }
        });
        return true;
    }
}
