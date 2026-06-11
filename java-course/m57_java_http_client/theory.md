# Модуль 57. Java HTTP Client — продвинутый уровень

В [модуле 38](../m38_http_protocol/theory.md) рассмотрены основы `java.net.http`: отправка GET/POST, чтение тела и заголовков. Этот модуль — следующий шаг: тонкая настройка клиента, таймауты, обработка ошибок, разные форматы ответов и асинхронность через `CompletableFuture`.

> Весь API входит в JDK 11+ — внешние зависимости не нужны. Базовые концепции HTTP см. в [модуле 55](../m55_http_basics/theory.md).

---

## Архитектура: клиент → запрос → ответ

```
 ┌─────────────────────────────────────────────────────────┐
 │                    HttpClient                           │
 │  (переиспользуется; хранит пул соединений)              │
 │  connectTimeout · version · followRedirects             │
 │  cookieHandler · authenticator · executor               │
 └────────────────────┬────────────────────────────────────┘
                      │ send(request, bodyHandler)
                      │ sendAsync(request, bodyHandler)
                      ▼
 ┌─────────────────────────────────────────────────────────┐
 │                   HttpRequest                           │
 │  uri · method · headers                                 │
 │  timeout(Duration) · version                            │
 │  body: BodyPublisher (ofString / ofFile / noBody …)     │
 └────────────────────┬────────────────────────────────────┘
                      │
                      ▼
 ┌─────────────────────────────────────────────────────────┐
 │               HttpResponse<T>                           │
 │  statusCode() · headers() · body(): T                   │
 │  body: BodyHandler (ofString / ofLines / ofFile …)      │
 └─────────────────────────────────────────────────────────┘
```

---

## HttpClient — конфигурация через builder

```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(5))      // таймаут соединения
    .version(HttpClient.Version.HTTP_2)         // предпочесть HTTP/2 (fallback → HTTP/1.1)
    .followRedirects(HttpClient.Redirect.NORMAL)// следовать 3xx (кроме HTTPS→HTTP)
    .build();
```

`HttpClient` — потокобезопасный; создавайте **один экземпляр** на приложение и переиспользуйте.

| Параметр builder | Что настраивает |
|------------------|----------------|
| `connectTimeout` | максимальное время установки TCP-соединения |
| `version` | HTTP_1_1 / HTTP_2 / HTTP_1_1 (по умолчанию) |
| `followRedirects` | NEVER / ALWAYS / NORMAL (3xx без понижения протокола) |
| `cookieHandler` | хранилище cookies (`new CookieManager()`) |
| `authenticator` | базовая/дайджест-аутентификация |
| `executor` | ThreadPool для `sendAsync` (по умолчанию ForkJoinPool) |

---

## HttpRequest — методы и тело

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
    .timeout(Duration.ofSeconds(10))            // таймаут одного запроса
    .header("Accept", "application/json")
    .header("User-Agent", "MyApp/1.0")
    .GET()                                      // или .POST(...) / .PUT(...) / .DELETE()
    .build();
```

### BodyPublishers (тело запроса)

| Метод | Когда использовать |
|-------|-------------------|
| `BodyPublishers.noBody()` | GET / DELETE без тела |
| `BodyPublishers.ofString(s)` | JSON/XML как строка |
| `BodyPublishers.ofByteArray(b)` | бинарные данные |
| `BodyPublishers.ofFile(path)` | отправить файл |
| `BodyPublishers.ofInputStream(sup)` | потоковая отправка |

---

## HttpResponse — чтение ответа

### BodyHandlers (тело ответа)

| Метод | Тип `body()` | Когда использовать |
|-------|-------------|-------------------|
| `BodyHandlers.ofString()` | `String` | небольшой JSON/текст |
| `BodyHandlers.ofLines()` | `Stream<String>` | построчная обработка |
| `BodyHandlers.ofByteArray()` | `byte[]` | бинарные данные |
| `BodyHandlers.ofFile(path)` | `Path` | сохранить на диск |
| `BodyHandlers.ofInputStream()` | `InputStream` | потоковая обработка |
| `BodyHandlers.discarding()` | `Void` | когда тело не нужно |

```java
// Получить как строку
HttpResponse<String> r = client.send(req, HttpResponse.BodyHandlers.ofString());
System.out.println(r.statusCode());  // 200, 201, 404 …
System.out.println(r.body());

// Обработать построчно (без загрузки всего тела в память)
HttpResponse<Stream<String>> r2 = client.send(req, HttpResponse.BodyHandlers.ofLines());
r2.body().forEach(System.out::println);
```

---

## Синхронный send vs асинхронный sendAsync

| | `send` | `sendAsync` |
|---|--------|------------|
| Возвращает | `HttpResponse<T>` | `CompletableFuture<HttpResponse<T>>` |
| Блокирует поток | да, до получения ответа | нет |
| Исключения | `IOException`, `InterruptedException` | `CompletionException` (оборачивает) |
| Когда использовать | простые скрипты, последовательные вызовы | несколько запросов параллельно, UI/реактивный код |

```java
// Асинхронный запрос — не блокирует поток
CompletableFuture<String> future = client
    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body);

// Запустить несколько параллельно и подождать все
CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
all.join();  // блокируем только здесь — один раз
```

---

## Обработка ошибок и ретраи

```java
// Проверка статуса — всегда!
if (response.statusCode() >= 400) {
    System.err.println("Ошибка: " + response.statusCode() + " — " + response.body());
}

// Простой ретрай при 5xx (не более N попыток)
HttpResponse<String> sendWithRetry(HttpClient client, HttpRequest req, int maxAttempts)
        throws IOException, InterruptedException {
    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
        HttpResponse<String> r = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (r.statusCode() < 500) return r;          // 2xx/4xx — не повторяем
        if (attempt < maxAttempts) Thread.sleep(500L * attempt);  // экспоненциальная задержка
    }
    throw new IOException("Сервер недоступен после " + maxAttempts + " попыток");
}
```

---

## Query-параметры и URI

`HttpRequest` принимает `java.net.URI` — нет встроенного метода для добавления параметров. Используйте `UriComponentsBuilder` (Spring) или постройте строку вручную:

```java
String userId = "1";
URI uri = URI.create("https://jsonplaceholder.typicode.com/posts?userId=" + userId);
// для сложных параметров — URLEncoder:
String encoded = URLEncoder.encode(value, StandardCharsets.UTF_8);
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| Создавать новый `HttpClient` на каждый запрос | пул соединений не используется; создавайте один раз |
| Не проверять `statusCode()` | 4xx/5xx — тоже «успешный» ответ с точки зрения клиента |
| Не задавать таймауты | запрос может зависнуть навсегда |
| `sendAsync` без обработки ошибок в pipeline | `CompletableFuture.exceptionally(...)` обязателен |
| `ofLines()` — не закрыть `Stream` | ресурсы сокета держатся открытыми; используйте try-with-resources |
| Конкатенировать URI вручную с непроверенными данными | SQL/URI-инъекция; кодируйте через `URLEncoder` |

---

## Дополнительные источники

- JavaDoc: `java.net.http` (docs.oracle.com/en/java/javase/17/docs/api/java.net.http).
- JEP 321 — HTTP Client (Standard) — мотивация и дизайн.
- Публичный тестовый API: `https://jsonplaceholder.typicode.com`.

## Что дальше

В [модуле 58](../m58_jdk_httpserver/theory.md) — встроенный HTTP-сервер JDK (`com.sun.net.httpserver`).
