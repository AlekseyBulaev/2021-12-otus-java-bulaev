package otus.listener.homework;

import otus.listener.Listener;
import otus.model.Message;
import otus.model.ObjectForMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> map = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        var message = copy(msg);
        map.put(msg.getId(), message);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        var result = map.get(id);
        return Optional.of(copy(result));
    }

    private Message copy(Message msg) {
        List<String> cpField13 = msg.getField13().getData().stream().toList();
        ObjectForMessage field13 = new ObjectForMessage();
        field13.setData(cpField13);
        return new Message.Builder(
                msg.getId())
                .field1(msg.getField1())
                .field2(msg.getField2())
                .field3(msg.getField3())
                .field4(msg.getField4())
                .field5(msg.getField5())
                .field6(msg.getField6())
                .field7(msg.getField7())
                .field8(msg.getField8())
                .field9(msg.getField9())
                .field10(msg.getField10())
                .field11(msg.getField11())
                .field12(msg.getField12())
                .field13(field13)
                .build();
    }
}
