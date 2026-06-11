# Модуль 82. Ленивая загрузка, проблема N+1, JOIN FETCH, EntityGraph

В [модуле 81](../m81_spring_data_jpa_transactions/theory.md) мы научились объединять изменения в транзакцию. Но связи между сущностями (`Category → Product`, `Order → Customer`) загружаются не сразу. Как именно Hibernate подтягивает связанные данные — и почему наивный код порождает **сотни лишних запросов** — тема этого модуля.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Проект — `shop-data-jpa`.

---

## FetchType: LAZY vs EAGER

У каждой связи есть стратегия загрузки — `fetch`:

| Связь | Значение по умолчанию |
|-------|-----------------------|
| `@OneToMany`, `@ManyToMany` | **LAZY** (по запросу) |
| `@ManyToOne`, `@OneToOne` | **EAGER** (сразу) |

```java
@Entity
class Category {
    @Id @GeneratedValue Long id;
    private String name;

    @OneToMany(mappedBy = "category")        // по умолчанию LAZY
    private List<Product> products = new ArrayList<>();
}

@Entity
class Product {
    @Id @GeneratedValue Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)       // ЯВНО переопределяем EAGER → LAZY
    private Category category;
}
```

> **Правило:** делайте **все** связи `LAZY`, в т.ч. `@ManyToOne(fetch = LAZY)`. EAGER «по умолчанию» у `@ManyToOne` — частый источник лишних запросов. Что и когда грузить — решайте в конкретном запросе (`JOIN FETCH`/`EntityGraph`), а не в маппинге.

---

## Как работает LAZY: прокси

При `LAZY` Hibernate не грузит связь сразу, а подставляет **прокси** (заглушку). Реальный SQL выполняется при **первом обращении** к данным — и только если открыт persistence context (транзакция/сессия).

```
   findById(category)         → SELECT * FROM categories WHERE id=?
        │
   category.getProducts()     → прокси ещё пуст
        │
   products.size() / iterate  → SELECT * FROM products WHERE category_id=?   ← загрузка ЗДЕСЬ
```

---

## LazyInitializationException

Если обратиться к ленивой связи **после** закрытия сессии (вне `@Transactional`, например в контроллере после возврата из сервиса) — Hibernate уже не может выполнить запрос:

```java
Category c = repo.findById(1L).orElseThrow();   // транзакция закрылась
c.getProducts().size();   // ❌ LazyInitializationException: could not initialize proxy - no Session
```

| Решение | Как |
|---------|-----|
| Обратиться к связи **внутри** транзакции | метод сервиса `@Transactional`, инициализировать до выхода |
| Загрузить связь сразу запросом | `JOIN FETCH` или `@EntityGraph` |
| Не тащить сущность в слой view | вернуть **DTO-проекцию** (связь уже «развёрнута») |

> ❌ Анти-решение — `spring.jpa.open-in-view=true` (включён по умолчанию в Boot!). Он держит сессию открытой до конца HTTP-ответа и **маскирует** проблему, порождая N+1 в слое представления. В реальных проектах его обычно **выключают** (`open-in-view=false`) и грузят данные явно.

---

## Проблема N+1

Самый частый перформанс-баг ORM. Загрузили **N** сущностей, затем в цикле трогаем их ленивую связь — получаем **1 + N** запросов.

```java
List<Category> cats = categoryRepo.findAll();           // 1 запрос: все категории (N штук)
for (Category c : cats) {
    System.out.println(c.getProducts().size());         // +1 запрос НА КАЖДУЮ категорию
}
// Итого при N=100 категорий → 1 + 100 = 101 запрос
```

```
   1 запрос   ──► [ cat1, cat2, ..., catN ]
                    │     │          │
   N запросов  ─────┴─────┴──────────┴───►  по products для каждой
   ──────────────────────────────────────
   ИТОГО: 1 + N запросов вместо одного-двух
```

Чтобы увидеть N+1 — включите лог SQL: `spring.jpa.show-sql=true` (и `spring.jpa.properties.hibernate.format_sql=true`).

---

## Решение 1: JOIN FETCH (JPQL)

Подтягиваем связь **одним** запросом через `join fetch`:

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c from Category c join fetch c.products")
    List<Category> findAllWithProducts();    // 1 запрос с JOIN — никакого N+1
}
```

- `distinct` убирает дубликаты корневой сущности (JOIN размножает строки).
- `JOIN FETCH` грузит связь немедленно, игнорируя `LAZY`.

> ⚠️ **Ловушка пагинации:** `JOIN FETCH` + `Pageable` для коллекций (`@OneToMany`) → Hibernate грузит **всё в память** и пагинирует там (warning `HHH000104: firstResult/maxResults specified with collection fetch; applying in memory`). Для коллекций сочетайте пагинацию с `@EntityGraph` либо грузите id страницей, затем подтягивайте связи.

---

## Решение 2: @EntityGraph

Декларативно указываем, какие связи подгрузить, **не переписывая JPQL** — работает поверх derived queries:

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = "products")
    List<Category> findByNameContaining(String part);   // products подтянутся одним JOIN'ом
}
```

`attributePaths` — какие связи загрузить жадно для конкретного метода. Можно несколько: `{"products", "products.supplier"}`.

| Подход | Когда |
|--------|-------|
| `JOIN FETCH` | нужен полный контроль над JPQL, сложные условия |
| `@EntityGraph` | поверх derived query, читается декларативнее |

---

## Решение 3: DTO-проекция (часто лучшее)

Если view нужны только несколько полей — **не грузите сущности вообще**. Запросите сразу DTO (см. [модуль 80](../m80_spring_data_jpa_advanced/theory.md)):

```java
public record CategorySummary(String name, long productCount) {}

@Query("""
       select new com.shop.CategorySummary(c.name, count(p))
       from Category c left join c.products p
       group by c.id, c.name
       """)
List<CategorySummary> summaries();      // 1 запрос, никаких прокси и LazyInitializationException
```

Нет управляемых сущностей → нет ленивых связей → нет N+1 и нет `LazyInitializationException`.

---

## @BatchSize: смягчение, а не лечение

Если связь всё же грузится лениво в цикле, `@BatchSize` объединяет `N` запросов в `N/size` через `IN (...)`:

```java
@OneToMany(mappedBy = "category")
@org.hibernate.annotations.BatchSize(size = 20)
private List<Product> products;
// вместо 100 запросов → 100/20 = 5 запросов IN (...)
```

Полезно, но первичный приём — `JOIN FETCH`/`EntityGraph`/DTO.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `LazyInitializationException` | доступ к LAZY-связи вне сессии | грузить в транзакции / `JOIN FETCH` / DTO |
| Тихий N+1 (медленно, но «работает») | цикл по ленивой связи | `JOIN FETCH` / `@EntityGraph` / `@BatchSize` |
| N+1 маскируется в проде | `open-in-view=true` | `spring.jpa.open-in-view=false`, грузить явно |
| Дубли строк при `JOIN FETCH` | JOIN размножает корень | `select distinct` |
| `HHH000104` пагинация в памяти | `JOIN FETCH` коллекции + `Pageable` | `@EntityGraph` или две выборки (id → данные) |
| EAGER «везде на всякий случай» | боязнь lazy-исключений | делать LAZY + грузить точечно под кейс |
| `count()` запрос тоже делает JOIN | fetch в countQuery | задать отдельный `countQuery` без fetch |

---

## Дополнительные источники

- [Spring Data JPA: Entity Graphs](https://docs.spring.io/spring-data/jpa/reference/jpa/entity-graph.html).
- [Vlad Mihalcea — N+1 query problem](https://vladmihalcea.com/n-plus-1-query-problem/).
- [модуль 86](../m86_hibernate_deep_dive_fetching/theory.md) — fetching в Hibernate глубже (то же на уровне HQL/Criteria).
- [модуль 91](../m91_hibernate_deep_dive_performance/theory.md) — производительность, batching, кэш.

## Что дальше

В [модуле 83](../m83_spring_data_jpa_testing/theory.md) — как **проверять** репозитории и запросы: `@DataJpaTest`, `TestEntityManager`, H2 и обзор Testcontainers. Тесты помогут ловить тот же N+1 и регрессии в запросах автоматически.
