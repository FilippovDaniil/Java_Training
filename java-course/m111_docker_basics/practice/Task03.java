package m111_docker_basics.practice;

/**
 * Задача 03 — Модуль 111: логи и выполнение команд внутри (logs, exec)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Запустите контейнер web (как в задаче 02), затем:
 *   1) Посмотрите логи: docker logs web
 *   2) Логи в реальном времени: docker logs -f web   (Ctrl+C для выхода — контейнер продолжит работу)
 *   3) Зайдите внутрь интерактивно: docker exec -it web sh
 *        - внутри выполните: ls /usr/share/nginx/html  и  cat /etc/nginx/nginx.conf | head
 *        - выйдите: exit
 *   4) Разовая команда без оболочки: docker exec web ls /
 *   5) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: научиться диагностировать контейнер: смотреть логи и выполнять команды внутри.
 *
 * ВАЖНО: exec работает только на ЗАПУЩЕННОМ контейнере; -it нужен для интерактивной оболочки.
 *
 * ПОДСКАЗКА: docker logs -f — аналог tail -f; exit выходит из exec-оболочки, не останавливая контейнер.
 */
public class Task03 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды диагностики контейнера
                # docker logs web
                # docker logs -f web            # follow (Ctrl+C — выход из просмотра)
                # docker exec -it web sh        # интерактивная оболочка внутри
                #   ls /usr/share/nginx/html
                #   exit
                # docker exec web ls /          # разовая команда без оболочки
                """;
        System.out.println(commands);
    }
}
