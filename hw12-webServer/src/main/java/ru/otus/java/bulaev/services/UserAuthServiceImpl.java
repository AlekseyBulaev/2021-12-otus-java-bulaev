package ru.otus.java.bulaev.services;

public class UserAuthServiceImpl implements UserAuthService {

    private final String login = "admin";
    private final String password = "123";
    @Override
    public boolean authenticate(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }
}
