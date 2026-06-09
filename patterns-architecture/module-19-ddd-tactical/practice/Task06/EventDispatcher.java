import java.util.ArrayList;
import java.util.List;

// Раздаёт события подписчикам (Observer на уровне домена).
class EventDispatcher {
    private final List<EventHandler> handlers = new ArrayList<>();

    public void subscribe(EventHandler handler) {
        // TODO: добавить обработчик
    }

    public void publish(MoneyDeposited event) {
        // TODO: вызвать handle(event) у всех подписчиков
    }
}
