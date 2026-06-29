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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task03 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);

        server.createContext("/status", new StatusHandlerExtended());
        server.createContext("/health", new HealthHandler());
        server.createContext("/version", new VersionHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("📌 Маршруты:");
        System.out.println("   /status  - Статус сервиса");
        System.out.println("   /health  - Проверка здоровья");
        System.out.println("   /version - Версия");
    }

    static class StatusHandlerExtended implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendJsonError(exchange, 405, "Method not allowed");
                return;
            }

            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String json = String.format("""
                    {
                      "status": "ok",
                      "service": "notes",
                      "version": "1.0",
                      "timestamp": "%s",
                      "uptime": "2h 15m 30s"
                    }
                    """, timestamp);

            sendJsonResponse(exchange, 200, json);
            System.out.println("✅ GET /status -> 200");
        }
    }

    static class HealthHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendJsonError(exchange, 405, "Method not allowed");
                return;
            }

            String json = """
                    {
                      "status": "healthy",
                      "checks": {
                        "database": "up",
                        "cache": "up",
                        "disk": "70% used"
                      }
                    }
                    """;

            sendJsonResponse(exchange, 200, json);
            System.out.println("✅ GET /health -> 200");
        }
    }

    static class VersionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendJsonError(exchange, 405, "Method not allowed");
                return;
            }

            String json = """
                    {
                      "version": "1.0.0",
                      "build": "2024-01-15T10:30:00Z",
                      "commit": "a1b2c3d4e5f6"
                    }
                    """;

            sendJsonResponse(exchange, 200, json);
            System.out.println("✅ GET /version -> 200");
        }
    }

    private static void sendJsonResponse(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Content-Length", String.valueOf(bytes.length));

        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
            os.flush();
        }
    }

    private static void sendJsonError(HttpExchange exchange, int status, String message) throws IOException {
        String json = String.format("{\"error\":\"%s\"}", message);
        sendJsonResponse(exchange, status, json);
    }
}
