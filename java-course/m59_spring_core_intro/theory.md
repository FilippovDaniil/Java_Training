# Модуль 59. Введение в Spring Core: IoC, DI, ApplicationContext

Spring Framework — фундамент современной Java-разработки. Его ядро решает одну задачу: **избавить разработчика от ручного управления зависимостями** между объектами.

> Практика — Java-конфигурация (@Configuration/@Bean) и XML-конфигурация. Зависимость: `org.springframework:spring-context:6.1.x`.

---

## Проблема: ручное управление зависимостями

Представьте типичный сервисный слой без фреймворков:

```
    ┌─────────────────────────────────────────┐
    │              main()                      │
    │                                          │
    │  ProductRepository repo =                │
    │      new JdbcProductRepository(ds);      │
    │                                          │
    │  ProductService svc =                    │
    │      new ProductService(repo);           │
    │                                          │
    │  OrderService order =                    │
    │      new OrderService(svc);              │
    └─────────────────────────────────────────┘
```

Каждый новый объект создаётся вручную. При замене `JdbcProductRepository` на `CacheProductRepository` придётся менять все места создания. Код main (или конфигурационный класс) **знает о деталях реализации** всех компонентов — это жёсткая связанность.

---

## Inversion of Control (IoC)

**Инверсия управления** — принцип, при котором объект **не создаёт** свои зависимости сам; вместо этого управление передаётся внешнему компоненту (контейнеру).

```
БЕЗ IoC                          С IoC (Spring)
─────────────────────────        ──────────────────────────────
OrderService сам создаёт         Контейнер создаёт все объекты
ProductService и репозиторий  →  и передаёт их тем, кто нуждается
(OrderService контролирует)      (контейнер контролирует)
```

> «Не звони нам — мы позвоним тебе» (Hollywood Principle).

---

## Dependency Injection (DI)

**Внедрение зависимостей** — конкретная реализация IoC: зависимости передаются объекту **снаружи** (через конструктор, сеттер или поле), а не создаются внутри.

| Вид DI | Как выглядит | Когда применять |
|--------|-------------|-----------------|
| **Конструктор** | `new Service(repo)` / `@Autowired` на конструкторе | Предпочтительный; зависимости обязательны; удобен для тестов |
| **Сеттер** | `setRepo(repo)` / `@Autowired` на методе | Необязательные зависимости; позволяет переопределить |
| **Поле** | `@Autowired` прямо на поле | Удобно, но плохо для тестов (нет доступа без рефлексии) |

Spring построен на механизме рефлексии Java — подробнее в [модуле 26](../m26_reflection_annotations/theory.md).

---

## ApplicationContext — контейнер бинов

**Бин (Bean)** — объект, жизненным циклом которого управляет Spring-контейнер.

**ApplicationContext** — главный интерфейс контейнера. Он:
1. Читает конфигурацию (Java-класс или XML).
2. Создаёт бины в нужном порядке.
3. Внедряет зависимости.
4. Управляет жизненным циклом (init, destroy).

```
         ┌──────────────────────────────────────────────┐
         │            ApplicationContext                  │
         │                                               │
  конфиг │  ┌─────────────┐   ┌──────────────────────┐  │
 ──────► │  │ BeanFactory │   │  ProductRepository   │  │
  (@Conf │  │  (создаёт)  │──►│  (singleton-экземпл) │  │
  или    │  └─────────────┘   └──────────┬───────────┘  │
  beans  │                               │ inject       │
  .xml)  │                    ┌──────────▼───────────┐  │
         │                    │    ProductService     │  │
         │                    │  (singleton-экземпл) │  │
         │                    └──────────────────────┘  │
         └──────────────────────────────────────────────┘
                  getBean(ProductService.class) → объект
```

| Реализация ApplicationContext | Источник конфигурации |
|------------------------------|-----------------------|
| `AnnotationConfigApplicationContext` | Java-класс с `@Configuration` |
| `ClassPathXmlApplicationContext` | XML-файл в classpath |
| `FileSystemXmlApplicationContext` | XML-файл по абсолютному пути |
| `WebApplicationContext` | Веб-приложения (Spring MVC) |

---

## Java-конфигурация: @Configuration и @Bean

Современный и предпочтительный способ — описать бины прямо в Java-классе:

```java
@Configuration          // <-- этот класс является источником конфигурации
public class AppConfig {

    @Bean               // <-- метод возвращает бин; имя бина = имя метода
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    public ProductService productService() {
        // Spring вызовет productRepository() и получит тот же singleton
        return new ProductService(productRepository());
    }
}
```

Подъём контейнера и получение бина:

```java
ApplicationContext ctx =
    new AnnotationConfigApplicationContext(AppConfig.class);

ProductService svc = ctx.getBean(ProductService.class);
svc.list(); // объект создан контейнером
```

---

## XML-конфигурация: beans.xml

Старый, но важный формат (часто встречается в legacy-проектах):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
         http://www.springframework.org/schema/beans
         https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="productRepository"
          class="com.example.InMemoryProductRepository"/>

    <bean id="productService"
          class="com.example.ProductService">
        <constructor-arg ref="productRepository"/>
    </bean>

</beans>
```

Подъём через XML:

```java
ApplicationContext ctx =
    new ClassPathXmlApplicationContext("beans.xml");
ProductService svc = ctx.getBean("productService", ProductService.class);
```

| Параметр | Java-конфиг | XML-конфиг |
|----------|-------------|------------|
| Рефакторинг IDE | полная поддержка | ограничена |
| Опечатки | ловит компилятор | ловит только runtime |
| Разделение конфига и кода | нет (код = конфиг) | есть |
| Распространённость в новых проектах | доминирует | редко |

---

## Жизненный цикл бина (упрощённо)

```
1. Создание экземпляра (конструктор)
2. Внедрение зависимостей (DI)
3. Инициализация (@PostConstruct / init-method)
4. Бин готов — используется приложением
5. Уничтожение (@PreDestroy / destroy-method) при закрытии контекста
```

По умолчанию каждый бин — **синглтон**: контейнер создаёт один экземпляр и возвращает его всем запрашивающим.

---

## Подводные камни

| Ошибка | Объяснение |
|--------|------------|
| Забыть `@Configuration` | Класс не будет обработан как источник бинов |
| Создать бин через `new` вне контейнера | Зависимости не будут внедрены; Spring не знает об объекте |
| `NoSuchBeanDefinitionException` | Бин не зарегистрирован или запрошен неверный тип/имя |
| `NoUniqueBeanDefinitionException` | Два бина одного типа — Spring не знает, какой выбрать |
| Циклические зависимости | A зависит от B, B — от A; Spring выбросит исключение при конструкторном DI |
| Закрыть контекст вручную | Лучше использовать try-with-resources: `try (var ctx = new AnnotationConfigApplicationContext(…))` |

---

## Дополнительные источники

- Официальная документация Spring: https://docs.spring.io/spring-framework/reference/
- «Spring in Action» (Craig Walls) — глава 1–2.
- «Pro Spring 6» (Iuliana Cosmina и др.).

## Что дальше

В [модуле 60](../m60_spring_core_beans/theory.md) — жизненный цикл бинов, области видимости (scope), @ComponentScan и стереотипные аннотации (@Component, @Service, @Repository).
