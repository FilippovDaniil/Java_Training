# Модуль 56. JSON, Jackson, DTO

JSON (JavaScript Object Notation) — текстовый формат обмена данными. Используется во всех REST API, конфигурационных файлах и очередях сообщений. В экосистеме Java основная библиотека для работы с JSON — **Jackson**.

> Практика — сериализация/десериализация объектов, аннотации, маппинг DTO↔Entity.  
> Предыдущий модуль: [модуль 55](../module-55-http-basics/theory.md) — HTTP, клиент-серверная модель.

---

## JSON-синтаксис

```
{
  "id": 1,
  "name": "Кофе",
  "price": 350.0,
  "available": true,
  "tags": ["горячий", "кофеин"],
  "category": {
    "id": 3,
    "name": "Напитки"
  },
  "expiresAt": null
}
```

| Тип значения | Пример |
|--------------|--------|
| Число (int/double) | `42`, `3.14` |
| Строка | `"hello"` |
| Булев | `true`, `false` |
| null | `null` |
| Массив | `[1, 2, 3]` |
| Объект | `{ "key": "value" }` |

---

## Библиотека Jackson 2.x

Jackson — стандарт де-факто для JSON в Java. Spring Boot 3.x подключает его автоматически.

**Зависимость (Gradle):**
```groovy
implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.2'
// для java.time (LocalDate, LocalDateTime):
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2'
```

**Зависимость (Maven):**
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.2</version>
</dependency>
```

---

## ObjectMapper: основные операции

```
Java-объект ──────── writeValueAsString() ──────► JSON-строка
JSON-строка ──────── readValue()           ──────► Java-объект
JSON-файл   ──────── readValue(File, ...)  ──────► Java-объект
Java-объект ──────── writeValue(File, ...) ──────► JSON-файл
```

```java
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper mapper = new ObjectMapper();

// Сериализация: объект → JSON
Product product = new Product(1L, "Кофе", 350.0);
String json = mapper.writeValueAsString(product);
// → {"id":1,"name":"Кофе","price":350.0}

// Красивый вывод (с отступами)
String prettyJson = mapper.writerWithDefaultPrettyPrinter()
                          .writeValueAsString(product);

// Десериализация: JSON → объект
Product parsed = mapper.readValue(json, Product.class);

// Список объектов
List<Product> list = mapper.readValue(jsonArray,
        new TypeReference<List<Product>>() {});
```

Jackson требует **JavaBean-совместимости** при работе с классами: либо публичные поля, либо геттеры/сеттеры, либо аннотация `@JsonCreator` на конструкторе. Для `record`-ов в Java 16+ Jackson 2.12+ работает автоматически.

---

## Аннотации Jackson 2.x

### Основные аннотации

| Аннотация | Где ставится | Что делает |
|-----------|-------------|------------|
| `@JsonProperty("name")` | поле / геттер | задаёт имя ключа в JSON |
| `@JsonIgnore` | поле / геттер | исключает поле из JSON |
| `@JsonInclude(Include.NON_NULL)` | класс / поле | не включать поле, если значение null |
| `@JsonFormat(pattern="...")` | поле с датой | формат сериализации даты |
| `@JsonCreator` | конструктор / фабричный метод | указывает Jackson, как создавать объект |
| `@JsonAlias({"old_name"})` | поле | принимать несколько имён при десериализации |
| `@JsonAnyGetter` / `@JsonAnySetter` | метод | маппинг динамических ключей в Map |

### Примеры

```java
import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)   // null-поля не выводить в JSON
public class UserDto {

    @JsonProperty("user_name")              // в JSON будет "user_name", не "userName"
    private String userName;

    @JsonIgnore                             // поле никогда не попадёт в JSON
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")     // дата как строка "2024-03-15"
    private LocalDate birthDate;

    // геттеры/сеттеры ...
}
```

```java
// @JsonCreator — десериализация через конструктор без сеттеров (иммутабельный объект)
public class Money {
    private final long amount;
    private final String currency;

    @JsonCreator
    public Money(@JsonProperty("amount")   long amount,
                 @JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
```

---

## Даты: JavaTimeModule

По умолчанию Jackson не умеет работать с `java.time.*`. Нужно подключить модуль:

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
// отключить вывод дат как timestamp (числа); вместо этого — строки ISO-8601
mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```

После этого `LocalDate`, `LocalDateTime`, `ZonedDateTime` сериализуются в читаемые строки.  
`@JsonFormat(pattern = "yyyy-MM-dd")` на поле переопределяет формат индивидуально.

---

## Обработка неизвестных полей

По умолчанию Jackson бросает `UnrecognizedPropertyException`, если в JSON есть поле, которого нет в классе. Это защита от опечаток.

```java
// Отключить глобально (для всего mapper):
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

// Или на уровне класса:
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto { ... }
```

Когда применять `ignoreUnknown = true`:
- потребляете внешний API, который может добавлять новые поля;
- у вас нет полного контроля над схемой JSON.

---

## DTO: зачем отделять от Entity

DTO (Data Transfer Object) — объект, созданный специально для передачи данных между слоями или по сети.

```
HTTP-запрос                Слой сервиса               БД
─────────────              ─────────────               ────
ProductCreateDto           ProductService              ProductEntity
  name: "Кофе"      ──►     toEntity()         ──►      id
  price: 350.0               save()                     name
                             toDto()            ◄──      price
ProductResponseDto    ◄──                                createdAt
  id: 1                                                  updatedAt
  name: "Кофе"                                           deletedAt  (внутреннее)
  price: 350.0
```

| Причина разделения | Объяснение |
|--------------------|------------|
| Скрыть внутренние поля | `createdAt`, `deletedAt`, `version` не нужны клиенту |
| Разные входящие/исходящие данные | при создании нет `id`; при ответе — есть |
| Валидация входных данных | `@NotNull`, `@Size` на DTO, а не на Entity |
| Изоляция контракта API от схемы БД | можно менять схему, не меняя API |
| Безопасность | `password`, `salt` никогда не уходят наружу |

### Маппинг Entity ↔ DTO (вручную)

```java
// Entity (хранится в БД, JPA-аннотации)
public class ProductEntity {
    private Long id;
    private String name;
    private double price;
    private boolean deleted;   // внутреннее — клиенту не нужно
}

// DTO (отдаётся по API)
public class ProductDto {
    private Long id;
    private String name;
    private double price;
}

// Маппер (ручной)
public class ProductMapper {
    public static ProductDto toDto(ProductEntity e) {
        ProductDto dto = new ProductDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setPrice(e.getPrice());
        return dto;
    }

    public static ProductEntity toEntity(ProductDto dto) {
        ProductEntity e = new ProductEntity();
        e.setName(dto.getName());
        e.setPrice(dto.getPrice());
        // id и deleted не трогаем — они управляются БД/бизнес-логикой
        return e;
    }
}
```

В реальных проектах вместо ручного маппинга используют **MapStruct** (генерация кода) или **ModelMapper**.

---

## Jackson 3 (новый namespace)

Jackson 3.x — следующее поколение библиотеки (пока в разработке/preview). Ключевое изменение — **смена namespace**:

```
Jackson 2.x   →   Jackson 3.x
──────────────────────────────────────────────────────────
com.fasterxml.jackson.*   →   tools.jackson.*
```

### Основные отличия Jackson 3

| Область | Jackson 2.x | Jackson 3.x |
|---------|-------------|-------------|
| Главный класс | `com.fasterxml.jackson.databind.ObjectMapper` | `tools.jackson.databind.ObjectMapper` |
| Builder-паттерн | `mapper.configure(...)` мутирует объект | `JsonMapper.builder().enable(...).build()` — иммутабельная конфигурация |
| `JavaTimeModule` | подключается вручную (`registerModule`) | встроен в `tools.jackson.datatype.jsr310` |
| Минимальная Java | Java 8 | Java 17 |
| `record`-поддержка | частичная (с 2.12) | полная, нативная |
| `@JsonProperty` | `com.fasterxml.jackson.annotation` | `tools.jackson.annotation` |

### Пример Jackson 3 (builder)

```java
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.datatype.jsr310.JavaTimeModule;

ObjectMapper mapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .build();
```

**Когда использовать Jackson 3:** пока Spring Boot 3.x использует Jackson 2.x. Jackson 3 придёт вместе со Spring Framework 7 / Spring Boot 4. Для продуктивного кода на Spring Boot 3.x — оставайтесь на Jackson 2.x.

**Миграция 2 → 3:** глобальный поиск/замена `com.fasterxml.jackson` → `tools.jackson` в импортах; замена `new ObjectMapper()` → `JsonMapper.builder().build()`; убрать явный `registerModule(new JavaTimeModule())` если он уже встроен.

---

## Подводные камни

| Ошибка | Причина | Решение |
|--------|---------|---------|
| `UnrecognizedPropertyException` | лишнее поле в JSON | `@JsonIgnoreProperties(ignoreUnknown = true)` |
| `InvalidDefinitionException: No serializer` | нет геттеров, класс не публичный | добавить геттеры или `@JsonProperty` на поля |
| `MismatchedInputException` | неверный JSON-тип (строка вместо числа) | проверить схему, использовать `@JsonProperty` |
| Дата сериализуется как timestamp | не подключён `JavaTimeModule` | `mapper.registerModule(new JavaTimeModule())` |
| `null` поля в JSON | по умолчанию null пишется в JSON | `@JsonInclude(NON_NULL)` на классе/поле |
| `@JsonIgnore` не работает при десериализации | Jackson всё равно пишет поле | добавить `@JsonProperty(access = READ_ONLY)` |
| `TypeReference` для generics | raw type `List.class` теряет тип элемента | `new TypeReference<List<Product>>() {}` |
| Циклические ссылки Entity | `@ManyToOne` ↔ `@OneToMany` → StackOverflow | никогда не сериализовывать Entity напрямую — только DTO |

---

## Дополнительные источники

- Официальная документация: [github.com/FasterXML/jackson-databind](https://github.com/FasterXML/jackson-databind)
- Baeldung: «Jackson ObjectMapper», «Jackson Annotation Examples»
- «Spring Boot Reference» — раздел JSON

## Что дальше

В [модуле 57](../module-57-java-http-client/theory.md) — Java HTTP Client: отправка HTTP-запросов из Java, интеграция с JSON (Jackson) для работы с REST API.
