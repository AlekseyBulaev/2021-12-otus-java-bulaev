package ru.otus.atm;

public class AmountService {
    public Amount depositAmount(long amount) {
        Amount result = new Amount(0, 0, 0, 0, 0, 0);
        while (result.getBalance() != amount) {
            if (result.getBalance() > amount) {
                decrement(result, result.getBalance() - amount);
            }
            increment(result, amount - result.getBalance());
        }
        return result;
    }

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

    private void increment(Amount amount, long delta) {
        if (delta >= 5000) {
            amount.setFiveThousand(1);
        } else if (delta >= 2000) {
            amount.setTwoThousand(1);
        } else if (delta >= 1000) {
            amount.setOneThousand(1);
        } else if (delta >= 500) {
            amount.setFiveHundred(1);
        } else if (delta >= 200) {
            amount.setTwoHundred(1);
        } else if (delta >= 100) {
            amount.setOneHundred(1);
        }
    }

    private void decrement(Amount amount, long delta) {
        if (delta < 100) {
            throw new IllegalArgumentException("сумма должна быть кратна 100");
        } else if (delta < 200) {
            amount.setOneHundred(-1);
        } else if (delta < 500) {
            amount.setTwoHundred(-1);
        } else if (delta < 1000) {
            amount.setFiveHundred(-1);
        } else if (delta < 2000) {
            amount.setOneThousand(-1);
        } else if (delta < 5000) {
            amount.setTwoThousand(-1);
        }
    }


    private void increment(Amount balance, Amount amount, long delta) {
        if (balance.getFiveThousand() > 0 && delta >= 5000) {
            amount.setFiveThousand(1);
            balance.setFiveThousand(-1);
        } else if (balance.getTwoThousand() > 0 && delta >= 2000) {
            amount.setTwoThousand(1);
            balance.setTwoThousand(-1);
        } else if (balance.getOneThousand() > 0 && delta >= 1000) {
            amount.setOneThousand(1);
            balance.setOneThousand(-1);
        } else if (balance.getFiveHundred() > 0 && delta >= 500) {
            amount.setFiveHundred(1);
            balance.setFiveHundred(-1);
        } else if (balance.getTwoHundred() > 0 && delta >= 200) {
            amount.setTwoHundred(1);
            balance.setTwoHundred(-1);
        } else if (balance.getOneHundred() > 0 && delta >= 100) {
            amount.setOneHundred(1);
            balance.setOneHundred(-1);
        }
        else {
            throw new IllegalArgumentException(String.format("невозможно выдать остаток= %s", delta));
        }
    }
}
