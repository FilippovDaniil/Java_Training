package m18_io_streams.practice;

/**
 * Задача 07 — Модуль 18 (МИНИ-ПРОЕКТ): Записная книжка
 *
 * ЗАДАНИЕ:
 *   Реализуйте консольную записную книжку, хранящую заметки в файле
 *   "notes.txt". Меню в цикле:
 *     1 — добавить заметку (запросить текст, ДОПИСАТЬ в файл с номером
 *         и датой/временем — можно просто порядковый номер);
 *     2 — показать все заметки (прочитать файл и вывести построчно);
 *     3 — посчитать количество заметок (число строк в файле);
 *     0 — выход.
 *   Программа должна корректно работать, даже если файл ещё не создан
 *   (при чтении несуществующего файла — вывести "Заметок пока нет").
 *
 * ПРИМЕР:
 *   1 → "Купить молоко"   (добавлено)
 *   1 → "Позвонить маме"  (добавлено)
 *   2 →
 *     1. Купить молоко
 *     2. Позвонить маме
 *   3 → Всего заметок: 2
 *
 * ПОДСКАЗКИ:
 *   - добавление: FileWriter("notes.txt", true) + PrintWriter;
 *   - чтение: BufferedReader; при FileNotFoundException — "Заметок пока нет";
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ваш код здесь (меню в цикле)

        scanner.close();
    }
}
