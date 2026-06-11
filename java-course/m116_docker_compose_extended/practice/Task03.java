package m116_docker_compose_extended.practice;

/**
 * Задача 03 — Модуль 116: несколько зависимостей сразу (depends_on на db+redis+rabbitmq)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сделайте так, чтобы app поднимался только когда ВСЕ инфраструктурные сервисы готовы:
 *     services:
 *       app:
 *         build: .
 *         depends_on:
 *           db:        { condition: service_healthy }
 *           redis:     { condition: service_healthy }
 *           rabbitmq:  { condition: service_healthy }
 *         environment:
 *           SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *           SPRING_DATA_REDIS_HOST: redis
 *           SPRING_RABBITMQ_HOST: rabbitmq
 *         ports: ["8080:8080"]
 *   (db/redis/rabbitmq — с healthcheck из задач 01–02 и модуля 115).
 *   Впишите фрагмент app в COMPOSE ниже.
 *
 * ЦЕЛЬ: координировать старт app со всеми зависимостями через service_healthy.
 *
 * ВАЖНО: app поднимется ТОЛЬКО когда ВСЕ перечисленные сервисы прошли healthcheck.
 *
 * ПОДСКАЗКА: каждая зависимость — отдельная строка с condition: service_healthy.
 */
public class Task03 {
    public static void main(String[] args) {
        String compose = """
                # TODO: app с несколькими healthy-зависимостями
                # services:
                #   app:
                #     build: .
                #     depends_on:
                #       db:        { condition: service_healthy }
                #       redis:     { condition: service_healthy }
                #       rabbitmq:  { condition: service_healthy }
                #     environment:
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_DATA_REDIS_HOST: redis
                #       SPRING_RABBITMQ_HOST: rabbitmq
                #     ports: ["8080:8080"]
                """;
        System.out.println(compose);
    }
}
