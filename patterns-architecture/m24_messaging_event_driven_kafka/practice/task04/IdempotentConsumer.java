package m24_messaging_event_driven_kafka.practice.task04;

import java.util.HashSet;
import java.util.Set;

// Дедуплицирует сообщения по id (защита от повторной доставки).
class IdempotentConsumer {
    private final Set<String> processed = new HashSet<>();

    public void onMessage(String id, String payload) {
        // TODO: если id уже в processed → напечатать "дубль пропущен: " + id и выйти;
        //       иначе добавить id и напечатать "обработано: " + payload
    }

    public int processedCount() {
        // TODO: число уникально обработанных
        return 0;
    }
}
