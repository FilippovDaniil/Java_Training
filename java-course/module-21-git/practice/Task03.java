/**
 * Задача 03 — Модуль 21: Изменения и diff (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Измените содержимое hello.txt (допишите строку).
 *   2. Посмотрите, что именно изменилось, через git diff.
 *   3. Добавьте изменения в индекс и сравните git diff и git diff --staged.
 *   4. Закоммитьте изменения.
 *
 * ОЖИДАЕМЫЕ КОМАНДЫ:
 *   git diff
 *   git add hello.txt
 *   git diff --staged
 *   git commit -m "Обновил hello.txt"
 *
 * ПРОВЕРКА:
 *   git log --oneline показывает два коммита.
 *   git diff (после commit) пуст — нет незафиксированных изменений.
 */
public class Task03 {
    public static void main(String[] args) {
        System.out.println("Выполните задание командами Git в терминале (см. комментарий выше).");
    }
}
