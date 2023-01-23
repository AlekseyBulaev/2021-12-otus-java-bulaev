package ru.otus.hw14springboot.dto.utils;

import ru.otus.hw14springboot.dto.ClientDto;
import ru.otus.hw14springboot.model.Client;

public interface ClientUtils {
    ClientDto fromClient(Client client);
    Client fromClientDto(ClientDto clientDto);
}
