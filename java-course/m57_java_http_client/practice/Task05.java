package m57_java_http_client.practice;

/**
 * Задача 05 — Модуль 57: Query-параметры, заголовки и BodyHandlers.ofLines
 *
 * ЗАДАНИЕ:
 *   1. Составьте URI с query-параметром: /posts?userId=1
 *      (используйте URI.create("...?userId=1")).
 *   2. Добавьте несколько заголовков к запросу:
 *        Accept: application/json
 *        User-Agent: JavaCourse-57/1.0
 *        X-Request-Id: test-42
 *   3. Используйте BodyHandlers.ofLines() вместо ofString().
 *      body() возвращает Stream<String> — обработайте построчно.
 *   4. Выведите:
 *        - код ответа
 *        - первые 5 строк тела (stream.limit(5))
 *        - общее количество строк в ответе
 *
 *   ЗАМЕЧАНИЕ: Stream<String> из ofLines() можно пройти только один раз.
 *   Для подсчёта числа строк И вывода части из них — используйте
 *   промежуточный List или два запроса.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Код: 200
 *   Первые 5 строк:
 *   [
 *     {
 *       "userId": 1,
 *       "id": 1,
 *   Всего строк: 83
 *
 * ПОДСКАЗКА:
 *   HttpResponse<Stream<String>> r = client.send(req, HttpResponse.BodyHandlers.ofLines());
 *   List<String> lines = r.body().collect(java.util.stream.Collectors.toList());
 *   lines.stream().limit(5).forEach(System.out::println);
 *   System.out.println("Всего строк: " + lines.size());
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task05 {
    public static void main(String[] args) throws Exception {
        // 1. URI с query-параметром userId=1

        // 2. Создайте GET-запрос с заголовками Accept, User-Agent, X-Request-Id

        // 3. Отправьте с BodyHandlers.ofLines() и соберите в List<String>

        // 4. Выведите код ответа, первые 5 строк и общее количество строк
    }
}
