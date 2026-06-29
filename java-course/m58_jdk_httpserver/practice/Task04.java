package m58_jdk_httpserver.practice;

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
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/echo-body", new ExtendedEchoBodyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("📌 POST http://localhost:8089/echo-body - эхо тела");
        System.out.println("📌 POST http://localhost:8089/echo-body?json=true - форматированный ответ");
    }

    static class ExtendedEchoBodyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                sendError(exchange, 405, "Method not allowed. Use POST.");
                return;
            }

            // Читаем параметры запроса
            String query = exchange.getRequestURI().getQuery();
            boolean pretty = query != null && query.contains("pretty=true");
            boolean jsonFormat = query != null && query.contains("json=true");

            // Читаем тело
            String body = readBody(exchange);

            // Получаем Content-Type запроса
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");

            // Логируем
            System.out.printf("📥 POST /echo-body%n");
            System.out.printf("   Content-Type: %s%n", contentType != null ? contentType : "unknown");
            System.out.printf("   Body length: %d байт%n", body.length());

            // Формируем ответ
            String response;
            if (jsonFormat) {
                // Форматированный JSON
                String escapedBody = body.replace("\"", "\\\"");
                response = String.format("{\"received\":\"%s\", \"length\":%d, \"contentType\":\"%s\"}",
                        escapedBody, body.length(),
                        contentType != null ? contentType : "unknown");
            } else {
                // Простой JSON
                String escapedBody = body.replace("\"", "\\\"");
                response = String.format("{\"received\":\"%s\"}", escapedBody);
            }

            // Pretty print (если запрошено)
            if (pretty) {
                response = response.replace("{", "{\n  ")
                        .replace("}", "\n}")
                        .replace(",", ",\n  ");
            }

            sendJson(exchange, 200, response);
            System.out.println("✅ POST /echo-body -> 200");
        }

        private String readBody(HttpExchange exchange) throws IOException {
            try (InputStream is = exchange.getRequestBody()) {
                byte[] bytes = is.readAllBytes();
                return new String(bytes, StandardCharsets.UTF_8);
            }
        }

        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(bytes.length));

            exchange.sendResponseHeaders(status, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
                os.flush();
            }
        }

        private void sendError(HttpExchange exchange, int status, String message) throws IOException {
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(bytes.length));

            exchange.sendResponseHeaders(status, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
                os.flush();
            }
        }
    }
}
