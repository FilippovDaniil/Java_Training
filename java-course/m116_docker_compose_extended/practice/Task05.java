package m116_docker_compose_extended.practice;

/**
 * Задача 05 — Модуль 116: профили Compose (необязательные сервисы)
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с docker-compose.yml + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сделайте RabbitMQ опциональным — он поднимается только при явном профиле:
 *     services:
 *       rabbitmq:
 *         image: rabbitmq:3-management-alpine
 *         profiles: ["messaging"]        # поднимется только если профиль активен
 *         ports: ["5672:5672", "15672:15672"]
 *   Запуск:
 *     docker compose up -d                       # БЕЗ rabbitmq
 *     docker compose --profile messaging up -d   # С rabbitmq
 *   Впишите фрагмент в COMPOSE ниже.
 *
 * ЦЕЛЬ: включать тяжёлые/необязательные сервисы выборочно через profiles.
 *
 * ВАЖНО: сервис с profiles НЕ стартует по умолчанию — только при --profile <имя> (или COMPOSE_PROFILES).
 *
 * ПОДСКАЗКА: удобно, когда часть команды работает без брокера, а часть — с ним.
 */
public class Task05 {
    public static void main(String[] args) {
        String compose = """
                # TODO: сделайте rabbitmq опциональным через profiles
                # services:
                #   rabbitmq:
                #     image: rabbitmq:3-management-alpine
                #     profiles: ["messaging"]
                #     ports: ["5672:5672", "15672:15672"]
                #
                # docker compose up -d                       # без rabbitmq
                # docker compose --profile messaging up -d   # с rabbitmq
                """;
        System.out.println(compose);
    }
}
