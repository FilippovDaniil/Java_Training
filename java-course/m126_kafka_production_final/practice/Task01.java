package m126_kafka_production_final.practice;

/**
 * Задача 01 — Модуль 126: прод-конфигурация кластера (RF, ISR, тома, листенеры)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Превратите dev-стек из модуля 119 в конфигурацию, приближенную к проду: ТРИ брокера,
 *   RF=3, min.insync.replicas=2, тома для данных, отдельные листенеры и выключенное
 *   автосоздание топиков. Впишите итог в COMPOSE:
 *     - три сервиса kafka-1, kafka-2, kafka-3 (KAFKA_NODE_ID 1/2/3),
 *       KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093;
 *     - KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
 *       KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
 *       KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
 *       KAFKA_MIN_INSYNC_REPLICAS: 2
 *       KAFKA_AUTO_CREATE_TOPICS_ENABLE: "false"
 *       KAFKA_DEFAULT_REPLICATION_FACTOR: 3
 *     - у каждого брокера свой именованный том на /var/lib/kafka/data;
 *     - healthcheck (как в модуле 119) и ограничения ресурсов (deploy.resources.limits).
 *   Затем создайте топики проекта с RF=3 и опишите команды проверки:
 *     kafka-topics.sh --create --topic task.created --partitions 12 --replication-factor 3 \
 *         --config min.insync.replicas=2
 *     kafka-topics.sh --describe --topic task.created         # Isr должен содержать 3 брокера
 *
 * ОЖИДАЕМЫЙ ИТОГ: стек из трёх брокеров, где остановка одного (docker compose stop kafka-2)
 *   НЕ приводит к отказу записи с acks=all — проверьте это экспериментом.
 *
 * ПРОВЕРКА (эксперимент отказоустойчивости):
 *   1) записать сообщения при трёх живых брокерах;
 *   2) docker compose stop kafka-2 -> describe покажет Isr из двух брокеров, запись работает;
 *   3) остановить второй брокер -> Isr = 1 < min.insync.replicas -> продюсер получает
 *      NOT_ENOUGH_REPLICAS. Зафиксируйте это наблюдение — оно объясняет смысл ISR.
 *
 * ЦЕЛЬ: понять связку RF / min.insync.replicas / acks на практике, а не по описанию.
 *
 * ВАЖНО: min.insync.replicas=3 при RF=3 означает «падение любого брокера останавливает запись».
 *   Правильный компромисс — 2: данные подтверждены двумя копиями, кластер терпит потерю одной.
 *
 * ПОДСКАЗКА: три брокера в Compose на одной машине — учебная модель (реальная отказоустойчивость
 *   требует разных узлов/зон). Но поведение ISR и acks воспроизводится честно.
 */
public class Task01 {
    public static void main(String[] args) {
        String compose = """
                # TODO: прод-приближенный кластер из трёх брокеров
                # services:
                #   kafka-1:
                #     image: apache/kafka:3.9.0
                #     environment:
                #       KAFKA_NODE_ID: 1
                #       KAFKA_PROCESS_ROLES: broker,controller
                #       KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093
                #       KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
                #       KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
                #       KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
                #       KAFKA_MIN_INSYNC_REPLICAS: 2
                #       KAFKA_DEFAULT_REPLICATION_FACTOR: 3
                #       KAFKA_AUTO_CREATE_TOPICS_ENABLE: "false"
                #     volumes: [ "kafka1data:/var/lib/kafka/data" ]
                #   kafka-2: ...  kafka-3: ...
                # volumes: { kafka1data:, kafka2data:, kafka3data: }
                #
                # топики:
                # kafka-topics.sh --create --topic task.created --partitions 12 \\
                #     --replication-factor 3 --config min.insync.replicas=2
                #
                # НАБЛЮДЕНИЯ ЭКСПЕРИМЕНТА:
                # 3 брокера живы -> Isr = ...  запись: ...
                # остановлен 1    -> Isr = ...  запись: ...
                # остановлены 2   -> Isr = ...  запись: ...
                """;
        System.out.println(compose);
    }
}
