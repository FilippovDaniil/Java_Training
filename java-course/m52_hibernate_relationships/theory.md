# Модуль 52. Связи сущностей в JPA/Hibernate

JPA позволяет выразить связи между таблицами прямо на уровне Java-классов с помощью четырёх аннотаций: `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@OneToOne`. Hibernate автоматически транслирует их в SQL JOIN-ы, INSERT-ы и ограничения внешних ключей.

> Практика — предметная область «блог»: Author, Post, Comment, Tag, UserProfile (H2 in-memory).

---

## Предметная область: блог

```
 ┌──────────────┐     1       N   ┌──────────────┐     1       N   ┌──────────────┐
 │   Author     │──────────────── │     Post     │──────────────── │   Comment    │
 │ id           │                 │ id           │                 │ id           │
 │ name         │                 │ title        │                 │ text         │
 │ email        │                 │ content      │                 │ author_id FK │
 └──────────────┘                 │ author_id FK │                 └──────────────┘
        │ 1                       └──────────────┘
        │                               N   M
        │ 1                        ╔═════════════╗
 ┌──────────────┐                  ║  post_tags  ║
 │ UserProfile  │                  ║  post_id FK ║
 │ id           │                  ║  tag_id  FK ║
 │ bio          │                  ╚═════════════╝
 │ author_id FK │                       M   N
 └──────────────┘                 ┌──────────────┐
                                  │     Tag      │
                                  │ id           │
                                  │ name         │
                                  └──────────────┘
```

---

## @ManyToOne и @OneToMany — связь «один ко многим»

Самая распространённая связь: один `Author` пишет много `Post`.

```java
// Сторона «многие» — здесь хранится внешний ключ
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // @ManyToOne: многие Post -> один Author
    // @JoinColumn: имя столбца FK в таблице posts
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}

// Сторона «один» — коллекция, mappedBy указывает на поле-владелец
@Entity
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // mappedBy = "author" — поле в классе Post
    // Hibernate НЕ создаёт второй FK; таблица управляется со стороны Post
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
}
```

### Владелец связи (owning side)

```
 Двунаправленная связь состоит из двух сторон:

  Post.author    ← ВЛАДЕЛЕЦ (owning side)
                   содержит @JoinColumn, управляет FK в БД

  Author.posts   ← ОБРАТНАЯ СТОРОНА (inverse side)
                   содержит mappedBy, только читает связь
                   Hibernate игнорирует изменения этой стороны при сохранении!
```

| Сторона | Аннотация | Управляет FK? |
|---------|-----------|---------------|
| Владелец | `@JoinColumn` | Да |
| Обратная | `mappedBy = "..."` | Нет |

**Правило:** всегда синхронизируйте обе стороны в коде:

```java
// Правильный способ добавить пост автору
Post post = new Post("Заголовок");
post.setAuthor(author);          // владелец → Hibernate запишет FK
author.getPosts().add(post);     // обратная → Java-объект в памяти согласован
```

---

## @ManyToMany — связь «многие ко многим»

Пост может иметь несколько тегов; один тег может быть у нескольких постов.

```java
@Entity
public class Post {

    // @JoinTable задаёт промежуточную таблицу и оба FK
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "post_tags",
        joinColumns        = @JoinColumn(name = "post_id"),  // FK к Post
        inverseJoinColumns = @JoinColumn(name = "tag_id")    // FK к Tag
    )
    private Set<Tag> tags = new HashSet<>();
}

@Entity
public class Tag {

    @ManyToMany(mappedBy = "tags")  // обратная сторона
    private Set<Post> posts = new HashSet<>();
}
```

```
post_tags (промежуточная таблица)
┌─────────┬────────┐
│ post_id │ tag_id │
├─────────┼────────┤
│    1    │   3    │
│    1    │   5    │
│    2    │   3    │
└─────────┴────────┘
```

> Не используйте `CascadeType.REMOVE` / `CascadeType.ALL` для `@ManyToMany` — удаление одного объекта удалит связанные объекты других авторов.

---

## @OneToOne — связь «один к одному»

Каждый `Author` имеет ровно один `UserProfile`.

```java
@Entity
public class Author {

    // Владелец: FK profile_id хранится в таблице authors
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id", unique = true)
    private UserProfile profile;
}

@Entity
public class UserProfile {

    // Обратная сторона — не управляет FK
    @OneToOne(mappedBy = "profile")
    private Author author;
}
```

Альтернатива — хранить FK в таблице `user_profiles` (FK `author_id`):

```java
@Entity
public class UserProfile {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", unique = true)
    private Author author;
}
```

---

## Cascade — каскадные операции

`CascadeType` управляет тем, что происходит с дочерними сущностями при операциях над родителем.

```java
@OneToMany(mappedBy = "post",
           cascade = CascadeType.ALL,   // перенести ВСЕ операции на комментарии
           orphanRemoval = true)         // удалить Comment, если убрать из списка
private List<Comment> comments = new ArrayList<>();
```

| CascadeType | Описание |
|-------------|----------|
| `PERSIST` | сохранить дочерние при `em.persist(parent)` |
| `MERGE` | обновить дочерние при `em.merge(parent)` |
| `REMOVE` | удалить дочерние при `em.remove(parent)` |
| `REFRESH` | обновить из БД вместе с родителем |
| `DETACH` | отсоединить от контекста вместе с родителем |
| `ALL` | все пять выше |

```java
// Пример: каскадное сохранение — не нужно отдельно persist(comment)
Post post = new Post("Пост о Java");
post.setAuthor(author);

Comment c1 = new Comment("Отлично!");
c1.setPost(post);
post.getComments().add(c1);  // добавили в список

em.persist(post);  // → Hibernate автоматически INSERT для c1 (CascadeType.PERSIST)
```

### orphanRemoval

```java
post.getComments().remove(c1);  // убираем из списка
em.merge(post);                 // → Hibernate выполнит DELETE comment WHERE id=...
                                // (работает только с orphanRemoval = true)
```

---

## FetchType — стратегия загрузки

```
EAGER — данные загружаются СРАЗУ вместе с родителем
LAZY  — данные загружаются ТОЛЬКО при первом обращении к коллекции
```

| Аннотация | Дефолт |
|-----------|--------|
| `@ManyToOne` | EAGER |
| `@OneToOne` | EAGER |
| `@OneToMany` | LAZY |
| `@ManyToMany` | LAZY |

**Рекомендация:** всегда явно указывайте `fetch = FetchType.LAZY` для `@ManyToOne` и `@OneToOne` — дефолт EAGER опасен.

---

## Проблема N+1 и JOIN FETCH

### Что такое N+1

```
Загрузить 10 авторов → 1 SELECT

Для каждого автора обратиться к author.getPosts() →
  + 10 отдельных SELECT (по одному на каждого автора)

Итого: 1 + 10 = 11 запросов вместо одного
```

Это «проблема N+1» — главная причина падения производительности в Hibernate.

### Решение: JOIN FETCH

```java
// JPQL с JOIN FETCH — загружает Author и его posts одним запросом
String jpql = "SELECT a FROM Author a JOIN FETCH a.posts WHERE a.id = :id";

List<Author> authors = em
    .createQuery(jpql, Author.class)
    .getResultList();
// → один SQL: SELECT a.*, p.* FROM authors a INNER JOIN posts p ON p.author_id = a.id
```

```
Без JOIN FETCH:
  SELECT * FROM authors                        ← 1 запрос
  SELECT * FROM posts WHERE author_id = 1      ← 1 запрос
  SELECT * FROM posts WHERE author_id = 2      ← 1 запрос
  ...                                          ← N запросов
                                               = N+1

С JOIN FETCH:
  SELECT a.*, p.* FROM authors a
    JOIN posts p ON p.author_id = a.id         ← 1 запрос
```

Также доступны: `@EntityGraph`, `@BatchSize`, `@Fetch(FetchMode.SUBSELECT)`.

---

## Подводные камни

| Ошибка | Объяснение | Решение |
|--------|-----------|---------|
| Забыть `mappedBy` | Hibernate создаст лишнюю промежуточную таблицу | Всегда указывать `mappedBy` на обратной стороне |
| Изменять только обратную сторону | FK не сохранится в БД | Синхронизировать обе стороны через вспомогательный метод |
| `CascadeType.ALL` на `@ManyToMany` | Удаление одного поста удалит общие теги | Использовать только `PERSIST` + `MERGE` |
| `FetchType.EAGER` повсюду | Лавинная загрузка всего графа объектов | Оставить LAZY, применять JOIN FETCH точечно |
| `LazyInitializationException` | Обращение к LAZY-коллекции вне сессии | Держать сессию открытой или использовать JOIN FETCH |
| Равенство по `id` в `Set` | `equals`/`hashCode` на полях с `@Id` нестабильны до persist | Реализовывать `equals` по бизнес-ключу |
| Двунаправленная `@OneToOne` EAGER | Загружает граф рекурсивно | Одну сторону сделать LAZY |
| `orphanRemoval` без владения | Работает только если коллекция принадлежит родителю | Применять только на стороне родителя |

---

## Сводная таблица аннотаций

| Аннотация | Тип связи | Где хранится FK | Дефолтный fetch |
|-----------|-----------|-----------------|-----------------|
| `@ManyToOne` | N → 1 | Таблица «многих» | EAGER |
| `@OneToMany` | 1 → N | Таблица «многих» (через mappedBy) | LAZY |
| `@ManyToMany` | N → N | Промежуточная таблица | LAZY |
| `@OneToOne` | 1 → 1 | Таблица владельца | EAGER |

---

## Дополнительные источники

- «Java Persistence with Hibernate» (C. Bauer, G. King, G. Gregory).
- Официальная документация Hibernate ORM: https://docs.jboss.org/hibernate/orm/6.4/userguide/html_single/
- Статья Vlad Mihalcea «The best way to map a @OneToMany relationship».

## Что дальше

В [модуле 53](../m53_hibernate_inheritance/theory.md) — стратегии наследования сущностей в JPA: `@Inheritance`, `SINGLE_TABLE`, `JOINED`, `TABLE_PER_CLASS`.

---

*Смежные темы: [модуль 51 — Hibernate ORM, основы](../m51_hibernate_orm/theory.md), [модуль 48 — проектирование БД](../m48_database_design/theory.md).*
