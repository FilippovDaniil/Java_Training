# Модуль 65. Spring Boot Web: REST-контроллеры, конфигурация, валидация

В [модуле 64](../module-64-spring-boot-intro/theory.md) мы подняли первое Boot-приложение и сделали простой эндпоинт. Теперь строим **полноценное REST-API**: приём и отдача JSON, параметры запроса, статусы ответа, типизированная конфигурация, валидация входных данных и единый контракт ошибок.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-validation`. Bare-`javac` НЕ компилируется — запускайте в IDE/Gradle.

---

## REST-контроллер и HTTP-методы

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")   // общий префикс для всех методов
public class ProductController {

    @GetMapping("/{id}")            // GET  /api/products/42
    public Product getOne(@PathVariable Long id) { ... }

    @GetMapping                     // GET  /api/products
    public List<Product> getAll() { ... }

    @PostMapping                    // POST /api/products
    public Product create(@RequestBody Product p) { ... }

    @PutMapping("/{id}")            // PUT  /api/products/42
    public Product replace(@PathVariable Long id, @RequestBody Product p) { ... }

    @DeleteMapping("/{id}")         // DELETE /api/products/42
    public void delete(@PathVariable Long id) { ... }
}
```

| Аннотация | HTTP-метод | Назначение |
|-----------|-----------|------------|
| `@GetMapping` | GET | чтение |
| `@PostMapping` | POST | создание |
| `@PutMapping` | PUT | полная замена |
| `@PatchMapping` | PATCH | частичное обновление / смена статуса |
| `@DeleteMapping` | DELETE | удаление |

---

## Источники данных запроса

```
                  HTTP-запрос
   POST /api/products/42?notify=true
   Body: { "name": "Мышь", "price": 990 }
        │           │          │
        ▼           ▼          ▼
   @PathVariable  @RequestParam  @RequestBody
   id = 42        notify = true  Product{name, price}
```

| Аннотация | Откуда берёт | Пример |
|-----------|--------------|--------|
| `@PathVariable` | часть URI | `/products/{id}` → `id` |
| `@RequestParam` | query-строка | `?page=2&size=10` |
| `@RequestBody` | тело запроса (JSON → объект) | `{ "name": ... }` |
| `@RequestHeader` | заголовок | `Authorization` |

Преобразование JSON ↔ объект делает **Jackson** (входит в `starter-web`). Подробнее про DTO и JSON — [модуль 56](../module-56-json-jackson-dto/theory.md) и [модуль 70](../module-70-spring-rest-dto-json/theory.md).

---

## DTO вместо сущностей

**DTO** (Data Transfer Object) — объект, описывающий форму данных на границе API. Не отдавайте наружу внутренние сущности — это связывает API с базой и протекает деталями реализации.

```java
public record ProductDto(Long id, String name, long priceKopecks) {}
```

`record` (Java 16+) идеален для DTO: неизменяемый, краткий, с готовыми `equals`/`hashCode`/`toString`.

---

## ResponseEntity и коды статусов

Возврат «голого» объекта даёт `200 OK`. Для контроля над статусом и заголовками используют `ResponseEntity`:

```java
@PostMapping
public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
    ProductDto saved = service.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);   // 201
}

@GetMapping("/{id}")
public ResponseEntity<ProductDto> getOne(@PathVariable Long id) {
    return service.find(id)
            .map(ResponseEntity::ok)                                // 200
            .orElse(ResponseEntity.notFound().build());             // 404
}
```

Альтернатива — аннотация `@ResponseStatus(HttpStatus.CREATED)` над методом.

| Ситуация | Статус |
|----------|--------|
| Чтение, действие выполнено | **200 OK** |
| Ресурс создан | **201 Created** |
| Выполнено, тела нет | **204 No Content** |
| Ошибка клиента (валидация) | **400 Bad Request** |
| Ресурс не найден | **404 Not Found** |
| Конфликт (дубликат) | **409 Conflict** |

> Соглашения REST (URI без глаголов, существительные во множественном числе, смена статуса через PATCH) подробно разбираются в [модуле 68](../module-68-spring-rest-design/theory.md).

---

## Bean Validation

Подключите `spring-boot-starter-validation`. Размечаете DTO аннотациями ограничений, в контроллере ставите `@Valid` — Spring проверит данные до входа в метод.

```java
import jakarta.validation.constraints.*;

public record CreateProductDto(
        @NotBlank(message = "Название обязательно")
        @Size(min = 2, max = 100)
        String name,

        @Min(value = 0, message = "Цена не может быть отрицательной")
        long priceKopecks
) {}
```

```java
@PostMapping
public ResponseEntity<?> create(@Valid @RequestBody CreateProductDto dto) { ... }
```

| Аннотация | Проверяет |
|-----------|-----------|
| `@NotNull` | значение не `null` |
| `@NotBlank` | строка не пустая и не из пробелов |
| `@NotEmpty` | коллекция/строка не пустая |
| `@Size(min, max)` | длина строки/коллекции |
| `@Min` / `@Max` | границы числа |
| `@Email` | формат email |
| `@Pattern(regexp)` | соответствие регулярке |

Если валидация не прошла — Spring бросает `MethodArgumentNotValidException`, по умолчанию это `400`.

---

## @ControllerAdvice — единый контракт ошибок

Чтобы клиент всегда получал ошибки в одинаковом формате, обработку исключений выносят в один класс:

```java
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> body = Map.of(
            "status", 400,
            "error", "Validation Failed",
            "fields", fields
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", 404, "error", ex.getMessage()));
    }
}
```

```
   Контроллер бросает исключение
            │
            ▼
   @RestControllerAdvice (перехватывает ГЛОБАЛЬНО)
            │
            ▼
   @ExceptionHandler(нужный тип) → ResponseEntity с единым телом
```

> Современный стандарт тела ошибки — **`ProblemDetail`** (RFC 7807), он встроен в Spring 6 / Boot 3. Подробно — в [модуле 72](../module-72-spring-rest-error-handling/theory.md).

---

## @ConfigurationProperties — типизированная конфигурация

Вместо россыпи `@Value` группируйте связанные свойства в один типизированный бин:

```properties
shop.currency=RUB
shop.tax-rate=20
shop.support-email=help@shop.ru
```

```java
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shop")
public class ShopProperties {
    private String currency;
    private int taxRate;             // shop.tax-rate → taxRate (relaxed binding)
    private String supportEmail;
    // геттеры/сеттеры...
}
```

Активировать: `@EnableConfigurationProperties(ShopProperties.class)` или `@ConfigurationPropertiesScan` на главном классе.

| `@Value` | `@ConfigurationProperties` |
|----------|----------------------------|
| по одному свойству | целый блок свойств |
| строковый SpEL | типобезопасно (int, List, вложенные объекты) |
| сложно тестировать | легко создать и подставить в тесте |
| подходит для 1–2 значений | предпочтителен для группы настроек |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@Valid` не срабатывает (нет 400) | забыли `@Valid` перед `@RequestBody` или нет `starter-validation` | добавить и аннотацию, и зависимость |
| POST возвращает `415 Unsupported Media Type` | клиент не прислал `Content-Type: application/json` | задать заголовок на стороне клиента |
| `@RequestParam` обязателен, а его нет → `400` | параметр не передан | `@RequestParam(required = false)` или `defaultValue` |
| Сущность БД «протекает» в JSON | отдаём `@Entity` напрямую | возвращать DTO/`record` |
| 404 не отдаётся, вместо него 200 с `null` | вернули `null` вместо `ResponseEntity.notFound()` | явно строить статус через `ResponseEntity` |
| `@ConfigurationProperties` не наполняется | бин не активирован | `@EnableConfigurationProperties` или `@ConfigurationPropertiesScan` |

---

## Дополнительные источники

- [Spring Web MVC — официальная документация](https://docs.spring.io/spring-framework/reference/web/webmvc.html).
- [Jakarta Bean Validation](https://beanvalidation.org/).
- [модуль 56](../module-56-json-jackson-dto/theory.md) — Jackson, DTO, сериализация JSON.
- Глобальные соглашения REST API — раздел «🌐 REST API — СОГЛАШЕНИЯ» в проектных инструкциях.

## Что дальше

В [модуле 66](../module-66-spring-boot-devops/theory.md) — эксплуатация: логирование, Actuator (`/health`, `/info`), упаковка в jar и тестирование (`@SpringBootTest`, `@WebMvcTest` + `MockMvc`).
