# Тема 09. Composite и Bridge

Последние структурные паттерны раздела. **Composite** собирает объекты в дерево и позволяет работать с веткой и листом единообразно. **Bridge** разделяет абстракцию и реализацию, чтобы они развивались независимо.

```
Composite — дерево «часть-целое»; лист и контейнер имеют один интерфейс
Bridge    — две независимые иерархии: «что» (абстракция) и «как» (реализация)
```

---

## 1. Composite — дерево «часть-целое»

Когда объекты образуют иерархию «контейнер содержит элементы, а элемент может быть другим контейнером», Composite даёт **общий интерфейс** и листу, и контейнеру. Клиент не различает их.

```java
interface FileSystemNode { long size(); }          // общий интерфейс

class FileLeaf implements FileSystemNode {           // лист
    private final long size;
    FileLeaf(long size) { this.size = size; }
    public long size() { return size; }
}

class Directory implements FileSystemNode {          // контейнер (композит)
    private final List<FileSystemNode> children = new ArrayList<>();
    void add(FileSystemNode node) { children.add(node); }
    public long size() {                             // рекурсивно суммирует детей
        long total = 0;
        for (FileSystemNode c : children) total += c.size();
        return total;
    }
}
```

```
        Directory
        +-- FileLeaf (10)
        +-- Directory
        |   +-- FileLeaf (5)
        |   +-- FileLeaf (5)
        +-- FileLeaf (20)
   size() рекурсивно: 10 + (5+5) + 20 = 40
```

Главная выгода: операция (`size()`, обход, рендер) вызывается единообразно — на листе она тривиальна, на контейнере рекурсивно обходит детей. Клиент не пишет `if (node instanceof Directory)`.

> Где встречается: дерево файлов, DOM/HTML, GUI-компоненты (панель содержит панели и кнопки), меню, орг-структура.

---

## 2. Bridge — мост между абстракцией и реализацией

Когда есть **две оси изменчивости** (например, «фигура» × «способ отрисовки»), наследование даёт комбинаторный взрыв классов (`VectorCircle`, `RasterCircle`, `VectorSquare`, ...). Bridge разносит их в **две иерархии**, связанные композицией.

```java
// реализация ("как рисовать") — отдельная иерархия
interface Renderer { String renderCircle(int r); }
class VectorRenderer implements Renderer { public String renderCircle(int r){ return "vector circle r="+r; } }
class RasterRenderer implements Renderer { public String renderCircle(int r){ return "raster circle r="+r; } }

// абстракция ("что рисуем") хранит ссылку на реализацию — это и есть «мост»
abstract class Shape {
    protected final Renderer renderer;                // мост к реализации
    Shape(Renderer renderer) { this.renderer = renderer; }
    abstract String draw();
}
class Circle extends Shape {
    private final int r;
    Circle(int r, Renderer renderer) { super(renderer); this.r = r; }
    String draw() { return renderer.renderCircle(r); } // делегирует реализации
}
```

```
   Абстракция (Shape)            Реализация (Renderer)
   + Circle -------мост(ссылка)--▶ VectorRenderer
   + Square                       + RasterRenderer
   N фигур × M рендереров = N+M классов, а не N*M
```

Использование: `new Circle(5, new VectorRenderer())` — фигуру и рендерер комбинируем свободно.

---

## Composite vs Decorator vs Bridge

| | Composite | Decorator | Bridge |
|---|-----------|-----------|--------|
| Структура | дерево из многих объектов | цепочка обёрток (1 объект) | две параллельные иерархии |
| Цель | работать с деревом единообразно | добавить поведение | развязать «что» и «как» |
| Связь | контейнер → много детей (один интерфейс) | обёртка → один inner (тот же интерфейс) | абстракция → реализация (разные интерфейсы) |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| `instanceof Directory` в клиенте | теряется смысл Composite | единый интерфейс + полиморфная операция |
| Методы `add/remove` в общем интерфейсе листа | у листа бессмысленный `add` | держать `add/remove` только в композите (компромисс «прозрачность vs безопасность») |
| Bridge подменяют наследованием | взрыв классов N*M | две иерархии + композиция |
| Путают Bridge и Strategy | оба — делегирование реализации | Bridge задумывается заранее как ось абстракция/реализация; Strategy — взаимозаменяемый алгоритм |
| Глубокая рекурсия в Composite | переполнение стека на гигантских деревьях | для очень больших деревьев — итеративный обход |

---

## 🔗 Связь с другими темами

- **Композиция (тема 01)** — оба паттерна на ней держатся.
- **Decorator (тема 07)** — родственная обёртка; Composite — про дерево, Decorator — про цепочку.
- **Visitor (тема 13)** — операции над деревом-Composite удобно выносить в Visitor.
- **Strategy (тема 10)** — близок к Bridge по механике делегирования.

## ➡️ Что дальше

Структурные паттерны пройдены. Тема 10 открывает **поведенческие паттерны** (Strategy, Command): как сделать алгоритмы взаимозаменяемыми и как превратить запрос в объект (с поддержкой отмены).
