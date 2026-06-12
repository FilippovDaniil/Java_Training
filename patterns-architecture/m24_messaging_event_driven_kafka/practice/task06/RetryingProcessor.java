package m24_messaging_event_driven_kafka.practice.task06;

import java.util.ArrayList;
import java.util.List;

// Повторяет обработку до maxAttempts раз, затем — в DLQ.
class RetryingProcessor {
    private static final int MAX_ATTEMPTS = 3;
    private final List<String> dlq = new ArrayList<>();

    public void process(String msg, int failuresBeforeSuccess) {
        // TODO: for (attempt = 1..MAX_ATTEMPTS):
        //         если attempt <= failuresBeforeSuccess → печать "ошибка [msg] попытка " + attempt;
        //         иначе → печать "успех [msg] попытка " + attempt; return;
        //       после цикла (все упали) → dlq.add(msg); печать "в DLQ [msg]"
    }

    public int dlqSize() {
        // TODO: размер DLQ
        return 0;
    }
}
