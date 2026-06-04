/**
 * Задача 02 — Модуль 111: фоновый запуск и проброс портов (-d, -p, --name)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   1) Запустите nginx в фоне с именем и пробросом порта:
 *        docker run -d --name web -p 8080:80 nginx
 *   2) Проверьте, что контейнер работает: docker ps
 *   3) Откройте в браузере http://localhost:8080 — должна открыться стартовая страница nginx.
 *   4) Остановите и удалите: docker stop web ; docker rm web
 *   5) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: освоить detached-режим (-d), проброс порта (-p host:container), имя (--name).
 *
 * СХЕМА ПОРТА:
 *   -p 8080:80  →  localhost:8080 (хост)  →  контейнер:80 (nginx)
 *
 * ПОДСКАЗКА: без -p сервис внутри контейнера недоступен снаружи.
 */
public class Task02 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды фонового запуска с портом
                # docker run -d --name web -p 8080:80 nginx
                # docker ps
                # открыть http://localhost:8080
                # docker stop web
                # docker rm web
                """;
        System.out.println(commands);
    }
}
