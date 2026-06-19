package m38_http_protocol.practice;

/**
 * Задача 02 — Модуль 38: Заголовки и статус
 *
 * ЗАДАНИЕ:
 *   Отправьте GET-запрос на https://jsonplaceholder.typicode.com/posts/1
 *   и выведите:
 *     - код ответа;
 *     - заголовок Content-Type ответа;
 *     - первые 100 символов тела.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Код: 200
 *   Content-Type: application/json; charset=utf-8
 *   Тело (начало): {"userId":1,"id":1,"title":"...
 *
 * ПОДСКАЗКА:
 *   resp.headers().firstValue("Content-Type").orElse("?");
 *   resp.body().substring(0, Math.min(100, resp.body().length()));
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task02 {
    public static void main(String[] args) throws Exception {
        // GET + чтение статуса, Content-Type и начала тела
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .header("Accept", "application/json")
                .build();

        try{
            HttpResponse<String> resp = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(resp.statusCode());
            System.out.println(resp.headers().firstValue("Content-Type").orElse("?"));
            System.out.println(resp.body().substring(0, Math.min(100, resp.body().length())));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
