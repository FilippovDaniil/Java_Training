package m57_java_http_client.practice;

/**
 * Задача 04 — Модуль 57: Таймауты и обработка исключений
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте поведение HttpClient при истечении таймаута.
 *
 *   1. Создайте HttpClient с connectTimeout(Duration.ofSeconds(3)).
 *   2. Создайте GET-запрос к https://jsonplaceholder.typicode.com/posts/2
 *      с ЗАВЕДОМО МАЛЫМ таймаутом запроса: timeout(Duration.ofMillis(1)).
 *      (1 мс — почти гарантированное срабатывание HttpTimeoutException.)
 *   3. Попробуйте отправить запрос. В блоке catch:
 *        - HttpTimeoutException → "Таймаут истёк: " + e.getMessage()
 *        - IOException          → "Ошибка ввода-вывода: " + e.getMessage()
 *        - InterruptedException → "Поток прерван"; Thread.currentThread().interrupt()
 *   4. Отдельно выполните тот же GET с нормальным таймаутом (5 секунд)
 *      и выведите код ответа — убедитесь, что без таймаута всё работает.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Запрос с малым таймаутом:
 *   Таймаут истёк: request timed out
 *
 *   Запрос с нормальным таймаутом:
 *   Код: 200
 *
 * ПОДСКАЗКА:
 *   import java.net.http.HttpTimeoutException;
 *   HttpRequest.newBuilder()
 *       .uri(...)
 *       .timeout(Duration.ofMillis(1))   // крошечный таймаут
 *       .GET().build();
 *   try { client.send(...) }
 *   catch (HttpTimeoutException e) { ... }
 *   catch (IOException e) { ... }
 *   catch (InterruptedException e) { ... }
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class Task04 {

    public static void main(String[] args) {
        // 1. Создайте HttpClient с connectTimeout 3 секунды

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))      // таймаут соединения
                .version(HttpClient.Version.HTTP_2)         // предпочесть HTTP/2 (fallback → HTTP/1.1)
                .followRedirects(HttpClient.Redirect.NORMAL)// следовать 3xx (кроме HTTPS→HTTP)
                .build();

        String url = "https://jsonplaceholder.typicode.com/posts/2";


        // 2. Запрос с таймаутом 1 мс — должен вызвать HttpTimeoutException
        System.out.println("Запрос с малым таймаутом:");
        // TODO: создать запрос с timeout(Duration.ofMillis(1)) и поймать исключения
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofMillis(1))
                .GET()
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        try{
            HttpResponse<String> r = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(r.version());
            System.out.println(r.statusCode());
            System.out.println(r.body());
        } catch (HttpTimeoutException e){
            System.out.println("Таймаут истёк: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (InterruptedException e){
            System.out.println("Поток прерван");
            Thread.currentThread().interrupt();
        }


        // 3. Запрос с нормальным таймаутом 5 секунд — должен успешно вернуть 200
        System.out.println("\nЗапрос с нормальным таймаутом:");
        // TODO: создать запрос с timeout(Duration.ofSeconds(5)) и вывести код ответа
        try{
            HttpResponse<String> r = client.send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(r.version());
            System.out.println(r.statusCode());
            System.out.println(r.body());
        } catch (HttpTimeoutException e){
            System.out.println("Таймаут истёк: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (InterruptedException e){
            System.out.println("Поток прерван");
            Thread.currentThread().interrupt();
        }

    }
}
