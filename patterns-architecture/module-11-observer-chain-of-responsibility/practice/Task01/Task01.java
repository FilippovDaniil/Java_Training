/**
 * Задача 01 — Тема 11: Observer (подписка на события)
 *
 * ЗАДАНИЕ:
 *   Реализуйте рассылку новостей подписчикам:
 *     - интерфейс Observer (файл Observer.java): void update(String news);
 *     - NewsAgency (файл NewsAgency.java) — субъект: хранит список Observer,
 *       subscribe(Observer), publish(String news) уведомляет ВСЕХ;
 *     - EmailSubscriber ("email: ...") и SmsSubscriber ("sms: ...").
 *   В main подпишите обоих и опубликуйте новость.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   email: Срочно!
 *   sms: Срочно!
 *
 * ТРЕБОВАНИЯ:
 *   - субъект знает только интерфейс Observer, не конкретные классы;
 *   - publish уведомляет всех подписчиков;
 *   - подписчики добавляются динамически.
 *
 * ПОДСКАЗКА:
 *   publish перебирает список observers и вызывает update(news) у каждого.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте NewsAgency, подпишите Email и Sms, опубликуйте новость
    }
}
