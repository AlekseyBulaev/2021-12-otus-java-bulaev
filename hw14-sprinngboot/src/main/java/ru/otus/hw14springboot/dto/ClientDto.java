package ru.otus.hw14springboot.dto;

public class ClientDto {

    private Long id;
    private String name;
    private String street;
    private String number;

    public ClientDto() {
    }

    public ClientDto(Long id, String name, String street, String number) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
