package m11_observer_chain_of_responsibility.practice.task01;

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
