package ru.otus.atm;

import java.util.Set;

public class Atm {

    private final Set<MoneyCassette> moneyCas;

    public Atm(Set<MoneyCassette> moneyCas) {
        this.moneyCas = moneyCas;
    }

    public long deposit(){
        return 1L;
    }

    public long withdraw(long amount){
        return 1L;
    }

    public double getAmount(String id){
        return 1.0;
    }
}
