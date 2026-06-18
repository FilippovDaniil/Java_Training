package m30_design_patterns.practice.task05;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
    private final List<Observer> observers = new ArrayList<>();

    public NewsAgency() {
        // observers уже готов к использованию
        System.out.println("NewsAgency создан. Список наблюдателей готов.");
    }

    public void subscribe(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Наблюдатель подписан: " + observer);
        }
    }

    public void unsubscribe(Observer observer) {
        if (observer != null) {
            boolean removed = observers.remove(observer);
            if (removed) {
                System.out.println("Наблюдатель отписан: " + observer);
            }
        }
    }

    public void publish(String news) {
        System.out.println("Публикация новости: " + news);
        notifyAllObservers(news);
    }

    private void notifyAllObservers(String news) {
        // Используем копию списка, чтобы избежать ConcurrentModificationException
        List<Observer> copy = new ArrayList<>(observers);
        for (Observer observer : copy) {
            observer.update(news);
        }
    }

    public int getObserverCount() {
        return observers.size();
    }
}
