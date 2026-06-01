/**
 * Задача 03 — Модуль 26: Исследование методов
 *
 * ЗАДАНИЕ:
 *   Используя рефлексию, выведите все методы класса Calculator:
 *   имя метода, тип возвращаемого значения и количество параметров.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   add: возвращает int, параметров: 2
 *   isPositive: возвращает boolean, параметров: 1
 *
 * ПОДСКАЗКА:
 *   for (Method m : Calculator.class.getDeclaredMethods())
 *       m.getName(); m.getReturnType().getSimpleName(); m.getParameterCount();
 */
import java.lang.reflect.Method;

public class Task03 {
    public static void main(String[] args) {
        // Переберите getDeclaredMethods() класса Calculator
    }
}

class Calculator {
    int add(int a, int b) { return a + b; }
    boolean isPositive(int n) { return n > 0; }
}
