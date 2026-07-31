# Модуль 123. Надёжность: гарантии доставки, идемпотентность, ретраи, DLQ, транзакции

Контракт события описан ([модуль 122](../m122_kafka_serialization_contracts/theory.md)), producer и consumer работают. Остался главный продуктовый вопрос: **что происходит, когда что-то ломается** — падает обработчик, недоступна БД, приходит битое сообщение, перезапускается под. В этом модуле: три гарантии доставки и как их получить, идемпотентный потребитель, ретраи с `DefaultErrorHandler`, dead-letter topic, неблокирующие ретраи `@RetryableTopic`, транзакции Kafka и поведение при перебалансировке.

> Практика — задачи в `practice/`. **ТРЕБУЮТСЯ ЗАВИСИМОСТИ** (`spring-boot-starter`, `spring-kafka`) — запуск в IDE (▶). Нужен брокер. Сквозной проект — надёжный **сервис уведомлений** Task Tracker: ретраи, DLQ, дедупликация, повторная обработка.

---

## Три гарантии доставки

```
   at-most-once     коммит офсета ДО обработки
                    упали -> событие потеряно, повторов нет          (потеря допустима)

   at-least-once    коммит офсета ПОСЛЕ обработки                    ← стандарт для бизнес-событий
                    упали -> событие придёт снова (возможен дубль)

   exactly-once     ровно один раз                                    (внутри Kafka: транзакции)
                    на границе с внешним миром достигается
                    только идемпотентной обработкой
```

| Гарантия | Как получить | Цена |
|----------|--------------|------|
| at-most-once | `ack` до обработки / auto-commit | потери при сбоях |
| at-least-once | `ack` после обработки + `acks=all` у продюсера | дубли → нужна идемпотентность |
| exactly-once (внутри Kafka) | транзакции продюсера + `isolation.level=read_committed` (read-process-write) | сложнее, медленнее, только Kafka→Kafka |
| «эффективно один раз» вовне | at-least-once + **идемпотентный потребитель** | таблица обработанных событий |

> Практическое правило: **at-least-once + идемпотентность**. Ставка на «exactly-once» там, где есть письма, платежи и внешние API, — самообман: подтверждение внешней системе может потеряться в любой момент.

---

## Идемпотентный потребитель

Идемпотентность = повторная обработка того же события не меняет результат.

| Приём | Как выглядит |
|-------|--------------|
| **Таблица обработанных событий** | `INSERT INTO processed_events(event_id) ... ON CONFLICT DO NOTHING` — если вставка не прошла, событие уже обработано |
| **Естественная идемпотентность** | операция сама по себе повторяема: `UPDATE task SET status='DONE' WHERE id=42` |
| **Upsert по ключу** | проекция состояния: `INSERT ... ON CONFLICT (id) DO UPDATE` |
| **Версия/номер события** | применять только если `event.version > current.version` (защита и от дублей, и от «старых» событий) |
| **Идемпотентный ключ во внешний API** | `Idempotency-Key: eventId` — платёжные шлюзы это поддерживают |

```java
@Transactional
public void handle(EventEnvelope<TaskCreated> event) {
    if (!processedEvents.markProcessed(event.eventId())) {   // INSERT ... ON CONFLICT DO NOTHING
        log.debug("дубль события {} — пропускаем", event.eventId());
        return;
    }
    notificationService.notify(event.payload());              // побочный эффект — один раз
}
```

```
   ВАЖНО: отметка «обработано» и сам эффект должны быть в ОДНОЙ транзакции БД.
   Иначе:  отметили -> упали до эффекта  = событие потеряно
           сделали эффект -> упали до отметки = дубль эффекта при повторе
```

**Ключ дедупликации — `eventId` из конверта, а не `(topic, partition, offset)`.** Офсет меняется при повторной публикации того же факта (двойная публикация, миграция топика), а `eventId` — нет.

---

## Ретраи: `DefaultErrorHandler` + `BackOff`

Обработчик ошибок контейнера решает: повторить, пропустить или отдать «восстановителю» (recoverer).

```java
@Bean
DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> template) {
    var recoverer = new DeadLetterPublishingRecoverer(template);       // куда девать безнадёжное
    var backOff = new ExponentialBackOffWithMaxRetries(3);             // 3 повтора
    backOff.setInitialInterval(1_000L);
    backOff.setMultiplier(2.0);                                       // 1с, 2с, 4с
    var handler = new DefaultErrorHandler(recoverer, backOff);
    handler.addNotRetryableExceptions(IllegalArgumentException.class); // валидация — не повторяем
    return handler;
}
```

```
   запись -> слушатель -> исключение
                             |
                    retryable? --нет--> recoverer (DLQ) сразу
                             |да
                    попытка 1 (пауза 1с) -> 2 (2с) -> 3 (4с)
                             |всё ещё падает
                             +--> recoverer (DLQ), офсет коммитится, обработка идёт дальше
```

| Тип ошибки | Что делать |
|------------|------------|
| Временная (БД недоступна, таймаут внешнего API, `502`) | повторять с backoff |
| Постоянная (битый JSON, нарушение бизнес-правила, `404` в чужом API) | сразу в DLQ, повторы бессмысленны |
| Ошибка десериализации | `ErrorHandlingDeserializer` + DLQ (модуль 121) |

> **Blocking retry** (повторы внутри контейнера) блокирует партицию: пока повторяем запись, следующие ждут. Порядок сохранён, но растёт lag. Держите общее время повторов заметно меньше `max.poll.interval.ms` (5 мин), иначе группа уйдёт в перебалансировку.

---

## Dead Letter Topic

```java
var recoverer = new DeadLetterPublishingRecoverer(template,
        (record, ex) -> new TopicPartition(record.topic() + ".DLT", -1));   // -1 = партиция по ключу
```

`DeadLetterPublishingRecoverer` публикует сбойную запись в `<топик>.DLT`, добавляя заголовки:

| Заголовок | Содержимое |
|-----------|------------|
| `kafka_dlt-original-topic` / `-partition` / `-offset` | откуда запись пришла |
| `kafka_dlt-exception-fqcn` | класс исключения |
| `kafka_dlt-exception-message` | сообщение об ошибке |
| `kafka_dlt-original-timestamp` | исходная метка времени |

```
   task.created ----(3 неудачных попытки)----> task.created.DLT
                                                     |
                                       мониторинг: любое сообщение в DLT = инцидент
                                       разбор -> исправление -> reprocessing
                                       (админ-эндпоинт «переиграть из DLT»)
```

DLQ без разбора бессмысленна: заведите **алерт на непустой DLT** и процедуру повторной обработки. Иначе это «папка, куда мы складываем потерянные деньги».

---

## Неблокирующие ретраи: `@RetryableTopic`

```java
@RetryableTopic(
        attempts = "4",
        backoff = @Backoff(delay = 1_000, multiplier = 2.0),
        dltStrategy = DltStrategy.FAIL_ON_ERROR,
        autoCreateTopics = "true")
@KafkaListener(topics = "task.created", groupId = "notifier")
public void on(TaskCreated event) { ... }

@DltHandler
public void onDlt(TaskCreated event,
                  @Header(KafkaHeaders.DLT_ORIGINAL_TOPIC) String topic,
                  @Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String message) { ... }
```

```
   task.created --сбой--> task.created-retry-0 --сбой--> task.created-retry-1 --...--> task.created-dlt
        |                        |                             |
   основная партиция             отдельные топики с задержкой; основной поток НЕ блокируется
   продолжает обработку
```

| | Blocking retry (`DefaultErrorHandler`) | Non-blocking (`@RetryableTopic`) |
|---|---|---|
| Порядок в партиции | сохраняется | **нарушается** (сбойная запись уходит в retry-топик) |
| Основной поток | ждёт повторов | не блокируется |
| Долгие паузы (минуты/часы) | нельзя (перебалансировка) | можно |
| Число топиков | 1 (+DLT) | 1 + по топику на попытку (+DLT) |

Выбор: **порядок важнее** → blocking; **пропускная способность и долгие паузы важнее** → non-blocking.

---

## Транзакции Kafka (read-process-write)

```yaml
spring:
  kafka:
    producer:
      transaction-id-prefix: tasktracker-tx-      # включает транзакционный продюсер
    consumer:
      isolation-level: read_committed             # не видеть незакоммиченные записи
```

```java
kafka.executeInTransaction(t -> {
    t.send("task.created", key, created);
    t.send("task.audit",   key, audit);
    return true;                                  // обе записи станут видны вместе
});
```

Транзакция даёт атомарность **внутри Kafka**: несколько отправок + коммит офсетов входного топика становятся одной операцией. Это и есть «exactly-once» в паре Kafka→Kafka (`Kafka Streams` включает её флагом `processing.guarantee=exactly_once_v2`).

```
   ЧЕГО ТРАНЗАКЦИЯ KAFKA НЕ ДАЁТ:
   транзакция БД  +  отправка в Kafka  ≠  атомарность
        |                     |
   коммит БД прошёл, брокер недоступен -> событие потеряно
   событие ушло, транзакция откатилась -> подписчики узнали о небывшем

   Решение — outbox pattern (модуль 124): событие пишется в таблицу той же транзакцией.
```

---

## Перебалансировка и «зависший» обработчик

| Параметр | Значение | Смысл |
|----------|----------|-------|
| `max.poll.interval.ms` | 300000 (5 мин) | не успели обработать партию — участник считается мёртвым, партиции переедут |
| `max-poll-records` | 500 | сколько записей отдаёт один `poll` |
| `session.timeout.ms` / `heartbeat.interval.ms` | 45с / 3с | сердцебиение членства в группе |
| `partition.assignment.strategy` | `CooperativeStickyAssignor` | перебалансировка без «остановки мира» |

```
   Обработка одной записи 2 сек, max-poll-records 500 -> 1000 сек на партию
        -> превышен max.poll.interval.ms -> rebalance -> те же записи заново -> цикл

   Лечение: уменьшить max-poll-records / ускорить обработку / увеличить партиции
            (увеличивать max.poll.interval.ms — последнее средство)
```

`ConsumerRebalanceListener` (в Spring — `ConsumerAwareRebalanceListener`) позволяет доделать работу перед отдачей партиций: закоммитить офсеты, дописать батч.

---

## Итоговая схема надёжности

```
   ПРОДЮСЕР                    БРОКЕР                     ПОТРЕБИТЕЛЬ
   acks=all                    RF=3                       ack после обработки
   enable.idempotence=true     min.insync.replicas=2      идемпотентность по eventId
   обработка ошибки send       retention с запасом        retry с backoff -> DLT
   (или outbox)                                            алерт на DLT + reprocessing
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Клиент получил два письма об одном событии | at-least-once без дедупликации | таблица обработанных `eventId` в той же транзакции |
| Дедупликация «не работает» после миграции топика | ключ дедупликации = офсет | дедуплицировать по `eventId` из конверта |
| Слушатель бесконечно повторяет одну запись | нет ограничения попыток / нет DLQ | `DefaultErrorHandler` с `BackOff` + `DeadLetterPublishingRecoverer` |
| Ошибка валидации повторяется 10 раз | она не-retryable, а обработчик этого не знает | `addNotRetryableExceptions(...)` |
| Группа ушла в rebalance во время повторов | суммарное время retry > `max.poll.interval.ms` | короче backoff или неблокирующие ретраи |
| Порядок событий сущности нарушился | non-blocking retry увёл запись в retry-топик | для строгого порядка — blocking retry |
| В DLT копятся сообщения, никто не знает | нет мониторинга | алерт на размер DLT + процедура reprocessing |
| Событие ушло, а в БД ничего нет | отправка внутри транзакции БД | публикация после коммита / outbox (модуль 124) |
| `read_committed` не помог: видны «мусорные» записи | продюсер не транзакционный | `transaction-id-prefix` + `executeInTransaction` |
| Батч падает целиком из-за одной записи | батчевый слушатель без указания сбойного индекса | `BatchListenerFailedException(record, index)` |

---

## Дополнительные источники

- [Spring for Apache Kafka — Handling Exceptions](https://docs.spring.io/spring-kafka/reference/kafka/annotation-error-handling.html) · [Non-Blocking Retries](https://docs.spring.io/spring-kafka/reference/retrytopic.html).
- [Spring Kafka — Transactions](https://docs.spring.io/spring-kafka/reference/kafka/transactions.html).
- [Kafka — Message Delivery Semantics](https://kafka.apache.org/documentation/#semantics) · [Incremental Cooperative Rebalancing](https://www.confluent.io/blog/incremental-cooperative-rebalancing-in-kafka/).

## Что дальше

В [модуле 124](../m124_kafka_event_patterns/theory.md) — **событийные паттерны**: outbox (атомарность БД и брокера), CDC/Debezium, event sourcing и CQRS-проекции, saga для распределённых процессов, compacted-топики как «таблица состояний», обзор Kafka Streams.
