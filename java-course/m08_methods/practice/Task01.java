package m08_methods.practice;

/**
 * Задача 01 — Модуль 08: Метод-приветствие (void)
 *
 * ЗАДАНИЕ:
 *   Напишите метод greet(String name), который выводит
 *   "Привет, <name>!". Вызовите его из main для двух разных имён.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Привет, Анна!
 *   Привет, Борис!
 *
 * ПОДСКАЗКА:
 *   public static void greet(String name) { ... }
 */
public class Task01 {
    public static void main(String[] args) {
        // Вызовите ваш метод здесь
        greetName("Anna");
        greetName("Boris");
    }

    // Объявите метод greet ниже
    public static void greetName(String name){
        System.out.println("Hello, " + name + "!");
    }
}
