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
