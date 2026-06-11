# Модуль 09. Работа со строками. Константы

Строки — самый частый тип данных. В Java строка — это объект класса `String` с богатым набором методов.

## Строки неизменяемы (immutable)

Объект `String` **нельзя изменить** после создания. Любой «изменяющий» метод возвращает **новую** строку.

```java
String s = "hello";
s.toUpperCase();              // создаёт "HELLO", но s не меняется!
System.out.println(s);        // hello
s = s.toUpperCase();          // теперь s ссылается на новую строку "HELLO"
```

> Из-за неизменяемости склейка строк в цикле через `+` неэффективна — для этого есть `StringBuilder`.

---

## Основные методы `String`

```java
String s = "Java Core";

s.length();              // 9   — длина (со скобками!)
s.charAt(0);             // 'J' — символ по индексу
s.indexOf("Core");       // 5   — позиция подстроки (или -1)
s.substring(5);          // "Core" — с индекса 5 до конца
s.substring(0, 4);       // "Java" — с 0 по 4 (не включая 4)
s.toUpperCase();         // "JAVA CORE"
s.toLowerCase();         // "java core"
s.replace("a", "@");     // "J@v@ Core"
s.trim();                // убирает пробелы по краям
s.contains("Co");        // true
s.startsWith("Java");    // true
s.endsWith("re");        // true
s.isEmpty();             // false  — длина == 0?
s.split(" ");            // массив ["Java", "Core"]
```

> ⚠️ У массива длина — поле `.length`, у строки — метод `.length()` со скобками.

---

## Сравнение строк: `equals`, НЕ `==`

```java
String a = "test";
String b = new String("test");

a == b;            // false — сравнивает ССЫЛКИ (разные объекты)
a.equals(b);       // true  — сравнивает СОДЕРЖИМОЕ
a.equalsIgnoreCase("TEST");  // true — без учёта регистра
```

> 🔑 **Правило:** для строк всегда используйте `.equals()`. Оператор `==` сравнивает ссылки, а не текст.

---

## Перебор символов строки

```java
String s = "abc";
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    System.out.println(c);
}
```

Символы можно сравнивать и проверять:

```java
Character.isDigit('5');     // true
Character.isLetter('A');    // true
Character.toUpperCase('a'); // 'A'
```

---

## `StringBuilder` — изменяемая строка

Когда нужно много раз менять строку (особенно в цикле), используйте `StringBuilder`.

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(", ");
sb.append("World");
sb.insert(0, ">> ");
sb.reverse();                  // переворачивает содержимое
String result = sb.toString(); // получить обычную String
```

### `String` + `+` vs `StringBuilder`

| Способ | Когда |
|--------|-------|
| `String` с `+` | разовая склейка 2–3 строк |
| `StringBuilder` | склейка в цикле, многократные изменения |

```java
// ❌ медленно — на каждой итерации создаётся новая строка
String r = "";
for (int i = 0; i < 1000; i++) r += i;

// ✅ быстро
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) sb.append(i);
```

---

## Форматирование: `String.format` и `printf`

```java
String name = "Иван";
int age = 30;
double price = 199.5;

String s = String.format("%s, возраст %d, баланс %.2f", name, age, price);
// "Иван, возраст 30, баланс 199.50"

System.out.printf("%-10s|%5d%n", "Товар", 42);   // вывод сразу
```

| Спецификатор | Значение |
|--------------|----------|
| `%s` | строка |
| `%d` | целое |
| `%f` | дробное (`%.2f` — 2 знака) |
| `%n` | перевод строки |
| `%-10s` | строка, выравнивание влево, ширина 10 |

---

## Константы: `final`

Константа — переменная, значение которой нельзя изменить. Имя — `UPPER_SNAKE_CASE`.

```java
final double PI = 3.14159;
final int MAX_USERS = 100;
// PI = 3.14;   // ОШИБКА компиляции — нельзя переприсвоить final
```

> Константы делают код понятнее и защищают от случайных изменений.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| сравнение строк через `==` | сравнивает ссылки; используйте `.equals()` |
| `s.toUpperCase()` без присваивания | строка неизменяема; результат теряется |
| `+` в большом цикле | медленно; используйте `StringBuilder` |
| `substring(0, 5)` для строки длиной 4 | `StringIndexOutOfBoundsException` |
| `s.length` без скобок | у строки это метод: `s.length()` |

---

## Что дальше

В [модуле 10](../module-10-oop-intro/theory.md) начинаем главную тему курса — ООП.
