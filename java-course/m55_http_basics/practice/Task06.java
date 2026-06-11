package m55_http_basics.practice;

/**
 * Задача 06 — Модуль 55: Заголовки запроса (Accept, Content-Type, Authorization)
 *
 * ЗАДАНИЕ:
 *   Отправьте POST-запрос на https://jsonplaceholder.typicode.com/posts
 *   с тремя заголовками:
 *     - Accept: application/json
 *     - Content-Type: application/json
 *     - Authorization: Bearer my-secret-token-12345
 *
 *   После отправки выведите:
 *     1. Статус-код ответа
 *     2. Список ВСЕХ заголовков, которые были отправлены в запросе
 *        (используйте request.headers().map())
 *     3. Заголовок Content-Type из ОТВЕТА (сервер может изменить формат)
 *     4. Тело ответа
 *
 *   Дополнительно: попробуйте отправить тот же запрос с
 *     Content-Type: text/plain
 *   и посмотрите, изменится ли статус ответа (у jsonplaceholder — нет, но
 *   в реальном API при несовпадении сервер вернёт 415).
 *   Запишите наблюдение в комментарии.
 *
 * ПОДСКАЗКА:
 *   request.headers()    — HttpHeaders исходящего запроса
 *   Заголовок Authorization: Bearer <token> — стандарт для JWT/OAuth токенов.
 *   В реальном API токен проверяется сервером; здесь это заглушка.
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task06 {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String body = "{\"title\":\"Важная заметка\",\"body\":\"содержимое\",\"userId\":7}";
        String token = "my-secret-token-12345";

        // TODO: соберите HttpRequest с нужными заголовками:
        //   .header("Accept", "application/json")
        //   .header("Content-Type", "application/json")
        //   .header("Authorization", "Bearer " + token)
        //   .POST(HttpRequest.BodyPublishers.ofString(body))
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                // TODO: добавьте заголовки здесь
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // TODO: выведите статус-код
        System.out.println("Статус: " /* + response.statusCode() */);
        System.out.println();

        // TODO: выведите все ИСХОДЯЩИЕ заголовки запроса
        System.out.println("=== Заголовки запроса (отправленные) ===");
        // request.headers().map().forEach((name, values) ->
        //         System.out.println("  " + name + ": " + String.join(", ", values)));
        System.out.println();

        // TODO: выведите Content-Type из ОТВЕТА
        System.out.println("Content-Type ответа: "
                /* + response.headers().firstValue("content-type").orElse("отсутствует") */);
        System.out.println();

        // TODO: выведите тело ответа
        System.out.println("Тело ответа:");
        System.out.println(/* response.body() */ "TODO");
        System.out.println();

        // TODO (дополнительно): повторите запрос с Content-Type: text/plain
        // и запишите наблюдение:
        System.out.println("Наблюдение при Content-Type: text/plain:");
        System.out.println("// TODO: статус = ?, изменилось ли поведение сервера?");
    }
}
