package m17_hexagonal_ports_adapters.practice.task03;

import java.util.Map;

// Driven-адаптер: реализует выходной порт.
class InMemoryGreetingRepository implements GreetingRepository {
    private final Map<String, String> greetings = Map.of("ru", "Привет", "en", "Hello");

    @Override
    public String find(String lang) {
        // TODO: вернуть greetings.get(lang)
        return "";
    }
}
