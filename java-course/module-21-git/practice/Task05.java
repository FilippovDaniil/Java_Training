/**
 * Задача 05 — Модуль 21: Ветки (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Создайте новую ветку feature-greeting и переключитесь на неё.
 *   2. В этой ветке измените hello.txt и сделайте коммит.
 *   3. Переключитесь обратно на main и убедитесь, что там изменений нет.
 *   4. Посмотрите список веток.
 *
 * ОЖИДАЕМЫЕ КОМАНДЫ:
 *   git switch -c feature-greeting   (или git checkout -b feature-greeting)
 *   ... правка файла ...
 *   git add . && git commit -m "Правка в ветке"
 *   git switch main
 *   git branch
 *
 * ПРОВЕРКА:
 *   В main файл без изменений из ветки; git branch показывает две ветки.
 */
public class Task05 {
    public static void main(String[] args) {
        System.out.println("Выполните задание командами Git в терминале (см. комментарий выше).");
    }
}
