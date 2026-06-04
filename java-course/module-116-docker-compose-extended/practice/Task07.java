/**
 * Задача 07 — Модуль 116: МИНИ-ПРОЕКТ «Полный стек Task Tracker: app+db+redis+rabbitmq»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЦЕЛЬ: собрать боевой многосервисный стек в одном docker-compose.yml: приложение и три
 *       инфраструктурных сервиса, каждый с healthcheck, app ждёт готовности всех. Капстоун модуля.
 *
 * ЗАДАНИЕ — напишите docker-compose.yml и поместите его в COMPOSE ниже:
 *   services:
 *     db:
 *       image: postgres:16-alpine
 *       environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
 *       volumes: [ "pgdata:/var/lib/postgresql/data" ]
 *       healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, timeout: 3s, retries: 5 }
 *     redis:
 *       image: redis:7-alpine
 *       healthcheck: { test: ["CMD","redis-cli","ping"], interval: 5s, timeout: 3s, retries: 5 }
 *     rabbitmq:
 *       image: rabbitmq:3-management-alpine
 *       ports: ["15672:15672"]
 *       healthcheck: { test: ["CMD","rabbitmq-diagnostics","ping"], interval: 10s, timeout: 5s, retries: 5 }
 *     app:
 *       build: .
 *       depends_on:
 *         db:       { condition: service_healthy }
 *         redis:    { condition: service_healthy }
 *         rabbitmq: { condition: service_healthy }
 *       environment:
 *         SPRING_PROFILES_ACTIVE: prod
 *         SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *         SPRING_DATASOURCE_USERNAME: app
 *         SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
 *         SPRING_DATA_REDIS_HOST: redis
 *         SPRING_RABBITMQ_HOST: rabbitmq
 *       ports: ["8080:8080"]
 *   volumes:
 *     pgdata:
 *
 *   + .env (gitignored): POSTGRES_PASSWORD=secret
 *   Запуск: docker compose up --build -d
 *
 * ОЖИДАЕМЫЙ ИТОГ: весь продакшен-подобный стек поднимается одной командой; app стартует
 *   после готовности БД, кэша и брокера. Основа для финального шаблона (модуль 118).
 *
 * ПОДСКАЗКА: соедините задачи 01 (redis), 02 (rabbitmq), 03 (мульти-depends_on) + Compose из модуля 115.
 */
public class Task07 {
    public static void main(String[] args) {
        String compose = """
                # TODO: полный docker-compose.yml (app + db + redis + rabbitmq)
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
                #     volumes: [ "pgdata:/var/lib/postgresql/data" ]
                #     healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, timeout: 3s, retries: 5 }
                #   redis:
                #     image: redis:7-alpine
                #     healthcheck: { test: ["CMD","redis-cli","ping"], interval: 5s, timeout: 3s, retries: 5 }
                #   rabbitmq:
                #     image: rabbitmq:3-management-alpine
                #     ports: ["15672:15672"]
                #     healthcheck: { test: ["CMD","rabbitmq-diagnostics","ping"], interval: 10s, timeout: 5s, retries: 5 }
                #   app:
                #     build: .
                #     depends_on:
                #       db:       { condition: service_healthy }
                #       redis:    { condition: service_healthy }
                #       rabbitmq: { condition: service_healthy }
                #     environment:
                #       SPRING_PROFILES_ACTIVE: prod
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_DATASOURCE_USERNAME: app
                #       SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
                #       SPRING_DATA_REDIS_HOST: redis
                #       SPRING_RABBITMQ_HOST: rabbitmq
                #     ports: ["8080:8080"]
                # volumes:
                #   pgdata:
                #
                # .env (gitignored): POSTGRES_PASSWORD=secret
                # docker compose up --build -d
                """;
        System.out.println(compose);
    }
}
