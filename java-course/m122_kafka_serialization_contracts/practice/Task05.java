package m122_kafka_serialization_contracts.practice;

/**
 * Задача 05 — Модуль 122: Avro + Schema Registry (схема, Compose, конфигурация клиентов)
 *
 * ФОРМАТ: носитель артефактов (.java + text-блоки + println, компилируется bare-javac).
 *   Confluent-библиотеки лежат вне Maven Central, поэтому здесь готовим артефакты и конфигурацию,
 *   а не Java-код: это ровно то, что делают в реальном проекте перед подключением Avro.
 *
 * ЗАДАНИЕ:
 *   1) Напишите Avro-схему события в ARTIFACT (файл src/main/avro/TaskCreated.avsc):
 *        {
 *          "type": "record",
 *          "name": "TaskCreated",
 *          "namespace": "com.example.tasktracker.events",
 *          "fields": [
 *            {"name": "taskId",   "type": "long"},
 *            {"name": "title",    "type": "string"},
 *            {"name": "authorId", "type": "long"},
 *            {"name": "priority", "type": "string", "default": "NORMAL"},
 *            {"name": "deadline", "type": ["null", {"type":"long","logicalType":"timestamp-millis"}],
 *                                 "default": null}
 *          ]
 *        }
 *   2) Добавьте в docker-compose.yml сервис Schema Registry (рядом с брокером из модуля 119):
 *        schema-registry:
 *          image: confluentinc/cp-schema-registry:7.7.1
 *          ports: ["8085:8081"]
 *          environment:
 *            SCHEMA_REGISTRY_HOST_NAME: schema-registry
 *            SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS: PLAINTEXT://kafka:9092
 *          depends_on: { kafka: { condition: service_healthy } }
 *   3) Опишите конфигурацию клиентов (application.yml):
 *        producer.value-serializer:   io.confluent.kafka.serializers.KafkaAvroSerializer
 *        consumer.value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
 *        properties.schema.registry.url: http://localhost:8085
 *        properties.auto.register.schemas: false      # схемы регистрирует CI
 *        properties.specific.avro.reader: true        # читать в сгенерированный класс
 *   4) Пропишите команды проверки Registry (REST API):
 *        curl http://localhost:8085/subjects
 *        curl http://localhost:8085/subjects/task.created-value/versions
 *        curl http://localhost:8085/config
 *        # установить правило совместимости для subject:
 *        curl -X PUT -H "Content-Type: application/json" \
 *             --data '{"compatibility":"BACKWARD"}' \
 *             http://localhost:8085/config/task.created-value
 *
 * ОЖИДАЕМЫЙ ИТОГ: готовый набор артефактов, которого достаточно, чтобы перевести топик с JSON
 *   на Avro: схема, сервис Registry, конфигурация клиентов, команды контроля совместимости.
 *
 * ЦЕЛЬ: понять, из чего состоит переход на строгие схемы и где именно проверяется совместимость.
 *
 * ВАЖНО: auto.register.schemas=true на проде опасно — приложение при релизе само зарегистрирует
 *   новую версию схемы, и ломающее изменение попадёт в Registry вместе с деплоем. Схемы должны
 *   регистрироваться отдельным шагом CI с проверкой совместимости ДО выпуска сервиса.
 *
 * ПОДСКАЗКА: порт 8085 наружу выбран, чтобы не столкнуться с kafka-ui (8081) из модуля 119.
 */
public class Task05 {
    public static void main(String[] args) {
        String artifact = """
                # TODO 1: src/main/avro/TaskCreated.avsc
                # {
                #   "type": "record",
                #   "name": "TaskCreated",
                #   "namespace": "com.example.tasktracker.events",
                #   "fields": [ ... taskId, title, authorId, priority(default), deadline(nullable) ]
                # }
                #
                # TODO 2: сервис schema-registry в docker-compose.yml
                # schema-registry:
                #   image: confluentinc/cp-schema-registry:7.7.1
                #   ports: ["8085:8081"]
                #   environment:
                #     SCHEMA_REGISTRY_HOST_NAME: schema-registry
                #     SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS: PLAINTEXT://kafka:9092
                #   depends_on: { kafka: { condition: service_healthy } }
                #
                # TODO 3: application.yml — KafkaAvroSerializer/Deserializer + schema.registry.url
                #
                # TODO 4: команды проверки
                # curl http://localhost:8085/subjects
                # curl http://localhost:8085/subjects/task.created-value/versions
                # curl -X PUT -H "Content-Type: application/json" --data '{"compatibility":"BACKWARD"}' \\
                #      http://localhost:8085/config/task.created-value
                """;
        System.out.println(artifact);
    }
}
