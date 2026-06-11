package m115_docker_compose.practice;

/**
 * Задача 07 — Модуль 115: МИНИ-ПРОЕКТ «Полный docker-compose.yml для Task Tracker»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЦЕЛЬ: собрать боевой Compose-стек Task Tracker: приложение + PostgreSQL, с томом данных,
 *       healthcheck-ожиданием готовности БД, конфигом снаружи и корректной сетью. Капстоун.
 *
 * ЗАДАНИЕ — напишите docker-compose.yml и поместите его в COMPOSE ниже:
 *   services:
 *     db:
 *       image: postgres:16-alpine
 *       environment:
 *         POSTGRES_DB: tasktracker
 *         POSTGRES_USER: app
 *         POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
 *       volumes:
 *         - pgdata:/var/lib/postgresql/data
 *       healthcheck:
 *         test: ["CMD-SHELL", "pg_isready -U app -d tasktracker"]
 *         interval: 5s
 *         timeout: 3s
 *         retries: 5
 *     app:
 *       build: .
 *       depends_on:
 *         db:
 *           condition: service_healthy
 *       environment:
 *         SPRING_PROFILES_ACTIVE: prod
 *         SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *         SPRING_DATASOURCE_USERNAME: app
 *         SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
 *       ports:
 *         - "8080:8080"
 *   volumes:
 *     pgdata:
 *
 *   + .env (gitignored): POSTGRES_PASSWORD=secret
 *
 *   Запуск:  docker compose up --build -d
 *   Проверка: curl http://localhost:8080/actuator/health
 *   Остановка: docker compose down   (данные целы)
 *
 * ОЖИДАЕМЫЙ ИТОГ: весь стек Task Tracker поднимается ОДНОЙ командой; БД ждёт готовности,
 *   данные в томе, секрет в .env, app адресует БД по имени сервиса. База для модулей 116–118.
 *
 * ПОДСКАЗКА: соедините задачи 02 (app+db), 03 (том), 04 (healthcheck), 05 (.env-секрет).
 */
public class Task07 {
    public static void main(String[] args) {
        String compose = """
                # TODO: полный docker-compose.yml Task Tracker
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment:
                #       POSTGRES_DB: tasktracker
                #       POSTGRES_USER: app
                #       POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
                #     volumes:
                #       - pgdata:/var/lib/postgresql/data
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
                #     environment:
                #       SPRING_PROFILES_ACTIVE: prod
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_DATASOURCE_USERNAME: app
                #       SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
                #     ports:
                #       - "8080:8080"
                # volumes:
                #   pgdata:
                #
                # .env (gitignored): POSTGRES_PASSWORD=secret
                #
                # docker compose up --build -d
                # curl http://localhost:8080/actuator/health
                # docker compose down
                """;
        System.out.println(compose);
    }
}
