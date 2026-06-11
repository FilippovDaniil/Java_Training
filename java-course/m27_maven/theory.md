# Модуль 27. Основы Maven

**Maven** — система сборки и управления зависимостями для Java-проектов. Он скачивает библиотеки, компилирует код, запускает тесты и собирает артефакт (`.jar`) — всё по единому стандарту.

> Этот модуль — практический. Задачи (`practice/`) — **упражнения для терминала**: каждый `TaskXX.java` содержит в комментарии задание (создать `pom.xml`, добавить зависимость, выполнить команды `mvn`). Сам Java-файл — лишь носитель задания.

## Зачем нужен Maven

| Вручную | С Maven |
|---------|---------|
| качать jar-ы и класть в `lib/` | объявить зависимость в `pom.xml` |
| следить за версиями библиотек | Maven подтянет нужные сам |
| `javac`/`jar` руками | `mvn package` собирает всё |
| у каждого своя структура | единый стандарт |

---

## Стандартная структура проекта

```
my-app/
├── pom.xml                    # описание проекта (главный файл)
└── src/
    ├── main/
    │   ├── java/              # исходный код
    │   └── resources/         # ресурсы (конфиги, файлы)
    └── test/
        └── java/              # тесты
```

> Maven построен на принципе **«convention over configuration»**: следуйте структуре — и ничего настраивать не нужно.

---

## `pom.xml` — сердце проекта

POM (Project Object Model) описывает проект в XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <!-- координаты проекта -->
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- зависимости проекта -->
    </dependencies>
</project>
```

### Координаты (GAV)

Любая библиотека и сам проект однозначно определяются тройкой:

| Координата | Пример | Значение |
|------------|--------|----------|
| `groupId` | `com.example` | организация/группа |
| `artifactId` | `my-app` | имя проекта |
| `version` | `1.0.0` | версия |

---

## Зависимости

```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>           <!-- только для тестов -->
    </dependency>
</dependencies>
```

Maven скачает их из центрального репозитория и положит в локальный кеш `~/.m2/repository`.

### Области видимости (scope)

| Scope | Когда доступна |
|-------|----------------|
| `compile` (по умолчанию) | везде |
| `test` | только при тестах |
| `provided` | даёт среда (напр. сервер) |
| `runtime` | только при выполнении |

---

## Жизненный цикл сборки (lifecycle)

Maven выполняет **фазы** по порядку. Запуск фазы выполняет все предыдущие.

```
validate → compile → test → package → verify → install → deploy
```

| Фаза | Что делает |
|------|-----------|
| `compile` | компилирует `src/main/java` |
| `test` | запускает тесты |
| `package` | собирает `.jar`/`.war` в `target/` |
| `install` | кладёт артефакт в локальный `~/.m2` |
| `clean` | удаляет `target/` (отдельный цикл) |

### Основные команды

```bash
mvn -version            # проверить установку
mvn clean               # очистить target/
mvn compile             # скомпилировать
mvn test                # прогнать тесты
mvn package             # собрать jar (в target/)
mvn clean install       # очистить, собрать, установить в ~/.m2
mvn dependency:tree     # дерево зависимостей
```

---

## Плагины

Сборку выполняют **плагины**. Например, плагин для указания версии Java или для сборки «толстого» jar со всеми зависимостями:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

---

## Maven vs Gradle (кратко)

| | Maven | Gradle |
|--|-------|--------|
| Формат | XML (`pom.xml`) | Groovy/Kotlin DSL |
| Стиль | декларативный | гибкий, программируемый |
| Скорость | медленнее | быстрее (кеш, инкрементально) |
| Распространённость | очень высокая | высокая (Android, новые проекты) |

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| код вне `src/main/java` | Maven не найдёт его |
| забыли `<version>` зависимости | сборка не пройдёт |
| неверные координаты | артефакт не скачается |
| тестовая библиотека без `<scope>test</scope>` | попадёт в продакшн-сборку |
| нет интернета при первой сборке | зависимости не скачаются |

---

## Что дальше

В [модуле 28](../module-28-junit/theory.md) — модульное тестирование с JUnit (подключается через Maven/Gradle).
