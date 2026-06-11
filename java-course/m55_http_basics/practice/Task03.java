package m55_http_basics.practice;

/**
 * Задача 03 — Модуль 55: GET-запрос и чтение заголовков ответа
 *
 * ЗАДАНИЕ:
 *   Отправьте GET-запрос на публичное API:
 *     https://jsonplaceholder.typicode.com/posts/1
 *
 *   Выведите:
 *     1. HTTP статус-код ответа
 *     2. Все заголовки ответа (имя: значение)
 *     3. Значение заголовка Content-Type отдельной строкой
 *     4. Первые 100 символов тела ответа
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Статус: 200
 *   --- Заголовки ---
 *   content-type: application/json; charset=utf-8
 *   cache-control: max-age=43200
 *   ... (остальные заголовки)
 *   Content-Type: application/json; charset=utf-8
 *   Начало тела: {
 *     "userId": 1,
 *     "id": 1,
 *     "title": ...
 *
 * ПОДСКАЗКА:
 *   response.headers().map()          — Map<String, List<String>> всех заголовков
 *   response.headers().firstValue("Content-Type") — Optional<String>
 *   Имена заголовков в HTTP/2 и java.net.http — в нижнем регистре.
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task03 {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // TODO: выведите статус-код
        System.out.println("Статус: " /* + ? */);

        // TODO: выведите все заголовки ответа (обойдите response.headers().map())
        System.out.println("--- Заголовки ---");
        // response.headers().map().forEach(...)

        // TODO: выведите значение заголовка Content-Type отдельно
        System.out.println("Content-Type: " /* + ? */);

        // TODO: выведите первые 100 символов тела
        String body = response.body();
        System.out.println("Начало тела: " /* + body.substring(0, Math.min(100, body.length())) */);
    }
}
