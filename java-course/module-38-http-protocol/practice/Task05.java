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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task05 {
    public static void main(String[] args) throws Exception {
        // Запросите оба URL и классифицируйте коды ответа
    }

    // TODO: метод describeStatus(int code)
}
