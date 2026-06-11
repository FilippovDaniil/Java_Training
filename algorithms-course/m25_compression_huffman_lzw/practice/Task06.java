package m25_compression_huffman_lzw.practice;

/**
 * Задача 06 — Тема 25: Сравнить коэффициент сжатия на тексте
 *
 * ЗАДАНИЕ:
 *   Сравните, насколько хорошо Хаффман и LZW сжимают разные данные. Посчитайте
 *   коэффициент сжатия (исходный размер в битах / сжатый размер в битах) для:
 *     - текста на естественном языке (повторяющиеся буквы);
 *     - данных с длинными повторами ("ABABAB...");
 *     - почти случайной строки.
 *   Сделайте вывод, где какой алгоритм выигрывает.
 *
 * ПРИМЕР / ВЫВОД (числа примерные):
 *   текст:          Хаффман ~1.7x   LZW ~1.5x
 *   повторы:        Хаффман ~1.1x   LZW ~5x   (LZW любит повторы)
 *   случайные:      Хаффман ~1.0x   LZW ~1.0x (несжимаемо)
 *
 * ТРЕБОВАНИЯ:
 *   - оценивать размер в БИТАХ: Хаффман — сумма длин кодов (+ накладные на таблицу,
 *     можно упомянуть); LZW — число кодов × бит на код;
 *   - сравнить на трёх типах входа; вывести коэффициенты;
 *   - реализации Хаффмана/LZW взять из задач 03 и 05.
 *
 * ПОДСКАЗКА:
 *   Исходный размер ASCII = длина × 8 бит. Учитывайте, что таблица/словарь — тоже накладные.
 */

public class Task06 {

    static double huffmanRatio(String s) {
        // TODO: исходные биты / биты после Хаффмана
        return 1.0;
    }
    static double lzwRatio(String s) {
        // TODO: исходные биты / биты после LZW
        return 1.0;
    }

    public static void main(String[] args) {
        String text = "the quick brown fox jumps over the lazy dog the fox";
        String repeats = "ABABABABABABABABABABABABABABABAB";
        // TODO: вывести коэффициенты обоих алгоритмов для разных входов и сделать вывод
        System.out.println("text  Huffman=" + huffmanRatio(text) + " LZW=" + lzwRatio(text));
        System.out.println("rep   Huffman=" + huffmanRatio(repeats) + " LZW=" + lzwRatio(repeats));
    }
}
