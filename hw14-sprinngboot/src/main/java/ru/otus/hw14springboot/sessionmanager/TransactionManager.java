package ru.otus.hw14springboot.sessionmanager;

public interface TransactionManager {

    <T> T doInTransaction(TransactionAction<T> action);
}
