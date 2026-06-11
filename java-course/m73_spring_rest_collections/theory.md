# Модуль 73. Коллекции: пагинация, сортировка, фильтрация

Эндпоинт `GET /api/tasks` возвращает **много** задач. Отдавать тысячи записей одним ответом — медленно и дорого. Клиенту нужны **страницы**, **сортировка** и **фильтры**. Разберём, как правильно спроектировать выдачу коллекций.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web` (для Task06 — `spring-boot-starter-data-jpa`/`spring-data-commons` ради `Pageable`/`Page`). Продолжаем **Task Tracker API**.

---

## Возврат коллекции

```java
@GetMapping("/api/tasks")
public List<TaskDto> all() {
    return service.findAll();   // → JSON-массив [ {...}, {...} ]
}
```

> Пустой результат — это **пустой массив `[]`** со статусом `200`, а **не** `null` и **не** `404`. `404` — когда не существует сам ресурс-коллекция (что для коллекций редкость).

---

## Пагинация: зачем и как

```
   Без пагинации:  GET /tasks → 50 000 записей в одном ответе (медленно!)
   С пагинацией:   GET /tasks?page=0&size=20 → 20 записей + метаданные
```

Две распространённые схемы параметров:

| Схема | Параметры | Пример |
|-------|-----------|--------|
| page/size | номер страницы + размер | `?page=2&size=20` |
| offset/limit | смещение + количество | `?offset=40&limit=20` |

Соотношение: `offset = page * size`.

### Ручная пагинация по списку

```java
int from = page * size;
int to   = Math.min(from + size, all.size());
List<TaskDto> pageContent = (from >= all.size()) ? List.of() : all.subList(from, to);
```

---

## Форма ответа с метаданными

Клиенту нужны не только данные, но и сведения о страницах: всего элементов, сколько страниц, есть ли следующая.

```java
public record PagedResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {}
```

```json
{
  "content": [ { "id":1, ... }, { "id":2, ... } ],
  "page": 0,
  "size": 20,
  "totalElements": 137,
  "totalPages": 7,
  "hasNext": true
}
```

```
   totalPages = ceil(totalElements / size)
   hasNext    = page + 1 < totalPages
```

---

## Сортировка

Параметр `sort` обычно в формате `поле,направление`:

```
   GET /tasks?sort=createdAt,desc
   GET /tasks?sort=priority,asc
```

Ручная сортировка списка:

```java
Comparator<TaskDto> cmp = Comparator.comparing(TaskDto::createdAt);
if ("desc".equalsIgnoreCase(direction)) cmp = cmp.reversed();
list.sort(cmp);
```

> **Безопасность:** не подставляйте имя поля из параметра напрямую в SQL `ORDER BY` — это путь к инъекции. Сверяйте с белым списком допустимых полей.

---

## Фильтрация

Фильтры — это **необязательные** query-параметры, которые сужают выборку:

```
   GET /tasks?status=DONE&assignee=ivan&priorityMin=3
```

```java
Stream<TaskDto> s = all.stream();
if (status != null)   s = s.filter(t -> t.status().equals(status));
if (assignee != null) s = s.filter(t -> t.assignee().equals(assignee));
List<TaskDto> result = s.toList();
```

Каждый фильтр применяется, только если параметр задан (паттерн «накопление условий»).

---

## Spring Data: Pageable и Page (превью)

Когда данные приходят из БД ([модуль 79](../m79_spring_data_jpa_repository/theory.md)), Spring Data берёт пагинацию на себя. Контроллер принимает `Pageable` прямо как аргумент:

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@GetMapping("/api/tasks")
public Page<TaskDto> all(Pageable pageable) {   // ?page=0&size=20&sort=createdAt,desc
    return service.findAll(pageable);            // Spring сам разберёт параметры
}
```

`Page<T>` уже содержит `content`, `totalElements`, `totalPages`, `number`, `hasNext()` — готовый ответ с метаданными.

| | `List<T>` | `Page<T>` | `Slice<T>` |
|-|-----------|-----------|------------|
| Данные | все/срез | срез | срез |
| Общее число | нет | **есть** (`COUNT`) | нет |
| Есть следующая | — | да | да (`hasNext`) |
| Стоимость | низкая | +COUNT-запрос | без COUNT (дешевле Page) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `null` вместо `[]` при пустой выборке | вернули `null` | возвращать пустой список |
| `IndexOutOfBoundsException` в `subList` | `from`/`to` за границей | `Math.min` и проверка `from >= size` |
| Клиент не знает общее число страниц | вернули голый список | отдавать метаданные (`PagedResponse`/`Page`) |
| Сортировка по произвольному полю → SQL-инъекция | имя поля из запроса в `ORDER BY` | белый список допустимых полей |
| `size` без ограничений → запрос всей таблицы | нет верхней границы | ограничить `size` (например, max 100) |
| Несогласованные параметры в разных эндпоинтах | каждый придумывает свои | единое соглашение (`page`/`size`/`sort`) |

---

## Дополнительные источники

- [Spring Data: Paging and Sorting](https://docs.spring.io/spring-data/commons/reference/repositories/query-methods-details.html#repositories.special-parameters).
- [API Design: Pagination (Microsoft REST Guidelines)](https://github.com/microsoft/api-guidelines).
- [модуль 24](../m24_stream_api_lambda/theory.md) — Stream API (фильтрация/сортировка коллекций).
- [модуль 79](../m79_spring_data_jpa_repository/theory.md) — `Pageable`/`Page` с реальной БД.

## Что дальше

В [модуле 74](../m74_spring_rest_crud_file/theory.md) — полный CRUD, не-CRUD действия и работа с файлами (загрузка/выгрузка).
