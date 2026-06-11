# Модуль 33. Guava и Apache Commons

**Guava** (Google) и **Apache Commons** — популярные библиотеки-утилиты, расширяющие стандартную Java: удобные коллекции, проверки, работа со строками и т.д.

> ⚠️ Задачи требуют **внешних зависимостей** (Guava, Apache Commons). Они не компилируются «голым» `javac` — добавьте библиотеки через Maven/Gradle (координаты ниже) и запускайте в проекте/IDE.

## Подключение зависимостей (Maven)

```xml
<dependencies>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>33.2.1-jre</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.14.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.4</version>
    </dependency>
</dependencies>
```

---

## Guava: коллекции

### Multimap — несколько значений на ключ

В обычном `Map` один ключ → одно значение. В `Multimap` ключ → **много** значений (без ручных `List` внутри `Map`).

```java
import com.google.common.collect.*;

Multimap<String, String> map = ArrayListMultimap.create();
map.put("фрукты", "яблоко");
map.put("фрукты", "банан");
map.put("овощи", "морковь");

System.out.println(map.get("фрукты"));   // [яблоко, банан]
System.out.println(map.size());          // 3 (всего пар)
```

> Без Guava пришлось бы писать `Map<String, List<String>>` и вручную создавать списки.

### BiMap — двунаправленная карта

Уникальны и ключи, и значения; можно искать в обе стороны.

```java
BiMap<String, Integer> bimap = HashBiMap.create();
bimap.put("один", 1);
bimap.put("два", 2);

System.out.println(bimap.get("один"));          // 1
System.out.println(bimap.inverse().get(2));     // "два" — обратный поиск
```

### Multiset — подсчёт с повторами

«Множество со счётчиком»: хранит, сколько раз встретился элемент.

```java
Multiset<String> words = HashMultiset.create();
words.add("кот");
words.add("кот");
words.add("пёс");

System.out.println(words.count("кот"));   // 2
```

### Неизменяемые коллекции

```java
List<String> list = ImmutableList.of("a", "b", "c");   // нельзя изменить
Map<String, Integer> map = ImmutableMap.of("x", 1, "y", 2);
// list.add("d");  // ❌ UnsupportedOperationException
```

---

## Guava: утилиты

### Preconditions — проверка аргументов

```java
import static com.google.common.base.Preconditions.*;

void setAge(int age) {
    checkArgument(age >= 0, "Возраст не может быть отрицательным: %s", age);
    this.age = age;
}
```

### Joiner и Splitter — склейка и разбиение строк

```java
String joined = Joiner.on(", ").skipNulls().join("a", null, "c");  // "a, c"

List<String> parts = Splitter.on(',')
    .trimResults()
    .omitEmptyStrings()
    .splitToList("a, b, , c");   // [a, b, c]
```

---

## Apache Commons Lang — `StringUtils`

Null-безопасные операции со строками:

```java
import org.apache.commons.lang3.StringUtils;

StringUtils.isBlank("   ");          // true (пусто или пробелы)
StringUtils.isEmpty("");             // true
StringUtils.capitalize("привет");    // "Привет"
StringUtils.reverse("abc");          // "cba"
StringUtils.repeat("ab", 3);         // "ababab"
StringUtils.defaultIfBlank(s, "—");  // запасное значение если пусто
```

> `StringUtils.isBlank(null)` вернёт `true` — не бросит `NullPointerException`, в отличие от `s.trim().isEmpty()`.

---

## Apache Commons Collections — `CollectionUtils`

```java
import org.apache.commons.collections4.CollectionUtils;

CollectionUtils.isEmpty(list);              // null-безопасная проверка
CollectionUtils.union(listA, listB);        // объединение
CollectionUtils.intersection(listA, listB); // пересечение
CollectionUtils.subtract(listA, listB);     // разность
```

---

## Нужны ли эти библиотеки сегодня

Многое из Guava/Commons вошло в современную Java:

| Возможность | Стандартная Java | Библиотека |
|-------------|------------------|------------|
| неизменяемый список | `List.of(...)` (Java 9+) | `ImmutableList.of` |
| Optional | `java.util.Optional` (Java 8+) | `com.google.common.base.Optional` |
| строки join | `String.join(...)` | `Joiner` |

> Но Multimap, BiMap, Multiset, Table и многие утилиты Commons по-прежнему **уникальны** и широко используются.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| забыли добавить зависимость | `ClassNotFoundException`/не компилируется |
| путаница Guava Optional и java.util.Optional | используйте стандартный, если нет причины |
| изменение Immutable-коллекции | `UnsupportedOperationException` |
| смешение версий guava (android/jre) | берите `-jre` для серверной Java |

## Дополнительные источники

- Guava Wiki — github.com/google/guava/wiki.
- Apache Commons — commons.apache.org.

## Что дальше

В [модуле 34](../m34_testing_junit_mockito/theory.md) — тестирование с JUnit и Mockito.
