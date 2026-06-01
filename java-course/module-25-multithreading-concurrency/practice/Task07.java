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
        // Разбейте диапазон, посчитайте параллельно через Callable/Future
    }
}
