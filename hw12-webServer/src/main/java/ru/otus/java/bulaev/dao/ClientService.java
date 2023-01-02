package ru.otus.java.bulaev.dao;

import ru.otus.java.bulaev.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client updateClient(ru.otus.java.bulaev.model.Client client);

    Optional<Client> getClient(long id);

    Client createClient(Client client);

    Client deleteClient(long id);
}