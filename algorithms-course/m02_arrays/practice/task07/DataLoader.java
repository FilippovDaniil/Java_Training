package m02_arrays.practice.task07;

/**
 * Модуль Data-Cruncher: загрузка числовых данных и базовые операции над массивом.
 * В реальной системе читал бы из файла; здесь — из строки (для изолированной проверки).
 */
public class DataLoader {

    /** Разобрать одну CSV-строку "1, 2, 3" в массив int. */
    public int[] parseCsvLine(String line) {
        // TODO: split(","), trim каждого токена, Integer.parseInt
        return new int[0];
    }

    /** Разобрать многострочный текст (числа через запятые и переводы строк) в один массив. */
    public int[] loadFromText(String text) {
        // TODO: разбить на строки, каждую через parseCsvLine, склеить в один массив
        return new int[0];
    }

    /** Циклический сдвиг влево на k. Возвращает НОВЫЙ массив. O(n). */
    public int[] rotateLeft(int[] a, int k) {
        // TODO: учесть k % n; вернуть новый массив со сдвигом
        return a.clone();
    }
}
