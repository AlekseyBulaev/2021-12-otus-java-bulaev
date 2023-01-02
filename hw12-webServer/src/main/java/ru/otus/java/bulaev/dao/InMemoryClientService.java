package ru.otus.java.bulaev.dao;

import ru.otus.java.bulaev.model.Address;
import ru.otus.java.bulaev.model.Client;
import ru.otus.java.bulaev.model.Phone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryClientService implements ClientService {

    private final Map<Long, Client> clients;

    public InMemoryClientService() {
        clients = new HashMap<>();
        clients.put(1L, new Client(1L, "Крис Гир", new Address(1L, "street1"), List.of(new Phone(1L, "123"))));
        clients.put(2L, new Client(2L, "Ая Кэш", new Address(2L, "street2"), List.of(new Phone(2L, "111"))));
        clients.put(3L, new Client(3L, "Десмин Боргес", new Address(3L, "street3"), List.of(new Phone(3L, "1123"))));
        clients.put(4L, new Client(4L, "Кетер Донохью", new Address(4L, "street4"), List.of(new Phone(4L, "1213"))));
        clients.put(5L, new Client(5L, "Стивен Шнайдер", new Address(4L, "street4"), List.of(new Phone(5L, "1223"))));
        clients.put(6L, new Client(6L, "Джанет Вэрни", new Address(5L, "street5"), List.of(new Phone(6L, "1233"))));
        clients.put(7L, new Client(7L, "Брэндон Смит", new Address(6L, "street6"), List.of(new Phone(7L, "1423"))));
    }

    @Override
    public Optional<Client> getClient(long id) {
        return Optional.ofNullable(clients.get(id));
    }

    @Override
    public Client updateClient(Client client) {
        return clients.put(client.getId(), client);
    }

    @Override
    public Client createClient(Client client) {
        return clients.put(client.getId(), client);
    }

    @Override
    public Client deleteClient(long id) {
        return clients.remove(id);
    }
}
