/**
 * Задача 02 — Модуль 118: финальный docker-compose.yml (app + db, лимиты)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите итоговый Compose-стек с томом, healthcheck, секретом из .env и лимитами ресурсов:
 *     services:
 *       db:
 *         image: postgres:16-alpine
 *         environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
 *         volumes: [ "pgdata:/var/lib/postgresql/data" ]
 *         healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, timeout: 3s, retries: 5 }
 *       app:
 *         build: .
 *         depends_on: { db: { condition: service_healthy } }
 *         environment:
 *           SPRING_PROFILES_ACTIVE: prod
 *           SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *           SPRING_DATASOURCE_USERNAME: app
 *           SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
 *         ports: ["8080:8080"]
 *         deploy:
 *           resources:
 *             limits: { memory: 512M, cpus: "2" }
 *     volumes:
 *       pgdata:
 *   Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: собрать финальный стек: данные в томе, готовность через healthcheck, секрет из .env, лимиты.
 *
 * ВАЖНО: deploy.resources.limits задаёт память/CPU декларативно (модуль 117) прямо в Compose.
 *
 * ПОДСКАЗКА: один файл — весь стек; добавить redis/rabbitmq можно из модуля 116 при необходимости.
 */
public class Task02 {
    public static void main(String[] args) {
        String compose = """
                # TODO: финальный docker-compose.yml
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: ${POSTGRES_PASSWORD} }
                #     volumes: [ "pgdata:/var/lib/postgresql/data" ]
                #     healthcheck: { test: ["CMD-SHELL","pg_isready -U app -d tasktracker"], interval: 5s, timeout: 3s, retries: 5 }
                #   app:
                #     build: .
                #     depends_on: { db: { condition: service_healthy } }
                #     environment:
                #       SPRING_PROFILES_ACTIVE: prod
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_DATASOURCE_USERNAME: app
                #       SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
                #     ports: ["8080:8080"]
                #     deploy:
                #       resources:
                #         limits: { memory: 512M, cpus: "2" }
                # volumes:
                #   pgdata:
                """;
        System.out.println(compose);
    }
}
