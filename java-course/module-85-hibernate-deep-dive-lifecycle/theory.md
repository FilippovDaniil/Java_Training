# Модуль 85. Hibernate Deep Dive: жизненный цикл сущности, dirty checking, flush

Spring Data JPA ([модули 77–84](../module-77-spring-data-jpa-intro/theory.md)) удобно прятал работу с `EntityManager`. Теперь — **под капот**. Чтобы понимать, почему `save()` иногда не нужен, когда выполняется SQL и откуда берётся `LazyInitializationException`, нужно знать **жизненный цикл сущности** и **persistence context**.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core` (или `jakarta.persistence` + провайдер), `com.h2database:h2` + файл `META-INF/persistence.xml` с persistence-unit `shop-pu`. Работаем через `EntityManager` напрямую (а не Spring Data). Проект — `shop-data-jpa`.

---

## Четыре состояния сущности

```
            new Product(...)
                  │  transient (новый, не в контексте, нет id из БД)
                  ▼
        ┌──── em.persist() ────┐
        │                      ▼
   em.remove()           MANAGED / persistent
        │                (в persistence context, отслеживается)
        ▼                      │  em.detach()/clear()/close()
     REMOVED                   ▼
   (помечен на DELETE)     DETACHED
                          (был managed, контекст закрыт; изменения НЕ отслеживаются)
                               │  em.merge()
                               └──► снова MANAGED (копия)
```

| Состояние | Что значит | В контексте? | Изменения → БД? |
|-----------|-----------|:------------:|:---------------:|
| **transient** | только что `new`, БД не знает | нет | нет |
| **managed** (persistent) | под управлением `EntityManager` | да | **да** (dirty checking) |
| **detached** | был managed, контекст закрыт/очищен | нет | нет (нужен `merge`) |
| **removed** | помечен к удалению | да | DELETE при flush |

---

## Persistence context = кэш 1-го уровня

`EntityManager` держит **persistence context** — карту `id → сущность` в пределах транзакции. Это **кэш первого уровня** (всегда включён, отключить нельзя).

```java
Product a = em.find(Product.class, 1L);   // SELECT ... (попал в контекст)
Product b = em.find(Product.class, 1L);   // НЕТ запроса — вернётся ТОТ ЖЕ объект из кэша
System.out.println(a == b);               // true — одна ссылка (identity гарантия)
```

> Один `id` в пределах контекста = один объект Java. Это **гарантия идентичности** (repeatable read внутри транзакции).

---

## Dirty checking: UPDATE без save()

Hibernate при загрузке делает **снимок** managed-сущности. На flush сравнивает текущее состояние со снимком и сам генерирует `UPDATE` для изменённых полей.

```java
em.getTransaction().begin();
Product p = em.find(Product.class, 1L);   // managed + снимок
p.setPrice(999);                           // меняем поле — БЕЗ em.merge()/save()
em.getTransaction().commit();              // flush → Hibernate сам делает UPDATE
```

```
   find → объект managed + снимок [price=500]
        │
   setPrice(999) → объект [price=999], снимок прежний
        │
   flush/commit → сравнение: price изменился → UPDATE products SET price=999 WHERE id=1
```

> Работает **только** для managed-сущностей. Для detached изменения не видны — нужен `merge`.

---

## Flush: когда реально летит SQL

`persist()`/изменения не выполняют SQL немедленно — они **откладываются** до **flush**. Flush синхронизирует контекст с БД.

Когда происходит flush (режим `FlushModeType.AUTO`, по умолчанию):

| Триггер | Пояснение |
|---------|-----------|
| перед `commit()` | гарантировать запись |
| перед выполнением **запроса** (JPQL/Criteria) | чтобы запрос видел незаписанные изменения |
| явный `em.flush()` | принудительно |

```java
Product p = new Product("Молоко");
em.persist(p);                 // INSERT ОТЛОЖЕН, но id (для IDENTITY) уже получен
// ... SQL ещё не на диске ...
em.flush();                    // ← теперь INSERT выполняется
```

`FlushModeType.COMMIT` — flush только на commit (запросы могут не видеть свежих изменений). Меняют редко.

> **flush ≠ commit.** flush отправляет SQL в БД (в рамках транзакции), commit фиксирует транзакцию. После flush без commit изменения видны только этой транзакции и откатятся при rollback.

---

## refresh: перечитать из БД

`em.refresh(entity)` перезатирает состояние managed-объекта данными из БД, **отменяя** несохранённые изменения в памяти:

```java
Product p = em.find(Product.class, 1L);
p.setPrice(0);            // ошибочное изменение в памяти
em.refresh(p);            // SELECT — вернёт price из БД, изменение в памяти потеряно
```

---

## merge vs persist

| Метод | Для какого состояния | Результат |
|-------|---------------------|-----------|
| `persist(e)` | **transient** | делает managed; `e` тот же объект |
| `merge(e)` | **detached**/transient | копирует состояние в managed-копию и **возвращает её** (исходный `e` остаётся detached!) |

```java
Product detached = ...;               // detached (контекст закрыт)
Product managed = em.merge(detached); // managed-копия — работать дальше с НЕЙ
// detached != managed
```

> Частая ошибка: `em.merge(p)` и продолжать менять `p` — он остался detached, изменения не сохранятся. Используйте возвращённый объект.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Изменения transient не сохранились | объект не managed | `persist()` |
| Изменения detached не сохранились | контекст закрыт | `merge()` и работать с результатом |
| `merge()` «не работает» | продолжают менять исходный объект | использовать возвращённый managed |
| SQL «не выполняется» сразу | отложен до flush | `em.flush()` / commit / запрос |
| Два `find` = два SELECT | разные контексты (em закрыт) | один контекст на единицу работы |
| `LazyInitializationException` | доступ к lazy после закрытия контекста | держать контекст открытым / fetch ([модуль 86](../module-86-hibernate-deep-dive-fetching/theory.md)) |
| `save()` пишут везде | не знают про dirty checking | для managed `save`/`merge` не нужен |

---

## Дополнительные источники

- [Jakarta Persistence: Entity Lifecycle](https://jakarta.ee/specifications/persistence/3.1/).
- [Vlad Mihalcea — JPA entity states](https://vladmihalcea.com/jpa-persist-merge-hibernate/).
- [модуль 81](../module-81-spring-data-jpa-transactions/theory.md) — то же dirty checking глазами Spring `@Transactional`.

## Что дальше

В [модуле 86](../module-86-hibernate-deep-dive-fetching/theory.md) — стратегии загрузки на уровне Hibernate: lazy-прокси, `Hibernate.initialize`, проблема N+1, `JOIN FETCH` в HQL, `EntityGraph`, `@BatchSize`/`@Fetch`, проекции.
