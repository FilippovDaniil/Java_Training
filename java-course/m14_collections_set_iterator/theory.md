# Модуль 14. Коллекции: `Set`, `HashSet`, итератор

**Множество** (`Set`) — коллекция **уникальных** элементов: дубликаты автоматически отбрасываются. Порядок, как правило, не гарантируется.

## `HashSet` — множество на хеш-таблице

```java
import java.util.HashSet;
import java.util.Set;

Set<String> tags = new HashSet<>();
tags.add("java");
tags.add("oop");
tags.add("java");        // дубликат — НЕ добавится

System.out.println(tags.size());          // 2
System.out.println(tags.contains("oop")); // true — проверка БЫСТРАЯ
tags.remove("java");
```

### Set vs List

| | `List` | `Set` |
|--|--------|-------|
| дубликаты | разрешены | **запрещены** |
| порядок | сохраняется | не гарантируется (`HashSet`) |
| доступ по индексу | да (`get(i)`) | нет |
| `contains()` | медленно (O(n)) | ⚡ быстро (O(1)) |

> Используйте `Set`, когда нужна **уникальность** и быстрая проверка наличия.

---

## Удаление дубликатов — типичное применение

```java
List<Integer> withDups = List.of(1, 2, 2, 3, 3, 3, 4);
Set<Integer> unique = new HashSet<>(withDups);   // {1, 2, 3, 4}
```

---

## Три реализации `Set`

| Класс | Порядок элементов | Скорость |
|-------|-------------------|----------|
| `HashSet` | не определён | ⚡ самый быстрый |
| `LinkedHashSet` | порядок добавления | чуть медленнее |
| `TreeSet` | отсортированный | медленнее (O(log n)) |

```java
Set<Integer> tree = new TreeSet<>();
tree.add(5); tree.add(1); tree.add(3);
System.out.println(tree);   // [1, 3, 5] — всегда отсортировано
```

```
HashSet:        {3, 1, 5}      ← порядок «как попало»
LinkedHashSet:  {5, 1, 3}      ← как добавляли
TreeSet:        {1, 3, 5}      ← по возрастанию
```

---

## Итератор

**Итератор** — объект для последовательного обхода коллекции. Цикл `for-each` использует его «под капотом».

```java
import java.util.Iterator;

Iterator<String> it = tags.iterator();
while (it.hasNext()) {        // есть ли следующий элемент?
    String tag = it.next();   // взять следующий и сдвинуться
    System.out.println(tag);
}
```

### Зачем нужен явный итератор: безопасное удаление

Удалять из коллекции внутри `for-each` **нельзя** — будет `ConcurrentModificationException`. Удалять надо через `iterator.remove()`:

```java
Iterator<Integer> it = numbers.iterator();
while (it.hasNext()) {
    if (it.next() % 2 == 0) {
        it.remove();          // ✅ безопасное удаление текущего элемента
    }
}
```

```java
// ❌ так НЕЛЬЗЯ:
for (Integer n : numbers) {
    if (n % 2 == 0) numbers.remove(n);   // ConcurrentModificationException
}
```

---

## `for-each` для множества

```java
for (String tag : tags) {
    System.out.println(tag);
}
```

> У `HashSet` порядок вывода может отличаться от порядка добавления — это нормально.

---

## Операции над множествами

```java
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6));

Set<Integer> union = new HashSet<>(a);
union.addAll(b);            // объединение: {1,2,3,4,5,6}

Set<Integer> intersect = new HashSet<>(a);
intersect.retainAll(b);     // пересечение: {3,4}

Set<Integer> diff = new HashSet<>(a);
diff.removeAll(b);          // разность a−b: {1,2}
```

---

## Множество объектов требует equals/hashCode

Чтобы `HashSet` корректно определял дубликаты **ваших** объектов, в классе должны быть переопределены `equals()` и `hashCode()` (см. [модуль 11](../m11_objects_constructors/theory.md)).

```java
Set<Book> books = new HashSet<>();
books.add(new Book("1984", 1949));
books.add(new Book("1984", 1949));   // дубликат отбросится ТОЛЬКО при корректных equals/hashCode
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| удаление в `for-each` | `ConcurrentModificationException`; нужен `Iterator.remove()` |
| ожидание порядка от `HashSet` | порядок не гарантирован; нужен `LinkedHashSet`/`TreeSet` |
| `Set` объектов без equals/hashCode | дубликаты не распознаются |
| доступ по индексу у `Set` | его нет; используйте `List` |

---

## Что дальше

В [модуле 15](../m15_map_collections_framework/theory.md) — словари `Map` и обзор всего фреймворка коллекций.
