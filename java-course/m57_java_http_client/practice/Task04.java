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

        String url = "https://jsonplaceholder.typicode.com/posts/2";

        // 2. Запрос с таймаутом 1 мс — должен вызвать HttpTimeoutException
        System.out.println("Запрос с малым таймаутом:");
        // TODO: создать запрос с timeout(Duration.ofMillis(1)) и поймать исключения

        // 3. Запрос с нормальным таймаутом 5 секунд — должен успешно вернуть 200
        System.out.println("\nЗапрос с нормальным таймаутом:");
        // TODO: создать запрос с timeout(Duration.ofSeconds(5)) и вывести код ответа
    }
}
