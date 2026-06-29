package m58_jdk_httpserver.practice;

/**
 * Задача 05 — Модуль 58: In-memory хранилище заметок (POST + GET список)
 *
 * ЗАДАНИЕ:
 *   Создайте HttpServer на порту 8080 с контекстом "/notes".
 *   Хранилище заметок — Map<Integer, String>, ключ — id (AtomicInteger).
 *
 *   Обработка:
 *     POST /notes  — тело запроса: простая строка текста заметки (не JSON).
 *                    Создать заметку, вернуть 201 + JSON: {"id":1,"text":"..."}
 *                    Если тело пустое — вернуть 400 + {"error":"Текст не может быть пустым","status":400}
 *
 *     GET  /notes  — вернуть 200 + JSON-массив всех заметок:
 *                    [{"id":1,"text":"..."},{"id":2,"text":"..."}]
 *                    Если заметок нет — вернуть пустой массив []
 *
 *     Прочие методы → 405
 *
 * КАК ПРОВЕРИТЬ:
 *     curl -X POST http://localhost:8080/notes -d "купить молоко"
 *       → {"id":1,"text":"купить молоко"}  (201)
 *     curl -X POST http://localhost:8080/notes -d "позвонить маме"
 *       → {"id":2,"text":"позвонить маме"} (201)
 *     curl http://localhost:8080/notes
 *       → [{"id":1,"text":"купить молоко"},{"id":2,"text":"позвонить маме"}]
 *
 * ПОДСКАЗКА:
 *   AtomicInteger counter = new AtomicInteger(0);
 *   Map<Integer, String> store = new ConcurrentHashMap<>();
 *   int id = counter.incrementAndGet();
 *   store.put(id, text);
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Task05 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/notes", new ExtendedNotesHandler());
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("📌 POST   /notes           - создать заметку");
        System.out.println("📌 GET    /notes           - все заметки");
        System.out.println("📌 GET    /notes/{id}      - заметка по ID");
        System.out.println("📌 PUT    /notes/{id}      - обновить заметку");
        System.out.println("📌 DELETE /notes/{id}      - удалить заметку");
    }

    static class ExtendedNotesHandler implements HttpHandler {
        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            // Проверяем, есть ли ID в пути
            String[] parts = path.split("/");
            boolean hasId = parts.length > 2;
            Integer id = null;

            if (hasId) {
                try {
                    id = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    sendError(exchange, 400, "Неверный формат ID");
                    return;
                }
            }

            switch (method) {
                case "POST" -> {
                    if (hasId) {
                        sendError(exchange, 405, "POST не поддерживает ID");
                    } else {
                        handleCreate(exchange);
                    }
                }
                case "GET" -> {
                    if (hasId) {
                        handleGetById(exchange, id);
                    } else {
                        handleGetAll(exchange);
                    }
                }
                case "PUT" -> {
                    if (hasId) {
                        handleUpdate(exchange, id);
                    } else {
                        sendError(exchange, 400, "Требуется ID");
                    }
                }
                case "DELETE" -> {
                    if (hasId) {
                        handleDelete(exchange, id);
                    } else {
                        sendError(exchange, 400, "Требуется ID");
                    }
                }
                default -> sendError(exchange, 405, "Метод не разрешён");
            }
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            String text = readBody(exchange);
            if (text == null || text.trim().isEmpty()) {
                sendError(exchange, 400, "Текст не может быть пустым");
                return;
            }

            int id = counter.incrementAndGet();
            store.put(id, text);

            String json = String.format("{\"id\":%d,\"text\":\"%s\"}", id, escapeJson(text));
            sendJson(exchange, 201, json);
            System.out.printf("✅ POST /notes -> 201 (id=%d)%n", id);
        }

        private void handleGetAll(HttpExchange exchange) throws IOException {
            String jsonArray = store.entrySet().stream()
                    .map(entry -> String.format("{\"id\":%d,\"text\":\"%s\"}",
                            entry.getKey(), escapeJson(entry.getValue())))
                    .collect(Collectors.joining(",", "[", "]"));

            sendJson(exchange, 200, jsonArray);
            System.out.printf("✅ GET /notes -> 200 (%d заметок)%n", store.size());
        }

        private void handleGetById(HttpExchange exchange, int id) throws IOException {
            String text = store.get(id);
            if (text == null) {
                sendError(exchange, 404, "Заметка не найдена");
                return;
            }

            String json = String.format("{\"id\":%d,\"text\":\"%s\"}", id, escapeJson(text));
            sendJson(exchange, 200, json);
            System.out.printf("✅ GET /notes/%d -> 200%n", id);
        }

        private void handleUpdate(HttpExchange exchange, int id) throws IOException {
            String text = readBody(exchange);
            if (text == null || text.trim().isEmpty()) {
                sendError(exchange, 400, "Текст не может быть пустым");
                return;
            }

            if (!store.containsKey(id)) {
                sendError(exchange, 404, "Заметка не найдена");
                return;
            }

            store.put(id, text);
            String json = String.format("{\"id\":%d,\"text\":\"%s\"}", id, escapeJson(text));
            sendJson(exchange, 200, json);
            System.out.printf("✅ PUT /notes/%d -> 200%n", id);
        }

        private void handleDelete(HttpExchange exchange, int id) throws IOException {
            String removed = store.remove(id);
            if (removed == null) {
                sendError(exchange, 404, "Заметка не найдена");
                return;
            }

            String json = String.format("{\"id\":%d,\"deleted\":true}", id);
            sendJson(exchange, 200, json);
            System.out.printf("✅ DELETE /notes/%d -> 200%n", id);
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

        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
            sendJson(exchange, code, json);
        }

        private String readBody(HttpExchange exchange) throws IOException {
            try (InputStream is = exchange.getRequestBody()) {
                byte[] bytes = is.readAllBytes();
                return new String(bytes, StandardCharsets.UTF_8);
            }
        }

        private String escapeJson(String text) {
            if (text == null) return "";
            return text.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
        }
    }
}
