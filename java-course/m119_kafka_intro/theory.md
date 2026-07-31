# Модуль 119. Брокеры сообщений и Apache Kafka: зачем, из чего состоит, как поднять

Всё, что мы делали до сих пор, было **синхронным**: клиент послал HTTP-запрос — сервис в этом же потоке сходил в БД и ответил ([модули 67–76](../m67_spring_rest_http_backend/theory.md)). В продуктовой разработке этого мало: сервисов много, часть работы не должна задерживать ответ пользователю, а падение соседа не должно ронять нас. Появляется **асинхронное взаимодействие через брокер сообщений**. RabbitMQ мы уже видели в Compose-стеке ([модуль 116](../m116_docker_compose_extended/theory.md)) — теперь разбираем **Apache Kafka**, отраслевой стандарт для событийных систем.

Этот модуль — фундамент: модель данных Kafka (топик → партиция → офсет), кто такие producer/consumer/consumer group, как поднять брокер в Docker Compose и как посмотреть на него изнутри через CLI. Без этого `@KafkaListener` в следующих модулях будет магией.

> Практика — задачи в `practice/`. **Задачи-носители:** `.java` с text-блоком (`docker-compose.yml` / CLI-команды) + `println` — **компилируются bare-javac**. Артефакты переносите в реальные файлы и выполняйте. Сквозной проект — **Task Tracker API**: учимся публиковать события о задачах.

---

## Синхронный вызов vs событие

```
   СИНХРОННО (REST):                        АСИНХРОННО (Kafka):
   +--------+  HTTP  +----------+           +--------+  event  +-------+  event  +----------+
   | orders |------->| notifier |           | orders |-------->| Kafka |-------->| notifier |
   +--------+ <------+----------+           +--------+         +-------+         +----------+
        ждёт ответа                              не ждёт          хранит          читает когда готов
   notifier упал -> orders получил 500     notifier упал -> события лежат в топике
   новый подписчик -> править orders       новый подписчик -> просто читает топик
```

| Критерий | Синхронный REST | Событие через брокер |
|----------|-----------------|----------------------|
| Связанность | вызывающий знает адрес и контракт получателя | знает только топик и формат события |
| Доступность получателя | обязательна **сейчас** | не обязательна: сообщение полежит |
| Ответ | есть (запрос-ответ) | нет (fire-and-forget), ответ — отдельным событием |
| Пиковая нагрузка | передаётся вниз мгновенно | сглаживается очередью (backpressure) |
| Новый потребитель | правки в отправителе | подписался и читает, отправитель не меняется |
| Когда выбирать | нужен результат для ответа клиенту | побочные эффекты, интеграции, аналитика, рассылки |

> Правило продуктовой разработки: **в HTTP-транзакции — только то, без чего нельзя ответить клиенту.** Письмо, push, пересчёт отчёта, синхронизация с CRM — событием.

---

## Kafka — не «очередь», а распределённый лог

RabbitMQ — брокер **очередей**: сообщение доставлено потребителю и удалено. Kafka — **распределённый журнал (log)**: сообщение дописывается в конец файла и живёт заданное время (`retention`), а потребители лишь двигают свой «курсор» по нему.

```
   Топик "task.created", 3 партиции. Каждая партиция — append-only лог:

   P0: [0][1][2][3][4]              ← новые записи дописываются справа
   P1: [0][1][2]
   P2: [0][1][2][3][4][5][6]
        ▲        ▲
        |        +-- offset 4: позиция группы "analytics" (читает медленно)
        +----------- offset 1: позиция группы "notifier"  (перечитывает историю)
```

| Термин | Что это |
|--------|---------|
| **Broker** | один процесс Kafka; несколько брокеров = **кластер** |
| **Topic** | именованный поток событий («таблица» событийного мира) |
| **Partition** | часть топика, упорядоченный append-only лог; единица параллелизма |
| **Offset** | номер записи внутри партиции (монотонно растёт, не переиспользуется) |
| **Record** | сообщение: `key`, `value`, `timestamp`, `headers` |
| **Producer** | пишет записи в топик |
| **Consumer** | читает записи и хранит свой офсет |
| **Consumer group** | набор консьюмеров, делящих партиции между собой |
| **Replication factor** | сколько копий партиции держат разные брокеры (прод: 3) |
| **Retention** | сколько данные лежат в топике (по времени/размеру), по умолчанию 7 дней |
| **KRaft** | встроенный режим кворума вместо внешнего ZooKeeper (актуальный) |

**Гарантия порядка есть только внутри партиции.** Порядок «по сущности» получают, отправляя все её события с одним ключом: `key = taskId` → hash(key) → всегда одна и та же партиция.

---

## Партиции, ключи и параллелизм

```
   producer.send("task.created", key="task-42", event)
        |
        +-- hash("task-42") % 3  ->  партиция P1  (всегда та же для этого ключа)

   Группа "notifier" из 2 консьюмеров на 3 партициях:
   C1 -> P0, P2      C2 -> P1          (партиция читается ровно одним членом группы)

   Группа из 4 консьюмеров на 3 партициях:
   C1 -> P0  C2 -> P1  C3 -> P2  C4 -> простаивает   ← лишний, партиций не хватило
```

| Правило | Следствие |
|---------|-----------|
| Партиция читается **одним** консьюмером группы | параллелизм группы ≤ число партиций |
| Ключ определяет партицию | один ключ = гарантированный порядок событий сущности |
| `key = null` | round-robin/sticky по партициям, порядок не гарантирован |
| Партиции легко добавить, убрать — нельзя | закладывайте запас (например, 6–12 на топик) |
| Разные группы читают независимо | одно событие получают и notifier, и analytics |

---

## Kafka в Docker Compose (KRaft, без ZooKeeper)

```yaml
services:
  kafka:
    image: apache/kafka:3.9.0            # официальный образ, KRaft + CLI внутри
    ports:
      - "29092:29092"                    # порт для приложений с ХОСТА
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka:9093
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_INTER_BROKER_LISTENER_NAME: INTERNAL
      KAFKA_LISTENERS: INTERNAL://:9092,HOST://:29092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: INTERNAL://kafka:9092,HOST://localhost:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,INTERNAL:PLAINTEXT,HOST:PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1          # одна нода -> RF=1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS: 0          # быстрый старт группы в dev
    healthcheck:
      test: ["CMD-SHELL", "/opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092 || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
```

### Два листенера — главный подводный камень

`advertised.listeners` — это адрес, который брокер **сам сообщает клиенту** при подключении. Клиент затем идёт именно по нему, а не по адресу из `bootstrap-servers`.

```
   приложение в контейнере  -> bootstrap kafka:9092      -> брокер отвечает "я kafka:9092"      OK
   приложение с хоста       -> bootstrap localhost:29092 -> брокер отвечает "я localhost:29092" OK
   один листенер на kafka:9092, клиент с хоста -> брокер отвечает "я kafka:9092" -> UnknownHostException
```

Поэтому в dev держат два листенера: `INTERNAL` (для контейнеров, `kafka:9092`) и `HOST` (для IDE/CLI с хоста, `localhost:29092`).

### Веб-интерфейс (удобно смотреть глазами)

```yaml
  kafka-ui:
    image: kafbat/kafka-ui:latest        # http://localhost:8081
    ports: ["8081:8080"]
    environment:
      KAFKA_CLUSTERS_0_NAME: local
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092
    depends_on:
      kafka: { condition: service_healthy }
```

---

## CLI: смотрим на брокер изнутри

Все скрипты лежат в образе в `/opt/kafka/bin` и запускаются через `docker compose exec`:

```bash
# топики
docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
    --create --topic task.created --partitions 3 --replication-factor 1
docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
    --describe --topic task.created

# записать / прочитать
docker compose exec -it kafka /opt/kafka/bin/kafka-console-producer.sh \
    --bootstrap-server localhost:9092 --topic task.created
docker compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server localhost:9092 --topic task.created --from-beginning

# группы и отставание (lag)
docker compose exec kafka /opt/kafka/bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
docker compose exec kafka /opt/kafka/bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 \
    --describe --group notifier
```

| Скрипт | Зачем |
|--------|-------|
| `kafka-topics.sh` | создать/удалить/описать топик, изменить число партиций |
| `kafka-console-producer.sh` | вручную отправить сообщения (в т.ч. с ключом) |
| `kafka-console-consumer.sh` | почитать топик глазами, `--from-beginning` — с начала |
| `kafka-consumer-groups.sh` | список групп, распределение партиций, **lag**, сброс офсетов |
| `kafka-configs.sh` | конфиги топика (`retention.ms`, `cleanup.policy`) |
| `kafka-broker-api-versions.sh` | быстрая проверка «брокер живой» (используем в healthcheck) |

**Lag** (`--describe --group`) — ключевая метрика эксплуатации: `LAG = конец лога − офсет группы`. Растёт → консьюмер не успевает.

---

## Kafka или RabbitMQ

| Нужно | Выбор |
|-------|-------|
| Поток событий, много подписчиков, перечитывание истории, аналитика | **Kafka** |
| Классическая рабочая очередь: задание одному воркеру, приоритеты, TTL на сообщение, сложная маршрутизация | **RabbitMQ** |
| Порядок по сущности при высокой пропускной способности | **Kafka** (ключ → партиция) |
| Гибкая топология exchange/routing key, RPC-паттерн | **RabbitMQ** |

Это не «лучше/хуже», а разные модели: лог с курсорами против очереди с подтверждением и удалением.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Клиент с хоста не подключается (`UnknownHostException: kafka`) | один `advertised.listeners` на внутреннее имя | два листенера: `INTERNAL` (kafka:9092) и `HOST` (localhost:29092) |
| Брокер падает сразу после старта в одну ноду | `offsets.topic.replication.factor=3` по умолчанию в некоторых образах | для dev выставить RF=1 у системных топиков |
| Kafka в K8s падает с Exit Code 1 за пару секунд | переменная `KAFKA_PORT` от Service links ломает конфиг | `enableServiceLinks: false` в поде (см. `~/.claude/CLAUDE.md`) |
| Приложение стартует раньше брокера и падает | `depends_on` без healthcheck | healthcheck + `condition: service_healthy`, в K8s — initContainer `wait-for-kafka` |
| Консьюмер «не видит» старые сообщения | новая группа читает с конца (`auto.offset.reset=latest`) | `--from-beginning` в CLI / `earliest` в конфиге группы |
| События одной сущности обрабатываются не по порядку | `key = null` → разные партиции | ключ = идентификатор сущности |
| Добавили консьюмеров, скорость не выросла | консьюмеров больше, чем партиций | увеличить партиции (уменьшить нельзя) |
| Данные «исчезли» через неделю | `retention.ms` по умолчанию 7 дней | увеличить retention или `cleanup.policy=compact` |
| `docker compose down` — топики пропали | нет тома для данных брокера | именованный volume на `/var/lib/kafka/data` |

---

## Дополнительные источники

- [Apache Kafka — Introduction](https://kafka.apache.org/documentation/#introduction) · [Design](https://kafka.apache.org/documentation/#design).
- [Kafka — Docker (apache/kafka, KRaft)](https://kafka.apache.org/documentation/#docker).
- [Confluent — Kafka Listeners explained](https://www.confluent.io/blog/kafka-listeners-explained/) — про `advertised.listeners`.
- Смежные разделы репозитория: [system-design-course, модуль 15 «Очереди и стриминг»](../../system-design-course/module-15-queues-streaming/theory.md) · [system-analysis-course, модуль 27 «Стили интеграции»](../../system-analysis-course/module-27-integration-styles/theory.md).

## Что дальше

В [модуле 120](../m120_kafka_producer_spring/theory.md) — **producer на Spring**: подключаем `spring-kafka`, настраиваем `KafkaTemplate`, отправляем события Task Tracker с ключом, разбираем сериализацию, `acks`, идемпотентность продюсера и обработку результата отправки.
