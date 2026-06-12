# Модуль 18. Потоки ввода-вывода (I/O Streams)

**Поток (stream)** в I/O — это последовательность данных, текущая от источника к приёмнику: файл, консоль, сеть. (Не путать со Stream API из [модуля 24](../m24_stream_api_lambda/theory.md) — это разные вещи!)

## Два вида потоков

| | Байтовые потоки | Символьные потоки |
|--|-----------------|-------------------|
| Базовые классы | `InputStream` / `OutputStream` | `Reader` / `Writer` |
| Единица данных | байт | символ (с учётом кодировки) |
| Для чего | картинки, аудио, любые байты | текст |

```
ИСТОЧНИК --▶ [ поток ] --▶ ПРОГРАММА     (чтение, Input/Reader)
ПРОГРАММА --▶ [ поток ] --▶ ПРИЁМНИК     (запись, Output/Writer)
```

> Для **текста** используйте `Reader`/`Writer`. Для **бинарных данных** — `InputStream`/`OutputStream`.

---

## Запись текста в файл: `FileWriter`

```java
import java.io.FileWriter;
import java.io.IOException;

try (FileWriter writer = new FileWriter("notes.txt")) {
    writer.write("Первая строка\n");
    writer.write("Вторая строка\n");
} catch (IOException e) {
    System.out.println("Ошибка записи: " + e.getMessage());
}
```

- `new FileWriter("file.txt")` — перезаписывает файл.
- `new FileWriter("file.txt", true)` — **дописывает** в конец (append).
- try-with-resources автоматически закроет файл.

> ⚠️ Файловые операции бросают **проверяемое** `IOException` — его обязательно обрабатывать (см. [модуль 17](../m17_exceptions/theory.md)).

---

## Чтение файла построчно: `BufferedReader`

`BufferedReader` буферизует данные и умеет читать **целые строки** методом `readLine()`.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

try (BufferedReader reader = new BufferedReader(new FileReader("notes.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {   // null = конец файла
        System.out.println(line);
    }
} catch (IOException e) {
    System.out.println("Ошибка чтения: " + e.getMessage());
}
```

```
FileReader (читает символы) --обёрнут в--▶ BufferedReader (читает строки)
```

> `readLine()` возвращает `null` в конце файла — это условие выхода из цикла.

---

## Зачем буферизация

| Без буфера (`FileReader.read()`) | С буфером (`BufferedReader`) |
|----------------------------------|------------------------------|
| обращение к диску на каждый символ | читает блоками в память |
| медленно | ⚡ быстро |
| нет `readLine()` | есть `readLine()` |

---

## Удобная запись: `PrintWriter`

`PrintWriter` даёт привычные `println`/`printf`, как у `System.out`:

```java
try (PrintWriter pw = new PrintWriter(new FileWriter("out.txt"))) {
    pw.println("Строка с переводом");
    pw.printf("Число: %d%n", 42);
}
```

---

## Чтение с консоли через `BufferedReader`

Альтернатива `Scanner` (быстрее для больших объёмов):

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();          // прочитать строку
int n = Integer.parseInt(br.readLine());  // строку → число вручную
```

| | `Scanner` | `BufferedReader` |
|--|-----------|------------------|
| удобство | высокое (`nextInt`, `nextDouble`) | ниже (только строки) |
| скорость | ниже | ⚡ выше |
| парсинг чисел | встроен | вручную (`parseInt`) |

---

## Байтовые потоки: копирование файла

```java
try (FileInputStream in = new FileInputStream("a.dat");
     FileOutputStream out = new FileOutputStream("b.dat")) {
    int b;
    while ((b = in.read()) != -1) {   // -1 = конец потока
        out.write(b);
    }
}
```

> Для байтовых потоков конец данных — это `-1` (а не `null`).

---

## Стандартные потоки

| Поток | Назначение |
|-------|-----------|
| `System.in` | стандартный ввод (клавиатура) — `InputStream` |
| `System.out` | стандартный вывод (консоль) — `PrintStream` |
| `System.err` | вывод ошибок | 

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| забыли закрыть поток | утечка ресурсов; используйте try-with-resources |
| не обработали `IOException` | ошибка компиляции (checked) |
| чтение байтами вместо символов | проблемы с кодировкой текста |
| перезапись вместо дозаписи | `FileWriter(path)` затирает; нужен `FileWriter(path, true)` |
| `readLine()` в бесконечном цикле | проверяйте `!= null` |

---

## Что дальше

В [модуле 19](../m19_io_nio/theory.md) — современный `java.nio` (`Path`, `Files`) и паттерн Decorator.
