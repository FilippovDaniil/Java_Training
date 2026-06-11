# Модуль 84. Миграции схемы (Flyway), оптимистичная блокировка, аудит

До сих пор схему создавал Hibernate через `ddl-auto`. Для **продакшена** это опасно: нет контроля над DDL, нет истории, нельзя откатить. Промышленный подход — **версионируемые миграции** (Flyway). Заодно разберём защиту от потерянных обновлений (`@Version`) и автоматический **аудит** (кто/когда менял).

> Практика — задачи в `practice/`. Задачи 01–03 — написание **SQL-миграций** (носитель — `.java` с text-блоком, как в [модуле 45](../m45_sql_dml/theory.md)). Задачи 04–07 — Spring Boot (`spring-boot-starter-data-jpa`, `flyway-core`, `com.h2database:h2`). Проект — `shop-data-jpa`.

---

## Почему не ddl-auto=update в проде

| `ddl-auto` | Поведение | Прод? |
|------------|-----------|-------|
| `create` / `create-drop` | пересоздаёт схему (теряет данные!) | ❌ |
| `update` | пытается доработать схему под сущности | ❌ непредсказуемо, не удаляет/не переименовывает, нет истории |
| `validate` | только проверяет соответствие схемы и сущностей | ✅ (вместе с миграциями) |
| `none` | ничего не делает | ✅ |

> Прод-рецепт: схему ведёт **Flyway**, а `spring.jpa.hibernate.ddl-auto=validate` лишь сверяет, что сущности совпадают с реальной схемой.

---

## Flyway: версионируемые миграции

Flyway применяет SQL-скрипты по порядку версий и помнит, что уже применено.

```
   src/main/resources/db/migration/
   ├── V1__create_products.sql        ← применится первой
   ├── V2__add_category_column.sql    ← затем
   ├── V3__seed_categories.sql
   └── R__refresh_catalog_view.sql    ← repeatable: применяется при изменении контента
```

**Правила именования:**

```
   V  1  __  create_products  .sql
   │  │      │
   │  │      описание (двойное подчёркивание перед ним)
   │  версия (1, 2, 2.1, 20240601 ...)
   префикс: V = versioned (однократно), R = repeatable
```

Как это работает:

```
   старт приложения
        │
   Flyway читает db/migration
        │
   сверяет с таблицей flyway_schema_history (что уже применено)
        │
   применяет НОВЫЕ версии по возрастанию, фиксирует в истории
        │
   (если checksum применённого файла изменился → ошибка validate!)
```

| Понятие | Смысл |
|---------|-------|
| `flyway_schema_history` | служебная таблица: версия, описание, checksum, успех |
| **versioned** (`V`) | применяется ровно один раз, в порядке версий |
| **repeatable** (`R`) | без версии; повторно применяется при изменении checksum (вьюхи, процедуры) |
| **никогда не меняй применённую миграцию** | поменялся checksum → `validate` упадёт; вместо правки — новая `V{n+1}` |

Подключение (Spring Boot): добавить зависимость `org.flywaydb:flyway-core` — Boot сам запустит Flyway на старте. H2 8.x требует `flyway-database-hsqldb`/`flyway-core` совместимой версии; для Postgres — `flyway-database-postgresql`.

---

## Оптимистичная блокировка: @Version

Проблема **потерянного обновления**: две транзакции читают строку, обе меняют, вторая затирает изменения первой.

```
   T1 читает Product(stock=10)        T2 читает Product(stock=10)
   T1 ставит stock=9, commit          T2 ставит stock=5, commit
                                       └─ затёрло изменение T1! (lost update)
```

Решение — поле `@Version`. Hibernate добавляет его в `WHERE` при `UPDATE` и инкрементит:

```java
@Entity
class Product {
    @Id @GeneratedValue Long id;
    private int stock;

    @Version              // ← версия строки (int/long/timestamp)
    private long version;
}
```

```sql
-- Hibernate генерирует:
UPDATE products SET stock = ?, version = version + 1
WHERE id = ? AND version = ?      -- ожидаемая версия
-- если строк обновлено 0 → кто-то уже изменил → ObjectOptimisticLockingFailureException
```

| Тип блокировки | Как работает | Когда |
|----------------|--------------|-------|
| **Оптимистичная** (`@Version`) | проверка версии при коммите; конфликт → исключение | редкие конфликты (большинство кейсов) |
| **Пессимистичная** (`SELECT ... FOR UPDATE`) | блокирует строку при чтении | частые конфликты, критичные операции |

Пессимистичную и Envers (история изменений) разбираем в [модуле 90](../m90_hibernate_deep_dive_locking/theory.md).

> Обработка: ловите `ObjectOptimisticLockingFailureException` (Spring) / `OptimisticLockException` (JPA) и **повторяйте** операцию (re-read + retry) либо сообщайте пользователю «данные изменились, обновите».

---

## Аудит: кто и когда менял

Часто нужно хранить `created_at`, `updated_at`, `created_by`. Spring Data JPA делает это автоматически.

```java
@Configuration
@EnableJpaAuditing                       // 1) включить аудит
class JpaConfig {
    @Bean
    AuditorAware<String> auditorAware() { // 4) кто текущий пользователь (для @CreatedBy)
        return () -> Optional.of("system"); // в реале — из SecurityContext
    }
}

@Entity
@EntityListeners(AuditingEntityListener.class)   // 2) слушатель заполняет поля
class Product {
    @Id @GeneratedValue Long id;

    @CreatedDate      private Instant createdAt;   // 3) проставится при INSERT
    @LastModifiedDate private Instant updatedAt;   //    обновится при каждом UPDATE
    @CreatedBy        private String createdBy;     //    из AuditorAware
    @LastModifiedBy   private String updatedBy;
}
```

```
   save(new Product)  → AuditingEntityListener → createdAt/createdBy заполнены
   изменение + commit → updatedAt/updatedBy обновлены автоматически
```

Часто аудит-поля выносят в `@MappedSuperclass` (базовый класс `Auditable`) и наследуют во всех сущностях.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Прод-схема «уехала» от кода | `ddl-auto=update` | Flyway + `ddl-auto=validate` |
| `Migration checksum mismatch` | изменили уже применённый `V`-скрипт | не править применённые; добавить новую версию |
| Flyway не запустился | нет `flyway-core` / пустая `db/migration` | добавить зависимость, положить скрипты |
| Потерянное обновление | нет `@Version` | добавить поле `@Version` |
| `ObjectOptimisticLockingFailureException` | конкурентное изменение | re-read + retry или сообщить пользователю |
| `@Version` руками меняют/инициализируют | им управляет Hibernate | не присваивать версию вручную |
| Аудит-поля пустые | забыли `@EnableJpaAuditing` / `@EntityListeners` | добавить обе аннотации |
| `@CreatedBy` всегда null | нет `AuditorAware`-бина | объявить `AuditorAware<>` |

---

## Дополнительные источники

- [Flyway Documentation](https://documentation.red-gate.com/fd).
- [Spring Boot: Database Initialization — Flyway](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway).
- [Spring Data JPA: Auditing](https://docs.spring.io/spring-data/jpa/reference/auditing.html).
- [модуль 90](../m90_hibernate_deep_dive_locking/theory.md) — пессимистичная блокировка, soft delete, Envers.

## Что дальше

Это **финал блока Spring Data JPA (77–84)**. Дальше — **Hibernate Deep Dive (85–92)**: в [модуле 85](../m85_hibernate_deep_dive_lifecycle/theory.md) заглянем под капот — жизненный цикл сущности, dirty checking, flush, detached. Spring Data JPA — это удобная обёртка над тем, что вы изучите там.
