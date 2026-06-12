package m20_event_sourcing_cqrs.practice.task06;

import java.util.ArrayList;
import java.util.List;

class EventStore {
    private final List<Object> events = new ArrayList<>();

    public void append(Object event) {
        // TODO: добавить в лог
    }

    public List<Object> events() {
        // TODO: вернуть копию лога
        return List.copyOf(events);
    }
}
