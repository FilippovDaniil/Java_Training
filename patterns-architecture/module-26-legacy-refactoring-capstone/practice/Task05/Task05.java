/**
 * Задача 05 — Тема 26: Anti-Corruption Layer (слой-переводчик)
 *
 * ЗАДАНИЕ:
 *   Чтобы старый формат не «протёк» в новый чистый домен, между ними ставим
 *   переводчик:
 *     - Customer (файл Customer.java) — чистая новая модель: name (String),
 *       birthYear (int); геттеры;
 *     - LegacyCustomerAdapter (файл LegacyCustomerAdapter.java): Customer
 *       translate(String legacyRecord) — разбирает строку формата "ИМЯ;ГОД"
 *       (split по ";") в Customer.
 *   В main: переведите "ИВАНОВ;1990" и выведите "Клиент: <name>, <birthYear>".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Клиент: ИВАНОВ, 1990
 *
 * ТРЕБОВАНИЯ:
 *   - новый домен (Customer) НЕ знает про формат legacy-строки;
 *   - вся «грязь» перевода изолирована в адаптере (ACL);
 *   - смена формата legacy затрагивает только адаптер, не домен.
 *
 * ПОДСКАЗКА:
 *   ACL — это Adapter (тема 07) на границе контекстов (тема 18). Он защищает
 *   чистоту новой модели от чужого/старого формата.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: LegacyCustomerAdapter.translate("ИВАНОВ;1990"); вывести имя и год
    }
}
