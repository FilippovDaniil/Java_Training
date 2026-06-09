import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

// Шина событий для хореографии (плумбинг — готова).
class EventBus {
    private final Map<Class<?>, List<Consumer<Object>>> subs = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void subscribe(Class<T> type, Consumer<T> handler) {
        subs.computeIfAbsent(type, k -> new ArrayList<>()).add((Consumer<Object>) handler);
    }

    public void publish(Object event) {
        subs.getOrDefault(event.getClass(), List.of()).forEach(h -> h.accept(event));
    }
}
