/**
 * Задача 07 — Модуль 114: МИНИ-ПРОЕКТ «12-factor запуск Task Tracker в контейнере»
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЦЕЛЬ: собрать корректный 12-factor запуск: один образ, конфигурация через env,
 *       данные в томе, файлы в bind-mount, логи в stdout.
 *
 * ЗАДАНИЕ — оформите в SOLUTION ниже полную команду запуска и сопутствующие файлы:
 *
 *   1) БД с томом:
 *        docker run -d --name db -p 5432:5432 \
 *            -e POSTGRES_DB=tasktracker -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret \
 *            -v pgdata:/var/lib/postgresql/data postgres:16-alpine
 *
 *   2) Приложение (один образ, вся конфигурация снаружи):
 *        docker run -d --name app -p 8080:8080 \
 *            --env-file .env.prod \
 *            -v "$(pwd)/uploads:/app/uploads" \
 *            tasktracker:1.0.0
 *
 *   3) .env.prod (gitignored):
 *        SPRING_PROFILES_ACTIVE=prod
 *        SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/tasktracker
 *        SPRING_DATASOURCE_USERNAME=app
 *        SPRING_DATASOURCE_PASSWORD=secret
 *        LOGGING_LEVEL_ROOT=INFO
 *        APP_UPLOAD_DIR=/app/uploads
 *
 *   4) Логи: docker logs -f app   (stdout, не файл)
 *
 * ОЖИДАЕМЫЙ ИТОГ: образ неизменен, всё конфигурируется снаружи; данные/файлы переживают контейнер.
 *   Ручное связывание контейнеров (host.docker.internal) — мотив перейти к Compose (модуль 115).
 *
 * ПОДСКАЗКА: соедините env (задачи 01–03), том БД (04), логи (05), bind-mount файлов (06).
 */
public class Task07 {
    public static void main(String[] args) {
        String solution = """
                # TODO: 12-factor запуск Task Tracker

                # 1) БД с томом
                # docker run -d --name db -p 5432:5432 \\
                #     -e POSTGRES_DB=tasktracker -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret \\
                #     -v pgdata:/var/lib/postgresql/data postgres:16-alpine

                # 2) Приложение (конфиг снаружи)
                # docker run -d --name app -p 8080:8080 \\
                #     --env-file .env.prod \\
                #     -v "$(pwd)/uploads:/app/uploads" \\
                #     tasktracker:1.0.0

                # 3) .env.prod (gitignored):
                #   SPRING_PROFILES_ACTIVE=prod
                #   SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/tasktracker
                #   SPRING_DATASOURCE_USERNAME=app
                #   SPRING_DATASOURCE_PASSWORD=secret
                #   LOGGING_LEVEL_ROOT=INFO
                #   APP_UPLOAD_DIR=/app/uploads

                # 4) docker logs -f app   (stdout)
                """;
        System.out.println(solution);
    }
}
