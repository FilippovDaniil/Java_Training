package m25_multithreading_concurrency.practice.task04;

/**
 * Задача 04 — Модуль 25: Состояние гонки и synchronized
 *
 * ЗАДАНИЕ:
 *   1. Создайте класс Counter с полем count и методом increment().
 *   2. Запустите ДВА потока, каждый вызывает increment() 100000 раз.
 *   3. Дождитесь обоих (join) и выведите итог.
 *   4. Сначала БЕЗ synchronized — обратите внимание, что итог обычно
 *      меньше 200000 (состояние гонки).
 *   5. Затем сделайте increment() synchronized — итог станет ровно 200000.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Без synchronized: <случайное число < 200000>
 *   С synchronized: 200000
 *
 * ПОДСКАЗКА:
 *   count++ не атомарна. synchronized гарантирует, что метод выполняет
 *   один поток за раз.
 */

public class Task04 {
    public static void main(String[] args) throws InterruptedException {
        // Запустите 2 потока, инкрементирующих общий Counter, сравните итоги

        Counter counter = new Counter();
        Runnable r = () -> {
            for (int i = 0; i < 10000; i++){
                counter.increment();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        Thread thread1 = new Thread(r);
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(counter.getCount());



    }
}
