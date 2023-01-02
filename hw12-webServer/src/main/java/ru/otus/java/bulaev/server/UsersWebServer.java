package ru.otus.java.bulaev.server;

public interface UsersWebServer {
    void start() throws Exception;

    void join() throws Exception;

    void stop() throws Exception;
}
