package ru.otus.atm.model;

public class OneHundred implements Bill {
    @Override
    public long getOrdinal() {
        return 100L;
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
        return OneHundred.class == obj.getClass();
    }

    @Override
    public int compareTo(Object o) {
        return (int) (((Bill) o).getOrdinal() - getOrdinal());
    }
}
