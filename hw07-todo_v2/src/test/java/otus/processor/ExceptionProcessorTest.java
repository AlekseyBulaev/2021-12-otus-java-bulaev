package otus.processor;

import org.junit.jupiter.api.Test;
import otus.model.Message;

import java.time.Instant;

public class ExceptionProcessorTest {

    @Test
    public void exceptionTest() {
        Message msg = new Message.Builder(1).build();
        EvenExceptionProcessor prc = new EvenExceptionProcessor((() -> Instant.now().getEpochSecond()));
        try {
            prc.process(msg);
        } catch (RuntimeException e) {
            if (prc.getTime() % 2 == 0) {
                throw new RuntimeException("EvenExceptionProcessor doesn't work");
            }
        }
    }
}
