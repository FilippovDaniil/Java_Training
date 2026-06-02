/**
 * Задача 03 — Модуль 57: PUT (обновление) и DELETE
 *
 * ЗАДАНИЕ:
 *   Выполните два запроса к https://jsonplaceholder.typicode.com:
 *
 *   A) PUT /posts/1 — полная замена поста:
 *      Тело: {"id":1,"title":"Обновлённый заголовок","body":"Новое тело","userId":1}
 *      Заголовок: Content-Type: application/json
 *      Выведите: код ответа и тело (сервер вернёт обновлённый объект).
 *      Ожидаемый код: 200.
 *
 *   B) DELETE /posts/1 — удаление поста:
 *      Тела нет.
 *      Выведите: код ответа (ожидается 200) и тело (будет пустой JSON {}).
 *
 *   Для каждого запроса используйте BodyHandlers.ofString().
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   PUT  /posts/1 → 200
 *   Тело: {"id":1,"title":"Обновлённый заголовок", ...}
 *
 *   DELETE /posts/1 → 200
 *   Тело: {}
 *
 * ПОДСКАЗКА:
 *   PUT:    .PUT(HttpRequest.BodyPublishers.ofString(json))
 *   DELETE: .DELETE()  — тело не нужно (BodyPublishers не указывается)
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task03 {
    // Общий клиент — создать один раз, переиспользовать
    static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        String base = "https://jsonplaceholder.typicode.com/posts/1";
        String putJson = "{\"id\":1,\"title\":\"Обновлённый заголовок\",\"body\":\"Новое тело\",\"userId\":1}";

        // A) Выполните PUT-запрос и выведите код + тело

        // B) Выполните DELETE-запрос и выведите код + тело
    }
}
