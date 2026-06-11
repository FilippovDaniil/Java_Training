# Модуль 114. Docker: внешняя конфигурация, профили, тома и логи

Один образ должен работать в dev, staging и prod **без пересборки** — различается только конфигурация, переданная **снаружи**. Это принцип 12-factor: «конфигурация — в окружении, не в коде». В этом модуле — как конфигурировать контейнер через переменные окружения и профили Spring, хранить данные в томах (volumes) и правильно работать с логами.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (команды/`application.yml`/env) + `println` — **компилируются bare-javac**. Сквозной проект — **Task Tracker API**.

---

## Принцип: образ один, конфигурация снаружи

```
                  ┌──────────────────────┐
   образ          │  tasktracker:1.0.0   │   (неизменный артефакт)
   (один)         └──────────┬───────────┘
                             │ запуск с разной конфигурацией
        ┌────────────────────┼────────────────────┐
        ▼                    ▼                     ▼
   dev (-e profile=dev)  staging (...)        prod (-e profile=prod)
```

> ❌ Анти-паттерн: «прод-образ» и «дев-образ» собираются отдельно. ✅ Правильно: один образ, разные env при запуске.

---

## Переменные окружения

Spring Boot читает свойства из переменных окружения (relaxed binding): `app.max-tasks` ↔ `APP_MAX_TASKS`, `spring.datasource.url` ↔ `SPRING_DATASOURCE_URL`.

```bash
docker run -d -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=prod \
    -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tasktracker \
    -e SPRING_DATASOURCE_USERNAME=app \
    -e SPRING_DATASOURCE_PASSWORD=secret \
    tasktracker:1.0.0
```

| Свойство Spring | Переменная окружения |
|-----------------|----------------------|
| `spring.profiles.active` | `SPRING_PROFILES_ACTIVE` |
| `spring.datasource.url` | `SPRING_DATASOURCE_URL` |
| `app.max-tasks` | `APP_MAX_TASKS` |
| `server.port` | `SERVER_PORT` |

Передавать пачкой удобно через файл:

```bash
docker run --env-file .env.prod tasktracker:1.0.0     # переменные из файла
```

---

## Профили Spring в контейнере

```
   application.yml            — общие свойства
   application-prod.yml       — активируется при SPRING_PROFILES_ACTIVE=prod
   application-dev.yml        — при ...=dev
```

```bash
docker run -e SPRING_PROFILES_ACTIVE=prod tasktracker:1.0.0
```

> Профиль-специфичные файлы лежат **внутри образа** (собраны с jar), а выбор активного профиля — **снаружи** (env). Секреты (пароли) в файлы образа не кладут — только через env/secret-store.

---

## Тома (volumes): данные переживают контейнер

Writable-слой контейнера удаляется вместе с ним. Данные, которые должны жить дольше (БД, загруженные файлы), хранят в **томах**:

```bash
# именованный том (управляется Docker):
docker run -d -v pgdata:/var/lib/postgresql/data postgres:16-alpine

# bind-mount (папка хоста):
docker run -d -v "$(pwd)/uploads:/app/uploads" tasktracker:1.0.0
```

| Тип | Запись | Когда |
|-----|--------|-------|
| именованный том (`-v name:/path`) | Docker управляет хранилищем | данные БД, состояние |
| bind-mount (`-v /host/path:/path`) | конкретная папка хоста | разработка, доступ к файлам с хоста |
| без тома | в writable-слое | временные данные (исчезнут с контейнером) |

```
   контейнер удалён  →  writable-слой исчез
                     →  но именованный том pgdata ОСТАЛСЯ  →  данные БД сохранены
```

---

## Логи: в stdout/stderr, не в файлы

12-factor: приложение пишет логи в **стандартный вывод**, а сбором занимается инфраструктура. Spring Boot по умолчанию логирует в консоль — это и нужно в контейнере.

```bash
docker logs -f tasktracker         # логи = stdout контейнера
```

> ❌ Не настраивайте запись логов в файл внутри контейнера (файл исчезнет, занимает слой). ✅ Пишите в stdout — Docker/драйвер логов (или Loki/ELK) их соберёт. Уровни логов настраивают через env: `-e LOGGING_LEVEL_ROOT=WARN`.

---

## 12-factor (ключевое для контейнеров)

| Принцип | На практике |
|---------|-------------|
| Конфигурация — в окружении | env-переменные, не хардкод |
| Один образ — много окружений | различие только в env/профиле |
| Backing services как ресурсы | БД по URL из env (подменяема) |
| Логи как потоки событий | stdout, не файлы |
| Stateless-процессы | состояние — во внешних томах/БД, не в контейнере |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Под разные среды собирают разные образы | конфиг зашит в образ | один образ + env снаружи |
| Данные БД пропали после `docker rm` | хранились в writable-слое | именованный том (`-v pgdata:/var/lib/postgresql/data`) |
| Секрет виден в `docker inspect`/истории | передан в Dockerfile/ENV образа | env при запуске / secret-store, не в образ |
| Логи не видны | пишутся в файл внутри контейнера | логировать в stdout |
| Профиль не применился | не задан `SPRING_PROFILES_ACTIVE` | передать env при `run` |
| `app.max-tasks` не подхватился из env | неверное имя переменной | relaxed binding: `APP_MAX_TASKS` |
| `.env` с паролями попал в git | не в `.gitignore` | добавить `.env*` в `.gitignore` |

---

## Дополнительные источники

- [The Twelve-Factor App](https://12factor.net/).
- [Spring Boot: Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html).
- [Docker: Volumes](https://docs.docker.com/storage/volumes/).
- [модуль 103](../m103_spring_test_config/theory.md) — профили и свойства (в тестах).

## Что дальше

В [модуле 115](../m115_docker_compose/theory.md) — **Docker Compose**: описать приложение + PostgreSQL одним `docker-compose.yml`, поднять весь стек одной командой, настроить порядок запуска (`depends_on` + healthcheck) и сети между сервисами. Конец ручного связывания контейнеров.
