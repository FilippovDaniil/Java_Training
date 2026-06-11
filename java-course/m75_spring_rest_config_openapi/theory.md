# Модуль 75. Конфигурация, версионирование и OpenAPI

API живёт долго: меняются требования, добавляются поля, приходят новые клиенты. Чтобы развивать API, **не ломая существующих клиентов**, нужны: грамотная конфигурация, стратегия **версионирования**, настройка **CORS** и автоматическая **документация** (OpenAPI/Swagger).

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`; для документации — `org.springdoc:springdoc-openapi-starter-webmvc-ui`. Продолжаем **Task Tracker API**.

---

## Внешняя конфигурация (напоминание)

Параметры API выносятся в `application.properties` и читаются через `@Value` или `@ConfigurationProperties` ([модуль 65](../m65_spring_boot_web_config/theory.md)):

```properties
api.title=Task Tracker API
api.version=1.0.0
api.default-page-size=20
```

Преимущество: одно и то же приложение настраивается под dev/prod без пересборки (профили — [модуль 62](../m62_spring_core_configuration/theory.md)).

---

## Эволюция API: что ломает, а что нет

| Изменение | Совместимо? |
|-----------|:-----------:|
| Добавить **необязательное** поле в ответ | ✅ да |
| Добавить новый эндпоинт | ✅ да |
| Сделать обязательное поле необязательным | ✅ да |
| Удалить/переименовать поле | ❌ нет |
| Сделать необязательное поле обязательным | ❌ нет |
| Изменить тип поля / семантику | ❌ нет |
| Изменить URI существующего ресурса | ❌ нет |

Правило: **развивайте аддитивно**. Несовместимые изменения — через новую версию.

---

## Версионирование API

Три основные стратегии:

| Стратегия | Пример | Плюсы | Минусы |
|-----------|--------|-------|--------|
| **URI** | `/api/v1/tasks`, `/api/v2/tasks` | наглядно, легко тестировать | URI «не вечны» (нарушает чистый REST) |
| **Заголовок** | `Accept: application/vnd.tracker.v2+json` | URI чистые | менее очевидно, сложнее тестировать |
| **Параметр** | `/api/tasks?version=2` | просто | засоряет query |

Самая распространённая — **URI-версионирование** (его и используем).

```java
@RestController
@RequestMapping("/api/v1/tasks")
class TaskControllerV1 { ... }

@RestController
@RequestMapping("/api/v2/tasks")     // v2 добавила поле/изменила форму
class TaskControllerV2 { ... }
```

Версионирование через заголовок — атрибутом `headers`:

```java
@GetMapping(value = "/api/tasks", headers = "X-API-Version=2")
public TaskV2Dto getV2() { ... }
```

---

## CORS

Браузер блокирует запросы со страницы одного источника к API другого (Same-Origin Policy). **CORS** разрешает кросс-доменные запросы. Точечно — `@CrossOrigin`:

```java
@CrossOrigin(origins = "https://app.example.com")
@RestController
@RequestMapping("/api/tasks")
class TaskController { ... }
```

Глобально — через `WebMvcConfigurer`:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://app.example.com")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
    }
}
```

> Не ставьте `allowedOrigins("*")` для API с авторизацией — это дыра в безопасности.

---

## OpenAPI / Swagger

**OpenAPI** — стандарт машиночитаемого описания REST-API. **Swagger UI** — веб-страница, генерируемая из OpenAPI, где API можно изучать и пробовать прямо в браузере.

Для Spring Boot 3 — библиотека **springdoc**:

```groovy
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

После подключения автоматически доступны:

```
   /v3/api-docs          — описание API в формате JSON (OpenAPI)
   /swagger-ui.html      — интерактивная документация
```

springdoc сам анализирует контроллеры и DTO. Аннотации уточняют документацию:

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Operation(summary = "Получить задачу по id", description = "Возвращает задачу или 404")
@ApiResponse(responseCode = "200", description = "Задача найдена")
@ApiResponse(responseCode = "404", description = "Задача не найдена")
@GetMapping("/{id}")
public TaskDto get(@PathVariable Long id) { ... }
```

```java
public record TaskDto(
        @Schema(description = "Идентификатор", example = "42") Long id,
        @Schema(description = "Заголовок", example = "Купить кофе") String title
) {}
```

Настройка общей информации:

```java
@Bean
public OpenAPI apiInfo() {
    return new OpenAPI().info(new Info()
            .title("Task Tracker API")
            .version("1.0.0")
            .description("API управления задачами"));
}
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Обновление сломало клиентов | несовместимое изменение без версии | аддитивные изменения или новая версия (`/v2`) |
| Браузерный фронт получает CORS-ошибку | источник не разрешён | настроить CORS для нужного origin |
| `allowedOrigins("*")` + куки/токены | небезопасно | перечислить конкретные origin |
| Swagger UI пуст | нет зависимости springdoc / контроллеры вне скана | добавить starter, проверить пакеты |
| Версии плодятся бесконтрольно | новая версия на каждое мелкое изменение | версионировать только при несовместимых изменениях |
| Документация расходится с кодом | ручная документация | генерировать из кода (springdoc) |

---

## Дополнительные источники

- [springdoc-openapi](https://springdoc.org/).
- [OpenAPI Specification](https://swagger.io/specification/).
- [Spring CORS](https://docs.spring.io/spring-framework/reference/web/webmvc-cors.html).
- [модуль 65](../m65_spring_boot_web_config/theory.md) — `@ConfigurationProperties`.

## Что дальше

В [модуле 76](../m76_spring_rest_testing/theory.md) — тестирование REST-API: `@WebMvcTest` + `MockMvc` (slice) и `@SpringBootTest` + `TestRestTemplate` (интеграция). На этом блок Spring REST (67–76) завершается.
