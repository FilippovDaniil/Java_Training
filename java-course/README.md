# Java Core — Учебный курс

Самодостаточный учебный репозиторий по **Core Java** (без веб-фреймворков, GUI и сторонних библиотек). Курс построен по принципу **«теория + практика»**: на каждую тему даётся объяснение и **7 задач** с возрастающей сложностью.

Упор сделан на практику, базовые навыки программиста и **ООП**.

---

## 📁 Структура проекта

```
java-course/
├── README.md                 # этот файл — навигация по курсу
├── .gitignore                # стандартный для Java / IntelliJ / Maven
└── module-XX-topic-name/     # одна папка на тему
    ├── theory.md             # объяснение темы с примерами кода
    └── practice/             # задачи для самостоятельного решения
        ├── Task01.java       # простые упражнения на синтаксис
        ├── ...
        └── Task07.java       # мини-проект, связывающий понятия темы
```

---

## 🗺️ Модули курса

| #  | Модуль | Тема | Теория |
|----|--------|------|--------|
| 01 | `module-01-intro-first-program` | Вводное занятие. Первая программа | [theory.md](module-01-intro-first-program/theory.md) |
| 02 | `module-02-variables-int-string` | Переменные, типы `int` и `String` | [theory.md](module-02-variables-int-string/theory.md) |
| 03 | `module-03-types-keyboard-input-idea` | Типы данных и ввод с клавиатуры. IDEA | [theory.md](module-03-types-keyboard-input-idea/theory.md) |
| 04 | `module-04-conditionals` | Условный оператор (`if`, `switch`, тернарный) | [theory.md](module-04-conditionals/theory.md) |
| 05 | `module-05-loops` | Циклы (`while`, `for`, `do-while`, `break`, `continue`) | [theory.md](module-05-loops/theory.md) |
| 06 | `module-06-arrays` | Одномерные массивы | [theory.md](module-06-arrays/theory.md) |
| 07 | `module-07-multidimensional-arrays` | Многомерные массивы | [theory.md](module-07-multidimensional-arrays/theory.md) |
| 08 | `module-08-methods` | Методы: параметры, возврат, рекурсия | [theory.md](module-08-methods/theory.md) |
| 09 | `module-09-strings` | Строки (`String`, `StringBuilder`, `String.format`) | [theory.md](module-09-strings/theory.md) |
| 10 | `module-10-oop-intro` | Знакомство с ООП (примитивы, ссылки, `null`) | [theory.md](module-10-oop-intro/theory.md) |
| 11 | `module-11-objects-constructors` | Объекты, конструкторы, инкапсуляция, `equals`/`hashCode` | [theory.md](module-11-objects-constructors/theory.md) |
| 12 | `module-12-classes-static` | Классы и `static`, вложенные классы | [theory.md](module-12-classes-static/theory.md) |
| 13 | `module-13-lists-generics` | Списки и Generics (`ArrayList`, обёртки) | [theory.md](module-13-lists-generics/theory.md) |
| 14 | `module-14-collections-set-iterator` | `Set`, `HashSet`, итератор, `for-each` | [theory.md](module-14-collections-set-iterator/theory.md) |
| 15 | `module-15-map-collections-framework` | `Map`, `HashMap`, `Collections` | [theory.md](module-15-map-collections-framework/theory.md) |
| 16 | `module-16-enums-switch` | Перечисления (`Enum`) и `switch` | [theory.md](module-16-enums-switch/theory.md) |
| 17 | `module-17-exceptions` | Исключения (`try-catch`, иерархия, `finally`) | [theory.md](module-17-exceptions/theory.md) |
| 18 | `module-18-io-streams` | Потоки ввода-вывода (`InputStream`, `Reader`) | [theory.md](module-18-io-streams/theory.md) |
| 19 | `module-19-io-nio` | `java.nio` (`Path`, `Files`), паттерн Decorator | [theory.md](module-19-io-nio/theory.md) |
| 20 | `module-20-date-time` | Дата и время (`java.time`) | [theory.md](module-20-date-time/theory.md) |
| 21 | `module-21-git` | Основы Git | [theory.md](module-21-git/theory.md) |
| 22 | `module-22-oop-encapsulation-polymorphism-interfaces` | ООП: инкапсуляция, полиморфизм, интерфейсы | [theory.md](module-22-oop-encapsulation-polymorphism-interfaces/theory.md) |
| 23 | `module-23-oop-overloading-overriding-abstract` | ООП: перегрузка, переопределение, абстракция | [theory.md](module-23-oop-overloading-overriding-abstract/theory.md) |
| 24 | `module-24-stream-api-lambda` | Stream API и лямбда-выражения | [theory.md](module-24-stream-api-lambda/theory.md) |
| 25 | `module-25-multithreading-concurrency` | Многопоточность (`Thread`, `ExecutorService`) | [theory.md](module-25-multithreading-concurrency/theory.md) |
| 26 | `module-26-reflection-annotations` | Reflection API и аннотации | [theory.md](module-26-reflection-annotations/theory.md) |
| 27 | `module-27-maven` | Основы Maven (`pom.xml`, lifecycle) | [theory.md](module-27-maven/theory.md) |
| 28 | `module-28-junit` | Модульное тестирование с JUnit | [theory.md](module-28-junit/theory.md) |

---

## 🚀 Рекомендации по изучению

1. **Порядок:** идите модуль за модулем сверху вниз — темы выстроены по нарастанию сложности.
2. **Сначала теория:** прочитайте `theory.md`, разберите примеры кода.
3. **Потом практика:** решайте задачи `Task01 → Task07`. Сложность растёт; седьмая задача — мини-проект.
4. **ООП — ключевая тема:** модулям 10, 11, 12, 22, 23 уделите особое внимание.

## ⚙️ Настройка окружения

| Что | Требование |
|-----|------------|
| JDK | 11 или выше (рекомендуется 17 LTS) |
| IDE | IntelliJ IDEA (Community Edition достаточно) |
| Сборка | для большинства модулей не нужна — файлы запускаются напрямую |

**Запуск одной задачи из консоли:**

```
javac Task01.java
java Task01
```

В IntelliJ IDEA: откройте файл и нажмите ▶ рядом с методом `main`.

---

## ℹ️ Важно

- Все задачи предназначены для **самостоятельного решения**. В репозитории — только условия и шаблоны, **готовых решений нет**.
- Бо́льшая часть файлов `TaskXX.java` компилируется с пустым `main` — можно сразу запускать и постепенно дописывать код.

### Особые модули (инструменты)

| Модуль | Чем отличается | Как выполнять |
|--------|----------------|---------------|
| 21 (Git) | Задачи — упражнения для терминала; `.java` лишь несёт условие | Выполнять команды Git в терминале |
| 27 (Maven) | Аналогично: создание `pom.xml` и команды `mvn` | Выполнять в терминале |
| 28 (JUnit) | Задачи — это JUnit-тесты (без `main`) | Запускать в **IntelliJ IDEA** (▶) или через **Maven/Gradle**; требуется JUnit 5 на classpath |

---

## 🚧 Часть 2: план расширения (модули 29–66)

Курс расширяется продвинутыми темами вплоть до production-ready Spring Boot. Модули 29–53 — Core/БД/Hibernate. Модули **54–66 — углублённый Spring-трек с полноценной практикой** (теория + 7 задач каждый): инструменты сборки, HTTP, JSON, ручной HTTP-сервер, Spring Core и Spring Boot. Прежние theory-only заглушки 54–56 заменены этой детализированной дорожкой.

> **Статус генерации** (обновляется по ходу): ⬜ — не начат, ⏳ — в работе, ✅ — готов.

| #  | Модуль | Тип | Внешние зависимости | Статус |
|----|--------|-----|---------------------|--------|
| 29 | `module-29-gc-reference-types` | практика | нет (`java.lang.ref`) | ✅ |
| 30 | `module-30-design-patterns` | практика | нет | ✅ |
| 31 | `module-31-dev-methodologies` | **теория** | — | ✅ |
| 32 | `module-32-maven-multimodule` | практика (терминал) | Maven | ✅ |
| 33 | `module-33-guava-apache-commons` | практика | Guava, Commons Collections | ✅ |
| 34 | `module-34-testing-junit-mockito` | практика | JUnit 5, Mockito | ✅ |
| 35 | `module-35-logging` | практика | SLF4J + Logback/Log4j2 | ✅ |
| 36 | `module-36-network-basics` | **теория** | — | ✅ |
| 37 | `module-37-software-architecture` | **теория** | — | ✅ |
| 38 | `module-38-http-protocol` | практика | нет (`java.net.http`) | ✅ |
| 39 | `module-39-servlets` | **теория** | — | ✅ |
| 40 | `module-40-servlet-containers` | **теория** | — | ✅ |
| 41 | `module-41-mvc-jsp` | **теория** | — | ✅ |
| 42 | `module-42-web-services` | **теория** | — | ✅ |
| 43 | `module-43-html-css` | **теория** | — | ✅ |
| 44 | `module-44-databases-intro` | практика (SQL) | H2/любая СУБД | ✅ |
| 45 | `module-45-sql-dml` | практика (SQL) | H2/любая СУБД | ✅ |
| 46 | `module-46-sql-queries` | практика (SQL) | H2/любая СУБД | ✅ |
| 47 | `module-47-transactions` | практика (SQL) | H2/любая СУБД | ✅ |
| 48 | `module-48-database-design` | практика | нет (схемы/SQL) | ✅ |
| 49 | `module-49-jdbc-1` | практика | нет компил. (`java.sql`); H2 для запуска | ✅ |
| 50 | `module-50-jdbc-2` | практика | нет компил. (`java.sql`); H2 для запуска | ✅ |
| 51 | `module-51-hibernate-orm` | практика | Hibernate, JPA, H2 | ✅ |
| 52 | `module-52-hibernate-relationships` | практика | Hibernate, JPA, H2 | ✅ |
| 53 | `module-53-hibernate-inheritance` | практика | Hibernate, JPA, H2 | ✅ |
| 54 | `module-54-gradle-build-tools` | практика (терминал) | Gradle | ✅ |
| 55 | `module-55-http-basics` | практика | нет (`java.net.http`) | ✅ |
| 56 | `module-56-json-jackson-dto` | практика | Jackson (`jackson-databind`) | ✅ |
| 57 | `module-57-java-http-client` | практика | нет (`java.net.http`) | ✅ |
| 58 | `module-58-jdk-httpserver` | практика | нет (`com.sun.net.httpserver`) | ✅ |
| 59 | `module-59-spring-core-intro` | практика | Spring Context | ✅ |
| 60 | `module-60-spring-core-beans` | практика | Spring Context | ✅ |
| 61 | `module-61-spring-core-advanced` | практика | Spring Context | ✅ |
| 62 | `module-62-spring-core-configuration` | практика | Spring Context | ✅ |
| 63 | `module-63-spring-core-events-aop` | практика | Spring Context + AspectJ | ✅ |
| 64 | `module-64-spring-boot-intro` | практика | Spring Boot Starter | ⬜ |
| 65 | `module-65-spring-boot-web-config` | практика | Spring Boot Starter Web | ⬜ |
| 66 | `module-66-spring-boot-devops` | практика | Spring Boot Actuator + Test | ⬜ |

**Порядок генерации (батчи по 5, с проверкой между ними):**
Батч 7 → 29–33 · Батч 8 → 34–38 · Батч 9 → 39–43 (все теория) · Батч 10 → 44–48 · Батч 11 → 49–53 · Батч 12 → 54–58 · Батч 13 → 59–63 (Spring Core) · Батч 14 → 64–66 (Spring Boot).

**Заметки по реализации:**
- Модуль 32 — продвинутый Maven (многомодульность, профили, плагины); основы уже в [модуле 27](module-27-maven/theory.md).
- JDBC (49–50) использует **H2** (встроенная БД), `java.sql` входит в JDK — код компилируется без зависимостей, H2-драйвер нужен только для запуска.
- Hibernate (51–53), Guava/Commons (33), Mockito (34), логирование (35) требуют зависимостей — они указаны в заголовке каждой задачи (`pom.xml`/Gradle).
- Модули «только теория» содержат ASCII-схемы, глоссарий ключевых терминов и ссылки на источники.

---

## 📐 Руководство по созданию модулей (для продолжения генерации)

> Этот раздел — инструкция для будущих сессий. Курс расширяется и дальше (план — 120+ модулей). Соблюдайте принципы ниже, чтобы новые модули были единообразны.

### 🔖 ТОЧКА ВОЗОБНОВЛЕНИЯ (пауза 2026-06-02)

**Сделано:** модули **01–63** (61 практический + теория-онли). Трек 54–66: ✅ 54–63 готовы.
**Следующий шаг — Батч 14 (модули 64–66, Spring Boot)** — единственное, что осталось по текущему ТЗ. Детали ниже.
**Правило процесса:** перед запуском батча СПРОСИТЬ у пользователя разрешение (он следит за токенами) — затем генерировать 3 модуля параллельными агентами (по образцу батчей 12–13), проверить структуру, обновить статусы здесь и в памяти.

### Что осталось доделать (на момент паузы)
- ✅ **Батч 11 (49–53)** — JDBC + Hibernate. JDBC (49–50) проверены компиляцией; Hibernate (51–53) — реалистичные шаблоны с deps.
- ✅ **Батч 12 (54–58)** — Gradle, HTTP basics, JSON/Jackson, java.net.http клиент, ручной HTTP-сервер. Bare-javac проверены: 54,55,57,58. Jackson (56) — шаблоны с deps.
- ✅ **Батч 13 (59–63)** — Spring Core (IoC/DI, бины, advanced, конфигурация, события+AOP). Реалистичные импорты org.springframework.* + TODO-каркасы; bare-javac не верифицируется (deps — норма).
- ⬜ **Батч 14 (64–66)** — Spring Boot (intro, web+config, devops/actuator/test). ← НАЧАТЬ ОТСЮДА.
- Готово: модули **01–48** (Часть 1: 01–28, Часть 2: 29–48) + **49–63**. Dep-free задачи проверены компиляцией.

### Детали Батча 14 (64–66) — следующий шаг
| # | Модуль | Тип | Что внутри (7 задач: 01–03 базовое, 04–06 сложнее, 07 мини-проект) |
|---|--------|-----|-----------|
| 64 | `module-64-spring-boot-intro` | практика (Spring Boot Starter — deps) | `@SpringBootApplication`, `SpringApplication.run`, авто-конфигурация, стартеры, `CommandLineRunner`, автодетект `@Component`, базовый `application.properties`, первый `@RestController`. Мини-проект: минимальное Boot-приложение магазина с runner + эндпоинтом |
| 65 | `module-65-spring-boot-web-config` | практика (Spring Boot Starter Web — deps) | `@RestController`/`@GetMapping`/`@PostMapping`, `@RequestBody`+JSON DTO, `@PathVariable`/`@RequestParam`, `@ConfigurationProperties`, Bean Validation (`@Valid`/`@NotNull`/`@Size`), `@ControllerAdvice`+`@ExceptionHandler` (единый контракт ошибок). Мини-проект: REST CRUD-микросервис (заметки/товары) с валидацией и конфигом |
| 66 | `module-66-spring-boot-devops` | практика (Actuator + Spring Boot Test — deps) | логирование (уровни в `application.properties`, SLF4J), Actuator (`/health`,`/info`, кастомный endpoint/info-contributor), DevTools и упаковка (`bootJar` — терминальная подача), `@SpringBootTest` (интеграционный), `@WebMvcTest`+`MockMvc` (slice). Мини-проект: production-ready микросервис с actuator + тестами + упаковкой |

Все три модуля (64–66) — реалистичные импорты `org.springframework.boot.*` / `org.springframework.web.bind.annotation.*` + строка «ТРЕБУЮТСЯ ЗАВИСИМОСТИ: …» в шапке каждой задачи. Bare-javac НЕ компилируются (Spring Boot не на classpath) — это ОЖИДАЕМО, как у Hibernate/Spring Core. Задача 66 с `@WebMvcTest`/`@SpringBootTest` — тест-класс БЕЗ `main` (стиль module-34), важные ловушки тестов Spring Security описаны в глобальном `~/.claude/CLAUDE.md`. Предметные области: магазин и заметки (сквозные через весь трек). После 66 трек 54–66 закрыт — курс обеспечивает переход Core Java → production-ready Spring Boot.

### Рецепт модуля (единый для всех)
1. **Папка:** `module-NN-kebab-topic/` (сквозная нумерация без пропусков).
2. **`theory.md`** (RU, 1–3 страницы): простое объяснение → примеры кода с комментариями → ASCII-схемы → таблицы (сравнения, «Подводные камни») → перекрёстные ссылки `[модуль NN](../module-NN-.../theory.md)` → раздел «Дополнительные источники» → «Что дальше» со ссылкой на следующий модуль. Для теории-онли добавлять глоссарий ключевых терминов.
3. **`practice/Task01.java … Task07.java`** (только для практических модулей): нарастающая сложность — Task01–03 синтаксис, Task04–06 сложнее, **Task07 — мини-проект**. Каждый файл:
   - JavaDoc-шапка: номер+тема, формулировка, примеры ввода/вывода, подсказки;
   - имя класса = имя файла; `public static void main` с пустым телом или минимальным `Scanner`/данными;
   - **компилируется с пустым main**; **решений НЕ давать** (только условие+шаблон, поля/методы как `// TODO`);
   - **без `package`** (default package), запуск по отдельности; кириллица — UTF-8.
4. **ООП-модули** — моделировать предметные области (зоопарк, банк, магазин, фигуры), требовать несколько классов, наследование, полиморфизм, композицию.

### Спец-форматы задач (когда обычный main не подходит)
- **Терминальные** (Git, Maven): `main` печатает «Выполните задание в терминале…», вся суть — в JavaDoc (команды, ожидаемый результат).
- **SQL** (БД-модули): тело — `String sql = """ -- ваш SQL здесь """;` + `System.out.println(sql);`. Студент пишет SQL в блоке, запускает в H2/любой СУБД.
- **Тесты** (JUnit/Mockito): файл — тест-класс БЕЗ `main`, методы `@Test` с пустым телом/`// TODO`, класс-под-тестом дать готовым. Запуск в IDE/Maven/Gradle.
- **С зависимостями** (Guava, Commons, SLF4J, Hibernate): реалистичные импорты + в шапке задачи строка «ТРЕБУЮТСЯ ЗАВИСИМОСТИ: …».
- **Теория-онли:** только `theory.md`, папку `practice/` НЕ создавать.

### Проверка и процесс
- **Компиляция:** каждый файл по отдельности `javac -encoding UTF-8 -d <tmp> <file>` (несколько `Task07` в одном пакете → `duplicate class`, поэтому строго по одному).
- **Зависимости из gradle-кеша** (`~/.gradle/caches/modules-2/files-2.1`): кеш БОГАТЫЙ (проверено 2026-06-02). Есть: JUnit 5, mockito, slf4j, logback, **com.fasterxml.jackson**, **com.h2database**, **org.hibernate.orm**, **jakarta.persistence**, **org.aspectj**, **org.springframework** (context/web/boot/test/security), **org.springframework.boot**, jakarta.validation/servlet. То есть при желании можно собрать classpath и верифицировать даже Spring/Hibernate/Jackson-задачи — но это НЕ обязательно (по процессу dep-задачи не верифицируются bare-javac, достаточно реалистичных импортов). НЕ найдены: Guava, Commons.
- **Батчи по 5 модулей**, после каждого — обновить статус в этой таблице (⬜→✅) и в памяти проекта, затем спросить пользователя (он следит за токенами).
- **Дубликаты тем:** при повторе уже пройденной темы делать продвинутый вариант с новым именем (как `module-32-maven-multimodule` vs `module-27-maven`), а не копию.

### Дальнейшее расширение (57 → 120+)
Продолжать сквозную нумерацию (`module-57-…`). Веб/Spring/инфраструктуру держать теорией, пока пользователь не попросит практику. Каждые 5 модулей фиксировать прогресс здесь и в памяти. Принципы выше — неизменны.
