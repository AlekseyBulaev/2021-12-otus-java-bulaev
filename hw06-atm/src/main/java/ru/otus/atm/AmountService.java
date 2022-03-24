package ru.otus.atm;

import static ru.otus.atm.Bill.*;

public class AmountService {

    public Amount withdrawAmount(Amount balance, long amount) {
        if (balance.getBalance() < amount) {
            throw new IllegalArgumentException("недостаточно средств");
        }

        Amount result = new Amount(0, 0, 0, 0, 0, 0);

        while (result.getBalance() != amount) {
            if (result.getBalance() > amount) {
                decrement(result, result.getBalance() - amount);
            }
            increment(balance, result, amount - result.getBalance());
        }
        return result;
    }

    private void decrement(Amount amount, long delta) {
        if (delta < 100) {
            throw new IllegalArgumentException("сумма должна быть кратна 100");
        } else if (delta < 200) {
            amount.setBillAmount(ONE_HUNDRED,-1);
        } else if (delta < 500) {
            amount.setBillAmount(TWO_HUNDRED,-1);
        } else if (delta < 1000) {
            amount.setBillAmount(ONE_THOUSAND,-1);
        } else if (delta < 2000) {
            amount.setBillAmount(TWO_THOUSAND,-1);
        } else if (delta < 5000) {
            amount.setBillAmount(FIVE_THOUSAND,-1);
        }
    }


    private void increment(Amount balance, Amount amount, long delta) {
        if (balance.getBillAmount(FIVE_THOUSAND) > 0 && delta >= 5000) {
            amount.setBillAmount(FIVE_THOUSAND,1);
            balance.setBillAmount(FIVE_THOUSAND,-1);
        } else if (balance.getBillAmount(TWO_THOUSAND) > 0 && delta >= 2000) {
            amount.setBillAmount(TWO_THOUSAND,1);
            balance.setBillAmount(TWO_THOUSAND,-1);
        } else if (balance.getBillAmount(ONE_THOUSAND) > 0 && delta >= 1000) {
            amount.setBillAmount(ONE_THOUSAND,1);
            balance.setBillAmount(ONE_THOUSAND,-1);
        } else if (balance.getBillAmount(FIVE_HUNDRED) > 0 && delta >= 500) {
            amount.setBillAmount(FIVE_HUNDRED,1);
            balance.setBillAmount(FIVE_HUNDRED,-1);
        } else if (balance.getBillAmount(TWO_HUNDRED) > 0 && delta >= 200) {
            amount.setBillAmount(TWO_HUNDRED,1);
            balance.setBillAmount(TWO_HUNDRED,-1);
        } else if (balance.getBillAmount(ONE_HUNDRED) > 0 && delta >= 100) {
            amount.setBillAmount(ONE_HUNDRED,1);
            balance.setBillAmount(ONE_HUNDRED,-1);
        }
        else {
            throw new IllegalArgumentException(String.format("невозможно выдать остаток= %s", delta));
        }
    }
}
