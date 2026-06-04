/**
 * Задача 04 — Модуль 116: docker-compose.override.yml для разработки
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок с override-файлом + println, bare-javac).
 *
 * ЗАДАНИЕ:
 *   Вынесите dev-настройки в override-файл, который Compose подхватит автоматически:
 *   1) Создайте docker-compose.override.yml (рядом с docker-compose.yml):
 *        services:
 *          app:
 *            build: .                       # в dev собираем из исходников
 *            environment:
 *              SPRING_PROFILES_ACTIVE: dev
 *              LOGGING_LEVEL_ROOT: DEBUG
 *            ports:
 *              - "5005:5005"                # порт удалённой отладки JVM
 *   2) docker compose up -d  → база + override смержатся автоматически (только для dev).
 *   3) Проверьте итоговую конфигурацию: docker compose config
 *   4) Впишите override в COMPOSE ниже.
 *
 * ЦЕЛЬ: отделить dev-настройки от базового файла через автоматический override-мерж.
 *
 * ВАЖНО: docker-compose.override.yml применяется АВТОМАТИЧЕСКИ при up; на проде его НЕ используют
 *        (там явные -f docker-compose.yml -f docker-compose.prod.yml).
 *
 * ПОДСКАЗКА: docker compose config показывает результат мержа базового и override файлов.
 */
public class Task04 {
    public static void main(String[] args) {
        String compose = """
                # Файл: docker-compose.override.yml (автоматический мерж в dev)
                # TODO:
                # services:
                #   app:
                #     build: .
                #     environment:
                #       SPRING_PROFILES_ACTIVE: dev
                #       LOGGING_LEVEL_ROOT: DEBUG
                #     ports:
                #       - "5005:5005"      # remote debug
                #
                # docker compose up -d        # база + override
                # docker compose config       # посмотреть итоговую конфигурацию
                """;
        System.out.println(compose);
    }
}
