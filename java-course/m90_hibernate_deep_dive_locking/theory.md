# Модуль 90. Hibernate Deep Dive: блокировки, soft delete, аудит истории (Envers)

В [модуле 84](../module-84-spring-data-jpa-migrations/theory.md) мы кратко увидели `@Version`. Теперь — полная картина управления конкуренцией: **оптимистичная** и **пессимистичная** блокировки, плюс два прикладных приёма поверх — **мягкое удаление** (soft delete) и **аудит истории** изменений через Envers.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2`, для Envers — `org.hibernate.orm:hibernate-envers` + `META-INF/persistence.xml` ("shop-pu"). Работаем через `EntityManager`. Проект — `shop-data-jpa`.

---

## Зачем блокировки: потерянное обновление

```
   T1 читает stock=10        T2 читает stock=10
   T1 пишет stock=9          T2 пишет stock=5   ← затёрло изменение T1 (lost update)
```

Два подхода защиты:

| | Оптимистичная | Пессимистичная |
|---|---------------|----------------|
| идея | «конфликты редки» — проверить версию при записи | «конфликты часты» — заблокировать строку при чтении |
| механизм | `@Version` + `WHERE version=?` | `SELECT ... FOR UPDATE` |
| при конфликте | исключение в конце | ждёт освобождения / таймаут |
| блокировки в БД | нет | да (строки залочены) |
| когда | большинство веб-сценариев | критичные операции, частая конкуренция |

---

## Оптимистичная блокировка (@Version)

```java
@Entity
class Product {
    @Id @GeneratedValue Long id;
    private int stock;
    @Version private long version;   // int / long / Instant
}
```

При `UPDATE` Hibernate добавляет `AND version = ?` и инкрементит версию. Если строк обновлено 0 (кто-то успел раньше) → `OptimisticLockException` / `ObjectOptimisticLockingFailureException`.

### Явные режимы оптимистичной блокировки

```java
em.find(Product.class, id, LockModeType.OPTIMISTIC);                  // проверить версию при коммите
em.find(Product.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT); // ПРИНУДИТЕЛЬНО поднять версию
```

| `LockModeType` | Смысл |
|----------------|-------|
| `OPTIMISTIC` | в конце транзакции проверить, что версия не менялась (даже при чистом чтении) |
| `OPTIMISTIC_FORCE_INCREMENT` | поднять версию, даже если саму сущность не меняли (например, изменили её ребёнка, а «версионировать» хотим родителя) |

---

## Пессимистичная блокировка

Блокирует строку в БД на время транзакции — другие ждут.

```java
Product p = em.find(Product.class, id, LockModeType.PESSIMISTIC_WRITE);
// SQL: SELECT ... FROM products WHERE id=? FOR UPDATE   — строка залочена до commit
p.setStock(p.getStock() - 1);
```

| `LockModeType` | SQL | Смысл |
|----------------|-----|-------|
| `PESSIMISTIC_READ` | `FOR SHARE` | другие могут читать, не могут писать |
| `PESSIMISTIC_WRITE` | `FOR UPDATE` | эксклюзивно: другие ждут и чтение-под-запись, и запись |
| `PESSIMISTIC_FORCE_INCREMENT` | `FOR UPDATE` + version++ | залочить и поднять версию |

### Таймаут блокировки

```java
em.find(Product.class, id, LockModeType.PESSIMISTIC_WRITE,
        java.util.Map.of("jakarta.persistence.lock.timeout", 2000)); // ждать не дольше 2с
```

Без таймаута транзакция может ждать вечно. Слишком агрессивные блокировки → **дедлоки** (две транзакции ждут строки друг друга). Держите критическую секцию короткой.

---

## Soft delete (мягкое удаление)

Иногда строку нельзя физически удалять (история, аудит, восстановление). Помечаем флагом `deleted`, а Hibernate подменяет `DELETE` на `UPDATE` и автоматически фильтрует «удалённые».

```java
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")  // em.remove → этот UPDATE
@SQLRestriction("deleted = false")                                   // все SELECT'ы добавят это условие
class Product {
    @Id @GeneratedValue Long id;
    private boolean deleted = false;
}
```

```
   em.remove(product)  →  UPDATE products SET deleted=true WHERE id=?   (строка остаётся в БД)
   em.find/HQL         →  ... WHERE ... AND deleted=false               (удалённые не видны)
```

> `@SQLRestriction` (Hibernate 6.3+) заменил устаревший `@Where`. Глобальный фильтр можно временно отключить (`@FilterDef`/`@Filter`), если нужно показать «корзину».

---

## Аудит истории: Hibernate Envers

Envers ведёт **полную историю** изменений сущности в отдельных таблицах (`*_AUD` + таблица ревизий).

```java
import org.hibernate.envers.Audited;

@Entity
@Audited                       // включить версионирование истории
class Product {
    @Id @GeneratedValue Long id;
    private String name;
    private int price;
}
```

Каждый `INSERT`/`UPDATE`/`DELETE` Envers записывает в `products_AUD` со своим номером ревизии. Чтение истории:

```java
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

AuditReader reader = AuditReaderFactory.get(em);
List<Number> revisions = reader.getRevisions(Product.class, productId);    // все ревизии товара
Product old = reader.find(Product.class, productId, revisions.get(0));     // состояние на ревизии
```

| Понятие Envers | Смысл |
|----------------|-------|
| `@Audited` | сущность/поле версионируется |
| таблица `*_AUD` | снимок строки на каждую ревизию |
| `REVINFO` | таблица ревизий (номер + timestamp) |
| `AuditReader` | API чтения истории и «машины времени» |

> Envers требует зависимость `hibernate-envers`. Отличие от `@Version`: `@Version` защищает от конкуренции (один счётчик), Envers **хранит всю историю** значений (зачем/когда менялось).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Lost update | нет блокировки | `@Version` (оптимистичная) или pessimistic |
| `OptimisticLockException` | конкурентное изменение | re-read + retry / сообщить пользователю |
| Транзакция висит вечно | пессимистичная блокировка без таймаута | `jakarta.persistence.lock.timeout` |
| Дедлок | встречные блокировки строк | короткая критическая секция, единый порядок захвата |
| «Удалённые» строки видны | нет фильтра soft delete | `@SQLRestriction` |
| Нужно физически удалить, а оно `UPDATE` | `@SQLDelete` перехватывает | нативный запрос в обход |
| Нет истории изменений | не подключён Envers | `@Audited` + `hibernate-envers` |
| `@Version` путают с историей | разные задачи | `@Version` — конкуренция, Envers — аудит |

---

## Дополнительные источники

- [Jakarta Persistence: Lock Modes](https://jakarta.ee/specifications/persistence/3.1/).
- [Hibernate ORM: Soft delete (@SoftDelete / @SQLRestriction)](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#soft-delete).
- [Hibernate Envers User Guide](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#envers).
- [модуль 84](../module-84-spring-data-jpa-migrations/theory.md) — `@Version` в контексте Spring Data + Flyway.

## Что дальше

В [модуле 91](../module-91-hibernate-deep-dive-performance/theory.md) — производительность: JDBC batching, bulk-операции, read-only, `StatelessSession` и кэш 2-го уровня.
