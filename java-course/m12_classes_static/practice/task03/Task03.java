package m12_classes_static.practice.task03;

/**
 * Задача 03 — Модуль 12: Статические константы
 *
 * ЗАДАНИЕ:
 *   В классе Physics объявите статические константы:
 *     - SPEED_OF_LIGHT = 299792458 (м/с)
 *     - GRAVITY = 9.81 (м/с^2)
 *   Используйте их в main для расчёта: путь свободного падения за
 *   t секунд = GRAVITY * t * t / 2.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (для t=3):
 *   Скорость света: 299792458
 *   Путь падения за 3 c: 44.145
 *
 * ПОДСКАЗКА:
 *   static final double GRAVITY = 9.81;
 *   Имена констант — БОЛЬШИМИ_БУКВАМИ.
 */

public class Task03 {
    public static void main(String[] args) {
        int t = 3;
        System.out.println(padenie(t));
        // Используйте константы Physics для расчётов
    }

    private static String padenie(int t){
        return String.format("%.3f",Physics.GRAVITY * t * t / 2);
    }
}
