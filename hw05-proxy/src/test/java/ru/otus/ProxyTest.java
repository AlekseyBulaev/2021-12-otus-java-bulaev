package ru.otus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ProxyTest {

    @Test
    public void test() {
        TestLogging test = LogProxy.createTestLogging(new TestLoggingImpl());

        assertDoesNotThrow(() ->  test.calculation());
        assertDoesNotThrow(() ->  test.calculation(1, 1));
        assertDoesNotThrow(() ->  test.calculation(1));
        assertDoesNotThrow(() ->  test.calculation(1, "123"));
    }
}
