package m116_docker_compose_extended.practice;

/**
 * Задача 06 — Модуль 116: dev-workflow и troubleshooting расширенного стека
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Освойте команды повседневной работы с многосервисным стеком и диагностику:
 *   1) Пересобрать и поднять: docker compose up -d --build
 *   2) Перезапустить ТОЛЬКО app, не трогая зависимости: docker compose up -d --no-deps app
 *   3) Логи нескольких сервисов: docker compose logs -f app db redis
 *   4) Зайти в сервисы: docker compose exec redis redis-cli ; docker compose exec db psql -U app -d tasktracker
 *   5) Итоговая конфигурация (мерж файлов): docker compose config
 *   6) Снести всё с томами: docker compose down -v
 *   Диагностика (см. theory.md): app не видит redis/rabbitmq → имя сервиса; гонка старта → healthcheck;
 *   RabbitMQ «красный» healthcheck → увеличить retries/timeout.
 *   Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: уверенно управлять и диагностировать стек app+db+redis+rabbitmq.
 *
 * ПОДСКАЗКА: --no-deps app перезапускает приложение без перезапуска БД/брокера.
 */
public class Task06 {
    public static void main(String[] args) {
        String commands = """
                # TODO: команды dev-workflow и диагностики
                # docker compose up -d --build
                # docker compose up -d --no-deps app        # только app
                # docker compose logs -f app db redis
                # docker compose exec redis redis-cli
                # docker compose exec db psql -U app -d tasktracker
                # docker compose config                     # итоговый мерж
                # docker compose down -v
                #
                # Диагностика:
                #   app не видит redis/rabbitmq → хост = имя сервиса;
                #   app падает на старте       → depends_on condition: service_healthy;
                #   rabbitmq healthcheck красный → увеличить retries/timeout.
                """;
        System.out.println(commands);
    }
}
