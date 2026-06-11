# Модуль 86. Hibernate Deep Dive: стратегии загрузки, N+1, JOIN FETCH, EntityGraph

В [модуле 82](../m82_spring_data_jpa_lazy_loading/theory.md) мы боролись с N+1 средствами Spring Data. Теперь — те же приёмы **на уровне Hibernate/HQL**, плюс инструменты, которых нет в Spring Data: `Hibernate.initialize`, `@Fetch(FetchMode)`, batch fetching, проекции через `Tuple`.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Работаем через `EntityManager`/HQL. Проект — `shop-data-jpa`.

---

## Lazy = прокси

При `FetchType.LAZY` Hibernate подставляет **прокси** (для `@ManyToOne`) или **обёртку-коллекцию** (`PersistentBag`/`PersistentSet` для `@OneToMany`). Реальный SELECT — при первом обращении к данным, пока контекст открыт.

```java
import org.hibernate.Hibernate;

Category c = em.find(Category.class, 1L);
System.out.println(Hibernate.isInitialized(c.getProducts())); // false — ещё не грузилось
Hibernate.initialize(c.getProducts());                        // принудительно инициализировать
System.out.println(Hibernate.isInitialized(c.getProducts())); // true
```

| Утилита | Зачем |
|---------|-------|
| `Hibernate.isInitialized(proxy)` | проверить, загружена ли связь |
| `Hibernate.initialize(proxy)` | инициализировать, пока контекст открыт (защита от `LazyInitializationException`) |

---

## Проблема N+1 (повторение, уровень HQL)

```java
List<Category> cats = em.createQuery("select c from Category c", Category.class).getResultList(); // 1
for (Category c : cats) {
    c.getProducts().size();   // +1 SELECT на каждую категорию → N+1
}
```

Включите `hibernate.show_sql=true` — увидите `1 + N` запросов. Ниже — четыре способа лечения.

---

## Решение 1: JOIN FETCH в HQL

```java
List<Category> cats = em.createQuery(
        "select distinct c from Category c join fetch c.products", Category.class)
    .getResultList();   // ОДИН SELECT с JOIN
```

- `distinct` убирает дубли корня (JOIN размножает строки).
- Нельзя сделать **два** `join fetch` для двух *коллекций* одновременно (декартово произведение) → `MultipleBagFetchException`. Для двух коллекций: либо `Set` вместо `List`, либо две выборки, либо `@BatchSize`.

---

## Решение 2: EntityGraph (JPA hint)

В чистом JPA `@EntityGraph` задаётся программно и подаётся как **hint** к запросу:

```java
EntityGraph<Category> graph = em.createEntityGraph(Category.class);
graph.addAttributeNodes("products");

Category c = em.find(Category.class, 1L,
        java.util.Map.of("jakarta.persistence.fetchgraph", graph));
```

| Hint | Смысл |
|------|-------|
| `jakarta.persistence.fetchgraph` | грузить ТОЛЬКО узлы графа (остальное — lazy) |
| `jakarta.persistence.loadgraph` | грузить узлы графа + связи с EAGER по умолчанию |

(В Spring Data то же делается аннотацией `@EntityGraph` — см. [модуль 82](../m82_spring_data_jpa_lazy_loading/theory.md).)

---

## Решение 3: batch fetching (@BatchSize) и @Fetch

`@BatchSize` объединяет догрузку lazy-связей в запросы `IN (...)`:

```java
@OneToMany(mappedBy = "category")
@org.hibernate.annotations.BatchSize(size = 10)   // 100 категорий → 10 запросов IN(...)
private List<Product> products;
```

`@Fetch(FetchMode)` управляет способом догрузки на уровне Hibernate:

| `FetchMode` | Поведение |
|-------------|-----------|
| `SELECT` | отдельный SELECT на каждую связь (по умолчанию для lazy) — источник N+1 |
| `JOIN` | один JOIN (для EAGER); игнорируется в HQL-запросах |
| `SUBSELECT` | догрузить связь для ВСЕХ корней одним под-запросом `WHERE id IN (select ...)` |

```java
@OneToMany(mappedBy = "category")
@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
private List<Product> products;   // N+1 → 1 + 1 (второй запрос — subselect для всех)
```

---

## Решение 4: проекции (не грузить сущности)

### HQL constructor expression (DTO)

```java
record CategorySummary(String name, long count) {}

List<CategorySummary> rows = em.createQuery(
        "select new com.shop.CategorySummary(c.name, count(p)) " +
        "from Category c left join c.products p group by c.id, c.name",
        CategorySummary.class).getResultList();
```

### Tuple / скалярная проекция

```java
import jakarta.persistence.Tuple;

List<Tuple> tuples = em.createQuery(
        "select c.name as name, count(p) as cnt from Category c left join c.products p group by c.id, c.name",
        Tuple.class).getResultList();
for (Tuple t : tuples) {
    System.out.println(t.get("name", String.class) + " -> " + t.get("cnt", Long.class));
}
```

Нет сущностей → нет прокси → нет N+1 и нет `LazyInitializationException`. Для read-моделей — лучший вариант.

---

## Какой приём выбрать

```
   нужны сами сущности + их коллекция     → JOIN FETCH (одна коллекция) / EntityGraph
   много корней, догрузка коллекций        → @BatchSize или @Fetch(SUBSELECT)
   нужны лишь несколько полей (read-model) → DTO/Tuple проекция (сущности не грузим)
   две коллекции сразу                      → НЕ два join fetch (MultipleBagFetchException);
                                              Set, или 2 выборки, или batch
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| N+1 запросов | lazy-доступ в цикле (`FetchMode.SELECT`) | JOIN FETCH / EntityGraph / `@BatchSize` / SUBSELECT |
| `LazyInitializationException` | доступ к lazy вне контекста | `Hibernate.initialize` в контексте / fetch / DTO |
| `MultipleBagFetchException` | два `join fetch` для `List`-коллекций | `Set`, две выборки или `@BatchSize` |
| Дубли строк при `join fetch` | JOIN размножает корень | `select distinct` |
| Пагинация + `join fetch` коллекции | Hibernate пагинирует в памяти (`HHH000104`) | EntityGraph / две выборки / batch |
| EAGER «зашит» в маппинг | негибко, грузит всегда | LAZY + точечный fetch под кейс |
| Проекция тащит всю сущность | забыли `select new`/Tuple | проецировать только нужные поля |

---

## Дополнительные источники

- [Hibernate ORM: Fetching](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#fetching).
- [Vlad Mihalcea — The best way to fix the Hibernate MultipleBagFetchException](https://vladmihalcea.com/hibernate-multiplebagfetchexception/).
- [модуль 82](../m82_spring_data_jpa_lazy_loading/theory.md) — те же приёмы в Spring Data JPA.

## Что дальше

В [модуле 87](../m87_hibernate_deep_dive_querying/theory.md) — языки запросов: HQL/JPQL, **Criteria API** (типобезопасные динамические запросы), native SQL, плюс владелец связи (owning side) и каскады.
