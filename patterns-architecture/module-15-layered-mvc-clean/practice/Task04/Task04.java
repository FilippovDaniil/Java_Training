/**
 * Задача 04 — Тема 15: MVC (список задач)
 *
 * ЗАДАНИЕ:
 *   Список задач по MVC:
 *     - TodoModel (файл TodoModel.java): хранит List<String> задач; add(String);
 *       items() возвращает список;
 *     - TodoView (файл TodoView.java): render(List<String>) печатает задачи
 *       по строкам с номером ("1. ...");
 *     - TodoController (файл TodoController.java): addTask(String) добавляет в
 *       модель и просит View перерисовать весь список.
 *   В main добавьте две задачи через контроллер.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   1. купить хлеб
 *   ---
 *   1. купить хлеб
 *   2. позвонить маме
 *
 * ТРЕБОВАНИЯ:
 *   - модель только хранит задачи (без печати);
 *   - view только рисует переданный список (без логики);
 *   - контроллер на каждое добавление обновляет модель и вызывает render.
 *
 * ПОДСКАЗКА:
 *   addTask(t): model.add(t); view.render(model.items());
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Model/View/Controller; addTask дважды
    }
}
