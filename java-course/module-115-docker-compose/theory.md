# Модуль 115. Docker Compose: приложение + PostgreSQL одной командой

Запускать приложение и БД отдельными `docker run`, вручную связывать их по сети, помнить порядок старта — утомительно и хрупко ([модули 111](../module-111-docker-basics/theory.md), [114](../module-114-docker-configuration/theory.md)). **Docker Compose** описывает весь стек в одном файле `docker-compose.yml` и поднимает его одной командой. В этом модуле — структура Compose-файла, сеть между сервисами, порядок запуска через `depends_on` + healthcheck, тома и переменные.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (несёт `docker-compose.yml`) + `println` — **компилируются bare-javac**. Сам файл создавайте в корне проекта и запускайте `docker compose up`. Сквозной проект — **Task Tracker API** + PostgreSQL.

---

## Зачем Compose

```
   БЕЗ Compose:                          С Compose:
   docker network create net             docker compose up -d      ← одна команда
   docker run -d --network net db ...
   docker run -d --network net app ...   весь стек описан в docker-compose.yml:
   (помнить порядок, сеть, env...)        - сервисы, сеть, тома, env, порядок старта
```

---

## Структура `docker-compose.yml`

```yaml
services:
  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: tasktracker
      POSTGRES_USER: app
      POSTGRES_PASSWORD: secret
    volumes:
      - pgdata:/var/lib/postgresql/data        # данные переживают пересоздание
    ports:
      - "5432:5432"

  app:
    build: .                                    # собрать из Dockerfile в текущей папке
    # image: tasktracker:1.0.0                  # ИЛИ использовать готовый образ
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker   # db = имя сервиса!
      SPRING_DATASOURCE_USERNAME: app
      SPRING_DATASOURCE_PASSWORD: secret
      SPRING_PROFILES_ACTIVE: prod
    ports:
      - "8080:8080"

volumes:
  pgdata:                                       # объявление именованного тома
```

| Ключ | Назначение |
|------|------------|
| `services` | контейнеры стека |
| `image` / `build` | готовый образ или сборка из Dockerfile |
| `environment` | переменные окружения сервиса |
| `ports` | проброс портов (`host:container`) |
| `volumes` | тома (данные) |
| `depends_on` | порядок запуска |

---

## Сеть: сервисы видят друг друга по имени

Compose создаёт общую сеть; сервисы адресуются **по имени сервиса** (не localhost!):

```
   app  ──jdbc:postgresql://db:5432/...──►  db
                          │
                          └─ "db" = имя сервиса, Compose резолвит его в IP контейнера
```

> Внутри Compose-сети `db` — это hostname сервиса БД. `localhost` внутри контейнера `app` указывал бы на сам `app`, а не на `db`.

---

## Команды Compose

```bash
docker compose up -d            # поднять весь стек в фоне
docker compose up --build       # пересобрать образы и поднять
docker compose ps               # статус сервисов
docker compose logs -f app      # логи сервиса
docker compose down             # остановить и удалить контейнеры/сеть
docker compose down -v          # + удалить тома (данные!)
docker compose restart app      # перезапустить сервис
```

---

## Порядок запуска: `depends_on` + healthcheck

`depends_on` гарантирует порядок **старта**, но не **готовности**: контейнер БД «запущен» ≠ «готов принимать соединения». Spring Boot, стартовав раньше готовности БД, упадёт. Решение — healthcheck + условие:

```yaml
services:
  db:
    image: postgres:16-alpine
    environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U app -d tasktracker"]   # БД готова?
      interval: 5s
      timeout: 3s
      retries: 5

  app:
    build: .
    depends_on:
      db:
        condition: service_healthy        # ← ждать, пока db не пройдёт healthcheck
    ports: ["8080:8080"]
```

```
   db стартует → healthcheck (pg_isready) проходит → service_healthy → app стартует
```

| `depends_on` форма | Гарантия |
|--------------------|----------|
| `- db` (список) | db **запущен** раньше app (но не обязательно готов) |
| `db: { condition: service_healthy }` | app ждёт **готовности** db (healthcheck) |

> Дополнительно приложение должно быть устойчиво к недоступности БД на старте (retry). Healthcheck снимает основную гонку, но не отменяет надёжный код подключения.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| app не видит БД по `localhost` | внутри контейнера localhost = сам app | адрес = имя сервиса (`db:5432`) |
| app падает на старте: «connection refused» | стартовал раньше готовности БД | `depends_on: condition: service_healthy` + healthcheck |
| Данные пропали после `down` | `down -v` удалил тома | `down` без `-v`; тома объявлять в `volumes` |
| Изменения кода не попали | образ не пересобран | `docker compose up --build` |
| Порт занят | конфликт `ports` на хосте | сменить host-порт (`"8081:8080"`) |
| Секреты в `docker-compose.yml` в git | пароли в файле | env-файл (`env_file:`) / секреты вне VCS |
| `depends_on` «не дождался» | без healthcheck — только порядок старта | добавить healthcheck + `service_healthy` |

---

## Дополнительные источники

- [Docker Compose — overview](https://docs.docker.com/compose/).
- [Compose file reference](https://docs.docker.com/reference/compose-file/).
- [Compose: `depends_on` & healthcheck](https://docs.docker.com/reference/compose-file/services/#depends_on).
- [`~/.claude/CLAUDE.md`] — `docker-compose up -d` / `down -v` в локальной разработке.

## Что дальше

В [модуле 116](../module-116-docker-compose-extended/theory.md) — **расширенный Compose**: добавим Redis и RabbitMQ, отладим порядок старта и удобный dev-workflow (override-файлы, пересборка, troubleshooting). Стек приблизится к боевому.
