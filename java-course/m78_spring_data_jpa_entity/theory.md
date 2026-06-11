# Модуль 78. Сущности JPA: ключи, маппинг полей, @Embeddable

В [модуле 77](../module-77-spring-data-jpa-intro/theory.md) сущность была минимальной (`@Entity` + `@Id`). Реальные сущности требуют точного **маппинга**: какая колонка обязательна, какой длины, как хранить перечисление и дату, как вынести группу полей в отдельный тип. Разберём детально.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Проект — `shop-data-jpa`.

---

## Сущность и таблица

```java
@Entity
@Table(name = "products",
       uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ...
}
```

| Аннотация | Назначение |
|-----------|-----------|
| `@Entity` | класс — сущность |
| `@Table(name=...)` | имя таблицы (по умолчанию — имя класса) |
| `@Id` | первичный ключ |
| `@GeneratedValue` | стратегия генерации ключа |

---

## Генерация первичного ключа

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

| Стратегия | Как работает | Когда |
|-----------|--------------|-------|
| `IDENTITY` | автоинкремент колонки в БД | MySQL, H2, PostgreSQL (`SERIAL`) |
| `SEQUENCE` | через последовательность БД | PostgreSQL, Oracle (лучше для батчей) |
| `TABLE` | отдельная таблица-счётчик | переносимо, но медленно |
| `AUTO` | выбирает Hibernate | по умолчанию |

> Для PostgreSQL обычно предпочитают `SEQUENCE` (позволяет батчинг INSERT). Для H2/MySQL — `IDENTITY`.

---

## Маппинг колонок: @Column

```java
@Column(name = "product_name", nullable = false, length = 100)
private String name;

@Column(unique = true)
private String sku;

@Column(precision = 10, scale = 2)
private BigDecimal weight;
```

| Атрибут `@Column` | Смысл |
|-------------------|-------|
| `name` | имя колонки |
| `nullable` | можно ли `NULL` (по умолчанию `true`) |
| `length` | длина `VARCHAR` (по умолчанию 255) |
| `unique` | уникальный индекс |
| `precision`/`scale` | для `BigDecimal` |
| `columnDefinition` | задать тип/DEFAULT вручную |

> **Урок из практики:** добавляя `@Column(nullable = false)` к НОВОМУ полю в таблицу с данными при `ddl-auto=update`, Hibernate не сможет добавить `NOT NULL` без DEFAULT. Решение: `@Column(columnDefinition = "integer default 0")`.

---

## Перечисления: @Enumerated

```java
public enum Status { NEW, ACTIVE, ARCHIVED }

@Enumerated(EnumType.STRING)     // хранить "ACTIVE" (строкой)
private Status status;
```

| `EnumType` | Хранится как | Риск |
|------------|--------------|------|
| `STRING` | имя константы (`"ACTIVE"`) | безопасно при изменении порядка |
| `ORDINAL` | порядковый номер (0,1,2) | **опасно**: вставка/перестановка констант ломает данные |

> Всегда используйте `EnumType.STRING`. `ORDINAL` — источник трудноуловимых багов.

---

## Дата и время

`java.time` (см. [модуль 20](../module-20-date-time/theory.md)) маппится автоматически:

```java
private LocalDate dueDate;          // → DATE
private LocalDateTime createdAt;    // → TIMESTAMP
private Instant updatedAt;          // → TIMESTAMP
```

Аннотации `@Temporal` нужны только для устаревших `java.util.Date`/`Calendar` — с `java.time` они не требуются.

---

## @Transient — не сохранять поле

```java
@Transient
private String displayLabel;        // вычисляемое — НЕ колонка в БД

public String getDisplayLabel() {
    return name + " (" + price + " руб.)";
}
```

`@Transient` исключает поле из персистентности — оно живёт только в объекте.

---

## @Embeddable — переиспользуемая группа полей (value object)

Когда несколько полей образуют единое целое (адрес, деньги, период), их выносят в `@Embeddable`-класс:

```java
@Embeddable
public class Money {
    private long amount;       // в копейках
    private String currency;   // "RUB"
    // конструкторы, геттеры...
}

@Entity
public class Product {
    @Id @GeneratedValue private Long id;

    @Embedded
    private Money price;        // поля Money станут колонками таблицы products
}
```

```
   Класс Product + @Embedded Money     Таблица products
   ───────────────────────────────     ───────────────────────────────────
   id, name,                           | id | name | amount | currency |
   Money(amount, currency)             |----|------|--------|----------|
```

Embeddable **не имеет своего `@Id`** и своей таблицы — его поля «встраиваются» в таблицу владельца. Переопределить имена колонок можно через `@AttributeOverride`.

| Сущность (`@Entity`) | Встраиваемый тип (`@Embeddable`) |
|----------------------|----------------------------------|
| своя таблица + `@Id` | колонки в таблице владельца, без `@Id` |
| имеет идентичность | value object (равенство по значению) |

---

## Составной ключ: @EmbeddedId

Если ключ состоит из нескольких колонок (например, `orderId` + `productId` у позиции заказа):

```java
@Embeddable
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
    // equals/hashCode ОБЯЗАТЕЛЬНЫ!
}

@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    private int quantity;
}
```

Альтернатива — `@IdClass`. Для составных ключей **обязательны** `equals`/`hashCode` (детально — [модуль 88](../module-88-hibernate-deep-dive-modeling/theory.md)).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Данные перепутались при добавлении enum-константы | `EnumType.ORDINAL` | всегда `EnumType.STRING` |
| `NULL`-ы где не ждали | забыт `nullable = false` | явно ограничить колонку |
| `column ... does not exist` (NOT NULL, есть строки) | `update` не может добавить NOT NULL без DEFAULT | `@Column(columnDefinition = "... default ...")` |
| Лишняя колонка в таблице | поле должно быть вычисляемым | пометить `@Transient` |
| `@Embeddable` без `equals`/`hashCode` в ключе | составной ключ требует их | реализовать `equals`/`hashCode` |
| Длинная строка не влезает | `length` по умолчанию 255 | задать `@Column(length = ...)` |

---

## Дополнительные источники

- [Jakarta Persistence: Entities](https://jakarta.ee/specifications/persistence/).
- [Hibernate ORM: Mapping](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html).
- [модуль 51](../module-51-hibernate-orm/theory.md) — основы Hibernate-маппинга.
- [модуль 88](../module-88-hibernate-deep-dive-modeling/theory.md) — value objects, `equals`/`hashCode`, идентификаторы.

## Что дальше

В [модуле 79](../module-79-spring-data-jpa-repository/theory.md) — репозитории: derived queries, `Page`/`Slice`, сортировка.
