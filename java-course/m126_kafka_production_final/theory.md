# Модуль 126. Kafka в проде: Compose и Kubernetes, метрики и lag, безопасность, тюнинг, финальный шаблон

Это **последний модуль части 5 и курса**. Код событийного сервиса написан ([модули 120–124](../m124_kafka_event_patterns/theory.md)) и покрыт тестами ([модуль 125](../m125_kafka_testing/theory.md)). Осталось то, что отличает «работает у меня» от «работает в проде»: конфигурация кластера, запуск в Kubernetes с известными ловушками, метрики и мониторинг отставания, безопасность (SASL/TLS/ACL), тюнинг под нагрузку, расчёт ёмкости, runbook инцидентов и **переиспользуемый шаблон событийного сервиса**.

> Практика — задачи в `practice/`. **Задачи-носители артефактов и аналитические:** `.java` с text-блоком (Compose/K8s-манифесты, конфиги метрик, ACL-команды, расчёты, чек-листы) + `println` — **компилируются bare-javac**. Сквозной проект — финальная упаковка **Task Tracker + сервис уведомлений**.

---

## Кластер: dev-однонодовый vs прод

| Параметр | Dev (1 брокер) | Прод (3+ брокеров) |
|----------|----------------|--------------------|
| `replication.factor` топиков | 1 | **3** |
| `min.insync.replicas` | 1 | **2** (при RF=3) |
| `acks` у продюсера | 1/all | **all** |
| Системные топики (`__consumer_offsets`) | RF=1 | RF=3 |
| Данные | том или без него | том обязательно, отдельный диск |
| `auto.create.topics.enable` | можно `true` | **`false`** |
| `delete.topic.enable` | `true` | ограничить правами (ACL) |
| Число партиций | 1–3 | по пропускной способности и числу консьюмеров |

```
   RF=3 + min.insync.replicas=2 + acks=all
        = запись подтверждена как минимум двумя репликами
        = потеря одного брокера не теряет данные и не останавливает запись
   RF=3 + min.insync.replicas=3 -> падение ОДНОГО брокера останавливает запись (слишком строго)
```

---

## Kubernetes: ловушки, проверенные на практике

| Симптом | Причина | Решение |
|---------|---------|---------|
| Kafka-под падает с Exit Code 1 через 2 секунды | K8s Service links создают переменную `KAFKA_PORT`, ломающую конфиг брокера | **`enableServiceLinks: false`** в спеке пода |
| Spring Boot в CrashLoop при старте вместе с Kafka | `@KafkaListener` подключается на старте, брокера ещё нет | `initContainer wait-for-kafka` (или `spring.kafka.listener.missing-topics-fatal=false` + ретраи) |
| Приложение не находит брокер | обращение по `localhost` | `SPRING_KAFKA_BOOTSTRAP_SERVERS` = DNS сервиса (`kafka:9092`) |
| Клиенты извне кластера не подключаются | `advertised.listeners` указывает на внутреннее имя | отдельный listener + Ingress/NodePort/LoadBalancer |
| Данные исчезли после рестарта пода | нет PVC | StatefulSet + `volumeClaimTemplates` |
| Секреты видны в манифесте | креды в ConfigMap | K8s **Secret** (+ монтирование как файл/env) |

```yaml
# фрагмент Deployment приложения-потребителя
spec:
  template:
    spec:
      enableServiceLinks: false                 # ← ловушка №1 (KAFKA_PORT)
      initContainers:
        - name: wait-for-kafka                  # ← ловушка №2 (порядок старта)
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z kafka 9092; do echo waiting; sleep 2; done']
      containers:
        - name: app
          env:
            - name: SPRING_KAFKA_BOOTSTRAP_SERVERS
              value: "kafka:9092"
            - name: SPRING_KAFKA_PROPERTIES_SASL_JAAS_CONFIG
              valueFrom:
                secretKeyRef: { name: kafka-credentials, key: jaas }
          readinessProbe:
            httpGet: { path: /actuator/health/readiness, port: 8080 }
```

> Сам кластер Kafka в Kubernetes в проде обычно ставят оператором (**Strimzi**), а не самописным StatefulSet: он берёт на себя перебалансировку, обновления, сертификаты и пользователей.

---

## Метрики и мониторинг

```
   приложение (Micrometer) --> /actuator/prometheus --> Prometheus --> Grafana + Alertmanager
   брокер (JMX)            --> kafka-exporter/JMX exporter -----------^
```

| Метрика | Что показывает | Порог алерта |
|---------|----------------|--------------|
| `kafka_consumer_fetch_manager_records_lag_max` | максимальное отставание группы | растёт устойчиво / выше N |
| `spring_kafka_listener_seconds` (таймер) | время обработки записи | p99 близко к `max.poll.interval.ms` |
| `kafka_producer_record_error_total` | ошибки отправки | > 0 |
| Число записей в `*.DLT` | необработанные события | **любое ненулевое = инцидент** |
| `kafka_producer_record_send_total` / `record_retry_total` | поток и ретраи | резкий рост ретраев |
| `under_replicated_partitions` (брокер) | реплики не догоняют | > 0 |
| `offline_partitions_count` (брокер) | партиции без лидера | > 0 — авария |
| Свободное место на диске брокера | retention съел диск | < 20% |

```yaml
management:
  endpoints.web.exposure.include: health,info,prometheus,metrics
  metrics.tags.application: task-tracker
  health.mail.enabled: false           # см. ~/.claude/CLAUDE.md: иначе /health = 503
```

**Lag — главная метрика эксплуатации.** Смысл прост: `LAG = конец лога − офсет группы`. Растущий lag означает «потребитель не успевает»: не хватает партиций/экземпляров, обработка стала медленнее, или потребитель зацикливается на ретраях.

```bash
# быстрый ручной способ (модуль 119)
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group notifier
```

---

## Безопасность

| Уровень | Механизм | Зачем |
|---------|----------|-------|
| Аутентификация | **SASL/SCRAM-SHA-512** (или mTLS) | брокер знает, кто подключился |
| Шифрование | **TLS** (`SASL_SSL`) | трафик не читается в сети |
| Авторизация | **ACL** на топики и группы | сервис пишет только в свои топики |
| Секреты | K8s Secret / Vault | креды не в git и не в ConfigMap |
| Изоляция | отдельный принципал на сервис | инцидент ограничен одним сервисом |

```properties
# клиент
security.protocol=SASL_SSL
sasl.mechanism=SCRAM-SHA-512
sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required \
    username="task-tracker" password="${KAFKA_PASSWORD}";
ssl.truststore.location=/etc/kafka/certs/truststore.jks
```

```bash
# ACL: продюсеру — только запись в свой топик; потребителю — чтение топика и своей группы
kafka-acls.sh --bootstrap-server kafka:9092 --add \
    --allow-principal User:task-tracker --operation Write --topic task.created
kafka-acls.sh --bootstrap-server kafka:9092 --add \
    --allow-principal User:notifier --operation Read --topic task.created --group notifier
```

> Принцип минимальных прав здесь не формальность: без ACL любой сервис может читать все топики (включая персональные данные в чужих событиях) и удалять топики.

---

## Тюнинг

### Продюсер

| Параметр | Дефолт | Куда крутить |
|----------|--------|--------------|
| `linger.ms` | 0 | 5–20 мс: больше батч → выше пропускная способность, чуть выше задержка |
| `batch.size` | 16 КБ | 32–128 КБ при большом потоке |
| `compression.type` | none | `lz4` (быстро) / `zstd` (сильнее сжимает) |
| `acks` | all | оставить `all` для бизнес-событий |
| `max.in.flight.requests.per.connection` | 5 | ≤5 при идемпотентности (иначе порядок) |
| `buffer.memory` | 32 МБ | увеличить при пиках, чтобы `send` не блокировался |

### Потребитель

| Параметр | Дефолт | Куда крутить |
|----------|--------|--------------|
| `max.poll.records` | 500 | уменьшить, если обработка одной записи долгая |
| `fetch.min.bytes` / `fetch.max.wait.ms` | 1 / 500 | увеличить для батчевой аналитики |
| `concurrency` (Spring) | 1 | до числа партиций |
| `max.poll.interval.ms` | 5 мин | не «лечить» им медленную обработку |
| `isolation.level` | read_uncommitted | `read_committed` при транзакциях |

### Сколько партиций

```
   партиции >= пиковая_пропускная_способность / пропускная_способность_одного_консьюмера
   партиции >= максимальное_желаемое_число_консьюмеров_в_группе

   пример: 6000 событий/с, один консьюмер тянет 800/с -> >= 8; берём 12 (запас на рост)
   ограничение: очень много партиций -> дольше перебалансировка и больше нагрузка на брокер
```

### Ёмкость диска

```
   диск = сообщений/с × средний_размер × retention_сек × RF × (1 + запас 30%)
   пример: 2000/с × 1 КБ × 604800 (7 дней) × 3 ≈ 3.4 ТБ   -> уменьшить retention,
                                                              включить сжатие или compaction
```

---

## Runbook: типовые инциденты

| Симптом | Первая проверка | Действие |
|---------|-----------------|----------|
| Lag растёт линейно | `--describe --group`, время обработки p99 | добавить экземпляры (≤ партиций), ускорить обработку, при нехватке — партиции |
| Lag растёт скачками, в логах `Rebalancing` | превышен `max.poll.interval.ms` | уменьшить `max-poll-records`, вынести долгую работу, проверить длину ретраев |
| В DLT посыпались записи | заголовки `kafka_dlt-*` | найти причину, исправить, переиграть DLT (модуль 123) |
| Потребитель «не видит» события | группа, офсеты, `auto-offset-reset` | проверить группу и топик, при необходимости `--reset-offsets` |
| Продюсер получает `TimeoutException` | доступность брокера, `advertised.listeners` | сеть/DNS, число ISR, место на диске брокера |
| `NOT_ENOUGH_REPLICAS` | ISR меньше `min.insync.replicas` | поднять упавший брокер; временно не снижать ISR-требование |
| Диск брокера кончается | retention, размер топиков | уменьшить retention, compaction, расширить диск |
| Дубликаты эффектов у потребителя | идемпотентность | дедупликация по `eventId` (модуль 123) |
| События одной сущности не по порядку | ключ, non-blocking retry | ключ = id сущности, для строгого порядка — blocking retry |

---

## Чек-лист production-готовности событийного сервиса

```
   КЛАСТЕР И ТОПИКИ
   [ ] RF=3, min.insync.replicas=2, acks=all
   [ ] auto.create.topics.enable=false; топики создаются инфраструктурой (IaC)
   [ ] число партиций рассчитано под пиковую нагрузку и число консьюмеров
   [ ] retention/compaction осознанно выбраны; диск посчитан с запасом
   [ ] DLT-топики созданы заранее (столько же партиций)

   ПРОДЮСЕР
   [ ] acks=all + enable.idempotence=true
   [ ] ключ = идентификатор сущности (порядок)
   [ ] сбой отправки обрабатывается (лог/метрика), важные события — через outbox
   [ ] compression включён (lz4/zstd)

   ПОТРЕБИТЕЛЬ
   [ ] enable-auto-commit=false, ack после обработки
   [ ] обработчик идемпотентен (дедупликация по eventId)
   [ ] retry с backoff + DLQ; не-retryable ошибки не повторяются
   [ ] ErrorHandlingDeserializer (защита от poison pill)
   [ ] concurrency ≤ числа партиций

   КОНТРАКТЫ
   [ ] конверт события: eventId, type, version, occurredAt, traceId
   [ ] правила совместимости описаны; изменения проходят ревью
   [ ] контракт задокументирован (AsyncAPI/схемы в репозитории)

   ЭКСПЛУАТАЦИЯ
   [ ] метрики в Prometheus, дашборд lag/ошибок/времени обработки
   [ ] алерты: lag, ненулевой DLT, ошибки продюсера, under-replicated partitions
   [ ] логи в stdout, traceId в логах
   [ ] runbook инцидентов и процедура reprocessing из DLT

   БЕЗОПАСНОСТЬ
   [ ] SASL/SCRAM или mTLS + TLS-шифрование
   [ ] ACL по принципу минимальных прав, свой принципал на сервис
   [ ] секреты в Secret/Vault, не в git и не в ConfigMap

   K8s
   [ ] enableServiceLinks: false
   [ ] initContainer wait-for-kafka
   [ ] probes на /actuator/health/{liveness,readiness}
   [ ] лимиты CPU/памяти, JVM container-aware (модуль 117)
```

---

## Шаблон событийного сервиса (структура)

```
   service/
   +-- api/            REST-контроллеры (команды и запросы)
   +-- domain/         бизнес-логика, сущности
   +-- events/         контракты событий (record + версии) — публичный API сервиса
   +-- messaging/
   |   +-- publisher/  KafkaTemplate, outbox-релэй
   |   +-- listener/   @KafkaListener + конфигурация ретраев/DLQ
   |   +-- config/     топики (NewTopic), сериализация, ErrorHandler
   +-- projection/     проекции (модель чтения)
   +-- ops/            эндпоинты эксплуатации (DLT stats/reprocess), метрики
   +-- resources/
       +-- application.yml            профили dev/prod, конфиг Kafka
       +-- asyncapi.yml               документация событий
   k8s/  Deployment (enableServiceLinks: false, initContainer), Secret, ServiceMonitor
   compose/  локальный стек: app + db + kafka + kafka-ui
```

---

## Путь, который мы прошли (весь курс)

```
   Core Java (01–28)
        | синтаксис, ООП, коллекции, исключения, потоки
   Инструменты и БД (29–58)
        | Maven/Gradle, тесты, SQL, JDBC, Hibernate, HTTP
   Spring (59–92)
        | Spring Core → Boot → REST → Data JPA → Hibernate Deep Dive
   Production-ready (93–118)
        | Security (93–100) → Test (101–110) → Docker (111–118)
   Асинхронность и события (119–126)      ← Часть 5
        | Kafka: брокер → producer → consumer → контракты → надёжность
        | → паттерны (outbox/CQRS/saga) → тесты → эксплуатация
        ▼
   Task Tracker API: REST + JWT + тесты + Docker + событийная интеграция через Kafka
   (+ сервис уведомлений, проекция дашборда, outbox, DLQ, мониторинг lag)
```

От `Hello, World` до распределённой системы из двух сервисов, которые общаются событиями, не теряют данные при сбоях и эксплуатируются по чек-листу.

---

## Подводные камни (итоговые по части)

| Проблема | Причина | Решение |
|----------|---------|---------|
| «В dev работало» | RF=1, автосоздание топиков, один листенер | прод-профиль: RF=3, ISR=2, топики из IaC, отдельные листенеры |
| Kafka-под падает в K8s за секунды | `KAFKA_PORT` от Service links | `enableServiceLinks: false` |
| Приложение в CrashLoop при деплое | брокер ещё не готов | initContainer `wait-for-kafka` |
| Отставание заметили от пользователей | нет алерта на lag | Prometheus + алерт на lag и на DLT |
| Инцидент разбирают «по памяти» | нет runbook | таблица «симптом → проверка → действие» в репозитории |
| Любой сервис читает любые топики | нет ACL | принципал на сервис + минимальные права |
| Диск брокера кончился в выходные | retention без расчёта | расчёт ёмкости + алерт на свободное место |
| Тюнинг «на глаз» ухудшил задержку | крутили `linger.ms`/`batch.size` без измерений | менять по одному параметру и смотреть метрики |

---

## Дополнительные источники

- [Kafka — Operations](https://kafka.apache.org/documentation/#operations) · [Security](https://kafka.apache.org/documentation/#security).
- [Strimzi — Kafka на Kubernetes](https://strimzi.io/docs/operators/latest/overview) · [Spring Boot — Actuator/Micrometer](https://docs.spring.io/spring-boot/reference/actuator/metrics.html).
- [Confluent — Monitoring Kafka: consumer lag](https://docs.confluent.io/platform/current/kafka/monitoring.html).
- [`~/.claude/CLAUDE.md`] — рабочие ловушки K8s: `enableServiceLinks: false` (Kafka Exit 1), `initContainer wait-for-*`, `MANAGEMENT_HEALTH_MAIL_ENABLED`, OpenSearch `vm.max_map_count`.

## Что дальше

**Курс завершён (модули 01–126).** Пройден путь от основ языка до production-ready микросервисной архитектуры: REST, безопасность, тестирование, контейнеризация и **асинхронная интеграция через Kafka**.

Дальнейшее развитие (за рамками курса): Kafka Streams/ksqlDB и потоковая аналитика, Kubernetes-операторы (Strimzi) и оркестрация, service mesh, распределённая трассировка (OpenTelemetry), CI/CD и SRE-практики. Для проектирования систем на этом фундаменте — разделы [`system-design-course`](../../system-design-course/README.md) и [`patterns-architecture`](../../patterns-architecture/README.md) этого репозитория.
