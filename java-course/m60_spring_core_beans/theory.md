# Модуль 60. Spring Core: бины, стереотипы, компонентное сканирование

> Предыдущий модуль: [модуль 59 — Spring Core. Введение](../m59_spring_core_intro/theory.md)

Этот модуль посвящён тому, как Spring **находит** классы и **собирает** граф зависимостей автоматически — без ручной регистрации каждого бина.

---

## Стереотипные аннотации

Spring предоставляет набор аннотаций-«ярлыков», которые:
1. помечают класс как **бин** (Spring создаст и зарегистрирует экземпляр),
2. передают **семантический смысл** (слой, роль).

```
       @Component          ← базовая аннотация (любой компонент)
      /    |     \
@Service @Repository @Controller
```

| Аннотация | Слой / роль | Дополнительное поведение |
|-----------|-------------|--------------------------|
| `@Component` | произвольный компонент | только регистрация бина |
| `@Service` | бизнес-логика | то же, семантический маркер |
| `@Repository` | слой доступа к данным | автоматическое преобразование `DataAccessException` |
| `@Controller` | веб-слой (Spring MVC) | регистрация как обработчик HTTP-запросов |

Все четыре — специализации `@Component`. Разница — в семантике и (для `@Repository`/`@Controller`) в дополнительной обработке Spring-инфраструктурой.

---

## @ComponentScan — сканирование пакета

Чтобы Spring нашёл аннотированные классы, нужно указать **пакет для сканирования**.

```java
@Configuration
@ComponentScan(basePackages = "com.example.blog")
public class AppConfig { }
```

Что происходит при старте контекста:

```
  ApplicationContext.start()
         |
         ▼
  @ComponentScan("com.example.blog")
         |
         ▼
  Сканирование classpath --► находит классы с @Component / @Service / @Repository / @Controller
         |
         ▼
  Для каждого класса: создать экземпляр, зарегистрировать в BeanFactory
         |
         ▼
  Разрешить зависимости (@Autowired) --► внедрить нужные бины
         |
         ▼
  Готовый граф бинов (ApplicationContext)
```

Имя бина по умолчанию — имя класса с маленькой буквы (`PostService` → `postService`). Переопределить: `@Component("myName")`.

---

## Виды инъекции зависимостей

Spring поддерживает три способа передать зависимость в бин.

### 1. Инъекция через поле (field injection)

```java
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository; // Spring внедряет напрямую в поле
}
```

### 2. Инъекция через сеттер (setter injection)

```java
@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
```

### 3. Инъекция через конструктор (constructor injection) — предпочтительная

```java
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {  // @Autowired не нужен (единственный конструктор)
        this.postRepository = postRepository;
    }
}
```

### Сравнение способов инъекции

| Критерий | Field | Setter | Constructor |
|----------|-------|--------|-------------|
| Поле может быть `final` | нет | нет | **да** |
| Видна обязательность зависимости | нет | нет | **да** |
| Тестируемость (без Spring) | плохо | средне | **хорошо** |
| Обнаружение цикла зависимостей | на этапе рантайма | на этапе рантайма | **на этапе старта** |
| Лаконичность кода | хорошо | плохо | средне (Lombok упрощает) |

**Вывод:** `constructor injection` — стандарт де-факто для обязательных зависимостей.  
`setter injection` оправдан для **опциональных** зависимостей (зависимость может не быть задана).  
`field injection` — наиболее короткий синтаксис, но наименее тестируемый: поле private, создать объект в unit-тесте без Spring невозможно без рефлексии.

---

## @Autowired

`@Autowired` указывает Spring, что нужно **внедрить** подходящий бин.

- На конструктор: начиная со Spring 4.3 необязательна, если конструктор единственный.
- На поле или сеттер: нужна явно.
- Если бинов-кандидатов нет — `NoSuchBeanDefinitionException`.
- Если кандидатов несколько — `NoUniqueBeanDefinitionException` (решается через `@Qualifier` или `@Primary`).

---

## Инъекция коллекций бинов

Если в контексте зарегистрировано несколько бинов одного интерфейса, Spring может внедрить **все сразу** как коллекцию.

```java
public interface Notifier { void notify(String message); }

@Component public class EmailNotifier implements Notifier { … }
@Component public class SmsNotifier   implements Notifier { … }

@Service
public class NotificationService {
    private final List<Notifier> notifiers;

    public NotificationService(List<Notifier> notifiers) {
        this.notifiers = notifiers;
    }

    public void notifyAll(String message) {
        notifiers.forEach(n -> n.notify(message));
    }
}
```

Порядок элементов в `List<Notifier>` определяется аннотацией `@Order(n)` на реализациях (меньшее значение — первее). Без `@Order` порядок не гарантирован.

---

## Подводные камни

| Проблема | Описание |
|----------|---------|
| **Field injection и тестируемость** | Нельзя передать зависимость через конструктор в unit-тесте — поле приватное. Нужна рефлексия (`ReflectionTestUtils`) или контекст Spring. |
| **Цикличные зависимости** | `A → B → A` через constructor injection вызывает `BeanCurrentlyInCreationException` при старте. Spring не может разрешить цикл. Через field/setter injection Spring разрывает цикл (паттерн «протолкнуть сначала», потом заполнить) — но это симптом плохого дизайна. Детально — в [модуле 61](../m61_spring_core_advanced/theory.md). |
| **@ComponentScan не охватывает пакет** | Бин не зарегистрируется, если его пакет вне `basePackages`. Ошибка — `NoSuchBeanDefinitionException`. |
| **Несколько бинов одного типа без @Primary / @Qualifier** | `NoUniqueBeanDefinitionException`. Добавьте `@Primary` к «главному» или `@Qualifier("name")` на точку инъекции. |

---

## Дополнительные источники

- Официальная документация Spring: [Core — Beans](https://docs.spring.io/spring-framework/reference/core/beans.html)
- Baeldung: [Spring Component Scanning](https://www.baeldung.com/spring-component-scanning)
- Baeldung: [Constructor Injection in Spring](https://www.baeldung.com/constructor-injection-in-spring)

## Что дальше

В [модуле 61](../m61_spring_core_advanced/theory.md) — области видимости бинов (scope), жизненный цикл (`@PostConstruct`, `@PreDestroy`), `@Bean`-методы в `@Configuration`, профили и циклические зависимости подробно.
