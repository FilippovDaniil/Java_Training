package m57_java_http_client.practice;

/**
 * Задача 06 — Модуль 57: Асинхронность — sendAsync и CompletableFuture
 *
 * ЗАДАНИЕ:
 *   Загрузите посты с id 1, 5, 10 ПАРАЛЛЕЛЬНО с помощью sendAsync.
 *   Все три запроса должны быть запущены до того, как хоть один получит ответ.
 *
 *   1. Для каждого id создайте запрос GET /posts/{id}.
 *   2. Запустите sendAsync — получите List<CompletableFuture<HttpResponse<String>>>.
 *   3. Дождитесь всех через CompletableFuture.allOf(...).join().
 *   4. Для каждого завершённого future:
 *        - вызовите future.join() для получения HttpResponse
 *        - выведите: "Пост {id}: код={statusCode}, длина={body.length()}"
 *   5. Добавьте обработку ошибок: .exceptionally(e -> null) перед allOf, чтобы
 *      ошибка одного запроса не отменяла остальные.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (порядок завершения может отличаться):
 *   Пост 1:  код=200, длина=292
 *   Пост 5:  код=200, длина=263
 *   Пост 10: код=200, длина=265
 *
 * ПОДСКАЗКА:
 *   CompletableFuture<HttpResponse<String>> f = client.sendAsync(req, BodyHandlers.ofString());
 *   List<CompletableFuture<...>> futures = List.of(f1, f2, f3);
 *   CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
 *   futures.forEach(f -> {
 *       HttpResponse<String> r = f.join();
 *       if (r != null) System.out.println(...);
 *   });
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Task06 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        int[] ids = {1, 5, 10};
        String base = "https://jsonplaceholder.typicode.com/posts/";

        // 1. Создайте список CompletableFuture — по одному sendAsync на каждый id
        List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();

        for (int id : ids) {
            // Для каждого id создаем отдельный запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(base + id))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaCourse-57/1.0")
                    .header("X-Request-ID", "test-42")
                    .GET()
                    .build();

            // Запускаем асинхронный запрос с обработкой ошибок
            CompletableFuture<HttpResponse<String>> future = client
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .exceptionally(e -> {
                        System.err.println("❌ Ошибка при запросе id=" + id + ": " + e.getMessage());
                        return null; // Возвращаем null при ошибке
                    });

            futures.add(future);
            System.out.println("🚀 Запрос отправлен для id=" + id);
        }

        // 2. Дождитесь всех через CompletableFuture.allOf(...).join()
        System.out.println("\n⏳ Ожидание завершения всех запросов...");
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .join();
        System.out.println("✅ Все запросы завершены\n");

        // 3. Пройдите по futures, получите ответы и выведите результат
        System.out.println("=== РЕЗУЛЬТАТЫ ===");
        for (int i = 0; i < futures.size(); i++) {
            int id = ids[i];
            CompletableFuture<HttpResponse<String>> future = futures.get(i);

            try {
                HttpResponse<String> response = future.join();

                if (response != null) {
                    int statusCode = response.statusCode();
                    int bodyLength = response.body() != null ? response.body().length() : 0;
                    System.out.printf("Пост %d: код=%d, длина=%d%n", id, statusCode, bodyLength);
                } else {
                    System.out.printf("Пост %d: Ошибка - ответ null%n", id);
                }

            } catch (Exception e) {
                System.out.printf("Пост %d: Ошибка - %s%n", id, e.getMessage());
            }
        }

        System.out.println("\n✅ Программа завершена");
    }
}