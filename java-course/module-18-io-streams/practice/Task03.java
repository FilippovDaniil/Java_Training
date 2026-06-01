/**
 * Задача 03 — Модуль 18: Дозапись в файл (append)
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой «журнал»: считывайте строки с клавиатуры и
 *   ДОПИСЫВАЙТЕ каждую в файл "log.txt" (не перезаписывая прежнее
 *   содержимое). Пустая строка завершает ввод.
 *
 * ПОДСКАЗКА:
 *   Режим дозаписи: new FileWriter("log.txt", true) — второй аргумент true.
 *   Запустите программу дважды — записи должны накапливаться.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ваш код здесь

        scanner.close();
    }
}
