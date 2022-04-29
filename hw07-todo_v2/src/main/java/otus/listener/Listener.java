package otus.listener;

import otus.model.Message;

public interface Listener {

    void onUpdated(Message msg);

}
