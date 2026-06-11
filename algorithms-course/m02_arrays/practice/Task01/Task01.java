package m02_arrays.practice.task01;

/**
 * Задача 01 — Тема 02: Свой DynamicArray
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс DynamicArray (файл DynamicArray.java) с методами
 *   add, get, remove, size. Внутри — обычный int[], который растёт при
 *   заполнении (удвоение ёмкости). В main продемонстрируйте работу.
 *
 * ПРИМЕР / ВЫВОД:
 *   size=3, [10, 20, 30]
 *   после remove(1): size=2, [10, 30]
 *
 * ТРЕБОВАНИЯ:
 *   - get/add по индексу — O(1) (add амортизированно);
 *   - remove(index) сдвигает хвост — O(n);
 *   - выход за границы — IndexOutOfBoundsException;
 *   - НЕ использовать java.util.ArrayList — пишем свой.
 *
 * ПОДСКАЗКА:
 *   Сдвиг при remove удобно делать через System.arraycopy(data, index+1, data, index, size-index-1).
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создать DynamicArray, добавить несколько элементов,
        //       вывести size и содержимое, удалить элемент, снова вывести
    }
}
