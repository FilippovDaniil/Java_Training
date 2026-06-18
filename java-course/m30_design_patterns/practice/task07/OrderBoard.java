package m30_design_patterns.practice.task07;

import java.util.ArrayList;
import java.util.List;

public class OrderBoard {
    private List<OrderObserver> observers = new ArrayList<>();
    private List<String> orderHistory = new ArrayList<>();
    private int orderCount = 0;

    public void subscribe(OrderObserver observer) {
        observers.add(observer);
        System.out.println("   ✅ Подписан наблюдатель: " + observer.getClass().getSimpleName());
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
        System.out.println("   ❌ Отписан наблюдатель: " + observer.getClass().getSimpleName());
    }

    public void placeOrder(String description, double price) {
        orderCount++;
        String orderInfo = "Заказ #" + orderCount + ": " + description;
        orderHistory.add(orderInfo);

        System.out.println("\n📢 НОВЫЙ ЗАКАЗ!");
        System.out.println("   " + orderInfo);
        System.out.println("   Сумма: " + String.format("%.2f", price) + " руб");
        System.out.println("\n   Уведомление наблюдателей:");

        notifyObservers(description, price);

        System.out.println("\n   ✅ Заказ #" + orderCount + " оформлен!");
    }

    private void notifyObservers(String description, double price) {
        for (OrderObserver observer : observers) {
            observer.onNewOrder(description, price);
        }
    }

    public void printHistory() {
        System.out.println("\n📜 История заказов:");
        for (String order : orderHistory) {
            System.out.println("   " + order);
        }
    }

    public int getOrderCount() {
        return orderCount;
    }
}