# Модуль 120. Spring for Apache Kafka: producer, `KafkaTemplate`, сериализация, надёжность отправки

Брокер поднят, топики созданы, CLI освоен ([модуль 119](../m119_kafka_intro/theory.md)). Теперь пишем **производителя событий на Spring**: подключаем `spring-kafka`, настраиваем `KafkaTemplate`, отправляем события Task Tracker с ключом, разбираем сериализацию в JSON, обрабатываем результат отправки и настраиваем гарантии (`acks`, идемпотентность, ретраи).

> Практика — задачи в `practice/`. **ТРЕБУЮТСЯ ЗАВИСИМОСТИ** (`spring-boot-starter`, `spring-kafka`) — запуск в IDE (▶) или через Gradle; bare-`javac` без classpath не соберёт, это норма. Нужен **запущенный брокер** из задачи 07 модуля 119. Сквозной проект — **Task Tracker API** публикует `task.created` и `task.status.changed`.

---

## Подключение

```groovy
implementation 'org.springframework.kafka:spring-kafka'      // версия из Spring Boot BOM
```

Стартер не нужен: Boot видит `spring-kafka` в classpath и включает автоконфигурацию — создаёт `ProducerFactory`, `KafkaTemplate`, `KafkaAdmin` из свойств `spring.kafka.*`.

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:29092          # с хоста; в контейнере — kafka:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      acks: all
      properties:
        enable.idempotence: true
        delivery.timeout.ms: 120000
```

```
   Boot-автоконфигурация продюсера:

   spring.kafka.* --> ProducerFactory --> KafkaTemplate<K,V> --> ваш бин
                      (пул Producer'ов,    (обёртка: send, sendDefault,
                       thread-safe)         executeInTransaction, метрики)
```

---

## `KafkaTemplate`: отправка

```java
@Service
public class TaskEventPublisher {

    private final KafkaTemplate<String, Object> kafka;

    public TaskEventPublisher(KafkaTemplate<String, Object> kafka) { this.kafka = kafka; }

    public void publishCreated(TaskCreated event) {
        kafka.send("task.created", String.valueOf(event.id()), event);   // topic, key, value
    }
}
```

| Способ | Когда |
|--------|-------|
| `send(topic, key, value)` | обычный случай: ключ определяет партицию |
| `send(topic, value)` | ключ не важен (порядок не нужен) |
| `send(ProducerRecord)` | нужны headers, явная партиция, timestamp |
| `sendDefault(key, value)` | топик задан в `template.setDefaultTopic(...)` |
| `send(Message<?>)` | интеграция со Spring Messaging (`MessageBuilder`) |

`send()` **не блокирует**: запись кладётся в буфер продюсера и уходит батчем. Возвращается `CompletableFuture<SendResult<K,V>>`.

```java
kafka.send("task.created", key, event)
     .whenComplete((result, ex) -> {
         if (ex != null) {
             log.error("Не удалось отправить {}: {}", key, ex.getMessage());
         } else {
             RecordMetadata md = result.getRecordMetadata();
             log.info("Отправлено в {}-{} offset {}", md.topic(), md.partition(), md.offset());
         }
     });
```

> **Не вызывайте `.get()` без таймаута** в обработчике HTTP-запроса: поток будет ждать подтверждения брокера. Нужен синхронный вариант — `.get(5, TimeUnit.SECONDS)`, и то осознанно.

---

## Сериализация значения

| Сериализатор | Что кладёт в лог | Когда |
|--------------|------------------|-------|
| `StringSerializer` | строку как есть | простые сообщения, ручной JSON |
| `JsonSerializer` (spring-kafka) | JSON через Jackson | дефолт для событий внутри компании |
| `ByteArraySerializer` | «сырые» байты | своя схема, Protobuf/Avro вручную |
| Avro/Protobuf + Schema Registry | компактный бинарь + схема | строгие контракты (модуль 122) |

```java
// JsonSerializer добавляет заголовок __TypeId__ с именем Java-класса.
// Если потребитель — другой сервис/язык, тип отправлять не нужно:
props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
```

**Событие — это DTO, а не сущность JPA.** Публикуйте `record TaskCreated(long id, String title, Instant occurredAt)`, а не `@Entity Task`: у сущности ленивые связи, служебные поля и своя жизнь, и любая правка схемы БД сломает контракт подписчиков.

---

## Ключ = порядок и распределение

```
   send("task.created", key="42", event)
        |
        +-- StringSerializer(key) -> байты -> murmur2 % partitions -> партиция 1
                                                    |
   все события задачи 42 -> партиция 1 -> порядок гарантирован
   key = null -> sticky-распределение по партициям -> порядок НЕ гарантирован
```

| Что нужно | Ключ |
|-----------|------|
| Порядок событий одной задачи | `taskId` |
| Порядок операций по счёту клиента | `clientId` (не `operationId`!) |
| Равномерная загрузка, порядок безразличен | `null` |
| Ровно одна партиция (строгий глобальный порядок) | один константный ключ — **анти-паттерн по масштабу** |

Ключ также участвует в **compaction** (модуль 119): в compacted-топике хранится последнее значение на ключ.

---

## Создание топиков из кода

Для dev/тестов удобно объявить топики бинами — `KafkaAdmin` создаст их при старте:

```java
@Bean
NewTopic taskCreated() {
    return TopicBuilder.name("task.created")
            .partitions(6)
            .replicas(1)
            .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")   // 7 дней
            .build();
}
```

> На проде топики создаёт инфраструктура (IaC/админ), а `auto.create.topics.enable` выключают: случайная опечатка в имени топика не должна создавать новый топик.

---

## Надёжность отправки

| Свойство | Значение | Смысл |
|----------|----------|-------|
| `acks` | `0` | не ждать подтверждения — можно потерять запись |
| | `1` | ждать лидера — потеря при падении лидера до репликации |
| | `all` | ждать все in-sync реплики — **прод-выбор** |
| `enable.idempotence` | `true` | брокер отбрасывает дубли ретраев (нумерация записей) |
| `retries` / `delivery.timeout.ms` | по умолчанию `Integer.MAX_VALUE` / 2 мин | сколько и как долго повторять |
| `max.in.flight.requests.per.connection` | ≤ 5 при идемпотентности | сохраняет порядок при ретраях |
| `min.insync.replicas` (на топике) | 2 при RF=3 | сколько реплик обязаны подтвердить при `acks=all` |
| `linger.ms` / `batch.size` | 5–20 мс / 16–64 КБ | батчинг: больше пропускная способность, чуть выше задержка |
| `compression.type` | `lz4` / `zstd` | меньше трафика и места в логе |

```
   acks=all + enable.idempotence=true + min.insync.replicas=2
        = «не потеряем и не продублируем» на стороне продюсера
```

С Spring Boot 3.x `enable.idempotence=true` — значение по умолчанию у Kafka-клиента; главное не сломать его сочетанием `acks=1` или `retries=0`.

---

## Что делать, если брокер недоступен

Событие потеряно, а транзакция БД уже прошла? Это классическая проблема двойной записи. Быстрые меры:

| Мера | Суть |
|------|------|
| Логировать сбой отправки | минимум — не терять факт молча (`whenComplete` с `log.error`) |
| Не отправлять из транзакции | публикация после коммита (`@TransactionalEventListener(AFTER_COMMIT)`) |
| **Outbox pattern** | событие пишется в таблицу той же транзакцией, отправляется отдельным процессом (модуль 124) |
| Kafka-транзакции | `executeInTransaction` — атомарность нескольких отправок (модуль 123) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `TimeoutException: Topic not present in metadata` | топика нет, а автосоздание выключено | создать топик (CLI/`NewTopic`), проверить имя |
| Приложение зависает в контроллере | `send(...).get()` без таймаута | не блокировать; при необходимости `get(timeout)` |
| В логе Kafka лежит `{"empty":true}` или ошибка Jackson | публикуется JPA-сущность с ленивыми связями | публиковать `record`-DTO события |
| Потребитель другого сервиса не может десериализовать | `JsonSerializer` добавил `__TypeId__` со своим классом | `ADD_TYPE_INFO_HEADERS=false`, договориться о схеме (модуль 122) |
| События сущности обрабатываются вразнобой | ключ не задан | ключ = идентификатор сущности |
| Записи «теряются» под нагрузкой | `acks=0/1` либо игнорируются ошибки `send` | `acks=all` + обработка ошибки |
| Дубликаты после сетевого сбоя | ретраи без идемпотентности | `enable.idempotence=true` (и идемпотентный консьюмер, модуль 123) |
| Событие уехало, а транзакция откатилась | отправка внутри `@Transactional` | публикация после коммита / outbox |
| Локально работает, в контейнере нет | `bootstrap-servers: localhost:29092` внутри контейнера | внутри Compose — `kafka:9092` (env `SPRING_KAFKA_BOOTSTRAP_SERVERS`) |

---

## Дополнительные источники

- [Spring for Apache Kafka — Sending Messages](https://docs.spring.io/spring-kafka/reference/kafka/sending-messages.html).
- [Spring Boot — Messaging: Apache Kafka](https://docs.spring.io/spring-boot/reference/messaging/kafka.html).
- [Kafka — Producer Configs](https://kafka.apache.org/documentation/#producerconfigs) · [Idempotent producer](https://kafka.apache.org/documentation/#semantics).

## Что дальше

В [модуле 121](../m121_kafka_consumer_spring/theory.md) — **потребитель**: `@KafkaListener`, consumer group, автоматический и ручной коммит офсетов, `concurrency` и батчевое чтение, `JsonDeserializer` с доверенными пакетами и `ErrorHandlingDeserializer`.
