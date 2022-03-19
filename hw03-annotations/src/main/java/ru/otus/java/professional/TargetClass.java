package ru.otus.java.professional;

import ru.otus.java.professional.annotations.After;
import ru.otus.java.professional.annotations.Before;
import ru.otus.java.professional.annotations.Test;

public class TargetClass {

    @Before public void init1() {}
    @Before public void init2() {}
    @Test public void testMethod1() {}
    @Test public void testMethod2() {}
    @Test public void testMethod3() {}
    @After public void destroy1() {}
    @After public void destroy2() {}
}
