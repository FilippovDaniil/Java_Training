# Java — Учебный курс (Core Java → Spring Boot → Docker)

Самодостаточный учебный репозиторий: путь **от основ Java до production-ready микросервиса** на Spring Boot с безопасностью, тестами и контейнеризацией. Построен по принципу **«теория + практика»**: на каждую тему — объяснение (`theory.md`) и **7 задач** с нарастающей сложностью (`Task01 → Task07`, где седьмая — мини-проект).

Курс охватывает **118 модулей** в четырёх частях:

- **Часть 1 (01–28)** — Core Java: синтаксис, ООП, коллекции, исключения, потоки.
- **Часть 2 (29–66)** — инструменты и БД: Maven/Gradle, тесты, SQL, JDBC, Hibernate, Spring Core, Spring Boot.
- **Часть 3 (67–92)** — углублённый Spring: REST/MVC, Spring Data JPA, Hibernate Deep Dive.
- **Часть 4 (93–118)** — production-ready: Spring Security, тестирование Spring Boot, Docker.

Сквозные практические проекты: **Task Tracker API** (REST → JWT → тесты → Docker) и **shop-data-jpa** (Data/Hibernate).

---

## 📁 Структура проекта

```
java-course/
├── README.md                 # этот файл — навигация по курсу
├── .gitignore
└── module-XX-topic-name/      # одна папка на тему
    ├── theory.md              # объяснение темы с примерами кода
    └── practice/              # задачи для самостоятельного решения
        ├── Task01.java        # простая задача из одного класса
        ├── ...
        └── Task07/            # задача со множеством типов → отдельная папка
            ├── Task07.java    #   точка входа (main/тест) + условие в JavaDoc
            ├── Service.java   #   вспомогательные классы/интерфейсы — каждый своим файлом
            └── ...
```

### 📂 Правило: задачи со множеством типов — в своей папке

- Если задача помещается **в один класс** — это файл `TaskNN.java`.
- Если в задаче **несколько top-level типов** (классы, интерфейсы, `record`, `enum`) — она оформлена как **папка `TaskNN/`**, где каждый тип лежит в своём файле. Точка входа — `TaskNN.java` (метод `main` или тест-класс), там же в JavaDoc — условие задачи. Так каждый вспомогательный файл можно дополнять и решать по отдельности.
- **Пакет не объявляется** (default package) — как во всём курсе. Файлы внутри папки компилируются вместе.

```
# компиляция задачи-папки (все файлы вместе):
javac -encoding UTF-8 -d out module-NN-.../practice/TaskNN/*.java
java -cp out TaskNN

# компиляция задачи из одного файла:
javac -encoding UTF-8 TaskNN.java
java TaskNN
```

В IntelliJ IDEA: откройте `TaskNN.java` и нажмите ▶ рядом с `main` (или запустите тест-класс).

---

## 🗺️ Модули курса

### Часть 1 — Core Java (01–28)

| #  | Тема | Каталог |
|----|------|---------|
| 01 | Вводное занятие. Первая программа | [`module-01-intro-first-program`](module-01-intro-first-program/theory.md) |
| 02 | Переменные, типы `int` и `String` | [`module-02-variables-int-string`](module-02-variables-int-string/theory.md) |
| 03 | Типы данных и ввод с клавиатуры. IDEA | [`module-03-types-keyboard-input-idea`](module-03-types-keyboard-input-idea/theory.md) |
| 04 | Условный оператор (`if`, `switch`, тернарный) | [`module-04-conditionals`](module-04-conditionals/theory.md) |
| 05 | Циклы (`while`, `for`, `do-while`, `break`, `continue`) | [`module-05-loops`](module-05-loops/theory.md) |
| 06 | Одномерные массивы | [`module-06-arrays`](module-06-arrays/theory.md) |
| 07 | Многомерные массивы | [`module-07-multidimensional-arrays`](module-07-multidimensional-arrays/theory.md) |
| 08 | Методы: параметры, возврат, рекурсия | [`module-08-methods`](module-08-methods/theory.md) |
| 09 | Строки (`String`, `StringBuilder`, `String.format`) | [`module-09-strings`](module-09-strings/theory.md) |
| 10 | Знакомство с ООП (примитивы, ссылки, `null`) | [`module-10-oop-intro`](module-10-oop-intro/theory.md) |
| 11 | Объекты, конструкторы, инкапсуляция, `equals`/`hashCode` | [`module-11-objects-constructors`](module-11-objects-constructors/theory.md) |
| 12 | Классы и `static`, вложенные классы | [`module-12-classes-static`](module-12-classes-static/theory.md) |
| 13 | Списки и Generics (`ArrayList`, обёртки) | [`module-13-lists-generics`](module-13-lists-generics/theory.md) |
| 14 | `Set`, `HashSet`, итератор, `for-each` | [`module-14-collections-set-iterator`](module-14-collections-set-iterator/theory.md) |
| 15 | `Map`, `HashMap`, `Collections` | [`module-15-map-collections-framework`](module-15-map-collections-framework/theory.md) |
| 16 | Перечисления (`Enum`) и `switch` | [`module-16-enums-switch`](module-16-enums-switch/theory.md) |
| 17 | Исключения (`try-catch`, иерархия, `finally`) | [`module-17-exceptions`](module-17-exceptions/theory.md) |
| 18 | Потоки ввода-вывода (`InputStream`, `Reader`) | [`module-18-io-streams`](module-18-io-streams/theory.md) |
| 19 | `java.nio` (`Path`, `Files`), паттерн Decorator | [`module-19-io-nio`](module-19-io-nio/theory.md) |
| 20 | Дата и время (`java.time`) | [`module-20-date-time`](module-20-date-time/theory.md) |
| 21 | Основы Git | [`module-21-git`](module-21-git/theory.md) |
| 22 | ООП: инкапсуляция, полиморфизм, интерфейсы | [`module-22-oop-encapsulation-polymorphism-interfaces`](module-22-oop-encapsulation-polymorphism-interfaces/theory.md) |
| 23 | ООП: перегрузка, переопределение, абстракция | [`module-23-oop-overloading-overriding-abstract`](module-23-oop-overloading-overriding-abstract/theory.md) |
| 24 | Stream API и лямбда-выражения | [`module-24-stream-api-lambda`](module-24-stream-api-lambda/theory.md) |
| 25 | Многопоточность (`Thread`, `ExecutorService`) | [`module-25-multithreading-concurrency`](module-25-multithreading-concurrency/theory.md) |
| 26 | Reflection API и аннотации | [`module-26-reflection-annotations`](module-26-reflection-annotations/theory.md) |
| 27 | Основы Maven (`pom.xml`, lifecycle) | [`module-27-maven`](module-27-maven/theory.md) |
| 28 | Модульное тестирование с JUnit | [`module-28-junit`](module-28-junit/theory.md) |

### Часть 2 — Инструменты, БД, Spring (29–66)

| #  | Тема | Каталог |
|----|------|---------|
| 29 | GC и типы ссылок (`java.lang.ref`) | [`module-29-gc-reference-types`](module-29-gc-reference-types/theory.md) |
| 30 | Паттерны проектирования | [`module-30-design-patterns`](module-30-design-patterns/theory.md) |
| 31 | Методологии разработки *(теория)* | [`module-31-dev-methodologies`](module-31-dev-methodologies/theory.md) |
| 32 | Maven: многомодульность, профили, плагины | [`module-32-maven-multimodule`](module-32-maven-multimodule/theory.md) |
| 33 | Guava и Apache Commons | [`module-33-guava-apache-commons`](module-33-guava-apache-commons/theory.md) |
| 34 | Тестирование: JUnit 5 + Mockito | [`module-34-testing-junit-mockito`](module-34-testing-junit-mockito/theory.md) |
| 35 | Логирование (SLF4J / Logback) | [`module-35-logging`](module-35-logging/theory.md) |
| 36 | Основы сетей *(теория)* | [`module-36-network-basics`](module-36-network-basics/theory.md) |
| 37 | Архитектура ПО *(теория)* | [`module-37-software-architecture`](module-37-software-architecture/theory.md) |
| 38 | Протокол HTTP (`java.net.http`) | [`module-38-http-protocol`](module-38-http-protocol/theory.md) |
| 39 | Сервлеты *(теория)* | [`module-39-servlets`](module-39-servlets/theory.md) |
| 40 | Контейнеры сервлетов *(теория)* | [`module-40-servlet-containers`](module-40-servlet-containers/theory.md) |
| 41 | MVC и JSP *(теория)* | [`module-41-mvc-jsp`](module-41-mvc-jsp/theory.md) |
| 42 | Веб-сервисы *(теория)* | [`module-42-web-services`](module-42-web-services/theory.md) |
| 43 | HTML и CSS *(теория)* | [`module-43-html-css`](module-43-html-css/theory.md) |
| 44 | Введение в БД (SQL) | [`module-44-databases-intro`](module-44-databases-intro/theory.md) |
| 45 | SQL DML | [`module-45-sql-dml`](module-45-sql-dml/theory.md) |
| 46 | SQL-запросы | [`module-46-sql-queries`](module-46-sql-queries/theory.md) |
| 47 | Транзакции | [`module-47-transactions`](module-47-transactions/theory.md) |
| 48 | Проектирование БД | [`module-48-database-design`](module-48-database-design/theory.md) |
| 49 | JDBC, ч.1 | [`module-49-jdbc-1`](module-49-jdbc-1/theory.md) |
| 50 | JDBC, ч.2 | [`module-50-jdbc-2`](module-50-jdbc-2/theory.md) |
| 51 | Hibernate ORM | [`module-51-hibernate-orm`](module-51-hibernate-orm/theory.md) |
| 52 | Hibernate: связи | [`module-52-hibernate-relationships`](module-52-hibernate-relationships/theory.md) |
| 53 | Hibernate: наследование | [`module-53-hibernate-inheritance`](module-53-hibernate-inheritance/theory.md) |
| 54 | Gradle | [`module-54-gradle-build-tools`](module-54-gradle-build-tools/theory.md) |
| 55 | HTTP basics (`java.net.http`) | [`module-55-http-basics`](module-55-http-basics/theory.md) |
| 56 | JSON, Jackson, DTO | [`module-56-json-jackson-dto`](module-56-json-jackson-dto/theory.md) |
| 57 | Java HTTP Client | [`module-57-java-http-client`](module-57-java-http-client/theory.md) |
| 58 | Ручной HTTP-сервер (JDK) | [`module-58-jdk-httpserver`](module-58-jdk-httpserver/theory.md) |
| 59 | Spring Core: IoC / DI | [`module-59-spring-core-intro`](module-59-spring-core-intro/theory.md) |
| 60 | Spring Core: бины, стереотипы | [`module-60-spring-core-beans`](module-60-spring-core-beans/theory.md) |
| 61 | Spring Core: Qualifier, scopes, lifecycle | [`module-61-spring-core-advanced`](module-61-spring-core-advanced/theory.md) |
| 62 | Spring Core: конфигурация, профили | [`module-62-spring-core-configuration`](module-62-spring-core-configuration/theory.md) |
| 63 | Spring Core: события и AOP | [`module-63-spring-core-events-aop`](module-63-spring-core-events-aop/theory.md) |
| 64 | Spring Boot: введение | [`module-64-spring-boot-intro`](module-64-spring-boot-intro/theory.md) |
| 65 | Spring Boot: web + конфигурация | [`module-65-spring-boot-web-config`](module-65-spring-boot-web-config/theory.md) |
| 66 | Spring Boot: Actuator, тесты, упаковка | [`module-66-spring-boot-devops`](module-66-spring-boot-devops/theory.md) |

### Часть 3 — Spring REST, Data JPA, Hibernate Deep Dive (67–92)

| #  | Тема | Каталог |
|----|------|---------|
| 67 | HTTP глазами backend-разработчика | [`module-67-spring-rest-http-backend`](module-67-spring-rest-http-backend/theory.md) |
| 68 | REST как стиль проектирования API: URI, ресурсы, методы | [`module-68-spring-rest-design`](module-68-spring-rest-design/theory.md) |
| 69 | Spring MVC: обработка запроса, конвертация тела | [`module-69-spring-rest-controllers`](module-69-spring-rest-controllers/theory.md) |
| 70 | DTO, Jackson, JSON, форма ответа | [`module-70-spring-rest-dto-json`](module-70-spring-rest-dto-json/theory.md) |
| 71 | Bean Validation, кастомная валидация, ответ об ошибке | [`module-71-spring-rest-validation`](module-71-spring-rest-validation/theory.md) |
| 72 | ProblemDetail (RFC 7807), `@ControllerAdvice` | [`module-72-spring-rest-error-handling`](module-72-spring-rest-error-handling/theory.md) |
| 73 | Коллекции, пагинация, сортировка, фильтрация | [`module-73-spring-rest-collections`](module-73-spring-rest-collections/theory.md) |
| 74 | CRUD и не-CRUD, загрузка/выгрузка файлов | [`module-74-spring-rest-crud-file`](module-74-spring-rest-crud-file/theory.md) |
| 75 | Конфигурация, версионирование API, OpenAPI/Swagger | [`module-75-spring-rest-config-openapi`](module-75-spring-rest-config-openapi/theory.md) |
| 76 | `@WebMvcTest`, MockMvc, интеграционное тестирование | [`module-76-spring-rest-testing`](module-76-spring-rest-testing/theory.md) |
| 77 | Persistence: JDBC → JPA → Hibernate → Spring Data JPA | [`module-77-spring-data-jpa-intro`](module-77-spring-data-jpa-intro/theory.md) |
| 78 | Entity, ключи, маппинг полей, `@Embeddable` | [`module-78-spring-data-jpa-entity`](module-78-spring-data-jpa-entity/theory.md) |
| 79 | Repository, derived queries, `Page`/`Slice` | [`module-79-spring-data-jpa-repository`](module-79-spring-data-jpa-repository/theory.md) |
| 80 | JPQL, `@Query`, проекции, native queries, Specification | [`module-80-spring-data-jpa-advanced`](module-80-spring-data-jpa-advanced/theory.md) |
| 81 | `@Transactional`, propagation, isolation, контекст | [`module-81-spring-data-jpa-transactions`](module-81-spring-data-jpa-transactions/theory.md) |
| 82 | Lazy loading, N+1, `JOIN FETCH`, `EntityGraph` | [`module-82-spring-data-jpa-lazy-loading`](module-82-spring-data-jpa-lazy-loading/theory.md) |
| 83 | `@DataJpaTest`, H2, обзор Testcontainers | [`module-83-spring-data-jpa-testing`](module-83-spring-data-jpa-testing/theory.md) |
| 84 | Flyway, оптимистичная блокировка, аудит | [`module-84-spring-data-jpa-migrations`](module-84-spring-data-jpa-migrations/theory.md) |
| 85 | Hibernate: жизненный цикл, dirty checking, flush | [`module-85-hibernate-deep-dive-lifecycle`](module-85-hibernate-deep-dive-lifecycle/theory.md) |
| 86 | Hibernate: fetching, N+1, `JOIN FETCH`, проекции | [`module-86-hibernate-deep-dive-fetching`](module-86-hibernate-deep-dive-fetching/theory.md) |
| 87 | Hibernate: HQL, Criteria, native SQL, каскады | [`module-87-hibernate-deep-dive-querying`](module-87-hibernate-deep-dive-querying/theory.md) |
| 88 | Hibernate: `equals`/`hashCode`, value objects, ключи | [`module-88-hibernate-deep-dive-modeling`](module-88-hibernate-deep-dive-modeling/theory.md) |
| 89 | Hibernate: наследование, `@DynamicUpdate` | [`module-89-hibernate-deep-dive-inheritance`](module-89-hibernate-deep-dive-inheritance/theory.md) |
| 90 | Hibernate: блокировки, soft delete, Envers | [`module-90-hibernate-deep-dive-locking`](module-90-hibernate-deep-dive-locking/theory.md) |
| 91 | Hibernate: batching, bulk, read-only, кэширование | [`module-91-hibernate-deep-dive-performance`](module-91-hibernate-deep-dive-performance/theory.md) |
| 92 | Hibernate: SQL-диагностика, анти-паттерны, аудит | [`module-92-hibernate-deep-dive-diagnostics`](module-92-hibernate-deep-dive-diagnostics/theory.md) |

### Часть 4 — Security, Test, Docker (93–118)

| #  | Тема | Каталог |
|----|------|---------|
| 93 | Spring Security: зачем нужен, filter chain | [`module-93-spring-security-intro`](module-93-spring-security-intro/theory.md) |
| 94 | In-memory users, `PasswordEncoder`, form login, CSRF | [`module-94-spring-security-inmemory`](module-94-spring-security-inmemory/theory.md) |
| 95 | Session-based security, HTTP Basic, CORS, cookies | [`module-95-spring-security-session-cors`](module-95-spring-security-session-cors/theory.md) |
| 96 | DB-backed users, `CustomUserDetailsService`, регистрация | [`module-96-spring-security-db-users`](module-96-spring-security-db-users/theory.md) |
| 97 | Request-level и method security, ownership | [`module-97-spring-security-authorization`](module-97-spring-security-authorization/theory.md) |
| 98 | Stateless, JWT: выдача и валидация | [`module-98-spring-security-jwt`](module-98-spring-security-jwt/theory.md) |
| 99 | Custom JWT filter, встроенный Bearer support | [`module-99-spring-security-custom-jwt-filter`](module-99-spring-security-custom-jwt-filter/theory.md) |
| 100 | MockMvc security tests, CSRF/JWT, hardening, audit | [`module-100-spring-security-testing`](module-100-spring-security-testing/theory.md) |
| 101 | JUnit 5, AssertJ, Hamcrest, JSONassert, Mockito | [`module-101-spring-test-basics`](module-101-spring-test-basics/theory.md) |
| 102 | Unit-тесты бизнес-логики без Spring-контекста | [`module-102-spring-test-unit`](module-102-spring-test-unit/theory.md) |
| 103 | Test profiles, properties, управляемая конфигурация | [`module-103-spring-test-config`](module-103-spring-test-config/theory.md) |
| 104 | `@WebMvcTest`, MockMvc, MockMvcTester, JSON tests | [`module-104-spring-test-webmvc`](module-104-spring-test-webmvc/theory.md) |
| 105 | Validation, error handling, pagination, file upload | [`module-105-spring-test-controller-scenarios`](module-105-spring-test-controller-scenarios/theory.md) |
| 106 | `@DataJpaTest`, TestEntityManager, flush, queries | [`module-106-spring-test-datajpa`](module-106-spring-test-datajpa/theory.md) |
| 107 | Lazy loading, конкурентность, Flyway, `@Sql` | [`module-107-spring-test-entity-relationships`](module-107-spring-test-entity-relationships/theory.md) |
| 108 | `@SpringBootTest`, MockMvc, RestTestClient | [`module-108-spring-test-full-context`](module-108-spring-test-full-context/theory.md) |
| 109 | Testcontainers, PostgreSQL, regression suite | [`module-109-spring-test-integration`](module-109-spring-test-integration/theory.md) |
| 110 | Security-тесты, внешние интеграции, async, REST Docs | [`module-110-spring-test-security-async`](module-110-spring-test-security-async/theory.md) |
| 111 | Контейнерное мышление, Docker CLI, первый контейнер | [`module-111-docker-basics`](module-111-docker-basics/theory.md) |
| 112 | Dockerfile, слои, кэш, multi-stage build | [`module-112-docker-dockerfile`](module-112-docker-dockerfile/theory.md) |
| 113 | Layered images, Buildpacks, `bootBuildImage` | [`module-113-docker-spring-boot-image`](module-113-docker-spring-boot-image/theory.md) |
| 114 | Externalized config, profiles, volumes, логи | [`module-114-docker-configuration`](module-114-docker-configuration/theory.md) |
| 115 | Compose: app + PostgreSQL, startup order, readiness | [`module-115-docker-compose`](module-115-docker-compose/theory.md) |
| 116 | +Redis, +RabbitMQ, dev-workflow, troubleshooting | [`module-116-docker-compose-extended`](module-116-docker-compose-extended/theory.md) |
| 117 | JVM под memory/CPU limits, image hygiene, non-root | [`module-117-docker-jvm-tuning`](module-117-docker-jvm-tuning/theory.md) |
| 118 | Финальный reusable template, production-ready образ | [`module-118-docker-final-template`](module-118-docker-final-template/theory.md) |

---

## 🚀 Рекомендации по изучению

1. **Порядок:** идите модуль за модулем сверху вниз — темы выстроены по нарастанию сложности.
2. **Сначала теория:** прочитайте `theory.md`, разберите примеры кода и схемы.
3. **Потом практика:** решайте задачи `Task01 → Task07`. Сложность растёт; седьмая задача — мини-проект, связывающий тему.
4. **ООП — фундамент:** модулям 10, 11, 12, 22, 23 уделите особое внимание.
5. **Готовых решений нет:** в задачах — только условие и шаблон с `// TODO`. Код пишете вы.

---

## ⚙️ Настройка окружения

| Что | Требование |
|-----|------------|
| JDK | 17 LTS (минимум 11; модули 111+ предполагают 17) |
| IDE | IntelliJ IDEA (Community Edition достаточно) |
| Сборка | Core-задачи запускаются напрямую; для Spring/JPA — Gradle (есть в Spring-проектах) |
| Docker | для модулей 111–118 — Docker Desktop / Rancher Desktop |

---

## ℹ️ Форматы задач

Большинство задач — обычные `.java`, которые **компилируются с пустым `main`** и постепенно дописываются. Но часть тем требует особого формата:

| Тип задач | Модули | Как выполнять |
|-----------|--------|---------------|
| Терминальные (Git, Maven) | 21, 27, 32 | Команды в терминале; `.java` лишь несёт условие в JavaDoc |
| SQL-носители | 44–48 | Внутри text-блока `String sql = """ … """;` — пишете SQL, запускаете в H2/любой СУБД |
| Тесты (JUnit/Mockito) | 28, 34, 76, 83, 101–110 | Тест-классы **без `main`**: запускайте в IDE (▶) или `./gradlew test` |
| С зависимостями (Spring, Hibernate, Jackson…) | 33, 35, 51–53, 56, 59–110 | В шапке задачи — «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; запуск в IDE/Gradle (bare-`javac` без classpath не соберёт — это норма) |
| Docker-носители артефактов | 111–118 | Внутри text-блока — `Dockerfile` / `docker-compose.yml` / `application.yml` / CLI-команды; переносите в реальные файлы и выполняйте |
| Только теория | 31, 36, 37, 39–43 | Папки `practice/` нет — только `theory.md` (с глоссарием и схемами) |

> Задачи со множеством типов оформлены как папка `TaskNN/` — см. [правило выше](#-правило-задачи-со-множеством-типов--в-своей-папке).

---

## 🧭 Что дальше (вне курса)

Учебный репозиторий доводит до уровня Junior/Middle Java-разработчика. Дальнейшее развитие: оркестрация (Kubernetes/Helm), CI/CD, мониторинг (Prometheus/Grafana/Loki), распределённые системы.
