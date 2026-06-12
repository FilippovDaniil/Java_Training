# Модуль 44. Введение в базы данных. DDL/DML

**База данных (БД)** — организованное хранилище данных. **СУБД** (система управления БД) — программа для работы с ней (MySQL, PostgreSQL, H2). **SQL** — язык запросов к реляционным БД.

> Практику можно выполнять в **H2** (встроенная БД, без установки) или любой СУБД (MySQL/PostgreSQL). Задачи — это SQL-скрипты; `.java`-файл лишь несёт условие и текст-блок для вашего SQL.

## Реляционная модель

Данные хранятся в **таблицах** (отношениях): строки (записи) и столбцы (поля).

```
        Таблица users
 +----+----------+-----+
 | id |  name    | age |   ← столбцы (поля)
 +----+----------+-----+
 | 1  | Иван     | 30  |   ← строка (запись)
 | 2  | Мария    | 25  |
 | 3  | Пётр     | 42  |
 +----+----------+-----+
```

| Термин | Значение |
|--------|----------|
| Таблица (table) | набор данных одного типа |
| Строка (row) | одна запись |
| Столбец (column) | одно поле/атрибут |
| Первичный ключ (PK) | уникальный идентификатор строки |
| Внешний ключ (FK) | ссылка на строку другой таблицы |

---

## Категории SQL-команд

```
SQL
 +-- DDL (Data Definition)    — структура: CREATE, ALTER, DROP
 +-- DML (Data Manipulation)  — данные: INSERT, SELECT, UPDATE, DELETE
 +-- DCL (Data Control)       — права: GRANT, REVOKE
 +-- TCL (Transaction Control)— транзакции: COMMIT, ROLLBACK
```

| Категория | Команды | О чём |
|-----------|---------|-------|
| DDL | CREATE, ALTER, DROP | структура таблиц |
| DML | INSERT, SELECT, UPDATE, DELETE | сами данные |
| DCL | GRANT, REVOKE | доступ |
| TCL | COMMIT, ROLLBACK | транзакции (см. [модуль 47](../m47_transactions/theory.md)) |

---

## DDL: создание таблицы

```sql
CREATE TABLE users (
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
    age   INT,
    email VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

| Элемент | Значение |
|---------|----------|
| `PRIMARY KEY` | уникальный идентификатор |
| `AUTO_INCREMENT` | авто-нумерация (в H2: `AUTO_INCREMENT` или `IDENTITY`) |
| `NOT NULL` | поле обязательно |
| `UNIQUE` | значения не повторяются |
| `DEFAULT` | значение по умолчанию |

### Основные типы данных

| Тип | Назначение |
|-----|-----------|
| `INT` / `BIGINT` | целые числа |
| `DECIMAL(p,s)` | точные дробные (деньги) |
| `VARCHAR(n)` | строка переменной длины |
| `TEXT` | длинный текст |
| `DATE` / `TIMESTAMP` | дата / дата-время |
| `BOOLEAN` | логический |

### Изменение и удаление

```sql
ALTER TABLE users ADD COLUMN phone VARCHAR(20);
ALTER TABLE users DROP COLUMN phone;
DROP TABLE users;            -- удалить таблицу целиком
```

---

## DML: базовые операции

### INSERT — добавить данные

```sql
INSERT INTO users (name, age, email) VALUES ('Иван', 30, 'ivan@mail.ru');
INSERT INTO users (name, age) VALUES ('Мария', 25), ('Пётр', 42);  -- несколько строк
```

### SELECT — прочитать данные

```sql
SELECT * FROM users;                       -- все столбцы, все строки
SELECT name, age FROM users;               -- конкретные столбцы
SELECT * FROM users WHERE age > 30;        -- с условием
SELECT * FROM users ORDER BY age DESC;     -- сортировка
```

(Подробно SELECT — в [модулях 45](../m45_sql_dml/theory.md) и [46](../m46_sql_queries/theory.md).)

---

## Запуск в H2 (без установки)

H2 — встроенная Java-БД. Варианты:
1. **В памяти**: `jdbc:h2:mem:testdb` — данные живут, пока работает программа.
2. **В файле**: `jdbc:h2:./data/mydb` — сохраняется на диск.
3. **H2 Console** — веб-интерфейс для выполнения SQL.

В этом модуле SQL можно выполнять в любом SQL-клиенте; JDBC-подключение из Java — в [модуле 49](../m49_jdbc_1/theory.md).

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| забыли `WHERE` в UPDATE/DELETE | изменятся/удалятся ВСЕ строки! |
| нет первичного ключа | строки не отличить, проблемы со связями |
| `VARCHAR` без длины | задавайте разумную длину |
| хранение денег в `FLOAT` | используйте `DECIMAL` (точность) |
| `SELECT *` в продакшене | лучше явно перечислять нужные столбцы |

## Дополнительные источники

- H2 Database (h2database.com).
- «Изучаем SQL» (Алан Бьюли).
- sqlbolt.com — интерактивная практика.

## Что дальше

В [модуле 45](../m45_sql_dml/theory.md) — подробно про DML и типы данных.
