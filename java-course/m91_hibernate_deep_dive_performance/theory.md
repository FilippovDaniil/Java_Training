# Модуль 91. Hibernate Deep Dive: производительность — batching, bulk, read-only, кэш 2-го уровня

ORM удобен, но «прозрачность» дорого стоит, если не понимать стоимость операций. N+1 мы закрыли ([модуль 86](../m86_hibernate_deep_dive_fetching/theory.md)). Здесь — массовые вставки/обновления, экономия памяти и кэширование.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Работаем через `EntityManager`. Проект — `shop-data-jpa`.

---

## JDBC batching

Без батчинга каждый `INSERT` — отдельный round-trip к БД. Батчинг группирует операторы в одну отправку.

```properties
# persistence.xml / application.properties
hibernate.jdbc.batch_size=50
hibernate.order_inserts=true     # сгруппировать однотипные INSERT
hibernate.order_updates=true
```

```
   без батчинга:  INSERT; INSERT; INSERT; ... (N round-trip)
   с batch_size=50: [50 INSERT одной пачкой]; [50]; ...  (N/50 round-trip)
```

> ⚠️ `GenerationType.IDENTITY` **отключает** батчинг вставок: Hibernate обязан получить сгенерированный id после каждого INSERT построчно. Для bulk-insert берите `SEQUENCE` ([модуль 88](../m88_hibernate_deep_dive_modeling/theory.md)).

---

## Чистка контекста в больших циклах

Persistence context **накапливает** все управляемые сущности — при вставке миллиона объектов это `OutOfMemoryError` и замедление dirty checking. Решение: периодически `flush()` + `clear()`.

```java
for (int i = 0; i < 100_000; i++) {
    em.persist(new Product("Товар-" + i));
    if (i % 50 == 0) {        // совпадает с batch_size
        em.flush();           // отправить пачку в БД
        em.clear();           // освободить контекст (объекты больше не нужны)
    }
}
```

```
   persist, persist, ... (контекст растёт)
        │  каждые 50:
        ├─ flush()  → пачка INSERT в БД
        └─ clear()  → контекст очищен (память освобождается)
```

---

## Bulk-операции vs построчная обработка

Поднять цену всем товарам категории:

```java
// ❌ медленно: N SELECT + N UPDATE, всё через контекст
for (Product p : repo.findByCategory("Еда")) p.setPrice(p.getPrice() + 10);

// ✅ один UPDATE на стороне БД:
int n = em.createQuery("update Product p set p.price = p.price + 10 where p.category = :c")
    .setParameter("c", "Еда").executeUpdate();
```

> Bulk идёт **мимо** persistence context (см. [модуль 87](../m87_hibernate_deep_dive_querying/theory.md)) — после него `em.clear()`/`refresh()`. Зато на порядки быстрее для массовых изменений.

---

## read-only: отключить dirty checking

Для чисто читающих сценариев снимок для dirty checking не нужен — это память и CPU:

```java
List<Product> list = em.createQuery("select p from Product p", Product.class)
    .setHint("org.hibernate.readOnly", true)        // не делать снимки, не отслеживать изменения
    .getResultList();
```

Или транзакция `@Transactional(readOnly = true)` ([модуль 81](../m81_spring_data_jpa_transactions/theory.md)). Меньше памяти, быстрее (нет сравнения при flush).

---

## StatelessSession: bulk без контекста

`StatelessSession` — низкоуровневый API **без** persistence context, кэша 1-го уровня, dirty checking и каскадов. Идеален для ETL/массовой обработки.

```java
import org.hibernate.StatelessSession;
import org.hibernate.SessionFactory;

SessionFactory sf = em.getEntityManagerFactory().unwrap(SessionFactory.class);
try (StatelessSession ss = sf.openStatelessSession()) {
    var tx = ss.getTransaction(); tx.begin();
    for (int i = 0; i < 100_000; i++) {
        ss.insert(new Product("Товар-" + i));   // прямой INSERT, без накопления в контексте
    }
    tx.commit();
}
```

| | обычный `Session`/`EntityManager` | `StatelessSession` |
|---|-----------------------------------|--------------------|
| кэш 1-го уровня | да | **нет** |
| dirty checking | да | нет (нужен явный `update`) |
| каскады | да | **нет** |
| память на больших объёмах | растёт (нужен clear) | стабильная |
| когда | обычная бизнес-логика | массовый импорт/экспорт |

---

## Кэш 2-го уровня

Кэш 1-го уровня живёт в пределах одного `EntityManager`. **Кэш 2-го уровня** — общий для `SessionFactory` (между сессиями/запросами), хранит редко меняющиеся данные (справочники).

```java
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)   // кэшируемая сущность
class Category { @Id Long id; String name; }
```

```properties
hibernate.cache.use_second_level_cache=true
hibernate.cache.region.factory_class=jcache   # напр. Ehcache/Caffeine как провайдер
```

```
   сессия 1: find(Category,1) → SELECT → кладёт в L2-кэш
   сессия 2: find(Category,1) → попадание в L2 (БЕЗ SELECT)
```

| Стратегия | Когда |
|-----------|-------|
| `READ_ONLY` | неизменяемые справочники |
| `READ_WRITE` | редко меняются, нужна согласованность |
| `NONSTRICT_READ_WRITE` | допустимо краткое расхождение |

> Кэш 2-го уровня — для **редко меняющихся** данных с высокой долей чтений. Для часто изменяемых он только вредит (инвалидация). Отдельно есть **query cache** (`hibernate.cache.use_query_cache`) для результатов запросов.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тысячи INSERT поодиночке | нет батчинга / `IDENTITY` | `batch_size` + `SEQUENCE` |
| `OutOfMemoryError` при импорте | контекст накапливает сущности | `flush()`+`clear()` в цикле / `StatelessSession` |
| Массовый апдейт тормозит | построчно через контекст | bulk `update`/`delete` (+ `clear` после) |
| Лишняя память на чтении | dirty-checking снимки | `readOnly` hint / `@Transactional(readOnly)` |
| Каскады «не сработали» в StatelessSession | у него их нет | обычный Session или ручные операции |
| L2-кэш на часто меняющихся данных | постоянная инвалидация | кэшировать только справочники |
| L2 «не работает» | не включён/нет провайдера | `use_second_level_cache` + region factory + `@Cache` |

---

## Дополнительные источники

- [Hibernate ORM: Performance & Batching](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#batch).
- [Hibernate ORM: Caching](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#caching).
- [Vlad Mihalcea — How to batch INSERT and UPDATE statements](https://vladmihalcea.com/how-to-batch-insert-and-update-statements-with-hibernate/).
- [модуль 86](../m86_hibernate_deep_dive_fetching/theory.md) — устранение N+1 (главный перформанс-фактор).

## Что дальше

В [модуле 92](../m92_hibernate_deep_dive_diagnostics/theory.md) — диагностика: логирование SQL, `Statistics` API, тестирование числа запросов (ловля N+1 в тестах), каталог анти-паттернов и финальный аудит. Это завершает курс по Hibernate.
