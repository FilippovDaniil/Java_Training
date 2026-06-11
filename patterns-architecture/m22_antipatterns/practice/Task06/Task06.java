package m22_antipatterns.practice.task06;

/**
 * Задача 06 — Тема 22: рефакторинг сразу двух запахов
 *
 * ЗАДАНИЕ:
 *   Дан был анемичный счёт с «голым» long-балансом и внешним сервисом операций
 *   (Anemic + Primitive Obsession). Исправьте ОБА запаха:
 *     - Money (файл Money.java): VO с amountCents/currency; plus(Money), minus(Money),
 *       isLessThan(Money); equals/toString;
 *     - Account (файл Account.java): id и Money balance (передаётся в конструктор);
 *       deposit(Money) и withdraw(Money) с инвариантом (нельзя списать больше баланса,
 *       иначе IllegalStateException) — поведение ВНУТРИ счёта; balance() → Money.
 *   В main: счёт с балансом Money(10000,"RUB"); deposit 5000; withdraw 3000;
 *   выведите баланс; попробуйте списать больше остатка (исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс: 12000 RUB
 *   Списание сверх остатка: отказано
 *
 * ТРЕБОВАНИЯ:
 *   - деньги — Value Object Money (не long): лечим Primitive Obsession;
 *   - операции и инвариант — внутри Account (не во внешнем сервисе): лечим Anemic Model;
 *   - withdraw сверх баланса отклоняется.
 *
 * ПОДСКАЗКА:
 *   Два антипаттерна часто ходят парой. Money даёт типобезопасность, богатый
 *   Account — инкапсуляцию правил (Information Expert + Value Object).
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: Account с Money(10000,"RUB"); deposit(Money 5000); withdraw(Money 3000);
        //       выведите balance(); withdraw сверх остатка — поймайте исключение
    }
}
