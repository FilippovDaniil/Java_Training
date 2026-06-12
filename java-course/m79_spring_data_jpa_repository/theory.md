# Модуль 79. Репозитории: derived queries, Page, Slice

Сущности готовы ([модуль 78](../m78_spring_data_jpa_entity/theory.md)). Теперь — **репозитории**: как Spring Data из имени метода генерирует SQL-запрос, как возвращать страницы и сортировать. Это «рабочая лошадка» слоя данных.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-data-jpa`, `com.h2database:h2`. Проект — `shop-data-jpa`.

---

## Иерархия репозиториев

```
   Repository<T,ID>               маркер (ничего не даёт)
        |
   CrudRepository<T,ID>           save, findById, findAll, delete, count, existsById
        |
   PagingAndSortingRepository     + findAll(Pageable), findAll(Sort)
        |
   JpaRepository<T,ID>            + flush, saveAll, deleteAllInBatch, findAll → List
```

На практике почти всегда наследуют **`JpaRepository`** — он включает всё.

```java
public interface ProductRepository extends JpaRepository<Product, Long> { }
```

| Готовый метод | SQL |
|---------------|-----|
| `save(e)` / `saveAll(list)` | INSERT/UPDATE |
| `findById(id)` → `Optional<T>` | SELECT по ключу |
| `findAll()` / `findAll(Sort)` | SELECT всех |
| `count()` | SELECT COUNT |
| `existsById(id)` | SELECT 1 |
| `deleteById(id)` / `delete(e)` | DELETE |

---

## Derived queries — запросы из имени метода

Spring Data **парсит имя метода** и генерирует запрос. Не нужно писать ни SQL, ни тело.

```java
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(long price);
    List<Product> findByCategoryAndPriceGreaterThan(String category, long price);
    List<Product> findByNameContainingIgnoreCase(String part);
    List<Product> findByCategoryOrderByPriceDesc(String category);
    Optional<Product> findFirstByOrderByPriceDesc();   // самый дорогой
}
```

Структура имени:

```
   find    By    Name    And    PriceLessThan    OrderBy    PriceDesc
   ----    --    ----    ---    -------------    -------    ---------
   глагол       поле    логика   поле+условие    сортировка   поле+напр
```

| Ключевое слово | Условие |
|----------------|---------|
| `findBy` / `getBy` / `readBy` | SELECT |
| `And` / `Or` | логическая связка |
| `Between` | диапазон |
| `LessThan` / `GreaterThan` / `...Equal` | сравнение |
| `Like` / `Containing` / `StartingWith` | подстрока |
| `IgnoreCase` | без учёта регистра |
| `In` | принадлежность списку |
| `IsNull` / `IsNotNull` | проверка null |
| `OrderBy...Asc/Desc` | сортировка |
| `True` / `False` | булево поле |

---

## Производные count / exists / delete

```java
long countByCategory(String category);
boolean existsBySku(String sku);
void deleteByStatus(Status status);     // требует @Transactional на вызывающем уровне
List<Product> findTop3ByOrderByPriceDesc();   // топ-3 дорогих
```

---

## Возврат Optional vs значение vs список

```java
Optional<Product> findBySku(String sku);   // 0 или 1 — Optional (рекомендуется)
Product findBySku(String sku);              // вернёт null, если нет (хуже)
List<Product> findByCategory(String cat);   // 0..N — список (никогда не null, пустой)
```

> Для «ноль или один» используйте `Optional` — это явно говорит вызывающему, что результата может не быть.

---

## Сортировка: Sort

```java
List<Product> products = repo.findAll(Sort.by("price").descending());
List<Product> byCat = repo.findByCategory("Книги", Sort.by("name").ascending());
```

```java
Sort sort = Sort.by(Sort.Direction.DESC, "price").and(Sort.by("name"));
```

---

## Пагинация: Pageable и Page

```java
import org.springframework.data.domain.*;

Page<Product> page = repo.findAll(PageRequest.of(0, 20, Sort.by("price").descending()));

page.getContent();        // список на странице
page.getTotalElements();  // всего записей (делается отдельный COUNT)
page.getTotalPages();
page.getNumber();         // текущая страница
page.hasNext();
```

Derived-метод тоже умеет в пагинацию:

```java
Page<Product> findByCategory(String category, Pageable pageable);
```

| Тип | Содержит | COUNT-запрос |
|-----|----------|:------------:|
| `List<T>` | все/срез | нет |
| `Slice<T>` | срез + «есть ли ещё» | **нет** (дешевле) |
| `Page<T>` | срез + общее число + число страниц | **да** |

> `Slice` дешевле `Page` (без `COUNT`). Берите `Page`, только когда реально нужно общее число/число страниц.

---

## Где взять Pageable в контроллере

Spring сам соберёт `Pageable` из query-параметров `?page=&size=&sort=`:

```java
@GetMapping("/api/products")
public Page<ProductDto> list(Pageable pageable) {
    return service.findAll(pageable);   // ?page=0&size=20&sort=price,desc
}
```

(см. [модуль 73](../m73_spring_rest_collections/theory.md) — там это было «превью», теперь — с настоящей БД).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Опечатка в имени метода → ошибка старта | поле не найдено по имени | имя должно точно соответствовать полю сущности |
| `findBy...` возвращает `null` вместо пустого списка | вернули не тот тип | для коллекций — `List` (пустой), не `null` |
| `deleteBy...` бросает «no transaction» | модифицирующий запрос вне транзакции | `@Transactional` на сервисном методе |
| Сложное имя метода нечитаемо | слишком много условий в имени | перейти на `@Query` ([модуль 80](../m80_spring_data_jpa_advanced/theory.md)) |
| `Page` медленный | лишний `COUNT` | взять `Slice`, если число не нужно |
| `findAll()` тянет всю таблицу | нет пагинации | использовать `Pageable` |

---

## Дополнительные источники

- [Spring Data JPA: Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html).
- [Spring Data: Paging and Sorting](https://docs.spring.io/spring-data/commons/reference/repositories/query-methods-details.html).
- [модуль 73](../m73_spring_rest_collections/theory.md) — пагинация на уровне REST.

## Что дальше

В [модуле 80](../m80_spring_data_jpa_advanced/theory.md) — когда derived-методов мало: JPQL, `@Query`, проекции, native queries и Specification.
