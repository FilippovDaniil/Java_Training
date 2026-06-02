/**
 * Задача 07 — Модуль 57 (МИНИ-ПРОЕКТ): Консольный CRUD-клиент к API блога
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс BlogApiClient с методами для работы с постами через
 *   https://jsonplaceholder.typicode.com/posts.
 *
 *   Требуемые методы:
 *     String getAll()              — GET /posts, вернуть тело (список постов)
 *     String getById(int id)       — GET /posts/{id}, вернуть тело
 *     String create(String title,
 *                   String body,
 *                   int userId)    — POST /posts, вернуть тело ответа (201)
 *     String update(int id,
 *                   String title,
 *                   String body)   — PUT /posts/{id}, вернуть тело (200)
 *     boolean delete(int id)       — DELETE /posts/{id}, вернуть true если код 200
 *
 *   Все методы должны:
 *     - бросать RuntimeException при IOException / InterruptedException
 *     - при статусе 5xx — повторять запрос до maxRetries раз (передаётся в конструктор)
 *     - при статусе 4xx — выводить "HTTP ошибка: <код>" и возвращать null / false
 *
 *   В main() реализуйте консольное меню:
 *     1 — все посты (вывести первые 500 символов)
 *     2 — пост по id (ввести id)
 *     3 — создать пост (ввести заголовок и текст)
 *     4 — обновить пост (ввести id, заголовок, текст)
 *     5 — удалить пост (ввести id)
 *     0 — выход
 *
 * ПОДСКАЗКА:
 *   - client.send() + цикл ретраев при statusCode >= 500:
 *       for (int attempt = 1; attempt <= maxRetries; attempt++) {
 *           HttpResponse<String> r = client.send(req, BodyHandlers.ofString());
 *           if (r.statusCode() < 500) return r;
 *           if (attempt < maxRetries) Thread.sleep(300L * attempt);
 *       }
 *   - Для POST/PUT формируйте JSON вручную:
 *       String json = String.format("{\"title\":\"%s\",\"body\":\"%s\",\"userId\":%d}", title, body, userId);
 *   - Scanner: после nextInt() вызывайте nextLine() для очистки буфера.
 *   - HttpClient создайте один раз в конструкторе BlogApiClient.
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Task07 {

    // ---------------------------------------------------------------
    // Вложенный класс-клиент (реализуйте все методы)
    // ---------------------------------------------------------------
    static class BlogApiClient {
        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
        private final HttpClient client;
        private final int maxRetries;

        BlogApiClient(int maxRetries) {
            // TODO: инициализировать HttpClient и maxRetries
            this.maxRetries = maxRetries;
            this.client = null; // замените на HttpClient.newHttpClient()
        }

        /** GET /posts — вернуть тело ответа (список всех постов) */
        String getAll() {
            // TODO
            return null;
        }

        /** GET /posts/{id} — вернуть тело или null при 4xx */
        String getById(int id) {
            // TODO
            return null;
        }

        /**
         * POST /posts — создать пост, вернуть тело ответа (включая новый id).
         * Ожидается статус 201.
         */
        String create(String title, String body, int userId) {
            // TODO: сформировать JSON, отправить POST, проверить 201
            return null;
        }

        /**
         * PUT /posts/{id} — полная замена.
         * Ожидается статус 200.
         */
        String update(int id, String title, String body) {
            // TODO
            return null;
        }

        /** DELETE /posts/{id} — вернуть true если статус 200 */
        boolean delete(int id) {
            // TODO
            return false;
        }

        /**
         * Вспомогательный метод: отправить запрос с ретраями при 5xx.
         * При 4xx — вывести предупреждение и вернуть ответ (не повторять).
         * При IOException/InterruptedException — обернуть в RuntimeException.
         */
        private HttpResponse<String> sendWithRetry(HttpRequest request) {
            // TODO: цикл maxRetries, Thread.sleep между попытками
            return null;
        }
    }

    // ---------------------------------------------------------------
    // Точка входа: консольное меню
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        BlogApiClient api = new BlogApiClient(3); // до 3 попыток при 5xx
        Scanner scanner = new Scanner(System.in);

        // TODO: реализовать меню с пунктами 0–5
        // Совет: после scanner.nextInt() вызывайте scanner.nextLine()

        scanner.close();
    }
}
