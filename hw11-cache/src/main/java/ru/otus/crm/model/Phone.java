package ru.otus.crm.model;

import javax.persistence.*;
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "client_id")
    private Client client;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Phone() {
    }

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public Phone(Long id, String number, Client client) {
        this.id = id;
        this.number = number;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone )) return false;
        return id != null && id.equals(((Phone) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "number='" + number + '\'' +
                '}';
    }
}
