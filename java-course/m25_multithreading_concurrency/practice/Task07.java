package m25_multithreading_concurrency.practice;

/**
 * Задача 07 — Модуль 25 (МИНИ-ПРОЕКТ): Параллельный подсчёт суммы
 *
 * ЗАДАНИЕ:
 *   Посчитайте сумму чисел от 1 до 1_000_000 ПАРАЛЛЕЛЬНО, разбив
 *   работу между потоками с помощью Callable и Future.
 *   1. Разбейте диапазон на 4 части.
 *   2. Для каждой части создайте Callable<Long>, считающий сумму своей
 *      части.
 *   3. Отправьте все задачи в ExecutorService (submit) — получите
 *      список Future<Long>.
 *   4. Соберите частичные результаты (future.get()) и сложите их.
 *   5. Сравните с эталоном n*(n+1)/2 и выведите оба значения.
 *   6. Завершите пул (shutdown).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Параллельная сумма: 500000500000
 *   Эталон: 500000500000
 *
 * ПОДСКАЗКИ:
 *   - Callable<Long> возвращает значение (в отличие от Runnable);
 *   - храните задачи/Future в списках; собирайте через future.get();
 *   - future.get() бросает проверяемые исключения — обработайте их;
 *   - используйте long для сумм (числа большие).
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task07 {
    public static void main(String[] args) throws Exception {
        int n = 1_000_000;
        int numThreads = 4;
        long chunkSize = n / numThreads; // 250_000

        ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        // 1. Разбиваем диапазон и отправляем задачи
        for (int i = 0; i < numThreads; i++) {
            long start = i * chunkSize + 1;
            long end = (i == numThreads - 1) ? n : (i + 1) * chunkSize;
            Callable<Long> task = () -> {
                long sum = 0;
                for (long j = start; j <= end; j++) {
                    sum += j;
                }
                return sum;
            };
            futures.add(pool.submit(task));
        }

        // 2. Собираем результаты
        long parallelSum = 0;
        for (Future<Long> future : futures) {
            parallelSum += future.get(); // может выбросить исключение
        }

        // 3. Эталонная сумма
        long expectedSum = n * (n + 1L) / 2L;

        System.out.println("Параллельная сумма: " + parallelSum);
        System.out.println("Эталон: " + expectedSum);

        // 4. Завершаем пул
        pool.shutdown();
    }
}