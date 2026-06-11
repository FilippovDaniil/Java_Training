# Модуль 107. Spring Test: связи сущностей, lazy loading и конкурентность

Репозитории проверять умеем ([модуль 106](../module-106-spring-test-datajpa/theory.md)). Но самые коварные баги JPA — в **связях**: lazy loading и `LazyInitializationException`, N+1, каскады, оптимистическая блокировка. Эти эффекты воспроизводимы тестом — и именно тест ловит их до прода. В этом модуле — как тестировать ленивую загрузку, `JOIN FETCH`, каскады, `@Version` и сидинг данных через `@Sql`/Flyway.

> Практика — задачи в `practice/`. **Тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`, `spring-boot-starter-test`. bare-javac не верифицируется (норма). Сквозной проект — **shop-data-jpa** (Category 1—N Product).

---

## Lazy loading в тесте: внутри транзакции работает

`@DataJpaTest` оборачивает каждый тест в транзакцию, поэтому persistence context **открыт** на всё время теста — и обращение к lazy-связи работает:

```java
@DataJpaTest
class CategoryTest {
    @Autowired TestEntityManager em;
    @Autowired CategoryRepository repo;

    @Test
    void lazy_access_inside_tx_ok() {
        Category c = em.persistAndFlush(categoryWithProducts());
        em.clear();

        Category loaded = repo.findById(c.getId()).orElseThrow();
        assertThat(loaded.getProducts()).hasSize(2);   // lazy-инициализация: контекст открыт → ОК
    }
}
```

---

## `LazyInitializationException`: когда контекст закрыт

Если обратиться к lazy-связи **после** закрытия контекста (детач), Hibernate бросит `LazyInitializationException`. Это воспроизводят `em.detach(...)`:

```java
@Test
void lazy_access_after_detach_fails() {
    Category c = em.persistAndFlush(categoryWithProducts());
    em.clear();
    Category loaded = repo.findById(c.getId()).orElseThrow();
    em.detach(loaded);                                  // отсоединяем от контекста

    assertThatThrownBy(() -> loaded.getProducts().size())
            .isInstanceOf(LazyInitializationException.class);   // прокси не может догрузиться
}
```

```
   managed (в контексте):  loaded.getProducts() → SELECT → ОК
   detached (вне):         loaded.getProducts() → LazyInitializationException
```

> На проде это «вылезает» при сериализации сущности в JSON вне транзакции (модули [82](../module-82-spring-data-jpa-lazy-loading/theory.md), [86](../module-86-hibernate-deep-dive-fetching/theory.md)). Тест фиксирует ожидаемое поведение.

---

## `JOIN FETCH` / `@EntityGraph`: проверяем инициализацию

Запрос с `JOIN FETCH` загружает связь сразу — её можно трогать даже после детача:

```java
// репозиторий: @Query("select c from Category c join fetch c.products where c.id = :id")
//              Optional<Category> findByIdWithProducts(@Param("id") Long id);

@Test
void join_fetch_initializes_collection() {
    Category c = em.persistAndFlush(categoryWithProducts());
    em.clear();

    Category loaded = repo.findByIdWithProducts(c.getId()).orElseThrow();
    em.detach(loaded);                                            // даже после детача...

    assertThat(Hibernate.isInitialized(loaded.getProducts())).isTrue();   // ...коллекция уже загружена
    assertThat(loaded.getProducts()).hasSize(2);                 // без LazyInitializationException
}
```

`Hibernate.isInitialized(collection)` — проверка, инициализирована ли связь.

---

## Каскады и `orphanRemoval`

```java
@Test
void cascade_persists_children() {
    Category c = new Category("Напитки");
    c.addProduct(new Product("Кофе", 100));      // cascade = PERSIST
    c.addProduct(new Product("Чай", 50));

    em.persistAndFlush(c);                         // сохранится и категория, и товары
    em.clear();

    assertThat(productRepo.count()).isEqualTo(2);
}

@Test
void orphan_removal_deletes_child() {
    Category c = em.persistAndFlush(categoryWithProducts());   // 2 товара
    c.getProducts().remove(0);                                  // убрали из коллекции
    em.persistAndFlush(c);                                      // orphanRemoval=true → DELETE
    em.clear();

    assertThat(productRepo.count()).isEqualTo(1);
}
```

---

## Оптимистическая блокировка: `@Version`

Конкурентное обновление воспроизводят двумя «снимками» одной сущности. Версия устаревает → `OptimisticLockException` (модуль [90](../module-90-hibernate-deep-dive-locking/theory.md)):

```java
@Test
void optimistic_lock_conflict() {
    Product p = em.persistAndFlush(new Product("Кофе", 100));   // version = 0
    Long id = p.getId();
    em.clear();

    // два независимых чтения «одновременно»
    Product first  = repo.findById(id).orElseThrow();           // version 0
    Product second = repo.findById(id).orElseThrow();           // version 0

    first.setPrice(120);
    repo.saveAndFlush(first);                                   // version → 1, коммит ок

    second.setPrice(150);                                        // у second всё ещё version 0
    assertThatThrownBy(() -> repo.saveAndFlush(second))
            .isInstanceOf(OptimisticLockingFailureException.class);   // конфликт версий
}
```

> `OptimisticLockingFailureException` — спринговая обёртка над Hibernate `StaleObjectStateException`. На проде её ловят и делают re-read + retry.

---

## Сидинг данных: `@Sql` и Flyway

```java
@Test
@Sql("/seed-catalog.sql")                  // выполнить SQL перед тестом
void counts_seeded() {
    assertThat(productRepo.count()).isEqualTo(10);
}
```

Если в проекте Flyway (модуль [84](../module-84-spring-data-jpa-migrations/theory.md)), миграции применяются и в тесте (схема + опорные данные). Для тестовых данных используют либо `@Sql`, либо отдельный набор Flyway-скриптов в `src/test/resources`.

| Инструмент | Когда |
|------------|-------|
| `@Sql("/script.sql")` | разовый сидинг для теста/класса |
| Flyway (`V*.sql`) | схема и общие данные, как в проде |
| `TestEntityManager.persist` | точечная подготовка в коде теста |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Lazy работает в тесте, падает на проде | в `@DataJpaTest` контекст открыт всю транзакцию | воспроизвести детачем (`em.detach`) или тестом сериализации |
| N+1 не виден в тесте | мало данных / не считают запросы | проверять `JOIN FETCH`/`@EntityGraph`; считать запросы (модуль 92) |
| Каскад не сработал | нет `cascade=...` на связи | задать каскад; проверить тестом `count()` |
| `@Version` не растёт | поле версии не объявлено `@Version` | добавить `@Version`; тест конфликта |
| `OptimisticLock`-тест не падает | оба сохранения в одном контексте | два независимых чтения + `saveAndFlush` |
| `@Sql` не нашёл файл | неверный путь | путь от корня classpath: `/seed-catalog.sql` |

---

## Дополнительные источники

- [Spring Framework: `@Sql`](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-sql.html).
- [Hibernate: Lazy initialization & `Hibernate.isInitialized`](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#fetching).
- [модуль 82](../module-82-spring-data-jpa-lazy-loading/theory.md) — lazy loading и N+1.
- [модуль 90](../module-90-hibernate-deep-dive-locking/theory.md) — оптимистическая блокировка.

## Что дальше

В [модуле 108](../module-108-spring-test-full-context/theory.md) — **тесты в полном контексте**: `@SpringBootTest`, реальный веб-сервер (`RANDOM_PORT`), `MockMvc` поверх всего приложения, `TestRestTemplate`/`RestTestClient`. Поднимаемся с уровня срезов к интеграции всего стека.
