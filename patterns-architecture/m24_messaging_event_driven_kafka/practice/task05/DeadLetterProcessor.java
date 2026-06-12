package m24_messaging_event_driven_kafka.practice.task05;

import java.util.ArrayList;
import java.util.List;

// Неуспешно обработанные сообщения отправляет в DLQ.
class DeadLetterProcessor {
    private final List<String> dlq = new ArrayList<>();

    public void process(String msg) {
        // TODO: если msg.startsWith("BAD") → dlq.add(msg) и печать "в DLQ: " + msg;
        //       иначе печать "обработано: " + msg
    }

    public int dlqSize() {
        // TODO: размер DLQ
        return 0;
    }
}
