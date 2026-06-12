# Модуль 54. Gradle: сборка Java-проектов

Gradle — современная система сборки, использующая Groovy (или Kotlin) DSL вместо XML. Быстрее Maven за счёт инкрементальной компиляции и кэша; гибче — любая задача сборки описывается кодом.

> Практика — терминальные команды Gradle, написание build.gradle, многомодульная сборка.

---

## Структура Gradle-проекта

```
my-app/
+-- gradle/
|   +-- wrapper/
|       +-- gradle-wrapper.jar        ← бинарный загрузчик wrapper
|       +-- gradle-wrapper.properties ← версия и URL дистрибутива
+-- gradlew                           ← wrapper-скрипт (Unix)
+-- gradlew.bat                       ← wrapper-скрипт (Windows)
+-- settings.gradle                   ← имя проекта, список модулей
+-- build.gradle                      ← скрипт сборки корневого модуля
+-- src/
    +-- main/java/                    ← исходный код
    +-- main/resources/               ← ресурсы (classpath)
    +-- test/java/                    ← тесты
```

> Всегда используйте `gradlew` / `gradlew.bat` вместо глобальной команды `gradle`.
> Wrapper гарантирует одинаковую версию Gradle для всей команды.

---

## settings.gradle — корневой файл

```groovy
// settings.gradle
rootProject.name = 'my-app'   // имя корневого проекта

// Для многомодульного проекта:
include 'app', 'core', 'api'
```

---

## build.gradle — основной скрипт

```groovy
// build.gradle — пример с плагином java
plugins {
    id 'java'                 // стандартный Java-плагин от Gradle
    // id 'application'       // добавляет задачи run / installDist / distZip
}

group   = 'com.example'       // координата: groupId
version = '1.0.0'             // координата: version

// Источник зависимостей
repositories {
    mavenCentral()            // Maven Central — основное хранилище
    // mavenLocal()           // ~/.m2/repository (локальный кэш Maven)
}

// Параметры компилятора Java
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // JUnit 5 BOM — управляет версиями всех junit-модулей
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testRuntimeOnly    'org.junit.platform:junit-platform-launcher'
}

test {
    useJUnitPlatform()        // активирует JUnit 5 runner
}
```

---

## Build Lifecycle (жизненный цикл сборки)

Gradle не имеет фиксированных фаз как Maven, но плагин `java` добавляет задачи в определённом порядке:

```
Инициализация         Конфигурация          Выполнение
-------------         ------------          ----------
Читает                Разбирает все         Запускает
settings.gradle  --►  build.gradle     --►  нужные задачи
Определяет            Строит граф           (только те, что
список проектов       зависимостей          запрошены)
                      задач (DAG)
```

### Порядок задач плагина `java`

```
compileJava --► processResources --► classes
                                        |
                                        ▼
                              compileTestJava --► processTestResources --► testClasses
                                                                               |
                                                                               ▼
                                                                             test --► check --► build
                                                                                          |
                                                             jar ◄------------------------+
```

Ключевые задачи:

| Задача | Что делает |
|--------|-----------|
| `compileJava` | компилирует исходники `src/main/java` |
| `processResources` | копирует `src/main/resources` в `build/resources/main` |
| `classes` | агрегирует compileJava + processResources |
| `jar` | упаковывает `build/classes` в `build/libs/*.jar` |
| `compileTestJava` | компилирует тесты |
| `test` | запускает тесты (JUnit / TestNG) |
| `check` | запускает все верификационные задачи (включает test) |
| `build` | полная сборка: classes + jar + check |
| `clean` | удаляет папку `build/` |

---

## Gradle vs Maven

| Критерий | Gradle | Maven |
|----------|--------|-------|
| Язык конфига | Groovy / Kotlin DSL | XML |
| Инкрементальная сборка | да (умный up-to-date) | ограниченно |
| Кэш сборки | да (Build Cache, распределённый) | нет |
| Многомодульность | нативная поддержка | через parent pom |
| Скорость | быстрее (особенно повторные сборки) | медленнее |
| Экосистема плагинов | богатая (plugins.gradle.org) | очень богатая |
| Кривая вхождения | выше (DSL, Groovy/Kotlin) | ниже (XML) |
| Android | официальный инструмент сборки | не используется |
| Spring Boot | поддерживается | основной инструмент |

---

## Конфигурации зависимостей

Конфигурация определяет, **когда** зависимость попадает в classpath.

```groovy
dependencies {
    // implementation — compile + runtime classpath, НЕ транзитивна потребителям
    implementation 'com.google.guava:guava:33.2.0-jre'

    // api — как implementation, но транзитивна (видна потребителям модуля)
    // использовать только с плагином 'java-library'!
    api 'org.apache.commons:commons-lang3:3.14.0'

    // compileOnly — только компиляция, НЕ в runtime (аннотации, Lombok)
    compileOnly 'org.projectlombok:lombok:1.18.32'

    // runtimeOnly — только runtime, НЕ в compile classpath (драйверы JDBC)
    runtimeOnly 'org.postgresql:postgresql:42.7.3'

    // testImplementation — только для тестов
    testImplementation 'org.mockito:mockito-core:5.11.0'

    // BOM (Bill of Materials) — управляет версиями через platform()
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
}
```

| Конфигурация | Compile classpath | Runtime classpath | Потребителям |
|--------------|:-----------------:|:-----------------:|:------------:|
| `implementation` | да | да | нет |
| `api` | да | да | да |
| `compileOnly` | да | нет | нет |
| `runtimeOnly` | нет | да | нет |
| `testImplementation` | (тест) да | (тест) да | нет |

> Правило: по умолчанию используйте `implementation`. `api` — только если типы
> из зависимости входят в **публичный API** вашего модуля.

---

## Кастомные задачи (tasks)

```groovy
// Простая задача
tasks.register('hello') {
    description = 'Приветственная задача'
    group       = 'custom'
    doLast {
        println "Привет от Gradle!"
    }
}

// Задача с зависимостью от другой
tasks.register('greet') {
    dependsOn 'hello'
    doLast {
        println "Сборка: ${project.name} v${project.version}"
    }
}

// Задача типа Copy
tasks.register('copyConfig', Copy) {
    from 'src/config'
    into "${buildDir}/config"
}
```

Запуск: `./gradlew hello`, `./gradlew greet`.

---

## Многомодульный проект

```
platform/                    ← корневой проект (не содержит Java-кода)
+-- settings.gradle
+-- build.gradle             ← общие настройки для всех подпроектов
+-- core/                    ← библиотека: бизнес-логика
|   +-- build.gradle
+-- app/                     ← приложение: зависит от :core
    +-- build.gradle
```

**settings.gradle** (корень):
```groovy
rootProject.name = 'platform'
include 'core', 'app'
```

**build.gradle** (корень — общие настройки через allprojects / subprojects):
```groovy
subprojects {
    apply plugin: 'java'
    repositories { mavenCentral() }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
```

**app/build.gradle** (зависит от модуля :core):
```groovy
dependencies {
    implementation project(':core')   // ссылка на другой модуль
}
```

Сборка всего: `./gradlew build` (из корня).
Сборка одного модуля: `./gradlew :app:build`.

---

## Gradle Wrapper — правильный старт

```bash
# Создать wrapper в существующем проекте (требует локального Gradle):
gradle wrapper --gradle-version 8.8

# После создания — всегда использовать wrapper:
./gradlew build          # Unix/Mac
.\gradlew.bat build      # Windows
./gradlew --version      # проверить версию
./gradlew tasks          # список всех доступных задач
./gradlew tasks --all    # полный список включая вспомогательные
./gradlew dependencies   # дерево зависимостей
./gradlew :app:dependencies --configuration runtimeClasspath  # конкретный модуль
```

**gradle-wrapper.properties:**
```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.8-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

---

## Подводные камни

| Проблема | Объяснение | Решение |
|----------|-----------|---------|
| `api` без `java-library` | конфигурация `api` не существует с плагином `java` | заменить на `implementation` или добавить `id 'java-library'` |
| использование `compile` (устар.) | конфигурация удалена в Gradle 7+ | использовать `implementation` или `api` |
| `buildDir` устарело в Gradle 8 | deprecated, вызывает предупреждение | использовать `layout.buildDirectory` |
| задача без `doLast` / `doFirst` | код в `configuration phase`, выполняется всегда | оборачивать логику в `doLast { }` |
| разные версии одной зависимости | конфликт версий в classpath | `configurations.all { resolutionStrategy { force '...' } }` |
| медленная первая сборка | Gradle скачивает дистрибутив и зависимости | нормально; повторные сборки быстрые благодаря кэшу |
| кодировка на Windows | `javac` использует системную кодировку | добавить `options.encoding = 'UTF-8'` в блок `compileJava` |

---

## Дополнительные источники

- Официальная документация: [docs.gradle.org](https://docs.gradle.org)
- Каталог плагинов: [plugins.gradle.org](https://plugins.gradle.org)
- «Gradle in Action» (Benjamin Muschko)

## Перекрёстные ссылки

- [Модуль 27 — Maven: основы](../m27_maven/theory.md)
- [Модуль 32 — Maven: многомодульные проекты](../m32_maven_multimodule/theory.md)

## Что дальше

В [модуле 55](../m55_http_basics/theory.md) — протокол HTTP: запросы, ответы, методы и коды состояний.
