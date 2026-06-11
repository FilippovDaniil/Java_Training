package m116_docker_compose_extended.practice;

/**
 * Задача 01 — Модуль 116: добавляем Redis в Compose-стек
 *
 * ФОРМАТ: носитель артефакта. .java с text-блоком (docker-compose.yml) и println —
 *   компилируется bare-javac. Файл создайте в корне проекта.
 *
 * ЗАДАНИЕ:
 *   К стеку app+db (модуль 115) добавьте сервис redis с healthcheck:
 *     services:
 *       redis:
 *         image: redis:7-alpine
 *         ports: ["6379:6379"]
 *         healthcheck:
 *           test: ["CMD", "redis-cli", "ping"]
 *           interval: 5s
 *           timeout: 3s
 *           retries: 5
 *       app:
 *         # ...
 *         depends_on:
 *           redis: { condition: service_healthy }
 *         environment:
 *           SPRING_DATA_REDIS_HOST: redis        # имя сервиса, НЕ localhost
 *           SPRING_DATA_REDIS_PORT: 6379
 *   Поднимите: docker compose up -d ; проверьте: docker compose exec redis redis-cli ping → PONG.
 *   Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: добавить инфраструктурный сервис (Redis) с healthcheck и адресовать его по имени.
 *
 * ВАЖНО: в Spring Boot 3.x свойства Redis — spring.data.redis.* (env SPRING_DATA_REDIS_HOST).
 *
 * ПОДСКАЗКА: redis-cli ping отвечает PONG, когда Redis готов принимать команды.
 */
public class Task01 {
    public static void main(String[] args) {
        String compose = """
                # TODO: добавьте сервис redis в docker-compose.yml
                # services:
                #   redis:
                #     image: redis:7-alpine
                #     ports: ["6379:6379"]
                #     healthcheck:
                #       test: ["CMD", "redis-cli", "ping"]
                #       interval: 5s
                #       timeout: 3s
                #       retries: 5
                #   app:
                #     depends_on:
                #       redis: { condition: service_healthy }
                #     environment:
                #       SPRING_DATA_REDIS_HOST: redis
                #       SPRING_DATA_REDIS_PORT: 6379
                """;
        System.out.println(compose);
    }
}
