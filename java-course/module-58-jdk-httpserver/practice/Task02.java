/**
 * Задача 02 — Модуль 58: Роутинг по HTTP-методу
 *
 * ЗАДАНИЕ:
 *   Запустите HttpServer на порту 8080.
 *   Зарегистрируйте один контекст "/echo" и внутри ОДНОГО HttpHandler
 *   разветвите обработку по методу запроса:
 *     GET  /echo  → статус 200, тело "Это GET-запрос"
 *     POST /echo  → статус 200, тело "Это POST-запрос"
 *     Любой другой метод → статус 405, тело "Метод не разрешён"
 *   Content-Type везде: text/plain; charset=UTF-8
 *
 * КАК ПРОВЕРИТЬ:
 *     curl http://localhost:8080/echo
 *       → Это GET-запрос
 *     curl -X POST http://localhost:8080/echo
 *       → Это POST-запрос
 *     curl -X DELETE http://localhost:8080/echo
 *       → Метод не разрешён   (статус 405)
 *
 * ПОДСКАЗКА:
 *   String method = exchange.getRequestMethod();
 *   switch (method) { case "GET": ... case "POST": ... default: ... }
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Task02 {

    public static void main(String[] args) throws IOException {
        // TODO: создайте сервер на порту 8080
        // TODO: зарегистрируйте контекст "/echo" → new EchoHandler()
        // TODO: setExecutor, start, вывод в консоль
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            // TODO: разветвите по методу
            //       GET  → 200 "Это GET-запрос"
            //       POST → 200 "Это POST-запрос"
            //       иначе → 405 "Метод не разрешён"
        }

        // Вспомогательный метод — завершите реализацию
        private void sendText(HttpExchange exchange, int status, String body) throws IOException {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            // TODO: sendResponseHeaders и запись bytes в getResponseBody()
        }
    }
}
