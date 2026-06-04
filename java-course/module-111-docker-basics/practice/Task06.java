/**
 * Задача 06 — Модуль 111: переменные окружения и inspect (-e, docker inspect)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   1) Запустите PostgreSQL, передав конфигурацию через переменные окружения (-e):
 *        docker run -d --name db -p 5432:5432 \
 *            -e POSTGRES_DB=tasktracker \
 *            -e POSTGRES_USER=app \
 *            -e POSTGRES_PASSWORD=secret \
 *            postgres:16-alpine
 *   2) Проверьте, что стартовал: docker logs db   (ищите "database system is ready to accept connections").
 *   3) Посмотрите метаданные контейнера: docker inspect db
 *        - найдите проброшенные порты и переменные окружения в JSON-выводе.
 *   4) Зайдите в psql внутри: docker exec -it db psql -U app -d tasktracker -c "\\l"
 *   5) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: конфигурировать контейнер через -e (env) и читать его метаданные через inspect.
 *
 * ВАЖНО: образ postgres настраивается ТОЛЬКО переменными окружения POSTGRES_* при первом старте.
 *
 * ПОДСКАЗКА: docker inspect отдаёт JSON; конкретное поле — через --format '{{.State.Status}}'.
 */
public class Task06 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды запуска postgres с env и inspect
                # docker run -d --name db -p 5432:5432 \\
                #     -e POSTGRES_DB=tasktracker \\
                #     -e POSTGRES_USER=app \\
                #     -e POSTGRES_PASSWORD=secret \\
                #     postgres:16-alpine
                # docker logs db
                # docker inspect db
                # docker inspect --format '{{.State.Status}}' db
                # docker exec -it db psql -U app -d tasktracker -c "\\l"
                """;
        System.out.println(commands);
    }
}
