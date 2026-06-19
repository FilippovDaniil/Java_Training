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
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Task06 {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    public static void main(String[] args) {
        System.out.println("=== Асинхронные запросы к API ===\n");
        System.out.println("Отправка асинхронных запросов...\n");

        List<Integer> postIds = List.of(1, 2, 3, 4, 5);
        List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();

        // Отправляем асинхронные запросы для каждого ID
        for (int id : postIds) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + id))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Отправляем асинхронный запрос
            CompletableFuture<HttpResponse<String>> future = client.sendAsync(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            futures.add(future);
        }

        // Ожидаем завершения всех запросов
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .join();

        // Выводим результаты
        System.out.println("Результаты запросов:");
        System.out.println("=" .repeat(60));

        for (CompletableFuture<HttpResponse<String>> future : futures) {
            HttpResponse<String> response = future.join();
            int id = extractId(response.uri().toString());
            int statusCode = response.statusCode();
            int bodyLength = response.body().length();

            System.out.printf("Пост %d: %d, длина %d%n", id, statusCode, bodyLength);
            System.out.printf("  Тело: %s...%n%n",
                    response.body().length() > 100 ?
                            response.body().substring(0, 100) + "..." :
                            response.body());
        }

        System.out.println("=" .repeat(60));
        System.out.println("✅ Все запросы выполнены асинхронно!");
    }

    private static int extractId(String uri) {
        try {
            String[] parts = uri.split("/");
            return Integer.parseInt(parts[parts.length - 1]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
