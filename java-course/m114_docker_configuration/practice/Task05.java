package m114_docker_configuration.practice;

/**
 * Задача 05 — Модуль 114: логи в stdout и управление уровнем через env
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Убедитесь, что приложение логирует в stdout (а не в файл), и управляйте уровнем снаружи:
 *   1) Запустите приложение и смотрите логи через Docker:
 *        docker run -d --name app -p 8080:8080 tasktracker:1.0.0
 *        docker logs -f app          # stdout контейнера = логи приложения
 *   2) Поднимите уровень логирования через env (без пересборки образа):
 *        docker run -d --name app -e LOGGING_LEVEL_ROOT=DEBUG tasktracker:1.0.0
 *   3) Точечно для пакета:
 *        -e LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB=DEBUG
 *   4) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: усвоить 12-factor для логов — пишем в stdout, уровень меняем через env.
 *
 * ВАЖНО: НЕ настраивайте запись логов в файл внутри контейнера (файл исчезнет, занимает слой).
 *        Сбором логов из stdout займётся инфраструктура (Docker драйвер / Loki / ELK).
 *
 * ПОДСКАЗКА: logging.level.root → LOGGING_LEVEL_ROOT (relaxed binding).
 */
public class Task05 {
    public static void main(String[] args) {
        String commands = """
                # TODO: логи в stdout + уровень через env
                # docker run -d --name app -p 8080:8080 tasktracker:1.0.0
                # docker logs -f app                                   # логи = stdout
                #
                # docker run -d --name app -e LOGGING_LEVEL_ROOT=DEBUG tasktracker:1.0.0
                # docker run -d --name app -e LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB=DEBUG tasktracker:1.0.0
                """;
        System.out.println(commands);
    }
}
