package ru.otus.hw14springboot.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import ru.otus.hw14springboot.model.Address;
import ru.otus.hw14springboot.model.Client;
import ru.otus.hw14springboot.model.Phone;
import ru.otus.hw14springboot.repository.ClientRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return StreamSupport
                .stream(clientRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public Client addClient() {
        return clientRepository.save(randomClient(null));
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public Client updateClient(Long id) {
        return clientRepository.save(randomClient(id));
    }

    private Client randomClient(Long id) {
        Faker faker = new Faker();
        var name = faker.name().fullName();
        var phone = faker.phoneNumber().phoneNumber();
        var address = faker.address().fullAddress();
        return new Client(id, name, new Address(id, address), new Phone(id, phone));
    }
}
