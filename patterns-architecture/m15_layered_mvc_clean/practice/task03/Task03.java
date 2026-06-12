package m15_layered_mvc_clean.practice.task03;

/**
 * Задача 03 — Тема 15: MVC (Model-View-Controller)
 *
 * ЗАДАНИЕ:
 *   Разделите счётчик на три роли:
 *     - CounterModel (файл CounterModel.java): поле value; inc(); value();
 *       (данные и правило — только модель);
 *     - CounterView (файл CounterView.java): render(int v) печатает "Счёт: v"
 *       (только отображение, без логики);
 *     - CounterController (файл CounterController.java): хранит Model и View;
 *       onIncrement() увеличивает модель и просит View показать новое значение.
 *   В main: нажмите «+» дважды через контроллер.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Счёт: 1
 *   Счёт: 2
 *
 * ТРЕБОВАНИЯ:
 *   - Model не знает о View (не печатает сам);
 *   - View не содержит логики (только render);
 *   - Controller связывает ввод → Model → View.
 *
 * ПОДСКАЗКА:
 *   onIncrement(): model.inc(); view.render(model.value());
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: создайте Model, View, Controller; вызовите onIncrement() дважды
    }
}
