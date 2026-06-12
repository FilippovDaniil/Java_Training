package m24_messaging_event_driven_kafka.practice.task03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// Маршрутизирует события обработчикам по типу (Event-Driven).
class EventRouter {
    private final Map<Class<?>, List<Consumer<Object>>> handlers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void on(Class<T> type, Consumer<T> handler) {
        // TODO: handlers.computeIfAbsent(type, k -> new ArrayList<>()).add((Consumer<Object>) handler)
    }

    public void dispatch(Object event) {
        // TODO: вызвать accept(event) у обработчиков для event.getClass()
    }
}
