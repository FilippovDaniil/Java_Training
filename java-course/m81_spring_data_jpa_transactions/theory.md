# Модуль 81. Транзакции: @Transactional, propagation, isolation

Запросы выполнены ([модуль 80](../module-80-spring-data-jpa-advanced/theory.md)), но бизнес-операция часто состоит из **нескольких** изменений, которые должны примениться **всё или ничего** (списать товар + создать заказ). Это обеспечивает **транзакция**. Разберём `@Transactional`, persistence context, распространение и уровни изоляции.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Проект — `shop-data-jpa`.

---

## Что такое транзакция (ACID)

```
   Транзакция = неделимая единица работы

   placeOrder():
      ├─ списать 1 шт. со склада   ┐
      ├─ создать заказ              ├─ ВСЁ или НИЧЕГО
      └─ списать деньги            ┘
   Если любой шаг упал → откатить все (rollback).
```

| Свойство ACID | Смысл |
|---------------|-------|
| **A**tomicity | всё или ничего |
| **C**onsistency | данные остаются согласованными |
| **I**solation | параллельные транзакции не мешают друг другу |
| **D**urability | зафиксированное сохраняется |

---

## @Transactional

Помечает метод (или класс) как транзакционный. Spring открывает транзакцию перед методом, коммитит после, **откатывает при исключении**.

```java
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final ProductRepository products;
    private final OrderRepository orders;
    // ...

    @Transactional
    public Order placeOrder(Long productId, int qty) {
        Product p = products.findById(productId).orElseThrow();
        p.setStock(p.getStock() - qty);          // 1
        Order order = orders.save(new Order(productId, qty));  // 2
        if (p.getStock() < 0) throw new IllegalStateException("Нет на складе"); // откат 1 и 2
        return order;
    }
}
```

> **Где ставить:** на **сервисном** слое (бизнес-операция), не на репозитории и не на контроллере. Граница транзакции = граница бизнес-операции.

---

## Persistence context и dirty checking

Внутри транзакции `EntityManager` держит **persistence context** — кэш управляемых сущностей. Загруженная сущность становится **managed**. Hibernate отслеживает её изменения и при коммите сам делает `UPDATE` — это **dirty checking**.

```java
@Transactional
public void rename(Long id, String newName) {
    Product p = repo.findById(id).orElseThrow();
    p.setName(newName);     // НЕТ вызова save()!
}                           // при коммите Hibernate сам сделает UPDATE (dirty checking)
```

```
   findById → объект managed (в кэше контекста)
        │
   setName(...) → Hibernate замечает изменение
        │
   commit → автоматический UPDATE (save() вызывать не нужно)
```

> Это работает **только** для managed-сущностей внутри транзакции. Для detached-объекта (вне контекста) изменения не отследятся — нужен явный `save()`.

---

## readOnly — оптимизация чтения

```java
@Transactional(readOnly = true)
public List<Product> findAll() { return repo.findAll(); }
```

`readOnly = true` подсказывает Hibernate отключить dirty checking (не делать снимки для сравнения) — быстрее для чисто читающих методов.

---

## Правила отката (rollback)

| Тип исключения | Поведение по умолчанию |
|----------------|------------------------|
| `RuntimeException` (unchecked) | **откат** |
| `Error` | откат |
| checked `Exception` | **НЕ откатывает!** (коммитит) |

Изменить:

```java
@Transactional(rollbackFor = Exception.class)        // откатывать и checked
@Transactional(noRollbackFor = NotFoundException.class)  // не откатывать конкретное
```

> Распространённая ошибка: бросить **checked** исключение и удивляться, что транзакция закоммитилась. Либо бросать unchecked, либо указать `rollbackFor`.

---

## Propagation — распространение транзакций

Что делать, если транзакционный метод вызывает другой транзакционный метод?

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void audit(String msg) { ... }   // ВСЕГДА своя транзакция
```

| Propagation | Поведение |
|-------------|-----------|
| `REQUIRED` (по умолч.) | присоединиться к текущей или создать новую |
| `REQUIRES_NEW` | приостановить текущую, создать новую (аудит/логи, которые должны сохраниться даже при откате основной) |
| `SUPPORTS` | использовать текущую, если есть; иначе без транзакции |
| `MANDATORY` | требует существующую (иначе ошибка) |
| `NESTED` | вложенная (savepoint) |
| `NEVER` | падает, если транзакция есть |

```
   placeOrder() [REQUIRED]
        │
        ├─ deductStock()  [REQUIRED]      → та же транзакция (откатится вместе)
        └─ audit()        [REQUIRES_NEW]  → отдельная транзакция (сохранится даже при откате placeOrder)
```

---

## Isolation — уровни изоляции

Защита от аномалий при параллельных транзакциях:

| Уровень | Грязное чтение | Неповторяемое чтение | Фантомы |
|---------|:--------------:|:--------------------:|:-------:|
| `READ_UNCOMMITTED` | возможно | возможно | возможно |
| `READ_COMMITTED` | нет | возможно | возможно |
| `REPEATABLE_READ` | нет | нет | возможно |
| `SERIALIZABLE` | нет | нет | нет |

```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
public void process() { ... }
```

Чем выше изоляция — тем меньше аномалий, но больше блокировок и ниже параллелизм. По умолчанию обычно `READ_COMMITTED` (зависит от БД). Подробнее о блокировках — [модуль 90](../module-90-hibernate-deep-dive-locking/theory.md).

---

## Самовызов не перехватывается

`@Transactional` работает через прокси (как AOP, [модуль 63](../module-63-spring-core-events-aop/theory.md)). Вызов метода **изнутри того же класса** обходит прокси — транзакция не откроется.

```java
public void outer() {
    inner();          // ❌ @Transactional на inner() НЕ сработает (self-invocation)
}
@Transactional
public void inner() { ... }
```

Решение: вынести `inner()` в отдельный бин.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Изменения не сохранились | нет `@Transactional` / detached-объект | транзакция на сервисе; managed-объект |
| Транзакция не откатилась | брошено **checked** исключение | `rollbackFor` или бросать unchecked |
| `@Transactional` «не работает» | self-invocation (вызов из того же класса) | вынести метод в отдельный бин |
| `LazyInitializationException` | доступ к ленивой связи вне транзакции | загрузить в транзакции / `JOIN FETCH` ([модуль 82](../module-82-spring-data-jpa-lazy-loading/theory.md)) |
| Аудит пропал при откате основной | общая транзакция | `REQUIRES_NEW` для аудита |
| Лишние блокировки/дедлоки | слишком высокая изоляция | подобрать минимально достаточный уровень |
| `save()` пишут «на всякий случай» | не знают про dirty checking | для managed-сущности `save()` не нужен |

---

## Дополнительные источники

- [Spring: Transaction Management](https://docs.spring.io/spring-framework/reference/data-access/transaction.html).
- [Spring Data JPA: Transactionality](https://docs.spring.io/spring-data/jpa/reference/jpa/transactions.html).
- [модуль 85](../module-85-hibernate-deep-dive-lifecycle/theory.md) — жизненный цикл сущности, flush, dirty checking глубже.
- [модуль 90](../module-90-hibernate-deep-dive-locking/theory.md) — блокировки.

## Что дальше

В [модуле 82](../module-82-spring-data-jpa-lazy-loading/theory.md) — ленивая загрузка, проблема N+1, `JOIN FETCH` и `EntityGraph`. На этом первый поток Spring Data JPA (77–81) завершается, далее — оптимизация и тестирование.
