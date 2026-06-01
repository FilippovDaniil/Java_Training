/**
 * Задача 01 — Модуль 38: Простой GET-запрос
 *
 * Используется встроенный java.net.http.HttpClient (зависимостей нет).
 * Для запуска нужен доступ в интернет.
 *
 * ЗАДАНИЕ:
 *   Отправьте GET-запрос на публичный тестовый API
 *   https://jsonplaceholder.typicode.com/todos/1
 *   и выведите код ответа и тело.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Код: 200
 *   Тело: {"userId":1,"id":1,"title":"...","completed":false}
 *
 * ПОДСКАЗКА:
 *   HttpClient client = HttpClient.newHttpClient();
 *   HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
 *   HttpResponse<String> resp = client.send(req, BodyHandlers.ofString());
 *   client.send бросает IOException и InterruptedException.
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task01 {
    public static void main(String[] args) throws Exception {
        // Отправьте GET и выведите statusCode() и body()
    }
}
