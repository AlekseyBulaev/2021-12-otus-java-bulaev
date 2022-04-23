package otus.processor;

import otus.model.Message;

public class ProcessorSwapper implements Processor{

    //Command ??
    @Override
    public Message process(Message message) {
        var field11 = message.getField11();
        var field12 = message.getField12();
        return message.toBuilder().field12(field11).field11(field12).build();
    }
}
