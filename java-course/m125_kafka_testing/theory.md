# Модуль 125. Тестирование событийного кода: моки, `@EmbeddedKafka`, Testcontainers, Awaitility

Событийный код труднее тестировать, чем REST: результат появляется **асинхронно и в другом потоке**, брокер — внешняя система, а сбои (ретраи, DLQ) нужно воспроизводить намеренно. Курс уже дал базу тестирования Spring ([модули 101–110](../m110_spring_test_security_async/theory.md)) — здесь разбираем, как применить её к Kafka: unit-тесты publisher'а и listener'а без брокера, `@EmbeddedKafka` для интеграции, `KafkaTestUtils`, Awaitility вместо `Thread.sleep`, Testcontainers `KafkaContainer` и проверка retry/DLQ/контрактов.

> Практика — задачи в `practice/`. **Формат — тест-классы без `main`**: запуск в IDE (▶) или `./gradlew test`. **ТРЕБУЮТСЯ ЗАВИСИМОСТИ**: `spring-kafka-test`, `spring-boot-starter-test`, `awaitility`, `org.testcontainers:kafka`. Сквозной проект — тесты сервиса уведомлений и outbox-контура Task Tracker.

---

## Пирамида тестов событийного сервиса

```
   мало   +-----------------------------------------+
          |  E2E: Testcontainers (реальный брокер)  |  порядок, DLQ, миграции, конфиг
          +-----------------------------------------+
          |  Интеграция: @EmbeddedKafka             |  listener + сериализация + офсеты
          +-----------------------------------------+
   много  |  Unit: сервис + мок KafkaTemplate       |  бизнес-логика, идемпотентность, ветвления
          +-----------------------------------------+
```

| Уровень | Что проверяем | Скорость |
|---------|---------------|----------|
| Unit | «сервис публикует событие с нужным ключом», «обработчик игнорирует дубль» | миллисекунды |
| `@EmbeddedKafka` | реальный поток publish → consume, десериализация, коммиты | секунды |
| Testcontainers | поведение с настоящим брокером: compaction, retry-топики, конфиг | десятки секунд |

Правило: **логику — вниз, инфраструктуру — вверх.** Если проверка идемпотентности требует поднятого брокера, значит логика склеена с транспортом.

---

## Unit: publisher с моком `KafkaTemplate`

```java
@ExtendWith(MockitoExtension.class)
class TaskEventPublisherTest {

    @Mock KafkaTemplate<String, Object> kafka;
    @InjectMocks TaskEventPublisher publisher;

    @Test
    void publishes_with_task_id_as_key() {
        given(kafka.send(anyString(), anyString(), any()))
                .willReturn(CompletableFuture.completedFuture(null));   // send не должен вернуть null!

        publisher.publishCreated(new TaskCreated(42L, "Отчёт"));

        then(kafka).should().send(eq("task.created"), eq("42"), any(TaskCreated.class));
    }
}
```

| Ловушка | Как избежать |
|---------|--------------|
| `NullPointerException` в `whenComplete` | замокать `send(...)` так, чтобы он возвращал `CompletableFuture` |
| Проверка «отправлено» вместо «отправлено правильно» | сверять **ключ**, топик и содержимое (`ArgumentCaptor`) |
| Тест «мок вызвал мок» | проверять именно контракт: топик + ключ + поля события |

## Unit: listener как обычный метод

```java
@Test
void duplicate_event_produces_single_notification() {
    var event = new EventEnvelope(UUID.randomUUID(), "task.created", ...);

    listener.on(event, ack);
    listener.on(event, ack);            // тот же eventId

    then(notificationService).should(times(1)).notify(any());
    then(ack).should(times(2)).acknowledge();     // дубль тоже подтверждается!
}
```

Метод слушателя — просто метод. Его можно вызвать напрямую с `Acknowledgment`-моком: так проверяются идемпотентность, ветвления и то, что офсет подтверждается в правильных случаях.

---

## `@EmbeddedKafka`: брокер в JVM теста

```java
@SpringBootTest
@EmbeddedKafka(partitions = 3, topics = {"task.created"},
               brokerProperties = {"listeners=PLAINTEXT://localhost:0"})   // 0 = случайный порт
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.consumer.auto-offset-reset=earliest"
})
class TaskEventsIntegrationTest {

    @Autowired KafkaTemplate<String, Object> kafka;
    @Autowired NotificationService notifications;      // проверяем эффект

    @Test
    void event_reaches_listener() {
        kafka.send("task.created", "42", new TaskCreated(42L, "Отчёт"));

        await().atMost(Duration.ofSeconds(10))
               .untilAsserted(() -> assertThat(notifications.sentCount()).isEqualTo(1));
    }
}
```

| Нюанс | Почему важно |
|-------|--------------|
| `spring.embedded.kafka.brokers` | адрес встроенного брокера подставляется в свойства приложения |
| `auto-offset-reset=earliest` | иначе слушатель стартует «с конца» и пропустит отправленное до подписки |
| `listeners=PLAINTEXT://localhost:0` | случайный порт — тесты не конфликтуют между собой |
| Один брокер на класс | тесты в классе делят состояние топиков → уникальные топики/группы на тест |
| `@DirtiesContext` | иногда нужен, чтобы не тащить состояние контекста между классами |

---

## `KafkaTestUtils`: консьюмер и продюсер прямо в тесте

```java
Map<String, Object> props = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafka);
props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
try (Consumer<String, String> consumer = new DefaultKafkaConsumerFactory<String, String>(props)
        .createConsumer()) {
    embeddedKafka.consumeFromAnEmbeddedTopic(consumer, "task.created");

    service.createTask("Отчёт", 10);                                   // действие

    ConsumerRecord<String, String> record =
            KafkaTestUtils.getSingleRecord(consumer, "task.created", Duration.ofSeconds(10));
    assertThat(record.key()).isEqualTo("42");
    assertThat(record.value()).contains("Отчёт");
}
```

| Метод | Зачем |
|-------|-------|
| `consumerProps(group, autoCommit, broker)` | готовые настройки тестового консьюмера |
| `getSingleRecord(consumer, topic, timeout)` | дождаться ровно одной записи |
| `getRecords(consumer, timeout)` | получить все накопленные записи |
| `producerProps(broker)` | настройки тестового продюсера |

Это основной способ проверить, что **сервис действительно опубликовал событие** с нужным ключом и телом — без обращения к внутренностям приложения.

---

## Асинхронность: Awaitility вместо `sleep`

```java
// ПЛОХО: на медленной машине упадёт, на быстрой — потратит время зря
Thread.sleep(2000);
assertThat(repository.count()).isEqualTo(1);

// ХОРОШО: ждём условие, а не время
await().atMost(Duration.ofSeconds(10))
       .pollInterval(Duration.ofMillis(100))
       .untilAsserted(() -> assertThat(repository.count()).isEqualTo(1));
```

| Приём | Когда |
|-------|-------|
| `await().untilAsserted(...)` | проверить эффект (запись в БД, счётчик, вызов мока) |
| `CountDownLatch` в тестовом слушателе | дождаться конкретного сообщения |
| `KafkaTestUtils.getSingleRecord(..., timeout)` | дождаться публикации |
| `ContainerTestUtils.waitForAssignment(container, partitions)` | дождаться, пока слушатель получил партиции |

> `waitForAssignment` закрывает самую частую причину «мигающего» теста: сообщение отправлено до того, как слушатель получил партиции, а группа читает с `latest` → эффекта нет.

---

## Testcontainers: настоящий брокер

```java
@SpringBootTest
@Testcontainers
class KafkaContainerIntegrationTest {

    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("apache/kafka:3.9.0"));

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }
}
```

| `@EmbeddedKafka` | Testcontainers |
|------------------|----------------|
| быстрее, без Docker | нужен Docker, старт медленнее |
| «почти Kafka» (та же кодовая база, но в JVM теста) | ровно тот брокер, что в проде |
| хорошо для listener/serialization тестов | нужен для конфигов, compaction, retry-топиков, версий брокера |

Для сервиса с БД + Kafka в одном тесте поднимают оба контейнера (`PostgreSQLContainer` + `KafkaContainer`) — так проверяется весь контур outbox → релэй → проекция (модуль 124).

---

## Тестирование retry и DLQ

```java
@Test
void failing_event_lands_in_dlt() {
    kafka.send("task.created", "42", "bad");                    // слушатель падает

    ConsumerRecord<String, String> dlt = KafkaTestUtils.getSingleRecord(
            dltConsumer, "task.created.DLT", Duration.ofSeconds(20));

    assertThat(dlt.value()).isEqualTo("bad");
    assertThat(new String(dlt.headers().lastHeader(
            KafkaHeaders.DLT_EXCEPTION_MESSAGE).value(), UTF_8)).contains("не могу обработать");
}
```

| Что проверять | Как |
|---------------|-----|
| Число попыток | счётчик в тестовом слушателе + `assertThat(attempts).isEqualTo(4)` |
| Попадание в DLQ | тестовый консьюмер на `<топик>.DLT` |
| Не-retryable ошибка | одна попытка вместо N |
| Идемпотентность при повторе | эффект один при двух доставках |
| Таймауты | уменьшить `BackOff` в тестовом профиле (иначе тест ждёт минуты) |

> В тестовом профиле держите короткие интервалы (`FixedBackOff(50, 2)`) — иначе набор тестов начнёт занимать минуты из-за пауз ретраев.

---

## Тестирование контракта события

```java
@Test
void event_json_matches_contract() throws Exception {
    String json = objectMapper.writeValueAsString(new TaskCreated(42L, "Отчёт", 10L, now));

    assertThatJson(json).isEqualTo("""
            {"id":42,"title":"Отчёт","authorId":10,"occurredAt":"${json-unit.any-string}"}""");
}
```

Контрактный тест ловит то, что не заметит компилятор: переименованное поле, изменившийся формат даты, лишнее поле. Практика — **зафиксировать «золотой» JSON события** (approval-тест) и получать падение теста при любом изменении контракта: тогда несовместимость обсуждается на ревью, а не в проде.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест «мигает» (то проходит, то нет) | сообщение отправлено раньше назначения партиций | `ContainerTestUtils.waitForAssignment(...)` / `auto-offset-reset=earliest` |
| `NullPointerException` в тесте publisher'а | мок `send()` вернул `null` | вернуть `CompletableFuture.completedFuture(...)` |
| Тесты влияют друг на друга | общий брокер и топики между тестами | уникальные топики/группы, `@DirtiesContext` при необходимости |
| Набор тестов идёт минуты | реальные интервалы ретраев/`Thread.sleep` | короткий `BackOff` в тестах, Awaitility вместо `sleep` |
| `@EmbeddedKafka` не подхватился | адрес брокера не подставлен в свойства | `spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}` |
| Testcontainers падает в CI | нет Docker/нет прав | `@EnabledIfDockerAvailable`-подход: разделить наборы тестов (unit / integration) |
| Проверяем «слушатель вызвался», а не результат | легко пропустить ошибку в бизнес-логике | проверять эффект: запись в БД, счётчик, вызов сервиса |
| Тест зелёный, в проде дубли | идемпотентность не покрыта тестом | отдельный тест «одно событие дважды → один эффект» |

---

## Дополнительные источники

- [Spring for Apache Kafka — Testing Applications](https://docs.spring.io/spring-kafka/reference/testing.html).
- [Testcontainers — Kafka Module](https://java.testcontainers.org/modules/kafka/).
- [Awaitility](https://github.com/awaitility/awaitility/wiki/Usage) · [JsonUnit](https://github.com/lukas-krecan/JsonUnit).

## Что дальше

В [модуле 126](../m126_kafka_production_final/theory.md) — **эксплуатация и капстоун части**: Kafka в Docker Compose и Kubernetes (включая ловушку `KAFKA_PORT` и `enableServiceLinks: false`), метрики и мониторинг lag, безопасность (SASL/SSL, ACL), тюнинг продюсера и консьюмера, чек-лист production-готовности и финальный шаблон событийного сервиса.
