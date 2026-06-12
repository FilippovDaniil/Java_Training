package m02_variables_int_string.practice;

/**
 * Задача 05 — Модуль 02: Обмен значений
 *
 * ЗАДАНИЕ:
 *   Даны две переменные: a = 100, b = 200.
 *   Поменяйте их значения местами (используя временную переменную)
 *   и выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   До обмена: a=100, b=200
 *   После обмена: a=200, b=100
 *
 * ПОДСКАЗКА:
 *   int temp = a; a = b; b = temp;
 */
public class Task05 {
    public static void main(String[] args) {
        int a = 100;
        int b = 200;
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a: " + a + ", b: " + b);
    }
}
