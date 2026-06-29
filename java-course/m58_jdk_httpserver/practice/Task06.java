package m58_jdk_httpserver.practice;

/**
 * Задача 06 — Модуль 58: Валидация и единый контракт ошибок
 *
 * ЗАДАНИЕ:
 *   Расширьте сервис заметок (порт 8080, контекст "/notes"):
 *     POST /notes         — создать заметку (тело = текст)
 *     GET  /notes         — список всех заметок
 *     GET  /notes/{id}    — получить одну заметку по id
 *
 *   Валидация и ошибки:
 *     - POST с пустым телом или телом только из пробелов → 400
 *     - GET /notes/{id} с несуществующим id             → 404
 *     - Неверный формат id (не число) в URL             → 400
 *     - Неподдерживаемый метод                          → 405
 *
 *   Единый контракт ошибок (обязателен для всех ошибок):
 *     {"error":"<сообщение>","status":<код>}
 *
 *   ВАЖНО: дан готовый каркас sendError — реализуйте его тело.
 *
 * КАК ПРОВЕРИТЬ:
 *     curl -X POST http://localhost:8080/notes -d "  "
 *       → {"error":"Текст не может быть пустым","status":400}
 *     curl -X POST http://localhost:8080/notes -d "купить хлеб"
 *       → {"id":1,"text":"купить хлеб"}  (201)
 *     curl http://localhost:8080/notes/1
 *       → {"id":1,"text":"купить хлеб"}  (200)
 *     curl http://localhost:8080/notes/99
 *       → {"error":"Заметка не найдена","status":404}
 *     curl http://localhost:8080/notes/abc
 *       → {"error":"Неверный формат id","status":400}
 *
 * ПОДСКАЗКА:
 *   Для извлечения id из пути "/notes/42":
 *     String[] parts = path.split("/");
 *     int id = Integer.parseInt(parts[parts.length - 1]);
 *   Оберните parseInt в try/catch NumberFormatException → sendError 400
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

public class Task06 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/notes", new ExtendedValidatedHandler());
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("📌 POST /notes           - создать заметку (макс 500 символов)");
        System.out.println("📌 GET  /notes           - все заметки");
        System.out.println("📌 GET  /notes/{id}      - заметка по ID");
        System.out.println("📌 DELETE /notes/{id}    - удалить заметку");
    }

    static class ExtendedValidatedHandler implements HttpHandler {
        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);
        private static final int MAX_TEXT_LENGTH = 500;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path   = exchange.getRequestURI().getPath();

            try {
                if ("POST".equals(method) && "/notes".equals(path)) {
                    handleCreate(exchange);
                } else if ("GET".equals(method) && "/notes".equals(path)) {
                    handleGetAll(exchange);
                } else if ("GET".equals(method) && path.startsWith("/notes/")) {
                    handleGetOne(exchange, path);
                } else if ("DELETE".equals(method) && path.startsWith("/notes/")) {
                    handleDelete(exchange, path);
                } else {
                    sendError(exchange, 405, "Метод не разрешён");
                }
            } catch (Exception e) {
                sendError(exchange, 500, "Внутренняя ошибка сервера: " + e.getMessage());
                System.err.println("❌ Ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            String text = readBody(exchange);
            String trimmed = text != null ? text.trim() : "";

            // Валидация: пустой текст
            if (trimmed.isEmpty()) {
                sendError(exchange, 400, "Текст не может быть пустым");
                return;
            }

            // Валидация: максимальная длина
            if (trimmed.length() > MAX_TEXT_LENGTH) {
                sendError(exchange, 400, "Текст не должен превышать " + MAX_TEXT_LENGTH + " символов");
                return;
            }

            // Валидация: запрещенные символы (пример)
            if (trimmed.matches(".*[<>{}].*")) {
                sendError(exchange, 400, "Текст содержит запрещенные символы");
                return;
            }

            int id = counter.incrementAndGet();
            store.put(id, trimmed);

            String json = noteToJson(id, trimmed);
            sendJson(exchange, 201, json);
            System.out.printf("✅ POST /notes -> 201 (id=%d, длина=%d)%n", id, trimmed.length());
        }

        private void handleGetAll(HttpExchange exchange) throws IOException {
            String jsonArray = store.entrySet().stream()
                    .map(entry -> noteToJson(entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(",", "[", "]"));

            sendJson(exchange, 200, jsonArray);
            System.out.printf("✅ GET /notes -> 200 (%d заметок)%n", store.size());
        }

        private void handleGetOne(HttpExchange exchange, String path) throws IOException {
            try {
                int id = extractId(path);
                String text = store.get(id);

                if (text == null) {
                    sendError(exchange, 404, "Заметка не найдена");
                    return;
                }

                String json = noteToJson(id, text);
                sendJson(exchange, 200, json);
                System.out.printf("✅ GET %s -> 200%n", path);

            } catch (NumberFormatException e) {
                sendError(exchange, 400, "Неверный формат id");
            }
        }

        private void handleDelete(HttpExchange exchange, String path) throws IOException {
            try {
                int id = extractId(path);
                String removed = store.remove(id);

                if (removed == null) {
                    sendError(exchange, 404, "Заметка не найдена");
                    return;
                }

                String json = String.format("{\"id\":%d,\"deleted\":true}", id);
                sendJson(exchange, 200, json);
                System.out.printf("✅ DELETE %s -> 200%n", path);

            } catch (NumberFormatException e) {
                sendError(exchange, 400, "Неверный формат id");
            }
        }

        private int extractId(String path) throws NumberFormatException {
            String[] parts = path.split("/");
            return Integer.parseInt(parts[parts.length - 1]);
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

        private String noteToJson(int id, String text) {
            String escapedText = text != null ? text.replace("\"", "\\\"") : "";
            return String.format("{\"id\":%d,\"text\":\"%s\"}", id, escapedText);
        }
    }
}
