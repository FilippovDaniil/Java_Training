# Модуль 103. Spring Test: профили, свойства и управляемая конфигурация тестов

Unit-тесты ([модуль 102](../module-102-spring-test-unit/theory.md)) контекст не поднимают. Но как только тест поднимает Spring (`@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest` — модули 104+), встаёт вопрос: **какая конфигурация активна в тесте?** Какие свойства, какой профиль, какие бины. Этот модуль — про управление окружением теста: профили (`@ActiveProfiles`), свойства (`@TestPropertySource`, `application-test.properties`), подмена бинов (`@TestConfiguration`) и динамические свойства (`@DynamicPropertySource`).

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимость: `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **Task Tracker API**.

---

## Профили: `@ActiveProfiles`

Профиль выбирает набор бинов и файл свойств. В тесте активируют отдельный профиль `test`, чтобы не задеть `dev`/`prod`:

```java
@SpringBootTest
@ActiveProfiles("test")            // активирует профиль test на время теста
class OrderServiceTest { ... }
```

```
   application.properties           — общие свойства (всегда)
   application-test.properties      — только при активном профиле test ← перекрывает общие
   application-prod.properties      — только при prod
```

`application-test.properties` (в `src/test/resources`):

```properties
app.feature.notifications=false
spring.datasource.url=jdbc:h2:mem:testdb
logging.level.org.springframework=WARN
```

---

## Свойства теста: 4 способа

| Способ | Где задаётся | Когда применять |
|--------|--------------|-----------------|
| `application-test.properties` | файл, профиль `test` | общий набор для всех тестов профиля |
| `@TestPropertySource(properties = {...})` | аннотация на классе | точечное переопределение для класса тестов |
| `@TestPropertySource(locations = "...")` | отдельный файл | специфичный файл свойств |
| `@SpringBootTest(properties = {...})` | аннотация | инлайн-переопределение при старте |
| `@DynamicPropertySource` | static-метод | значение известно только в рантайме (порт, URL контейнера) |

### `@TestPropertySource` — переопределение

```java
@SpringBootTest
@TestPropertySource(properties = {
    "app.max-tasks=3",
    "app.feature.notifications=false"
})
class LimitsTest {
    @Value("${app.max-tasks}") int maxTasks;     // = 3 в этом тесте
}
```

### Инлайн через `@SpringBootTest(properties = ...)`

```java
@SpringBootTest(properties = "app.max-tasks=5")
class AnotherTest { ... }
```

> Приоритет (упрощённо): `@DynamicPropertySource` / `@TestPropertySource` / inline-`properties` **перекрывают** файлы `application-*.properties`.

---

## `@ConfigurationProperties` в тесте

Типобезопасную конфигурацию (модуль [65](../module-65-spring-boot-web-config/theory.md)) удобно тестировать на корректность биндинга:

```java
@ConfigurationProperties(prefix = "app")
record AppProps(int maxTasks, boolean notifications) {}

@SpringBootTest
@TestPropertySource(properties = {"app.max-tasks=7", "app.notifications=true"})
@EnableConfigurationProperties(AppProps.class)
class AppPropsTest {
    @Autowired AppProps props;

    @Test
    void binds() {
        assertThat(props.maxTasks()).isEqualTo(7);
        assertThat(props.notifications()).isTrue();
    }
}
```

---

## `@TestConfiguration`: подмена бинов

Когда в тесте нужен **другой** бин (заглушка вместо реального: фиксированные часы, фейковый шлюз), его объявляют в `@TestConfiguration`:

```java
@TestConfiguration
class TestClockConfig {
    @Bean
    Clock fixedClock() {                                  // вместо системных часов — фиксированные
        return Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    }
}

@SpringBootTest
@Import(TestClockConfig.class)        // подключить тестовый бин
class ReportServiceTest {
    @Autowired Clock clock;           // получит fixedClock
}
```

| Подмена | Инструмент |
|---------|------------|
| Заменить бин на заглушку/фиксированный | `@TestConfiguration` + `@Import` |
| Заменить бин моком в slice/контексте | `@MockBean` (Mockito-мок в контексте) — модуль [104](../module-104-spring-test-webmvc/theory.md) |

> `@TestConfiguration` (в отличие от `@Configuration`) **не подхватывается** автосканированием — подключается явно через `@Import`, поэтому не «протекает» в другие тесты.

---

## `@DynamicPropertySource`: значения из рантайма

Когда свойство известно только при старте (порт встроенного брокера, URL Testcontainers — модуль [109](../module-109-spring-test-integration/theory.md)):

```java
@DynamicPropertySource
static void props(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);   // ленивый поставщик
    registry.add("spring.datasource.username", container::getUsername);
}
```

`add(name, supplier)` принимает **поставщик** (`Supplier`), значение вычисляется лениво — к моменту, когда контекст реально читает свойство.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `application-test.properties` не подхватился | не активен профиль `test` | `@ActiveProfiles("test")` |
| Файл свойств не там | положили в `src/main/resources` | тестовые свойства — в `src/test/resources` |
| `@TestConfiguration` не применилась | не импортировали | `@Import(TestConfig.class)` (она не автосканируется) |
| Свойство не переопределилось | перебивается файлом с бо́льшим приоритетом | `@TestPropertySource`/inline перекрывают файлы |
| `@DynamicPropertySource` не сработал | метод не `static` | сделать `static void ...(DynamicPropertyRegistry)` |
| Профиль `test` «течёт» в прод | активировали глобально | `@ActiveProfiles` — только в тестах, не в `application.properties` |
| `@Value` = `${...}` (литерал) | свойство не определено | задать в test-свойствах или дефолт `${app.x:def}` |

---

## Дополнительные источники

- [Spring Boot: Testing — Properties & Configuration](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html).
- [`@TestPropertySource` — Spring Framework docs](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-testpropertysource.html).
- [`@DynamicPropertySource`](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-dynamicpropertysource.html).
- [модуль 65](../module-65-spring-boot-web-config/theory.md) — `@ConfigurationProperties` и профили.

## Что дальше

В [модуле 104](../module-104-spring-test-webmvc/theory.md) — **тестирование веб-слоя**: `@WebMvcTest`, `MockMvc`, новый текучий `MockMvcTester`, `@MockBean` сервиса, JSON-проверки (`jsonPath`), `@JsonTest` с `JacksonTester`. Это первый «срезовый» тест, поднимающий часть контекста — и здесь конфигурация из этого модуля начинает работать на практике.
