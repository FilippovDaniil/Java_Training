package m57_java_http_client.practice;

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
    // Вложенный класс-клиент
    // ---------------------------------------------------------------
    static class BlogApiClient {
        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
        private final HttpClient client;
        private final int maxRetries;

        BlogApiClient(int maxRetries) {
            this.maxRetries = maxRetries;
            this.client = HttpClient.newHttpClient();
        }

        /** GET /posts — вернуть тело ответа (список всех постов) */
        String getAll() {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = sendWithRetry(request);
            return response != null ? response.body() : null;
        }

        /** GET /posts/{id} — вернуть тело или null при 4xx */
        String getById(int id) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = sendWithRetry(request);
            return response != null && response.statusCode() == 200 ? response.body() : null;
        }

        /**
         * POST /posts — создать пост, вернуть тело ответа (включая новый id).
         * Ожидается статус 201.
         */
        String create(String title, String body, int userId) {
            String json = String.format(
                    "{\"title\":\"%s\",\"body\":\"%s\",\"userId\":%d}",
                    escapeJson(title), escapeJson(body), userId
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = sendWithRetry(request);

            if (response != null && response.statusCode() == 201) {
                return response.body();
            } else if (response != null) {
                System.err.println("⚠️ HTTP ошибка: " + response.statusCode());
                return null;
            }
            return null;
        }

        /**
         * PUT /posts/{id} — полная замена.
         * Ожидается статус 200.
         */
        String update(int id, String title, String body) {
            String json = String.format(
                    "{\"id\":%d,\"title\":\"%s\",\"body\":\"%s\"}",
                    id, escapeJson(title), escapeJson(body)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = sendWithRetry(request);

            if (response != null && response.statusCode() == 200) {
                return response.body();
            } else if (response != null) {
                System.err.println("⚠️ HTTP ошибка: " + response.statusCode());
                return null;
            }
            return null;
        }

        /** DELETE /posts/{id} — вернуть true если статус 200 или 204 */
        boolean delete(int id) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = sendWithRetry(request);

            if (response != null) {
                int statusCode = response.statusCode();
                if (statusCode == 200 || statusCode == 204) {
                    return true;
                } else {
                    System.err.println("⚠️ HTTP ошибка: " + statusCode);
                    return false;
                }
            }
            return false;
        }

        /**
         * Вспомогательный метод: отправить запрос с ретраями при 5xx.
         * При 4xx — вывести предупреждение и вернуть ответ (не повторять).
         * При IOException/InterruptedException — обернуть в RuntimeException.
         */
        private HttpResponse<String> sendWithRetry(HttpRequest request) {
            int attempt = 0;
            while (attempt < maxRetries) {
                attempt++;
                try {
                    HttpResponse<String> response = client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

                    int statusCode = response.statusCode();

                    // Успех или клиентская ошибка (4xx) - не повторяем
                    if (statusCode < 500) {
                        if (statusCode >= 400) {
                            System.err.println("⚠️ HTTP ошибка: " + statusCode);
                        }
                        return response;
                    }

                    // Серверная ошибка (5xx) - повторяем
                    System.err.printf("⚠️ Серверная ошибка %d (попытка %d/%d)%n",
                            statusCode, attempt, maxRetries);

                    if (attempt < maxRetries) {
                        long waitTime = 300L * attempt;
                        System.out.printf("   Ожидание %d мс перед повторной попыткой...%n", waitTime);
                        Thread.sleep(waitTime);
                    }

                } catch (IOException | InterruptedException e) {
                    // Восстанавливаем прерывание при InterruptedException
                    if (e instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                    }
                    System.err.println("❌ Ошибка при отправке запроса: " + e.getMessage());
                    if (attempt == maxRetries) {
                        throw new RuntimeException("Не удалось выполнить запрос после " + maxRetries + " попыток", e);
                    }
                }
            }

            return null;
        }

        /**
         * Экранирование специальных символов в JSON
         */
        private String escapeJson(String text) {
            if (text == null) {
                return "";
            }
            return text.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
        }
    }

    // ---------------------------------------------------------------
    // Точка входа: консольное меню
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        BlogApiClient api = new BlogApiClient(3); // до 3 попыток при 5xx
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== КОНСОЛЬНЫЙ CRUD-КЛИЕНТ К API БЛОГА ===\n");
        System.out.println("https://jsonplaceholder.typicode.com/posts\n");
        System.out.println("Доступные команды:");
        System.out.println("  1 - Получить все посты");
        System.out.println("  2 - Получить пост по ID");
        System.out.println("  3 - Создать новый пост");
        System.out.println("  4 - Обновить пост");
        System.out.println("  5 - Удалить пост");
        System.out.println("  0 - Выход\n");

        boolean running = true;
        while (running) {
            System.out.print("Выберите действие: ");
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> getAllPosts(api);
                case 2 -> getPostById(api, scanner);
                case 3 -> createPost(api, scanner);
                case 4 -> updatePost(api, scanner);
                case 5 -> deletePost(api, scanner);
                case 0 -> {
                    running = false;
                    System.out.println("👋 До свидания!");
                }
                default -> System.out.println("❌ Неверный ввод. Выберите 0-5.");
            }
            System.out.println();
        }

        scanner.close();
    }

    // ===== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ДЛЯ МЕНЮ =====

    private static void getAllPosts(BlogApiClient api) {
        System.out.println("\n📋 Загрузка всех постов...");
        try {
            String response = api.getAll();
            if (response != null) {
                System.out.println("✅ Получено " + countPosts(response) + " постов");
                if (response.length() > 500) {
                    System.out.println("📝 Первые 500 символов:");
                    System.out.println(response.substring(0, 500) + "...");
                } else {
                    System.out.println("📝 Ответ:");
                    System.out.println(response);
                }
            } else {
                System.out.println("❌ Не удалось получить посты");
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private static void getPostById(BlogApiClient api, Scanner scanner) {
        System.out.print("\nВведите ID поста: ");
        int id = readInt(scanner);

        try {
            String response = api.getById(id);
            if (response != null) {
                System.out.println("✅ Пост найден:");
                System.out.println(response);
            } else {
                System.out.println("❌ Пост с ID " + id + " не найден");
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private static void createPost(BlogApiClient api, Scanner scanner) {
        System.out.println("\n📝 Создание нового поста");
        System.out.print("  Заголовок: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Заголовок не может быть пустым");
            return;
        }

        System.out.print("  Текст: ");
        String body = scanner.nextLine().trim();
        if (body.isEmpty()) {
            System.out.println("❌ Текст не может быть пустым");
            return;
        }

        System.out.print("  ID пользователя (1-10): ");
        int userId = readInt(scanner);
        if (userId < 1 || userId > 10) {
            System.out.println("❌ ID пользователя должен быть от 1 до 10");
            return;
        }

        try {
            String response = api.create(title, body, userId);
            if (response != null) {
                System.out.println("✅ Пост создан успешно!");
                System.out.println("📝 Ответ сервера:");
                System.out.println(response);
                System.out.println("💡 Примечание: API имитирует создание, фактически данные не сохраняются.");
            } else {
                System.out.println("❌ Не удалось создать пост");
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private static void updatePost(BlogApiClient api, Scanner scanner) {
        System.out.println("\n✏️ Обновление поста");
        System.out.print("  ID поста: ");
        int id = readInt(scanner);

        if (id <= 0) {
            System.out.println("❌ ID должен быть положительным числом");
            return;
        }

        // Сначала проверяем, существует ли пост
        try {
            String existing = api.getById(id);
            if (existing == null) {
                System.out.println("❌ Пост с ID " + id + " не найден");
                return;
            }
            System.out.println("   Текущий пост: " + existing.substring(0, Math.min(100, existing.length())) + "...");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при проверке поста: " + e.getMessage());
            return;
        }

        System.out.print("  Новый заголовок: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Заголовок не может быть пустым");
            return;
        }

        System.out.print("  Новый текст: ");
        String body = scanner.nextLine().trim();
        if (body.isEmpty()) {
            System.out.println("❌ Текст не может быть пустым");
            return;
        }

        try {
            String response = api.update(id, title, body);
            if (response != null) {
                System.out.println("✅ Пост обновлен успешно!");
                System.out.println("📝 Ответ сервера:");
                System.out.println(response);
                System.out.println("💡 Примечание: API имитирует обновление, фактически данные не сохраняются.");
            } else {
                System.out.println("❌ Не удалось обновить пост");
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private static void deletePost(BlogApiClient api, Scanner scanner) {
        System.out.print("\n🗑️ Введите ID поста для удаления: ");
        int id = readInt(scanner);

        if (id <= 0) {
            System.out.println("❌ ID должен быть положительным числом");
            return;
        }

        try {
            boolean deleted = api.delete(id);
            if (deleted) {
                System.out.println("✅ Пост с ID " + id + " успешно удален");
                System.out.println("💡 Примечание: API имитирует удаление, фактически данные не удаляются.");
            } else {
                System.out.println("❌ Не удалось удалить пост с ID " + id);
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    /**
     * Безопасное чтение целого числа из Scanner
     */
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // очищаем буфер
                return value;
            } catch (java.util.InputMismatchException e) {
                System.out.print("❌ Введите число: ");
                scanner.nextLine(); // очищаем буфер
            }
        }
    }

    /**
     * Подсчет количества постов в JSON-ответе
     */
    private static int countPosts(String json) {
        if (json == null || json.isEmpty()) {
            return 0;
        }
        // Простой подсчет: считаем количество "id":
        int count = 0;
        String search = "\"id\"";
        int index = 0;
        while ((index = json.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }
        return count;
    }
}