# Модуль 92. Hibernate Deep Dive: диагностика, анти-паттерны, тестирование, финальный аудит

Финальный модуль Hibernate-трека и всего блока persistence. Здесь — как **видеть**, что Hibernate делает на самом деле (логи, `Statistics`), как **ловить N+1 в тестах**, каталог типовых анти-паттернов и **чек-лист аудита** перед продакшеном.

> Практика — задачи в `practice/`. Зависимости: `org.hibernate.orm:hibernate-core`, `com.h2database:h2` + `META-INF/persistence.xml` ("shop-pu"). Задача 01 — конфигурационный носитель (без зависимостей). Проект — `shop-data-jpa`.

---

## Видеть SQL: логирование

```properties
hibernate.show_sql=true            # печатать SQL в консоль
hibernate.format_sql=true          # форматировать (читабельно)
hibernate.use_sql_comments=true    # комментарий-подсказка, какой запрос/операция
```

`show_sql` показывает SQL, но **с `?` вместо значений**. Чтобы увидеть **связанные параметры** — TRACE-логгер JDBC-биндинга (logback):

```xml
<!-- logback.xml -->
<logger name="org.hibernate.SQL" level="DEBUG"/>
<logger name="org.hibernate.orm.jdbc.bind" level="TRACE"/>   <!-- значения параметров -->
```

```
   show_sql           → select * from products where price > ?
   + jdbc.bind TRACE  → binding parameter [1] as [INTEGER] - [100]
```

> ⚠️ TRACE-логи параметров — только для отладки: они шумные и могут раскрыть данные в логах. В проде выключать.

---

## Statistics API — измеримая диагностика

```properties
hibernate.generate_statistics=true
```

```java
import org.hibernate.stat.Statistics;

Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
st.clear();                          // обнулить перед измерением
// ... выполнить операции ...
System.out.println(st.getPrepareStatementCount());   // сколько SQL-операторов
System.out.println(st.getEntityLoadCount());          // сколько сущностей загружено
System.out.println(st.getQueryExecutionCount());      // сколько запросов выполнено
System.out.println(st.getSecondLevelCacheHitCount()); // попаданий в L2-кэш
```

| Счётчик | О чём |
|---------|-------|
| `getPrepareStatementCount()` | число подготовленных SQL — главный индикатор N+1 |
| `getEntityLoadCount()` | сколько сущностей материализовано |
| `getQueryExecutionCount()` | число выполненных HQL/Criteria-запросов |
| `getSecondLevelCacheHitCount/MissCount` | эффективность L2-кэша |

---

## Ловля N+1 в тестах

Анти-паттерн N+1 нельзя «увидеть глазами» в большой системе — его ловят **тестом на число запросов**:

```java
Statistics st = sessionFactory.getStatistics();
st.clear();

List<Category> cats = repo.findAllWithProducts();      // ожидаем ОДИН запрос
cats.forEach(c -> c.getProducts().size());

assertThat(st.getPrepareStatementCount()).isEqualTo(1);  // если N+1 — будет 1+N, тест упадёт
```

```
   до фикса (lazy в цикле):   getPrepareStatementCount() == 1 + N   → тест КРАСНЫЙ
   после JOIN FETCH:           getPrepareStatementCount() == 1       → тест ЗЕЛЁНЫЙ
```

> Такой тест — «предохранитель»: если кто-то случайно сломает fetch-стратегию, число запросов вырастет и тест это поймает.

---

## Каталог анти-паттернов (повторение всего трека)

| Анти-паттерн | Симптом | Где разбирали | Лечение |
|--------------|---------|---------------|---------|
| **N+1** | 1+N запросов | [86](../m86_hibernate_deep_dive_fetching/theory.md), [82](../m82_spring_data_jpa_lazy_loading/theory.md) | JOIN FETCH / EntityGraph / batch / DTO |
| `open-in-view=true` | N+1 в слое view, lazy «магически» работает | [82](../m82_spring_data_jpa_lazy_loading/theory.md) | `open-in-view=false`, грузить явно |
| EAGER везде | лишние JOIN/запросы всегда | [82](../m82_spring_data_jpa_lazy_loading/theory.md), [86](../m86_hibernate_deep_dive_fetching/theory.md) | LAZY + точечный fetch |
| `equals/hashCode` по generated id | объект «теряется» в `Set` | [88](../m88_hibernate_deep_dive_modeling/theory.md) | бизнес-ключ |
| `ddl-auto=update` в проде | неконтролируемая схема | [84](../m84_spring_data_jpa_migrations/theory.md) | Flyway + `validate` |
| нет границы транзакции | detached, `LazyInitializationException` | [81](../m81_spring_data_jpa_transactions/theory.md), [85](../m85_hibernate_deep_dive_lifecycle/theory.md) | `@Transactional` на сервисе |
| `save()` «на всякий случай» | лишний код | [81](../m81_spring_data_jpa_transactions/theory.md), [85](../m85_hibernate_deep_dive_lifecycle/theory.md) | dirty checking для managed |
| построчные правки вместо bulk | тысячи UPDATE | [91](../m91_hibernate_deep_dive_performance/theory.md) | bulk `update`/`delete` |
| `IDENTITY` для bulk-insert | нет батчинга | [91](../m91_hibernate_deep_dive_performance/theory.md) | `SEQUENCE` + `batch_size` |
| правка применённой миграции | checksum mismatch | [84](../m84_spring_data_jpa_migrations/theory.md) | новая версия `V{n+1}` |

---

## Чек-лист аудита перед продакшеном

```
   [ ] Все связи LAZY; жадная загрузка — точечно (JOIN FETCH / EntityGraph)
   [ ] Нет N+1 (проверено тестом на число запросов / Statistics)
   [ ] open-in-view выключен; данные грузятся в сервисном слое
   [ ] Границы транзакций — на сервисе (@Transactional); read-only для чтения
   [ ] Схема под Flyway; ddl-auto=validate (НЕ update)
   [ ] equals/hashCode по бизнес-ключу (или UUID), не по generated id
   [ ] @Version там, где возможна конкуренция; обработка конфликтов
   [ ] Массовые операции — bulk / batch / StatelessSession; flush+clear в циклах
   [ ] DTO-проекции для read-моделей (не тащить сущности во view)
   [ ] L2-кэш — только для справочников; query cache осознанно
   [ ] Логи SQL/параметров включены ТОЛЬКО в dev; в проде — выкл.
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| «Не вижу реальный SQL» | только `show_sql` (с `?`) | TRACE `org.hibernate.orm.jdbc.bind` |
| N+1 проскочил в прод | не было теста на запросы | тест на `getPrepareStatementCount()` |
| Statistics всегда 0 | не включён `generate_statistics` | включить в конфиге |
| Логи параметров в проде | забыли выключить TRACE | dev-only профиль логирования |
| «Оптимизировал», но не замерил | нет метрик | `Statistics` до/после |

---

## Дополнительные источники

- [Hibernate ORM: Statistics](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#statistics).
- [Vlad Mihalcea — How to detect the N+1 query problem during testing](https://vladmihalcea.com/how-to-detect-the-n-plus-one-query-problem-during-testing/).
- [модуль 86](../m86_hibernate_deep_dive_fetching/theory.md) и [91](../m91_hibernate_deep_dive_performance/theory.md) — приёмы, которые здесь проверяем.

## Что дальше

Это **завершает Часть 3 и весь Hibernate/persistence-трек** (77–92). Вы прошли путь JDBC → JPA → Hibernate → Spring Data JPA и обратно «под капот»: жизненный цикл, fetching, запросы, моделирование, наследование, блокировки, производительность и диагностика. Дальнейшее развитие — практика на сквозном проекте `shop-data-jpa` и перенос чек-листа аудита в реальные сервисы. См. [README](../README.md) для карты курса.
