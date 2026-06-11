# Модуль 88. Hibernate Deep Dive: equals/hashCode, Embeddables, ключи, идентификаторы

Сущность — это не «класс с полями». У неё есть **идентичность**, и от того, как вы определите `equals`/`hashCode`, зависит корректность `Set`, `Map` и `merge`. Плюс — как моделировать **value objects** (`@Embeddable`), составные ключи и выбирать стратегию генерации id.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Проект — `shop-data-jpa`.

---

## Проблема equals/hashCode для сущностей

Соблазн — сгенерировать `equals`/`hashCode` по **всем полям** или по `id`. Оба варианта ломаются.

### Почему НЕ по сгенерированному id

`@GeneratedValue` id появляется **только после persist**. Если положить transient-сущность в `HashSet` до сохранения, её `hashCode` посчитается по `id == null`, а после persist id изменится → объект «потеряется» в set.

```java
Set<Product> set = new HashSet<>();
Product p = new Product("Молоко");   // id == null
set.add(p);                          // hashCode по id=null
em.persist(p);                       // id стал = 1 → hashCode изменился!
set.contains(p);                     // ❌ false — объект «потерян» в корзине set
```

### Правильно: бизнес-ключ (business/natural key)

`equals`/`hashCode` — по **неизменному бизнес-признаку** (артикул, email, номер заказа), а не по id:

```java
@Entity
class Product {
    @Id @GeneratedValue Long id;
    private String sku;     // артикул — бизнес-ключ, задаётся при создании и не меняется

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return sku != null && sku.equals(other.sku);
    }
    @Override public int hashCode() {
        return sku != null ? sku.hashCode() : 0;   // стабилен до и после persist
    }
}
```

| Подход | Проблема |
|--------|----------|
| по всем полям | меняется при `setX` → объект «прыгает» в set |
| по сгенерированному `id` | id == null до persist → ломается в `HashSet` |
| **по бизнес-ключу** ✅ | стабилен, если ключ неизменный и уникальный |

> Если стабильного бизнес-ключа нет, компромисс — присваивать `UUID` в конструкторе и считать `equals` по нему (id-суррогат, известный до persist).

---

## @Embeddable — value object

Value object не имеет своего id и живёт **внутри** сущности (его поля — колонки той же таблицы). Идеален для `Money`, `Address`, `Period`.

```java
@Embeddable
class Money {
    private long amount;       // в копейках
    private String currency;
    protected Money() {}
    public Money(long amount, String currency) { this.amount = amount; this.currency = currency; }
    // equals/hashCode ПО ЗНАЧЕНИЮ (value object — равны, если равны все поля)
}

@Entity
class Product {
    @Id @GeneratedValue Long id;
    @Embedded
    private Money price;       // колонки amount, currency в таблице products
}
```

```
   таблица products:
   ┌────┬────────┬─────────┬──────────┐
   │ id │ name   │ amount  │ currency │   ← поля Money «вплавлены» в строку
   └────┴────────┴─────────┴──────────┘
```

> Value object **равен по значению**: два `Money(10000,"RUB")` равны. Сущность — **по идентичности** (бизнес-ключ). Это разная семантика равенства.

---

## @AttributeOverride — одно value object дважды

Если встраиваем один тип несколько раз (адрес доставки и адрес выставления счёта), колонки конфликтуют — переименуем:

```java
@Embedded
@AttributeOverrides({
    @AttributeOverride(name = "city",   column = @Column(name = "ship_city")),
    @AttributeOverride(name = "street", column = @Column(name = "ship_street"))
})
private Address shippingAddress;

@Embedded
@AttributeOverrides({
    @AttributeOverride(name = "city",   column = @Column(name = "bill_city")),
    @AttributeOverride(name = "street", column = @Column(name = "bill_street"))
})
private Address billingAddress;
```

---

## Составной ключ: @EmbeddedId

Иногда первичный ключ — комбинация полей (строка заказа = заказ + товар):

```java
@Embeddable
class OrderLineId implements java.io.Serializable {     // ОБЯЗАН быть Serializable + equals/hashCode
    private Long orderId;
    private Long productId;
    // equals/hashCode по обоим полям
}

@Entity
class OrderLine {
    @EmbeddedId
    private OrderLineId id;
    private int qty;
}
```

| Способ | Как |
|--------|-----|
| `@EmbeddedId` | ключ — отдельный `@Embeddable`-класс (рекомендуется) |
| `@IdClass` | поля-ключи прямо в сущности + класс-дублёр ключа |

> Класс составного ключа **обязан** реализовать `Serializable` и корректные `equals`/`hashCode` — Hibernate использует их для идентичности.

---

## @NaturalId — естественный ключ Hibernate

Если у сущности есть уникальный бизнес-идентификатор (sku, ISBN, email), пометьте его `@NaturalId` — Hibernate даст быстрый поиск и кэширование по нему:

```java
@Entity
class Product {
    @Id @GeneratedValue Long id;          // суррогатный PK
    @NaturalId(mutable = false)
    private String sku;                    // естественный ключ
}

// поиск (Hibernate Session API):
Product p = session.bySimpleNaturalId(Product.class).load("SKU-123");
```

> Хорошая практика: суррогатный `@Id` (для FK и производительности) **плюс** `@NaturalId` (бизнес-смысл, уникальность). `equals`/`hashCode` — по natural id.

---

## Стратегии генерации id

| `GenerationType` | Как | Замечание |
|------------------|-----|-----------|
| `IDENTITY` | авто-инкремент БД (`AUTO_INCREMENT`/`IDENTITY`) | id известен только после INSERT; мешает batch-insert |
| `SEQUENCE` | последовательность БД | предпочтительно (Postgres/Oracle); поддерживает пулинг id |
| `TABLE` | отдельная таблица-счётчик | переносимо, но медленно — избегать |
| `AUTO` | выбирает провайдер | для прода лучше задавать явно |
| UUID | `@GeneratedValue` + `UUID` тип | id известен до INSERT; крупнее int |

> Для PostgreSQL обычно `SEQUENCE` (с `@SequenceGenerator`). `IDENTITY` прост, но отключает JDBC-батчинг вставок ([модуль 91](../m91_hibernate_deep_dive_performance/theory.md)).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Объект «теряется» в `HashSet` | `hashCode` по generated id (меняется при persist) | бизнес-ключ / UUID в конструкторе |
| `equals` по всем полям | объект «прыгает» в коллекции при изменении | бизнес-ключ |
| Конфликт колонок при двойном `@Embedded` | одинаковые имена | `@AttributeOverride` |
| `@EmbeddedId` не работает | ключ не `Serializable` / нет equals/hashCode | реализовать оба |
| Value object сравнивается по ссылке | не переопределён equals | equals/hashCode по значению |
| `IDENTITY` тормозит вставки пачкой | стратегия отключает batching | `SEQUENCE` для bulk-insert |

---

## Дополнительные источники

- [Vlad Mihalcea — How to implement equals and hashCode using the JPA entity identifier](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/).
- [Hibernate ORM: Embeddable & NaturalId](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#embeddables).
- [модуль 78](../m78_spring_data_jpa_entity/theory.md) — маппинг полей и `@Embeddable` в Spring Data.
- [модуль 11](../m11_objects_constructors/theory.md) — equals/hashCode в чистой Java.

## Что дальше

В [модуле 89](../m89_hibernate_deep_dive_inheritance/theory.md) — наследование сущностей (`SINGLE_TABLE`/`JOINED`/`TABLE_PER_CLASS`), `@MappedSuperclass`, `@DynamicUpdate` и полиморфные запросы.
