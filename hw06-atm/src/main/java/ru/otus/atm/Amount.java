package ru.otus.atm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Amount {
    private final Map<Bill, Long> billsAmount = new HashMap<>();

    public Amount() {
        Arrays.stream(Bill.values()).forEach(bill -> {
            billsAmount.put(bill, 0L);
        });
    }

    public long getBillAmount(Bill bill) {
        return billsAmount.get(bill);
    }

    public void setBillAmount(Bill bill, long amount) {
        var result = billsAmount.get(bill) + amount;
        billsAmount.put(bill, result);
    }

    public Amount copyAmount() {
        Amount amount = new Amount();
        billsAmount.forEach(amount::addBill);
        return amount;
    }

    public void addBill(Bill bill, long amount) {
        billsAmount.put(bill, amount);
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
