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
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Task06 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        int[] ids = {1, 5, 10};
        String base = "https://jsonplaceholder.typicode.com/posts/";

        // 1. Создайте список CompletableFuture — по одному sendAsync на каждый id

        // 2. Дождитесь всех через CompletableFuture.allOf(...).join()

        // 3. Пройдите по futures, получите ответы и выведите результат
    }
}
