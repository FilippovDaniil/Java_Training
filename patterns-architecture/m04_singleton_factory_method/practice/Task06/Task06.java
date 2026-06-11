package m04_singleton_factory_method.practice.task06;

/**
 * Задача 06 — Тема 04: фабрика на реестре (без switch, OCP)
 *
 * ЗАДАНИЕ:
 *   Перепишите фабрику так, чтобы добавление нового типа НЕ требовало правок
 *   её кода — через реестр поставщиков:
 *     - интерфейс Notification (файл Notification.java): String send(String text);
 *       реализации EmailNotification ("[email] ...") и SmsNotification ("[sms] ...");
 *     - NotificationFactory (файл NotificationFactory.java): хранит
 *       Map<String, Supplier<Notification>>; методы
 *         void register(String key, Supplier<Notification> supplier);
 *         Notification create(String key);   // IllegalArgumentException, если нет
 *   В main зарегистрируйте "email" и "sms", создайте оба и отправьте текст.
 *   Затем зарегистрируйте НОВЫЙ тип (например, лямбдой) без правок фабрики.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [email] Привет
 *   [sms] Привет
 *   [push] Привет
 *
 * ТРЕБОВАНИЯ:
 *   - внутри фабрики НЕТ switch/if по типу;
 *   - новый тип добавляется вызовом register(...), а не изменением create(...);
 *   - create возвращает абстракцию Notification.
 *
 * ПОДСКАЗКА:
 *   Supplier<Notification> — это «рецепт создания»: EmailNotification::new или
 *   лямбда () -> new PushNotification(). create(key) берёт рецепт из Map и
 *   вызывает .get().
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: создайте NotificationFactory, register("email", ...), register("sms", ...),
        //       create и send; затем register нового типа лямбдой и создайте его
    }
}
