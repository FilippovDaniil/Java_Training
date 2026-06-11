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

public class Task07 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/notes", new NotesServer());
        server.setExecutor(Executors.newFixedThreadPool(4));
        server.start();
        System.out.println("Сервер заметок запущен: http://localhost:8080/notes");
    }

    // =========================================================
    //  Основной обработчик — реализуйте методы с пометкой TODO
    // =========================================================
    static class NotesServer implements HttpHandler {

        private final Map<Integer, String> store = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path   = exchange.getRequestURI().getPath().replaceAll("/$", ""); // убрать trailing /

            try {
                if ("/notes".equals(path)) {
                    // Коллекционные операции
                    if ("GET".equals(method))       { handleGetAll(exchange); }
                    else if ("POST".equals(method)) { handleCreate(exchange); }
                    else                            { sendError(exchange, 405, "Метод не поддерживается"); }
                } else if (path.startsWith("/notes/")) {
                    // Операции с конкретной заметкой
                    int id = parseId(path);
                    if ("GET".equals(method))        { handleGetOne(exchange, id); }
                    else if ("PUT".equals(method))   { handleUpdate(exchange, id); }
                    else if ("DELETE".equals(method)){ handleDelete(exchange, id); }
                    else                             { sendError(exchange, 405, "Метод не поддерживается"); }
                } else {
                    sendError(exchange, 404, "Маршрут не найден");
                }
            } catch (NumberFormatException e) {
                sendError(exchange, 400, "Неверный формат id");
            }
        }

        // --- Обработчики ---

        private void handleGetAll(HttpExchange exchange) throws IOException {
            // TODO: собрать JSON-массив из store.entrySet()
            //       порядок не важен (Map не гарантирует порядок)
            //       вернуть 200 + массив
        }

        private void handleCreate(HttpExchange exchange) throws IOException {
            // TODO: прочитать тело (JSON {"text":"..."})
            // TODO: извлечь значение поля text (extractText)
            // TODO: если пустое → 400
            // TODO: сохранить, вернуть 201 + {"id":N,"text":"..."}
        }

        private void handleGetOne(HttpExchange exchange, int id) throws IOException {
            // TODO: найти в store по id
            //       если нет → 404
            //       иначе → 200 + {"id":N,"text":"..."}
        }

        private void handleUpdate(HttpExchange exchange, int id) throws IOException {
            // TODO: проверить существование id → 404 если нет
            // TODO: прочитать тело, извлечь text
            // TODO: если пустой → 400
            // TODO: store.put(id, text), вернуть 200 + {"id":N,"text":"..."}
        }

        private void handleDelete(HttpExchange exchange, int id) throws IOException {
            // TODO: если id нет в store → 404
            // TODO: store.remove(id)
            // TODO: sendResponseHeaders(204, -1) — нет тела
            //       exchange.getResponseBody().close()
        }

        // --- Вспомогательные методы ---

        /**
         * Отправить JSON-ответ.
         * ВАЖНО: bytes.length, не json.length() — иначе кириллица обрежется.
         */
        private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
            // TODO: реализуйте
        }

        /** Единый контракт ошибок */
        private void sendError(HttpExchange exchange, int code, String message) throws IOException {
            String json = String.format("{\"error\":\"%s\",\"status\":%d}", message, code);
            sendJson(exchange, code, json);
        }

        /** Прочитать тело запроса в строку */
        private String readBody(HttpExchange exchange) throws IOException {
            // TODO: реализуйте
            return null;
        }

        /**
         * Извлечь значение поля "text" из JSON вида {"text":"значение"}.
         * Точный JSON-парсер не нужен — достаточно простого indexOf.
         * Верните null если поле не найдено или значение пустое.
         */
        private String extractText(String json) {
            // TODO: найти "text" в строке json, вернуть значение или null
            return null;
        }

        /** Извлечь числовой id из пути /notes/{id} */
        private int parseId(String path) {
            String[] parts = path.split("/");
            return Integer.parseInt(parts[parts.length - 1]); // бросит NumberFormatException
        }

        /** Сформировать JSON одной заметки */
        private String noteToJson(int id, String text) {
            return String.format("{\"id\":%d,\"text\":\"%s\"}", id, text.replace("\"", "\\\""));
        }
    }
}
