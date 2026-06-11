package m11_observer_chain_of_responsibility.practice.task02;

import java.util.ArrayList;
import java.util.List;

class WeatherStation {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        // TODO: добавить
    }

    public void unsubscribe(Observer observer) {
        // TODO: удалить
    }

    public void setTemperature(int temperature) {
        // TODO: уведомить всех текущих подписчиков update(temperature)
    }
}
