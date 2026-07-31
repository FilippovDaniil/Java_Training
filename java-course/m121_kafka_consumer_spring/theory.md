# Модуль 121. `@KafkaListener`: consumer group, офсеты, коммиты, параллелизм, десериализация

События публикуются ([модуль 120](../m120_kafka_producer_spring/theory.md)) — пора их **читать**. В этом модуле: `@KafkaListener` и как он устроен внутри, `group.id` и `auto-offset-reset`, автоматический и **ручной коммит офсетов**, `concurrency` и батчевое чтение, `JsonDeserializer` с доверенными пакетами и `ErrorHandlingDeserializer` — защита от «сообщения-отравы».

> Практика — задачи в `practice/`. **ТРЕБУЮТСЯ ЗАВИСИМОСТИ** (`spring-boot-starter`, `spring-kafka`) — запуск в IDE (▶). Нужен брокер и топики из модуля 119. Сквозной проект — **сервис уведомлений** читает `task.created` и `task.status.changed`.

---

## Как работает `@KafkaListener`

```
   spring.kafka.consumer.*  --> ConsumerFactory
                                     |
   @KafkaListener  --> KafkaListenerContainerFactory --> MessageListenerContainer
                                                              |  цикл в своём потоке:
                                                              +- consumer.poll(timeout)
                                                              +- вызов вашего метода на каждой записи
                                                              +- коммит офсета (по ack-mode)
                                                              +- обработка ошибок (ErrorHandler)
```

Вы пишете метод — контейнер обеспечивает поток, poll-цикл, коммиты, ретраи и обработку ошибок.

```java
@Component
public class TaskEventsListener {

    @KafkaListener(topics = "task.created", groupId = "notifier")
    public void onTaskCreated(TaskCreated event) {
        // ваша обработка
    }
}
```

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:29092
    consumer:
      group-id: notifier                 # общий group.id по умолчанию
      auto-offset-reset: earliest        # новая группа читает лог с начала
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "com.example.events"
```

Автоконфигурацию слушателей включает `@EnableKafka` — в Spring Boot он добавляется автоматически при наличии `spring-kafka`.

---

## Что можно принимать в метод слушателя

| Подпись | Что получаете |
|---------|---------------|
| `void on(TaskCreated event)` | десериализованное значение |
| `void on(String json)` | «сырое» значение строкой |
| `void on(ConsumerRecord<String, TaskCreated> rec)` | всё: key, value, topic, partition, offset, headers, timestamp |
| `void on(TaskCreated e, @Header(KafkaHeaders.RECEIVED_KEY) String key)` | значение + отдельные заголовки |
| `void on(List<TaskCreated> batch)` | батч записей (`batch-listener: true`) |
| `void on(TaskCreated e, Acknowledgment ack)` | ручное подтверждение офсета |
| `@SendTo("other.topic")` + `return` | ответ публикуется в другой топик |

```java
@KafkaListener(topics = {"task.created", "task.status.changed"}, groupId = "audit")
public void onAny(ConsumerRecord<String, Object> rec) {
    log.info("{}-{} offset {} key {}", rec.topic(), rec.partition(), rec.offset(), rec.key());
}
```

---

## Группы, офсеты и `auto-offset-reset`

```
   Топик task.created                группы читают НЕЗАВИСИМО:
   [0][1][2][3][4][5]
         ▲        ▲
         |        +-- группа "analytics": офсет 5
         +----------- группа "notifier":  офсет 2

   Офсеты групп хранятся в системном топике __consumer_offsets на брокере.
```

| `auto-offset-reset` | Поведение, когда для группы **нет** сохранённого офсета |
|---------------------|--------------------------------------------------------|
| `latest` (по умолчанию) | читать только новые сообщения; вся история пропущена |
| `earliest` | читать лог с начала — нужно, если история важна |
| `none` | исключение, если офсета нет |

> Значение применяется **только при первом чтении группы** (или после истечения `offsets.retention.minutes`). Смена `earliest`/`latest` у уже читавшей группы ничего не изменит — нужен `--reset-offsets` (модуль 119).

**Смена `group.id` = новый читатель с нуля.** Приём на практике: чтобы перечитать топик, заводят группу `notifier-v2`.

---

## Коммит офсета: кто и когда

```
   poll() -> [записи] -> ваш метод -> КОММИТ офсета
                                        |
             ack-mode решает: когда именно и что произойдёт при падении
```

| `ack-mode` | Когда коммитится | Особенности |
|------------|------------------|-------------|
| `BATCH` (по умолчанию в Spring) | после обработки всех записей poll'а | компромисс скорости и безопасности |
| `RECORD` | после каждой записи | меньше повторов при сбое, больше коммитов |
| `TIME` / `COUNT` / `COUNT_TIME` | по таймеру/счётчику | для потоковой нагрузки |
| `MANUAL` / `MANUAL_IMMEDIATE` | вы вызываете `ack.acknowledge()` | полный контроль |

```yaml
spring:
  kafka:
    consumer:
      enable-auto-commit: false      # ключевое: коммитом управляет Spring, а не клиент Kafka
    listener:
      ack-mode: manual_immediate
```

```java
@KafkaListener(topics = "task.created", groupId = "notifier")
public void on(TaskCreated event, Acknowledgment ack) {
    notificationService.notify(event);   // сначала работа...
    ack.acknowledge();                   // ...потом подтверждение офсета
}
```

**Порядок важен.** Коммит до обработки = **at-most-once** (упали → событие потеряно). Коммит после обработки = **at-least-once** (упали → событие придёт снова, обработчик обязан быть идемпотентным — модуль 123).

> `enable.auto.commit=true` (коммит по таймеру внутри клиента Kafka) в Spring-приложениях не используют: офсет может закоммититься раньше, чем сообщение реально обработано.

---

## Параллелизм: `concurrency` и партиции

```java
@KafkaListener(topics = "task.created", groupId = "notifier", concurrency = "3")
```

```
   concurrency = 3 -> 3 потока-консьюмера в одном приложении
   топик из 6 партиций -> каждому потоку по 2 партиции

   concurrency = 8 на 6 партициях -> 2 потока простаивают
   3 экземпляра приложения x concurrency 3 = 9 консьюмеров -> лишние простаивают
```

| Хочу | Делать |
|------|--------|
| Обрабатывать быстрее | `concurrency` ≤ число партиций; при нехватке — добавить партиции |
| Сохранить порядок по сущности | ключ = id сущности; параллелизм не ломает порядок внутри партиции |
| Тяжёлая обработка (сеть, БД) | больше партиций + больше экземпляров сервиса, а не длинный `max.poll.interval.ms` |

---

## Батчевое чтение

```yaml
spring:
  kafka:
    listener:
      type: batch
    consumer:
      max-poll-records: 500
```

```java
@KafkaListener(topics = "task.created", groupId = "analytics")
public void onBatch(List<TaskCreated> events, Acknowledgment ack) {
    analytics.saveAll(events);   // одна вставка на 500 событий вместо 500 вставок
    ack.acknowledge();
}
```

Батч выгоден, когда обработка идёт в БД/внешний API пачками. Цена: при ошибке повторяется весь батч (частично лечится `BatchListenerFailedException` — модуль 123).

---

## Десериализация значения

```yaml
value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
properties:
  spring.json.trusted.packages: "com.example.events"      # или "*" (небезопасно)
  spring.json.value.default.type: com.example.events.TaskCreated
```

| Проблема | Решение |
|----------|---------|
| В сообщении нет заголовка типа | задать `spring.json.value.default.type` |
| Тип в заголовке — класс чужого сервиса | `JsonMessageConverter` + тип из подписи метода; либо мапить пакеты `spring.json.type.mapping` |
| «Отравленное» сообщение (битый JSON) — контейнер зацикливается | **`ErrorHandlingDeserializer`** |

```yaml
value-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
properties:
  spring.deserializer.value.delegate.class: org.springframework.kafka.support.serializer.JsonDeserializer
```

Обёртка перехватывает сбой десериализации и передаёт запись в обработчик ошибок (а тот отправит её в DLQ — модуль 123), вместо бесконечного повтора одной и той же битой записи.

```
   БЕЗ ErrorHandlingDeserializer:               С ErrorHandlingDeserializer:
   битая запись -> исключение в poll            битая запись -> null + заголовок с ошибкой
        -> офсет не двигается                        -> ErrorHandler -> DLQ
        -> тот же сбой в цикле (poison pill)         -> офсет двинулся, обработка продолжается
```

---

## Управление контейнером во время работы

```java
@Autowired KafkaListenerEndpointRegistry registry;

registry.getListenerContainer("notifier-listener").pause();    // остановить чтение
registry.getListenerContainer("notifier-listener").resume();
```

Пригодится для «технической паузы» (миграция, деградация внешнего API) и для тестов. `@KafkaListener(id = "notifier-listener", autoStartup = "false")` — слушатель, который стартует по команде.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Слушатель «не видит» старые сообщения | новая группа + `auto-offset-reset: latest` | `earliest` (до первого чтения) или `--reset-offsets` |
| События приходят повторно после рестарта | коммит не дошёл (at-least-once) | идемпотентная обработка (модуль 123) |
| Событие потерялось при падении | коммит раньше обработки / auto-commit | `enable-auto-commit: false`, ack после работы |
| `Rebalancing` в цикле, `max.poll.interval.ms exceeded` | обработка одной партии дольше 5 минут | ускорить обработку, уменьшить `max-poll-records`, вынести долгую работу в пул |
| Добавили `concurrency`, скорость та же | партиций меньше, чем потоков | увеличить число партиций |
| `SerializationException` в бесконечном цикле | «отравленная» запись без `ErrorHandlingDeserializer` | обёртка + DLQ |
| `The class ... is not in the trusted packages` | `JsonDeserializer` защищается от произвольных типов | указать `spring.json.trusted.packages` |
| Два слушателя одного топика в одном приложении читают по разу | одинаковый `group.id` — они делят партиции | разные `group.id` для разных задач |
| `@KafkaListener` не срабатывает вообще | класс не бин / нет `spring-kafka` в classpath / не тот топик | `@Component`, проверить имя топика и `bootstrap-servers` |

---

## Дополнительные источники

- [Spring for Apache Kafka — Receiving Messages](https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages.html).
- [Spring Kafka — Committing Offsets / Ack modes](https://docs.spring.io/spring-kafka/reference/kafka/receiving-messages/message-listener-container.html#committing-offsets).
- [Kafka — Consumer Configs](https://kafka.apache.org/documentation/#consumerconfigs) · [Consumer groups и rebalance](https://kafka.apache.org/documentation/#design_consumerposition).

## Что дальше

В [модуле 122](../m122_kafka_serialization_contracts/theory.md) — **контракты событий**: конверт события (`eventId`, `type`, `occurredAt`, `version`), JSON против Avro/Protobuf, Schema Registry и совместимость схем, заголовки сообщений, соглашения об именовании топиков.
