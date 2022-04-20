package otus.processor;

import org.junit.jupiter.api.Test;
import otus.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessorSwapperTest {
    @Test
    public void swapperTest() {
        Message msg = new Message.Builder(1).field11("field11").field12("field12").build();

        Processor swapper = new ProcessorSwapper();
        var result = swapper.process(msg);
        assertEquals("field11", result.getField12());
        assertEquals("field12", result.getField11());

        result = swapper.process(result);
        assertEquals("field11", result.getField11());
        assertEquals("field12", result.getField12());
    }
}
