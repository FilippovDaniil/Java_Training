import java.util.ArrayList;
import java.util.List;

class NewsAgency {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        // TODO: добавить наблюдателя
    }

    public void publish(String news) {
        // TODO: уведомить всех наблюдателей (update(news))
    }
}
