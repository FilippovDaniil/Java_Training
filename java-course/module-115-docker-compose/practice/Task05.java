/**
 * Задача 05 — Модуль 115: переменные через env_file (секреты вне docker-compose.yml)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с compose/.env + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Вынесите пароли из docker-compose.yml в env-файл (чтобы не коммитить секреты):
 *   1) Файл .env (gitignored):
 *        POSTGRES_PASSWORD=secret
 *        SPRING_DATASOURCE_PASSWORD=secret
 *   2) В docker-compose.yml ссылайтесь на переменные и подключите env_file:
 *        services:
 *          db:
 *            image: postgres:16-alpine
 *            environment:
 *              POSTGRES_DB: tasktracker
 *              POSTGRES_USER: app
 *              POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}      # подставится из .env
 *          app:
 *            build: .
 *            env_file: .env
 *            environment:
 *              SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
 *              SPRING_DATASOURCE_USERNAME: app
 *   3) Добавьте .env в .gitignore. Впишите оба файла в CONFIG ниже.
 *
 * ЦЕЛЬ: держать секреты вне VCS — Compose подставляет ${VAR} из .env / env_file.
 *
 * ВАЖНО: ${VAR} в docker-compose.yml резолвится из .env автоматически; env_file передаёт переменные в контейнер.
 *
 * ПОДСКАЗКА: .env в корне читается Compose'ом по умолчанию для подстановки ${...}.
 */
public class Task05 {
    public static void main(String[] args) {
        String config = """
                # .env (gitignored):
                # TODO:
                #   POSTGRES_PASSWORD=secret
                #   SPRING_DATASOURCE_PASSWORD=secret
                #
                # docker-compose.yml:
                #   services:
                #     db:
                #       image: postgres:16-alpine
                #       environment:
                #         POSTGRES_DB: tasktracker
                #         POSTGRES_USER: app
                #         POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
                #     app:
                #       build: .
                #       env_file: .env
                #       environment:
                #         SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tasktracker
                #         SPRING_DATASOURCE_USERNAME: app
                #
                # .gitignore: .env
                """;
        System.out.println(config);
    }
}
