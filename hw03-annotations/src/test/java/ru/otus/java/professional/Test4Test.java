package ru.otus.java.professional;

import ru.otus.java.professional.annotations.After;
import ru.otus.java.professional.annotations.Before;
import ru.otus.java.professional.annotations.Test;

class Test4Test {
    @Before public void init() {}
    @Test public void test() { throw new RuntimeException("Test Exception"); }
    @After public void after() {}
}
