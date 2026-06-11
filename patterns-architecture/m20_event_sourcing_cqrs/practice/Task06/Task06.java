package m20_event_sourcing_cqrs.practice.task06;

/**
 * Задача 06 — Тема 20: Event Sourcing + CQRS вместе
 *
 * ЗАДАНИЕ:
 *   Соедините обе идеи: события хранятся в логе (ES), read-модель строится из
 *   этого лога проекцией (CQRS).
 *     - события MoneyDeposited(amount), MoneyWithdrawn(amount) (records, готовы);
 *     - EventStore (файл EventStore.java): append(Object); List<Object> events();
 *     - BalanceProjection (файл BalanceProjection.java): on(Object) обновляет
 *       баланс; balance();
 *   В main: запишите в store [Deposited 100, Withdrawn 30, Deposited 50] (write);
 *   постройте BalanceProjection, проиграв ВСЕ события из store (read), выведите баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс из проекции: 120
 *
 * ТРЕБОВАНИЯ:
 *   - лог событий (EventStore) — единственный источник истины (write);
 *   - баланс получается ПРОЕКЦИЕЙ из лога, не хранится отдельно (read);
 *   - из того же лога можно построить и другую проекцию.
 *
 * ПОДСКАЗКА:
 *   for (Object e : store.events()) projection.on(e); — read-сторона «проигрывает»
 *   лог. Так write (события) и read (проекция) разделены, но связаны логом.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: append 3 события в EventStore; постройте BalanceProjection из events(); выведите balance()
    }
}
