package m17_hexagonal_ports_adapters.practice.task05;

/**
 * Задача 05 — Тема 17: тестируемость ядра через fake-адаптер
 *
 * ЗАДАНИЕ:
 *   Покажите, что ядро тестируется без реальной БД — подставив fake-адаптер,
 *   реализующий выходной порт.
 *     - Account (файл Account.java): id, balanceCents; deposit(amount); balance(); id();
 *     - выходной порт AccountStore (файл ...): Account load(String id); void save(Account a);
 *     - FakeAccountStore (файл ...): in-memory Map + счётчик saveCount(); метод
 *       put(Account) для подготовки данных в тесте;
 *     - ЯДРО DepositService (файл DepositService.java): зависит от AccountStore;
 *       deposit(id, amount) → load, deposit, save.
 *   В main: положите в fake счёт с балансом 0, выполните deposit(5000) через ядро,
 *   проверьте новый баланс и что save вызван 1 раз — всё без настоящей БД.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс A после пополнения: 5000
 *   save вызван раз: 1
 *
 * ТРЕБОВАНИЯ:
 *   - DepositService не знает, fake это или реальная БД (зависит от порта);
 *   - FakeAccountStore позволяет проверить поведение ядра без инфраструктуры;
 *   - тестируемость — прямое следствие портов и адаптеров.
 *
 * ПОДСКАЗКА:
 *   Fake-адаптер — это «двойник» из темы 16, но реализующий выходной порт ядра.
 *   В реальных тестах его заменил бы мок или Testcontainers (тема 25).
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: FakeAccountStore.put(счёт "A" с 0); DepositService.deposit("A", 5000);
        //       вывести баланс и saveCount()
    }
}
