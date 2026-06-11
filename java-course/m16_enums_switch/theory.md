# Модуль 16. Перечисления (`Enum`) и `switch`

**Enum** (перечисление) — специальный тип для фиксированного набора именованных констант: дни недели, статусы заказа, времена года. Это безопаснее, чем «магические» числа или строки.

## Простейший enum

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

```java
Day today = Day.FRIDAY;
System.out.println(today);          // FRIDAY
```

> Константы enum пишут `БОЛЬШИМИ_БУКВАМИ`. Это полноценные объекты-синглтоны.

### Зачем enum вместо констант

```java
// ❌ "магические" значения — легко ошибиться
int status = 2;       // что значит 2?

// ✅ читаемо и безопасно
OrderStatus status = OrderStatus.SHIPPED;
```

---

## Полезные методы enum

```java
Day d = Day.MONDAY;

d.name();        // "MONDAY" — имя константы
d.ordinal();     // 0 — порядковый номер (с нуля)
Day.values();    // массив всех констант
Day.valueOf("FRIDAY");   // Day.FRIDAY — из строки (бросит исключение, если нет)
```

### Перебор всех значений

```java
for (Day d : Day.values()) {
    System.out.println(d.ordinal() + ": " + d.name());
}
```

---

## `switch` по enum

Enum и `switch` — идеальная пара. В `case` пишут **только имя константы** (без `Day.`):

```java
switch (today) {
    case SATURDAY:
    case SUNDAY:
        System.out.println("Выходной");
        break;
    default:
        System.out.println("Рабочий день");
}
```

Стрелочный `switch` (Java 14+) ещё короче:

```java
String type = switch (today) {
    case SATURDAY, SUNDAY -> "Выходной";
    default -> "Рабочий день";
};
```

---

## Enum с полями, конструктором и методами

Enum может хранить данные и иметь поведение.

```java
enum Planet {
    EARTH(5.97e24, 6.37e6),
    MARS(6.42e23, 3.39e6);          // константы вызывают конструктор

    private final double mass;
    private final double radius;

    Planet(double mass, double radius) {   // конструктор — всегда private
        this.mass = mass;
        this.radius = radius;
    }

    double surfaceGravity() {
        return 6.67e-11 * mass / (radius * radius);
    }
}
```

```java
System.out.println(Planet.EARTH.surfaceGravity());  // ~9.8
```

> Конструктор enum **неявно `private`** — нельзя создать новую константу извне.

---

## Enum как Singleton

Enum с единственной константой — самый надёжный способ реализовать **Singleton** (единственный экземпляр на всю программу). JVM гарантирует единственность.

```java
enum Database {
    INSTANCE;                       // единственный экземпляр

    private int connections = 0;

    public void connect() {
        connections++;
        System.out.println("Подключение #" + connections);
    }
}
```

```java
Database.INSTANCE.connect();   // Подключение #1
Database.INSTANCE.connect();   // Подключение #2 — тот же объект
```

> Почему enum-синглтон лучше «классического»: потокобезопасен из коробки, защищён от рефлексии и сериализации.

---

## Сравнение enum

Константы enum можно сравнивать через `==` (они синглтоны) или в `switch`:

```java
if (today == Day.FRIDAY) { ... }   // безопасно для enum
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `case Day.MONDAY:` | в `switch` по enum пишут просто `case MONDAY:` |
| `valueOf("monday")` | чувствительно к регистру → `IllegalArgumentException` |
| публичный конструктор enum | конструктор всегда `private` |
| сравнение enum через `.equals()` | работает, но `==` нагляднее и null-безопаснее |

---

## Что дальше

В [модуле 17](../m17_exceptions/theory.md) — обработка ошибок через исключения.
