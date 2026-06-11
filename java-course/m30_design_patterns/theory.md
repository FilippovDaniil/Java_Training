# Модуль 30. Паттерны проектирования (GoF)

**Паттерн проектирования** — типовое проверенное решение часто встречающейся задачи проектирования. Их описала «банда четырёх» (Gang of Four, GoF) и разделила на три группы.

```
                  Паттерны GoF
        ┌──────────────┼──────────────┐
   Порождающие     Структурные    Поведенческие
   (создание       (композиция    (взаимодействие
    объектов)       объектов)      объектов)
```

| Группа | Назначение | Примеры |
|--------|-----------|---------|
| Порождающие | как создавать объекты | Singleton, Factory Method, Abstract Factory, Builder, Prototype |
| Структурные | как собирать из объектов структуры | Adapter, Decorator, Facade, Proxy, Composite, Bridge, Flyweight |
| Поведенческие | как объекты взаимодействуют | Strategy, Observer, Command, Iterator, State, Template Method, Chain of Responsibility |

---

## Порождающие паттерны

### Singleton — единственный экземпляр

Гарантирует, что у класса ровно один объект и даёт глобальную точку доступа.

```java
class Config {
    private static final Config INSTANCE = new Config();
    private Config() {}                       // приватный конструктор
    public static Config getInstance() { return INSTANCE; }
}
```

> Лучший способ в Java — через `enum` (см. [модуль 16](../module-16-enums-switch/theory.md)): потокобезопасно из коробки.

### Factory Method — фабричный метод

Делегирует создание объекта методу, скрывая конкретный класс.

```java
interface Transport { void deliver(); }
class Truck implements Transport { public void deliver() { System.out.println("По дороге"); } }
class Ship  implements Transport { public void deliver() { System.out.println("По морю"); } }

class TransportFactory {
    static Transport create(String type) {
        return switch (type) {
            case "truck" -> new Truck();
            case "ship"  -> new Ship();
            default -> throw new IllegalArgumentException("Неизвестный тип");
        };
    }
}
```

### Builder — пошаговое построение

Создаёт сложный объект по шагам, избегая «телескопических» конструкторов.

```java
Pizza pizza = new Pizza.Builder()
    .size(30)
    .cheese(true)
    .pepperoni(true)
    .build();
```

### Prototype — клонирование

Создаёт объект копированием существующего (`clone()`), а не через `new`.

---

## Структурные паттерны

### Adapter — адаптер

Преобразует интерфейс класса в другой, ожидаемый клиентом. «Переходник».

```java
// есть OldPrinter с методом printOld(), нужен интерфейс Printer.print()
class PrinterAdapter implements Printer {
    private final OldPrinter old;
    PrinterAdapter(OldPrinter old) { this.old = old; }
    public void print(String s) { old.printOld(s); }
}
```

### Decorator — декоратор

Добавляет поведение, оборачивая объект (подробно — в [модуле 19](../module-19-io-nio/theory.md)).

### Facade — фасад

Простой единый интерфейс к сложной подсистеме.

```java
class Computer {
    void start() {                 // фасад скрывает детали
        cpu.boot();
        memory.load();
        disk.read();
    }
}
```

### Proxy — заместитель

Объект-«посредник», контролирующий доступ к другому объекту (ленивая загрузка, кеш, проверка прав, логирование).

```java
class ImageProxy implements Image {
    private RealImage real;
    private final String file;
    ImageProxy(String file) { this.file = file; }
    public void display() {
        if (real == null) real = new RealImage(file);  // ленивая загрузка
        real.display();
    }
}
```

---

## Поведенческие паттерны

### Strategy — стратегия

Семейство взаимозаменяемых алгоритмов; выбор — во время выполнения.

```java
interface SortStrategy { void sort(int[] arr); }
class QuickSort  implements SortStrategy { public void sort(int[] a) { /* ... */ } }
class BubbleSort implements SortStrategy { public void sort(int[] a) { /* ... */ } }

class Sorter {
    private SortStrategy strategy;
    Sorter(SortStrategy s) { this.strategy = s; }
    void execute(int[] a) { strategy.sort(a); }
}
```

> Лямбды (см. [модуль 24](../module-24-stream-api-lambda/theory.md)) — частая лёгкая реализация Strategy.

### Observer — наблюдатель

Объекты-подписчики автоматически уведомляются об изменении субъекта.

```java
interface Observer { void update(String event); }

class Subject {
    private final List<Observer> observers = new ArrayList<>();
    void subscribe(Observer o) { observers.add(o); }
    void notifyAll(String event) {
        for (Observer o : observers) o.update(event);
    }
}
```

```
   Subject ──notify──▶ Observer1
           ──notify──▶ Observer2
           ──notify──▶ Observer3
```

### Command — команда

Инкапсулирует запрос в объект (для очередей, отмены, логирования).

### Template Method — шаблонный метод

Скелет алгоритма в базовом классе; шаги переопределяют потомки (см. абстрактные классы, [модуль 23](../module-23-oop-overloading-overriding-abstract/theory.md)).

---

## Антипаттерны (чего избегать)

| Антипаттерн | Проблема |
|-------------|----------|
| God Object | один класс делает всё |
| Spaghetti Code | запутанные связи, нет структуры |
| Singleton-абьюз | глобальное состояние повсюду |
| Copy-Paste | дублирование вместо переиспользования |

---

## Когда применять паттерны

> Паттерны — инструмент, а не цель. Не «впихивайте» их ради паттернов. Применяйте, когда задача действительно соответствует проблеме, которую паттерн решает. Часто простой код лучше «навёрнутого».

## Дополнительные источники

- «Design Patterns» (Gamma, Helm, Johnson, Vlissides) — первоисточник.
- «Head First Design Patterns» — наглядное введение.
- refactoring.guru — иллюстрированные разборы паттернов.

## Что дальше

В [модуле 31](../module-31-dev-methodologies/theory.md) — методологии разработки.
