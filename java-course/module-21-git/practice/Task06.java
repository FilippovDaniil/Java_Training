/**
 * Задача 06 — Модуль 21: Слияние веток (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Находясь на ветке main, слейте в неё ветку feature-greeting
 *      (созданную в Task05).
 *   2. Убедитесь, что изменения из ветки появились в main.
 *   3. Удалите ветку feature-greeting (она больше не нужна).
 *   4. Просмотрите историю — изменения из ветки должны быть в логе.
 *
 * ОЖИДАЕМЫЕ КОМАНДЫ:
 *   git switch main
 *   git merge feature-greeting
 *   git branch -d feature-greeting
 *   git log --oneline
 *
 * ПРОВЕРКА:
 *   hello.txt в main содержит изменения из ветки;
 *   git branch показывает только main.
 */
public class Task06 {
    public static void main(String[] args) {
        System.out.println("Выполните задание командами Git в терминале (см. комментарий выше).");
    }
}
