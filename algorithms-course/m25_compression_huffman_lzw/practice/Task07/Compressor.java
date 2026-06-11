package m25_compression_huffman_lzw.practice.task07;

/**
 * Модуль Data-Cruncher (ФИНАЛ): сжатие и распаковка данных для экспорта результатов.
 * Объединяет идеи курса: Хаффман (куча + дерево) и LZW (словарь). Предоставляет
 * единый интерфейс compress/decompress и оценку коэффициента сжатия.
 *
 * Это завершающий модуль: Data-Cruncher умеет загружать данные (тема 02),
 * анализировать их (поиск/сортировки/графы/DP) и теперь — сжимать результаты.
 */
public class Compressor {

    /** Сжать строку Хаффманом; вернуть упакованное представление (биты + дерево/коды). */
    public String huffmanCompress(String data) {
        // TODO: построить дерево Хаффмана, закодировать (как в задаче 03)
        return "";
    }

    /** Распаковать то, что вернул huffmanCompress. */
    public String huffmanDecompress(String packed) {
        // TODO: декодировать по сохранённому дереву/кодам
        return "";
    }

    /** Сжать LZW; вернуть список кодов. */
    public java.util.List<Integer> lzwCompress(String data) {
        // TODO: LZW (как в задаче 05)
        return new java.util.ArrayList<>();
    }

    /** Распаковать коды LZW. */
    public String lzwDecompress(java.util.List<Integer> codes) {
        // TODO: LZW-распаковка
        return "";
    }

    /** Коэффициент сжатия Хаффмана для данных (исходные биты / сжатые биты). */
    public double huffmanRatio(String data) {
        // TODO: длина*8 / число бит после кодирования
        return 1.0;
    }
}
