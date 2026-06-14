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
import java.util.*;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the words: ");
        String text = scanner.nextLine();
        // Ваш код здесь
        String[] massive_strok = text.toLowerCase().split("\\s+");
        Set<String> set = new LinkedHashSet<>(Arrays.asList(massive_strok));
        Set<String> treeSet = new TreeSet<>(set);
        System.out.println("vsevo slov: " + massive_strok.length);
        System.out.println("unikalnih slov: " + set.size());
        System.out.println("po alfavitu: " + treeSet);
        System.out.println("kak dobavlyali: " + set);


        scanner.close();
    }
}
