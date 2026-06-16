package m25_multithreading_concurrency.practice.task05;

/**
 * Задача 05 — Модуль 25: volatile-флаг остановки
 *
 * ЗАДАНИЕ:
 *   Создайте класс Worker (implements Runnable) с volatile-полем
 *   boolean running = true. В run() поток в цикле while(running)
 *   что-то делает (например, печатает "работаю..." с паузой).
 *   Метод stop() выставляет running = false.
 *   В main: запустите поток, дайте поработать ~1 секунду (sleep),
 *   затем вызовите stop() и дождитесь завершения (join).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   работаю...
 *   работаю...
 *   ...
 *   Остановлен
 *
 * ПОДСКАЗКА:
 *   volatile гарантирует, что изменение running сразу увидит рабочий
 *   поток. Без volatile он может «не заметить» остановку.
 */

public class Task05 {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();

        // Даём поработать ~1 секунду
        Thread.sleep(1000);

        // Останавливаем
        worker.stop();
        System.out.println("Остановлен");

        // Дожидаемся завершения потока
        thread.join();
    }
}
