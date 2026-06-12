# Модуль 51. Hibernate / JPA ORM — основы

ORM (Object-Relational Mapping) — слой, который автоматически превращает объекты Java в строки таблиц и обратно. Вы работаете с обычными классами, а не с SQL-запросами напрямую.

> Практика — отображение сущностей и CRUD-операции через Hibernate 6 / Jakarta Persistence API (JPA). H2 как in-memory БД.

---

## Что такое JPA и Hibernate

```
 +----------------------------------------------+
 |              Ваш Java-код                    |
 |  Post post = new Post("Заголовок", "Текст"); |
 +--------------------+-------------------------+
                      |  JPA API (интерфейс)
 +--------------------▼-------------------------+
 |           Hibernate (реализация JPA)         |
 |  генерирует SQL, управляет кешем, сессиями   |
 +--------------------+-------------------------+
                      |  JDBC
 +--------------------▼-------------------------+
 |        База данных (H2, PostgreSQL…)         |
 +----------------------------------------------+
```

| Понятие | Значение |
|---------|----------|
| **JPA** | спецификация (Jakarta Persistence API) — только интерфейсы |
| **Hibernate** | реализация JPA + собственные расширения |
| **Entity** | Java-класс, отображённый на таблицу БД |
| **SessionFactory / EntityManagerFactory** | фабрика соединений, создаётся один раз |
| **Session / EntityManager** | единица работы, открывается на операцию/транзакцию |

---

## Зависимости (Gradle)

```groovy
// build.gradle
dependencies {
    implementation 'org.hibernate.orm:hibernate-core:6.5.2.Final'
    runtimeOnly    'com.h2database:h2:2.2.224'
}
```

> Hibernate 6 использует **jakarta.persistence.\*** (не javax.persistence.\*).

---

## Сущность @Entity

Минимальный маппинг:

```java
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity                        // класс = таблица
@Table(name = "posts")         // явное имя таблицы (необязательно)
public class Post {

    @Id                                              // первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автоинкремент БД
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Конструктор без аргументов — ОБЯЗАТЕЛЕН для JPA
    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // getters / setters …
}
```

```
 Класс Post        →   таблица posts
 поле id           →   колонка id (PK, AUTOINCREMENT)
 поле title        →   колонка title VARCHAR(255) NOT NULL
 поле content      →   колонка content TEXT
 поле createdAt    →   колонка created_at
```

---

## Жизненный цикл объекта (States)

```
  new Post(...)         em.persist()
  +------------+        +-------------+        +-------------+
  |  TRANSIENT |-------►|  PERSISTENT |-------►|   DETACHED  |
  | (вне JPA)  |        | (в сессии)  |        | (вне сессии)|
  +------------+        +------+------+        +------+------+
                               |  em.remove()          |  em.merge()
                               ▼                       ▼
                         +-------------+        +-------------+
                         |   REMOVED   |        |  PERSISTENT |
                         |  (удалён)   |        |  (снова)    |
                         +-------------+        +-------------+
```

| Состояние | Описание |
|-----------|----------|
| **Transient** | объект создан через `new`, JPA о нём не знает |
| **Persistent** | объект привязан к открытой сессии; изменения автоматически синхронизируются с БД |
| **Detached** | сессия закрыта; объект существует в памяти, но изменения не отслеживаются |
| **Removed** | помечен на удаление; DELETE выполнится при commit |

---

## Конфигурация: hibernate.cfg.xml

```xml
<!-- src/main/resources/hibernate.cfg.xml -->
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- H2 in-memory -->
        <property name="hibernate.connection.driver_class">org.h2.Driver</property>
        <property name="hibernate.connection.url">jdbc:h2:mem:blogdb;DB_CLOSE_DELAY=-1</property>
        <property name="hibernate.connection.username">sa</property>
        <property name="hibernate.connection.password"></property>

        <!-- Диалект -->
        <property name="hibernate.dialect">org.hibernate.dialect.H2Dialect</property>

        <!-- Создать схему при старте, удалить при остановке -->
        <property name="hibernate.hbm2ddl.auto">create-drop</property>

        <!-- Логировать SQL в консоль -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Сущности -->
        <mapping class="Post"/>
    </session-factory>
</hibernate-configuration>
```

> Альтернатива — `persistence.xml` (стандарт JPA) в `src/main/resources/META-INF/`.  
> Для простых учебных задач достаточно **hibernate.cfg.xml** + `Configuration`.

---

## Создание SessionFactory и Session

```java
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Одноразовое создание фабрики (вся программа)
SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")   // читаем конфиг
        .addAnnotatedClass(Post.class)     // регистрируем сущность
        .buildSessionFactory();

// Единица работы
try (Session session = factory.openSession()) {
    Transaction tx = session.beginTransaction();

    Post post = new Post("Мой первый пост", "Содержание поста");
    session.persist(post);   // INSERT выполнится при commit

    tx.commit();             // фиксируем транзакцию
}   // session.close() автоматически
```

---

## CRUD-операции

### Сохранение (persist)

```java
session.persist(post);          // transient → persistent
// INSERT INTO posts … при commit
```

### Поиск по id (find / get)

```java
Post found = session.get(Post.class, 1L);   // null если нет
// или через EntityManager:
Post found = em.find(Post.class, 1L);
```

### Обновление (merge)

```java
// post — detached (сессия была закрыта)
post.setTitle("Новый заголовок");
Post managed = session.merge(post);   // detached → persistent
// UPDATE … при commit
```

### Удаление (remove)

```java
Post managed = session.get(Post.class, 1L);  // загрузить как managed
session.remove(managed);                      // marked as REMOVED
// DELETE … при commit
```

---

## JPQL — запросы объектно-ориентированным языком

```java
// Все посты
List<Post> posts = session
        .createQuery("FROM Post ORDER BY createdAt DESC", Post.class)
        .getResultList();

// С условием
List<Post> byAuthor = session
        .createQuery("FROM Post p WHERE p.title LIKE :pattern", Post.class)
        .setParameter("pattern", "%Java%")
        .getResultList();
```

> JPQL работает с **именами классов и полей** (Post, title), а не с именами таблиц/колонок.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| Нет конструктора без аргументов | Hibernate не сможет создать объект при загрузке из БД — `InstantiationException` |
| Работа вне транзакции | `persist/merge/remove` требуют активной транзакции, иначе `TransactionRequiredException` |
| Обновление detached-объекта без merge | Изменения молча теряются, в БД ничего не попадает |
| `session.load()` vs `session.get()` | `load()` возвращает прокси и кидает исключение при отсутствии; `get()` возвращает `null` |
| Закрыть фабрику слишком рано | `SessionFactory.close()` делают один раз при завершении программы |
| N+1 проблема | JPQL без JOIN FETCH грузит дочерние сущности по одному SELECT на каждую |

---

## Перекрёстные ссылки

- Предыдущий модуль: [Модуль 50 — JDBC углублённо](../m50_jdbc_2/theory.md) — прямые SQL-запросы через JDBC, которые Hibernate заменяет автоматически.
- Следующий модуль: [Модуль 52 — Hibernate связи (@OneToMany, @ManyToOne)](../m52_hibernate_relationships/theory.md) — отображение связей между сущностями.

---

## Дополнительные источники

- Официальная документация Hibernate: https://docs.jboss.org/hibernate/orm/6.5/userguide/html_single/
- Jakarta Persistence спецификация: https://jakarta.ee/specifications/persistence/
- «Java Persistence with Hibernate» (Christian Bauer, Gavin King).

## Что дальше

В [модуле 52](../m52_hibernate_relationships/theory.md) — отображение связей: `@OneToMany`, `@ManyToOne`, `@ManyToMany`, стратегии загрузки (LAZY/EAGER) и каскадирование операций.
