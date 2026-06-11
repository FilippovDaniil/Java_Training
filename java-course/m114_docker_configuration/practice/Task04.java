package m114_docker_configuration.practice;

/**
 * Задача 04 — Модуль 114: именованный том для данных PostgreSQL
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сделайте так, чтобы данные БД переживали удаление контейнера:
 *   1) Запустите postgres с именованным томом:
 *        docker run -d --name db -p 5432:5432 \
 *            -e POSTGRES_DB=tasktracker -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret \
 *            -v pgdata:/var/lib/postgresql/data \
 *            postgres:16-alpine
 *   2) Создайте таблицу/данные (через psql), затем УДАЛИТЕ контейнер: docker rm -f db
 *   3) Запустите db СНОВА с тем же томом (-v pgdata:...) — данные на месте!
 *   4) Посмотреть тома: docker volume ls ; удалить: docker volume rm pgdata
 *   5) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: понять, что writable-слой исчезает с контейнером, а именованный том — нет.
 *
 * СХЕМА:
 *   docker rm db → writable-слой исчез, НО том pgdata остался → данные сохранены при новом запуске.
 *
 * ПОДСКАЗКА: путь /var/lib/postgresql/data — где postgres хранит данные внутри контейнера.
 */
public class Task04 {
    public static void main(String[] args) {
        String commands = """
                # TODO: запуск postgres с именованным томом
                # docker run -d --name db -p 5432:5432 \\
                #     -e POSTGRES_DB=tasktracker -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret \\
                #     -v pgdata:/var/lib/postgresql/data \\
                #     postgres:16-alpine
                #
                # docker rm -f db                      # контейнер удалён
                # docker run -d --name db ... -v pgdata:/var/lib/postgresql/data postgres:16-alpine
                #   → данные на месте (том сохранился)
                #
                # docker volume ls
                # docker volume rm pgdata              # удалить том (данные сотрутся)
                """;
        System.out.println(commands);
    }
}
