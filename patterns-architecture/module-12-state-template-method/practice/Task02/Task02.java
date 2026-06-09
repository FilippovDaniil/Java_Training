/**
 * Задача 02 — Тема 12: State (светофор, циклические переходы)
 *
 * ЗАДАНИЕ:
 *   Реализуйте светофор как конечный автомат с циклом RED → GREEN → YELLOW → RED:
 *     - интерфейс TrafficLightState (файл TrafficLightState.java):
 *       TrafficLightState next(); String color();
 *     - RedState (next → Green), GreenState (next → Yellow), YellowState (next → Red);
 *     - TrafficLight (файл TrafficLight.java) — контекст: next() меняет состояние,
 *       color() возвращает текущий цвет.
 *   В main сделайте 4 переключения next(), печатая цвет, — убедитесь, что после
 *   YELLOW снова RED (цикл замкнулся).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   RED
 *   GREEN
 *   YELLOW
 *   RED
 *
 * ТРЕБОВАНИЯ:
 *   - переходы заданы в самих состояниях (без switch в контексте);
 *   - цикл замыкается (YELLOW → RED);
 *   - добавление состояния не требует правок контекста.
 *
 * ПОДСКАЗКА:
 *   Каждое состояние знает только своего «преемника»: Red.next() = new GreenState().
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: создайте TrafficLight, выведите цвет, затем 3 раза next() с выводом цвета
    }
}
