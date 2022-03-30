package ru.otus.atm.model;

public class OneThousand implements Bill {
    @Override
    public long getOrdinal() {
        return 1_000L;
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
        return OneThousand.class == obj.getClass();
    }

    @Override
    public int compareTo(Object o) {
        return (int) (((Bill) o).getOrdinal() - getOrdinal());
    }
}
