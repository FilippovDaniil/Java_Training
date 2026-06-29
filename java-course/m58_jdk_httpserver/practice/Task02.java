package m58_jdk_httpserver.practice;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Task02 {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/echo", new ExtendedEchoHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("🚀 Сервер запущен: http://localhost:8089");
        System.out.println("📌 GET  /echo - возвращает 'Это GET-запрос'");
        System.out.println("📌 POST /echo - возвращает тело запроса в ответе");
        System.out.println("📌 DELETE /echo - 405 Method not allowed");
    }

    static class ExtendedEchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            switch (method) {
                case "GET" -> sendText(exchange, 200, "Это GET-запрос");

                case "POST" -> {
                    // Читаем тело запроса
                    String body = readRequestBody(exchange);
                    String response = "Это POST-запрос\nТело запроса: " + body;
                    sendText(exchange, 200, response);
                }

                default -> sendText(exchange, 405, "Метод не разрешён");
            }
        }

        /**
         * Читает тело запроса из HttpExchange
         */
        private String readRequestBody(HttpExchange exchange) throws IOException {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        }

        /**
         * Отправляет текстовый ответ
         */
        private void sendText(HttpExchange exchange, int status, String body) throws IOException {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            exchange.getResponseHeaders().set("Content-Length", String.valueOf(bytes.length));

            exchange.sendResponseHeaders(status, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
                os.flush();
            }

            System.out.printf("✅ %s /echo -> %d%n", exchange.getRequestMethod(), status);
        }
    }
}
