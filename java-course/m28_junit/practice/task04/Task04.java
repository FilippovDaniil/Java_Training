package m28_junit.practice.task04;

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

    // Тест 1: успешное снятие уменьшает баланс
    @Test
    void testSuccessfulWithdrawalDecreasesBalance() {
        BankAccount account = new BankAccount(1000.0);
        assertEquals(1000.0, account.getBalance(), 0.001, "Initial balance should be 1000");

        account.withdraw(250.0);
        assertEquals(750.0, account.getBalance(), 0.001, "Balance should decrease by 250");

        account.withdraw(100.0);
        assertEquals(650.0, account.getBalance(), 0.001, "Balance should decrease by another 100");
    }

    // Тест 2: снятие суммы больше баланса бросает IllegalStateException
    @Test
    void testWithdrawMoreThanBalanceThrowsException() {
        BankAccount account = new BankAccount(500.0);

        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(1000.0);
        }, "Withdrawing more than balance should throw IllegalStateException");
    }

    // Тест 3: снятие отрицательной суммы бросает IllegalArgumentException
    @Test
    void testWithdrawNegativeAmountThrowsException() {
        BankAccount account = new BankAccount(500.0);

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        }, "Withdrawing negative amount should throw IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0);
        }, "Withdrawing zero amount should throw IllegalArgumentException");
    }

    // Тест 4: проверка сообщения исключения
    @Test
    void testExceptionMessages() {
        BankAccount account = new BankAccount(1000.0);

        // Проверка сообщения для IllegalStateException
        IllegalStateException stateException = assertThrows(IllegalStateException.class, () -> {
            account.withdraw(1500.0);
        });
        assertEquals("Недостаточно средств", stateException.getMessage(),
                "Exception message should be 'Недостаточно средств'");

        // Проверка сообщения для IllegalArgumentException
        IllegalArgumentException argException = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100.0);
        });
        assertEquals("Сумма должна быть положительной", argException.getMessage(),
                "Exception message should be 'Сумма должна быть положительной'");
    }

    // Дополнительный тест: баланс не меняется при ошибках
    @Test
    void testBalanceUnchangedOnError() {
        BankAccount account = new BankAccount(1000.0);

        // Попытка снять больше, чем есть
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(2000.0);
        });
        assertEquals(1000.0, account.getBalance(), 0.001,
                "Balance should not change after failed withdrawal");

        // Попытка снять отрицательную сумму
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
        assertEquals(1000.0, account.getBalance(), 0.001,
                "Balance should not change after invalid withdrawal attempt");
    }
}
