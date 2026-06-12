# Модуль 63. События Spring. Основы AOP

В Spring есть два механизма «сквозного» взаимодействия между компонентами:

* **События (ApplicationEvent / @EventListener)** — слабосвязанная коммуникация внутри контейнера.
* **AOP (Aspect-Oriented Programming)** — перехват вызовов методов без изменения их кода.

> Практика — задачи в `practice/`. Зависимости: `spring-context`, `spring-aspects`, `aspectjweaver`, `spring-test` + JUnit 5.

---

## Часть 1. События Spring

### Что такое событие

Событие — это произвольный объект, который **публикуется** одним компонентом и **принимается** другими. Компоненты не знают друг о друге — только о событии.

```
                   +--------------------------------------------+
                   |          ApplicationContext                 |
                   |                                            |
   OrderService --►|-- publish(OrderCreatedEvent) -------------►|--► EmailListener
   (издатель)      |                                            |--► AuditListener
                   |  (контейнер сам доставляет всем слушателям)|
                   +--------------------------------------------+
```

### Встроенные события Spring

| Событие | Когда публикуется |
|---------|------------------|
| `ContextRefreshedEvent` | контекст поднят и инициализирован |
| `ContextClosedEvent` | контекст закрывается |
| `ContextStartedEvent` | вызван `context.start()` |
| `ContextStoppedEvent` | вызван `context.stop()` |
| `RequestHandledEvent` | обработан HTTP-запрос (Spring MVC) |

### Кастомное событие

```java
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {
    private final String orderId;

    public OrderCreatedEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
}
```

> Начиная со Spring 4.2 можно использовать **любой POJO** как событие — без наследования от `ApplicationEvent`.

### Публикация события

```java
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(String orderId) {
        // ... бизнес-логика ...
        publisher.publishEvent(new OrderCreatedEvent(this, orderId));
    }
}
```

### Слушатель события

```java
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener {

    @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("Отправляем письмо для заказа: " + event.getOrderId());
    }
}
```

### Асинхронные события

По умолчанию события **синхронные** — слушатель вызывается в том же потоке, что и издатель. Для асинхронности:

```java
// Включить поддержку асинхронности в конфигурации:
@Configuration
@EnableAsync
public class AppConfig { ... }

// Пометить слушатель:
@Async
@EventListener
public void onOrderCreated(OrderCreatedEvent event) { ... }
```

### Синхронные vs асинхронные события

| Характеристика | Синхронные | Асинхронные (`@Async`) |
|----------------|-----------|------------------------|
| Поток выполнения | тот же, что у издателя | отдельный поток из пула |
| Транзакция | общая с издателем | отдельная |
| Обработка ошибок | исключение доходит до издателя | не доходит (только лог) |
| Применение | аудит, валидация | уведомления, тяжёлые операции |

### События vs прямой вызов

| | Прямой вызов метода | Событие |
|-|---------------------|---------|
| Связанность | высокая (знает о конкретном сервисе) | низкая (знает только о событии) |
| Несколько получателей | нужно явно вызывать каждого | все слушатели уведомляются автоматически |
| Добавление нового реакции | изменить издателя | добавить новый `@EventListener` |

---

## Часть 2. AOP — аспектно-ориентированное программирование

### Сквозная функциональность (cross-cutting concerns)

Некоторые задачи повторяются в каждом слое приложения: логирование, транзакции, аудит, кэширование, контроль доступа. Их называют **сквозными**. Без AOP код засоряется одинаковыми блоками во всех сервисах.

AOP позволяет вынести эту логику в один **аспект** и применять его декларативно.

### Как работает AOP-прокси

Spring AOP работает через **прокси-объект**: вместо реального бина в контейнер помещается обёртка, которая перехватывает вызовы и выполняет advice.

```
                    +-----------------------------------------+
   Вызывающий       |  Spring-контейнер                       |
   код              |                                         |
   --------------►  |  +----------+       +--------------+   |
   myService.foo()  |  |  Прокси  |------►| LoggingAspect|   |
                    |  |          | advice | (@Before foo) |   |
                    |  |          |◄------+               |   |
                    |  |          |       +--------------+   |
                    |  |          |------►+--------------+   |
                    |  |          |       | Реальный бин |   |
                    |  +----------+       |  MyService   |   |
                    |                     +--------------+   |
                    +-----------------------------------------+
```

### Виды прокси

| Тип | Когда используется | Ограничение |
|-----|-------------------|-------------|
| JDK Dynamic Proxy | класс реализует интерфейс | только публичные методы интерфейса |
| CGLIB Proxy | нет интерфейса (или `proxyTargetClass = true`) | нельзя перехватить `final`-методы |

### Ключевые понятия AOP

| Термин | Значение |
|--------|---------|
| **Aspect** | класс, содержащий сквозную логику |
| **Advice** | конкретный перехватчик (что делать и когда) |
| **Pointcut** | выражение, описывающее _где_ применять advice |
| **Join point** | конкретная точка вызова метода во время выполнения |
| **Weaving** | процесс внедрения аспекта в целевой код |

### Типы advice

| Аннотация | Когда выполняется |
|-----------|------------------|
| `@Before` | до выполнения метода |
| `@After` | после (и при успехе, и при исключении) |
| `@AfterReturning` | после успешного возврата |
| `@AfterThrowing` | если метод бросил исключение |
| `@Around` | полный контроль: до, вместо и после (самый мощный) |

### Пример: аспект логирования

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // pointcut: все публичные методы в пакете com.example.service
    @Before("execution(public * com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Вызов: " + joinPoint.getSignature().getName());
    }
}
```

### @Around — полный контроль

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@Around("execution(* com.example.service.*.*(..))")
public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    Object result = pjp.proceed();  // вызвать реальный метод
    long elapsed = System.nanoTime() - start;
    System.out.println(pjp.getSignature().getName() + " заняло " + elapsed + " нс");
    return result;
}
```

### Синтаксис pointcut-выражений

```
execution( [модификатор] возвращаемый_тип [класс.]метод(аргументы) )

Примеры:
  execution(* com.example.service.OrderService.*(..))   — все методы OrderService
  execution(public * com.example.service.*.*(..))       — все публичные методы в пакете
  execution(* *..*Service.*(..))                        — все методы классов, оканчивающихся на Service
  execution(* com.example..*.*(..))                     — все методы во всех подпакетах
  @annotation(org.springframework.transaction.annotation.Transactional) — методы с аннотацией
```

---

## Часть 3. Тестирование Spring-контекста

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppConfig.class)  // загружает контекст из AppConfig
class OrderServiceTest {

    @Autowired
    OrderService orderService;  // бин инжектируется прямо в тест

    @Test
    void orderCreatesSuccessfully() {
        // TODO: вызвать сервис и проверить результат
    }
}
```

| Аннотация | Описание |
|-----------|---------|
| `@SpringJUnitConfig` | объединяет `@ExtendWith(SpringExtension.class)` + `@ContextConfiguration` |
| `@ContextConfiguration` | явно задаёт конфигурационные классы/XML |
| `@Autowired` в тест-классе | инъекция бинов из поднятого контекста |
| `@DirtiesContext` | сбросить контекст после теста (если состояние изменено) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| **Self-invocation не перехватывается** | метод вызывает другой метод _того же класса_ — прокси обходится | вынести метод в отдельный бин или использовать `ApplicationContext.getBean()` |
| **Прокси только для бинов** | AOP работает только с объектами, управляемыми Spring | создавать объекты через контейнер (`@Component` / `@Bean`), не через `new` |
| **`final`-методы не перехватываются** | CGLIB не может переопределить `final` | убрать `final` с методов, которые должны перехватываться |
| **Асинхронные события и транзакции** | `@Async` создаёт новую транзакцию, изменения из исходной ещё не закоммичены | использовать `@TransactionalEventListener(phase = AFTER_COMMIT)` |
| **Порядок нескольких аспектов** | не определён по умолчанию | добавить `@Order(n)` на аспект |

---

## Дополнительные источники

- Официальная документация Spring: [Events](https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-events) и [AOP](https://docs.spring.io/spring-framework/reference/core/aop.html).
- «Spring in Action» (Craig Walls) — главы по AOP и событиям.
- [модуль 62](../m62_spring_core_configuration/theory.md) — конфигурация Spring-контейнера (`@Configuration`, `@Bean`, `@ComponentScan`).
- [модуль 26](../m26_reflection_annotations/theory.md) — рефлексия и аннотации (основа механизма прокси).

## Что дальше

В [модуле 64](../m64_spring_boot_intro/theory.md) — Spring Boot: автоконфигурация, стартеры, `application.properties`, встроенный сервер.
