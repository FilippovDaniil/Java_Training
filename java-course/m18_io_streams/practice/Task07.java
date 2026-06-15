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
import java.io.*;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "notes.txt";

        while (true) {
            System.out.println("\n=== Записная книжка ===");
            System.out.println("1 — добавить заметку");
            System.out.println("2 — показать все заметки");
            System.out.println("3 — посчитать количество заметок");
            System.out.println("0 — выход");
            System.out.print("Ваш выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 0 до 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    addNote(fileName, scanner);
                    break;
                case 2:
                    showAllNotes(fileName);
                    break;
                case 3:
                    countNotes(fileName);
                    break;
                case 0:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addNote(String fileName, Scanner scanner) {
        System.out.print("Введите текст заметки: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Заметка не может быть пустой.");
            return;
        }

        // Определяем номер для новой заметки (текущее количество строк + 1)
        int nextNumber = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            nextNumber = lines + 1;
        } catch (FileNotFoundException e) {
            // файл ещё не существует, номер остаётся 1
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        // Дописываем новую заметку
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(nextNumber + ". " + text);
            System.out.println("Заметка добавлена (№" + nextNumber + ").");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static void showAllNotes(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean hasNotes = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasNotes = true;
            }
            if (!hasNotes) {
                System.out.println("Заметок пока нет.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Заметок пока нет.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void countNotes(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int count = 0;
            while (reader.readLine() != null) {
                count++;
            }
            System.out.println("Всего заметок: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("Всего заметок: 0");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}