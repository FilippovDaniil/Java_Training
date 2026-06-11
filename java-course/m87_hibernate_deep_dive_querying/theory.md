# Модуль 87. Hibernate Deep Dive: HQL, Criteria API, native SQL, owning side, каскады

Spring Data выводил методы из имён. Под капотом — три языка запросов: **HQL/JPQL** (объектный), **Criteria API** (типобезопасный, динамический) и **native SQL** (сырой). Плюс две темы, без которых связи ломаются: **владелец связи** (owning side) и **каскады**.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Работаем через `EntityManager`. Проект — `shop-data-jpa`.

---

## HQL / JPQL

Запрос по **сущностям и их полям**, а не по таблицам и колонкам.

```java
List<Product> list = em.createQuery(
        "select p from Product p where p.category = :cat and p.price > :min " +
        "order by p.price desc", Product.class)
    .setParameter("cat", "Еда")
    .setParameter("min", 100)
    .getResultList();

long count = em.createQuery("select count(p) from Product p", Long.class).getSingleResult();
```

| Приём | Синтаксис |
|-------|-----------|
| именованный параметр | `:name` + `.setParameter("name", v)` |
| позиционный | `?1` + `.setParameter(1, v)` |
| один результат | `.getSingleResult()` (бросает, если 0 или >1) |
| пагинация | `.setFirstResult(offset).setMaxResults(limit)` |
| named query | `@NamedQuery` на сущности + `em.createNamedQuery("...")` |

### Bulk update/delete

```java
int updated = em.createQuery("update Product p set p.price = p.price * 1.1 where p.category = :c")
    .setParameter("c", "Еда").executeUpdate();   // один UPDATE по всем строкам
```

> ⚠️ Bulk-операции идут **мимо** persistence context: загруженные в контекст сущности не узнают об изменении. После bulk делайте `em.clear()` или `em.refresh()`, иначе получите устаревшие данные.

---

## Criteria API — типобезопасные динамические запросы

Когда условия собираются **в рантайме** (фильтры формы), конкатенация HQL-строк опасна. Criteria строит запрос объектами:

```java
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Product> cq = cb.createQuery(Product.class);
Root<Product> root = cq.from(Product.class);

List<Predicate> filters = new ArrayList<>();
if (category != null) filters.add(cb.equal(root.get("category"), category));
if (minPrice != null) filters.add(cb.gt(root.get("price"), minPrice));

cq.select(root).where(cb.and(filters.toArray(new Predicate[0]))).orderBy(cb.asc(root.get("name")));
List<Product> result = em.createQuery(cq).getResultList();
```

```
   CriteriaBuilder  → фабрика предикатов (equal, gt, like, and, or...)
   CriteriaQuery<T> → сам запрос (select/where/orderBy/groupBy)
   Root<T>          → корневая сущность (root.get("поле"))
   Predicate        → условие; собираем список и комбинируем and/or
```

| HQL | Criteria |
|-----|----------|
| читается как текст | многословно, но типобезопасно |
| статичные запросы | **динамические** условия (null → не добавляем предикат) |
| опечатка в поле → рантайм | опечатка ловится позже, но структура проверяется |

---

## Native SQL

Когда нужны фичи конкретной СУБД (оконные функции, `INSERT ... ON CONFLICT`, спец-типы):

```java
// в сущности:
List<Product> products = em.createNativeQuery(
        "SELECT * FROM products WHERE price > :min", Product.class)   // маппинг в сущность
    .setParameter("min", 100).getResultList();

// скалярный результат:
Number total = (Number) em.createNativeQuery("SELECT count(*) FROM products").getSingleResult();
```

Для сложных результатов — `@SqlResultSetMapping`. Минус native SQL: теряется переносимость между СУБД и часть возможностей ORM.

---

## Owning side — кто владеет связью

В двунаправленной связи **одна** сторона — владелец (хранит внешний ключ). Hibernate синхронизирует FK **только по владельцу**.

```java
@Entity
class Category {
    @OneToMany(mappedBy = "category")     // ОБРАТНАЯ сторона (mappedBy = не владелец)
    private List<Product> products = new ArrayList<>();
}

@Entity
class Product {
    @ManyToOne @JoinColumn(name = "category_id")   // ВЛАДЕЛЕЦ (тут колонка-FK)
    private Category category;
}
```

```
   owning side  = сторона с @JoinColumn (обычно @ManyToOne) → пишет FK
   inverse side = сторона с mappedBy (@OneToMany)           → только читает
```

> **Ловушка:** добавили product в `category.getProducts()`, но НЕ выставили `product.setCategory(category)` → FK не запишется (Hibernate смотрит на владельца). Всегда синхронизируйте **обе** стороны хелпером:

```java
public void addProduct(Product p) {
    products.add(p);          // обратная сторона (для навигации в памяти)
    p.setCategory(this);      // ВЛАДЕЛЕЦ (для записи FK) — без этого FK = null
}
```

---

## Каскады и orphanRemoval

Каскад распространяет операции с родителя на детей:

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderLine> lines = new ArrayList<>();
```

| `CascadeType` | Распространяет |
|---------------|----------------|
| `PERSIST` | `persist` на детей (сохранил заказ → сохранились строки) |
| `MERGE` | `merge` |
| `REMOVE` | `remove` (удалил заказ → удалились строки) |
| `ALL` | все операции |
| `orphanRemoval = true` | удалить ребёнка при **удалении из коллекции** (`lines.remove(x)` → DELETE) |

> Разница `REMOVE` и `orphanRemoval`: `REMOVE` срабатывает при удалении **родителя**; `orphanRemoval` — при отвязывании ребёнка от родителя (даже если родитель жив).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| FK не записался | выставили только обратную сторону | синхронизировать владельца (`setCategory`) |
| Bulk update не виден в коде | идёт мимо контекста | `em.clear()`/`refresh()` после |
| `NonUniqueResultException` | `getSingleResult()` вернул >1 | уточнить запрос / `getResultList()` |
| Динамический HQL-конкат | дыры/опечатки | Criteria API |
| Native SQL не переносится | диалект СУБД | держать в HQL, native — точечно |
| Дети не сохранились | нет `cascade = PERSIST` | добавить каскад |
| «Осиротевший» ребёнок остался в БД | нет `orphanRemoval` | `orphanRemoval = true` |
| Удаление родителя → FK violation | нет `cascade = REMOVE` | каскад или ручное удаление детей |

---

## Дополнительные источники

- [Hibernate ORM: HQL and JPQL](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#hql).
- [Jakarta Persistence: Criteria API](https://jakarta.ee/specifications/persistence/3.1/).
- [Vlad Mihalcea — The best way to map a bidirectional association](https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/).
- [модуль 80](../m80_spring_data_jpa_advanced/theory.md) — те же `@Query`/Specification в Spring Data.

## Что дальше

В [модуле 88](../m88_hibernate_deep_dive_modeling/theory.md) — моделирование домена: правильные `equals`/`hashCode`, value objects (`@Embeddable`), составные ключи и стратегии идентификаторов.
