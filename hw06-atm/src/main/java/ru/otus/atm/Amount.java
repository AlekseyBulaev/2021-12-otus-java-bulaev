package ru.otus.atm;

import ru.otus.atm.model.Bill;

import java.util.Map;
import java.util.TreeMap;

public class Amount {
    private final Map<Bill, Long> billsAmount;

    public Amount() {
        this.billsAmount = new TreeMap<>();
    }

    public Amount(Map<Bill, Long> billsAmount) {
        this.billsAmount = billsAmount;
    }

    public Map<Bill, Long> getBillsAmount () {
        return billsAmount;
    }

    public long getBillAmount(Bill bill) {
        billsAmount.putIfAbsent(bill, 0L);
        return billsAmount.get(bill);
    }

    public void setBillAmount(Bill bill, long amount) {
        billsAmount.putIfAbsent(bill, 0L);
        var result = billsAmount.get(bill) + amount;
        billsAmount.put(bill, result);
    }

    public Amount copyAmount() {
        Amount amount = new Amount(new TreeMap<>());
        billsAmount.forEach(amount::setBillAmount);
        return amount;
    }

    public boolean isValid() {
        return (long) billsAmount.values().size() > 0;
    }

    public long getBalance() {
        return billsAmount.keySet().stream().mapToLong(x -> x.calculate(billsAmount.get(x))).sum();
    }

    void setAmount(Amount addAmount) {
        addAmount.getBillsAmount().forEach((bill, value) -> {
            billsAmount.putIfAbsent(bill, 0L);
            var result = billsAmount.get(bill) + value;
            if (result >= 0) {
                billsAmount.put(bill, result);
            } else {
                throw new IllegalArgumentException("недостаточно средств");
            }
        });
    }
}
