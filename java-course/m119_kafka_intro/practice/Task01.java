package m119_kafka_intro.practice;

/**
 * Задача 01 — Модуль 119: поднять Kafka в KRaft-режиме через Docker Compose
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Напишите docker-compose.yml с одним сервисом kafka (без ZooKeeper, режим KRaft):
 *     services:
 *       kafka:
 *         image: apache/kafka:3.9.0
 *         ports: ["29092:29092"]
 *         environment:
 *           KAFKA_NODE_ID: 1
 *           KAFKA_PROCESS_ROLES: broker,controller
 *           KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka:9093
 *           KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
 *           KAFKA_INTER_BROKER_LISTENER_NAME: INTERNAL
 *           KAFKA_LISTENERS: INTERNAL://:9092,HOST://:29092,CONTROLLER://:9093
 *           KAFKA_ADVERTISED_LISTENERS: INTERNAL://kafka:9092,HOST://localhost:29092
 *           KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,INTERNAL:PLAINTEXT,HOST:PLAINTEXT
 *           KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
 *           KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
 *           KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
 *   Поднимите и проверьте:
 *     docker compose up -d
 *     docker compose logs kafka | tail -20
 *     docker compose exec kafka /opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092
 *
 * ОЖИДАЕМЫЙ ИТОГ: контейнер в состоянии Up, последняя команда печатает версии API брокера
 *   (значит брокер принимает клиентов).
 *
 * ЦЕЛЬ: получить локальный брокер одной командой и понять минимальный набор KRaft-настроек.
 *
 * ВАЖНО: одна нода -> у системных топиков replication factor должен быть 1, иначе брокер
 *   не сможет создать __consumer_offsets и упадёт.
 *
 * ПОДСКАЗКА: два листенера (INTERNAL для контейнеров, HOST для хоста) — см. раздел
 *   «Два листенера» в theory.md; порт 29092 наружу нужен для задач следующих модулей.
 */
public class Task01 {
    public static void main(String[] args) {
        String compose = """
                # TODO: docker-compose.yml с одним брокером Kafka (KRaft)
                # services:
                #   kafka:
                #     image: apache/kafka:3.9.0
                #     ports: ["29092:29092"]
                #     environment:
                #       KAFKA_NODE_ID: 1
                #       KAFKA_PROCESS_ROLES: broker,controller
                #       KAFKA_CONTROLLER_QUORUM_VOTERS: 1@kafka:9093
                #       KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
                #       KAFKA_INTER_BROKER_LISTENER_NAME: INTERNAL
                #       KAFKA_LISTENERS: INTERNAL://:9092,HOST://:29092,CONTROLLER://:9093
                #       KAFKA_ADVERTISED_LISTENERS: INTERNAL://kafka:9092,HOST://localhost:29092
                #       KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: CONTROLLER:PLAINTEXT,INTERNAL:PLAINTEXT,HOST:PLAINTEXT
                #       KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
                #       KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
                #       KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
                #
                # docker compose up -d
                # docker compose exec kafka /opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092
                """;
        System.out.println(compose);
    }
}
