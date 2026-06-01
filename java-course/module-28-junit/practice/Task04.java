/**
 * Задача 04 — Модуль 28: Проверка исключений (assertThrows)
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс BankAccount реализован и бросает исключения при некорректных
 *   операциях. Напишите тесты:
 *     1) успешное снятие уменьшает баланс (assertEquals);
 *     2) снятие суммы больше баланса бросает IllegalStateException
 *        (assertThrows);
 *     3) снятие отрицательной суммы бросает IllegalArgumentException
 *        (assertThrows);
 *     4) проверьте сообщение исключения (assertThrows возвращает само
 *        исключение — проверьте getMessage()).
 *
 * ПОДСКАЗКА:
 *   assertThrows(IllegalStateException.class, () -> account.withdraw(1000));
 *   Exception e = assertThrows(...); assertEquals("...", e.getMessage());
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task04 {

    // TODO: напишите тесты с assertEquals и assertThrows

}

// Класс под тестом (готов)
class BankAccount {
    private double balance;

    BankAccount(double balance) { this.balance = balance; }

    double getBalance() { return balance; }

    void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (amount > balance) {
            throw new IllegalStateException("Недостаточно средств");
        }
        balance -= amount;
    }
}
