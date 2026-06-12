package m13_lists_generics.practice.task07;

/**
 * Задача 07 — Модуль 13 (МИНИ-ПРОЕКТ): Список дел (To-Do)
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой менеджер задач на List<Task>.
 *   Класс задачи (назовите его, например, TodoItem) с полями:
 *     - title (String)
 *     - done (boolean) — выполнена ли.
 *   Реализуйте интерактивное меню в цикле:
 *     1 — добавить задачу (запросить название);
 *     2 — отметить задачу выполненной (по номеру в списке);
 *     3 — показать все задачи (формат: "[ ] Название" или "[x] Название");
 *     4 — удалить задачу по номеру;
 *     0 — выход.
 *
 * ПРИМЕР ВЫВОДА СПИСКА:
 *   1. [x] Купить продукты
 *   2. [ ] Помыть машину
 *   3. [ ] Прочитать главу
 *
 * ПОДСКАЗКИ:
 *   - меню — цикл while(true) + switch по введённому числу + break при 0;
 *   - номера для пользователя начинаются с 1, индексы списка — с 0;
 *   - перед обращением к list.get(index) проверяйте границы;
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        List<TodoItem> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        // Ваш код здесь (меню в цикле)

        scanner.close();
    }
}
