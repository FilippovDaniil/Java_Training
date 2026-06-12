package m30_design_patterns.practice.task05;

/**
 * Задача 05 — Модуль 30: Observer (наблюдатель)
 *
 * ЗАДАНИЕ:
 *   Реализуйте подписку на события.
 *   1. Интерфейс Observer с методом update(String news).
 *   2. Класс NewsAgency (субъект): хранит список наблюдателей,
 *      методы subscribe(Observer), unsubscribe(Observer) и
 *      publish(String news), который уведомляет ВСЕХ подписчиков.
 *   3. Реализации наблюдателей: EmailSubscriber, SmsSubscriber
 *      (печатают полученную новость со своим префиксом).
 *   4. В main: подпишите нескольких наблюдis, опубликуйте новость,
 *      отпишите одного, опубликуйте снова.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Email получил: Срочные новости!
 *   SMS получил: Срочные новости!
 *   (после отписки SMS)
 *   Email получил: Вторая новость
 *
 * ПОДСКАЗКА:
 *   В publish переберите список observers и вызовите update у каждого.
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        // Подпишите наблюдателей, публикуйте новости, отпишите одного
    }
}
