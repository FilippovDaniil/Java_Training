package m12_state_template_method.practice.task04;

/**
 * Задача 04 — Тема 12: Template Method (приготовление напитка)
 *
 * ЗАДАНИЕ:
 *   Классический пример: общий рецепт напитка, разные шаги у кофе и чая.
 *     - абстрактный Beverage (файл Beverage.java) с final prepare():
 *         печатает "вода вскипячена", затем brew(), затем "налито в чашку",
 *         и если wantsCondiments() — печатает addCondiments();
 *       abstract String brew(); abstract String addCondiments();
 *       boolean wantsCondiments() { return true; }   // hook
 *     - Coffee (brew "заварен кофе", addCondiments "добавлено молоко");
 *     - Tea (brew "заварен чай", addCondiments "добавлен лимон",
 *       wantsCondiments → false — без добавок).
 *   В main приготовьте кофе и чай.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   вода вскипячена
 *   заварен кофе
 *   налито в чашку
 *   добавлено молоко
 *   вода вскипячена
 *   заварен чай
 *   налито в чашку
 *
 * ТРЕБОВАНИЯ:
 *   - prepare() — final (порядок шагов фиксирован);
 *   - brew()/addCondiments() реализуют подклассы;
 *   - Tea через hook wantsCondiments()=false пропускает добавки.
 *
 * ПОДСКАЗКА:
 *   Чай не добавляет лимон, потому что переопределил hook на false — добавки
 *   просто не вызываются.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Coffee и Tea, вызовите prepare() у каждого
    }
}
