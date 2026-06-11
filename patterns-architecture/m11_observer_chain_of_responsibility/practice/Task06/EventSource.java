package m11_observer_chain_of_responsibility.practice.task06;

import java.util.ArrayList;
import java.util.List;

// Субъект (Observer): рассылает события подписчикам.
class EventSource {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        // TODO: добавить
    }

    public void fire(int level, String msg) {
        // TODO: уведомить всех onEvent(level, msg)
    }
}
