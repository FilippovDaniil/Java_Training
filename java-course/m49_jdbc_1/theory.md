# Модуль 49. JDBC — основы

JDBC (Java Database Connectivity) — стандартный API для работы с реляционными базами данных из Java. Он входит в JDK (`java.sql`), поэтому никаких сторонних зависимостей для компиляции не нужно — только JAR-драйвер конкретной СУБД в runtime.

> Практика — интернет-магазин: таблица `products` (id, name, price, quantity). Драйвер: H2 (`com.h2database:h2`).

---

## Ключевые интерфейсы

```
java.sql
 ├── DriverManager        — фабрика соединений (статический класс)
 ├── Connection           — одно соединение с БД
 ├── Statement            — SQL-запрос без параметров
 ├── PreparedStatement    — SQL-запрос с параметрами (?)
 ├── ResultSet            — таблица результатов SELECT
 └── ResultSetMetaData    — метаданные о столбцах ResultSet
```

| Интерфейс | Когда использовать |
|-----------|-------------------|
| `Statement` | разовые DDL-запросы (CREATE, DROP), динамический SQL без параметров |
| `PreparedStatement` | запросы с пользовательскими параметрами; защита от SQL-инъекций |
| `CallableStatement` | вызов хранимых процедур |

---

## Получение Connection

```java
// JDBC URL для H2 в памяти
String url  = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
String user = "sa";
String pass = "";

// DriverManager.getConnection — самый простой способ
Connection conn = DriverManager.getConnection(url, user, pass);
```

```
DriverManager.getConnection(url, user, pass)
        │
        ▼
   Connection   ◄── нужно закрыть! (implements AutoCloseable)
        │
   ┌────┴────────────────┐
   │                     │
Statement          PreparedStatement
   │                     │
ResultSet          ResultSet
```

> Всегда закрывайте ресурсы через **try-with-resources**. Не закрытое соединение — утечка ресурсов.

```java
// Правильный паттерн:
try (Connection conn = DriverManager.getConnection(url, user, pass);
     Statement  stmt = conn.createStatement()) {
    // работа с БД
} // conn и stmt закрываются автоматически
```

---

## Statement — DDL и простые запросы

```java
try (Connection conn = DriverManager.getConnection(url, user, pass);
     Statement  stmt = conn.createStatement()) {

    // DDL — CREATE TABLE
    stmt.executeUpdate("""
        CREATE TABLE products (
            id       BIGINT AUTO_INCREMENT PRIMARY KEY,
            name     VARCHAR(100) NOT NULL,
            price    DECIMAL(10,2) NOT NULL,
            quantity INT NOT NULL DEFAULT 0
        )
    """);

    // DML — INSERT (возвращает число затронутых строк)
    int rows = stmt.executeUpdate(
        "INSERT INTO products(name,price,quantity) VALUES('Ноутбук',89999.00,5)"
    );

    // DQL — SELECT
    ResultSet rs = stmt.executeQuery("SELECT * FROM products");
    while (rs.next()) {
        long   id   = rs.getLong("id");
        String name = rs.getString("name");
        System.out.println(id + " " + name);
    }
}
```

| Метод Statement | Когда |
|-----------------|-------|
| `executeUpdate(sql)` | INSERT / UPDATE / DELETE / DDL → возвращает `int` (затронутые строки) |
| `executeQuery(sql)` | SELECT → возвращает `ResultSet` |
| `execute(sql)` | любой запрос; `true` если вернул `ResultSet` |

---

## PreparedStatement — параметры и безопасность

```java
String sql = "INSERT INTO products(name, price, quantity) VALUES(?, ?, ?)";

try (Connection conn = DriverManager.getConnection(url, user, pass);
     PreparedStatement ps = conn.prepareStatement(sql)) {

    ps.setString(1, "Мышь");           // индекс с 1, не с 0
    ps.setBigDecimal(2, new BigDecimal("1299.50"));
    ps.setInt(3, 100);
    int rows = ps.executeUpdate();
    System.out.println("Вставлено: " + rows);
}
```

```
❌ SQL-инъекция через Statement:
   "SELECT * FROM products WHERE name = '" + userInput + "'"
   userInput = "'; DROP TABLE products; --"  → катастрофа!

✅ PreparedStatement: параметры экранируются драйвером автоматически
   "SELECT * FROM products WHERE name = ?"
   ps.setString(1, userInput)  → безопасно
```

---

## ResultSetMetaData — метаданные столбцов

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM products");
ResultSetMetaData meta = rs.getMetaData();

int cols = meta.getColumnCount();
for (int i = 1; i <= cols; i++) {
    System.out.printf("%-20s %s%n",
        meta.getColumnName(i),     // имя столбца
        meta.getColumnTypeName(i)  // тип: BIGINT, VARCHAR, DECIMAL, …
    );
}
```

---

## Маппинг ResultSet → Java-объект

```java
record Product(long id, String name, BigDecimal price, int quantity) {}

List<Product> list = new ArrayList<>();
ResultSet rs = stmt.executeQuery("SELECT * FROM products");
while (rs.next()) {
    list.add(new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getBigDecimal("price"),
        rs.getInt("quantity")
    ));
}
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `Connection` не закрыт | утечка соединений; при пуле — исчерпание |
| Индекс параметра с 0 | `ps.setString(0, ...)` → `SQLException`; параметры начинаются с **1** |
| `Statement` с пользовательским вводом | SQL-инъекция; всегда `PreparedStatement` |
| `rs.next()` не вызван перед `rs.get*()` | `SQLException`; курсор стоит перед первой строкой |
| `DB_CLOSE_DELAY=-1` не указан (H2 in-memory) | БД закрывается при первом закрытии Connection; следующий `getConnection` получает пустую БД |
| Тип `DECIMAL` → `getDouble()` | потеря точности; используйте `getBigDecimal()` |
| DDL внутри транзакции | многие СУБД авто-коммитят DDL; поведение зависит от БД |

---

## Перекрёстные ссылки

- Схема таблицы `products` спроектирована в [модуле 48](../m48_database_design/theory.md) (нормальные формы, ключи).
- Продвинутые возможности JDBC (транзакции, пул соединений, batch) — в [модуле 50](../m50_jdbc_2/theory.md).

---

## Дополнительные источники

- Официальная документация: [docs.oracle.com/javase/tutorial/jdbc](https://docs.oracle.com/javase/tutorial/jdbc/basics/)
- «JDBC API Tutorial and Reference» (Maydene Fisher et al.)
- H2 Database: [h2database.com](https://h2database.com)

---

## Что дальше

В [модуле 50](../m50_jdbc_2/theory.md) — транзакции, batch-запросы, пул соединений (HikariCP) и паттерн DAO.
