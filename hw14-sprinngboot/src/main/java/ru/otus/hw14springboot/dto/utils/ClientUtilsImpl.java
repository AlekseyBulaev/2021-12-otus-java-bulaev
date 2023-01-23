package ru.otus.hw14springboot.dto.utils;

import org.springframework.stereotype.Service;
import ru.otus.hw14springboot.dto.ClientDto;
import ru.otus.hw14springboot.model.Address;
import ru.otus.hw14springboot.model.Client;
import ru.otus.hw14springboot.model.Phone;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ClientUtilsImpl implements ClientUtils {
    @Override
    public ClientDto fromClient(Client client) {
        ClientDto result =  new ClientDto(
                client.getId(),
                client.getName(),
                null,
                null
        );
        if(nonNull(client.getAddresses())) {
            result.setStreet(client
                    .getAddresses()
                    .stream()
                    .map(Address::getStreet)
                    .collect(Collectors.joining(",")));
        }
        if(nonNull(client.getPhones())) {
            result.setNumber(client
                    .getPhones()
                    .stream()
                    .map(Phone::getNumber)
                    .collect(Collectors.joining(",")));
        }
        return result;
    }

    @Override
    public Client fromClientDto(ClientDto clientDto) {
        Set<Address> addresses= null;
        Set<Phone> phones= null;
        if(!clientDto.getStreet().isBlank()) {
            addresses = Arrays.stream(clientDto.getStreet().split(",")).map(Address::new).collect(Collectors.toSet());
        }
        if(!clientDto.getNumber().isBlank()) {
            phones = Arrays.stream(clientDto.getNumber().split(",")).map(Phone::new).collect(Collectors.toSet());
        }
        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                addresses,
                phones
        );
    }
}
