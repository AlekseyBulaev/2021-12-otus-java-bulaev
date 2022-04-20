package otus.processor;

import otus.model.Message;

public class EvenExceptionProcessor implements Processor{

    DateTimeProvider provider;
    long time;
    EvenExceptionProcessor(DateTimeProvider provider) {
        this.provider = provider;
    }

    boolean setTime() {
        time = provider.getTime();
        return time % 2 != 0;
    }

    long getTime() {
        return time;
    }

    @Override
    public Message process(Message message) {
        if(setTime()) {
            throw new RuntimeException("Even seconds occurred");
        }
        return message;
    }
}
