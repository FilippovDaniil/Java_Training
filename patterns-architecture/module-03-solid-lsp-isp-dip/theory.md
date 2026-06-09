# Тема 03. SOLID: LSP, ISP, DIP

Завершаем SOLID тремя оставшимися буквами. Если SRP и OCP (тема 02) — про размер и расширяемость классов, то LSP, ISP и DIP — про **корректность абстракций и направление зависимостей**.

```
L — Liskov Substitution    подтип обязан вести себя как базовый тип
I — Interface Segregation   много узких интерфейсов лучше одного «толстого»
D — Dependency Inversion    зависим от абстракций, не от конкретики
```

---

## 1. LSP — Liskov Substitution Principle

> Объект подтипа должен быть **взаимозаменяем** с объектом базового типа без поломки программы.

Если код работает с `Base`, подстановка любого `Derived` не должна ломать логику: не сужать входы, не ослаблять гарантии результата, не бросать неожиданных исключений.

**Классическое нарушение — Квадрат/Прямоугольник:**

```java
class Rectangle {
    void setWidth(int w)  { ... }
    void setHeight(int h) { ... }
}
class Square extends Rectangle {        // ❌ математически «is-a», программно — нет
    void setWidth(int w)  { width = height = w; }   // меняет и высоту!
}
// код, ожидающий Rectangle: setWidth(5); setHeight(4); assert area == 20;
// для Square area == 16 → подстановка сломала инвариант → LSP нарушен
```

**Лечение:** не наследовать там, где поведение расходится. Сделать `Shape` с `area()` и независимые `Rectangle`/`Square` (наследование «is-a» по поведению, а не по школьной геометрии).

> Признак нарушения LSP: в наследнике появляется `@Override`, который **бросает** `UnsupportedOperationException` или «выключает» поведение родителя (`Penguin extends Bird { void fly(){ throw ... } }`).

---

## 2. ISP — Interface Segregation Principle

> Клиента нельзя заставлять зависеть от методов, которые он не использует.

«Толстый» интерфейс заставляет реализации писать заглушки для ненужных методов — это запах.

```java
// ❌ Толстый интерфейс: простой принтер вынужден «реализовать» сканер и факс
interface MultiFunction {
    void print(String doc);
    void scan(String doc);
    void fax(String doc);
}
class SimplePrinter implements MultiFunction {
    public void print(String d) { ... }
    public void scan(String d)  { throw new UnsupportedOperationException(); } // ❌
    public void fax(String d)   { throw new UnsupportedOperationException(); } // ❌
}
```

```java
// ✅ Разделили на ролевые интерфейсы
interface Printer { void print(String doc); }
interface Scanner { void scan(String doc); }
interface Fax     { void fax(String doc); }

class SimplePrinter implements Printer { ... }                 // только то, что умеет
class Mfu implements Printer, Scanner, Fax { ... }             // комбинирует роли
```

ISP — это SRP, применённый к **интерфейсам**: каждый интерфейс об одной роли.

---

## 3. DIP — Dependency Inversion Principle

> 1. Модули верхнего уровня не должны зависеть от модулей нижнего уровня — оба зависят от **абстракций**.
> 2. Абстракции не зависят от деталей — детали зависят от абстракций.

«Инверсия» означает: стрелка зависимости разворачивается. Бизнес-логика не тянется к конкретной БД — наоборот, конкретная БД реализует интерфейс, заданный бизнес-логикой.

```java
// ❌ Прямая зависимость: сервис «прибит» к конкретному хранилищу
class ReportService {
    private final MySqlOrderDao dao = new MySqlOrderDao();   // нельзя подменить, нельзя тестировать
}
```

```java
// ✅ Зависимость на абстракцию, конкретику передают снаружи
interface OrderRepository { List<Order> findAll(); }          // абстракция «принадлежит» бизнес-слою

class ReportService {
    private final OrderRepository repo;
    ReportService(OrderRepository repo) { this.repo = repo; } // внедрение зависимости
}
class InMemoryOrderRepository implements OrderRepository { ... }  // деталь зависит от абстракции
class MySqlOrderRepository    implements OrderRepository { ... }  // подменяется без правок сервиса
```

```
   Без DIP                         С DIP (стрелка инвертирована)
ReportService ──▶ MySqlDao     ReportService ──▶ OrderRepository ◀── MySqlRepository
(верхний)        (нижний)      (верхний)         (абстракция)        (нижний, деталь)
```

DIP — теоретическая основа **Dependency Injection** (тема 16) и **гексагональной архитектуры** (тема 17). На практике конкретику внедряют через конструктор.

---

## Как три принципа связаны

| Принцип | Про что | Нарушение пахнет как |
|---------|---------|----------------------|
| LSP | поведение подтипа | `@Override` бросает «не поддерживается»; наследник ломает инвариант |
| ISP | размер интерфейса | реализация полна заглушек для ненужных методов |
| DIP | направление зависимости | `new ConcreteClass()` глубоко в бизнес-логике; нельзя подменить/протестировать |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Наследование по «здравому смыслу» | Square extends Rectangle, Penguin extends Bird ломают код | наследуй по поведению; расходится — не наследуй |
| Заглушки `UnsupportedOperationException` | реализация не умеет половину интерфейса | разрежь интерфейс по ISP |
| `new` конкретного класса в конструкторе сервиса | невозможно подменить/замокать | внедряй абстракцию через конструктор (DIP) |
| Абстракция в «нижнем» слое | интерфейс репозитория лежит рядом с JDBC-кодом | абстракцию определяет потребитель (бизнес-слой) |
| Один интерфейс «на всё» | толстый контракт, его реализуют частично | несколько ролевых интерфейсов |

---

## 🔗 Связь с другими темами

- **Темы 01–02** — LSP опирается на корректное наследование; DIP/ISP продолжают OCP.
- **DI и IoC (тема 16)** — практическая реализация DIP (контейнер внедряет зависимости).
- **Hexagonal (тема 17)** — порты = абстракции, заданные ядром (DIP в масштабе архитектуры).
- **Strategy/Adapter (темы 07, 10)** — держатся на DIP/ISP.

## ➡️ Что дальше

С темой 03 SOLID собран полностью. Дальше начинаются **паттерны GoF**: тема 04 — порождающие паттерны Singleton и Factory Method (как централизованно и гибко создавать объекты, не нарушая SOLID).
