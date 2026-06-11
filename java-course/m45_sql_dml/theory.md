# Модуль 45. Типы данных и DML: INSERT / SELECT / UPDATE / DELETE

DML (Data Manipulation Language) — команды для работы с **данными** внутри таблиц. Здесь разбираем их подробно.

> Практика — SQL-скрипты (H2/любая СУБД). `.java` несёт условие и текст-блок для вашего SQL.

## Типы данных (подробнее)

| Категория | Типы | Пример |
|-----------|------|--------|
| Целые | `INT`, `BIGINT`, `SMALLINT` | `age INT` |
| Дробные точные | `DECIMAL(p,s)`, `NUMERIC` | `price DECIMAL(10,2)` |
| Дробные | `FLOAT`, `DOUBLE` | научные расчёты |
| Строки | `CHAR(n)`, `VARCHAR(n)`, `TEXT` | `name VARCHAR(100)` |
| Дата/время | `DATE`, `TIME`, `TIMESTAMP` | `created TIMESTAMP` |
| Логика | `BOOLEAN` | `active BOOLEAN` |

> `DECIMAL(10,2)` — 10 значащих цифр, 2 после запятой. Для денег — только `DECIMAL`, не `FLOAT` (округления).

---

## INSERT — добавление

```sql
-- с указанием столбцов (рекомендуется)
INSERT INTO products (name, price, qty) VALUES ('Кофе', 350.00, 100);

-- несколько строк за раз
INSERT INTO products (name, price, qty) VALUES
    ('Чай', 200.00, 50),
    ('Сахар', 80.00, 200);

-- из другого запроса
INSERT INTO archive_products (name, price)
SELECT name, price FROM products WHERE qty = 0;
```

---

## SELECT — выборка

### Структура запроса

```sql
SELECT  столбцы
FROM    таблица
WHERE   условие
ORDER BY столбец [ASC|DESC]
LIMIT   n;
```

### Фильтрация WHERE и операторы

```sql
SELECT * FROM products WHERE price > 100;
SELECT * FROM products WHERE price >= 100 AND qty < 50;
SELECT * FROM products WHERE name = 'Кофе' OR name = 'Чай';
SELECT * FROM products WHERE NOT active;
```

| Оператор | Значение |
|----------|----------|
| `=` `<>` `!=` | равно / не равно |
| `<` `>` `<=` `>=` | сравнение |
| `AND` `OR` `NOT` | логические |

### Специальные условия

```sql
WHERE price BETWEEN 100 AND 500       -- диапазон (включительно)
WHERE category IN ('Напитки', 'Еда')  -- из списка
WHERE name LIKE 'Ко%'                 -- начинается на "Ко"
WHERE name LIKE '%фе'                 -- заканчивается на "фе"
WHERE name LIKE '_оре'                -- _ = ровно один символ
WHERE email IS NULL                   -- проверка на NULL
WHERE email IS NOT NULL
```

> ⚠️ `NULL` нельзя сравнивать через `=`. Только `IS NULL` / `IS NOT NULL`. `NULL = NULL` даёт не `true`, а `NULL`.

### Прочее

```sql
SELECT DISTINCT category FROM products;   -- уникальные значения
SELECT name AS товар, price AS цена FROM products;  -- псевдонимы
SELECT * FROM products ORDER BY price DESC, name ASC;  -- многоуровневая сортировка
SELECT * FROM products LIMIT 10;          -- первые 10 (в H2/MySQL/PostgreSQL)
```

---

## UPDATE — изменение

```sql
UPDATE products SET price = 400 WHERE name = 'Кофе';
UPDATE products SET price = price * 1.1 WHERE category = 'Напитки';  -- +10%
UPDATE products SET qty = 0, active = FALSE WHERE qty < 0;           -- несколько полей
```

> ⚠️ **Всегда** проверяйте `WHERE`! `UPDATE products SET price = 0;` обнулит цену у **всех** товаров.

---

## DELETE — удаление

```sql
DELETE FROM products WHERE qty = 0;
DELETE FROM products WHERE category = 'Снято с продажи';
DELETE FROM products;          -- ⚠️ удалит ВСЕ строки!
```

| Команда | Что делает |
|---------|-----------|
| `DELETE FROM t WHERE ...` | удаляет строки по условию |
| `DELETE FROM t` | удаляет все строки (структура остаётся) |
| `TRUNCATE TABLE t` | быстро очищает всю таблицу |
| `DROP TABLE t` | удаляет таблицу целиком (DDL) |

---

## Безопасные привычки

```sql
-- сначала проверьте SELECT с тем же WHERE:
SELECT * FROM products WHERE qty = 0;     -- что попадёт под удаление?
-- затем выполняйте:
DELETE FROM products WHERE qty = 0;
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| UPDATE/DELETE без WHERE | затронет все строки |
| `WHERE x = NULL` | не работает; нужно `IS NULL` |
| `FLOAT` для денег | ошибки округления; используйте `DECIMAL` |
| `LIKE` без индекса на больших данных | медленно (особенно `%...%`) |
| забыли кавычки у строк | `WHERE name = Кофе` — ошибка; нужно `'Кофе'` |

## Дополнительные источники

- «Изучаем SQL» (Алан Бьюли).
- sqlbolt.com, pgexercises.com — практика.

## Что дальше

В [модуле 46](../module-46-sql-queries/theory.md) — сложные запросы: JOIN, GROUP BY, индексы.
