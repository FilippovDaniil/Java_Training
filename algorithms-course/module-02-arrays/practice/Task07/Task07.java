/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 02: загрузка CSV + rotate
 *
 * ЗАДАНИЕ:
 *   Расширьте Data-Cruncher классом DataLoader (файл DataLoader.java):
 *     - parseCsvLine(String line): int[] — разбирает строку вида "1,2,3,4"
 *       в массив целых;
 *     - loadFromText(String text): int[] — несколько строк/чисел через
 *       запятые и переводы строк -> один массив (для теста читаем из строки,
 *       не из файла, чтобы задача была изолированной и проверяемой);
 *     - rotateLeft(int[] a, int k): int[] — циклический сдвиг влево на k
 *       (повторно используйте идею из задачи 04).
 *   В main загрузите данные из строки-заглушки и покажите rotate.
 *
 * ПРИМЕР / ВЫВОД:
 *   Загружено 6 чисел: [10, 20, 30, 40, 50, 60]
 *   rotateLeft(k=2):    [30, 40, 50, 60, 10, 20]
 *
 * ТРЕБОВАНИЯ:
 *   - parseCsvLine игнорирует пробелы вокруг чисел (trim);
 *   - loadFromText умеет несколько строк (разделитель строк — \n);
 *   - rotateLeft — O(n), без изменения исходного массива (возвращает новый).
 *
 * ПОДСКАЗКА:
 *   line.split(",") -> Integer.parseInt(token.trim()).
 *   Для нескольких строк — text.split("\\R") или split("\n").
 */

import java.util.Arrays;

public class Task07 {
    public static void main(String[] args) {
        String csv = "10, 20, 30\n40, 50, 60";
        DataLoader loader = new DataLoader();
        int[] data = loader.loadFromText(csv);
        System.out.println("Загружено " + data.length + " чисел: " + Arrays.toString(data));
        System.out.println("rotateLeft(k=2): " + Arrays.toString(loader.rotateLeft(data, 2)));
    }
}
