# Модуль 19. `java.nio` и паттерн Decorator

`java.nio.file` (New I/O) — современный API для работы с файлами. Он удобнее и компактнее классического `java.io` из [модуля 18](../m18_io_streams/theory.md).

## `Path` — путь к файлу или каталогу

```java
import java.nio.file.Path;
import java.nio.file.Paths;

Path path = Paths.get("data", "notes.txt");   // data/notes.txt
// или: Path path = Path.of("data/notes.txt");

path.getFileName();      // notes.txt
path.getParent();        // data
path.toAbsolutePath();   // полный путь от корня
path.isAbsolute();       // false
```

> `Path` — это **описание** пути, сам файл при этом может ещё не существовать.

---

## `Files` — операции с файлами

Класс `Files` содержит статические методы для чтения, записи и управления файлами.

### Запись и чтение текста — в одну строку

```java
import java.nio.file.Files;
import java.util.List;

// записать список строк (создаёт/перезаписывает файл)
Files.write(path, List.of("Первая", "Вторая", "Третья"));

// прочитать ВСЕ строки сразу в список
List<String> lines = Files.readAllLines(path);

// прочитать весь файл как одну строку
String content = Files.readString(path);

// записать строку целиком
Files.writeString(path, "Привет, мир");
```

Сравните с `java.io` (модуль 18) — там для того же нужен цикл с `BufferedReader`. NIO короче.

### Управление файлами

```java
Files.exists(path);              // существует ли
Files.createFile(path);          // создать файл
Files.createDirectories(dir);    // создать каталоги (включая родительские)
Files.delete(path);              // удалить (ошибка, если нет)
Files.deleteIfExists(path);      // удалить, если существует
Files.size(path);                // размер в байтах
Files.copy(src, dest);           // копировать
Files.move(src, dest);           // переместить/переименовать
```

### Обход каталога

```java
import java.util.stream.Stream;

try (Stream<Path> files = Files.list(dir)) {    // содержимое каталога
    files.forEach(System.out::println);
}
```

> `Files.list` возвращает Stream (см. [модуль 24](../m24_stream_api_lambda/theory.md)) — его нужно закрывать (try-with-resources).

---

## `java.io` vs `java.nio` — что выбрать

| | `java.io` | `java.nio.file` |
|--|-----------|------------------|
| Чтение всех строк | цикл `readLine()` | `Files.readAllLines()` |
| Запись | `FileWriter` + цикл | `Files.write()` |
| Управление (copy/delete) | класс `File`, неудобно | `Files`, удобно |
| Стиль | потоковый | высокоуровневый |

> Для простых операций «прочитать/записать файл целиком» используйте `Files`. Для потоковой обработки больших файлов — `BufferedReader` из модуля 18.

---

## Паттерн Decorator (Декоратор)

**Decorator** — приём, при котором один объект «оборачивает» другой, добавляя ему возможности, не меняя его класс. Вся библиотека `java.io` построена на этом паттерне.

```java
//   базовый поток        обёртка-буфер        обёртка-удобный API
new PrintWriter(new BufferedWriter(new FileWriter("f.txt")));

//   FileReader        →  BufferedReader (добавил readLine())
new BufferedReader(new FileReader("f.txt"));
```

```
+---------------------------------------------+
| BufferedReader  (+ буферизация, readLine)     |
|  +--------------------------------------+    |
|  | FileReader  (чтение символов из файла) |    |
|  +--------------------------------------+    |
+---------------------------------------------+
   каждый «слой» добавляет возможности
```

### Своя реализация Decorator

```java
interface Coffee {
    String description();
    double cost();
}

class SimpleCoffee implements Coffee {
    public String description() { return "Кофе"; }
    public double cost() { return 100; }
}

// декоратор хранит ссылку на оборачиваемый объект
class MilkDecorator implements Coffee {
    private final Coffee coffee;
    MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public String description() { return coffee.description() + " + молоко"; }
    public double cost() { return coffee.cost() + 30; }
}
```

```java
Coffee c = new MilkDecorator(new SimpleCoffee());
System.out.println(c.description() + " = " + c.cost());  // Кофе + молоко = 130.0
```

> Decorator позволяет **гибко комбинировать** поведение во время выполнения, вместо создания множества подклассов.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `Files.readAllLines` для гигантского файла | грузит всё в память; для больших — потоковое чтение |
| `Files.delete` несуществующего файла | бросает исключение; используйте `deleteIfExists` |
| незакрытый `Files.list` Stream | утечка; оборачивайте в try-with-resources |
| `Path` ≠ существующий файл | путь — это лишь описание; проверяйте `Files.exists` |

---

## Что дальше

В [модуле 20](../m20_date_time/theory.md) — работа с датой и временем.
