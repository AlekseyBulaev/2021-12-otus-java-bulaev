package ru.otus.atm.model;

public class Nominal implements Bill {

    private final long nominal;

    public Nominal(long nominal) {
        this.nominal = nominal;
    }
    @Override
    public long getOrdinal() {
        return nominal;
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
        return nominal == ((Nominal) obj).getOrdinal();
    }

    @Override
    public int compareTo(Object o) {
        return (int) (getOrdinal() - ((Bill) o).getOrdinal());
    }
}
