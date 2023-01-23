package ru.otus.hw14springboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table(name = "client")
public class Client {

    @Id
    private final Long id;

    private final String name;

    @MappedCollection(idColumn = "client_id")
    private final Set<Address> addresses;

    @MappedCollection(idColumn = "client_id")
    private final Set<Phone> phones;

    @PersistenceCreator
    public Client(Long id, String name,Set<Address> addresses, Set<Phone> phones) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Set<Phone> getPhones() {
        return phones;
    }
}
