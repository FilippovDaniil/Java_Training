# Модуль 20. Дата и время (`java.time`)

Современный API даты и времени появился в Java 8 (пакет `java.time`). Он **неизменяемый** (immutable), потокобезопасный и удобный — в отличие от старых `Date` и `Calendar`.

## Основные классы

| Класс | Что хранит | Пример |
|-------|-----------|--------|
| `LocalDate` | только дата | `2026-06-01` |
| `LocalTime` | только время | `14:30:00` |
| `LocalDateTime` | дата + время | `2026-06-01T14:30` |
| `ZonedDateTime` | дата + время + часовой пояс | `...+03:00[Europe/Moscow]` |
| `Period` | промежуток в годах/месяцах/днях | `P1Y2M10D` |
| `Duration` | промежуток в часах/минутах/секундах | `PT5H30M` |

---

## Создание объектов

```java
import java.time.*;

LocalDate today = LocalDate.now();              // текущая дата
LocalDate date = LocalDate.of(2026, 6, 1);      // конкретная дата (год, месяц, день)

LocalTime now = LocalTime.now();                // текущее время
LocalTime time = LocalTime.of(14, 30);          // 14:30

LocalDateTime dt = LocalDateTime.now();
LocalDateTime dt2 = LocalDateTime.of(2026, 6, 1, 14, 30);
```

> Месяцы — **с 1** (1 = январь), в отличие от старого `Calendar`, где было с 0.

---

## Извлечение компонентов

```java
LocalDate d = LocalDate.of(2026, 6, 1);

d.getYear();          // 2026
d.getMonthValue();    // 6
d.getMonth();         // JUNE (enum Month)
d.getDayOfMonth();    // 1
d.getDayOfWeek();     // MONDAY (enum DayOfWeek)
d.isLeapYear();       // true — високосный?
d.lengthOfMonth();    // 30
```

---

## Арифметика дат (возвращает НОВЫЙ объект)

Объекты неизменяемы, поэтому методы возвращают новое значение:

```java
LocalDate d = LocalDate.of(2026, 6, 1);

LocalDate later = d.plusDays(10);      // 2026-06-11
LocalDate nextMonth = d.plusMonths(1); // 2026-07-01
LocalDate prevYear = d.minusYears(1);  // 2025-06-01

// d НЕ изменилась — она immutable!
```

```java
LocalTime t = LocalTime.of(14, 30);
t.plusHours(2);      // 16:30
t.minusMinutes(45);  // 13:45
```

---

## Сравнение дат

```java
LocalDate a = LocalDate.of(2026, 1, 1);
LocalDate b = LocalDate.of(2026, 12, 31);

a.isBefore(b);   // true
a.isAfter(b);    // false
a.isEqual(b);    // false
a.compareTo(b);  // отрицательное (a раньше)
```

---

## `Period` и `Duration` — промежутки

### `Period` — для дат (годы, месяцы, дни)

```java
LocalDate birth = LocalDate.of(2000, 5, 15);
LocalDate now = LocalDate.of(2026, 6, 1);

Period age = Period.between(birth, now);
System.out.println(age.getYears());    // 26
System.out.println(age.getMonths());   // 0
System.out.println(age.getDays());     // 17
```

Точное число дней:

```java
import java.time.temporal.ChronoUnit;
long days = ChronoUnit.DAYS.between(birth, now);   // полное число дней
```

### `Duration` — для времени (часы, минуты, секунды)

```java
LocalTime start = LocalTime.of(9, 0);
LocalTime end = LocalTime.of(17, 30);

Duration work = Duration.between(start, end);
System.out.println(work.toHours());      // 8
System.out.println(work.toMinutes());    // 510
```

---

## Форматирование и разбор: `DateTimeFormatter`

```java
import java.time.format.DateTimeFormatter;

LocalDate d = LocalDate.of(2026, 6, 1);
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

String text = d.format(fmt);                 // "01.06.2026"
LocalDate parsed = LocalDate.parse("15.03.2026", fmt);  // обратно в дату
```

| Шаблон | Значение |
|--------|----------|
| `yyyy` | год (2026) |
| `MM` | месяц (06) |
| `dd` | день (01) |
| `HH:mm:ss` | часы:минуты:секунды |

---

## Часовые пояса: `ZonedDateTime`

```java
import java.time.ZoneId;

ZonedDateTime moscow = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
ZonedDateTime tokyo = moscow.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| ожидание, что `plusDays` изменит объект | классы immutable; результат нужно присвоить |
| месяцы с 0 (как в старом API) | в `java.time` месяцы с 1 |
| `Period` для подсчёта времени | `Period` — для дат, `Duration` — для времени |
| `Period.getDays()` как «всего дней» | это только дни-остаток; для всего — `ChronoUnit.DAYS.between` |
| использование старого `Date`/`Calendar` | предпочитайте `java.time` |

---

## Что дальше

В [модуле 21](../module-21-git/theory.md) — основы системы контроля версий Git.
