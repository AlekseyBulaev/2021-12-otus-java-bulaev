package ru.otus.java.bulaev.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
