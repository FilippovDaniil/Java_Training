/**
 * Задача 04 — Модуль 115: порядок запуска — healthcheck + service_healthy
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Устраните гонку «app стартовал раньше готовности БД». Добавьте healthcheck к db и условие к app:
 *     services:
 *       db:
 *         image: postgres:16-alpine
 *         environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
 *         healthcheck:
 *           test: ["CMD-SHELL", "pg_isready -U app -d tasktracker"]
 *           interval: 5s
 *           timeout: 3s
 *           retries: 5
 *       app:
 *         build: .
 *         depends_on:
 *           db:
 *             condition: service_healthy        # ← ждать готовности БД
 *         ports: ["8080:8080"]
 *   Поднимите: docker compose up -d ; app дождётся прохождения healthcheck БД.
 *   Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: гарантировать, что app стартует ПОСЛЕ готовности БД (а не просто после её запуска).
 *
 * ВАЖНО: depends_on без condition даёт лишь порядок СТАРТА; готовность — только через healthcheck.
 *
 * ПОДСКАЗКА: pg_isready — утилита проверки готовности PostgreSQL принимать соединения.
 */
public class Task04 {
    public static void main(String[] args) {
        String compose = """
                # TODO: docker-compose.yml с healthcheck и service_healthy
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
                #     healthcheck:
                #       test: ["CMD-SHELL", "pg_isready -U app -d tasktracker"]
                #       interval: 5s
                #       timeout: 3s
                #       retries: 5
                #   app:
                #     build: .
                #     depends_on:
                #       db:
                #         condition: service_healthy
                #     ports: ["8080:8080"]
                """;
        System.out.println(compose);
    }
}
