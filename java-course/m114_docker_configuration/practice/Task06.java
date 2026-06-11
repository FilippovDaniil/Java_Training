package m114_docker_configuration.practice;

/**
 * Задача 06 — Модуль 114: bind-mount для загруженных файлов
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Task Tracker сохраняет вложения в /app/uploads внутри контейнера. Чтобы файлы были доступны
 *   на хосте и переживали контейнер — смонтируйте папку хоста (bind-mount):
 *   1) Создайте папку на хосте: mkdir uploads
 *   2) Запустите с bind-mount:
 *        docker run -d -p 8080:8080 \
 *            -v "$(pwd)/uploads:/app/uploads" \
 *            -e APP_UPLOAD_DIR=/app/uploads \
 *            tasktracker:1.0.0
 *        (Windows PowerShell: -v "${PWD}/uploads:/app/uploads")
 *   3) Загрузите файл через API и проверьте, что он появился в ./uploads на хосте.
 *   4) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: различать bind-mount (папка хоста) и именованный том (задача 04); применять для файлов.
 *
 * ТАБЛИЦА:
 *   именованный том (pgdata:/path)   — Docker управляет хранилищем (данные БД);
 *   bind-mount (/host/path:/path)    — конкретная папка хоста (доступ к файлам, разработка).
 *
 * ПОДСКАЗКА: APP_UPLOAD_DIR указывает приложению, куда писать (совпадает с точкой монтирования).
 */
public class Task06 {
    public static void main(String[] args) {
        String commands = """
                # TODO: bind-mount папки для вложений
                # mkdir uploads
                # docker run -d -p 8080:8080 \\
                #     -v "$(pwd)/uploads:/app/uploads" \\
                #     -e APP_UPLOAD_DIR=/app/uploads \\
                #     tasktracker:1.0.0
                # (PowerShell: -v "${PWD}/uploads:/app/uploads")
                #
                # → загруженные файлы видны в ./uploads на хосте и переживут контейнер
                """;
        System.out.println(commands);
    }
}
