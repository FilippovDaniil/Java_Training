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

public class Task05 {

    public static void main(String[] args) throws IOException {
        // TODO: создайте сервер на порту 8080
        // TODO: createContext("/notes", new NotesHandler())
        // TODO: setExecutor(Executors.newFixedThreadPool(4))
        // TODO: start, вывод
    }

    static class NotesHandler implements HttpHandler {
        // Хранилище: id → текст заметки
        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("POST".equals(method)) {
                handleCreate(exchange);
            } else if ("GET".equals(method)) {
                handleGetAll(exchange);
            } else {
                // TODO: ответить 405
            }
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            // TODO: прочитать тело из exchange.getRequestBody()
            String text = null; // замените

            // TODO: если text пустой → sendError(exchange, 400, "Текст не может быть пустым")

            // TODO: int id = counter.incrementAndGet(); store.put(id, text);
            // TODO: сформировать JSON {"id":id,"text":"..."}
            // TODO: sendJson(exchange, 201, json)
        }

        private void handleGetAll(HttpExchange exchange) throws IOException {
            // TODO: пройти по store.entrySet() (или entrySet().stream()),
            //       собрать JSON-массив вида [{"id":1,"text":"..."},...]
            // TODO: sendJson(exchange, 200, jsonArray)
        }

        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            // TODO: sendResponseHeaders + write bytes
        }

        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
            sendJson(exchange, code, json);
        }

        private String readBody(HttpExchange exchange) throws IOException {
            // TODO: реализуйте чтение тела запроса (UTF-8)
            return null;
        }
    }
}
