# Тема 18. Strategic DDD: строительные блоки домена

**Domain-Driven Design** (DDD, Эрик Эванс) — подход, где код выражает предметную область, а не технические детали. Гексагон (тема 17) даёт «ядро» — DDD говорит, **чем его наполнить**. Начнём со строительных блоков: Bounded Context, Ubiquitous Language, Value Object, Entity, Aggregate.

```
Ubiquitous Language — единый язык кода и бизнеса
Bounded Context     — граница, внутри которой термины однозначны
Value Object        — объект-значение: важно ЧТО, не КТО (равенство по значению)
Entity              — сущность с идентичностью (равенство по id)
Aggregate           — кластер объектов с единым «корнем» и инвариантами
```

---

## 1. Ubiquitous Language и Bounded Context

**Ubiquitous Language** — общий язык, на котором говорят и эксперты, и код: класс называется `Order`, метод — `confirm()`, а не `processData()`. Имена в коде = термины бизнеса.

**Bounded Context** — граница, внутри которой термин имеет **одно** значение. «Товар» в контексте *Каталога* (название, цена, описание) — это не тот же «Товар» в контексте *Доставки* (вес, габариты). Один термин — две разные модели в разных контекстах.

```
   Контекст «Каталог»            Контекст «Доставка»
   Product { title, price }      Product { weightGrams, dimensions }
   (одно слово — разные модели; не пытайтесь сделать «один Product на всё»)
```

> Главная мысль: не делайте единую модель «на весь мир». Разделите систему на контексты, в каждом — своя согласованная модель.

---

## 2. Value Object — объект-значение

Описывает **характеристику**, не имеет идентичности. Два VO равны, если равны их значения. Должен быть **неизменяемым**.

```java
final class Money {                                  // VO: важно сколько, а не «какие именно деньги»
    private final long amountCents;
    private final String currency;
    Money(long amountCents, String currency) {
        if (amountCents < 0) throw new IllegalArgumentException();
        this.amountCents = amountCents; this.currency = currency;
    }
    Money plus(Money other) {                         // операции возвращают НОВЫЙ объект
        return new Money(this.amountCents + other.amountCents, currency);
    }
    @Override public boolean equals(Object o) { /* по amountCents + currency */ }
    @Override public int hashCode() { /* по полям */ }
}
// new Money(100,"RUB").equals(new Money(100,"RUB")) == true — равны по значению
```

Признаки VO: неизменяемость, равенство по значению (`equals`/`hashCode` по полям), отсутствие id, самопроверка в конструкторе. Примеры: `Money`, `Email`, `DateRange`, `Quantity`.

---

## 3. Entity — сущность

Имеет **идентичность** (id), которая сохраняется при изменении свойств. Два Entity равны, если равны их **id**, даже если остальные поля различаются.

```java
class Customer {                                     // Entity: важно КТО, а не текущее имя
    private final String id;                          // идентичность
    private String name;                              // может меняться, личность та же
    Customer(String id, String name) { this.id = id; this.name = name; }
    void rename(String newName) { this.name = newName; }
    @Override public boolean equals(Object o) { /* ТОЛЬКО по id */ }
    @Override public int hashCode() { /* по id */ }
}
```

| | Value Object | Entity |
|---|--------------|--------|
| Идентичность | нет | есть (id) |
| Равенство | по значению | по id |
| Изменяемость | неизменяем | может меняться |
| Вопрос | «что это?» | «кто это?» |

---

## 4. Aggregate — агрегат

**Агрегат** — кластер связанных Entity/VO, к которому обращаются как к единому целому через **корень агрегата** (Aggregate Root). Корень стережёт **инварианты** всего кластера; внешний код не лезет во внутренности напрямую.

```java
class Order {                                        // Aggregate Root
    private final String id;
    private final List<OrderLine> lines = new ArrayList<>();   // внутренности агрегата
    private boolean confirmed = false;

    public void addLine(String sku, int qty, Money price) {    // доступ только через корень
        if (confirmed) throw new IllegalStateException("заказ подтверждён");  // инвариант
        lines.add(new OrderLine(sku, qty, price));
    }
    public Money total() { /* сумма по lines */ }
    public List<OrderLine> lines() { return List.copyOf(lines); }  // наружу — копия, не ссылка
}
```

**Правила агрегата:**
- внешний код держит ссылку только на корень, не на внутренние объекты;
- инварианты (правила консистентности) проверяет корень;
- наружу отдавать копии/неизменяемые представления, чтобы внутренности нельзя было поменять в обход корня;
- агрегат — граница транзакционной консистентности (сохраняется целиком).

```
        Order (Aggregate Root)
         ├── OrderLine  ← внутри агрегата, доступ только через Order
         ├── OrderLine
         └── total(), addLine() — корень стережёт инварианты
   снаружи: ссылка только на Order
```

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Изменяемый Value Object | у двух мест «общее» значение поменялось | делай VO неизменяемым, операции возвращают новый |
| Entity без equals по id | дубликаты в Set, баги сравнения | equals/hashCode только по id |
| «Один Product на все контексты» | модель пухнет, поля «то для одного, то для другого» | раздели на Bounded Contexts |
| Доступ к внутренностям агрегата | инварианты нарушаются в обход корня | работай только через корень, отдавай копии |
| Анемичный агрегат | вся логика снаружи, корень — мешок геттеров | поведение и инварианты — в корне (см. тема 22) |
| Огромный агрегат | блокировки, медленные транзакции | агрегат маленький; ссылайся на другие по id |

---

## 🔗 Связь с другими темами

- **Hexagonal (тема 17)** — агрегаты живут в ядре гексагона.
- **Information Expert (тема 14)** — корень агрегата = эксперт по своим инвариантам.
- **Tactical DDD (тема 19)** — Repository хранит агрегаты, Domain Events исходят из них.
- **Антипаттерны (тема 22)** — Anemic Domain Model = агрегат без поведения.

## ➡️ Что дальше

Тема 19 — **Tactical DDD**: Repository (хранение агрегатов), доменные сервисы (логика между агрегатами), фабрики (сборка агрегатов) и Domain Events (факты, произошедшие в домене).
