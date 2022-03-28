package ru.otus;

import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    public void test() {
        TestLogging test = LogProxy.createTestLogging();
        test.calculation(1);
        test.calculation(1, "123");
        test.calculation(1, 1);
    }
}
