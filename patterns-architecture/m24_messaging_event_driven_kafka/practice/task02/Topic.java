package m24_messaging_event_driven_kafka.practice.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Pub-Sub: событие получают все подписчики.
class Topic {
    private final List<Consumer<String>> subscribers = new ArrayList<>();

    public void subscribe(Consumer<String> sub) {
        // TODO: добавить подписчика
    }

    public void publish(String event) {
        // TODO: вызвать accept(event) у всех подписчиков
    }
}
