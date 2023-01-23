package ru.otus.hw14springboot.service;

import ru.otus.hw14springboot.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();
    ClientDto addClient(ClientDto client);

    ClientDto findById(Long id);
    void deleteClient(ClientDto client);
    ClientDto updateClient(ClientDto client);
}
