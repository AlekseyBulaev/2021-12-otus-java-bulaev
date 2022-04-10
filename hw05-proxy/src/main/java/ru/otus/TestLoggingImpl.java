package ru.otus;

import ru.otus.model.Log;

public class TestLoggingImpl implements TestLogging {
    @Override
    public void calculation(int param) {}
    @Override
    public void calculation(int param, String param2) {}
    @Override
    public void calculation(int param, int param2) {}
    @Override
    public void calculation() {}
}
