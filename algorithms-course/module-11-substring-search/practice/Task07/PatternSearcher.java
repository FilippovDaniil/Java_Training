/**
 * Модуль Data-Cruncher: поиск паттернов в загруженных текстовых данных.
 * Использует КМП за O(n + m). В реальной системе текст читался бы из файла;
 * здесь — из переданной строки (для изолированной проверки).
 */
import java.util.ArrayList;
import java.util.List;

public class PatternSearcher {

    /** Все вхождения паттерна в текст (КМП). */
    public List<Integer> findAll(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        // TODO: КМП (префикс-функция + проход по тексту)
        return result;
    }

    /** Сколько раз паттерн встречается в тексте. */
    public int count(String text, String pattern) {
        // TODO: вернуть findAll(...).size()
        return 0;
    }

    /** Номера строк (1-based), в которых встречается паттерн. */
    public List<Integer> linesContaining(String text, String pattern) {
        List<Integer> lines = new ArrayList<>();
        // TODO: разбить text по \n; для каждой строки проверить наличие паттерна
        return lines;
    }
}
