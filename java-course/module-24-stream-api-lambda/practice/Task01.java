/**
 * Задача 01 — Модуль 24: Первая лямбда
 *
 * ЗАДАНИЕ:
 *   Дан функциональный интерфейс Operation с методом
 *   int apply(int a, int b). Создайте через ЛЯМБДЫ четыре операции:
 *   сложение, вычитание, умножение, деление. Примените к 12 и 4.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Сложение: 16
 *   Вычитание: 8
 *   Умножение: 48
 *   Деление: 3
 *
 * ПОДСКАЗКА:
 *   Operation plus = (a, b) -> a + b;
 *   plus.apply(12, 4);
 */
public class Task01 {
    public static void main(String[] args) {
        // Создайте 4 лямбды Operation и примените к 12 и 4
    }
}

interface Operation {
    int apply(int a, int b);
}
