# Модуль 116. Docker Compose: расширенный стек (+Redis, +RabbitMQ) и dev-workflow

Базовый стек app + PostgreSQL собран ([модуль 115](../m115_docker_compose/theory.md)). Реальное приложение часто опирается и на другие инфраструктурные сервисы: **Redis** (кэш/сессии) и **RabbitMQ** (очередь сообщений). В этом модуле — как добавить их в Compose, настроить healthcheck и порядок старта для каждого, удобный dev-workflow (override-файлы, профили Compose, пересборка) и диагностику типовых проблем.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (`docker-compose.yml`/`application.yml`/команды) + `println` — **компилируются bare-javac**. Сквозной проект — **Task Tracker API** + PostgreSQL + Redis + RabbitMQ.

---

## Расширенный стек

```
                    +-------------+
                    |  app (Boot) |
                    +--+---+---+--+
            jdbc       |   |   |   amqp
        +--------------+   |   +--------------+
        ▼              redis▼                  ▼
   +---------+      +---------+         +----------+
   | postgres |      |  redis  |         | rabbitmq |
   +---------+      +---------+         +----------+
```

Каждый сервис — отдельный контейнер в одной Compose-сети; app адресует их **по имени сервиса** (`db`, `redis`, `rabbitmq`).

---

## Добавляем Redis

```yaml
services:
  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
    healthcheck:
      test: ["CMD", "redis-cli", "ping"]      # ответит PONG, когда готов
      interval: 5s
      timeout: 3s
      retries: 5

  app:
    # ...
    depends_on:
      redis:
        condition: service_healthy
    environment:
      SPRING_DATA_REDIS_HOST: redis            # имя сервиса
      SPRING_DATA_REDIS_PORT: 6379
```

> В Spring Boot 3.x свойства Redis — `spring.data.redis.*` (env: `SPRING_DATA_REDIS_HOST`). Хост — имя сервиса `redis`, не localhost.

---

## Добавляем RabbitMQ

```yaml
services:
  rabbitmq:
    image: rabbitmq:3-management-alpine        # с веб-консолью управления
    ports:
      - "5672:5672"        # AMQP (приложение)
      - "15672:15672"      # веб-консоль управления (http://localhost:15672, guest/guest)
    healthcheck:
      test: ["CMD", "rabbitmq-diagnostics", "ping"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    depends_on:
      rabbitmq:
        condition: service_healthy
    environment:
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_PORT: 5672
```

| Сервис | Образ | Порты | Healthcheck |
|--------|-------|-------|-------------|
| PostgreSQL | `postgres:16-alpine` | 5432 | `pg_isready` |
| Redis | `redis:7-alpine` | 6379 | `redis-cli ping` |
| RabbitMQ | `rabbitmq:3-management-alpine` | 5672 (+15672 UI) | `rabbitmq-diagnostics ping` |

---

## Несколько зависимостей сразу

```yaml
  app:
    depends_on:
      db:        { condition: service_healthy }
      redis:     { condition: service_healthy }
      rabbitmq:  { condition: service_healthy }
```

app поднимется только когда **все** перечисленные сервисы прошли healthcheck.

---

## Dev-workflow: override-файлы

Compose автоматически объединяет `docker-compose.yml` (база) и `docker-compose.override.yml` (локальные настройки разработки):

```yaml
# docker-compose.override.yml — применяется автоматически при docker compose up
services:
  app:
    build: .                      # в dev собираем из исходников
    environment:
      SPRING_PROFILES_ACTIVE: dev
      LOGGING_LEVEL_ROOT: DEBUG
    ports:
      - "5005:5005"               # порт удалённой отладки
```

```
   docker compose up
        |
   читает docker-compose.yml + docker-compose.override.yml (мерж)
        +- override перекрывает/дополняет базу (только для dev)
```

> На проде override не используют (или передают явный набор: `-f docker-compose.yml -f docker-compose.prod.yml`).

---

## Профили Compose (необязательные сервисы)

Сервисы можно включать выборочно через `profiles`:

```yaml
services:
  rabbitmq:
    image: rabbitmq:3-management-alpine
    profiles: ["messaging"]        # поднимется только если профиль активен
```

```bash
docker compose up -d                       # без rabbitmq
docker compose --profile messaging up -d   # с rabbitmq
```

---

## Команды dev-workflow

```bash
docker compose up -d --build         # пересобрать и поднять
docker compose up -d --no-deps app   # перезапустить только app (не трогая зависимости)
docker compose logs -f app db        # логи нескольких сервисов
docker compose exec redis redis-cli  # зайти в Redis
docker compose config                # показать итоговую (смерженную) конфигурацию
docker compose down -v               # снести всё + тома
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| app не видит Redis/RabbitMQ | адресуют по localhost | хост = имя сервиса (`redis`, `rabbitmq`) |
| app стартует раньше готовности брокера | `depends_on` без condition | healthcheck + `service_healthy` для каждого |
| RabbitMQ долго стартует, healthcheck «красный» | малый `retries`/`interval` | увеличить `retries`/`timeout` |
| override применился на проде | `docker-compose.override.yml` берётся автоматически | на проде — явные `-f` файлы, без override |
| Необязательный сервис всегда поднимается | нет `profiles` | вынести в `profiles: [...]` |
| Неверный итоговый конфиг | мерж файлов неочевиден | `docker compose config` — посмотреть результат |
| Свойства Redis не читаются | старые ключи (`spring.redis.*`) | Spring Boot 3.x: `spring.data.redis.*` |

---

## Дополнительные источники

- [Compose: Multiple Compose files / override](https://docs.docker.com/compose/multiple-compose-files/).
- [Compose: Profiles](https://docs.docker.com/compose/profiles/).
- [Spring Boot: Redis](https://docs.spring.io/spring-boot/reference/data/nosql.html#data.nosql.redis) · [RabbitMQ / AMQP](https://docs.spring.io/spring-boot/reference/messaging/amqp.html).
- [`~/.claude/CLAUDE.md`] — ловушки локального стека (Kafka `enableServiceLinks: false`, порядок старта).

## Что дальше

В [модуле 117](../m117_docker_jvm_tuning/theory.md) — **JVM под контейнерные лимиты**: как JVM видит память/CPU контейнера, `-XX:MaxRAMPercentage` вместо `-Xmx`, гигиена образа (минимальный размер, distroless) и запуск от non-root пользователя. Готовим образ к проду по-настоящему.
