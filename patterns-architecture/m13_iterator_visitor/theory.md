# Тема 13. Iterator и Visitor

Завершаем поведенческие паттерны. **Iterator** даёт единый способ обходить элементы коллекции, не раскрывая её устройство. **Visitor** позволяет добавлять новые операции над иерархией объектов, не меняя сами объекты.

```
Iterator — последовательный обход элементов без знания внутренней структуры
Visitor  — новая операция над иерархией без изменения её классов (double dispatch)
```

---

## 1. Iterator — обход без раскрытия структуры

Iterator отделяет **обход** от **хранения**. В Java встроен в язык: реализуй `Iterable<T>` (метод `iterator()`), и коллекция работает в `for-each`.

```java
class Bag<T> implements Iterable<T> {
    private final List<T> items = new ArrayList<>();
    public void add(T item) { items.add(item); }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {                 // свой итератор
            private int i = 0;
            public boolean hasNext() { return i < items.size(); }
            public T next() { return items.get(i++); }
        };
    }
}

Bag<String> bag = new Bag<>();
for (String s : bag) { ... }                      // for-each работает «бесплатно»
```

```
   for-each ──▶ iterator() ──▶ hasNext()/next()
   (клиент не знает, что внутри — массив, список или дерево)
```

Можно сделать **специальные** итераторы: по диапазону, с фильтром, в обратном порядке — клиент использует их одинаково.

> Где встречается: весь Java Collections Framework; `Iterable`/`Iterator`; `Stream` как ленивый «обходчик»; курсоры БД.

---

## 2. Visitor — операции над иерархией

Когда над иерархией классов нужно добавлять **много разных операций** (площадь, отрисовка, экспорт), складывать их методами в каждый класс — значит раздувать классы и нарушать SRP/OCP. Visitor выносит операцию наружу.

Механика — **double dispatch**: элемент вызывает `accept(visitor)`, а тот вызывает у визитора метод, специфичный для типа элемента.

```java
interface ShapeVisitor { void visitCircle(Circle c); void visitSquare(Square s); }

interface Shape { void accept(ShapeVisitor v); }
class Circle implements Shape {
    final int r; Circle(int r){ this.r = r; }
    public void accept(ShapeVisitor v){ v.visitCircle(this); }   // 2-я диспетчеризация
}
class Square implements Shape {
    final int side; Square(int s){ this.side = s; }
    public void accept(ShapeVisitor v){ v.visitSquare(this); }
}

class AreaVisitor implements ShapeVisitor {       // новая ОПЕРАЦИЯ — без правок Circle/Square
    long total = 0;
    public void visitCircle(Circle c){ total += (long)(Math.PI*c.r*c.r); }
    public void visitSquare(Square s){ total += (long)s.side*s.side; }
}
```

```
   shape.accept(visitor)         (1-я диспетчеризация — по типу shape)
        └──▶ visitor.visitXxx(this)   (2-я — по типу visitor + точный тип элемента)
```

Чтобы добавить операцию (например, «периметр») — пишем новый Visitor, классы фигур не трогаем.

### Цена Visitor

Visitor облегчает добавление **операций**, но усложняет добавление **новых типов элементов** (нужно дописать метод во все визиторы). Применяй, когда иерархия типов стабильна, а операций над ней — много.

> Где встречается: обход AST компиляторов; сериализация/экспорт древовидных структур; обход Composite (тема 09).

---

## Iterator vs Visitor

| | Iterator | Visitor |
|---|----------|---------|
| Задача | перебрать элементы | выполнить операцию над каждым типом |
| Знает типы элементов | нет (работает единообразно) | да (метод на каждый тип) |
| Что легко добавлять | новые способы обхода | новые операции (визиторы) |
| Что трудно | — | новые типы элементов |

Часто работают вместе: итератор перебирает элементы, для каждого вызывается `accept(visitor)`.

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| `next()` без проверки `hasNext()` | NoSuchElementException | проверяй hasNext или лови исключение |
| Модификация коллекции во время обхода | ConcurrentModificationException | `iterator.remove()` или копия |
| Visitor с `instanceof` вместо accept | теряется суть Visitor | double dispatch через accept |
| Новый тип элемента | надо дописать во ВСЕ визиторы | применяй Visitor при стабильной иерархии типов |
| Visitor ради одной операции | переусложнение | если операция одна — обычный метод проще |

---

## 🔗 Связь с другими темами

- **Composite (тема 09)** — Visitor — естественный способ обходить дерево-Composite.
- **Iterator** — основа Stream API и Collections (см. `../../java-course`).
- **Open/Closed (тема 02)** — Visitor открыт для новых операций, закрыт для правок элементов.
- **CQRS-проекции (тема 20)** — обход событий визитором для построения проекций.

## ➡️ Что дальше

Поведенческие паттерны (и весь блок GoF) пройдены. Тема 14 открывает **архитектурный уровень**: начнём с принципов **GRASP** — как распределять обязанности между классами (Information Expert, Low Coupling, High Cohesion, Controller, Pure Fabrication).
