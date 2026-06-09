import java.util.ArrayList;
import java.util.List;

class OrderTemplate {
    // TODO: поля name (String) и items (List<String>)

    public OrderTemplate(String name) {
        // TODO: задать name, пустой список items
    }

    private OrderTemplate(String name, List<String> items) {
        // TODO: вспомогательный конструктор для copy()
    }

    public void addItem(String item) {
        // TODO
    }

    // ГЛУБОКАЯ копия: новый список items
    public OrderTemplate copy() {
        // TODO: вернуть new OrderTemplate(name, new ArrayList<>(items))
        return null;
    }

    public List<String> getItems() {
        // TODO
        return List.of();
    }

    public String getName() {
        // TODO
        return "";
    }
}
