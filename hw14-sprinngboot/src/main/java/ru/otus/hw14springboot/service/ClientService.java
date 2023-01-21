package ru.otus.hw14springboot.service;

import ru.otus.hw14springboot.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client addClient();

    Client findById(Long id);
    void delete(Client client);
    Client updateClient(Long id);
}
