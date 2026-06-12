# Модуль 24. Stream API и лямбда-выражения

Stream API (Java 8+) позволяет обрабатывать коллекции в **декларативном** стиле: «что сделать», а не «как перебрать». Лямбды — компактная запись функций.

## Лямбда-выражения

**Лямбда** — это анонимная функция. Компактная замена анонимного класса (см. [модуль 23](../m23_oop_overloading_overriding_abstract/theory.md)) для функциональных интерфейсов.

```java
// было (анонимный класс):
Runnable r1 = new Runnable() {
    public void run() { System.out.println("Работа"); }
};

// стало (лямбда):
Runnable r2 = () -> System.out.println("Работа");
```

### Синтаксис

```java
() -> выражение                    // без параметров
x -> x * 2                         // один параметр
(a, b) -> a + b                    // несколько параметров
(int a, int b) -> { return a + b; } // с телом из нескольких строк
```

---

## Функциональные интерфейсы

**Функциональный интерфейс** — интерфейс с **одним** абстрактным методом. Лямбда — его реализация. Готовые из `java.util.function`:

| Интерфейс | Метод | Назначение | Пример |
|-----------|-------|-----------|--------|
| `Predicate<T>` | `test(T)→boolean` | проверка | `x -> x > 0` |
| `Function<T,R>` | `apply(T)→R` | преобразование | `s -> s.length()` |
| `Consumer<T>` | `accept(T)→void` | действие | `x -> println(x)` |
| `Supplier<T>` | `get()→T` | поставщик | `() -> "значение"` |

```java
import java.util.function.Predicate;

Predicate<Integer> isPositive = x -> x > 0;
System.out.println(isPositive.test(5));   // true
System.out.println(isPositive.test(-3));  // false
```

---

## Stream — конвейер обработки

**Stream** (поток данных — не путать с I/O из [модуля 18](../m18_io_streams/theory.md)) — это последовательность элементов, над которой выполняется цепочка операций.

```java
import java.util.List;

List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

int sum = numbers.stream()        // 1. создать поток
    .filter(n -> n % 2 == 0)      // 2. промежуточная: только чётные
    .mapToInt(n -> n * n)         // 3. промежуточная: возвести в квадрат
    .sum();                       // 4. терминальная: сумма
// sum = 4 + 16 + 36 = 56
```

```
источник --filter--▶ map --▶ ... --▶ терминальная операция --▶ результат
(коллекция)   (промежуточные, ленивые)        (запускает конвейер)
```

> **Промежуточные** операции ленивы (возвращают Stream), **терминальная** запускает обработку и возвращает результат. Без терминальной операции конвейер не выполнится.

---

## Промежуточные операции

```java
stream.filter(x -> x > 0)        // отфильтровать по условию
      .map(x -> x * 2)           // преобразовать каждый элемент
      .sorted()                  // отсортировать
      .distinct()                // убрать дубликаты
      .limit(5)                  // взять первые 5
      .skip(2);                  // пропустить первые 2
```

---

## Терминальные операции

```java
stream.forEach(System.out::println);     // выполнить действие для каждого
long n = stream.count();                  // количество
List<T> list = stream.collect(Collectors.toList());  // собрать в список
boolean any = stream.anyMatch(x -> x > 10);  // есть ли подходящий
boolean all = stream.allMatch(x -> x > 0);   // все подходят?
Optional<T> first = stream.findFirst();      // первый элемент
int total = stream.reduce(0, Integer::sum);  // свёртка (аккумуляция)
```

### `Collectors` — сбор результатов

```java
import java.util.stream.Collectors;

List<String> names = people.stream()
    .map(Person::getName)
    .collect(Collectors.toList());

Map<String, List<Person>> byCity = people.stream()
    .collect(Collectors.groupingBy(Person::getCity));   // группировка

String joined = names.stream()
    .collect(Collectors.joining(", "));                  // склейка в строку
```

---

## Ссылки на методы (method references)

Сокращение лямбды, которая просто вызывает существующий метод:

```java
list.forEach(x -> System.out.println(x));   // лямбда
list.forEach(System.out::println);          // ссылка на метод — короче

names.stream().map(s -> s.toUpperCase());    // лямбда
names.stream().map(String::toUpperCase);     // ссылка на метод
```

| Вид | Синтаксис |
|-----|-----------|
| статический метод | `Integer::parseInt` |
| метод объекта | `System.out::println` |
| метод по типу | `String::toUpperCase` |
| конструктор | `ArrayList::new` |

---

## Числовые стримы

```java
IntStream.range(1, 5).forEach(System.out::println);   // 1,2,3,4
int sum = IntStream.rangeClosed(1, 100).sum();         // 5050

double avg = numbers.stream()
    .mapToInt(Integer::intValue)
    .average()
    .orElse(0);
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| забыли терминальную операцию | конвейер не выполнится (ленивость) |
| повторное использование Stream | поток одноразовый; создайте заново |
| изменение коллекции внутри стрима | побочные эффекты ломают логику |
| `NullPointerException` в стриме | проверяйте на null до обработки |
| Stream вместо обычного цикла для простого | для тривиальных задач цикл нагляднее |

---

## Что дальше

В [модуле 25](../m25_multithreading_concurrency/theory.md) — многопоточность.
