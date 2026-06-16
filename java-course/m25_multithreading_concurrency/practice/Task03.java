package m25_multithreading_concurrency.practice;

/**
 * Задача 03 — Модуль 25: Несколько потоков и join
 *
 * ЗАДАНИЕ:
 *   Запустите ТРИ потока, каждый из которых печатает свой номер
 *   5 раз. В main дождитесь завершения ВСЕХ потоков через join(),
 *   и только потом выведите "Все потоки завершены".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (порядок строк потоков может чередоваться):
 *   Поток 1: ...
 *   Поток 2: ...
 *   ...
 *   Все потоки завершены
 *
 * ПОДСКАЗКА:
 *   Сохраните потоки в массив, запустите все start(), затем в цикле
 *   вызовите join() для каждого. join() бросает InterruptedException.
 */
public class Task03 {
    public static void main(String[] args) throws InterruptedException {
        // Создайте 3 потока, запустите, дождитесь через join()
        CountThread[] threads = {
                new CountThread(),
                new CountThread(),
                new CountThread()
        };

            try {
                for (CountThread thread : threads) {
                    thread.start();
                    thread.join();
                }
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

