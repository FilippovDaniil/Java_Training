# Модуль 38. Протоколы HTTP/HTTPS

**HTTP** (HyperText Transfer Protocol) — протокол передачи данных в вебе: клиент шлёт **запрос**, сервер возвращает **ответ**. Работает поверх TCP (см. [модуль 36](../m36_network_basics/theory.md)).

> Практика выполняется на встроенном `java.net.http.HttpClient` (Java 11+) — **внешние зависимости не нужны**. Для запуска требуется доступ в интернет.

## Запрос и ответ

```
КЛИЕНТ                                      СЕРВЕР
  │   GET /users/1 HTTP/1.1                   │
  │   Host: api.example.com                   │
  │   Accept: application/json                │
  │ ─────────────────────────────────────────▶│
  │                                            │
  │   HTTP/1.1 200 OK                          │
  │   Content-Type: application/json           │
  │                                            │
  │   {"id":1,"name":"Иван"}                   │
  │ ◀─────────────────────────────────────────│
```

### Структура запроса

```
GET /path?query=1 HTTP/1.1        ← строка запроса (метод, путь, версия)
Host: example.com                 ← заголовки (headers)
Accept: application/json
                                  ← пустая строка
тело запроса (для POST/PUT)       ← body
```

---

## HTTP-методы

| Метод | Назначение | Тело |
|-------|-----------|------|
| `GET` | получить данные | нет |
| `POST` | создать ресурс | да |
| `PUT` | полностью заменить | да |
| `PATCH` | частично обновить | да |
| `DELETE` | удалить | обычно нет |

> GET должен быть **идемпотентным** и без побочных эффектов (только чтение).

---

## Коды состояния (status codes)

```
1xx — информация
2xx — успех        (200 OK, 201 Created, 204 No Content)
3xx — перенаправление (301 Moved, 304 Not Modified)
4xx — ошибка клиента  (400 Bad Request, 401, 403, 404 Not Found)
5xx — ошибка сервера  (500 Internal Error, 503 Unavailable)
```

| Код | Значение |
|-----|----------|
| 200 | OK — успех |
| 201 | Created — создано |
| 400 | Bad Request — неверный запрос |
| 401 | Unauthorized — не аутентифицирован |
| 403 | Forbidden — нет прав |
| 404 | Not Found — не найдено |
| 500 | Internal Server Error — сбой сервера |

---

## Заголовки (headers)

Метаданные запроса/ответа:

| Заголовок | Назначение |
|-----------|-----------|
| `Content-Type` | формат тела (`application/json`) |
| `Accept` | какой формат ждёт клиент |
| `Authorization` | токен/учётные данные |
| `User-Agent` | клиентское приложение |
| `Set-Cookie` / `Cookie` | передача cookies |

---

## HTTPS — защищённый HTTP

HTTPS = HTTP + **TLS** (шифрование). Данные между клиентом и сервером шифруются, что защищает от перехвата.

```
HTTP:   данные ──── открытым текстом ────▶ (можно перехватить)
HTTPS:  данные ──── зашифровано (TLS) ───▶ (защищено)
```

> Порт HTTP — 80, HTTPS — 443. Сегодня практически весь веб использует HTTPS.

---

## `HttpClient` в Java (java.net.http)

С Java 11 в JDK встроен современный HTTP-клиент.

### GET-запрос

```java
import java.net.URI;
import java.net.http.*;

HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users/1"))
    .GET()
    .header("Accept", "application/json")
    .build();

HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());

System.out.println(response.statusCode());   // 200
System.out.println(response.body());          // тело ответа
```

### POST-запрос с телом

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Иван\"}"))
    .build();
```

### Чтение заголовков ответа

```java
HttpHeaders headers = response.headers();
headers.firstValue("Content-Type").ifPresent(System.out::println);
```

---

## Синхронный и асинхронный вызов

```java
// синхронно — блокирует поток до ответа
HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());

// асинхронно — возвращает CompletableFuture, не блокирует
client.sendAsync(request, BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println);
```

---

## Cookies

```java
import java.net.CookieManager;

HttpClient client = HttpClient.newBuilder()
    .cookieHandler(new CookieManager())   // автоматически хранит cookies
    .build();
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| не проверяют `statusCode()` | 4xx/5xx — тоже «ответ», но это ошибка |
| блокирующий `send` в UI/потоке | используйте `sendAsync` для отзывчивости |
| ручная сборка JSON строками | используйте библиотеку (Jackson/Gson) |
| HTTP вместо HTTPS для приватных данных | данные передаются открыто |
| отсутствие таймаутов | запрос может «зависнуть»; задавайте `.timeout(...)` |

## Дополнительные источники

- JavaDoc `java.net.http`.
- MDN Web Docs — HTTP (developer.mozilla.org).
- Публичные тестовые API: `https://jsonplaceholder.typicode.com`, `https://httpbin.org`.

## Что дальше

В [модуле 39](../m39_servlets/theory.md) — сервлеты (теория).
