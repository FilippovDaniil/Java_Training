package m21_git.practice;

/**
 * Задача 04 — Модуль 21: .gitignore (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   1. Создайте в репозитории файл, который НЕ нужно отслеживать,
 *      например temp.log, и папку target/ с файлом внутри.
 *   2. Создайте файл .gitignore и добавьте в него правила игнорирования
 *      (*.log и target/).
 *   3. Убедитесь через git status, что игнорируемые файлы больше
 *      не отображаются как untracked.
 *   4. Закоммитьте .gitignore.
 *
 * СОДЕРЖИМОЕ .gitignore:
 *   *.log
 *   target/
 *
 * ПРОВЕРКА:
 *   git status не показывает temp.log и target/;
 *   .gitignore попал в коммит.
 */
public class Task04 {
    public static void main(String[] args) {
        System.out.println("Выполните задание командами Git в терминале (см. комментарий выше).");
    }
}
