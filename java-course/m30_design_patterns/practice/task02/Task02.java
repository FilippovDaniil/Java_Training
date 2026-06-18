package m30_design_patterns.practice.task02;

import java.util.ArrayList;

/**
 * Задача 02 — Модуль 30: Factory Method (фабричный метод)
 *
 * ЗАДАНИЕ:
 *   Создайте интерфейс Notification с методом send(String message)
 *   и реализации EmailNotification, SmsNotification, PushNotification
 *   (каждая печатает свой способ доставки).
 *   Класс-фабрика NotificationFactory с методом
 *   create(String type) возвращает нужную реализацию по строке
 *   ("email", "sms", "push"); при неизвестном типе — исключение.
 *   В main создайте уведомления разных типов через фабрику и отправьте.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Email: Привет
 *   SMS: Привет
 *   Push: Привет
 *
 * ПОДСКАЗКА:
 *   static Notification create(String type) { switch(type){...} }
 *   Клиент не знает конкретные классы — только интерфейс и фабрику.
 */

public class Task02 {
    public static void main(String[] args) {
        // Создайте уведомления через NotificationFactory и отправьте
        ArrayList<Notification> arrayList = new ArrayList<>();
        arrayList.add(NotificationFactory.create("Sms"));
        arrayList.add(NotificationFactory.create("Push"));
        arrayList.add(NotificationFactory.create("Email"));
        for (Notification notification : arrayList) {
            notification.send("check");
        }
    }
}
