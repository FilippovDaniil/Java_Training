/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 01: ComplexityAnalyzer
 *
 * ЗАДАНИЕ:
 *   Заложите фундамент сквозного проекта Data-Cruncher — «аналитической
 *   системы обработки данных». Создайте модуль ComplexityAnalyzer
 *   (файл ComplexityAnalyzer.java), который принимает массив и прогоняет
 *   на нём несколько алгоритмов с РАЗНОЙ сложностью, замеряя время:
 *     - min(arr)            — O(n)
 *     - hasDuplicatePairs   — O(n^2) (наивный перебор пар)
 *     - isSortedLog (через бинарный признак) или sumLogarithmicProbe — O(log n)-проба
 *   Метод analyze(int[]) печатает таблицу: алгоритм, сложность, время (мс).
 *
 * ПРИМЕР / ВЫВОД:
 *   === Data-Cruncher: ComplexityAnalyzer (n=5000) ===
 *   min(arr)            O(n)     t=  0.04 ms
 *   hasDuplicatePairs   O(n^2)   t= 51.20 ms
 *   probeLogarithmic    O(log n) t=  0.00 ms
 *
 * ТРЕБОВАНИЯ:
 *   - ComplexityAnalyzer — отдельный класс с методом analyze(int[]);
 *   - каждый замеряемый алгоритм — отдельный метод;
 *   - время через System.nanoTime(); печать в мс;
 *   - этот модуль будет расти в следующих темах — пишите аккуратно.
 *
 * ПОДСКАЗКА:
 *   Сгенерируйте массив в main (например случайные числа) и передайте в analyze.
 *   Для замера заведите вспомогательный метод time(Runnable r): long.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: создать массив (например 5000 случайных чисел),
        //       создать ComplexityAnalyzer и вызвать analyze(arr)
    }
}
