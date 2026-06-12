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
+-- README.md                 # этот файл — навигация по курсу
+-- .gitignore
+-- module-XX-topic-name/      # одна папка на тему
    +-- theory.md              # объяснение темы с примерами кода
    +-- practice/              # задачи для самостоятельного решения
        +-- Task01.java        # простая задача из одного класса
        +-- ...
        +-- Task07/            # задача со множеством типов → отдельная папка
            +-- Task07.java    #   точка входа (main/тест) + условие в JavaDoc
            +-- Service.java   #   вспомогательные классы/интерфейсы — каждый своим файлом
            +-- ...
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
| 01 | Вводное занятие. Первая программа | [`m01_intro_first_program`](m01_intro_first_program/theory.md) |
| 02 | Переменные, типы `int` и `String` | [`m02_variables_int_string`](m02_variables_int_string/theory.md) |
| 03 | Типы данных и ввод с клавиатуры. IDEA | [`m03_types_keyboard_input_idea`](m03_types_keyboard_input_idea/theory.md) |
| 04 | Условный оператор (`if`, `switch`, тернарный) | [`m04_conditionals`](m04_conditionals/theory.md) |
| 05 | Циклы (`while`, `for`, `do-while`, `break`, `continue`) | [`m05_loops`](m05_loops/theory.md) |
| 06 | Одномерные массивы | [`m06_arrays`](m06_arrays/theory.md) |
| 07 | Многомерные массивы | [`m07_multidimensional_arrays`](m07_multidimensional_arrays/theory.md) |
| 08 | Методы: параметры, возврат, рекурсия | [`m08_methods`](m08_methods/theory.md) |
| 09 | Строки (`String`, `StringBuilder`, `String.format`) | [`m09_strings`](m09_strings/theory.md) |
| 10 | Знакомство с ООП (примитивы, ссылки, `null`) | [`m10_oop_intro`](m10_oop_intro/theory.md) |
| 11 | Объекты, конструкторы, инкапсуляция, `equals`/`hashCode` | [`m11_objects_constructors`](m11_objects_constructors/theory.md) |
| 12 | Классы и `static`, вложенные классы | [`m12_classes_static`](m12_classes_static/theory.md) |
| 13 | Списки и Generics (`ArrayList`, обёртки) | [`m13_lists_generics`](m13_lists_generics/theory.md) |
| 14 | `Set`, `HashSet`, итератор, `for-each` | [`m14_collections_set_iterator`](m14_collections_set_iterator/theory.md) |
| 15 | `Map`, `HashMap`, `Collections` | [`m15_map_collections_framework`](m15_map_collections_framework/theory.md) |
| 16 | Перечисления (`Enum`) и `switch` | [`m16_enums_switch`](m16_enums_switch/theory.md) |
| 17 | Исключения (`try-catch`, иерархия, `finally`) | [`m17_exceptions`](m17_exceptions/theory.md) |
| 18 | Потоки ввода-вывода (`InputStream`, `Reader`) | [`m18_io_streams`](m18_io_streams/theory.md) |
| 19 | `java.nio` (`Path`, `Files`), паттерн Decorator | [`m19_io_nio`](m19_io_nio/theory.md) |
| 20 | Дата и время (`java.time`) | [`m20_date_time`](m20_date_time/theory.md) |
| 21 | Основы Git | [`m21_git`](m21_git/theory.md) |
| 22 | ООП: инкапсуляция, полиморфизм, интерфейсы | [`m22_oop_encapsulation_polymorphism_interfaces`](m22_oop_encapsulation_polymorphism_interfaces/theory.md) |
| 23 | ООП: перегрузка, переопределение, абстракция | [`m23_oop_overloading_overriding_abstract`](m23_oop_overloading_overriding_abstract/theory.md) |
| 24 | Stream API и лямбда-выражения | [`m24_stream_api_lambda`](m24_stream_api_lambda/theory.md) |
| 25 | Многопоточность (`Thread`, `ExecutorService`) | [`m25_multithreading_concurrency`](m25_multithreading_concurrency/theory.md) |
| 26 | Reflection API и аннотации | [`m26_reflection_annotations`](m26_reflection_annotations/theory.md) |
| 27 | Основы Maven (`pom.xml`, lifecycle) | [`m27_maven`](m27_maven/theory.md) |
| 28 | Модульное тестирование с JUnit | [`m28_junit`](m28_junit/theory.md) |

### Часть 2 — Инструменты, БД, Spring (29–66)

| #  | Тема | Каталог |
|----|------|---------|
| 29 | GC и типы ссылок (`java.lang.ref`) | [`m29_gc_reference_types`](m29_gc_reference_types/theory.md) |
| 30 | Паттерны проектирования | [`m30_design_patterns`](m30_design_patterns/theory.md) |
| 31 | Методологии разработки *(теория)* | [`m31_dev_methodologies`](m31_dev_methodologies/theory.md) |
| 32 | Maven: многомодульность, профили, плагины | [`m32_maven_multimodule`](m32_maven_multimodule/theory.md) |
| 33 | Guava и Apache Commons | [`m33_guava_apache_commons`](m33_guava_apache_commons/theory.md) |
| 34 | Тестирование: JUnit 5 + Mockito | [`m34_testing_junit_mockito`](m34_testing_junit_mockito/theory.md) |
| 35 | Логирование (SLF4J / Logback) | [`m35_logging`](m35_logging/theory.md) |
| 36 | Основы сетей *(теория)* | [`m36_network_basics`](m36_network_basics/theory.md) |
| 37 | Архитектура ПО *(теория)* | [`m37_software_architecture`](m37_software_architecture/theory.md) |
| 38 | Протокол HTTP (`java.net.http`) | [`m38_http_protocol`](m38_http_protocol/theory.md) |
| 39 | Сервлеты *(теория)* | [`m39_servlets`](m39_servlets/theory.md) |
| 40 | Контейнеры сервлетов *(теория)* | [`m40_servlet_containers`](m40_servlet_containers/theory.md) |
| 41 | MVC и JSP *(теория)* | [`m41_mvc_jsp`](m41_mvc_jsp/theory.md) |
| 42 | Веб-сервисы *(теория)* | [`m42_web_services`](m42_web_services/theory.md) |
| 43 | HTML и CSS *(теория)* | [`m43_html_css`](m43_html_css/theory.md) |
| 44 | Введение в БД (SQL) | [`m44_databases_intro`](m44_databases_intro/theory.md) |
| 45 | SQL DML | [`m45_sql_dml`](m45_sql_dml/theory.md) |
| 46 | SQL-запросы | [`m46_sql_queries`](m46_sql_queries/theory.md) |
| 47 | Транзакции | [`m47_transactions`](m47_transactions/theory.md) |
| 48 | Проектирование БД | [`m48_database_design`](m48_database_design/theory.md) |
| 49 | JDBC, ч.1 | [`m49_jdbc_1`](m49_jdbc_1/theory.md) |
| 50 | JDBC, ч.2 | [`m50_jdbc_2`](m50_jdbc_2/theory.md) |
| 51 | Hibernate ORM | [`m51_hibernate_orm`](m51_hibernate_orm/theory.md) |
| 52 | Hibernate: связи | [`m52_hibernate_relationships`](m52_hibernate_relationships/theory.md) |
| 53 | Hibernate: наследование | [`m53_hibernate_inheritance`](m53_hibernate_inheritance/theory.md) |
| 54 | Gradle | [`m54_gradle_build_tools`](m54_gradle_build_tools/theory.md) |
| 55 | HTTP basics (`java.net.http`) | [`m55_http_basics`](m55_http_basics/theory.md) |
| 56 | JSON, Jackson, DTO | [`m56_json_jackson_dto`](m56_json_jackson_dto/theory.md) |
| 57 | Java HTTP Client | [`m57_java_http_client`](m57_java_http_client/theory.md) |
| 58 | Ручной HTTP-сервер (JDK) | [`m58_jdk_httpserver`](m58_jdk_httpserver/theory.md) |
| 59 | Spring Core: IoC / DI | [`m59_spring_core_intro`](m59_spring_core_intro/theory.md) |
| 60 | Spring Core: бины, стереотипы | [`m60_spring_core_beans`](m60_spring_core_beans/theory.md) |
| 61 | Spring Core: Qualifier, scopes, lifecycle | [`m61_spring_core_advanced`](m61_spring_core_advanced/theory.md) |
| 62 | Spring Core: конфигурация, профили | [`m62_spring_core_configuration`](m62_spring_core_configuration/theory.md) |
| 63 | Spring Core: события и AOP | [`m63_spring_core_events_aop`](m63_spring_core_events_aop/theory.md) |
| 64 | Spring Boot: введение | [`m64_spring_boot_intro`](m64_spring_boot_intro/theory.md) |
| 65 | Spring Boot: web + конфигурация | [`m65_spring_boot_web_config`](m65_spring_boot_web_config/theory.md) |
| 66 | Spring Boot: Actuator, тесты, упаковка | [`m66_spring_boot_devops`](m66_spring_boot_devops/theory.md) |

### Часть 3 — Spring REST, Data JPA, Hibernate Deep Dive (67–92)

| #  | Тема | Каталог |
|----|------|---------|
| 67 | HTTP глазами backend-разработчика | [`m67_spring_rest_http_backend`](m67_spring_rest_http_backend/theory.md) |
| 68 | REST как стиль проектирования API: URI, ресурсы, методы | [`m68_spring_rest_design`](m68_spring_rest_design/theory.md) |
| 69 | Spring MVC: обработка запроса, конвертация тела | [`m69_spring_rest_controllers`](m69_spring_rest_controllers/theory.md) |
| 70 | DTO, Jackson, JSON, форма ответа | [`m70_spring_rest_dto_json`](m70_spring_rest_dto_json/theory.md) |
| 71 | Bean Validation, кастомная валидация, ответ об ошибке | [`m71_spring_rest_validation`](m71_spring_rest_validation/theory.md) |
| 72 | ProblemDetail (RFC 7807), `@ControllerAdvice` | [`m72_spring_rest_error_handling`](m72_spring_rest_error_handling/theory.md) |
| 73 | Коллекции, пагинация, сортировка, фильтрация | [`m73_spring_rest_collections`](m73_spring_rest_collections/theory.md) |
| 74 | CRUD и не-CRUD, загрузка/выгрузка файлов | [`m74_spring_rest_crud_file`](m74_spring_rest_crud_file/theory.md) |
| 75 | Конфигурация, версионирование API, OpenAPI/Swagger | [`m75_spring_rest_config_openapi`](m75_spring_rest_config_openapi/theory.md) |
| 76 | `@WebMvcTest`, MockMvc, интеграционное тестирование | [`m76_spring_rest_testing`](m76_spring_rest_testing/theory.md) |
| 77 | Persistence: JDBC → JPA → Hibernate → Spring Data JPA | [`m77_spring_data_jpa_intro`](m77_spring_data_jpa_intro/theory.md) |
| 78 | Entity, ключи, маппинг полей, `@Embeddable` | [`m78_spring_data_jpa_entity`](m78_spring_data_jpa_entity/theory.md) |
| 79 | Repository, derived queries, `Page`/`Slice` | [`m79_spring_data_jpa_repository`](m79_spring_data_jpa_repository/theory.md) |
| 80 | JPQL, `@Query`, проекции, native queries, Specification | [`m80_spring_data_jpa_advanced`](m80_spring_data_jpa_advanced/theory.md) |
| 81 | `@Transactional`, propagation, isolation, контекст | [`m81_spring_data_jpa_transactions`](m81_spring_data_jpa_transactions/theory.md) |
| 82 | Lazy loading, N+1, `JOIN FETCH`, `EntityGraph` | [`m82_spring_data_jpa_lazy_loading`](m82_spring_data_jpa_lazy_loading/theory.md) |
| 83 | `@DataJpaTest`, H2, обзор Testcontainers | [`m83_spring_data_jpa_testing`](m83_spring_data_jpa_testing/theory.md) |
| 84 | Flyway, оптимистичная блокировка, аудит | [`m84_spring_data_jpa_migrations`](m84_spring_data_jpa_migrations/theory.md) |
| 85 | Hibernate: жизненный цикл, dirty checking, flush | [`m85_hibernate_deep_dive_lifecycle`](m85_hibernate_deep_dive_lifecycle/theory.md) |
| 86 | Hibernate: fetching, N+1, `JOIN FETCH`, проекции | [`m86_hibernate_deep_dive_fetching`](m86_hibernate_deep_dive_fetching/theory.md) |
| 87 | Hibernate: HQL, Criteria, native SQL, каскады | [`m87_hibernate_deep_dive_querying`](m87_hibernate_deep_dive_querying/theory.md) |
| 88 | Hibernate: `equals`/`hashCode`, value objects, ключи | [`m88_hibernate_deep_dive_modeling`](m88_hibernate_deep_dive_modeling/theory.md) |
| 89 | Hibernate: наследование, `@DynamicUpdate` | [`m89_hibernate_deep_dive_inheritance`](m89_hibernate_deep_dive_inheritance/theory.md) |
| 90 | Hibernate: блокировки, soft delete, Envers | [`m90_hibernate_deep_dive_locking`](m90_hibernate_deep_dive_locking/theory.md) |
| 91 | Hibernate: batching, bulk, read-only, кэширование | [`m91_hibernate_deep_dive_performance`](m91_hibernate_deep_dive_performance/theory.md) |
| 92 | Hibernate: SQL-диагностика, анти-паттерны, аудит | [`m92_hibernate_deep_dive_diagnostics`](m92_hibernate_deep_dive_diagnostics/theory.md) |

### Часть 4 — Security, Test, Docker (93–118)

| #  | Тема | Каталог |
|----|------|---------|
| 93 | Spring Security: зачем нужен, filter chain | [`m93_spring_security_intro`](m93_spring_security_intro/theory.md) |
| 94 | In-memory users, `PasswordEncoder`, form login, CSRF | [`m94_spring_security_inmemory`](m94_spring_security_inmemory/theory.md) |
| 95 | Session-based security, HTTP Basic, CORS, cookies | [`m95_spring_security_session_cors`](m95_spring_security_session_cors/theory.md) |
| 96 | DB-backed users, `CustomUserDetailsService`, регистрация | [`m96_spring_security_db_users`](m96_spring_security_db_users/theory.md) |
| 97 | Request-level и method security, ownership | [`m97_spring_security_authorization`](m97_spring_security_authorization/theory.md) |
| 98 | Stateless, JWT: выдача и валидация | [`m98_spring_security_jwt`](m98_spring_security_jwt/theory.md) |
| 99 | Custom JWT filter, встроенный Bearer support | [`m99_spring_security_custom_jwt_filter`](m99_spring_security_custom_jwt_filter/theory.md) |
| 100 | MockMvc security tests, CSRF/JWT, hardening, audit | [`m100_spring_security_testing`](m100_spring_security_testing/theory.md) |
| 101 | JUnit 5, AssertJ, Hamcrest, JSONassert, Mockito | [`m101_spring_test_basics`](m101_spring_test_basics/theory.md) |
| 102 | Unit-тесты бизнес-логики без Spring-контекста | [`m102_spring_test_unit`](m102_spring_test_unit/theory.md) |
| 103 | Test profiles, properties, управляемая конфигурация | [`m103_spring_test_config`](m103_spring_test_config/theory.md) |
| 104 | `@WebMvcTest`, MockMvc, MockMvcTester, JSON tests | [`m104_spring_test_webmvc`](m104_spring_test_webmvc/theory.md) |
| 105 | Validation, error handling, pagination, file upload | [`m105_spring_test_controller_scenarios`](m105_spring_test_controller_scenarios/theory.md) |
| 106 | `@DataJpaTest`, TestEntityManager, flush, queries | [`m106_spring_test_datajpa`](m106_spring_test_datajpa/theory.md) |
| 107 | Lazy loading, конкурентность, Flyway, `@Sql` | [`m107_spring_test_entity_relationships`](m107_spring_test_entity_relationships/theory.md) |
| 108 | `@SpringBootTest`, MockMvc, RestTestClient | [`m108_spring_test_full_context`](m108_spring_test_full_context/theory.md) |
| 109 | Testcontainers, PostgreSQL, regression suite | [`m109_spring_test_integration`](m109_spring_test_integration/theory.md) |
| 110 | Security-тесты, внешние интеграции, async, REST Docs | [`m110_spring_test_security_async`](m110_spring_test_security_async/theory.md) |
| 111 | Контейнерное мышление, Docker CLI, первый контейнер | [`m111_docker_basics`](m111_docker_basics/theory.md) |
| 112 | Dockerfile, слои, кэш, multi-stage build | [`m112_docker_dockerfile`](m112_docker_dockerfile/theory.md) |
| 113 | Layered images, Buildpacks, `bootBuildImage` | [`m113_docker_spring_boot_image`](m113_docker_spring_boot_image/theory.md) |
| 114 | Externalized config, profiles, volumes, логи | [`m114_docker_configuration`](m114_docker_configuration/theory.md) |
| 115 | Compose: app + PostgreSQL, startup order, readiness | [`m115_docker_compose`](m115_docker_compose/theory.md) |
| 116 | +Redis, +RabbitMQ, dev-workflow, troubleshooting | [`m116_docker_compose_extended`](m116_docker_compose_extended/theory.md) |
| 117 | JVM под memory/CPU limits, image hygiene, non-root | [`m117_docker_jvm_tuning`](m117_docker_jvm_tuning/theory.md) |
| 118 | Финальный reusable template, production-ready образ | [`m118_docker_final_template`](m118_docker_final_template/theory.md) |

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
| JDK | **21 LTS** (проект пинит toolchain 21; в IDE: Gradle JVM = 21 и Project SDK = 21 — на JDK 25 IntelliJ краснит stdlib) |
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
