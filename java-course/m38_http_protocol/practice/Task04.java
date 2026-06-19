package m38_http_protocol.practice;

/**
 * Задача 04 — Модуль 38: Пользовательские заголовки и таймаут
 *
 * ЗАДАНИЕ:
 *   Отправьте GET на https://httpbin.org/headers (этот сервис вернёт
 *   обратно полученные им заголовки). Установите:
 *     - собственный User-Agent (например "JavaCourseClient/1.0");
 *     - Accept: application/json;
 *     - таймаут запроса 5 секунд.
 *   Выведите тело ответа — в нём вы увидите свои заголовки.
 *
 * ПОДСКАЗКА:
 *   HttpRequest.newBuilder()
 *     .uri(...)
 *     .timeout(Duration.ofSeconds(5))
 *     .header("User-Agent", "JavaCourseClient/1.0")
 *     .header("Accept", "application/json")
 *     .GET().build();
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class Task04 {
    public static void main(String[] args) throws Exception {
        // GET с кастомными заголовками и таймаутом; выведите тело
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers"))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent","JavaCourseClient/1.0")
                .timeout(Duration.ofSeconds(5))
                .build();

        try{
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());   // 200
            System.out.println(response.body());          // тело ответа
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
