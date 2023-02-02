package ru.otus.hw14springboot.service;

import org.springframework.stereotype.Service;
import ru.otus.hw14springboot.dto.ClientDto;
import ru.otus.hw14springboot.dto.utils.ClientUtils;
import ru.otus.hw14springboot.model.Client;
import ru.otus.hw14springboot.repository.ClientRepository;
import ru.otus.hw14springboot.sessionmanager.TransactionManager;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientUtils clientUtils;
    private final TransactionManager transactionManager;

    public ClientServiceImpl(ClientRepository clientRepository, ClientUtils clientUtils, TransactionManager transactionManager) {
        this.clientRepository = clientRepository;
        this.clientUtils = clientUtils;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(clientUtils::fromClient).toList();
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientUtils.fromClientDto(clientDto);
        Client result = transactionManager.doInTransaction(() ->
                clientRepository.save(client)
        );
        return clientUtils.fromClient(result);
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        return clientUtils.fromClient(client);
    }

    @Override
    public void deleteClient(ClientDto client) {
        transactionManager.doInTransaction(() -> {
            clientRepository.delete(clientUtils.fromClientDto(client));
            return null;
                }
        );

    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client result = transactionManager.doInTransaction(() ->
                clientRepository.save(clientUtils.fromClientDto(clientDto))
        );
        return clientUtils.fromClient(result);
    }
}
