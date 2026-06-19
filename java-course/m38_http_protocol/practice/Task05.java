package m38_http_protocol.practice;

/**
 * Задача 05 — Модуль 38: Обработка кодов состояния
 *
 * ЗАДАНИЕ:
 *   Запросите несколько URL и для каждого выведите осмысленную реакцию
 *   на код ответа:
 *     - https://jsonplaceholder.typicode.com/posts/1   (200 OK)
 *     - https://jsonplaceholder.typicode.com/posts/9999 (404 Not Found)
 *   Реализуйте метод, который по statusCode печатает:
 *     2xx → "Успех", 4xx → "Ошибка клиента", 5xx → "Ошибка сервера",
 *     иначе → "Прочее".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   /posts/1 → 200 Успех
 *   /posts/9999 → 404 Ошибка клиента
 *
 * ПОДСКАЗКА:
 *   int code = resp.statusCode();
 *   if (code >= 200 && code < 300) ... else if (code >= 400 && code < 500) ...
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Task05 {
    public static void main(String[] args) throws Exception {
        // Запросите оба URL и классифицируйте коды ответа
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent","JavaCourseClient/1.0")
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/999999999999999999999"))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent","JavaCourseClient/1.0")
                .timeout(Duration.ofSeconds(5))
                .build();

        try{
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(describeStatus(response.statusCode()));   // 200
            System.out.println(response.body());          // тело ответа

            HttpResponse<String> response1 = client.send(request1,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(describeStatus(response1.statusCode()));   // 200
            System.out.println(response1.body());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // TODO: метод describeStatus(int code)
    private static String describeStatus(int code){
        if (code >= 200 && code < 300){
            return "Код успешен: " + code;
        }
        else if (code >= 400 && code < 500){
            return "Ошибка запроса: " + code;
        }else {
            return "Code: " + code;
        }
    }
}
