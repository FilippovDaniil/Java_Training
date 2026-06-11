# Модуль 64. Знакомство со Spring Boot

В [модулях 59–63](../m59_spring_core_intro/theory.md) мы вручную поднимали контейнер Spring (`AnnotationConfigApplicationContext`), сами описывали бины, конфигурацию, профили. **Spring Boot** убирает почти всю эту рутину: достаточно одной аннотации и одной строки `main`, и приложение поднимается само — с веб-сервером, логированием, разумными настройками по умолчанию.

> Практика — задачи в `practice/`. Зависимости: `org.springframework.boot:spring-boot-starter` (+ `spring-boot-starter-web` для эндпоинтов). Bare-`javac` НЕ компилируется без зависимостей — запускайте в IDE/Gradle.

---

## Что такое Spring Boot

Spring Boot — это надстройка над Spring Framework. Его три кита:

| Кит | Что даёт |
|-----|----------|
| **Автоконфигурация** | сам настраивает бины по тому, что есть в classpath (есть `spring-web` → поднимет MVC и Tomcat) |
| **Стартеры** | «пакеты зависимостей» — подключаешь один `starter`, он тянет всё нужное согласованных версий |
| **Встроенный сервер** | Tomcat/Jetty/Undertow внутри jar — не нужен внешний контейнер сервлетов (ср. [модуль 40](../m40_servlet_containers/theory.md)) |

Девиз: **convention over configuration** — соглашения важнее конфигурации. Меньше кода — больше готового поведения.

---

## Точка входа: @SpringBootApplication

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
```

`@SpringBootApplication` — это **три аннотации в одной**:

```
@SpringBootApplication
   │
   ├── @Configuration          ← класс может содержать @Bean (см. модуль 62)
   ├── @EnableAutoConfiguration ← включить автоконфигурацию
   └── @ComponentScan          ← сканировать @Component/@Service/... в этом пакете и ниже
```

> **Важно про расположение.** `@ComponentScan` сканирует пакет главного класса и **все вложенные**. Поэтому главный класс кладут в **корневой пакет** (например, `com.example.shop`), а все остальные — в подпакеты (`com.example.shop.service`, `...web`). Если бин лежит «выше» главного класса — он не будет найден.

---

## Как поднимается приложение

```
 SpringApplication.run(...)
        │
        ▼
 1. создаёт ApplicationContext (для web — сервлетный)
 2. читает classpath → решает, что автонастроить (auto-configuration)
 3. @ComponentScan находит ваши бины
 4. читает application.properties / application.yml
 5. поднимает встроенный Tomcat (если есть spring-web)
 6. вызывает все CommandLineRunner / ApplicationRunner
        │
        ▼
 приложение работает (для web — слушает порт 8080)
```

При старте в консоли появляется **баннер** Spring Boot и строки вида `Started ShopApplication in 1.234 seconds`.

---

## Стартеры (starters)

Стартер — это «метазависимость». Вместо подбора десятка библиотек вручную подключаете один artifact.

| Стартер | Что включает |
|---------|--------------|
| `spring-boot-starter` | ядро: контекст, логирование, автоконфигурация |
| `spring-boot-starter-web` | Spring MVC + REST + встроенный Tomcat + Jackson (JSON) |
| `spring-boot-starter-data-jpa` | Spring Data JPA + Hibernate (см. [модуль 77](../m77_spring_data_jpa_intro/theory.md)) |
| `spring-boot-starter-validation` | Bean Validation (Hibernate Validator) |
| `spring-boot-starter-test` | JUnit 5 + Mockito + Spring Test + MockMvc |
| `spring-boot-starter-actuator` | мониторинг и метрики (см. [модуль 66](../m66_spring_boot_devops/theory.md)) |

Пример (Gradle):

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

Версии библиотек согласует **Spring Boot BOM** (Bill of Materials) — указывать версии каждой библиотеки вручную не нужно.

---

## CommandLineRunner и ApplicationRunner

Чтобы выполнить код **сразу после старта** контекста (сидинг данных, прогрев кэша, демонстрация):

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Приложение запущено! Аргументы: " + args.length);
    }
}
```

| Интерфейс | Сигнатура `run` | Аргументы |
|-----------|-----------------|-----------|
| `CommandLineRunner` | `run(String... args)` | «сырые» аргументы командной строки |
| `ApplicationRunner` | `run(ApplicationArguments args)` | разобранные опции (`--name=value`) |

Можно объявить и через `@Bean` (см. [модуль 62](../m62_spring_core_configuration/theory.md)):

```java
@Bean
CommandLineRunner seed(ProductService service) {
    return args -> service.addInitial();
}
```

---

## application.properties

Файл `src/main/resources/application.properties` (или `application.yml`) — внешняя конфигурация. Spring Boot читает его автоматически.

```properties
# имя приложения и порт сервера
spring.application.name=shop-service
server.port=8080

# свои настройки
shop.currency=RUB
shop.welcome-message=Добро пожаловать в магазин!
```

Чтение значений (см. [модуль 62](../m62_spring_core_configuration/theory.md)):

```java
@Value("${shop.welcome-message}")
private String welcome;
```

---

## Первый REST-эндпоинт

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Привет от Spring Boot!";
    }
}
```

Запустите приложение и откройте `http://localhost:8080/hello`. `@RestController` = `@Controller` + `@ResponseBody`: возвращённое значение пишется прямо в тело ответа (строка — как текст, объект — как JSON). Подробно — в [модуле 65](../m65_spring_boot_web_config/theory.md).

---

## Запуск и упаковка

```
./gradlew bootRun        # запустить из исходников
./gradlew bootJar        # собрать исполняемый "fat jar" со всеми зависимостями
java -jar build/libs/shop-0.0.1.jar   # запустить собранный jar
```

«Fat jar» (uber-jar) содержит и ваш код, и все зависимости, и встроенный Tomcat — этого jar достаточно для запуска на любой машине с JDK.

---

## Spring Boot vs «голый» Spring

| | Голый Spring (модули 59–63) | Spring Boot |
|-|------------------------------|-------------|
| Поднятие контекста | вручную `new AnnotationConfigApplicationContext` | `SpringApplication.run` |
| Веб-сервер | настроить и развернуть в Tomcat вручную | встроенный, из коробки |
| Зависимости | подбирать по одной + следить за версиями | стартеры + BOM |
| Конфигурация | много `@Bean` | автоконфигурация + `application.properties` |
| Точка старта | свой `main` + конфиг-классы | одна аннотация |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Бин не найден / `NoSuchBeanDefinitionException` | класс лежит вне пакета главного класса | держать всё в подпакетах главного класса |
| `Port 8080 was already in use` | порт занят другим процессом | `server.port=8081` или освободить порт |
| Приложение стартует и сразу завершается | нет `spring-web` → нет встроенного сервера, нечего «слушать» | добавить `spring-boot-starter-web` (для веб-приложения) |
| `@Value` подставляет литерал `${...}` | свойства нет в `application.properties` или опечатка | проверить имя ключа и файл ресурсов |
| Несколько `main` в проекте | Boot не знает, какой класс главный | оставить один `@SpringBootApplication` или указать `mainClass` |

---

## Дополнительные источники

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/index.html).
- [Spring Initializr](https://start.spring.io/) — генератор стартового проекта.
- [модуль 54](../m54_gradle_build_tools/theory.md) — Gradle и сборка проектов.
- [модуль 59](../m59_spring_core_intro/theory.md) — IoC/DI, основа, на которой стоит Boot.

## Что дальше

В [модуле 65](../m65_spring_boot_web_config/theory.md) — построение REST-API: `@RestController`, JSON DTO, `@RequestBody`/`@PathVariable`, валидация и единый контракт ошибок через `@ControllerAdvice`.
