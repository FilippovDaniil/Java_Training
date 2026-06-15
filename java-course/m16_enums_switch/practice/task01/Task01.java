package m16_enums_switch.practice.task01;

/**
 * Задача 01 — Модуль 16: Базовый enum
 *
 * ЗАДАНИЕ:
 *   Объявите enum Season (WINTER, SPRING, SUMMER, AUTUMN).
 *   В main переберите все значения через values() и выведите каждое
 *   вместе с его порядковым номером (ordinal).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   0: WINTER
 *   1: SPRING
 *   2: SUMMER
 *   3: AUTUMN
 *
 * ПОДСКАЗКА:
 *   for (Season s : Season.values()) { s.ordinal(); s.name(); }
 */
public class Task01 {
    public static void main(String[] args) {
        // Переберите Season.values()
        Season season = Season.SPRING;
        System.out.println(season);
        for (Season s : Season.values()){
            System.out.println(s.ordinal() + ": " + s.name());
        }
    }
}

// TODO: объявите enum Season
