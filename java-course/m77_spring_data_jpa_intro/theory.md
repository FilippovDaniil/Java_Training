# Модуль 77. Введение в Spring Data JPA

REST-блок ([модули 67–76](../module-67-spring-rest-http-backend/theory.md)) научил отдавать данные. Но данные нужно где-то **хранить**. Этот блок (77–84) — про слой доступа к данным в Spring. Начинаем со второго сквозного проекта — **`shop-data-jpa`** (товары, категории, заказы; БД — H2).

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Bare-`javac` НЕ компилируется — запускайте в IDE/Gradle.

---

## Эволюция доступа к данным

```
   JDBC ──────────► JPA ──────────► Hibernate ──────────► Spring Data JPA
   (модуль 49-50)   (стандарт)      (реализация JPA)      (надстройка Spring)

   ручной SQL,      аннотации,       ORM-движок,           репозитории «из
   ResultSet,       EntityManager,   кэш, ленивая           воздуха», derived
   много кода       объекты≈таблицы  загрузка               queries, пагинация
```

| Уровень | Что делаете вы | Что делает он |
|---------|----------------|---------------|
| **JDBC** ([49](../module-49-jdbc-1/theory.md)) | пишете SQL, разбираете `ResultSet` вручную | только соединение и выполнение SQL |
| **JPA** | размечаете классы аннотациями | стандарт API (спецификация) |
| **Hibernate** | то же + настройки | генерирует SQL, ORM, кэш, грязная проверка |
| **Spring Data JPA** | объявляете **интерфейс** репозитория | сам реализует CRUD и запросы |

Чем выше уровень — тем меньше шаблонного кода.

---

## Что такое ORM

**ORM** (Object-Relational Mapping) — отображение объектов на таблицы:

```
   Java-объект Product            Таблица products
   ────────────────────          ─────────────────────
   class Product {               | id | name   | price |
     Long id;          ◄────────►|----|--------|-------|
     String name;                | 1  | Кофе   | 500   |
     long price;                 | 2  | Чай    | 300   |
   }
```

Вы работаете с **объектами**, ORM переводит это в SQL: `save(product)` → `INSERT`, изменение поля → `UPDATE`, `findById` → `SELECT`.

---

## Зависимости и настройка

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'      // встроенная БД в памяти
}
```

`application.properties`:

```properties
# H2 in-memory
spring.datasource.url=jdbc:h2:mem:shop
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=create-drop   # создать схему по сущностям при старте
spring.jpa.show-sql=true                     # печатать SQL в консоль
spring.jpa.properties.hibernate.format_sql=true

# консоль H2 (http://localhost:8080/h2-console)
spring.h2.console.enabled=true
```

| `ddl-auto` | Поведение |
|------------|-----------|
| `none` | ничего не делать со схемой |
| `validate` | проверить соответствие схемы сущностям |
| `update` | дополнить схему (не удаляя) |
| `create` | создать заново при старте |
| `create-drop` | создать при старте, удалить при остановке (удобно для тестов) |

> В production схемой управляет **Flyway/Liquibase** ([модуль 84](../module-84-spring-data-jpa-migrations/theory.md)), а `ddl-auto=validate`. `create`/`update` — только для разработки.

---

## Первая сущность

```java
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long price;

    protected Product() {}          // JPA требует конструктор без аргументов

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }
    // геттеры/сеттеры...
}
```

| Аннотация | Назначение |
|-----------|-----------|
| `@Entity` | класс — управляемая сущность (детально — [модуль 78](../module-78-spring-data-jpa-entity/theory.md)) |
| `@Id` | первичный ключ |
| `@GeneratedValue` | автогенерация ключа |
| `@Table` | имя таблицы (по умолчанию = имя класса) |

---

## Первый репозиторий

Самое «волшебное» в Spring Data: объявляете **интерфейс**, реализацию пишет Spring.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // CRUD уже есть! save, findById, findAll, deleteById, count ...
}
```

`JpaRepository<Product, Long>` — сущность `Product`, тип ключа `Long`. Никакой реализации писать не нужно.

```java
@Component
class SeedRunner implements CommandLineRunner {
    private final ProductRepository repo;
    SeedRunner(ProductRepository repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        repo.save(new Product("Кофе", 500));     // INSERT
        repo.save(new Product("Чай", 300));
        System.out.println("Всего товаров: " + repo.count());   // SELECT COUNT
        repo.findAll().forEach(p -> System.out.println(p.getName()));
    }
}
```

```
   repo.save(product)   ──► INSERT INTO products ...
   repo.findById(1L)    ──► SELECT * FROM products WHERE id = 1
   repo.findAll()       ──► SELECT * FROM products
   repo.count()         ──► SELECT COUNT(*) FROM products
   repo.deleteById(1L)  ──► DELETE FROM products WHERE id = 1
```

Ни строчки SQL — а CRUD работает.

---

## Под капотом: EntityManager и persistence context

Spring Data использует **`EntityManager`** — центральный объект JPA. Его рабочая область — **persistence context** (контекст постоянства), кэш управляемых объектов в рамках транзакции (детально — [модуль 81](../module-81-spring-data-jpa-transactions/theory.md) и [модуль 85](../module-85-hibernate-deep-dive-lifecycle/theory.md)).

```java
@PersistenceContext
EntityManager em;

em.persist(product);          // сделать объект управляемым (INSERT при flush)
Product p = em.find(Product.class, 1L);   // найти по id
```

Spring Data — обёртка над этим: вы почти никогда не трогаете `EntityManager` напрямую, но полезно знать, что он внутри.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `Table not found` | `ddl-auto=none`, схема не создана | `create-drop`/`update` для dev или миграции |
| `No qualifying bean ... EntityManagerFactory` | нет `starter-data-jpa` / нет драйвера БД | добавить зависимости |
| Сущность без конструктора без аргументов | JPA создаёт объект рефлексией | добавить `protected`/`public` конструктор без параметров |
| `@Entity` без `@Id` | у сущности нет первичного ключа | добавить `@Id` |
| Данные «исчезают» после рестарта | `h2:mem` — в памяти | для сохранения — файловая БД или другая СУБД |
| Репозиторий не находится | интерфейс вне пакета сканирования | держать в подпакете главного класса |

---

## Дополнительные источники

- [Spring Data JPA — Reference](https://docs.spring.io/spring-data/jpa/reference/index.html).
- [Jakarta Persistence (JPA) Spec](https://jakarta.ee/specifications/persistence/).
- [модуль 49](../module-49-jdbc-1/theory.md), [модуль 50](../module-50-jdbc-2/theory.md) — JDBC (уровень ниже).
- [модуль 51](../module-51-hibernate-orm/theory.md) — Hibernate ORM (без Spring Data).

## Что дальше

В [модуле 78](../module-78-spring-data-jpa-entity/theory.md) — сущности глубже: генерация ключей, `@Column`, перечисления, `@Embeddable`, составные ключи.
