# Модуль 112. Docker: Dockerfile, слои, кэш и multi-stage build

Запускать чужие образы умеем ([модуль 111](../m111_docker_basics/theory.md)). Теперь соберём **свой**. `Dockerfile` — это рецепт образа: набор инструкций, каждая создаёт слой. Понимание слоёв и кэша сборки отличает образ, который собирается за секунды, от того, что качает интернет каждый раз. В этом модуле — инструкции Dockerfile, кэширование слоёв, `.dockerignore` и multi-stage build.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (несёт `Dockerfile`) и `println` — **компилируются bare-javac**. Сам `Dockerfile` создавайте в корне проекта и собирайте `docker build`. Сквозной проект — **Task Tracker API** (упаковка Spring Boot jar).

---

## Dockerfile: основные инструкции

```dockerfile
FROM eclipse-temurin:17-jre            # базовый образ (с чего начинаем)
WORKDIR /app                            # рабочая директория внутри образа
COPY build/libs/app.jar app.jar         # скопировать файл с хоста в образ
EXPOSE 8080                             # задокументировать порт (не публикует сам!)
ENTRYPOINT ["java", "-jar", "app.jar"]  # команда запуска контейнера
```

| Инструкция | Назначение |
|------------|------------|
| `FROM` | базовый образ (обязательно первая) |
| `WORKDIR` | рабочая директория (создаётся, если нет) |
| `COPY` | копировать файлы хост → образ |
| `ADD` | как COPY + распаковка архивов/URL (обычно предпочитают COPY) |
| `RUN` | выполнить команду при СБОРКЕ (создаёт слой) |
| `ENV` | переменная окружения в образе |
| `EXPOSE` | задокументировать порт (публикует `-p` при `run`) |
| `ENTRYPOINT` | основная команда контейнера |
| `CMD` | аргументы по умолчанию / команда (может переопределяться) |

### `ENTRYPOINT` vs `CMD`

```dockerfile
ENTRYPOINT ["java", "-jar", "app.jar"]   # фиксированная команда
CMD ["--spring.profiles.active=prod"]     # аргументы по умолчанию (переопределяемы при run)
```

| | переопределяется при `docker run ...` |
|--|--------------------------------------|
| `ENTRYPOINT` | нет (только через `--entrypoint`) |
| `CMD` | да (аргументами после имени образа) |

---

## Сборка и слои

```bash
docker build -t tasktracker:1.0.0 .     # собрать образ из Dockerfile в текущей директории (.)
docker run -p 8080:8080 tasktracker:1.0.0
```

Каждая инструкция Dockerfile = **слой** (read-only). Слои кэшируются:

```
   FROM eclipse-temurin:17-jre   -+
   WORKDIR /app                   +- слои (кэшируются по содержимому)
   COPY app.jar app.jar          -+
   ENTRYPOINT [...]              -+
```

> Docker переиспользует кэш слоя, пока инструкция и её входные данные **не изменились**. Изменили `app.jar` → пересобирается слой `COPY` и все **после** него; слои **до** берутся из кэша.

---

## Порядок инструкций = эффективность кэша

Меняющееся (код) кладут **в конец**, стабильное (зависимости) — **в начало**, чтобы кэш реже инвалидировался:

```dockerfile
# ❌ плохо: любое изменение кода инвалидирует загрузку зависимостей
COPY . .
RUN ./gradlew build

# ✅ лучше: зависимости отдельным слоем (меняются редко) → кэшируются
COPY build.gradle settings.gradle ./
RUN ./gradlew dependencies          # слой кэшируется, пока не изменился build.gradle
COPY src ./src                       # код меняется часто — отдельный слой ниже
RUN ./gradlew bootJar
```

---

## `.dockerignore`

Исключает лишнее из контекста сборки (ускоряет, уменьшает образ):

```
# .dockerignore
.git
build
*.md
.idea
target
```

> Без `.dockerignore` в контекст уходит вся папка (вкл. `.git`, `build`) — медленно и может попасть лишнее.

---

## Multi-stage build: сборка и рантайм раздельно

Проблема: если собирать jar внутри образа c JDK + Gradle, финальный образ тащит компилятор и кэш — сотни МБ лишнего. Решение — **два этапа**: собрать в одном, скопировать результат в чистый рантайм-образ.

```dockerfile
# -- Этап 1: сборка (есть JDK + Gradle) --
FROM eclipse-temurin:17-jdk AS build
WORKDIR /src
COPY . .
RUN ./gradlew bootJar --no-daemon        # собираем jar

# -- Этап 2: рантайм (только JRE, ничего лишнего) --
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /src/build/libs/*.jar app.jar    # копируем ТОЛЬКО jar из этапа build
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```
   [Этап build: JDK+Gradle+исходники]  --только jar--►  [Этап runtime: JRE+jar]
            (выбрасывается)                                  (финальный образ — компактный)
```

| | один этап (JDK+Gradle) | multi-stage |
|--|------------------------|-------------|
| размер образа | большой (компилятор, кэш) | маленький (JRE + jar) |
| поверхность атаки | шире | у́же |

> Альтернатива — собирать jar на хосте, а в Dockerfile только `COPY` (надёжнее в окружениях с нестабильным интернетом — см. урок из `~/.claude/CLAUDE.md`). Multi-stage хорош для CI; «собрать снаружи + COPY» — для локальной упаковки.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Каждая сборка качает зависимости заново | код скопирован до зависимостей | сперва `COPY` манифестов сборки, потом код |
| Образ огромный | собирали jar внутри (JDK+Gradle в образе) | multi-stage: рантаймовый образ = JRE + jar |
| `EXPOSE` не открыл порт | `EXPOSE` лишь документирует | публиковать через `-p` при `run` |
| `.git`/`build` попали в образ | нет `.dockerignore` | добавить `.dockerignore` |
| Аргументы запуска «зашиты» | всё в `ENTRYPOINT` | переопределяемые — в `CMD` |
| Gradle в Docker падает по таймауту | wrapper качает дистрибутив без кэша | `--no-daemon`; либо собрать jar на хосте + `COPY` (CLAUDE.md) |
| `latest` базовый образ «плывёт» | плавающий тег | фиксировать (`eclipse-temurin:17-jre`) |

---

## Дополнительные источники

- [Dockerfile reference](https://docs.docker.com/reference/dockerfile/).
- [Docker: Multi-stage builds](https://docs.docker.com/build/building/multi-stage/).
- [Docker build cache — best practices](https://docs.docker.com/build/cache/).
- [`~/.claude/CLAUDE.md`] — урок: multi-stage с Gradle ненадёжна → собирать jar на хосте, Dockerfile только COPY.

## Что дальше

В [модуле 113](../m113_docker_spring_boot_image/theory.md) — **образы именно для Spring Boot**: layered jars (расслоение зависимостей и кода для кэша), `bootBuildImage` (Buildpacks — образ без Dockerfile вообще). Доведём упаковку Spring Boot до оптимума.
