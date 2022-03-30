package ru.otus.atm.model;

public class TwoHundred implements Bill {
    @Override
    public long getOrdinal() {
        return 200L;
    }

    @Override
    public long calculate(long amount) {
        return amount * getOrdinal();
    }

    @Override
    public int hashCode() {
        return (int) getOrdinal();
    }

    @Override
    public boolean equals(Object obj) {
        return TwoHundred.class == obj.getClass();
    }

    @Override
    public int compareTo(Object o) {
        return (int) (getOrdinal() - ((Bill) o).getOrdinal());
    }
}
