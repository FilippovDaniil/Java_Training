# Модуль 110. Spring Test: security в полном контексте, async, внешние интеграции, REST Docs

Завершаем блок тестирования. Здесь — четыре «продвинутых» темы, нужные production-приложению: **security-тесты в полном контексте** (`@SpringBootTest` + `spring-security-test`), тестирование **асинхронного** кода (`@Async`/`CompletableFuture`), изоляция от **внешних интеграций** (мок границы) и документирование API через **Spring REST Docs**. После него — переход к Docker (модули 111–118).

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-web`/`security`/`test`, **`spring-security-test`**; для документации — **`spring-restdocs-mockmvc`**; для async — стандартные средства. bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## Security в полном контексте

В отличие от среза `@WebMvcTest` (модуль [100](../m100_spring_security_testing/theory.md)), здесь поднимается всё приложение — security-цепочка реальная, с реальными правилами:

```java
@SpringBootTest
@AutoConfigureMockMvc                    // MockMvc в полном контексте
class SecurityIntegrationTest {
    @Autowired MockMvc mockMvc;

    @Test
    void anonymous_401() throws Exception {
        mockMvc.perform(get("/api/tasks")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    void user_200() throws Exception {
        mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void user_forbidden_on_admin() throws Exception {
        mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());
    }
}
```

Те же инструменты, что в модуле 100 (`@WithMockUser`, post-processors `httpBasic()`/`jwt()`/`csrf()`), но проверяется **интеграция** security со всем стеком, а не срез.

---

## Тестирование `@Async`

Асинхронный метод (`@Async` возвращает `CompletableFuture`, модуль [25](../m25_multithreading_concurrency/theory.md)) выполняется в другом потоке — тест должен **дождаться** результата:

```java
@SpringBootTest
class AsyncServiceTest {
    @Autowired ReportService service;        // метод помечен @Async, класс — @EnableAsync

    @Test
    void async_result() throws Exception {
        CompletableFuture<String> future = service.generateReport();

        String result = future.get(2, TimeUnit.SECONDS);   // ждём с таймаутом
        assertThat(result).isEqualTo("READY");
    }
}
```

Для проверки **побочного эффекта** асинхронной задачи (что-то записалось «когда-нибудь потом») удобен Awaitility:

```java
import static org.awaitility.Awaitility.await;

service.processAsync(id);                                  // запустили, метод вернулся сразу
await().atMost(2, TimeUnit.SECONDS)
       .untilAsserted(() -> assertThat(repo.findById(id).get().isProcessed()).isTrue());
```

| Случай | Приём |
|--------|-------|
| async возвращает `CompletableFuture` | `future.get(timeout)` |
| async с побочным эффектом | `await().atMost(...).untilAsserted(...)` (Awaitility) |
| нужно детерминированно | в unit-тесте вызвать логику синхронно (без `@Async`) |

---

## Изоляция от внешних интеграций

Тест **не** должен ходить в реальный платёжный шлюз / стороннее API. Границу мокают:

```java
@SpringBootTest
@AutoConfigureMockMvc
class OrderIntegrationTest {
    @Autowired MockMvc mockMvc;
    @MockBean PaymentGateway paymentGateway;     // внешняя граница — мок

    @Test
    @WithMockUser
    void places_order_without_real_payment() throws Exception {
        when(paymentGateway.charge(any())).thenReturn(new Receipt("OK"));

        mockMvc.perform(post("/api/orders").with(csrf())
                        .contentType(APPLICATION_JSON).content("{\"amount\":100}"))
               .andExpect(status().isCreated());

        verify(paymentGateway).charge(any());     // граница вызвана, но реальный платёж не ушёл
    }
}
```

> Правило: в интеграционном тесте мокают **только внешние границы** (сторонние сервисы, почта, платежи) — внутренние слои оставляют настоящими. Для имитации HTTP-сервиса целиком применяют WireMock (поднимает фейковый HTTP-эндпоинт) — это та же идея на уровне сети.

---

## Документирование API: Spring REST Docs

REST Docs генерирует **документацию из тестов**: тест проходит → создаются сниппеты (curl, запрос, ответ, поля), которые вставляют в `.adoc`/`.md`. Документация всегда соответствует коду (тест бы упал).

```java
@WebMvcTest(TaskController.class)
@AutoConfigureRestDocs                                    // включить REST Docs
class TaskDocsTest {
    @Autowired MockMvc mockMvc;
    @MockBean TaskService service;

    @Test
    void documents_get_task() throws Exception {
        when(service.find(1L)).thenReturn(new TaskDto(1L, "Кофе", "NEW"));

        mockMvc.perform(get("/api/tasks/1"))
               .andExpect(status().isOk())
               .andDo(document("get-task",                // ← сниппеты в build/.../snippets/get-task/
                       responseFields(
                           fieldWithPath("id").description("Идентификатор"),
                           fieldWithPath("title").description("Заголовок"),
                           fieldWithPath("status").description("Статус"))));
    }
}
```

| Что даёт REST Docs | |
|--------------------|--|
| источник документации | проходящий тест (не отдельный документ) |
| сниппеты | curl, http-request, http-response, request/response-fields |
| гарантия | документация не «протухнет» — рассинхрон ломает тест |

> Отличие от OpenAPI/springdoc (модуль [75](../m75_spring_rest_config_openapi/theory.md)): springdoc генерирует спеку из аннотаций/кода, REST Docs — из **тестов** (точнее отражает реальное поведение, но требует писать тесты).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `MockMvc` нет в `@SpringBootTest` | не добавили `@AutoConfigureMockMvc` | добавить аннотацию |
| async-тест «не видит» результат | не дождались потока | `future.get(timeout)` или Awaitility |
| Тест ходит в реальное API | не замокана граница | `@MockBean` на внешнем клиенте (или WireMock) |
| `@Async` в unit-тесте не асинхронен | контекст не поднят/`@EnableAsync` нет | для интеграции — `@SpringBootTest`; для логики — синхронный вызов |
| REST Docs не генерит сниппеты | нет `@AutoConfigureRestDocs`/`document(...)` | добавить аннотацию и `andDo(document(...))` |
| Security-тест проходит, прод дырявый | проверяли срез, а не интеграцию | `@SpringBootTest` + реальные правила |
| Flaky async-тест | фиксированный `Thread.sleep` | заменить на Awaitility (опрос условия) |

---

## Дополнительные источники

- [Spring Security: Testing](https://docs.spring.io/spring-security/reference/servlet/test/index.html).
- [Spring: Testing `@Async`](https://docs.spring.io/spring-framework/reference/integration/scheduling.html) + [Awaitility](https://github.com/awaitility/awaitility).
- [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/).
- [модуль 100](../m100_spring_security_testing/theory.md) — security-тесты в срезе `@WebMvcTest`.

## Что дальше

**Блок Spring Test (101–110) завершён**: от инструментов и unit-тестов до Testcontainers, security, async и REST Docs — полное покрытие пирамиды. Дальше — **Docker (модули 111–118)**: контейнеризация приложения, Dockerfile, образы Spring Boot, Compose и production-ready упаковка. Начинаем с [модуля 111](../m111_docker_basics/theory.md) — основы контейнеров.
