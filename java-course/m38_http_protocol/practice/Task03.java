package m38_http_protocol.practice;

/**
 * Задача 03 — Модуль 38: POST-запрос с телом (JSON)
 *
 * ЗАДАНИЕ:
 *   Отправьте POST на https://jsonplaceholder.typicode.com/posts
 *   с JSON-телом {"title":"Привет","body":"текст","userId":1}.
 *   Установите заголовок Content-Type: application/json.
 *   Выведите код ответа (ожидается 201) и тело ответа.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Код: 201
 *   Тело: {"title":"Привет","body":"текст","userId":1,"id":101}
 *
 * ПОДСКАЗКА:
 *   .header("Content-Type", "application/json")
 *   .POST(HttpRequest.BodyPublishers.ofString(json))
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task03 {
    public static void main(String[] args) throws Exception {
        String json = "{\"title\":\"Привет\",\"body\":\"текст\",\"userId\":1}";
        // Отправьте POST с этим телом и выведите ответ
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Accept", "application/json")
                .build();

        try{
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());   // 200
            System.out.println(response.body());          // тело ответа
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
