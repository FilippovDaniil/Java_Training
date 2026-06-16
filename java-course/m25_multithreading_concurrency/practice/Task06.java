package m25_multithreading_concurrency.practice;

/**
 * Задача 06 — Модуль 25: ExecutorService (пул потоков)
 *
 * ЗАДАНИЕ:
 *   Создайте пул из 3 потоков (Executors.newFixedThreadPool).
 *   Отправьте в него 9 задач: каждая печатает свой номер и имя потока,
 *   который её выполняет. После отправки корректно завершите пул
 *   (shutdown).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример, порядок может отличаться):
 *   Задача 0 в pool-1-thread-1
 *   Задача 1 в pool-1-thread-2
 *   Задача 2 в pool-1-thread-3
 *   Задача 3 в pool-1-thread-1
 *   ...
 *
 * ПОДСКАЗКА:
 *   ExecutorService pool = Executors.newFixedThreadPool(3);
 *   pool.submit(() -> ... Thread.currentThread().getName() ...);
 *   pool.shutdown();
 *   Обратите внимание: 9 задач выполняют всего 3 потока.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task06 {
    public static void main(String[] args) {
        // 1. Создаём пул из 3 потоков
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 2. Отправляем 9 задач
        for (int i = 0; i < 9; i++) {
            int taskId = i; // для использования в лямбде
            pool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Задача " + taskId + " в " + threadName);
            });
        }

        // 3. Завершаем пул (новые задачи не принимаются)
        pool.shutdown();

        // (Необязательно) дождаться окончания всех задач – для примера
        // try {
        //     if (!pool.awaitTermination(1, TimeUnit.MINUTES)) {
        //         pool.shutdownNow();
        //     }
        // } catch (InterruptedException e) {
        //     pool.shutdownNow();
        // }
    }
}