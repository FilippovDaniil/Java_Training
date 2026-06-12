# Модуль 53. Наследование в JPA/Hibernate

В объектной модели наследование — базовый инструмент. В реляционных БД таблицы плоские: в них нет наследования. JPA решает это противоречие через стратегии маппинга.

> Практика — маппинг иерархий через `@Inheritance`, полиморфные запросы (H2 + Hibernate).
> Зависимости: `org.hibernate.orm:hibernate-core:6.x`, `com.h2database:h2`.

---

## @MappedSuperclass — «без таблицы»

Самый простой вариант: общий суперкласс хранит повторяющиеся поля, но **сам не является @Entity** и не получает таблицы.

```java
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // геттеры / сеттеры
}

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;
    private String author;
}

@Entity
@Table(name = "electronics")
public class Electronics extends BaseEntity {
    private String brand;
    private String model;
}
```

```
BaseEntity (нет таблицы)
    +-- books(id, created_at, title, author)
    +-- electronics(id, created_at, brand, model)
```

**Когда использовать:** общие технические поля (`id`, `createdAt`, `updatedAt`) без необходимости полиморфных запросов.

---

## @Inheritance — три стратегии

Когда нужен полиморфизм (`EntityManager.find(Product.class, id)` возвращает любой подкласс), используют аннотацию `@Inheritance` на корневом `@Entity`.

### SINGLE_TABLE

Все классы иерархии — **одна таблица**. Строки разделяются столбцом-дискриминатором.

```java
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
}

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Product {
    private String author;
    private String isbn;
}

@Entity
@DiscriminatorValue("ELECTRONICS")
public class Electronics extends Product {
    private String brand;
    private Integer warrantyMonths;
}

@Entity
@DiscriminatorValue("CLOTHING")
public class Clothing extends Product {
    private String size;
    private String material;
}
```

**Как ложится в таблицу:**

```
products
+----+-----------------+----------+-----------+--------+------+------------------+------+----------+
| id |  product_type   |   name   |   price   | author | isbn |      brand       |warr. |  size    |
+----+-----------------+----------+-----------+--------+------+------------------+------+----------+
|  1 | BOOK            | Война... |  850.00   | Толстой| 978..| NULL             | NULL | NULL     |
|  2 | ELECTRONICS     | Ноутбук  | 75000.00  | NULL   | NULL | Lenovo           |  24  | NULL     |
|  3 | CLOTHING        | Футболка |   990.00  | NULL   | NULL | NULL             | NULL | XL       |
+----+-----------------+----------+-----------+--------+------+------------------+------+----------+
```

Поля подклассов, не относящиеся к текущей строке, хранятся как `NULL`.

### JOINED

Каждый класс — **своя таблица**. Таблица подкласса содержит только «свои» поля; первичный ключ одновременно является внешним ключом на родительскую таблицу.

```java
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
}

@Entity
@Table(name = "books")
public class Book extends Product {
    private String author;
    private String isbn;
}

@Entity
@Table(name = "electronics")
public class Electronics extends Product {
    private String brand;
    private Integer warrantyMonths;
}
```

**Как ложится в таблицы:**

```
products                      books                     electronics
+----+----------+----------+  +----+---------+-------+  +----+--------+-------+
| id |   name   |  price   |  | id | author  |  isbn |  | id | brand  | warr. |
+----+----------+----------+  +----+---------+-------+  +----+--------+-------+
|  1 | Война... |  850.00  |  |  1 | Толстой | 978.. |  |  2 | Lenovo |  24   |
|  2 | Ноутбук  |75000.00  |  +----+---------+-------+  +----+--------+-------+
+----+----------+----------+       ↑ id = FK на products.id
```

При чтении Hibernate делает `JOIN products ON books.id = products.id`.

### TABLE_PER_CLASS

Каждый **конкретный** класс — полная отдельная таблица со всеми полями (включая унаследованные). Абстрактный суперкласс таблицы не получает.

```java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id @GeneratedValue(strategy = GenerationType.TABLE) // IDENTITY не работает с TABLE_PER_CLASS
    private Long id;
    private String name;
    private BigDecimal price;
}

@Entity
@Table(name = "books")
public class Book extends Product {
    private String author;
    private String isbn;
}

@Entity
@Table(name = "electronics")
public class Electronics extends Product {
    private String brand;
    private Integer warrantyMonths;
}
```

**Как ложится в таблицы:**

```
books                                       electronics
+----+----------+-----------+---------+-------+   +----+----------+-----------+--------+-------+
| id |   name   |   price   | author  |  isbn |   | id |   name   |   price   | brand  | warr. |
+----+----------+-----------+---------+-------+   +----+----------+-----------+--------+-------+
|  1 | Война... |   850.00  | Толстой | 978.. |   |  2 | Ноутбук  | 75000.00  | Lenovo |  24   |
+----+----------+-----------+---------+-------+   +----+----------+-----------+--------+-------+

  name и price дублируются в каждой таблице!
```

Запрос `FROM Product` транслируется в `UNION ALL` по всем таблицам — медленно при большом числе подтипов.

---

## Полиморфные запросы

```java
// Возвращает ВСЕ Products (Book, Electronics, Clothing) — полиморфизм
List<Product> all = em.createQuery("FROM Product", Product.class).getResultList();

// Только книги
List<Book> books = em.createQuery("FROM Book", Book.class).getResultList();

// instanceof для разбора типа
for (Product p : all) {
    if (p instanceof Book b) {
        System.out.println("Книга: " + b.getAuthor());
    } else if (p instanceof Electronics e) {
        System.out.println("Электроника: " + e.getBrand());
    }
}
```

---

## Сравнение стратегий

| Критерий | SINGLE_TABLE | JOINED | TABLE_PER_CLASS |
|----------|-------------|--------|-----------------|
| Таблиц | 1 | 1 + N | N |
| NULL-поля | много | нет | нет |
| JOIN при чтении | нет | да (1 JOIN на уровень) | нет (UNION) |
| INSERT | 1 запрос | 2 запроса | 1 запрос |
| Полиморфный запрос | быстро | средне | медленно (UNION) |
| FK между наследниками | сложно | легко | невозможно |
| Нормализация | низкая | высокая | средняя |
| `@DiscriminatorColumn` | обязателен | опционален | не нужен |
| `IDENTITY` генератор | работает | работает | не работает |

**Практические рекомендации:**

- **SINGLE_TABLE** — когда иерархия небольшая, подтипы мало отличаются по полям, важна производительность чтения.
- **JOINED** — когда иерархия глубокая, поля подтипов сильно различаются, важна нормализация.
- **TABLE_PER_CLASS** — избегайте, если нужны полиморфные запросы; подходит только при полной изоляции подтипов.
- **@MappedSuperclass** — когда полиморфизм не нужен, только переиспользование полей.

---

## Подводные камни

| Ошибка | Объяснение | Решение |
|--------|-----------|---------|
| `NOT NULL` на поле подкласса при SINGLE_TABLE | Hibernate не может гарантировать NOT NULL для поля одного подтипа | Убрать `nullable = false` или перейти на JOINED |
| `IDENTITY` при TABLE_PER_CLASS | БД не может гарантировать уникальность id между таблицами | Использовать `GenerationType.TABLE` или `SEQUENCE` |
| `@DiscriminatorColumn` без `@DiscriminatorValue` | Hibernate использует полное имя класса как дискриминатор | Всегда явно указывайте `@DiscriminatorValue` |
| Полиморфный запрос с TABLE_PER_CLASS на большой БД | Генерируется `UNION ALL` по всем таблицам | Переходите на SINGLE_TABLE или JOINED |
| Нет `@Inheritance` на корневом `@Entity` | По умолчанию применяется SINGLE_TABLE — может быть сюрпризом | Всегда явно указывайте стратегию |
| `@MappedSuperclass` и `EntityManager.find()` | Нельзя делать find по суперклассу — он не Entity | Использовать `@Entity` + одну из стратегий |

---

## Перекрёстные ссылки

- Предыдущий модуль: [Модуль 52 — Связи в Hibernate (relationships)](../m52_hibernate_relationships/theory.md)
- Основы Hibernate: [Модуль 51](../m51_hibernate_orm/theory.md)
- Проектирование БД: [Модуль 48](../m48_database_design/theory.md)

## Дополнительные источники

- Документация Hibernate ORM: https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance
- «Java Persistence with Hibernate» (Christian Bauer, Gavin King, Gary Gregory).
- Спецификация JPA 3.x, раздел 2.12 (Inheritance Mapping).

## Что дальше

В [модуле 54](../m54_gradle_build_tools/theory.md) — система сборки Gradle: управление зависимостями, многомодульные проекты, плагины.
