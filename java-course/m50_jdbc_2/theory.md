# Модуль 50. JDBC продвинутый: транзакции, batch, DAO

> Предметная область — **банк**: таблица `accounts (id, owner, balance)` и переводы.  
> Практика — H2 in-memory (`jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1`).

---

## try-with-resources: безопасное закрытие ресурсов

`Connection`, `Statement`, `ResultSet` реализуют `AutoCloseable`. Оборачивайте каждый в `try-with-resources` — JVM закроет ресурс автоматически даже при исключении.

```java
// ✅ Правильно: ресурсы закрываются гарантированно
try (Connection con = DriverManager.getConnection(URL, USER, PASS);
     Statement  st  = con.createStatement();
     ResultSet  rs  = st.executeQuery("SELECT * FROM accounts")) {
    while (rs.next()) {
        System.out.println(rs.getLong("id") + " " + rs.getString("owner"));
    }
} // rs, st, con закрываются в обратном порядке
```

```
Порядок закрытия (обратный порядку объявления):
  ┌────────────┐   ┌───────────┐   ┌────────────┐
  │ Connection │ ◄─│ Statement │ ◄─│ ResultSet  │
  └────────────┘   └───────────┘   └────────────┘
  закрывается       закрывается      закрывается
  последним         вторым           первым
```

---

## Транзакции: атомарный перевод денег

По умолчанию JDBC работает в режиме **auto-commit**: каждый SQL немедленно фиксируется.  
Для атомарности нескольких операций отключайте auto-commit и управляйте транзакцией вручную.

```java
con.setAutoCommit(false);               // отключить auto-commit
try {
    st.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE id = 1");
    st.executeUpdate("UPDATE accounts SET balance = balance + 500 WHERE id = 2");
    con.commit();                        // зафиксировать обе операции
} catch (SQLException e) {
    con.rollback();                      // откатить при ошибке
    throw e;
}
```

```
       Транзакция перевода
  ┌─────────────────────────────────┐
  │  setAutoCommit(false)           │
  │  UPDATE accounts ... id=1  (-500)│
  │  UPDATE accounts ... id=2  (+500)│
  │  commit()  ──────────────────►  │  ← оба UPDATE видны одновременно
  └─────────────────────────────────┘
         ↑ при ошибке — rollback() — ни один UPDATE не применён
```

---

## Обработка SQLException

`SQLException` несёт три ключевых поля:

| Метод | Смысл | Пример |
|-------|-------|--------|
| `getSQLState()` | Код стандарта SQL (5 символов) | `"23505"` — нарушение UNIQUE |
| `getErrorCode()` | Код конкретной СУБД | зависит от БД |
| `getMessage()` | Текст ошибки | `"Unique index or primary key violation"` |

```java
try {
    st.executeUpdate("INSERT INTO accounts(id, owner, balance) VALUES(1,'Дубль',0)");
} catch (SQLException e) {
    System.out.println("SQLState : " + e.getSQLState());
    System.out.println("ErrorCode: " + e.getErrorCode());
    System.out.println("Message  : " + e.getMessage());
    con.rollback();   // откатить текущую транзакцию
}
```

---

## Batch-операции

Вместо N отдельных `executeUpdate()` отправляйте команды **пакетом** — меньше roundtrip к БД.

```java
PreparedStatement ps = con.prepareStatement(
    "INSERT INTO accounts(owner, balance) VALUES(?, ?)");

for (int i = 0; i < 1000; i++) {
    ps.setString(1, "Владелец " + i);
    ps.setLong(2, 1000L * i);
    ps.addBatch();               // добавить в пакет (не отправлять)
}
int[] counts = ps.executeBatch(); // отправить всё одним запросом
```

```
Без batch: 1000 roundtrip
  [INSERT]─► БД   [INSERT]─► БД   ... × 1000

С batch: 1 roundtrip
  [INSERT × 1000]────────────────────────► БД
```

| Метод | Назначение |
|-------|-----------|
| `addBatch()` | добавить команду в очередь |
| `executeBatch()` | отправить очередь; возвращает `int[]` с кол-вом изменённых строк |
| `clearBatch()` | очистить очередь (если нужно отменить) |

---

## Уровни изоляции транзакций

Несколько параллельных транзакций могут мешать друг другу. JDBC позволяет управлять компромиссом «изоляция vs производительность».

```
Проблемы параллельных транзакций:
  Dirty Read        — читаем незафиксированные данные другой транзакции
  Non-repeatable Read — повторное чтение одной строки даёт разный результат
  Phantom Read      — повторный SELECT возвращает другой набор строк
```

| Константа | Dirty Read | Non-rep. Read | Phantom Read | Производительность |
|-----------|-----------|--------------|-------------|-------------------|
| `TRANSACTION_READ_UNCOMMITTED` | ✅ возможен | ✅ | ✅ | наивысшая |
| `TRANSACTION_READ_COMMITTED` | ❌ защищён | ✅ | ✅ | высокая |
| `TRANSACTION_REPEATABLE_READ` | ❌ | ❌ | ✅ | средняя |
| `TRANSACTION_SERIALIZABLE` | ❌ | ❌ | ❌ | наименьшая |

```java
con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); // до начала транзакции
```

> По умолчанию большинство СУБД используют `READ_COMMITTED`.

---

## Паттерн DAO (Data Access Object)

DAO изолирует SQL-код от бизнес-логики. Сервис работает с интерфейсом, не зная о JDBC.

```
┌──────────────────┐         ┌─────────────────┐         ┌──────────┐
│  TransferService │────────►│  AccountDao      │────────►│   БД     │
│  (бизнес-логика) │ интерфейс│  (JDBC-реализация)│  SQL   │ accounts │
└──────────────────┘         └─────────────────┘         └──────────┘
```

```java
// Интерфейс — контракт без деталей реализации
interface AccountDao {
    Account findById(long id) throws SQLException;
    void    save(Account account) throws SQLException;
    void    updateBalance(long id, long newBalance) throws SQLException;
}

// Реализация — JDBC-детали скрыты здесь
class JdbcAccountDao implements AccountDao {
    private final Connection con;
    JdbcAccountDao(Connection con) { this.con = con; }

    @Override
    public Account findById(long id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM accounts WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Account(
                    rs.getLong("id"), rs.getString("owner"), rs.getLong("balance"));
                return null;
            }
        }
    }
    // ... остальные методы
}
```

---

## Подводные камни

| Ошибка | Последствие | Решение |
|--------|-------------|---------|
| Не закрыть `Connection` | утечка соединений, зависание приложения | `try-with-resources` |
| Забыть `rollback()` в `catch` | транзакция висит, блокирует строки | всегда `rollback()` в `catch` |
| Auto-commit при batch | каждая строка — отдельная транзакция, медленно | `setAutoCommit(false)` перед batch |
| Строковая конкатенация в SQL | SQL-инъекция | только `PreparedStatement` с `?` |
| Не проверять `rs.next()` | `NullPointerException` если строк нет | `if (rs.next())` перед чтением |
| Один `Connection` на несколько потоков | гонки состояний, некорректные данные | отдельный `Connection` на поток |

---

## Дополнительные источники

- Oracle JDBC Tutorial: https://docs.oracle.com/javase/tutorial/jdbc/
- H2 документация: https://www.h2database.com/html/main.html
- «Java. Библиотека профессионала» (Хорстманн), глава про JDBC.

## Что дальше

В [модуле 49](../module-49-jdbc-1/theory.md) — основы JDBC: подключение, простые запросы, `PreparedStatement`.  
В [модуле 51](../module-51-hibernate-orm/theory.md) — Hibernate ORM: работа с БД без написания SQL вручную.
