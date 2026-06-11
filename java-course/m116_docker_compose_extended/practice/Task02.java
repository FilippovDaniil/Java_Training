package m116_docker_compose_extended.practice;

/**
 * Задача 02 — Модуль 116: добавляем RabbitMQ (с веб-консолью) в Compose
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Добавьте брокер сообщений RabbitMQ с management-консолью и healthcheck:
 *     services:
 *       rabbitmq:
 *         image: rabbitmq:3-management-alpine
 *         ports:
 *           - "5672:5672"        # AMQP (приложение)
 *           - "15672:15672"      # веб-консоль (http://localhost:15672, guest/guest)
 *         healthcheck:
 *           test: ["CMD", "rabbitmq-diagnostics", "ping"]
 *           interval: 10s
 *           timeout: 5s
 *           retries: 5
 *       app:
 *         depends_on:
 *           rabbitmq: { condition: service_healthy }
 *         environment:
 *           SPRING_RABBITMQ_HOST: rabbitmq
 *           SPRING_RABBITMQ_PORT: 5672
 *   Поднимите и откройте веб-консоль http://localhost:15672 (guest/guest). Впишите YAML в COMPOSE.
 *
 * ЦЕЛЬ: подключить RabbitMQ; различать порт AMQP (5672) и порт UI (15672).
 *
 * ВАЖНО: образ -management даёт веб-консоль; RabbitMQ стартует медленнее — увеличенный retries.
 *
 * ПОДСКАЗКА: rabbitmq-diagnostics ping проверяет готовность брокера.
 */
public class Task02 {
    public static void main(String[] args) {
        String compose = """
                # TODO: добавьте сервис rabbitmq
                # services:
                #   rabbitmq:
                #     image: rabbitmq:3-management-alpine
                #     ports:
                #       - "5672:5672"      # AMQP
                #       - "15672:15672"    # UI (guest/guest)
                #     healthcheck:
                #       test: ["CMD", "rabbitmq-diagnostics", "ping"]
                #       interval: 10s
                #       timeout: 5s
                #       retries: 5
                #   app:
                #     depends_on:
                #       rabbitmq: { condition: service_healthy }
                #     environment:
                #       SPRING_RABBITMQ_HOST: rabbitmq
                #       SPRING_RABBITMQ_PORT: 5672
                """;
        System.out.println(compose);
    }
}
