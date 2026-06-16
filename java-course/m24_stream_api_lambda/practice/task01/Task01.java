package m24_stream_api_lambda.practice.task01;

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
        Operation plus = (a, b) -> a + b;
        Operation minus = (a,b) -> a - b;
        Operation umnojit = (a,b) -> a * b;
        Operation delenie = (a,b) -> a / b;
        System.out.println("Сложение: " + plus.apply(12,4));
        System.out.println("Вычитание: " + minus.apply(12,4));
        System.out.println("Умножение: " + umnojit.apply(12,4));
        System.out.println("Деление: " + delenie.apply(12,4));
    }
}
