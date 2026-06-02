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

public class Task06 {

    public static void main(String[] args) throws IOException {
        // TODO: создайте сервер на порту 8080
        // TODO: createContext("/notes", new ValidatedNotesHandler())
        // TODO: setExecutor, start, вывод
    }

    static class ValidatedNotesHandler implements HttpHandler {
        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path   = exchange.getRequestURI().getPath();

            // TODO: реализуйте роутинг:
            //   POST + "/notes"        → handleCreate
            //   GET  + "/notes"        → handleGetAll
            //   GET  + "/notes/{id}"   → handleGetOne (извлечь id из path)
            //   иначе                  → sendError 405
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            // TODO: прочитать тело, обрезать пробелы (.trim())
            // TODO: если пустое → sendError(exchange, 400, "Текст не может быть пустым")
            // TODO: сохранить, ответить 201 + JSON
        }

        private void handleGetAll(HttpExchange exchange) throws IOException {
            // TODO: собрать JSON-массив из store, ответить 200
        }

        private void handleGetOne(HttpExchange exchange, String path) throws IOException {
            // TODO: извлечь id из path (split + parseInt)
            //       при NumberFormatException → sendError(400, "Неверный формат id")
            //       если id не в store → sendError(404, "Заметка не найдена")
            //       иначе → 200 + {"id":id,"text":"..."}
        }

        // === КАРКАС: реализуйте тела всех вспомогательных методов ===

        /** Отправить JSON-ответ. bytes.length должен совпадать с длиной тела. */
        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            // TODO
        }

        /** Единый контракт ошибок: {"error":"...","status":N} */
        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
            sendJson(exchange, code, json);
        }

        /** Прочитать тело запроса в строку (UTF-8). */
        private String readBody(HttpExchange exchange) throws IOException {
            // TODO
            return null;
        }

        /** Сформировать JSON-объект заметки. */
        private String noteToJson(int id, String text) {
            return String.format("{\"id\":%d,\"text\":\"%s\"}", id, text.replace("\"", "\\\""));
        }
    }
}
