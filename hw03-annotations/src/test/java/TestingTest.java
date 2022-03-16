
import ru.otus.java.professional.Framework;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingTest {
    @org.junit.jupiter.api.Test
    public void test1() {
        String expected = "TOTAL= 3 \nFAILED= 0 \nPASSED= 3";
        assertEquals(expected, new Framework().start("ru.otus.java.professional.TargetClass"));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        String expected = "TOTAL= 1 \nFAILED= 1 \nPASSED= 0";
        assertEquals(expected, new Framework().start("ru.otus.java.professional.Test4Test"));
    }
}

