/**
 * Задача 07 — Тема 05 (МИНИ-ПРОЕКТ BAM): продуктовые семьи и выписка
 *
 * Развитие BAM. Объедините два порождающих паттерна:
 *   - ABSTRACT FACTORY — открыть согласованный набор продуктов (счёт + карта)
 *     для тарифа клиента: Retail (физлицо) или Corporate (юрлицо);
 *   - BUILDER — пошагово собрать месячную выписку (AccountStatement).
 *
 * ЗАДАНИЕ:
 *   1. Продукты: Account (файл Account.java) — String type(); long monthlyFeeCents();
 *      Card (файл Card.java) — String type(); long limitCents().
 *   2. Реализации: RetailAccount (fee 0) + RetailCard (лимит 5_000_00);
 *      CorporateAccount (fee 1_000_00) + CorporateCard (лимит 100_000_00).
 *   3. BankProductFactory (файл BankProductFactory.java):
 *      Account createAccount(); Card createCard(); семьи RetailFactory и
 *      CorporateFactory создают согласованную пару.
 *   4. AccountStatement (файл AccountStatement.java) с Builder:
 *      обязательные clientName и accountType; addLine(String desc, long amountCents)
 *      накапливает операции; build() проверяет обязательные поля; есть метод
 *      print()/describe() с перечнем операций и итогом.
 *   В main: для Retail и Corporate откройте продукты через свою фабрику,
 *   соберите выписку с 2–3 операциями и выведите её.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Открыт retail-счёт (fee 0), карта retail (лимит 500000)
 *   Выписка: Аня / retail
 *     - пополнение: +50000
 *     - покупка: -12000
 *   Итог: 38000
 *
 * ТРЕБОВАНИЯ:
 *   - account и card всегда из одной BankProductFactory (тариф не смешивается);
 *   - AccountStatement неизменяема после build(), обязательные поля проверяются;
 *   - добавление новой продуктовой семьи = новая фабрика, без правок клиента.
 *
 * ПОДСКАЗКА:
 *   Abstract Factory отвечает «какие продукты у этого тарифа», Builder —
 *   «как собрать конкретную выписку». В теме 19 (Tactical DDD) фабрики
 *   превратятся в фабрики агрегатов.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: через RetailFactory/CorporateFactory создайте Account+Card,
        //       соберите AccountStatement через Builder, выведите выписку
    }
}
