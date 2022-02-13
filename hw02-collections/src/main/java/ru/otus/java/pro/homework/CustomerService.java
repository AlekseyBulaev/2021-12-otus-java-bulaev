package ru.otus.java.pro.homework;


import java.util.*;

import static java.util.Objects.isNull;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> customersEntry = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> smallestEntry = customersEntry.firstEntry();
        if (isNull(smallestEntry)) {
            return null;
        }
        Customer smallest = smallestEntry.getKey();
        return new AbstractMap.SimpleEntry<>(new Customer(smallest.getId(), smallest.getName(), smallest.getScores()), smallestEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        Map.Entry<Customer, String> nextEntry = customersEntry.higherEntry(customer);
        if (isNull(nextEntry)) {
            return null;
        }
        Customer nextCustomer = nextEntry.getKey();
        return new AbstractMap.SimpleEntry<>(new Customer(nextCustomer.getId(), nextCustomer.getName(), nextCustomer.getScores()), nextEntry.getValue());
    }

    public void add(final Customer customer, String data) {
        customersEntry.putIfAbsent(customer, data);
    }
}
