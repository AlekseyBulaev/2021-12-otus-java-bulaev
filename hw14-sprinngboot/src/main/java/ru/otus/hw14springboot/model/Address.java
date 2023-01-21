package ru.otus.hw14springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import static java.util.Objects.isNull;

@Table("address")
public class Address implements Persistable<Long> {
    @Id
    private Long clientId;
    private String street;
    @Transient
    @JsonIgnore
    private Boolean isInsert;
    public Address(Long clientId, String street) {
        this.clientId = clientId;
        this.street = street;
        this.isInsert = isNull(clientId);
    }
    @PersistenceCreator
    public Address( String street) {
        this(null, street);
    }

    public Long getId() {
        return clientId;
    }

    @Override
    public boolean isNew() {
        return isInsert;
    }

    public String getStreet() {
        return street;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id='" + clientId + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
