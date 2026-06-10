/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 21: планировщик задач с зависимостями
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher DependencyScheduler (файл DependencyScheduler.java):
 *   по графу зависимостей задач постройте корректный порядок выполнения
 *   (топосортировка) и обнаруживайте циклические зависимости.
 *
 * ПРИМЕР / ВЫВОД:
 *   5 задач; зависимости: 0->1, 0->2, 1->3, 2->3, 3->4
 *   порядок выполнения: [0, 1, 2, 3, 4]  (каждая после своих зависимостей)
 *   hasCycle: false
 *   если добавить 4->0 -> hasCycle: true, порядок: [] (невыполнимо)
 *
 * ТРЕБОВАНИЯ:
 *   - executionOrder — топосортировка (Кан);
 *   - hasCycle — true, если зависимости образуют цикл;
 *   - циклический случай -> пустой порядок.
 *
 * ПОДСКАЗКА:
 *   Это «боевое» применение топосортировки: сборка проекта, расписание задач.
 */

public class Task07 {
    public static void main(String[] args) {
        DependencyScheduler s = new DependencyScheduler(5);
        s.addDependency(0, 1); s.addDependency(0, 2);
        s.addDependency(1, 3); s.addDependency(2, 3); s.addDependency(3, 4);
        System.out.println("порядок: " + s.executionOrder());
        System.out.println("hasCycle: " + s.hasCycle());
    }
}
