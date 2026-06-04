/**
 * Задача 03 — Модуль 115: именованный том для БД в Compose
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Добавьте том, чтобы данные БД переживали docker compose down (без -v):
 *     services:
 *       db:
 *         image: postgres:16-alpine
 *         environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
 *         volumes:
 *           - pgdata:/var/lib/postgresql/data
 *         ports: ["5432:5432"]
 *     volumes:
 *       pgdata:                    # объявление тома на верхнем уровне
 *   Проверьте: up -d → создать данные → down (без -v) → up -d → данные на месте.
 *   А docker compose down -v удалит том вместе с данными.
 *   Впишите YAML в COMPOSE ниже.
 *
 * ЦЕЛЬ: подключить именованный том в Compose и понять разницу down vs down -v.
 *
 * ТАБЛИЦА:
 *   docker compose down      — удалить контейнеры/сеть, ТОМА сохранить (данные целы);
 *   docker compose down -v   — + удалить тома (данные сотрутся).
 *
 * ПОДСКАЗКА: том объявляется и в сервисе (volumes:), и на верхнем уровне (секция volumes:).
 */
public class Task03 {
    public static void main(String[] args) {
        String compose = """
                # TODO: docker-compose.yml с томом pgdata
                # services:
                #   db:
                #     image: postgres:16-alpine
                #     environment: { POSTGRES_DB: tasktracker, POSTGRES_USER: app, POSTGRES_PASSWORD: secret }
                #     volumes:
                #       - pgdata:/var/lib/postgresql/data
                #     ports: ["5432:5432"]
                #
                # volumes:
                #   pgdata:
                #
                # docker compose down      # данные сохранены (том цел)
                # docker compose down -v   # данные удалены (том снесён)
                """;
        System.out.println(compose);
    }
}
