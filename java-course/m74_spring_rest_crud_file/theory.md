# Модуль 74. CRUD, не-CRUD действия и работа с файлами

Большинство эндпоинтов — это **CRUD** (Create/Read/Update/Delete). Но бывают и действия вне CRUD, а ещё API часто работает с **файлами**: загрузка вложений, выгрузка отчётов, экспорт. Разберём оба сценария.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`. Продолжаем **Task Tracker API**.

---

## CRUD — четыре базовые операции

| Операция | HTTP | URI | Успех |
|----------|------|-----|-------|
| **C**reate | POST | `/tasks` | 201 + `Location` |
| **R**ead (один) | GET | `/tasks/{id}` | 200 / 404 |
| **R**ead (список) | GET | `/tasks` | 200 |
| **U**pdate (целиком) | PUT | `/tasks/{id}` | 200 |
| **U**pdate (частично) | PATCH | `/tasks/{id}` | 200 |
| **D**elete | DELETE | `/tasks/{id}` | 204 |

Это «скелет» почти любого ресурса (детально — [модуль 68](../module-68-spring-rest-design/theory.md)).

---

## Не-CRUD действия

Иногда нужно действие, не сводящееся к CRUD: «архивировать», «назначить исполнителя», «экспортировать». Подходы (от лучшего к запасному):

1. **Смена состояния:** `PATCH /tasks/{id}` с `{"status":"ARCHIVED"}`.
2. **Подресурс-существительное:** `POST /tasks/{id}/assignments` — создать назначение.
3. **Действие-эндпоинт (когда иначе никак):** `POST /tasks/{id}/export`.

> Файловые операции (`upload`/`download`) — как раз пример не-CRUD: они оперируют не JSON-представлением, а бинарными данными.

---

## Загрузка файла (upload)

Файлы шлют как `multipart/form-data`. В Spring файл принимается параметром `MultipartFile`:

```java
import org.springframework.web.multipart.MultipartFile;

@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) return ResponseEntity.badRequest().body("Файл пуст");
    String name = file.getOriginalFilename();
    long size   = file.getSize();
    byte[] bytes = file.getBytes();        // содержимое (или file.getInputStream())
    // ... сохранить ...
    return ResponseEntity.ok("Загружен: " + name + " (" + size + " байт)");
}
```

| Метод `MultipartFile` | Возвращает |
|------------------------|------------|
| `getOriginalFilename()` | имя файла у клиента |
| `getContentType()` | MIME-тип (`image/png`) |
| `getSize()` | размер в байтах |
| `getBytes()` / `getInputStream()` | содержимое |
| `isEmpty()` | пуст ли файл |
| `transferTo(File)` | сохранить на диск |

Ограничения размера — в `application.properties`:

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

## Файл + метаданные в одном запросе

Когда вместе с файлом нужен JSON (например, описание вложения), используют `@RequestPart`:

```java
@PostMapping(value = "/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> upload(
        @RequestPart("file") MultipartFile file,
        @RequestPart("meta") AttachmentMeta meta) {   // meta — JSON-часть
    ...
}
```

---

## Несколько файлов

```java
@PostMapping("/upload-many")
public String uploadMany(@RequestParam("files") MultipartFile[] files) {
    return "Загружено файлов: " + files.length;
}
```

---

## Выгрузка файла (download)

Возвращаем содержимое плюс заголовки `Content-Type` и `Content-Disposition`:

```java
@GetMapping("/download/{id}")
public ResponseEntity<byte[]> download(@PathVariable Long id) {
    byte[] data = storage.load(id);
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"report-" + id + ".pdf\"")
            .body(data);
}
```

| `Content-Disposition` | Поведение браузера |
|------------------------|--------------------|
| `attachment; filename="x"` | скачать как файл |
| `inline` | показать в браузере |

Для больших файлов лучше отдавать `Resource` (стрим), а не грузить всё в память:

```java
@GetMapping("/download/{id}")
public ResponseEntity<Resource> download(@PathVariable Long id) {
    Resource res = new InputStreamResource(storage.openStream(id));
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file\"")
            .body(res);
}
```

---

## Экспорт данных (CSV)

Частый кейс — отдать коллекцию как файл (CSV/Excel):

```java
@GetMapping(value = "/export", produces = "text/csv")
public ResponseEntity<String> exportCsv() {
    StringBuilder csv = new StringBuilder("id,title,status\n");
    tasks.forEach(t -> csv.append(t.id()).append(',')
                          .append(t.title()).append(',')
                          .append(t.status()).append('\n'));
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tasks.csv\"")
            .body(csv.toString());
}
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `MultipartFile` приходит `null`/пустой | имя параметра ≠ имени поля формы | согласовать `@RequestParam("file")` и поле формы |
| `MaxUploadSizeExceededException` | файл больше лимита | поднять `spring.servlet.multipart.max-file-size` |
| Файл скачивается с кривым именем | нет/неверный `Content-Disposition` | задать `attachment; filename="..."` |
| Браузер открывает файл вместо скачивания | `inline` или нет заголовка | `Content-Disposition: attachment` |
| OOM при больших файлах | весь файл грузится в `byte[]` | отдавать/принимать через стрим (`Resource`/`InputStream`) |
| `415` при загрузке | нет `consumes = multipart/form-data` | указать `consumes` |

---

## Дополнительные источники

- [Spring: Multipart (File Upload)](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/multipart.html).
- [MDN: Content-Disposition](https://developer.mozilla.org/ru/docs/Web/HTTP/Headers/Content-Disposition).
- [модуль 18](../module-18-io-streams/theory.md) и [модуль 19](../module-19-io-nio/theory.md) — потоки и файлы в Java.

## Что дальше

В [модуле 75](../module-75-spring-rest-config-openapi/theory.md) — конфигурация, версионирование API, CORS и документация через OpenAPI/Swagger.
