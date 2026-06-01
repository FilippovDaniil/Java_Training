/**
 * Задача 02 — Модуль 38: Заголовки и статус
 *
 * ЗАДАНИЕ:
 *   Отправьте GET-запрос на https://jsonplaceholder.typicode.com/posts/1
 *   и выведите:
 *     - код ответа;
 *     - заголовок Content-Type ответа;
 *     - первые 100 символов тела.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Код: 200
 *   Content-Type: application/json; charset=utf-8
 *   Тело (начало): {"userId":1,"id":1,"title":"...
 *
 * ПОДСКАЗКА:
 *   resp.headers().firstValue("Content-Type").orElse("?");
 *   resp.body().substring(0, Math.min(100, resp.body().length()));
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task02 {
    public static void main(String[] args) throws Exception {
        // GET + чтение статуса, Content-Type и начала тела
    }
}
