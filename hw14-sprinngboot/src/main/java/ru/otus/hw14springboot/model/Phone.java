package ru.otus.hw14springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import static java.util.Objects.isNull;

@Table("phone")
public class Phone implements Persistable<Long> {
    @Id
    private Long clientId;

    private String num;
    @Transient
    @JsonIgnore
    private Boolean isInsert;

    @Override
    public Long getId() {
        return clientId;
    }

    @Override
    public boolean isNew() {
        return isInsert;
    }

    @PersistenceCreator
    public Phone(Long clientId, String num) {
        this.clientId = clientId;
        this.num = num;
        this.isInsert = isNull(clientId);
    }

    public Phone(String num) {
        this(null, num);
    }

    public Long getClientId() {
        return clientId;
    }

    public String getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone )) return false;
        return clientId != null && clientId.equals(((Phone) o).getClientId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Phone{" +
                "clientId=" + clientId +
                ", num='" + num + '\'' +
                '}';
    }
}
