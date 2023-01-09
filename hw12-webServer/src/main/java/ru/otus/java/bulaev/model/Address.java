package ru.otus.java.bulaev.model;

public class Address {

    private Long id;

    private String street;
    public Address(Long id, String street) {
        this.id = id;
        this.street = street;
    }

    @Override
    public String toString() {
        return "{" +
                "street='" + street + '\'' +
                '}';
    }
}
