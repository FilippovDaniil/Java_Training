# Модуль 113. Docker: образы для Spring Boot — layered jars и Buildpacks

Обычный Dockerfile кладёт весь jar одним слоем ([модуль 112](../m112_docker_dockerfile/theory.md)). Но в Spring Boot jar ≈ 95% — это **зависимости** (меняются редко), и лишь ~5% — **ваш код** (меняется постоянно). Если всё в одном слое, любая правка кода пересобирает и перекачивает весь слой с зависимостями. Spring Boot решает это **layered jars** (расслоение) и **Buildpacks** (`bootBuildImage` — образ вообще без Dockerfile). В этом модуле — оба подхода.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (Dockerfile / build.gradle-фрагмент / команды) + `println` — **компилируются bare-javac**. Сквозной проект — **Task Tracker API**.

---

## Проблема «толстого» jar в одном слое

```
   COPY app.jar app.jar      ← один слой ~50 МБ
        │
   правка одной строки кода → весь слой (50 МБ) пересобирается и перекачивается
```

Хочется: зависимости — отдельным слоем (стабильный, кэшируется), код — отдельным (тонкий, меняется часто).

---

## Layered jars: расслоение Spring Boot

Spring Boot умеет раскладывать jar на слои по изменчивости:

```
   dependencies         — сторонние зависимости (меняются редко)        ← кэшируется
   spring-boot-loader   — загрузчик Spring Boot (почти не меняется)
   snapshot-dependencies— SNAPSHOT-зависимости (меняются чаще)
   application          — ВАШ код и ресурсы (меняются постоянно)         ← тонкий слой
```

Посмотреть слои в jar:

```bash
java -Djarmode=layertools -jar app.jar list      # показать слои
java -Djarmode=layertools -jar app.jar extract   # извлечь слои в папки
```

### Dockerfile с layered jars

```dockerfile
# ── этап извлечения слоёв ──
FROM eclipse-temurin:17-jre AS builder
WORKDIR /app
COPY build/libs/app.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract     # распаковать на слои

# ── рантайм: копируем слои по отдельности (порядок = от стабильного к изменчивому) ──
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./                # ← ваш код последним (тонкий слой)
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
```

```
   правка кода → пересобирается только слой application (КБ), а dependencies берётся из кэша
```

> Layered jars включены в Spring Boot по умолчанию (executable jar уже расслоён). `JarLauncher` — entrypoint, запускающий распакованное приложение.

---

## Buildpacks: образ без Dockerfile (`bootBuildImage`)

Spring Boot умеет собирать образ **вообще без Dockerfile** — через Cloud Native Buildpacks. Одна команда Gradle:

```bash
./gradlew bootBuildImage                                  # соберёт образ автоматически
./gradlew bootBuildImage --imageName=tasktracker:1.0.0    # с явным именем
```

Buildpacks сами:
- подбирают подходящий JRE,
- применяют layered jars,
- настраивают JVM под контейнер,
- следуют best practices (non-root пользователь и т.п.).

### Настройка в `build.gradle`

```groovy
tasks.named('bootBuildImage') {
    imageName = 'tasktracker:1.0.0'
    environment = ['BP_JVM_VERSION': '17']
}
```

| | Dockerfile | Buildpacks (`bootBuildImage`) |
|--|-----------|-------------------------------|
| нужен Dockerfile | да | **нет** |
| контроль | полный | конвенции (меньше кода) |
| best practices | руками | из коробки (JRE, layers, non-root) |
| когда | нужна тонкая настройка | стандартное Spring Boot приложение |

> Требует доступного Docker-демона (Buildpacks собирают образ через него). Имя/теги задаются в `build.gradle` или флагом `--imageName`.

---

## Какой подход выбирать

```
   стандартное Spring Boot приложение, хочется «просто работало»  →  bootBuildImage (Buildpacks)
   нужна тонкая настройка образа (базовый образ, доп. пакеты)     →  Dockerfile + layered jars
   нестабильный интернет / собираем jar на хосте                  →  Dockerfile «только COPY» (модуль 112)
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Правка кода перекачивает все зависимости | jar одним слоем | layered jars (слои по изменчивости) |
| `layertools` не находит слои | старая версия / jar не Spring Boot | Spring Boot 2.3+; собрать `bootJar` |
| Неверный entrypoint после extract | запускают `-jar`, а слои распакованы | `JarLauncher` (в новых версиях `org.springframework.boot.loader.launch.JarLauncher`) |
| `bootBuildImage` падает | Docker-демон недоступен | поднять Docker/Rancher Desktop |
| Имя образа не задано | не указали `imageName` | `--imageName=` или в `build.gradle` |
| Образ Buildpacks большой | дефолтная база | настроить `BP_*` env / выбрать base image |

---

## Дополнительные источники

- [Spring Boot: Container Images — Layering](https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html).
- [Spring Boot: Building Container Images (`bootBuildImage`)](https://docs.spring.io/spring-boot/reference/packaging/container-images/cloud-native-buildpacks.html).
- [Cloud Native Buildpacks](https://buildpacks.io/).
- [модуль 66](../m66_spring_boot_devops/theory.md) — `bootJar` и упаковка Spring Boot.

## Что дальше

В [модуле 114](../m114_docker_configuration/theory.md) — **внешняя конфигурация контейнера**: переменные окружения и профили (`SPRING_PROFILES_ACTIVE`), тома (volumes) для данных, логи в stdout, принципы 12-factor. Один образ — разные окружения через конфигурацию снаружи.
