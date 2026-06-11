package m57_java_http_client.practice;

/**
 * Задача 02 — Модуль 57: POST с JSON-телом и разбор статуса 201
 *
 * ЗАДАНИЕ:
 *   Создайте новый пост через POST-запрос к
 *   https://jsonplaceholder.typicode.com/posts
 *   Тело запроса — JSON:
 *     {"title":"Мой заголовок","body":"Текст заметки","userId":1}
 *
 *   Требования:
 *   1. Установите заголовок Content-Type: application/json.
 *   2. Тело передайте через BodyPublishers.ofString(json).
 *   3. Проверьте код ответа: сервер должен вернуть 201 Created.
 *   4. Выведите полное тело ответа (в нём будет id нового поста).
 *   5. Если сервер вернул не 201 — выведите сообщение об ошибке
 *      вида "Ожидался 201, получен: <код>".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Статус: 201 Created
 *   Ответ: {"title":"Мой заголовок","body":"Текст заметки","userId":1,"id":101}
 *
 * ПОДСКАЗКА:
 *   HttpRequest.newBuilder()
 *       .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
 *       .header("Content-Type", "application/json")
 *       .POST(HttpRequest.BodyPublishers.ofString(json))
 *       .build();
 *   int code = response.statusCode();
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task02 {
    public static void main(String[] args) throws Exception {
        // JSON-тело для нового поста
        String json = "{\"title\":\"Мой заголовок\",\"body\":\"Текст заметки\",\"userId\":1}";

        // 1. Создайте HttpClient (можно HttpClient.newHttpClient())

        // 2. Создайте POST-запрос с заголовком Content-Type и телом из json

        // 3. Отправьте запрос через send(..., BodyHandlers.ofString())

        // 4. Проверьте statusCode() и выведите результат или сообщение об ошибке
    }
}
