/**
 * Задача 06 — Тема 10: Strategy + Command вместе
 *
 * ЗАДАНИЕ:
 *   Команда «отсортируй массив» инкапсулирует действие, а КАК сортировать —
 *   решает стратегия:
 *     - интерфейс SortStrategy (файл SortStrategy.java): void sort(int[] arr);
 *       реализации AscendingSort (по возрастанию) и DescendingSort (по убыванию);
 *     - интерфейс Command (файл Command.java): void execute();
 *     - SortCommand (файл SortCommand.java): хранит int[] и SortStrategy,
 *       execute() сортирует массив выбранной стратегией.
 *   В main отсортируйте один массив по возрастанию, другой — по убыванию,
 *   обе операции через SortCommand.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   По возрастанию: [1, 2, 3, 5]
 *   По убыванию: [5, 3, 2, 1]
 *
 * ТРЕБОВАНИЯ:
 *   - команда не знает алгоритм сортировки — он задаётся стратегией;
 *   - стратегия не знает, что её запускают через команду;
 *   - комбинация: Command = «что сделать», Strategy = «как именно».
 *
 * ПОДСКАЗКА:
 *   SortCommand.execute() = strategy.sort(arr). Можно реализовать сортировку
 *   простым способом (например, Arrays.sort + при убывании развернуть).
 */

import java.util.Arrays;

public class Task06 {
    public static void main(String[] args) {
        // TODO: создайте SortCommand с AscendingSort и с DescendingSort, execute(),
        //       выведите массивы (Arrays.toString)
    }
}
