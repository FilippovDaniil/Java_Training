package m17_exceptions.practice;

/**
 * Задача 07 — Модуль 17 (МИНИ-ПРОЕКТ): Надёжный банкомат
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс ATM (банкомат) с защитой через исключения.
 *   1. Собственное исключение InsufficientFundsException
 *      (наследник RuntimeException).
 *   2. Класс ATM: private поле balance; конструктор (начальный баланс);
 *      метод withdraw(int amount):
 *        - бросает IllegalArgumentException, если amount <= 0;
 *        - бросает InsufficientFundsException, если amount > balance;
 *        - иначе уменьшает баланс и выводит "Выдано: <amount>".
 *   3. В main организуйте цикл: пользователь вводит суммы для снятия,
 *      каждая попытка — в try-catch (обработайте оба исключения),
 *      finally выводит текущий баланс. Ввод 0 завершает работу.
 *
 * ПРИМЕР СЕССИИ:
 *   Баланс: 1000
 *   Снять: 300  → Выдано: 300, Баланс: 700
 *   Снять: 5000 → Недостаточно средств, Баланс: 700
 *   Снять: -1   → Некорректная сумма, Баланс: 700
 *   Снять: 0    → Завершение
 *
 * ПОДСКАЗКА:
 *   Используйте всё из модуля: собственное исключение, throw,
 *   try-catch (multi или несколько), finally.
 */
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Создайте ATM и организуйте цикл снятия с обработкой ошибок

        scanner.close();
    }
}

// TODO: класс ATM и исключение InsufficientFundsException
