/**
 * Задача 06 — Тема 09: Composite + Bridge вместе
 *
 * ЗАДАНИЕ:
 *   Группа графики (Composite), где каждая фигура рисуется через Renderer (Bridge).
 *     - Renderer (файл Renderer.java): String draw(String shapeName);
 *       реализации VectorRenderer ("[vector:NAME]") и RasterRenderer ("[raster:NAME]");
 *     - Graphic (файл Graphic.java) — общий интерфейс Composite: String render();
 *     - ShapeGraphic (файл ShapeGraphic.java) — лист: хранит имя фигуры и Renderer
 *       (мост), render() = renderer.draw(name);
 *     - GraphicGroup (файл GraphicGroup.java) — композит: List<Graphic>, add(...),
 *       render() склеивает render() детей.
 *   В main соберите группу: круг через вектор, квадрат через растр, и вложенную
 *   группу; выведите render() всей группы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [vector:circle][raster:square][vector:triangle]
 *
 * ТРЕБОВАНИЯ:
 *   - Composite: группа и фигура реализуют один Graphic, группы вложенные;
 *   - Bridge: фигура делегирует отрисовку Renderer (вектор/растр комбинируются);
 *   - в одной группе у разных фигур могут быть разные рендереры.
 *
 * ПОДСКАЗКА:
 *   Composite отвечает за СТРУКТУРУ (дерево фигур), Bridge — за СПОСОБ отрисовки
 *   каждой фигуры. Это типичная связка в графических редакторах.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: соберите GraphicGroup из ShapeGraphic (с разными Renderer) и
        //       вложенной группы, выведите render()
    }
}
