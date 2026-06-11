# Модуль 108. Spring Test: тесты в полном контексте (@SpringBootTest)

Срезы (`@WebMvcTest`, `@DataJpaTest`) проверяют слой в изоляции, заменяя соседей моками. Но иногда нужно проверить **всё приложение целиком**: контроллер → сервис → репозиторий → БД, с реальными бинами. Для этого — `@SpringBootTest`: он поднимает полный Spring-контекст. В этом модуле — режимы веб-окружения, `MockMvc` и `TestRestTemplate`/`RestTestClient` поверх живого приложения, и когда что выбирать.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `com.h2database:h2`, `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## `@SpringBootTest`: полный контекст

```
   @WebMvcTest / @DataJpaTest   →  СРЕЗ: один слой, соседи замоканы (быстро)
   @SpringBootTest              →  ВЕСЬ контекст: реальные бины всех слоёв (медленнее, честнее)
```

```java
@SpringBootTest                                // поднять всё приложение
class TaskFlowTest {
    @Autowired TaskService service;            // реальный сервис
    @Autowired TaskRepository repository;       // реальный репозиторий + H2

    @Test
    void create_then_find() {
        Long id = service.create("Кофе");
        assertThat(repository.findById(id)).isPresent();   // прошли весь стек
    }
}
```

---

## Режимы веб-окружения (`webEnvironment`)

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)          // по умолчанию
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)   // реальный сервер, случайный порт
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // реальный сервер, порт из конфига
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)          // без веб-окружения
```

| Режим | Сервер | Чем тестировать |
|-------|--------|-----------------|
| `MOCK` (по умолчанию) | нет (mock-сервлет-окружение) | `MockMvc` (+ `@AutoConfigureMockMvc`) |
| `RANDOM_PORT` | реальный, случайный порт | `TestRestTemplate` / `RestTestClient` (реальный HTTP) |
| `DEFINED_PORT` | реальный, фикс. порт | то же (редко — конфликты портов) |
| `NONE` | нет | сервисы/репозитории напрямую |

---

## `MOCK` + `MockMvc`: полный контекст без сети

```java
@SpringBootTest                              // webEnvironment = MOCK
@AutoConfigureMockMvc                         // включить MockMvc в полном контексте
class TaskMockMvcTest {
    @Autowired MockMvc mockMvc;

    @Test
    void list_endpoint() throws Exception {
        mockMvc.perform(get("/api/tasks"))
               .andExpect(status().isOk());
    }
}
```

> Отличие от `@WebMvcTest`: здесь **реальные** сервисы и репозитории (не моки). Тест проходит через весь стек, но без сетевого слоя (быстрее `RANDOM_PORT`).

---

## `RANDOM_PORT` + `TestRestTemplate`: настоящий HTTP

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskHttpTest {
    @Autowired TestRestTemplate restTemplate;     // авто-настроен на нужный порт

    @LocalServerPort int port;                     // случайный порт (если нужен явно)

    @Test
    void get_over_http() {
        ResponseEntity<String> resp = restTemplate.getForEntity("/api/tasks", String.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void post_creates() {
        ResponseEntity<TaskDto> resp = restTemplate.postForEntity(
                "/api/tasks", new CreateTask("Кофе"), TaskDto.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody().title()).isEqualTo("Кофе");
    }
}
```

`TestRestTemplate` сам подставляет базовый URL (`http://localhost:<random>`), относительный путь `/api/tasks` достаточно.

---

## `RestTestClient` / `RestClient`: современный клиент

В новых версиях для HTTP-тестов используют текучий `RestTestClient` (поверх `RestClient`):

```java
@Autowired RestTestClient client;   // (Spring 6.2+) либо строится из RestClient на порт

client.get().uri("/api/tasks")
      .exchange()
      .expectStatus().isOk()
      .expectBody().jsonPath("$[0].title").isEqualTo("Кофе");
```

> Стиль аналогичен `WebTestClient` (из WebFlux), но для обычного MVC. В курсе показываем `TestRestTemplate` (повсеместен) и упоминаем `RestTestClient` (актуальный).

---

## `@DirtiesContext`: пересоздать контекст

Полный контекст переиспользуется между тестовыми классами (кэшируется — это ускоряет прогон). Если тест «портит» контекст (меняет бин, состояние), его помечают:

```java
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)   // пересоздать контекст после класса
class StatefulTest { ... }
```

> Использовать **точечно**: каждый сброс контекста замедляет сборку. Лучше писать тесты, не портящие состояние (или откатывать через `@Transactional`).

---

## Когда что выбирать

| Цель | Инструмент |
|------|------------|
| Логика одного класса | unit-тест (модуль 102) |
| Контроллер изолированно | `@WebMvcTest` (модуль 104) |
| Репозиторий изолированно | `@DataJpaTest` (модуль 106) |
| Весь стек без сети | `@SpringBootTest` + `MockMvc` |
| Весь стек по HTTP | `@SpringBootTest(RANDOM_PORT)` + `TestRestTemplate`/`RestTestClient` |
| Реальная СУБД | + Testcontainers (модуль 109) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `MockMvc` не внедряется в `@SpringBootTest` | нет `@AutoConfigureMockMvc` | добавить аннотацию |
| `TestRestTemplate` бьёт не туда | не `RANDOM_PORT` / абсолютный URL | `webEnvironment=RANDOM_PORT`, относительный путь |
| Данные текут между тестами | полный контекст не откатывает сам | `@Transactional` на тесте или чистка |
| Сборка медленная | много `@SpringBootTest` / `@DirtiesContext` | срезы где можно; не злоупотреблять `@DirtiesContext` |
| Контекст не поднимается | не хватает бина/конфигурации | проверить, что приложение стартует; смотреть лог |
| `@MockBean` в `@SpringBootTest` ради скорости | подмена ломает «честность» интеграции | мокать только внешние границы (сторонние API) |

---

## Дополнительные источники

- [Spring Boot: `@SpringBootTest`](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html).
- [`TestRestTemplate` — Javadoc](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/web/client/TestRestTemplate.html).
- [Spring: `RestTestClient`](https://docs.spring.io/spring-framework/reference/testing/resttestclient.html).
- [модуль 76](../m76_spring_rest_testing/theory.md) — `@SpringBootTest` + `TestRestTemplate` (первое знакомство).

## Что дальше

В [модуле 109](../m109_spring_test_integration/theory.md) — **интеграционные тесты с Testcontainers**: запуск реального PostgreSQL в Docker-контейнере на время теста (`@Container`, `@ServiceConnection`), проверка на той же СУБД, что в проде. Самый верх пирамиды тестов.
