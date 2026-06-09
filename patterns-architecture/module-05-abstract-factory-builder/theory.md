# Тема 05. Abstract Factory и Builder

Ещё два порождающих паттерна. **Builder** решает проблему «слишком много параметров конструктора», **Abstract Factory** — «создать семейство согласованных объектов».

```
Builder           — собрать сложный объект по шагам, читаемо и безопасно
Abstract Factory  — создать семью связанных объектов одного «стиля»
```

---

## 1. Builder — пошаговое построение

### Проблема: телескопические конструкторы

```java
// ❌ что значат эти аргументы? легко перепутать порядок
new Pizza(30, true, false, true, false, "тонкое");
new Pizza(25, false);                 // и сколько таких перегрузок писать?
```

### Решение: fluent Builder

```java
Pizza pizza = new Pizza.Builder()
    .size(30)
    .cheese(true)
    .pepperoni(true)
    .build();                         // читается как фраза, порядок не важен
```

Каркас:

```java
class Pizza {
    private final int size;
    private final boolean cheese;
    private Pizza(Builder b) { this.size = b.size; this.cheese = b.cheese; }  // приватный ctor

    static class Builder {
        private int size;
        private boolean cheese;
        Builder size(int s)      { this.size = s; return this; }   // возвращает this → цепочка
        Builder cheese(boolean c){ this.cheese = c; return this; }
        Pizza build() { return new Pizza(this); }                  // здесь же — валидация
    }
}
```

**Плюсы:** читаемость, неизменяемость результата (`final` поля), валидация в `build()`, легко добавлять опциональные параметры.

> Где встречается: `StringBuilder`, `Stream.Builder`, `HttpRequest.newBuilder()` (java.net.http), Lombok `@Builder`.

---

## 2. Abstract Factory — семейство объектов

Создаёт **группы связанных** объектов, не привязываясь к их конкретным классам. Гарантирует, что объекты из одной семьи сочетаются.

```java
// продукты двух видов
interface Button   { String render(); }
interface Checkbox { String render(); }

// абстрактная фабрика создаёт ВСЮ семью
interface UiFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// конкретные семьи — согласованные между собой
class LightFactory implements UiFactory {
    public Button createButton()     { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}
class DarkFactory implements UiFactory {
    public Button createButton()     { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}
```

```
          UiFactory (абстрактная фабрика)
         /                              \
   LightFactory                     DarkFactory
   ├ LightButton                    ├ DarkButton
   └ LightCheckbox                  └ DarkCheckbox
   (одна согласованная семья)       (другая семья)
```

Клиент работает только с `UiFactory`, `Button`, `Checkbox` — и не может случайно смешать светлую кнопку с тёмным чекбоксом.

### Factory Method vs Abstract Factory

| | Factory Method | Abstract Factory |
|---|----------------|------------------|
| Создаёт | ОДИН продукт | СЕМЬЮ связанных продуктов |
| Механизм | один метод (часто в подклассе) | объект-фабрика с несколькими методами |
| Когда | вариативность одного объекта | согласованные группы объектов |

---

## Builder vs Abstract Factory

| | Builder | Abstract Factory |
|---|---------|------------------|
| Задача | собрать ОДИН сложный объект по шагам | создать СЕМЬЮ простых объектов сразу |
| Акцент | как конструируется (порядок, опции) | какие классы выбираются (семья) |
| Результат | один настроенный объект | несколько согласованных объектов |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Builder без валидации в `build()` | создаётся объект в неверном состоянии | проверяй обязательные поля в `build()` |
| Изменяемый объект после построения | builder вернул объект с сеттерами | поля `final`, только приватный ctor |
| Abstract Factory ради одного продукта | переусложнение | если продукт один — это Factory Method |
| Смешение семей | светлая кнопка + тёмный чекбокс | клиент берёт ВСЁ из одной фабрики |
| Builder на 2 поля | бойлерплейт там, где хватит конструктора | Builder нужен от ~4 параметров / опциональных |

---

## 🔗 Связь с другими темами

- **Тема 04** — продолжение порождающих паттернов (Singleton, Factory Method).
- **DIP (тема 03)** — клиент зависит от абстрактной фабрики и интерфейсов продуктов.
- **DDD-фабрики (тема 19)** — Builder/Factory применяются для сборки агрегатов.
- `HttpRequest.Builder`, `StringBuilder` — Builder в стандартной библиотеке.

## ➡️ Что дальше

Тема 06 — **Prototype и Object Pool**: создание объекта копированием готового образца и переиспользование «тяжёлых» объектов через пул.
