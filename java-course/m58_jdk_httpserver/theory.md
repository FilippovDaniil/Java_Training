# Модуль 58. Ручной HTTP-сервер на JDK (com.sun.net.httpserver)

Java поставляется с встроенным HTTP-сервером — `com.sun.net.httpserver`. Он входит в стандартный JDK (не требует сторонних библиотек) и позволяет поднять работающий HTTP-сервер буквально в десяти строках кода. Это мощный инструмент для прототипирования, юнит-тестов и небольших микросервисов. А ещё — лучший способ понять, как HTTP работает «под капотом», прежде чем перейти к Spring.

> Практика — создание HTTP-сервера и REST-микросервиса без сторонних библиотек.
> Знание HTTP обязательно — см. [модуль 55](../m55_http_basics/theory.md) и [модуль 57](../m57_java_http_client/theory.md).

---

## Архитектура: как сервер обрабатывает запрос

```
  Клиент (браузер / curl)
        |
        |  TCP-соединение  (порт 8080)
        ▼
  +-----------------------------------------+
  |           HttpServer                    |
  |  слушает порт, принимает соединения     |
  |                                         |
  |  createContext("/notes")  --------------+--► NotesHandler
  |  createContext("/")       --------------+--► RootHandler
  +-----------------------------------------+
        |
        |  передаёт HttpExchange
        ▼
  +----------------------+
  |    HttpHandler       |  ◄-- ваш код
  |  handle(exchange)    |
  |  – читает метод/URI  |
  |  – читает тело       |
  |  – пишет ответ       |
  +----------------------+
```

---

## Запуск сервера

```java
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
server.createContext("/", new RootHandler());
server.setExecutor(Executors.newFixedThreadPool(4)); // пул потоков
server.start();
System.out.println("Сервер запущен: http://localhost:8080");
```

| Параметр `create` | Значение |
|-------------------|----------|
| `InetSocketAddress(8080)` | адрес и порт прослушивания |
| `0` (backlog) | очередь ожидающих соединений (0 = системный дефолт) |

### setExecutor

| Значение | Поведение |
|----------|-----------|
| `setExecutor(null)` | однопоточный сервер (дефолт) |
| `Executors.newFixedThreadPool(N)` | пул из N потоков — рекомендуется для нагрузки |
| `Executors.newVirtualThreadPerTaskExecutor()` | виртуальные потоки (Java 21+) |

---

## HttpHandler и HttpExchange

Каждый контекст требует реализацию `HttpHandler`:

```java
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // здесь обрабатываем запрос
    }
}
```

### Ключевые методы HttpExchange

| Метод | Что возвращает |
|-------|----------------|
| `getRequestMethod()` | `"GET"`, `"POST"`, `"PUT"`, `"DELETE"` |
| `getRequestURI()` | `URI` — полный URI запроса |
| `getRequestURI().getPath()` | строка пути, например `"/notes/5"` |
| `getRequestHeaders()` | `Headers` — заголовки запроса |
| `getRequestBody()` | `InputStream` — тело запроса |
| `getResponseHeaders()` | `Headers` — заголовки ответа (для установки) |
| `sendResponseHeaders(code, length)` | отправить статус и длину тела |
| `getResponseBody()` | `OutputStream` — поток для записи тела ответа |

---

## Отправка ответа

```java
private void sendResponse(HttpExchange exchange, int statusCode, String body) throws IOException {
    byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
    exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
    exchange.sendResponseHeaders(statusCode, bytes.length);
    try (OutputStream os = exchange.getResponseBody()) {
        os.write(bytes);
    }
}
```

**Критически важно:** `sendResponseHeaders(statusCode, length)` — второй аргумент это **длина тела в байтах**, не символах. Для кириллицы они различаются.

Значения второго аргумента:
- `>= 0` — тело фиксированной длины (передаётся в `Content-Length`)
- `-1` — нет тела (ответы 204, 304)

---

## Чтение тела запроса

```java
private String readBody(HttpExchange exchange) throws IOException {
    try (InputStream is = exchange.getRequestBody()) {
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }
}
```

---

## Роутинг по пути и методу

```java
@Override
public void handle(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();
    String path   = exchange.getRequestURI().getPath();

    if ("GET".equals(method) && "/notes".equals(path)) {
        handleGetAll(exchange);
    } else if ("POST".equals(method) && "/notes".equals(path)) {
        handleCreate(exchange);
    } else if ("GET".equals(method) && path.startsWith("/notes/")) {
        handleGetOne(exchange, path);
    } else {
        sendError(exchange, 404, "Маршрут не найден");
    }
}
```

---

## JSON-ответы вручную

Без сторонних библиотек JSON формируется строками:

```java
// Объект
String json = String.format("{\"id\":%d,\"text\":\"%s\"}", id, text);

// Массив
StringBuilder sb = new StringBuilder("[");
for (Note n : notes) {
    sb.append(String.format("{\"id\":%d,\"text\":\"%s\"},", n.id, n.text));
}
if (sb.length() > 1) sb.setLength(sb.length() - 1); // убрать последнюю запятую
sb.append("]");
```

---

## Единый контракт ошибок

Все ошибки возвращаются в едином формате:

```json
{"error": "Заметка не найдена", "status": 404}
```

```java
private void sendError(HttpExchange exchange, int code, String message) throws IOException {
    String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
    sendResponse(exchange, code, json);
}
```

| HTTP-статус | Когда использовать |
|-------------|-------------------|
| 200 OK | успешный GET |
| 201 Created | успешный POST (создание) |
| 204 No Content | успешный DELETE без тела |
| 400 Bad Request | ошибка клиента (пустое тело, невалидные данные) |
| 404 Not Found | ресурс не найден |
| 405 Method Not Allowed | метод не поддерживается |

---

## Извлечение ID из пути

```java
// Путь: /notes/42
String path = exchange.getRequestURI().getPath(); // "/notes/42"
String[] parts = path.split("/");                 // ["", "notes", "42"]
int id = Integer.parseInt(parts[parts.length - 1]);
```

---

## Подводные камни

| Ошибка | Последствие | Решение |
|--------|-------------|---------|
| Не закрыть `getResponseBody()` | соединение зависает, клиент ждёт | `try-with-resources` для OutputStream |
| Длина тела в символах, не байтах | `Content-Length` неверен, тело обрезается | `bytes.length`, где `bytes = body.getBytes(UTF_8)` |
| `sendResponseHeaders` без последующей записи | клиент получает пустой ответ | всегда писать тело после `sendResponseHeaders` |
| Забыть `server.start()` | сервер не запускается, ошибки нет | вызывать явно |
| `setExecutor(null)` под нагрузкой | все запросы в одном потоке, очередь растёт | использовать `Executors.newFixedThreadPool(N)` |
| Не обработать `NumberFormatException` | 500-like падение при плохом ID в URL | `try/catch` с отправкой 400 |
| Кириллица в JSON без явного UTF-8 | мусор на клиенте | `getBytes(StandardCharsets.UTF_8)` везде |

---

## Контекст и маршрутизация

`createContext(path)` регистрирует **префикс** пути, а не точный путь:

```java
server.createContext("/notes", handler);
// Получит запросы: /notes  /notes/  /notes/42  /notes/42/tags
```

Точную проверку пути делайте внутри `HttpHandler`.

---

## ASCII-схема полного цикла запроса

```
curl -X POST http://localhost:8080/notes -d '{"text":"купить молоко"}'
  |
  | TCP SYN → ACK
  ▼
HttpServer.accept()
  |
  | парсит HTTP-запрос (метод, URI, заголовки, тело)
  ▼
Executor.submit(задача) — новый поток из пула
  |
  ▼
NotesHandler.handle(exchange)
  +- getRequestMethod()  → "POST"
  +- getRequestURI()     → /notes
  +- getRequestBody()    → {"text":"купить молоко"}
  +- создать заметку, id = 1
  +- sendResponseHeaders(201, длина)
  +- getResponseBody().write({"id":1,"text":"купить молоко"})
  |
  ▼
Клиент получает ответ 201 Created
```

---

## Дополнительные источники

- [Javadoc com.sun.net.httpserver](https://docs.oracle.com/en/java/se/17/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html)
- [JEP 408 — Simple Web Server (Java 18)](https://openjdk.org/jeps/408) — упрощённая версия для статики
- «Java Network Programming» (Elliotte Harold) — глава о серверных сокетах

## Что дальше

В [модуле 59](../m59_spring_core_intro/theory.md) мы перейдём к **Spring Framework**. Spring IoC, DispatcherServlet и `@RestController` автоматизируют всё то, что мы делали вручную в этом модуле: роутинг, чтение тела, сериализацию JSON, обработку ошибок. Понимание ручного сервера делает Spring прозрачным — теперь вы знаете, что происходит «под капотом».
