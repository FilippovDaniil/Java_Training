# Модуль 80. Продвинутые запросы: JPQL, @Query, проекции, Specification

Derived-методы ([модуль 79](../m79_spring_data_jpa_repository/theory.md)) хороши для простых случаев, но имя `findByCategoryAndPriceGreaterThanAndAvailableTrueOrderByPriceDesc` уже нечитаемо. Для сложных запросов есть **JPQL**, аннотация **`@Query`**, **проекции** и динамические **Specification**.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Проект — `shop-data-jpa`.

---

## JPQL — запросы по объектам, не по таблицам

**JPQL** (Jakarta Persistence Query Language) похож на SQL, но оперирует **сущностями и их полями**, а не таблицами и колонками:

```
   SQL:   SELECT * FROM products WHERE price > 1000
   JPQL:  SELECT p FROM Product p WHERE p.price > 1000
                        ▲ имя класса      ▲ имя поля
```

Hibernate сам переведёт JPQL в SQL под конкретную БД.

---

## @Query — явный запрос

Когда derived-метода мало, пишут запрос вручную:

```java
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :min AND p.category = :cat")
    List<Product> expensiveInCategory(@Param("min") long min, @Param("cat") String category);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:part%")
    List<Product> searchByName(@Param("part") String part);
}
```

| Способ передачи параметра | Синтаксис |
|---------------------------|-----------|
| **именованный** (рекомендуется) | `:min` + `@Param("min")` |
| позиционный | `?1`, `?2` |

---

## Native query — «сырой» SQL

Когда нужны возможности конкретной БД (оконные функции, спец-синтаксис):

```java
@Query(value = "SELECT * FROM products WHERE price > :min", nativeQuery = true)
List<Product> nativeExpensive(@Param("min") long min);
```

| | JPQL | Native SQL |
|-|------|------------|
| Оперирует | сущностями/полями | таблицами/колонками |
| Переносимость между БД | высокая | низкая |
| Доступ к спец-функциям БД | ограничен | полный |
| `nativeQuery` | `false` (по умолч.) | `true` |

---

## @Modifying — изменяющие запросы

`@Query` по умолчанию только читает. Для UPDATE/DELETE добавьте `@Modifying` (и транзакцию):

```java
@Modifying
@Query("UPDATE Product p SET p.price = p.price * :factor WHERE p.category = :cat")
int raisePrices(@Param("factor") double factor, @Param("cat") String category);
// возвращает число изменённых строк; вызывать внутри @Transactional
```

> Bulk-update обходит persistence context (см. [модуль 91](../m91_hibernate_deep_dive_performance/theory.md)) — уже загруженные объекты в кэше не обновятся. После такого запроса часто нужен `flush`/`clear`.

---

## Проекции — возвращать только нужное

Часто не нужна вся сущность — достаточно пары полей. **Проекция** возвращает «срез».

### Интерфейсная проекция

```java
public interface ProductView {
    String getName();
    long getPrice();
}

@Query("SELECT p.name AS name, p.price AS price FROM Product p WHERE p.category = :cat")
List<ProductView> findViewsByCategory(@Param("cat") String category);
// или просто derived: List<ProductView> findByCategory(String cat);
```

Spring сам создаст прокси, реализующий интерфейс. Выбираются только указанные колонки — быстрее, чем тянуть всю сущность.

### DTO-проекция (class-based) через конструктор

```java
public record ProductDto(String name, long price) {}

@Query("SELECT new com.example.ProductDto(p.name, p.price) FROM Product p")
List<ProductDto> findAllDtos();
```

| Проекция | Плюс | Минус |
|----------|------|-------|
| интерфейсная | минимум кода | только геттеры |
| DTO (конструктор) | полноценный объект | нужно полное имя класса в JPQL |

---

## Specification — динамические запросы (Criteria API)

Когда набор условий **неизвестен заранее** (фильтр с любым сочетанием полей), derived/@Query неудобны. **Specification** строит запрос программно через Criteria API.

```java
public interface ProductRepository extends JpaRepository<Product, Long>,
                                            JpaSpecificationExecutor<Product> { }
```

```java
public class ProductSpecs {
    public static Specification<Product> hasCategory(String category) {
        return (root, query, cb) ->
            category == null ? null : cb.equal(root.get("category"), category);
    }
    public static Specification<Product> priceAtLeast(Long min) {
        return (root, query, cb) ->
            min == null ? null : cb.greaterThanOrEqualTo(root.get("price"), min);
    }
}
```

```java
// собираем динамически — каждое условие применяется, только если параметр задан
Specification<Product> spec = Specification
        .where(ProductSpecs.hasCategory(category))
        .and(ProductSpecs.priceAtLeast(minPrice));

List<Product> result = repo.findAll(spec);
```

```
   category=null, min=1000  →  WHERE price >= 1000
   category="Книги", min=null → WHERE category = 'Книги'
   оба заданы               →  WHERE category = 'Книги' AND price >= 1000
```

`null` из спецификации означает «условие не применять» — удобно для опциональных фильтров.

---

## Что выбрать

```
   Простой фильтр (1-2 поля)          → derived query (findBy...)
   Фиксированный сложный запрос        → @Query (JPQL)
   Нужны спец-функции БД               → @Query (native)
   Только часть полей                  → проекция (interface/DTO)
   Изменение многих строк              → @Modifying
   Динамический фильтр (любой набор)   → Specification
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@Modifying`-запрос «ничего не делает» | нет транзакции | `@Transactional` на сервисе |
| Объекты в кэше не обновились после bulk-update | bulk обходит persistence context | `flush`/`clear` или перечитать |
| DTO-проекция не создаётся | неполное имя класса в `new ...` | указать FQN: `new com.example.Dto(...)` |
| JPQL-ошибка «unexpected token» | пишете имена таблиц/колонок | JPQL — это сущности/поля, не таблицы |
| Native query ломается на другой БД | спец-синтаксис | предпочитать JPQL, где можно |
| Specification всегда пустой результат | условие не `null`, но неверно | возвращать `null` для «не применять», иначе `cb....` |

---

## Дополнительные источники

- [Spring Data JPA: @Query](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query).
- [Spring Data JPA: Projections](https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html).
- [Spring Data JPA: Specifications](https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html).
- [модуль 87](../m87_hibernate_deep_dive_querying/theory.md) — HQL, Criteria API глубже.

## Что дальше

В [модуле 81](../m81_spring_data_jpa_transactions/theory.md) — транзакции: `@Transactional`, propagation, isolation и persistence context.
