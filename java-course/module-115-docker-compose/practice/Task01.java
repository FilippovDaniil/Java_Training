/**
 * Задача 01 — Модуль 115: первый docker-compose.yml (только PostgreSQL)
 *
 * ФОРМАТ: носитель артефакта. .java с text-блоком (несёт docker-compose.yml) и println —
 *   компилируется bare-javac. Сам файл создайте в корне проекта.
 *
 * ЗАДАНИЕ:
 *   1) Создайте docker-compose.yml с одним сервисом БД:
 *        services:
 *          db:
 *            image: postgres:16-alpine
 *            environment:
 *              POSTGRES_DB: tasktracker
 *              POSTGRES_USER: app
 *              POSTGRES_PASSWORD: secret
 *            ports:
 *              - "5432:5432"
 *   2) Поднимите: docker compose up -d
 *   3) Проверьте: docker compose ps ; docker compose logs db
 *   4) Остановите: docker compose down
 *   5) Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: освоить базовую структуру Compose (services → сервис → image/environment/ports).
 *
 * ВАЖНО: отступы в YAML значимы (2 пробела); строки портов берут в кавычки ("5432:5432").
 *
 * ПОДСКАЗКА: docker compose (с пробелом) — современный синтаксис; docker-compose (дефис) — старый.
 */
public class Task01 {
    public static void main(String[] args) {
        String compose = """
                # Файл: docker-compose.yml
                # TODO: опишите сервис db
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment:
                #       POSTGRES_DB: tasktracker
                #       POSTGRES_USER: app
                #       POSTGRES_PASSWORD: secret
                #     ports:
                #       - "5432:5432"
                #
                # docker compose up -d
                # docker compose ps
                # docker compose down
                """;
        System.out.println(compose);
    }
}
