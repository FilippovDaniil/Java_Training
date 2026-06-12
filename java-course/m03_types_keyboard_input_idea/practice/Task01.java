package m03_types_keyboard_input_idea.practice;

/**
 * Задача 01 — Модуль 03: Примитивные типы
 *
 * ЗАДАНИЕ:
 *   Объявите по одной переменной каждого типа со своим значением:
 *   int, double, char, boolean, long.
 *   Выведите все значения, каждое на отдельной строке.
 *
 * ПРИМЕР ВЫВОДА:
 *   int: 42
 *   double: 3.14
 *   char: A
 *   boolean: true
 *   long: 10000000000
 *
 * ПОДСКАЗКА:
 *   long требует суффикс L: long big = 10000000000L;
 */
public class Task01 {
    public static void main(String[] args) {
        int a = 52;
        double b = 3.13;
        char c = 'C';
        boolean is_false = true;
        long d = 1_000_000_000;
        System.out.println(((Object) a).getClass().getName() + ": " + a);
        System.out.println(((Object) b).getClass().getName() + ": " + b);
        System.out.println(((Object) c).getClass().getName() + ": " + c);
        System.out.println(((Object) is_false).getClass().getName() + ": " + is_false);
        System.out.println(((Object) d).getClass().getName() + ": " + d);

    }
}
