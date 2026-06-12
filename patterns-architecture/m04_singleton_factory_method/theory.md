# Тема 04. Singleton и Factory Method

Первые два **порождающих** паттерна GoF. Порождающие паттерны отвечают на вопрос «как создавать объекты», отделяя код создания от кода использования.

```
Порождающие паттерны (создание объектов)
  Singleton        — ровно один экземпляр на всю программу
  Factory Method   — создание делегировано методу/подклассу
  Abstract Factory — семейства объектов (тема 05)
  Builder          — пошаговое построение (тема 05)
  Prototype        — создание копированием (тема 06)
```

---

## 1. Singleton — единственный экземпляр

Гарантирует, что у класса **ровно один** объект, и даёт глобальную точку доступа. Применяется для конфигурации, реестров, пулов, генераторов ID.

### Способ 1 — eager (готов сразу)

```java
class IdSequence {
    private static final IdSequence INSTANCE = new IdSequence();  // создан при загрузке класса
    private long counter = 0;
    private IdSequence() {}                                       // никто снаружи не создаст
    public static IdSequence getInstance() { return INSTANCE; }
    public synchronized long next() { return ++counter; }
}
```

Потокобезопасен «из коробки»: статическое поле инициализируется JVM один раз.

### Способ 2 — holder idiom (ленивый + потокобезопасный)

```java
class Config {
    private Config() {}
    private static class Holder { static final Config INSTANCE = new Config(); }
    public static Config getInstance() { return Holder.INSTANCE; }   // создаётся при первом обращении
}
```

Вложенный класс `Holder` загружается лениво — при первом вызове `getInstance()`, и JVM гарантирует потокобезопасность загрузки класса.

### Способ 3 — enum (рекомендуемый в Java)

```java
enum AppConfig {
    INSTANCE;
    private int timeout = 30;
    public int timeout() { return timeout; }
}
// использование: AppConfig.INSTANCE.timeout()
```

`enum` даёт потокобезопасность, защиту от рефлексии и сериализации бесплатно (Джошуа Блох, *Effective Java*).

> ⚠️ **Singleton — палка о двух концах.** Это глобальное состояние: усложняет тесты (нельзя подменить), создаёт скрытые связи. Применяйте редко и осознанно; часто лучше **один экземпляр + DI** (тема 16), а не «жёсткий» Singleton.

---

## 2. Factory Method — фабричный метод

Выносит создание объекта в отдельный метод, скрывая конкретный класс за общим интерфейсом. Клиент просит «создай транспорт», не зная, какой именно класс получит.

### Простой вариант — статическая фабрика

```java
interface Transport { void deliver(); }
class Truck implements Transport { public void deliver() { System.out.println("по дороге"); } }
class Ship  implements Transport { public void deliver() { System.out.println("по морю"); } }

class TransportFactory {
    static Transport create(String type) {
        return switch (type) {
            case "truck" -> new Truck();
            case "ship"  -> new Ship();
            default -> throw new IllegalArgumentException("неизвестный тип: " + type);
        };
    }
}
```

### Канонический GoF-вариант — метод в подклассе

Создание делегируется **подклассу** через абстрактный метод-фабрику:

```java
abstract class Logistics {
    abstract Transport createTransport();          // фабричный метод
    void planDelivery() {                          // общая логика использует продукт фабрики
        Transport t = createTransport();
        t.deliver();
    }
}
class RoadLogistics extends Logistics { Transport createTransport() { return new Truck(); } }
class SeaLogistics  extends Logistics { Transport createTransport() { return new Ship();  } }
```

```
   Logistics.planDelivery()
        | вызывает
        ▼
   createTransport()  ← каждый подкласс решает, ЧТО создать
        |
   Truck / Ship
```

### Реестр вместо switch (OCP)

Чтобы добавление нового типа не правило `switch`, фабрику строят на `Map<String, Supplier<T>>`:

```java
Map<String, Supplier<Transport>> registry = Map.of(
    "truck", Truck::new,
    "ship",  Ship::new
);
Transport t = registry.get(type).get();   // новый тип = новая запись в реестре
```

---

## Когда что применять

| Паттерн | Решает проблему | Признак, что нужен |
|---------|-----------------|--------------------|
| Singleton | нужен ровно один экземпляр с общим доступом | конфиг, реестр, пул, генератор ID |
| Factory Method (простой) | спрятать выбор класса за одним местом | `new` конкретных классов разбросан по коду |
| Factory Method (GoF) | подкласс решает, что создавать | базовый алгоритм одинаков, продукт — разный |

---

## ⚠️ Подводные камни

| Ошибка | Симптом | Лечение |
|--------|---------|---------|
| Singleton через `if (instance==null)` без синхронизации | в многопотоке создаётся два объекта | holder idiom или enum |
| Singleton-абьюз | глобальное состояние повсюду, тесты хрупкие | один экземпляр + DI вместо жёсткого Singleton |
| Фабрика с растущим `switch` | новый тип → правка switch (нарушение OCP) | реестр `Map<key, Supplier>` |
| Фабрика возвращает конкретный класс | клиент завязан на реализацию | возвращай интерфейс/абстракцию |
| Логика создания в конструкторе клиента | нельзя подменить продукт | вынеси создание в фабрику/фабричный метод |

---

## 🔗 Связь с другими темами

- **DIP/OCP (темы 02–03)** — фабрика возвращает абстракцию, реестр заменяет switch.
- **Abstract Factory, Builder (тема 05)** — расширения идеи «создание как отдельная ответственность».
- **DI и IoC (тема 16)** — контейнер сам управляет единственными экземплярами (вытесняет Singleton).
- Базовый обзор GoF — в `../../java-course/m30_design_patterns`.

## ➡️ Что дальше

Тема 05 — **Abstract Factory и Builder**: как создавать целые семейства согласованных объектов и как пошагово собирать сложные объекты с множеством параметров.
