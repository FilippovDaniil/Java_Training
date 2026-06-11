package m111_docker_basics.practice;

/**
 * Задача 01 — Модуль 111: первый контейнер (pull, run, ps)
 *
 * ФОРМАТ: носитель команд. Обычный .java с text-блоком и println (компилируется bare-javac).
 *   Команды выполняйте в терминале; в COMMANDS впишите их как образец.
 *
 * ЗАДАНИЕ:
 *   1) Скачайте образ nginx: docker pull nginx:latest
 *   2) Запустите контейнер на переднем плане: docker run nginx
 *      (затем Ctrl+C; обратите внимание — терминал «занят» логами nginx).
 *   3) В другом терминале посмотрите образы и контейнеры: docker images, docker ps -a
 *   4) Запустите hello-world для проверки установки: docker run hello-world
 *   5) Впишите эти команды в COMMANDS ниже (задача напечатает их как памятку).
 *
 * ЦЕЛЬ: пройти путь образ → контейнер; понять pull/run/ps.
 *
 * ПОДСКАЗКА: docker run сам скачает образ, если его нет локально (pull не обязателен).
 */
public class Task01 {
    public static void main(String[] args) {
        String commands = """
                # Файл-памятка: базовые команды первого запуска
                # TODO: впишите команды

                # docker pull nginx:latest        # скачать образ
                # docker run hello-world           # проверка установки Docker
                # docker run nginx                 # запуск (foreground, Ctrl+C для выхода)
                # docker images                    # локальные образы
                # docker ps -a                     # все контейнеры (вкл. остановленные)
                """;
        System.out.println(commands);
    }
}
