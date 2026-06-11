package m14_collections_set_iterator.practice;

/**
 * Задача 07 — Модуль 14 (МИНИ-ПРОЕКТ): Уникальные слова текста
 *
 * ЗАДАНИЕ:
 *   Считайте строку текста и выведите:
 *     - общее количество слов;
 *     - количество УНИКАЛЬНЫХ слов (без учёта регистра);
 *     - список уникальных слов в АЛФАВИТНОМ порядке;
 *     - список слов в порядке их первого появления в тексте.
 *
 * ПРИМЕР:
 *   Ввод: "кот пёс кот рыба пёс кот"
 *   Всего слов: 6
 *   Уникальных: 3
 *   По алфавиту: [кот, пёс, рыба]
 *   В порядке появления: [кот, пёс, рыба]
 *
 * ПОДСКАЗКИ:
 *   - разбейте текст: text.toLowerCase().split("\\s+");
 *   - для алфавитного порядка используйте TreeSet;
 *   - для порядка появления используйте LinkedHashSet.
 */
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        // Ваш код здесь

        scanner.close();
    }
}
