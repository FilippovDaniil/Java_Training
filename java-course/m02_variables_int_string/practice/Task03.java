package m02_variables_int_string.practice;

/**
 * Задача 03 — Модуль 02: Конкатенация строк
 *
 * ЗАДАНИЕ:
 *   Объявите две строковые переменные: firstName и lastName.
 *   Склейте их в одну строку "Привет, <Имя> <Фамилия>!" и выведите.
 *
 * ПРИМЕР ВЫВОДА (для firstName="Иван", lastName="Петров"):
 *   Привет, Иван Петров!
 *
 * ПОДСКАЗКА:
 *   Используйте оператор + для склейки строк.
 */
public class Task03 {
    public static void main(String[] args) {
        String firstName = "Daniil";
        String lastName = "Filippov";
        System.out.println("Hello, " + firstName + " " + lastName + "!");
    }
}
