package m02_variables_int_string.practice;

/**
 * Задача 04 — Модуль 02: Число внутри строки
 *
 * ЗАДАНИЕ:
 *   Дана переменная age = 25.
 *   Выведите фразу "Мне 25 лет", подставив значение переменной
 *   в строку через конкатенацию.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Мне 25 лет
 *
 * ПОДСКАЗКА:
 *   "Мне " + age + " лет"  — int автоматически станет текстом.
 */
public class Task04 {
    public static void main(String[] args) {
        int age = 25;
        System.out.println("Hello, I am: " + age);
    }
}
