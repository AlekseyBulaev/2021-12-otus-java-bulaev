package otus.listener.homework;

import otus.listener.Listener;
import otus.model.Message;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

//    private Map<>
    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}
