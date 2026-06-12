package m06_prototype_pool.practice.task02;

/**
 * Задача 02 — Тема 06: Prototype через Cloneable / clone()
 *
 * ЗАДАНИЕ:
 *   Реализуйте Point (файл Point.java) с поддержкой clone():
 *     - поля x, y (int);
 *     - implements Cloneable;
 *     - @Override public Point clone() — через super.clone() (с обработкой
 *       CloneNotSupportedException).
 *   В main создайте точку, клонируйте, измените координаты КЛОНА и покажите,
 *   что оригинал не изменился (для примитивов поверхностной копии достаточно).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Оригинал: (1, 2)
 *   Клон после сдвига: (10, 20)
 *   Оригинал: (1, 2)
 *
 * ТРЕБОВАНИЯ:
 *   - реализован интерфейс Cloneable;
 *   - clone() использует super.clone() и возвращает тип Point (ковариантный тип);
 *   - изменение клона не трогает оригинал (поля примитивные).
 *
 * ПОДСКАЗКА:
 *   try { return (Point) super.clone(); }
 *   catch (CloneNotSupportedException e) { throw new AssertionError(e); }
 *   Помните: для ИЗМЕНЯЕМЫХ полей-объектов super.clone() даёт лишь поверхностную
 *   копию — там пришлось бы копировать вложенные объекты вручную (см. Task03).
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: создайте Point, клонируйте, измените клон, сравните с оригиналом
    }
}
