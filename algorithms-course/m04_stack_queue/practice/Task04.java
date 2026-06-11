package m04_stack_queue.practice;

/**
 * Задача 04 — Тема 04: Наивная очередь с приоритетом (min) за O(n)
 *
 * ЗАДАНИЕ:
 *   Реализуйте очередь с приоритетом (минимальную), НЕ используя кучу и
 *   PriorityQueue. Наивно: храните элементы в списке/массиве, а extractMin
 *   ищет минимум линейным проходом. Оцените сложность операций.
 *
 * ПРИМЕР / ВЫВОД:
 *   insert 5, 1, 3
 *   extractMin -> 1
 *   extractMin -> 3
 *   extractMin -> 5
 *
 * ТРЕБОВАНИЯ:
 *   - insert: O(1) (добавить в конец) ИЛИ O(n) (вставка по порядку) — выберите
 *     один вариант и укажите сложность обоих методов в TODO;
 *   - extractMin: если insert O(1), то extractMin O(n) (линейный поиск + удаление);
 *   - extractMin на пустой — исключение.
 *
 * ПОДСКАЗКА:
 *   Простейший вариант: insert = add в ArrayList (O(1)); extractMin = найти
 *   индекс минимума, запомнить значение, удалить его (O(n)). В теме 15 это
 *   ускорится до O(log n) на куче.
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static class NaiveMinPriorityQueue {
        private final List<Integer> items = new ArrayList<>();

        // TODO: сложность? ___
        public void insert(int x) {
            // TODO
        }

        // TODO: сложность? ___
        public int extractMin() {
            // TODO: найти минимум, удалить и вернуть; пусто -> исключение
            return 0;
        }

        public boolean isEmpty() { return items.isEmpty(); }
    }

    public static void main(String[] args) {
        NaiveMinPriorityQueue pq = new NaiveMinPriorityQueue();
        // TODO: insert 5,1,3 и трижды extractMin
    }
}
