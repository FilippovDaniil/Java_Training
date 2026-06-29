package m58_jdk_httpserver.practice;

/**
 * Задача 01 — Модуль 58: Запуск минимального HTTP-сервера
 *
 * ЗАДАНИЕ:
 *   Запустите HttpServer на порту 8080.
 *   Зарегистрируйте контекст "/" — он должен отвечать на все запросы.
 *   Ответ: статус 200, Content-Type text/plain, тело "Hello from JDK HttpServer!".
 *   Используйте setExecutor(null) (однопоточный режим).
 *   Выведите в консоль: "Сервер запущен: http://localhost:8080"
 *   Сервер должен работать до завершения программы (не вызывайте server.stop).
 *
 * КАК ПРОВЕРИТЬ:
 *   Запустите программу, затем в другом терминале:
 *     curl -v http://localhost:8080/
 *   Ожидаемый ответ:
 *     HTTP/1.1 200 OK
 *     Content-Type: text/plain
 *     Hello from JDK HttpServer!
 *
 * ПОДСКАЗКА:
 *   HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
 *   server.createContext("/", exchange -> { ... });
 *   server.setExecutor(null);
 *   server.start();
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Task01 {
    private static final Map<String, String> posts = new HashMap<>();
    private static int idCounter = 1;

    public static void main(String[] args) throws IOException {
        // Инициализация тестовыми данными
        posts.put("1", "Первый пост");
        posts.put("2", "Второй пост");
        posts.put("3", "Третий пост");

        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);

        // GET /posts - получить все посты
        server.createContext("/posts", exchange -> {
            String method = exchange.getRequestMethod();

            if ("GET".equals(method)) {
                String response = posts.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining("\n"));
                sendResponse(exchange, 200, "text/plain; charset=UTF-8", response);

            } else if ("POST".equals(method)) {
                // Читаем тело запроса
                String body = readRequestBody(exchange);
                String id = String.valueOf(idCounter++);
                posts.put(id, body);
                sendResponse(exchange, 201, "text/plain; charset=UTF-8", "Created: " + id);

            } else if ("DELETE".equals(method)) {
                String path = exchange.getRequestURI().getPath();
                String id = path.substring(path.lastIndexOf('/') + 1);
                if (posts.remove(id) != null) {
                    sendResponse(exchange, 200, "text/plain; charset=UTF-8", "Deleted: " + id);
                } else {
                    sendResponse(exchange, 404, "text/plain; charset=UTF-8", "Not found: " + id);
                }
            } else {
                sendResponse(exchange, 405, "text/plain; charset=UTF-8", "Method not allowed");
            }
        });

        // GET /posts/{id} - получить пост по ID
        server.createContext("/posts/", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "text/plain; charset=UTF-8", "Method not allowed");
                return;
            }

            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf('/') + 1);

            String post = posts.get(id);
            if (post != null) {
                sendResponse(exchange, 200, "text/plain; charset=UTF-8", "ID: " + id + "\n" + post);
            } else {
                sendResponse(exchange, 404, "text/plain; charset=UTF-8", "Post not found: " + id);
            }
        });

        server.setExecutor(null);
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("\n📌 Доступные маршруты:");
        System.out.println("   GET    /posts              - Все посты");
        System.out.println("   POST   /posts              - Создать пост (тело в запросе)");
        System.out.println("   GET    /posts/{id}         - Пост по ID");
        System.out.println("   DELETE /posts/{id}         - Удалить пост");
        System.out.println("\n📌 Примеры:");
        System.out.println("   curl http://localhost:8089/posts");
        System.out.println("   curl -X POST -d 'Новый пост' http://localhost:8089/posts");
        System.out.println("   curl http://localhost:8089/posts/1");
        System.out.println("   curl -X DELETE http://localhost:8089/posts/1");
    }

    private static String readRequestBody(HttpExchange exchange) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String contentType, String response) {
        try {
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(responseBytes.length));

            exchange.sendResponseHeaders(statusCode, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
                os.flush();
            }

            System.out.printf("✅ %s %s -> %d%n",
                    exchange.getRequestMethod(),
                    exchange.getRequestURI(),
                    statusCode);

        } catch (IOException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }
}
