# Модуль 89. Hibernate Deep Dive: наследование сущностей, @DynamicUpdate, полиморфные запросы

Реляционные таблицы не знают про наследование, а доменная модель — знает (Payment → CardPayment/CashPayment). JPA предлагает три стратегии отображения иерархии классов на таблицы. Выбор влияет на схему, производительность и ограничения целостности.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Проект — `shop-data-jpa`.

---

## Три стратегии наследования

```java
@Inheritance(strategy = InheritanceType.XXX)
```

### 1. SINGLE_TABLE (по умолчанию)

Вся иерархия — **одна** таблица; тип строки различает **дискриминатор**.

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
abstract class Payment { @Id @GeneratedValue Long id; long amount; }

@Entity @DiscriminatorValue("CARD")
class CardPayment extends Payment { String cardNumber; }

@Entity @DiscriminatorValue("CASH")
class CashPayment extends Payment { String cashier; }
```

```
   таблица payments:
   ┌────┬────────┬──────────────┬─────────────┬─────────┐
   │ id │ amount │ payment_type │ card_number │ cashier │
   ├────┼────────┼──────────────┼─────────────┼─────────┤
   │  1 │  1000  │ CARD         │ 4111...     │  NULL   │
   │  2 │   500  │ CASH         │   NULL      │ Иванов  │
   └────┴────────┴──────────────┴─────────────┴─────────┘
```

✅ быстро (нет JOIN), полиморфные запросы дёшевы.
❌ колонки подклассов **обязаны быть nullable** — нельзя поставить `NOT NULL` на `card_number` (у CASH-строк он null). Целостность на уровне БД теряется.

### 2. JOINED

Базовая таблица + по таблице на подкласс; объект собирается **JOIN**'ом.

```java
@Inheritance(strategy = InheritanceType.JOINED)
```

```
   payments(id, amount)
        ├── card_payments(id → payments.id, card_number)
        └── cash_payments(id → payments.id, cashier)
   CardPayment = payments JOIN card_payments
```

✅ нормализовано, можно `NOT NULL` на колонках подклассов, нет «дыр».
❌ каждый доступ — JOIN (медленнее), полиморфные запросы дороже.

### 3. TABLE_PER_CLASS

По **полной** таблице на каждый конкретный класс (с дублированием колонок базового).

```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```

```
   card_payments(id, amount, card_number)   ← amount продублирован
   cash_payments(id, amount, cashier)        ← amount продублирован
```

✅ нет JOIN для конкретного типа.
❌ полиморфный запрос `from Payment` = `UNION` по всем таблицам (медленно); проблемы с генерацией id (нельзя IDENTITY). Применяют редко.

| Стратегия | Таблиц | Полиморфизм | NOT NULL подклассов | Когда |
|-----------|:------:|-------------|---------------------|-------|
| `SINGLE_TABLE` | 1 | быстрый | ❌ нельзя | мало полей, важна скорость (по умолчанию) |
| `JOINED` | 1+N | JOIN | ✅ можно | важна нормализация и целостность |
| `TABLE_PER_CLASS` | N | UNION | ✅ | редко; запросы по конкретному типу |

---

## @MappedSuperclass — НЕ наследование сущностей

`@MappedSuperclass` отдаёт **только поля и маппинг** наследникам, но **не является сущностью**: нет своей таблицы, нельзя запросить полиморфно, не может быть целью связи.

```java
@MappedSuperclass
abstract class Auditable {
    @Column(name = "created_at") Instant createdAt;   // колонка появится в КАЖДОЙ таблице-наследнике
}

@Entity class Product extends Auditable { ... }   // products.created_at
@Entity class Order   extends Auditable { ... }   // orders.created_at
```

| | `@MappedSuperclass` | `@Inheritance` (Entity) |
|---|---------------------|-------------------------|
| это сущность? | нет | да |
| своя таблица | нет (поля копируются вниз) | да (по стратегии) |
| `from Base` (полиморфно) | ❌ нельзя | ✅ можно |
| цель ассоциации | ❌ нельзя | ✅ можно |
| зачем | переиспользовать общие поля (аудит, id) | моделировать «is-a» иерархию |

> Аудит-поля из [модуля 84](../module-84-spring-data-jpa-migrations/theory.md) — классический `@MappedSuperclass`. Иерархию «Платёж → Карта/Наличные» — `@Inheritance`.

---

## @DynamicUpdate / @DynamicInsert

По умолчанию Hibernate генерирует UPDATE со **всеми** колонками (закэшированный SQL). `@DynamicUpdate` генерирует SQL только для **изменённых** колонок:

```java
@Entity
@org.hibernate.annotations.DynamicUpdate
class Product {
    @Id Long id; String name; int price; int stock;
}
// изменили только price → UPDATE products SET price=? WHERE id=?  (без name/stock)
```

Полезно для «широких» таблиц и при оптимистичной блокировке (меньше конфликтов по незатронутым полям). Минус — SQL генерируется на лету (нельзя закэшировать), что для частых однотипных апдейтов может быть медленнее. `@DynamicInsert` — аналогично для INSERT (пропускает null-колонки).

---

## Полиморфные запросы

Запрос по базовому типу возвращает **все** подтипы:

```java
List<Payment> all = em.createQuery("select p from Payment p", Payment.class).getResultList();
// вернёт и CardPayment, и CashPayment
```

Фильтрация по типу и приведение в HQL:

```java
// только карточные:
em.createQuery("select p from Payment p where type(p) = CardPayment", Payment.class);

// доступ к полю подкласса через treat():
em.createQuery("select p from Payment p where treat(p as CardPayment).cardNumber like '4%'", Payment.class);
```

| Конструкция | Смысл |
|-------------|-------|
| `type(p) = CardPayment` | фильтр по конкретному классу |
| `treat(p as CardPayment)` | привести к подтипу (доступ к его полям) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Нельзя `NOT NULL` на поле подкласса | `SINGLE_TABLE` (общая таблица) | `JOINED` или валидация в коде |
| Полиморфный запрос медленный | `TABLE_PER_CLASS` (UNION) | `SINGLE_TABLE`/`JOINED` |
| `from Base` ничего не возвращает | `Base` — `@MappedSuperclass`, не сущность | `@Inheritance` если нужен полиморфизм |
| Много JOIN на каждый select | `JOINED` для горячего пути | взвесить `SINGLE_TABLE` |
| UPDATE всех колонок | поведение по умолчанию | `@DynamicUpdate` (точечно) |
| `TABLE_PER_CLASS` + `IDENTITY` | стратегия несовместима | `SEQUENCE`/`TABLE` генератор |
| Дискриминатор не задан | забыли `@DiscriminatorValue` | задать значение каждому подклассу |

---

## Дополнительные источники

- [Hibernate ORM: Inheritance](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance).
- [Vlad Mihalcea — Inheritance strategies with JPA and Hibernate](https://vladmihalcea.com/inheritance-jpa-hibernate/).
- [модуль 53](../module-53-hibernate-inheritance/theory.md) — введение в наследование Hibernate (Часть 2).
- [модуль 88](../module-88-hibernate-deep-dive-modeling/theory.md) — `@MappedSuperclass` для общих полей.

## Что дальше

Это завершает **средний блок Hibernate Deep Dive (85–89)**. В следующем батче — [модуль 90](../module-90-hibernate-deep-dive-locking/theory.md): блокировки (оптимистичная/пессимистичная), soft delete и аудит истории через Envers. Затем производительность (91) и диагностика (92).
