package m38_http_protocol.practice;

/**
 * Задача 06 — Модуль 38: Асинхронные запросы
 *
 * ЗАДАНИЕ:
 *   Отправьте НЕСКОЛЬКО запросов АСИНХРОННО (sendAsync), не блокируя
 *   поток на каждом. Запросите, например, посты с id 1, 2, 3 с
 *   https://jsonplaceholder.typicode.com/posts/{id}.
 *   Дождитесь всех ответов и выведите код + длину тела каждого.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (порядок может отличаться):
 *   Пост 1: 200, длина 292
 *   Пост 2: 200, длина 270
 *   Пост 3: 200, длина 305
 *
 * ПОДСКАЗКА:
 *   client.sendAsync(req, BodyHandlers.ofString()) возвращает
 *   CompletableFuture<HttpResponse<String>>.
 *   Соберите список фьючерсов и дождитесь через .join() или
 *   CompletableFuture.allOf(...).join().
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Task06 {
    public static void main(String[] args) {
        // Отправьте 3 асинхронных запроса и обработайте все ответы
    }
}
