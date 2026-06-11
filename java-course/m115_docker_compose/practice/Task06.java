package m115_docker_compose.practice;

/**
 * Задача 06 — Модуль 115: рабочий цикл Compose (up/logs/ps/down) и troubleshooting
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Освойте повседневные команды управления стеком:
 *   1) Поднять с пересборкой: docker compose up --build -d
 *   2) Статус и логи:        docker compose ps ; docker compose logs -f app
 *   3) Перезапустить сервис: docker compose restart app
 *   4) Выполнить команду в сервисе: docker compose exec db psql -U app -d tasktracker -c "\\dt"
 *   5) Остановить (данные целы): docker compose down
 *   6) Полная очистка (с томами): docker compose down -v
 *   Диагностика частых проблем (см. таблицу в theory.md): порт занят, app не видит db, гонка старта.
 *   Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: уверенно управлять Compose-стеком и диагностировать типовые сбои.
 *
 * ПОДСКАЗКА: docker compose logs -f app — первый шаг диагностики, если app не стартует.
 */
public class Task06 {
    public static void main(String[] args) {
        String commands = """
                # TODO: рабочий цикл Compose
                # docker compose up --build -d
                # docker compose ps
                # docker compose logs -f app
                # docker compose restart app
                # docker compose exec db psql -U app -d tasktracker -c "\\dt"
                # docker compose down        # данные целы
                # docker compose down -v     # + удалить тома
                #
                # Диагностика:
                #   - "port is already allocated" → сменить host-порт ("8081:8080");
                #   - app не видит db → адрес = имя сервиса (db:5432), не localhost;
                #   - app падает на старте → depends_on: condition: service_healthy.
                """;
        System.out.println(commands);
    }
}
