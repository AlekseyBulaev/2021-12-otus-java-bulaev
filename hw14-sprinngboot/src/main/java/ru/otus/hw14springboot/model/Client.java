package ru.otus.hw14springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.domain.Persistable;

import static java.util.Objects.isNull;

@Table("client")
public class Client implements Persistable<Long> {
    @Id
    private Long id;

    private String name;
    @MappedCollection(idColumn = "client_id")
    private Address address;
    @MappedCollection(idColumn = "client_id")
    private Phone phone;

    @Transient
    @JsonIgnore
    private Boolean isInsert;

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(Long id, String name, Address address, Phone phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isInsert = isNull(id);
    }
    public Client(String name, Address address, Phone phone) {
        this(null, name, address, phone);
    }
    @Override
    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isInsert;
    }

    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public Phone getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone=" + phone +
                '}';
    }
}
