# Модуль 83. Тестирование слоя данных: @DataJpaTest, H2, Testcontainers

Репозитории и запросы ([модули 79–82](../m79_spring_data_jpa_repository/theory.md)) тоже нужно **тестировать**: derived queries, JPQL, маппинг, отсутствие N+1. Spring Boot даёт срез `@DataJpaTest` — быстрый изолированный тест слоя persistence на in-memory H2.

> Практика — задачи в `practice/`. Это **тест-классы без `main`** (стиль [модуля 34](../m34_testing_junit_mockito/theory.md)): методы `@Test` с пустым телом/`// TODO`, запуск в IDE / Gradle. Зависимости: `spring-boot-starter-data-jpa`, `spring-boot-starter-test`, `com.h2database:h2`.

---

## Что такое @DataJpaTest

Тестовый **срез** (slice): поднимает не всё приложение, а только слой данных.

```java
@DataJpaTest
class ProductRepositoryTest {
    @Autowired ProductRepository repo;
    @Autowired TestEntityManager em;

    @Test
    void savesAndFinds() {
        Product saved = repo.save(new Product("Молоко"));
        assertThat(repo.findById(saved.getId())).isPresent();
    }
}
```

Что делает `@DataJpaTest`:

| Поведение | Деталь |
|-----------|--------|
| Поднимает JPA-инфраструктуру | `@Entity`, `EntityManager`, репозитории Spring Data |
| Подменяет БД на встроенную | по умолчанию ищет **H2/HSQLDB/Derby** в classpath |
| Оборачивает каждый тест в транзакцию | по завершении — **rollback** (тесты не пачкают друг друга) |
| НЕ грузит | `@Service`, `@RestController`, web-слой |

> Из-за авто-rollback каждый `@Test` стартует с чистой БД. Состояние одного теста не «протекает» в другой.

---

## TestEntityManager

Тонкая обёртка над `EntityManager` для подготовки данных в тесте:

```java
@Autowired TestEntityManager em;

@Test
void findsByName() {
    em.persist(new Product("Хлеб"));
    em.persist(new Product("Хлебцы"));
    em.flush();      // отправить INSERT'ы в БД
    em.clear();      // очистить persistence context — заставить читать из БД

    List<Product> found = repo.findByNameContaining("Хлеб");
    assertThat(found).hasSize(2);
}
```

| Метод | Зачем |
|-------|-------|
| `persist(e)` | сохранить тестовую сущность |
| `flush()` | синхронизировать контекст с БД (выполнить SQL немедленно) |
| `clear()` | очистить кэш 1-го уровня → следующее чтение пойдёт в БД |
| `find(Class, id)` | прочитать напрямую через EntityManager |

> **Почему `flush()` + `clear()` важны:** без них репозиторий может вернуть объект из кэша persistence context, **не выполнив** реальный SQL. Тогда тест запроса/маппинга ничего по-настоящему не проверит. `clear()` гарантирует «холодное» чтение из БД.

---

## Проверки: AssertJ

`spring-boot-starter-test` приносит **AssertJ** — fluent-ассерты:

```java
import static org.assertj.core.api.Assertions.assertThat;

assertThat(repo.count()).isEqualTo(2);
assertThat(repo.findById(99L)).isEmpty();
assertThat(product.getName()).isEqualTo("Хлеб").startsWith("Хл");
assertThat(list).hasSize(3).extracting(Product::getName).contains("Молоко");
```

---

## Подготовка данных через @Sql

Можно засеять схему/данные SQL-скриптом:

```java
@Test
@Sql("/test-data/products.sql")     // выполнится перед тестом
void countsSeeded() {
    assertThat(repo.count()).isEqualTo(10);
}
```

Скрипт лежит в `src/test/resources`. Удобно для крупных наборов.

---

## H2 vs реальная БД: ограничение среза

H2 — быстро и без Docker, но это **другая СУБД**. Её диалект отличается от PostgreSQL/MySQL: типы, функции, точное поведение блокировок, native SQL. Тест на H2 может пройти, а на проде (Postgres) — упасть.

```
   @DataJpaTest + H2          Testcontainers + реальный Postgres
   ──────────────────         ─────────────────────────────────
   ⚡ быстро, без Docker        🐢 медленнее (поднимает контейнер)
   ⚠ другой диалект            ✅ та же СУБД, что в проде
   ✅ для derived/JPQL          ✅ для native SQL, миграций, блокировок
```

### Обзор Testcontainers

[Testcontainers](https://testcontainers.com/) запускает реальную БД в Docker на время теста:

```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // НЕ подменять на H2
@Testcontainers
class ProductRepositoryPgTest {

    @Container
    @ServiceConnection                       // Spring Boot 3.1+: сам пропишет url/user/pass
    static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:16");

    @Autowired ProductRepository repo;
    // ... те же тесты, но на настоящем Postgres
}
```

Ключевые моменты:
- `@AutoConfigureTestDatabase(replace = NONE)` — отменяет авто-подмену на H2 (иначе контейнер не используется).
- `@ServiceConnection` (Boot 3.1+) — автоматически связывает контейнер с `DataSource` без ручного `@DynamicPropertySource`.
- Нужен запущенный Docker.

> Стратегия: быстрые тесты репозиториев — на H2 (`@DataJpaTest`); критичные к диалекту (native SQL, миграции, блокировки) — на Testcontainers.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест запроса «проходит», но запрос не выполнялся | объект взят из кэша контекста | `em.flush(); em.clear();` перед чтением |
| Тесты влияют друг на друга | нет отката | `@DataJpaTest` откатывает сам; не отключайте транзакцию без нужды |
| Testcontainers-тест всё равно идёт на H2 | не отключена авто-подмена | `@AutoConfigureTestDatabase(replace = NONE)` |
| `@DataJpaTest` не видит `@Service` | срез грузит только слой данных | тестировать сервисы отдельно (`@SpringBootTest` / мок-репозиторий) |
| Тест на H2 зелёный, прод (Postgres) красный | разница диалектов | критичное проверять на Testcontainers |
| `LazyInitializationException` в тесте | доступ к lazy вне транзакции теста | тест транзакционный по умолчанию; грузить через fetch/в тесте |
| Падает на `@Sql`-скрипте | путь/синтаксис под H2 | путь от classpath (`/...`), совместимый SQL |

---

## Дополнительные источники

- [Spring Boot: Testing — @DataJpaTest](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.autoconfigured-spring-data-jpa).
- [Testcontainers for Java](https://java.testcontainers.org/modules/databases/).
- [модуль 76](../m76_spring_rest_testing/theory.md) — тестирование REST-слоя (`@WebMvcTest`, MockMvc).
- [модуль 92](../m92_hibernate_deep_dive_diagnostics/theory.md) — тестирование числа SQL-запросов (ловля N+1 в тестах).

## Что дальше

В [модуле 84](../m84_spring_data_jpa_migrations/theory.md) — управление схемой в продакшене: миграции **Flyway**, оптимистичная блокировка `@Version` и **аудит** (`@CreatedDate`/`@LastModifiedDate`). Это закрывает блок Spring Data JPA (77–84).
