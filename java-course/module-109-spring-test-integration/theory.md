# Модуль 109. Spring Test: интеграционные тесты с Testcontainers

`@DataJpaTest` по умолчанию тестирует на встроенной H2. Но H2 — **не** PostgreSQL: разный SQL-диалект, типы, поведение. Тест на H2 может пройти, а прод на PostgreSQL — упасть. Решение — **Testcontainers**: библиотека поднимает настоящий PostgreSQL в Docker-контейнере на время теста. Тесты бегут на той же СУБД, что в проде. Это вершина пирамиды: медленно, но максимально достоверно.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-data-jpa`, `spring-boot-starter-test`, **`org.testcontainers:postgresql`**, **`org.testcontainers:junit-jupiter`**, драйвер `org.postgresql:postgresql`. **Нужен запущенный Docker** (Rancher Desktop/Docker Desktop). bare-javac не верифицируется (норма). Сквозной проект — **shop-data-jpa**.

---

## Зачем настоящая СУБД

```
   H2 (встроенная)                       PostgreSQL (Testcontainers)
   ───────────────                       ───────────────────────────
   быстро, в памяти                      Docker-контейнер (старт ~секунды)
   диалект «совместимый», но НЕ Postgres настоящий диалект/типы/поведение
   маскирует диалект-специфичные баги    ловит их до прода
   для быстрых repo-тестов               для надёжной интеграции
```

---

## Классический способ: `@Testcontainers` + `@Container` + `@DynamicPropertySource`

```java
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)   // не подменять на H2!
@Testcontainers
class ProductRepositoryPgTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");        // образ СУБД

    @DynamicPropertySource                                          // прокинуть координаты в Spring
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired ProductRepository repository;

    @Test
    void runs_on_real_postgres() {
        repository.save(new Product("Кофе", 100));
        assertThat(repository.count()).isEqualTo(1);
    }
}
```

| Элемент | Роль |
|---------|------|
| `@Testcontainers` | подключает JUnit-расширение, управляет жизненным циклом контейнеров |
| `@Container` на `static` поле | поднять контейнер (static → один на класс) |
| `PostgreSQLContainer<>("postgres:16-alpine")` | образ и версия СУБД |
| `@DynamicPropertySource` | передать `jdbcUrl`/`username`/`password` в Spring (модуль [103](../module-103-spring-test-config/theory.md)) |
| `@AutoConfigureTestDatabase(replace=NONE)` | запретить подмену на H2 |

> `@Container` на **static**-поле → контейнер один на весь класс (быстрее). На **нестатическом** — новый на каждый тест (изоляция ценой скорости).

---

## Современный способ: `@ServiceConnection` (Spring Boot 3.1+)

`@ServiceConnection` избавляет от ручного `@DynamicPropertySource` — Spring сам берёт координаты из контейнера:

```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class ProductRepositoryModernTest {

    @Container
    @ServiceConnection                              // ← Spring сам настроит datasource из контейнера
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired ProductRepository repository;

    @Test
    void works() {
        repository.save(new Product("Кофе", 100));
        assertThat(repository.count()).isEqualTo(1);
    }
}
```

| Подход | Настройка datasource |
|--------|----------------------|
| `@DynamicPropertySource` | вручную: `registry.add("spring.datasource.url", ...)` |
| `@ServiceConnection` | автоматически (рекомендуется в новых проектах) |

---

## `@SpringBootTest` + Testcontainers: полная интеграция

Контейнер используют и в полном контексте — для end-to-end на реальной СУБД:

```java
@SpringBootTest
@Testcontainers
class FullIntegrationTest {
    @Container @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired ProductService service;     // реальный сервис → репозиторий → настоящий PostgreSQL

    @Test
    void end_to_end_on_postgres() {
        service.create("Кофе", 100);
        assertThat(service.count()).isEqualTo(1);
    }
}
```

---

## Жизненный цикл и переиспользование

- **static-контейнер** стартует один раз на класс, останавливается после всех тестов класса — баланс скорости/изоляции.
- Запуск контейнера занимает несколько секунд → интеграционных тестов **немного** (вершина пирамиды), основная масса — unit и срезы.
- Для ускорения локальной разработки есть reuse-режим (`withReuse(true)` + флаг в `~/.testcontainers.properties`) — контейнер переиспользуется между прогонами.

```
        ╱╲        Testcontainers (реальный PostgreSQL) — мало, медленно   ← ЭТОТ модуль
       ╱──╲       @SpringBootTest (полный контекст)
      ╱────╲      @WebMvcTest / @DataJpaTest (срезы, H2)
     ╱──────╲     unit-тесты (без Spring) — много, быстро
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест на H2 ок, на проде падает | разные диалекты | Testcontainers с тем же образом, что в проде |
| Контейнер не стартует | Docker не запущен | поднять Docker/Rancher Desktop |
| `@DataJpaTest` всё равно на H2 | не отключена подмена | `@AutoConfigureTestDatabase(replace=NONE)` |
| Свойства datasource не прокинулись | забыли `@DynamicPropertySource`/`@ServiceConnection` | один из двух способов |
| Контейнер на каждый тест (медленно) | поле `@Container` не `static` | сделать `static` |
| Долгий прогон CI | слишком много интеграционных тестов | держать их немногочисленными; основное — срезы/unit |
| `@DynamicPropertySource` не сработал | метод не `static` | сделать `static void ...(DynamicPropertyRegistry)` |

---

## Дополнительные источники

- [Testcontainers for Java — Quickstart](https://java.testcontainers.org/quickstart/junit_5_quickstart/).
- [Spring Boot: Testcontainers & `@ServiceConnection`](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html).
- [Testcontainers — PostgreSQL Module](https://java.testcontainers.org/modules/databases/postgres/).
- [модуль 83](../module-83-spring-data-jpa-testing/theory.md) — обзор Testcontainers (введение).

## Что дальше

В [модуле 110](../module-110-spring-test-security-async/theory.md) — завершение блока Spring Test: **security-тесты в полном контексте**, тестирование **асинхронного** кода (`@Async`/`CompletableFuture`), мокирование **внешних интеграций** и документирование API через **Spring REST Docs**. После него — переход к Docker (модули 111–118).
