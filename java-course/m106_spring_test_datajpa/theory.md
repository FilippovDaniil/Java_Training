# Модуль 106. Spring Test: тестирование слоя данных (@DataJpaTest)

Web-слой проверяли срезом `@WebMvcTest` ([модуль 104](../m104_spring_test_webmvc/theory.md)). Для слоя **данных** есть свой срез — `@DataJpaTest`: он поднимает JPA/Hibernate, репозитории и встроенную БД (H2), но **не** веб и сервисы. Каждый тест выполняется в транзакции с **авто-откатом**, поэтому тесты не мешают друг другу. В этом модуле — `@DataJpaTest`, `TestEntityManager`, важная связка `flush`/`clear` и проверка запросов репозитория.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`, `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **shop-data-jpa** (товары/категории, как в [модулях 77–92](../m77_spring_data_jpa_intro/theory.md)).

---

## `@DataJpaTest`: что внутри среза

```
   @DataJpaTest
   +----------------------------------------------+
   | ЗАГРУЖАЕТСЯ:  @Entity, репозитории (JpaRepository),
   |              EntityManager, TestEntityManager, встроенная H2
   | НЕ загружается: @Service, @Controller, веб
   | КАЖДЫЙ ТЕСТ:  в транзакции → авто-ROLLBACK после теста
   +----------------------------------------------+
```

```java
@DataJpaTest
class ProductRepositoryTest {

    @Autowired ProductRepository repository;
    @Autowired TestEntityManager em;       // помощник для подготовки данных

    @Test
    void saves_and_finds() {
        Product saved = repository.save(new Product("Кофе", 100));
        assertThat(repository.findById(saved.getId())).isPresent();
    }
}
```

> Авто-откат означает: данные, записанные тестом, **исчезают** после него. По умолчанию `@DataJpaTest` подменяет реальную БД на встроенную (H2) — даже если в проекте PostgreSQL.

---

## `TestEntityManager`: подготовка данных

Обёртка над `EntityManager` для тестов — короче и нагляднее:

```java
@Autowired TestEntityManager em;

Product p = em.persistAndFlush(new Product("Кофе", 100));   // сохранить + flush (получить id)
em.persist(new Product("Чай", 50));                          // без flush
Product found = em.find(Product.class, p.getId());           // найти по id
em.flush();                                                  // отправить SQL в БД
em.clear();                                                  // очистить persistence context (кэш 1 уровня)
```

| Метод | Назначение |
|-------|------------|
| `persist(e)` | сохранить (в контексте) |
| `persistAndFlush(e)` | сохранить и сразу выполнить SQL (получить id) |
| `find(Class, id)` | загрузить по id |
| `flush()` | синхронизировать контекст с БД (SQL) |
| `clear()` | очистить контекст — следующее чтение пойдёт в БД |

---

## Зачем `flush` + `clear`: «холодное» чтение

Persistence context — это **кэш первого уровня**. Если сохранить сущность и тут же её прочитать, Hibernate вернёт **тот же объект из памяти**, не обращаясь к БД. Это маскирует ошибки маппинга. Чтобы проверить, что данные действительно корректно **записались и читаются из БД**, контекст очищают:

```java
@Test
void reads_from_db_not_cache() {
    Product p = em.persistAndFlush(new Product("Кофе", 100));
    em.clear();                                  // ← выбрасываем объект из кэша 1 уровня

    Product fromDb = repository.findById(p.getId()).orElseThrow();  // настоящий SELECT
    assertThat(fromDb.getName()).isEqualTo("Кофе");
    assertThat(fromDb).isNotSameAs(p);           // другой экземпляр — реально из БД
}
```

```
   persistAndFlush → INSERT, объект в кэше
   БЕЗ clear():  findById → тот же объект из кэша (БД не трогается)  ← маскирует баги маппинга
   С clear():    findById → SELECT из БД → новый объект              ← честная проверка
```

---

## Тестирование запросов репозитория

### Derived query

```java
@Test
void finds_by_name_containing() {
    em.persist(new Product("Кофе арабика", 100));
    em.persist(new Product("Чай зелёный", 50));
    em.flush();

    List<Product> found = repository.findByNameContainingIgnoreCase("кофе");
    assertThat(found).hasSize(1).extracting(Product::getName).containsExactly("Кофе арабика");
}
```

### `@Query` (JPQL)

```java
// в репозитории: @Query("select p from Product p where p.price > :min")
//                List<Product> findExpensive(@Param("min") int min);

@Test
void custom_query_filters_by_price() {
    em.persist(new Product("Дешёвый", 10));
    em.persist(new Product("Дорогой", 1000));
    em.flush();

    assertThat(repository.findExpensive(100)).extracting(Product::getName).containsExactly("Дорогой");
}
```

---

## Реальная БД вместо H2: `@AutoConfigureTestDatabase`

Иногда тестировать на той же СУБД, что в проде (особенности диалекта). Тогда отключают подмену БД:

```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)   // не подменять на H2
class ProductRepositoryPgTest { ... }      // использует реальный datasource (часто + Testcontainers, модуль 109)
```

| Значение `replace` | Поведение |
|--------------------|-----------|
| `ANY` (по умолчанию) | подменить на встроенную H2 |
| `NONE` | использовать настроенный datasource (реальную БД) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест видит данные другого теста | нет изоляции (но `@DataJpaTest` откатывает) | не отключать авто-откат без нужды |
| Чтение возвращает «слишком правильный» объект | читается из кэша 1 уровня, не из БД | `em.flush(); em.clear();` перед чтением |
| `id == null` после persist | не было `flush` (для `IDENTITY` id ставится при INSERT) | `persistAndFlush` |
| `@DataJpaTest` тащит сервисы | сервис помечен `@Component`, попал в скан | срез грузит только JPA — выделить сервис из data-слоя |
| Тест на H2 проходит, на проде падает | различия диалектов | `@AutoConfigureTestDatabase(replace=NONE)` + Testcontainers (модуль 109) |
| `LazyInitializationException` в тесте | доступ к lazy вне транзакции | в `@DataJpaTest` тест транзакционный (lazy работает); подробнее — модуль [107](../m107_spring_test_entity_relationships/theory.md) |

---

## Дополнительные источники

- [Spring Boot: Testing — `@DataJpaTest`](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.autoconfigured-spring-data-jpa).
- [`TestEntityManager` — Javadoc](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/autoconfigure/orm/jpa/TestEntityManager.html).
- [модуль 83](../m83_spring_data_jpa_testing/theory.md) — первое знакомство с `@DataJpaTest`.
- [модуль 85](../m85_hibernate_deep_dive_lifecycle/theory.md) — persistence context и кэш 1 уровня.

## Что дальше

В [модуле 107](../m107_spring_test_entity_relationships/theory.md) — **тестирование связей и конкурентности**: lazy loading и `LazyInitializationException`, проверка `JOIN FETCH`/N+1, каскады, оптимистическая блокировка (`@Version`) и сидинг данных через `@Sql`/Flyway. Углубляемся в data-тесты.
