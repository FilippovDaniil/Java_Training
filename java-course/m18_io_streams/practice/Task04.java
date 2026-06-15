package m18_io_streams.practice;

/**
 * Задача 04 — Модуль 18: Чтение с консоли через BufferedReader
 *
 * ЗАДАНИЕ:
 *   Не используя Scanner, считайте с клавиатуры через BufferedReader:
 *     - имя (строка);
 *     - возраст (число — преобразуйте строку вручную).
 *   Выведите приветствие. Обработайте возможный IOException и
 *   NumberFormatException (если введено не число).
 *
 * ПРИМЕР:
 *   Ввод: Иван / 30 → Привет, Иван! Тебе 30.
 *   Ввод: Иван / x  → Возраст должен быть числом
 *
 * ПОДСКАЗКА:
 *   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *   int age = Integer.parseInt(br.readLine());
 */
import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task04 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter the name:");
            String name = br.readLine();

            int age = 0;
            boolean valid = false;
            while (!valid) {
                System.out.println("Enter the age:");
                String ageInput = br.readLine();
                try {
                    age = Integer.parseInt(ageInput);
                    valid = true; // если парсинг успешен, выходим из цикла
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number for age.");
                }
            }

            System.out.println(name + ", " + age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
