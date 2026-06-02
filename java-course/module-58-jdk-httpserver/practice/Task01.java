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
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Task01 {
    public static void main(String[] args) throws IOException {
        // TODO: создайте HttpServer на порту 8080
        // HttpServer server = HttpServer.create(...);

        // TODO: зарегистрируйте контекст "/" с лямбда-обработчиком,
        //       который отправляет 200 + "Hello from JDK HttpServer!"

        // TODO: установите executor (null = однопоточный)

        // TODO: запустите сервер и выведите сообщение в консоль
    }
}
