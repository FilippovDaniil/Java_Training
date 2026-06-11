package m57_java_http_client.practice;

/**
 * Задача 01 — Модуль 57: Конфигурация HttpClient через builder
 *
 * Используется встроенный java.net.http (Java 11+). Доступ в интернет обязателен.
 *
 * ЗАДАНИЕ:
 *   1. Создайте HttpClient через HttpClient.newBuilder() с параметрами:
 *        - connectTimeout: 5 секунд
 *        - version: HttpClient.Version.HTTP_2
 *        - followRedirects: HttpClient.Redirect.NORMAL
 *   2. Отправьте GET-запрос на https://jsonplaceholder.typicode.com/posts/1
 *      с заголовком Accept: application/json.
 *   3. Выведите:
 *        - используемую версию HTTP (response.version())
 *        - код ответа
 *        - тело ответа (JSON)
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Версия: HTTP_1_1        (или HTTP_2 если сервер поддерживает)
 *   Код: 200
 *   Тело: {
 *     "userId": 1,
 *     "id": 1,
 *     "title": "sunt aut facere ...",
 *     "body": "quia et suscipit ..."
 *   }
 *
 * ПОДСКАЗКА:
 *   HttpClient.newBuilder()
 *       .connectTimeout(Duration.ofSeconds(5))
 *       .version(HttpClient.Version.HTTP_2)
 *       .followRedirects(HttpClient.Redirect.NORMAL)
 *       .build();
 *   response.version() возвращает HttpClient.Version.
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Task01 {
    public static void main(String[] args) throws Exception {
        // 1. Создайте HttpClient с connectTimeout, version=HTTP_2, followRedirects=NORMAL

        // 2. Создайте GET-запрос к /posts/1 с заголовком Accept: application/json

        // 3. Отправьте запрос (send) и выведите version, statusCode, body
    }
}
