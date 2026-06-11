package m23_microservices_vs_monolith.practice.task07;

/**
 * Задача 07 — Тема 23 (МИНИ-ПРОЕКТ BAM): модульный монолит
 *
 * Развитие BAM. Соберите модульный монолит из двух модулей с чёткими границами,
 * общающихся через публичные интерфейсы (готовых к выделению в сервисы).
 *
 * ЗАДАНИЕ:
 *   1. AccountModule (файл ...): void debit(String id, long amount) (хватает
 *      средств, иначе IllegalStateException); long balance(String id);
 *      AccountModuleImpl владеет своим хранилищем + seed(id, balance).
 *   2. NotificationModule (файл ...): void notify(String msg);
 *      ConsoleNotificationModule печатает "[notify] msg".
 *   3. BankingFacade (файл BankingFacade.java): зависит от ОБОИХ модулей (интерфейсов);
 *      withdraw(id, amount) → accounts.debit(id, amount), затем notify(
 *      "списано " + amount + ", остаток " + accounts.balance(id)).
 *   В main: наполните счёт A-1=10000; через фасад спишите 3000; выведите баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [notify] списано 3000, остаток 7000
 *   Баланс A-1: 7000
 *
 * ТРЕБОВАНИЯ:
 *   - модули общаются ТОЛЬКО через публичные интерфейсы (не лезут в чужие данные);
 *   - каждый модуль владеет своими данными;
 *   - фасад координирует модули, не реализуя их логику сам;
 *   - границы модулей готовы к выделению в отдельные сервисы (тот же контракт → сетевой клиент).
 *
 * ПОДСКАЗКА:
 *   Это сводит тему: модули с границами (Task01–02) + фасад/шлюз (Task04). Из такого
 *   монолита микросервис выделяется заменой реализации контракта на сетевую.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: AccountModuleImpl + seed("A-1", 10000); BankingFacade(accounts, notifications);
        //       withdraw("A-1", 3000); вывести баланс
    }
}
