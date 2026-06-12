# Модуль 15. `Map`, `HashMap`, обзор Collections Framework

**Map** (словарь, отображение) хранит пары **ключ → значение**. По ключу можно мгновенно получить значение. Ключи уникальны.

## `HashMap` — основная реализация

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> ages = new HashMap<>();
ages.put("Анна", 25);          // добавить пару
ages.put("Борис", 30);
ages.put("Анна", 26);          // ключ уже есть → значение ЗАМЕНИТСЯ (26)

System.out.println(ages.get("Анна"));        // 26
System.out.println(ages.get("Иван"));        // null — нет такого ключа
System.out.println(ages.getOrDefault("Иван", 0));  // 0 — значение по умолчанию
System.out.println(ages.containsKey("Борис"));     // true
System.out.println(ages.size());             // 2
ages.remove("Борис");
```

```
   ключ      значение
 +--------+----------+
 | "Анна" |    26    |
 +--------+----------+
 |"Борис" |    30    |
 +--------+----------+
```

> Ключи образуют множество (уникальны). Значения могут повторяться.

---

## Перебор Map

```java
// по ключам
for (String name : ages.keySet()) {
    System.out.println(name + " → " + ages.get(name));
}

// по значениям
for (int age : ages.values()) {
    System.out.println(age);
}

// по парам (самый эффективный способ)
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}
```

| Метод | Что возвращает |
|-------|----------------|
| `keySet()` | множество ключей |
| `values()` | коллекцию значений |
| `entrySet()` | множество пар `Entry` |

---

## Частая задача: подсчёт частоты

`getOrDefault` идеален для счётчиков:

```java
Map<String, Integer> freq = new HashMap<>();
for (String word : words) {
    freq.put(word, freq.getOrDefault(word, 0) + 1);
}
```

Или короче — через `merge`:

```java
freq.merge(word, 1, Integer::sum);
```

---

## Реализации `Map`

| Класс | Порядок ключей | Когда |
|-------|----------------|-------|
| `HashMap` | не определён | ⚡ обычный выбор |
| `LinkedHashMap` | порядок добавления | нужен предсказуемый порядок |
| `TreeMap` | ключи отсортированы | нужна сортировка по ключу |

```java
Map<String, Integer> tree = new TreeMap<>();
tree.put("в", 1); tree.put("а", 2); tree.put("б", 3);
System.out.println(tree);   // {а=2, б=3, в=1} — по ключам
```

---

## Обзор Collections Framework

```
                 Iterable
                    |
                Collection ---------------+
               /         \                |
          List            Set            (Map — отдельная иерархия)
         /    \          /  |  \              |
  ArrayList  LinkedList Hash Tree Linked   HashMap  TreeMap  LinkedHashMap
                        Set  Set  HashSet
```

| Интерфейс | Суть | Дубликаты | Порядок |
|-----------|------|-----------|---------|
| `List` | упорядоченная последовательность | да | по индексу |
| `Set` | уникальные элементы | нет | зависит от реализации |
| `Map` | пары ключ→значение | ключи уникальны | зависит от реализации |

> `Map` **не** наследует `Collection` — это отдельная ветвь, но входит в Collections Framework.

---

## Класс-утилита `Collections`

Статические методы для работы с коллекциями:

```java
import java.util.Collections;

Collections.sort(list);              // сортировка по возрастанию
Collections.sort(list, Collections.reverseOrder());  // по убыванию
Collections.reverse(list);           // развернуть
Collections.shuffle(list);           // перемешать
Collections.max(list);               // максимум
Collections.min(list);               // минимум
Collections.frequency(list, x);      // сколько раз встречается x
int n = Collections.binarySearch(sortedList, key);  // бинарный поиск
```

---

## Когда что использовать (шпаргалка)

| Нужно | Используйте |
|-------|-------------|
| последовательность с дубликатами, доступ по индексу | `ArrayList` |
| только уникальные элементы | `HashSet` |
| уникальные, отсортированные | `TreeSet` |
| пары ключ→значение | `HashMap` |
| пары, отсортированные по ключу | `TreeMap` |

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `get()` несуществующего ключа → `null` | используйте `getOrDefault` или `containsKey` |
| ключи-объекты без equals/hashCode | поиск по ключу не сработает |
| ожидание порядка от `HashMap` | порядок не гарантирован |
| `NullPointerException` при unboxing `null` | `int x = map.get(absentKey)` — null нельзя распаковать |

---

## Что дальше

В [модуле 16](../m16_enums_switch/theory.md) — перечисления `enum`.
