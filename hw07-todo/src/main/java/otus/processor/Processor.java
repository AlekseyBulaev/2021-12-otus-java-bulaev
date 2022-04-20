package otus.processor;

import otus.model.Message;

public interface Processor {
    Message process(Message message);
}
