package m119_kafka_intro.practice;

/**
 * Задача 07 — Модуль 119: МИНИ-ПРОЕКТ «Dev-стек Task Tracker: app + db + kafka + kafka-ui»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЦЕЛЬ: собрать окружение, в котором будут выполняться все задачи модулей 120–126: приложение,
 *       PostgreSQL, брокер Kafka и веб-интерфейс к нему — одной командой, с healthcheck
 *       и правильным порядком старта. Капстоун модуля.
 *
 * ЗАДАНИЕ — соберите docker-compose.yml (расширяем стек из модуля 115) и впишите в COMPOSE:
 *   services:
 *     db:
 *       image: postgres:16-alpine
 *       environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
 *       volumes: [ "pgdata:/var/lib/postgresql/data" ]
 *       healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, retries: 5 }
 *     kafka:
 *       image: apache/kafka:3.9.0
 *       ports: ["29092:29092"]
 *       environment: <KRaft-настройки из задачи 01>
 *       volumes: [ "kafkadata:/var/lib/kafka/data" ]        # данные переживают down (без -v)
 *       healthcheck:
 *         test: ["CMD-SHELL","/opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092 || exit 1"]
 *         interval: 10s
 *         timeout: 5s
 *         retries: 10
 *     kafka-ui:
 *       image: kafbat/kafka-ui:latest
 *       ports: ["8081:8080"]
 *       environment: { KAFKA_CLUSTERS_0_NAME: local, KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092 }
 *       depends_on: { kafka: { condition: service_healthy } }
 *     app:
 *       build: .
 *       depends_on:
 *         db:    { condition: service_healthy }
 *         kafka: { condition: service_healthy }
 *       environment:
 *         SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *         SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:9092           # имя сервиса, не localhost!
 *       ports: ["8080:8080"]
 *   volumes: { pgdata:, kafkadata: }
 *
 *   Затем создайте топики сквозного проекта и проверьте их в UI (http://localhost:8081):
 *     task.created  (6 партиций)
 *     task.status.changed  (6 партиций)
 *
 * ОЖИДАЕМЫЙ ИТОГ: docker compose up -d поднимает весь стек; app стартует только после
 *   готовности БД и брокера; в kafka-ui видны оба топика, их партиции и сообщения.
 *
 * ПРОВЕРКА:
 *   docker compose ps                      # все сервисы healthy/Up
 *   docker compose exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
 *   curl http://localhost:8081             # UI отвечает
 *
 * ПОДСКАЗКА: соедините задачу 01 (брокер), модуль 115 (db + depends_on/healthcheck) и модуль 116
 *   (мульти-depends_on). Секрет POSTGRES_PASSWORD — в .env (gitignored), как в модуле 118.
 */
public class Task07 {
    public static void main(String[] args) {
        String compose = """
                # TODO: полный dev-стек app + db + kafka + kafka-ui
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
                #     volumes: [ "pgdata:/var/lib/postgresql/data" ]
                #     healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, retries: 5 }
                #   kafka:
                #     image: apache/kafka:3.9.0
                #     ports: ["29092:29092"]
                #     environment:
                #       # KRaft-настройки из задачи 01 (два листенера: INTERNAL и HOST)
                #     volumes: [ "kafkadata:/var/lib/kafka/data" ]
                #     healthcheck:
                #       test: ["CMD-SHELL","/opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092 || exit 1"]
                #       interval: 10s
                #       timeout: 5s
                #       retries: 10
                #   kafka-ui:
                #     image: kafbat/kafka-ui:latest
                #     ports: ["8081:8080"]
                #     environment: { KAFKA_CLUSTERS_0_NAME: local, KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092 }
                #     depends_on: { kafka: { condition: service_healthy } }
                #   app:
                #     build: .
                #     depends_on:
                #       db:    { condition: service_healthy }
                #       kafka: { condition: service_healthy }
                #     environment:
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
                #     ports: ["8080:8080"]
                # volumes:
                #   pgdata:
                #   kafkadata:
                #
                # топики: task.created (6 партиций), task.status.changed (6 партиций)
                """;
        System.out.println(compose);
    }
}
