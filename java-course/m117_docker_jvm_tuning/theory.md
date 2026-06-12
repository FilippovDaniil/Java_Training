# Модуль 117. Docker: JVM под контейнерные лимиты, гигиена образа, non-root

Образ собран, стек поднимается ([модули 113–116](../m113_docker_spring_boot_image/theory.md)). Остался production-tuning: чтобы JVM **уважала лимиты памяти/CPU** контейнера (иначе OOM-kill), чтобы образ был **маленьким и безопасным**, и чтобы приложение работало **не от root**. В этом модуле — память/CPU JVM в контейнере, гигиена образа и non-root запуск.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (Dockerfile/команды/флаги JVM) + `println` — **компилируются bare-javac**. Сквозной проект — **Task Tracker API**.

---

## JVM и память контейнера

Исторически JVM смотрела на память **хоста**, а не контейнера, и легко выходила за лимит → контейнер убивался ядром (OOM-kill, код 137). Современные JVM (JDK 10+, особенно 11/17) **container-aware**: видят cgroup-лимиты автоматически.

```
   docker run -m 512m ...        # лимит памяти контейнера = 512 МБ
        |
   JDK 17: JVM сама видит 512 МБ (cgroups) → считает heap от этого лимита
```

### Управление heap: `MaxRAMPercentage`, не `-Xmx`

В контейнере heap задают **в процентах** от доступной памяти, а не фиксированным `-Xmx` (тогда один образ адаптируется к разным лимитам):

```bash
java -XX:MaxRAMPercentage=75.0 -jar app.jar      # heap = 75% памяти контейнера
```

```dockerfile
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

| Подход | Поведение |
|--------|-----------|
| `-Xmx512m` (фикс.) | один образ ↔ один лимит; при смене `-m` нужно править |
| `-XX:MaxRAMPercentage=75` | heap масштабируется от лимита контейнера (гибко) |

> Оставляйте запас (~25%): кроме heap JVM нужна метаспейс, стеки потоков, off-heap. 75% — разумный старт.

---

## Лимиты CPU

```bash
docker run --cpus="2" -m 1g ...        # не больше 2 CPU и 1 ГБ
```

JVM (JDK 17) по числу доступных CPU настраивает пулы (GC-потоки, common ForkJoinPool, `availableProcessors()`). Под жёстким CPU-лимитом это важно — иначе JVM создаёт слишком много потоков.

| Флаг run | Лимит |
|----------|-------|
| `-m 512m` / `--memory=512m` | память |
| `--cpus="1.5"` | доля CPU |
| `--memory-swap` | память + swap |

---

## Гигиена образа: размер и безопасность

```
   eclipse-temurin:17-jdk        ~450 МБ   (компилятор — для рантайма НЕ нужен)
   eclipse-temurin:17-jre        ~270 МБ   (только рантайм)
   eclipse-temurin:17-jre-alpine ~170 МБ   (минимальный musl-дистрибутив)
   distroless/java17             ~230 МБ   (без shell и пакетного менеджера — мин. поверхность атаки)
```

Принципы:
- рантайм — **JRE**, не JDK;
- база — **alpine**/**distroless** для минимума;
- меньше слоёв и пакетов → меньше уязвимостей;
- не ставить лишние утилиты в прод-образ.

> Сканировать образ на уязвимости: `docker scout cves <image>` (или Trivy). Меньше пакетов — меньше CVE.

---

## Non-root пользователь

По умолчанию процесс в контейнере — **root**. Это риск: пробой приложения → root-доступ к контейнеру. Запускают от непривилегированного пользователя:

```dockerfile
FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S app && adduser -S app -G app     # создать пользователя app
USER app                                          # дальше всё — от него
WORKDIR /app
COPY --chown=app:app build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```
   root (по умолчанию)  →  пробой → root в контейнере (опаснее)
   USER app             →  пробой → ограниченные права (безопаснее)
```

> Buildpacks (`bootBuildImage`, [модуль 113](../m113_docker_spring_boot_image/theory.md)) делают non-root **из коробки** — ещё причина их предпочесть.

---

## HEALTHCHECK в Dockerfile

Образ может сам сообщать о здоровье (для оркестратора/Compose):

```dockerfile
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1
```

> Опирается на Spring Boot Actuator (`/actuator/health`, [модуль 66](../m66_spring_boot_devops/theory.md)). Healthcheck в Compose (`healthcheck:`) — то же снаружи; в Dockerfile — встроено в образ.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Контейнер убит (OOM, код 137) | heap больше лимита контейнера | `-XX:MaxRAMPercentage=75`, лимит `-m` |
| Старая JVM игнорирует лимит | JDK < 10 не container-aware | JDK 11/17 (видит cgroups) |
| `-Xmx` зашит, не адаптируется | фиксированный heap | `MaxRAMPercentage` (процент от лимита) |
| Образ огромный | базовый JDK / лишние пакеты | JRE-alpine/distroless, multi-stage |
| Процесс от root | дефолт Docker | `USER app` / Buildpacks |
| Много уязвимостей в образе | толстая база, старые пакеты | минимальная база, обновление, `docker scout` |
| JVM создаёт слишком много потоков | не видит CPU-лимит | `--cpus`; JDK 17 учитывает |

---

## Дополнительные источники

- [Java in Containers — memory/CPU awareness](https://docs.oracle.com/en/java/javase/17/docs/specs/man/java.html) (флаги `-XX:MaxRAMPercentage`).
- [Docker: Runtime resource constraints (`--memory`, `--cpus`)](https://docs.docker.com/config/containers/resource_constraints/).
- [Distroless images](https://github.com/GoogleContainerTools/distroless) · [`docker scout`](https://docs.docker.com/scout/).
- [модуль 66](../m66_spring_boot_devops/theory.md) — Actuator `/actuator/health` (для HEALTHCHECK).

## Что дальше

В [модуле 118](../m118_docker_final_template/theory.md) — **финальный production-ready шаблон**: соберём всё вместе (multi-stage/layered Dockerfile, non-root, JVM-tuning, healthcheck, Compose со всеми сервисами, лимиты) в переиспользуемый template и пройдём чек-лист прод-готовности. Это капстоун всего курса.
