package m124_kafka_event_patterns.practice;

/**
 * Задача 03 — Модуль 124: CDC через Debezium (конфигурация коннектора + Outbox Event Router)
 *
 * ФОРМАТ: носитель артефактов (.java + text-блок + println, компилируется bare-javac).
 *   Debezium запускается как сервис Kafka Connect, а не как Java-библиотека приложения, —
 *   поэтому здесь готовим Compose-сервис и JSON-конфигурацию коннектора.
 *
 * ЗАДАНИЕ:
 *   1) Добавьте в docker-compose.yml сервис Kafka Connect с Debezium (рядом с брокером из
 *      модуля 119) и PostgreSQL с logical replication:
 *        db:
 *          image: postgres:16-alpine
 *          command: ["postgres", "-c", "wal_level=logical"]     # обязательно для CDC
 *        connect:
 *          image: debezium/connect:2.7
 *          ports: ["8083:8083"]
 *          environment:
 *            BOOTSTRAP_SERVERS: kafka:9092
 *            GROUP_ID: connect-cluster
 *            CONFIG_STORAGE_TOPIC: connect-configs
 *            OFFSET_STORAGE_TOPIC: connect-offsets
 *            STATUS_STORAGE_TOPIC: connect-status
 *          depends_on: { kafka: { condition: service_healthy }, db: { condition: service_healthy } }
 *   2) Опишите конфигурацию коннектора (POST на Connect REST API) для таблицы outbox
 *      с трансформацией Outbox Event Router:
 *        {
 *          "name": "tasktracker-outbox-connector",
 *          "config": {
 *            "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
 *            "database.hostname": "db", "database.port": "5432",
 *            "database.user": "app", "database.password": "secret",
 *            "database.dbname": "tasktracker",
 *            "topic.prefix": "tasktracker",
 *            "table.include.list": "public.outbox",
 *            "transforms": "outbox",
 *            "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",
 *            "transforms.outbox.route.by.field": "topic",
 *            "transforms.outbox.table.field.event.key": "aggregate_id",
 *            "transforms.outbox.table.field.event.payload": "payload",
 *            "value.converter": "org.apache.kafka.connect.json.JsonConverter",
 *            "value.converter.schemas.enable": "false"
 *          }
 *        }
 *   3) Пропишите команды управления коннектором:
 *        curl -X POST -H "Content-Type: application/json" --data @connector.json \
 *             http://localhost:8083/connectors
 *        curl http://localhost:8083/connectors
 *        curl http://localhost:8083/connectors/tasktracker-outbox-connector/status
 *        curl -X DELETE http://localhost:8083/connectors/tasktracker-outbox-connector
 *   4) Ответьте в COMPARISON: чем этот вариант лучше и хуже собственного релэя (задача 02).
 *
 * ОЖИДАЕМЫЙ ИТОГ: готовая конфигурация, при которой вставка строки в таблицу outbox
 *   автоматически превращается в сообщение в топике task.created — без кода отправки в приложении.
 *
 * ЦЕЛЬ: понять второй способ доставки событий из БД и научиться читать конфигурацию Debezium.
 *
 * ВАЖНО: `wal_level=logical` и слот репликации — требования к БД, а не к приложению. Забытый
 *   слот, от которого никто не читает, останавливает очистку WAL и заполняет диск сервера БД —
 *   классическая авария при экспериментах с CDC.
 *
 * ПОДСКАЗКА: Outbox Event Router берёт имя топика из поля таблицы (route.by.field), ключ —
 *   из aggregate_id, тело — из payload. Именно поэтому таблица outbox из задачи 01 имеет
 *   такие поля: она изначально спроектирована под оба способа доставки.
 */
public class Task03 {
    public static void main(String[] args) {
        String artifacts = """
                # TODO 1: сервисы db (wal_level=logical) и connect (debezium/connect) в docker-compose.yml
                #
                # TODO 2: connector.json — PostgresConnector + transforms.outbox (EventRouter)
                #   "table.include.list": "public.outbox"
                #   "transforms.outbox.route.by.field": "topic"
                #   "transforms.outbox.table.field.event.key": "aggregate_id"
                #   "transforms.outbox.table.field.event.payload": "payload"
                #
                # TODO 3: команды управления
                # curl -X POST -H "Content-Type: application/json" --data @connector.json http://localhost:8083/connectors
                # curl http://localhost:8083/connectors/tasktracker-outbox-connector/status
                #
                # TODO 4 — COMPARISON: свой релэй vs Debezium
                # | Критерий | Релэй в приложении | Debezium/CDC |
                # |----------|--------------------|--------------|
                # | код отправки        | пишем сами | не нужен |
                # | инфраструктура      | нет доп. сервисов | нужен Kafka Connect |
                # | задержка публикации | ... | ... |
                # | нагрузка на БД      | опрос таблицы | чтение WAL |
                # | риски               | ... | забытый слот репликации -> WAL растёт |
                """;
        System.out.println(artifacts);
    }
}
