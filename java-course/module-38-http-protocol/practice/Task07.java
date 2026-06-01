/**
 * Задача 07 — Модуль 38 (МИНИ-ПРОЕКТ): Консольный клиент API
 *
 * ЗАДАНИЕ:
 *   Напишите небольшой консольный клиент к публичному API
 *   https://jsonplaceholder.typicode.com. Меню в цикле:
 *     1 — получить пост по id (GET /posts/{id}), вывести тело;
 *     2 — получить список постов пользователя
 *         (GET /posts?userId={id}), вывести их количество;
 *     3 — создать пост (POST /posts с JSON), вывести код и id из ответа;
 *     4 — удалить пост (DELETE /posts/{id}), вывести код ответа;
 *     0 — выход.
 *   Обрабатывайте ошибки сети и неуспешные коды (4xx/5xx) — выводите
 *   понятное сообщение, не роняя программу.
 *
 * ДОПОЛНИТЕЛЬНО (по желанию):
 *   Извлеките из JSON-ответа значение поля (например, "title")
 *   простым разбором строки или через библиотеку (Gson/Jackson).
 *
 * ПОДСКАЗКИ:
 *   - вынесите отправку запроса в отдельный метод, возвращающий
 *     HttpResponse<String>;
 *   - для query-параметров просто стройте URL: ".../posts?userId=" + id;
 *   - DELETE: HttpRequest.newBuilder().uri(...).DELETE().build();
 *   - оборачивайте send в try-catch (IOException/InterruptedException);
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Scanner scanner = new Scanner(System.in);
        // Реализуйте меню-клиент к jsonplaceholder API

        scanner.close();
    }
}
