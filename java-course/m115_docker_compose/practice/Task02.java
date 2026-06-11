package m115_docker_compose.practice;

/**
 * Задача 02 — Модуль 115: добавляем сервис приложения (build + depends_on)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Добавьте к БД сервис приложения, собираемый из Dockerfile:
 *     services:
 *       db:
 *         image: postgres:16-alpine
 *         environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
 *         ports: ["5432:5432"]
 *       app:
 *         build: .                  # собрать из Dockerfile в текущей папке
 *         depends_on:
 *           - db                    # db стартует раньше app
 *         environment:
 *           SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker   # db = имя сервиса!
 *           SPRING_DATASOURCE_USERNAME: app
 *           SPRING_DATASOURCE_PASSWORD: secret
 *         ports: ["8080:8080"]
 *   Поднимите: docker compose up --build -d ; проверьте curl http://localhost:8080/actuator/health.
 *   Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: связать app и db в одном стеке; адресовать БД ПО ИМЕНИ СЕРВИСА (db), не localhost.
 *
 * ВАЖНО: внутри Compose-сети localhost у app = сам app; БД доступна как хост "db".
 *
 * ПОДСКАЗКА: build: . собирает образ из Dockerfile; --build пересобирает при up.
 */
public class Task02 {
    public static void main(String[] args) {
        String compose = """
                # TODO: docker-compose.yml с db + app
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
                #     ports: ["5432:5432"]
                #   app:
                #     build: .
                #     depends_on: [db]
                #     environment:
                #       SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #       SPRING_DATASOURCE_USERNAME: app
                #       SPRING_DATASOURCE_PASSWORD: secret
                #     ports: ["8080:8080"]
                #
                # docker compose up --build -d
                # curl http://localhost:8080/actuator/health
                """;
        System.out.println(compose);
    }
}
