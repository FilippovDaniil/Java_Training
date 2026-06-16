package m25_multithreading_concurrency.practice;

import java.sql.SQLOutput;

/**
 * Задача 02 — Модуль 25: Поток через Runnable и лямбду
 *
 * ЗАДАНИЕ:
 *   Создайте поток, передав Runnable в конструктор Thread.
 *   Реализуйте Runnable ЛЯМБДОЙ: пусть поток печатает 5 раз "tick"
 *   с паузой 200 мс между выводами (Thread.sleep).
 *   В main запустите поток.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   tick
 *   tick
 *   ... (с паузами)
 *
 * ПОДСКАЗКА:
 *   Runnable r = () -> { ... Thread.sleep(200) ... };
 *   new Thread(r).start();
 *   Thread.sleep бросает InterruptedException — оберните в try-catch.
 */
public class Task02 {
    public static void main(String[] args) {
        // Создайте Runnable лямбдой и запустите поток
        Runnable r = () -> {
            for (int i = 0; i < 7; i++){
                try{
                    Thread.sleep(2000);
                    System.out.println("catch");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
}
