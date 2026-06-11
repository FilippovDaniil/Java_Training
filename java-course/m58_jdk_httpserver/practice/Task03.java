package m58_jdk_httpserver.practice;

/**
 * Задача 03 — Модуль 58: JSON-ответ вручную
 *
 * ЗАДАНИЕ:
 *   Запустите HttpServer на порту 8080.
 *   Контекст "/status" — только GET.
 *   Верните JSON-ответ:
 *     {"status":"ok","service":"notes","version":"1.0"}
 *   Заголовок: Content-Type: application/json; charset=UTF-8
 *   HTTP-статус: 200
 *
 *   ВАЖНО: длина тела в sendResponseHeaders — это bytes.length (байты UTF-8),
 *          а не json.length() (символы). Для ASCII они совпадают, но для
 *          кириллицы — нет. Используйте bytes.length всегда.
 *
 * КАК ПРОВЕРИТЬ:
 *     curl -i http://localhost:8080/status
 *   Ожидаемый вывод:
 *     HTTP/1.1 200 OK
 *     Content-Type: application/json; charset=UTF-8
 *     {"status":"ok","service":"notes","version":"1.0"}
 *
 * ПОДСКАЗКА:
 *   String json = "{\"status\":\"ok\",\"service\":\"notes\",\"version\":\"1.0\"}";
 *   byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
 *   exchange.sendResponseHeaders(200, bytes.length);
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Task03 {

    public static void main(String[] args) throws IOException {
        // TODO: создайте сервер на порту 8080
        // TODO: createContext("/status", new StatusHandler())
        // TODO: setExecutor, start, вывод
    }

    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                // TODO: ответить 405 с телом "Метод не разрешён"
                return;
            }

            String json = "{\"status\":\"ok\",\"service\":\"notes\",\"version\":\"1.0\"}";
            // TODO: перевести json в байты (UTF-8)
            // TODO: установить заголовок Content-Type: application/json; charset=UTF-8
            // TODO: sendResponseHeaders(200, bytes.length)
            // TODO: записать байты в getResponseBody() и закрыть поток
        }
    }
}
