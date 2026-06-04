# Модуль 118. Docker: финальный production-ready шаблон (капстоун курса)

Это **последний модуль курса**. Здесь всё, что мы изучили о контейнеризации ([модули 111–117](../module-111-docker-basics/theory.md)), собирается в **переиспользуемый production-ready шаблон**: финальный Dockerfile, `docker-compose.yml` со всеми сервисами, `.dockerignore`, шаблон `.env` и чек-лист готовности к проду. Этот шаблон — то, что можно скопировать в новый Spring Boot проект и сразу получить грамотную упаковку.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (финальные артефакты) + `println` — **компилируются bare-javac**. Сквозной проект — **Task Tracker API** (финальная упаковка).

---

## Что собираем в шаблон

```
   проект/
   ├── Dockerfile                  # multi-stage / layered, non-root, JVM-tuning, HEALTHCHECK
   ├── .dockerignore               # чистый контекст сборки
   ├── docker-compose.yml          # app + db (+ redis/rabbitmq), healthcheck, тома, лимиты
   ├── .env.example                # шаблон переменных (без секретов, в git)
   └── .env                        # реальные секреты (gitignored)
```

---

## Финальный Dockerfile

Совмещает multi-stage ([112](../module-112-docker-dockerfile/theory.md)), layered jars ([113](../module-113-docker-spring-boot-image/theory.md)), non-root и JVM-tuning ([117](../module-117-docker-jvm-tuning/theory.md)):

```dockerfile
# ── этап сборки ──
FROM eclipse-temurin:17-jdk AS build
WORKDIR /src
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon          # кэш зависимостей
COPY src ./src
RUN ./gradlew bootJar --no-daemon

# ── этап рантайма: минимальный, non-root, container-aware ──
FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S app && adduser -S app -G app
WORKDIR /app
COPY --from=build --chown=app:app /src/build/libs/*.jar app.jar
USER app
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

> Альтернатива всему этому — `./gradlew bootBuildImage` (Buildpacks): non-root, layers и JVM-tuning из коробки. Dockerfile нужен, когда требуется тонкий контроль.

---

## Финальный docker-compose.yml

```yaml
services:
  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: tasktracker
      POSTGRES_USER: app
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - pgdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U app -d tasktracker"]
      interval: 5s
      timeout: 3s
      retries: 5

  app:
    build: .
    depends_on:
      db:
        condition: service_healthy
    environment:
      SPRING_PROFILES_ACTIVE: prod
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
      SPRING_DATASOURCE_USERNAME: app
      SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "8080:8080"
    deploy:
      resources:
        limits:
          memory: 512M             # лимит памяти (модуль 117)
          cpus: "2"

volumes:
  pgdata:
```

---

## `.dockerignore` и `.env.example`

```
# .dockerignore
.git
.idea
.gradle
build
*.md
```

```
# .env.example (в git — шаблон, без реальных значений)
POSTGRES_PASSWORD=change-me
# .env (gitignored — реальные секреты)
```

> `.env.example` коммитят как образец, реальный `.env` — в `.gitignore`. Новый разработчик копирует `.env.example` → `.env` и заполняет.

---

## Чек-лист production-готовности

```
   ОБРАЗ
   [ ] multi-stage / layered jars (компилятор не в рантайме)
   [ ] минимальная база (JRE-alpine / distroless)
   [ ] non-root пользователь (USER)
   [ ] JVM container-aware (MaxRAMPercentage, JDK 17)
   [ ] HEALTHCHECK (Actuator)
   [ ] просканирован на CVE (docker scout / trivy)
   [ ] фиксированные теги образов (не latest)

   КОНФИГУРАЦИЯ
   [ ] один образ — много сред (конфиг через env)
   [ ] секреты вне образа и вне git (.env gitignored / secret-store)
   [ ] профиль через SPRING_PROFILES_ACTIVE
   [ ] логи в stdout

   ДАННЫЕ И ОРКЕСТРАЦИЯ
   [ ] данные в именованных томах
   [ ] depends_on + healthcheck (порядок и готовность)
   [ ] лимиты памяти/CPU
   [ ] стек поднимается одной командой (docker compose up)
```

---

## Путь, который мы прошли (весь курс)

```
   Core Java (01–28)
        │ синтаксис, ООП, коллекции, исключения, потоки
   Инструменты и БД (29–53)
        │ Maven/Gradle, тесты, SQL, JDBC, Hibernate
   Spring (54–92)
        │ Spring Core → Boot → REST → Data JPA → Hibernate Deep Dive
   Production-ready (93–118)        ← Часть 4
        │ Security (93–100) → Test (101–110) → Docker (111–118)
        ▼
   Task Tracker API: REST + JWT-аутентификация + полное покрытие тестами + Docker-упаковка
```

От первой программы `Hello, World` до production-ready микросервиса, который собирается в безопасный контейнер и поднимается одной командой вместе с БД.

---

## Подводные камни (итоговые)

| Проблема | Причина | Решение |
|----------|---------|---------|
| Шаблон «почти готов», но течёт | пропущен пункт чек-листа | пройти чек-лист целиком перед прод-релизом |
| Секрет утёк в git/образ | `.env`/пароль в VCS или ENV образа | `.env` gitignored, секреты в secret-store |
| Образ воспроизводится по-разному | `latest`-теги | фиксировать версии (`postgres:16`, `temurin:17`) |
| Стек не поднимается «с нуля» | недокументированные шаги | всё в Compose + `.env.example`; `docker compose up` |
| Прод-tuning сделан, но не проверен | не запускали с лимитами | прогон `docker run -m ... --cpus ...` + нагрузка |

---

## Дополнительные источники

- [Docker — Build best practices](https://docs.docker.com/build/building/best-practices/).
- [Spring Boot — Production-ready features](https://docs.spring.io/spring-boot/reference/actuator/index.html).
- [The Twelve-Factor App](https://12factor.net/) · [Docker Compose production](https://docs.docker.com/compose/production/).
- [`~/.claude/CLAUDE.md`] — реальные уроки прод-упаковки (Dockerfile только COPY, build-and-load, K8s-ловушки).

## Что дальше

**Курс завершён.** Учебный путь от основ Java до production-ready Spring Boot микросервиса (безопасность, тестирование, контейнеризация) пройден полностью — модули 01–118.

Дальнейшее развитие (за рамками курса): оркестрация (Kubernetes/Helm — см. `rancher.md` и `~/.claude/CLAUDE.md`), CI/CD, мониторинг (Prometheus/Grafana/Loki), распределённые системы. Учебный репозиторий — прочный фундамент для них.
