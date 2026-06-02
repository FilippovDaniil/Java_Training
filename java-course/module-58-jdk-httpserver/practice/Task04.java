/**
 * Задача 04 — Модуль 58: Чтение тела POST-запроса
 *
 * ЗАДАНИЕ:
 *   Запустите HttpServer на порту 8080.
 *   Контекст "/echo-body":
 *     POST → прочитать тело запроса (InputStream → String, UTF-8),
 *            вернуть 200 + JSON: {"received":"<тело запроса>"}
 *     GET  → вернуть 405
 *
 *   Тело может быть любым текстом (не обязательно JSON).
 *   Не используйте сторонние библиотеки.
 *
 * КАК ПРОВЕРИТЬ:
 *     curl -X POST http://localhost:8080/echo-body \
 *          -H "Content-Type: text/plain" \
 *          -d "привет мир"
 *   Ожидаемый ответ:
 *     {"received":"привет мир"}
 *
 * ПОДСКАЗКА:
 *   InputStream is = exchange.getRequestBody();
 *   String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
 *   // Для экранирования кавычек в body используйте body.replace("\"", "\\\"")
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Task04 {

    public static void main(String[] args) throws IOException {
        // TODO: создайте сервер на порту 8080
        // TODO: createContext("/echo-body", new EchoBodyHandler())
        // TODO: setExecutor, start, вывод
    }

    static class EchoBodyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                // TODO: ответить 405
                return;
            }

            // TODO: прочитать тело запроса из exchange.getRequestBody() в String (UTF-8)
            String body = null; // замените на реальное чтение

            // TODO: экранировать кавычки в body (на случай если тело содержит ")
            // TODO: сформировать JSON: {"received":"<body>"}
            // TODO: отправить 200 + JSON с Content-Type application/json; charset=UTF-8
        }

        /** Вспомогательный метод чтения тела — реализуйте */
        private String readBody(HttpExchange exchange) throws IOException {
            // TODO: прочитать exchange.getRequestBody() и вернуть как String UTF-8
            return null;
        }

        /** Вспомогательный метод отправки JSON-ответа — реализуйте */
        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            // TODO: sendResponseHeaders и запись в getResponseBody()
        }
    }
}
