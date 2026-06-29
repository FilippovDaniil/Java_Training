package m58_jdk_httpserver.practice;

/**
 * Задача 07 — Модуль 58: Мини-проект «REST-микросервис Заметки»
 *
 * ЗАДАНИЕ:
 *   Реализуйте полноценный REST-микросервис управления заметками
 *   на базе com.sun.net.httpserver. Без сторонних библиотек.
 *
 *   Эндпоинты:
 *     GET    /notes          — список всех заметок
 *     POST   /notes          — создать заметку (тело: {"text":"..."})
 *     GET    /notes/{id}     — получить заметку по id
 *     PUT    /notes/{id}     — обновить текст заметки (тело: {"text":"..."})
 *     DELETE /notes/{id}     — удалить заметку (ответ 204, без тела)
 *
 *   Хранилище: Map<Integer, String> + AtomicInteger (in-memory).
 *   JSON входящий: {"text":"текст"} — извлечь значение поля text вручную.
 *   JSON исходящий: {"id":N,"text":"..."} / [{"id":N,"text":"..."},...]
 *
 *   Валидация:
 *     - text пустой или отсутствует → 400 {"error":"...","status":400}
 *     - id не число → 400
 *     - id не существует → 404
 *     - метод не поддерживается → 405
 *
 *   Пул потоков: Executors.newFixedThreadPool(4)
 *   Порт: 8080
 *
 * КАК ПРОВЕРИТЬ (последовательность команд):
 *     curl -X POST http://localhost:8080/notes \
 *          -H "Content-Type: application/json" \
 *          -d '{"text":"купить молоко"}'
 *       → 201  {"id":1,"text":"купить молоко"}
 *
 *     curl -X POST http://localhost:8080/notes \
 *          -H "Content-Type: application/json" \
 *          -d '{"text":"позвонить маме"}'
 *       → 201  {"id":2,"text":"позвонить маме"}
 *
 *     curl http://localhost:8080/notes
 *       → 200  [{"id":1,"text":"купить молоко"},{"id":2,"text":"позвонить маме"}]
 *
 *     curl http://localhost:8080/notes/1
 *       → 200  {"id":1,"text":"купить молоко"}
 *
 *     curl -X PUT http://localhost:8080/notes/1 \
 *          -H "Content-Type: application/json" \
 *          -d '{"text":"купить хлеб и молоко"}'
 *       → 200  {"id":1,"text":"купить хлеб и молоко"}
 *
 *     curl -X DELETE http://localhost:8080/notes/2
 *       → 204  (пустое тело)
 *
 *     curl http://localhost:8080/notes/99
 *       → 404  {"error":"Заметка не найдена","status":404}
 *
 *     curl -X POST http://localhost:8080/notes \
 *          -H "Content-Type: application/json" \
 *          -d '{"text":""}'
 *       → 400  {"error":"Поле text не может быть пустым","status":400}
 *
 * ПОДСКАЗКА (парсинг text из {"text":"..."}):
 *   Можно найти значение регулярным выражением или простым indexOf:
 *     int start = body.indexOf("\"text\"");
 *     // найти значение после ":"
 *   Или просто: если body.contains("\"text\"") — извлечь подстроку между ближайшими "...".
 *   Точный парсер JSON НЕ требуется — достаточно простого извлечения поля text.
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

public class Task07 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/notes", new NotesServer());
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();

        System.out.println("🚀 Сервер заметок запущен: http://localhost:8089/notes");
        System.out.println("📌 Доступные эндпоинты:");
        System.out.println("   GET    /notes          - список всех заметок");
        System.out.println("   POST   /notes          - создать заметку");
        System.out.println("   GET    /notes/{id}     - получить заметку по id");
        System.out.println("   PUT    /notes/{id}     - обновить заметку");
        System.out.println("   DELETE /notes/{id}     - удалить заметку");
        System.out.println("📌 Нажмите Ctrl+C для остановки");
    }

    // =========================================================
    //  Основной обработчик
    // =========================================================
    static class NotesServer implements HttpHandler {

        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path   = exchange.getRequestURI().getPath().replaceAll("/$", "");

            try {
                if ("/notes".equals(path)) {
                    // Коллекционные операции
                    if ("GET".equals(method)) {
                        handleGetAll(exchange);
                    } else if ("POST".equals(method)) {
                        handleCreate(exchange);
                    } else {
                        sendError(exchange, 405, "Метод не поддерживается");
                    }
                } else if (path.startsWith("/notes/")) {
                    // Операции с конкретной заметкой
                    int id = parseId(path);
                    if ("GET".equals(method)) {
                        handleGetOne(exchange, id);
                    } else if ("PUT".equals(method)) {
                        handleUpdate(exchange, id);
                    } else if ("DELETE".equals(method)) {
                        handleDelete(exchange, id);
                    } else {
                        sendError(exchange, 405, "Метод не поддерживается");
                    }
                } else {
                    sendError(exchange, 404, "Маршрут не найден");
                }
            } catch (NumberFormatException e) {
                sendError(exchange, 400, "Неверный формат id");
            }
        }

        // --- Обработчики ---

        private void handleGetAll(HttpExchange exchange) throws IOException {
            String jsonArray = store.entrySet().stream()
                    .map(entry -> noteToJson(entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(",", "[", "]"));

            sendJson(exchange, 200, jsonArray);
            System.out.printf("✅ GET /notes -> 200 (%d заметок)%n", store.size());
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            String body = readBody(exchange);
            String text = extractText(body);

            if (text == null || text.trim().isEmpty()) {
                sendError(exchange, 400, "Поле text не может быть пустым");
                System.out.println("⚠️ POST /notes -> 400 (пустой text)");
                return;
            }

            int id = counter.incrementAndGet();
            store.put(id, text.trim());

            String json = noteToJson(id, text.trim());
            sendJson(exchange, 201, json);
            System.out.printf("✅ POST /notes -> 201 (id=%d)%n", id);
        }

        private void handleGetOne(HttpExchange exchange, int id) throws IOException {
            String text = store.get(id);
            if (text == null) {
                sendError(exchange, 404, "Заметка не найдена");
                System.out.printf("⚠️ GET /notes/%d -> 404%n", id);
                return;
            }

            String json = noteToJson(id, text);
            sendJson(exchange, 200, json);
            System.out.printf("✅ GET /notes/%d -> 200%n", id);
        }

        private void handleUpdate(HttpExchange exchange, int id) throws IOException {
            // Проверяем существование
            if (!store.containsKey(id)) {
                sendError(exchange, 404, "Заметка не найдена");
                System.out.printf("⚠️ PUT /notes/%d -> 404%n", id);
                return;
            }

            // Читаем и валидируем тело
            String body = readBody(exchange);
            String text = extractText(body);

            if (text == null || text.trim().isEmpty()) {
                sendError(exchange, 400, "Поле text не может быть пустым");
                System.out.printf("⚠️ PUT /notes/%d -> 400%n", id);
                return;
            }

            // Обновляем
            store.put(id, text.trim());

            String json = noteToJson(id, text.trim());
            sendJson(exchange, 200, json);
            System.out.printf("✅ PUT /notes/%d -> 200%n", id);
        }

        private void handleDelete(HttpExchange exchange, int id) throws IOException {
            if (!store.containsKey(id)) {
                sendError(exchange, 404, "Заметка не найдена");
                System.out.printf("⚠️ DELETE /notes/%d -> 404%n", id);
                return;
            }

            store.remove(id);

            // 204 No Content - без тела
            exchange.sendResponseHeaders(204, -1);
            exchange.getResponseBody().close();
            System.out.printf("✅ DELETE /notes/%d -> 204%n", id);
        }

        // --- Вспомогательные методы ---

        /**
         * Отправить JSON-ответ.
         * ВАЖНО: bytes.length, не json.length() — иначе кириллица обрежется.
         */
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

        /** Единый контракт ошибок */
        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
            sendJson(exchange, code, json);
            System.out.printf("❌ %s -> %d: %s%n", exchange.getRequestURI(), code, message);
        }

        /** Прочитать тело запроса в строку */
        private String readBody(HttpExchange exchange) throws IOException {
            try (InputStream is = exchange.getRequestBody()) {
                byte[] bytes = is.readAllBytes();
                return new String(bytes, StandardCharsets.UTF_8);
            }
        }

        /**
         * Извлечь значение поля "text" из JSON вида {"text":"значение"}.
         * Точный JSON-парсер не нужен — достаточно простого indexOf.
         */
        private String extractText(String json) {
            if (json == null || json.isEmpty()) {
                return null;
            }

            // Ищем "text"
            int textIndex = json.indexOf("\"text\"");
            if (textIndex == -1) {
                return null;
            }

            // Ищем после "text":
            int colonIndex = json.indexOf(":", textIndex);
            if (colonIndex == -1) {
                return null;
            }

            // Ищем первую кавычку после двоеточия
            int firstQuote = json.indexOf("\"", colonIndex);
            if (firstQuote == -1) {
                return null;
            }

            // Ищем вторую кавычку
            int secondQuote = json.indexOf("\"", firstQuote + 1);
            if (secondQuote == -1) {
                return null;
            }

            // Извлекаем значение между кавычками
            String value = json.substring(firstQuote + 1, secondQuote);

            // Экранируем обратные слеши (убираем экранирование)
            return value.replace("\\\"", "\"")
                    .replace("\\\\", "\\")
                    .replace("\\n", "\n")
                    .replace("\\r", "\r")
                    .replace("\\t", "\t");
        }

        /** Извлечь числовой id из пути /notes/{id} */
        private int parseId(String path) {
            String[] parts = path.split("/");
            return Integer.parseInt(parts[parts.length - 1]); // бросит NumberFormatException
        }

        /** Сформировать JSON одной заметки */
        private String noteToJson(int id, String text) {
            String escapedText = text != null ? text.replace("\"", "\\\"") : "";
            return String.format("{\"id\":%d,\"text\":\"%s\"}", id, escapedText);
        }
    }
}
