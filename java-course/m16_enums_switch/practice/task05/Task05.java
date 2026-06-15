package m16_enums_switch.practice.task05;

/**
 * Задача 05 — Модуль 16: valueOf, name, ordinal
 *
 * ЗАДАНИЕ:
 *   Дан enum Level (LOW, MEDIUM, HIGH). Считайте с клавиатуры строку
 *   (например "MEDIUM") и преобразуйте её в константу через valueOf().
 *   Выведите её имя и порядковый номер. Обработайте ситуацию, когда
 *   введено несуществующее значение (поймайте IllegalArgumentException
 *   и выведите "Неизвестный уровень").
 *
 * ПРИМЕРЫ:
 *   Ввод: HIGH    → Уровень HIGH, номер 2
 *   Ввод: SUPER   → Неизвестный уровень
 *
 * ПОДСКАЗКА:
 *   Level.valueOf(s) бросает IllegalArgumentException при отсутствии.
 *   Оберните вызов в try-catch (исключения — тема модуля 17).
 */

import java.util.Scanner;

public class Task05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your level: ");
        String input = scanner.nextLine().trim().toUpperCase(); // приводим к верхнему регистру для надёжности

        try {
            Level level = Level.valueOf(input); // пробуем преобразовать
            System.out.println("Level: " + level.name() + ", number: " + level.ordinal());
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown level");
        }

        scanner.close();
    }
}
