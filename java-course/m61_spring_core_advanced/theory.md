# Модуль 61. Spring Core Advanced: квалификаторы, области видимости, жизненный цикл, циклические зависимости

> Предварительные знания: [модуль 60](../m60_spring_core_beans/theory.md) — основы IoC-контейнера, `@Component`, `@Autowired`, `AnnotationConfigApplicationContext`.

---

## 1. Несколько реализаций одного интерфейса: конфликт кандидатов

Если в контексте зарегистрированы две реализации одного интерфейса, Spring не знает, какую внедрить, и выбрасывает `NoUniqueBeanDefinitionException`.

```
       Интерфейс Notifier
       /         \
EmailNotifier   SmsNotifier
      ↑               ↑
  @Component      @Component

   NotificationService
   @Autowired Notifier notifier  ← кто победит?
```

### 1.1. Разрешение конфликта — три способа

| Способ | Аннотация | Когда применять |
|--------|-----------|-----------------|
| Квалификатор | `@Qualifier("beanName")` | нужен конкретный бин, явно |
| Основной | `@Primary` | один бин — «умолчание», остальные — исключения |
| По имени поля | имя поля == имя бина | неявно, работает, но хрупко |

**`@Primary`** помечает одну реализацию как предпочтительную — Spring внедрит её, если квалификатор не указан.

**`@Qualifier`** точечно выбирает нужный бин:

```java
@Autowired
@Qualifier("smsNotifier")
private Notifier notifier;
```

Схема разрешения:

```
@Autowired Notifier notifier
        |
        ▼
  Есть @Qualifier? --► Да --► внедрить бин с этим именем
        |
        ▼ Нет
  Есть @Primary? --► Да --► внедрить помеченный @Primary
        |
        ▼ Нет
  Имя поля совпадает с именем бина? --► Да --► внедрить
        |
        ▼ Нет
  NoUniqueBeanDefinitionException
```

---

## 2. Области видимости бинов (Bean Scopes)

| Область | Аннотация | Создаётся | Количество |
|---------|-----------|-----------|------------|
| **singleton** | `@Scope("singleton")` (умолчание) | при старте контекста | один на контекст |
| **prototype** | `@Scope("prototype")` | при каждом запросе | новый каждый раз |
| **request** | `@Scope("request")` | на каждый HTTP-запрос | один на запрос |
| **session** | `@Scope("session")` | на HTTP-сессию | один на сессию |
| **application** | `@Scope("application")` | на всё приложение | один |

> Web-области (request, session, application) доступны только в Spring MVC/WebFlux контексте. В standalone-приложении (`AnnotationConfigApplicationContext`) их использовать нельзя.

**Singleton vs Prototype:**

```
Singleton:                   Prototype:
  context.getBean(A)           context.getBean(A)
       |                            |
       +--► один объект             +--► новый объект
  context.getBean(A) --► тот же    context.getBean(A) --► другой объект
```

---

## 3. @Lazy — отложенная инициализация

По умолчанию singleton-бины создаются **при старте** контекста. `@Lazy` откладывает создание до первого обращения.

```java
@Component
@Lazy
public class HeavyReportService { ... }
```

Применимо:
- к классу — ленивый сам бин;
- к `@Autowired` полю/конструктору — ленивый конкретный inject-point.

**Когда использовать:** дорогая инициализация (соединение с внешним сервисом, загрузка большого ресурса), нужна только иногда.

---

## 4. Жизненный цикл бина

```
  Контейнер находит класс (@Component / @Bean)
              |
              ▼
  Создание объекта (конструктор)
              |
              ▼
  Инъекция зависимостей (setters / fields / constructor)
              |
              ▼
  @PostConstruct --► инициализация (открыть соединение, загрузить кеш)
              |
              ▼
    БИЗНЕС-ЛОГИКА (бин используется)
              |
              ▼
  context.close() / shutdown
              |
              ▼
  @PreDestroy --► очистка (закрыть соединение, сохранить состояние)
              |
              ▼
    Объект уничтожен сборщиком мусора
```

### Способы подключить колбэки жизненного цикла

| Способ | Инициализация | Деструкция |
|--------|---------------|------------|
| Аннотации (рекомендуется) | `@PostConstruct` | `@PreDestroy` |
| Интерфейсы Spring | `InitializingBean.afterPropertiesSet()` | `DisposableBean.destroy()` |
| XML / @Bean атрибуты | `initMethod = "init"` | `destroyMethod = "cleanup"` |

> `@PostConstruct` / `@PreDestroy` — стандарт Jakarta EE (пакет `jakarta.annotation`). Spring поддерживает их без дополнительных зависимостей.

**Важно:** `@PreDestroy` вызывается **только для singleton**-бинов. Для prototype Spring создаёт объект и «забывает» о нём — очистку нужно делать вручную.

---

## 5. Циклические зависимости

Цикл возникает, когда A зависит от B, а B — от A (или длиннее: A → B → C → A).

### 5.1. Конструкторная инъекция — всегда ошибка

```java
@Component class A {
    A(B b) { ... }   // A создаётся → нужен B
}
@Component class B {
    B(A a) { ... }   // B создаётся → нужен A → тупик
}
```

Spring выбросит `BeanCurrentlyInCreationException` сразу при старте.

### 5.2. Field / setter инъекция — исторически работало

Ранние версии Spring (до 6.x) разрешали цикл через поля: контейнер создавал объекты, затем инжектировал ссылки. Начиная с Spring 6, это поведение **по умолчанию отключено** (`spring.main.allow-circular-references=false`).

### 5.3. Как устранить цикл

| Способ | Суть |
|--------|------|
| Рефакторинг | выделить общую зависимость в третий бин C |
| `@Lazy` на одной точке инъекции | прокси создаётся сразу, реальный объект — при первом вызове |
| Setter-инъекция вместо конструктора | разрывает цикл в момент создания |
| Переход к Event/Observer | A не зависит от B напрямую, публикует событие |

**Рекомендация:** цикл — почти всегда сигнал о плохом дизайне. Сначала попробуйте рефакторинг.

---

## Подводные камни

| Ситуация | Проблема |
|----------|----------|
| Prototype-бин внутри Singleton | Singleton создаётся один раз → prototype-зависимость тоже одна на всё время жизни. Нужен `ApplicationContext.getBean()` или `@Lookup`. |
| `@PreDestroy` не вызывается | Prototype не управляется контейнером после создания — деструкция не срабатывает. |
| `@Lazy` на Singleton с циклом | Решает только конкретный вид цикла через field-inject; конструкторный цикл не разрешается. |
| `@Qualifier` с неверным именем | `NoSuchBeanDefinitionException` в рантайме; имя чувствительно к регистру. |
| Цикл через конструктор в Spring 6 | Даже `spring.main.allow-circular-references=true` не поможет для конструкторного цикла — только рефакторинг. |

---

## Дополнительные источники

- Spring Framework Docs: [Core — Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-dependencies)
- Spring Framework Docs: [Bean Scopes](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)
- Spring Framework Docs: [Lifecycle Callbacks](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-lifecycle)

---

## Что дальше

В [модуле 62](../m62_spring_core_configuration/theory.md) — конфигурация через `@Configuration` / `@Bean`, профили (`@Profile`), внешние свойства (`@Value`, `@ConfigurationProperties`).
