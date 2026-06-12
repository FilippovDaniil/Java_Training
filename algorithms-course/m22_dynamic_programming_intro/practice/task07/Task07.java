package m22_dynamic_programming_intro.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 22: рюкзак + схожесть строк
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher OptimizerModule (файл OptimizerModule.java):
 *     - bestSelection — оптимальный набор объектов под лимит (рюкзак 0/1);
 *     - similarity — схожесть двух строк через редакционное расстояние.
 *
 * ПРИМЕР / ВЫВОД:
 *   объекты: веса=[2,3,4,5], ценности=[3,4,5,6], лимит=5
 *   лучший набор по ценности = 7   (объекты с весами 2 и 3: 3+4)
 *   similarity("data", "date") ≈ 0.75   (1 правка из 4 символов)
 *
 * ТРЕБОВАНИЯ:
 *   - bestSelection — DP рюкзака; similarity — на базе Левенштейна;
 *   - similarity в диапазоне [0..1] (1 = строки идентичны);
 *   - переиспользуйте DP из задач 03 и 05.
 *
 * ПОДСКАЗКА:
 *   Это «боевое» применение DP: подбор оптимального набора и сравнение текстов.
 */

public class Task07 {
    public static void main(String[] args) {
        OptimizerModule m = new OptimizerModule();
        System.out.println("лучший набор = " + m.bestSelection(new int[]{2,3,4,5}, new int[]{3,4,5,6}, 5));
        System.out.printf("similarity = %.2f%n", m.similarity("data", "date"));
    }
}
