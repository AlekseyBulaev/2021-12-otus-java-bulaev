package ru.otus.hw14springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw14springboot.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {}
