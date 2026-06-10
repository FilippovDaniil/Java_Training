/**
 * Задача 07 (ФИНАЛ Data-Cruncher) — Тема 25: модуль сжатия/распаковки
 *
 * ЗАДАНИЕ:
 *   Завершите сквозной проект: добавьте Compressor (файл Compressor.java) —
 *   модуль сжатия результатов анализа для экспорта. Поддержите два метода
 *   (Хаффман и LZW), round-trip (распаковка восстанавливает исходные данные)
 *   и оценку коэффициента сжатия.
 *
 * ПРИМЕР / ВЫВОД:
 *   data = "result: aaaaabbbbcccd repeated aaaaabbbbcccd"
 *   Хаффман: round-trip ok, коэффициент ~1.8x
 *   LZW:     round-trip ok (повторы сжаты)
 *
 * ТРЕБОВАНИЯ:
 *   - huffmanCompress/Decompress и lzwCompress/Decompress дают round-trip;
 *   - huffmanRatio показывает выигрыш на избыточных данных;
 *   - это финальный модуль: Data-Cruncher = загрузка -> анализ -> сжатие.
 *
 * ПОДСКАЗКА:
 *   Переиспользуйте Хаффман (задача 03) и LZW (задача 05). На этом курс завершён:
 *   от анализа сложности (тема 01) до сжатия данных (тема 25) — полный путь.
 */

public class Task07 {
    public static void main(String[] args) {
        Compressor c = new Compressor();
        String data = "result: aaaaabbbbcccd repeated aaaaabbbbcccd";

        String packed = c.huffmanCompress(data);
        System.out.println("Хаффман round-trip ok: " + data.equals(c.huffmanDecompress(packed)));
        System.out.printf("Хаффман коэффициент: %.2fx%n", c.huffmanRatio(data));

        var codes = c.lzwCompress(data);
        System.out.println("LZW round-trip ok: " + data.equals(c.lzwDecompress(codes)));
    }
}
