# Модуль 32. Maven: многомодульные проекты, профили, плагины

Основы Maven (структура, зависимости, lifecycle) — в [модуле 27](../m27_maven/theory.md). Здесь — продвинутые возможности: **многомодульные проекты**, профили и управление плагинами.

> Этот модуль — практический, задачи (`practice/`) — **упражнения для терминала**: создание `pom.xml` и команды `mvn`. `.java`-файл лишь несёт условие.

## Зачем многомодульные проекты

Большое приложение разбивают на **модули** (подпроекты): общий код, бизнес-логика, веб-слой, тесты. Maven собирает их вместе.

```
parent (pom)
 +-- common      (общие утилиты, модели)
 +-- service     (бизнес-логика, зависит от common)
 +-- app         (точка входа, зависит от service)
```

| Без модулей | С модулями |
|-------------|------------|
| один огромный проект | логическое разделение |
| всё компилируется вместе | переиспользование частей |
| трудно тестировать части | независимая сборка модулей |

---

## Родительский (parent) POM

Агрегатор с `packaging=pom` объединяет дочерние модули в секции `<modules>`:

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-platform</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>           <!-- агрегатор, не jar -->

    <modules>
        <module>common</module>
        <module>service</module>
        <module>app</module>
    </modules>
</project>
```

> `mvn install` в корне соберёт **все** модули в правильном порядке (реактор Maven сам разрешит зависимости между ними).

---

## Дочерний модуль наследует родителя

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-platform</artifactId>
        <version>1.0.0</version>
    </parent>

    <artifactId>service</artifactId>     <!-- groupId/version наследуются -->

    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>common</artifactId>     <!-- зависимость на соседний модуль -->
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

---

## `dependencyManagement` — единые версии

В родителе централизованно задают версии, дочерние модули указывают зависимость **без версии**:

```xml
<!-- в parent pom -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

```xml
<!-- в дочернем модуле — версию НЕ указываем, она берётся из parent -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
</dependency>
```

> Так все модули используют **одну** версию библиотеки — нет конфликтов.

---

## Профили (profiles)

Профиль — набор настроек для разных окружений (dev, test, prod). Активируется флагом или условием.

```xml
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <db.url>jdbc:h2:mem:devdb</db.url>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <db.url>jdbc:postgresql://prod-server/db</db.url>
        </properties>
    </profile>
</profiles>
```

Активация:

```bash
mvn package                # профиль dev (по умолчанию)
mvn package -P prod        # активировать профиль prod
```

---

## `pluginManagement` — управление плагинами

Как `dependencyManagement`, но для плагинов: версии и конфигурация задаются в родителе, модули лишь подключают.

```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>          <!-- запускает тесты -->
            </plugin>
        </plugins>
    </pluginManagement>
</build>
```

### Полезные плагины

| Плагин | Назначение |
|--------|-----------|
| `maven-compiler-plugin` | компиляция, версия Java |
| `maven-surefire-plugin` | запуск unit-тестов |
| `maven-shade-plugin` | «толстый» jar со всеми зависимостями |
| `maven-assembly-plugin` | сборка дистрибутивов |
| `exec-maven-plugin` | запуск Java-кода через `mvn exec:java` |

---

## Реактор Maven

При сборке многомодульного проекта Maven строит **граф зависимостей** между модулями и собирает их в правильном порядке:

```
mvn install (в корне)
   +-▶ common  (нет зависимостей → первым)
   +-▶ service (зависит от common → вторым)
   +-▶ app     (зависит от service → последним)
```

---

## Подводные камни

| Ошибка | Объяснение |
|--------|-----------|
| `packaging` не `pom` у агрегатора | многомодульная сборка не сработает |
| версия зависимости в каждом модуле | используйте `dependencyManagement` |
| циклические зависимости модулей | реактор не сможет упорядочить сборку |
| забыли модуль в `<modules>` | он не соберётся |
| сборка модуля до зависимости | стройте из корня (реактор сам упорядочит) |

## Дополнительные источники

- «Maven: The Complete Reference» (Sonatype).
- Официальная документация maven.apache.org (Guide to Working with Multiple Modules).

## Что дальше

В [модуле 33](../m33_guava_apache_commons/theory.md) — библиотеки Guava и Apache Commons.
