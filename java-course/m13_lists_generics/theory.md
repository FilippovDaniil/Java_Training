# Модуль 13. Списки и Generics

Массив имеет фиксированный размер. **Список** (`ArrayList`) — динамический: растёт и сжимается автоматически.

## `ArrayList` — динамический список

```java
import java.util.ArrayList;
import java.util.List;

List<String> names = new ArrayList<>();   // пустой список строк
names.add("Анна");          // [Анна]
names.add("Борис");         // [Анна, Борис]
names.add("Виктор");        // [Анна, Борис, Виктор]

System.out.println(names.size());     // 3
System.out.println(names.get(0));     // Анна — по индексу
names.set(1, "Богдан");               // заменить элемент
names.remove("Виктор");               // удалить по значению
names.remove(0);                      // удалить по индексу
System.out.println(names.contains("Богдан"));  // true
System.out.println(names.indexOf("Богдан"));   // 0
names.clear();                        // очистить
System.out.println(names.isEmpty());  // true
```

> `List` — интерфейс, `ArrayList` — реализация. Принято объявлять переменную типом интерфейса: `List<String> x = new ArrayList<>();`

---

## Generics — типизация `<>`

**Generics** («обобщения») позволяют указать, какой тип хранит коллекция. Угловые скобки `<>` фиксируют тип элементов.

```java
List<Integer> numbers = new ArrayList<>();   // только Integer
numbers.add(10);
// numbers.add("abc");  // ❌ ошибка компиляции — не Integer
int x = numbers.get(0); // не нужно приводить тип
```

Без generics (старый стиль) пришлось бы хранить `Object` и приводить вручную — небезопасно. Generics дают **проверку типов на этапе компиляции**.

```
List<String>   — список строк
List<Integer>  — список целых
List<Book>     — список объектов Book
```

---

## Классы-обёртки (wrapper classes)

Коллекции хранят только **объекты**, а не примитивы. Для каждого примитива есть класс-обёртка:

| Примитив | Обёртка |
|----------|---------|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |
| `long` | `Long` |

### Автоупаковка и распаковка (autoboxing/unboxing)

Java автоматически конвертирует примитив ⇄ обёртку:

```java
List<Integer> list = new ArrayList<>();
list.add(5);                 // autoboxing: int 5 → Integer
int value = list.get(0);     // unboxing: Integer → int
```

> Полезные статические методы обёрток: `Integer.parseInt("42")`, `Integer.MAX_VALUE`, `Double.parseDouble("3.14")`.

---

## Перебор списка

```java
// for-each (чаще всего)
for (String name : names) {
    System.out.println(name);
}

// классический for (нужен индекс)
for (int i = 0; i < names.size(); i++) {
    System.out.println(i + ": " + names.get(i));
}
```

---

## Список объектов

```java
List<Book> library = new ArrayList<>();
library.add(new Book("1984", 1949));
library.add(new Book("Дюна", 1965));

for (Book b : library) {
    System.out.println(b.getTitle());
}
```

---

## `ArrayList` vs `LinkedList`

| Операция | `ArrayList` | `LinkedList` |
|----------|-------------|--------------|
| доступ по индексу `get(i)` | ⚡ быстро (O(1)) | 🐢 медленно (O(n)) |
| добавление в конец | ⚡ быстро | ⚡ быстро |
| вставка/удаление в начале | 🐢 медленно (сдвиг) | ⚡ быстро |
| хранение | массив внутри | узлы со ссылками |

> На практике почти всегда используют `ArrayList` — он быстрее для типичных задач. `LinkedList` — когда много вставок/удалений в начало/середину.

---

## Полезное: `Collections`

```java
import java.util.Collections;

Collections.sort(numbers);          // сортировка
Collections.reverse(numbers);       // разворот
Collections.max(numbers);           // максимум
int min = Collections.min(numbers); // минимум
```

(Подробнее — в [модуле 15](../m15_map_collections_framework/theory.md).)

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `List<int>` | generics работают только с объектами: `List<Integer>` |
| `IndexOutOfBoundsException` | обращение к `get(i)` за границей |
| удаление в цикле `for-each` | `ConcurrentModificationException`; используйте `Iterator` (см. [модуль 14](../m14_collections_set_iterator/theory.md)) |
| `list.size` вместо `list.size()` | у списка это метод |
| сравнение элементов через `==` | используйте `.equals()` |

---

## Что дальше

В [модуле 14](../m14_collections_set_iterator/theory.md) — множества `Set` и итераторы.
