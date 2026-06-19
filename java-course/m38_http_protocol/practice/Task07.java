package m38_http_protocol.practice;

/**
 * Задача 07 — Модуль 38 (МИНИ-ПРОЕКТ): Консольный клиент API
 *
 * ЗАДАНИЕ:
 *   Напишите небольшой консольный клиент к публичному API
 *   https://jsonplaceholder.typicode.com. Меню в цикле:
 *     1 — получить пост по id (GET /posts/{id}), вывести тело;
 *     2 — получить список постов пользователя
 *         (GET /posts?userId={id}), вывести их количество;
 *     3 — создать пост (POST /posts с JSON), вывести код и id из ответа;
 *     4 — удалить пост (DELETE /posts/{id}), вывести код ответа;
 *     0 — выход.
 *   Обрабатывайте ошибки сети и неуспешные коды (4xx/5xx) — выводите
 *   понятное сообщение, не роняя программу.
 *
 * ДОПОЛНИТЕЛЬНО (по желанию):
 *   Извлеките из JSON-ответа значение поля (например, "title")
 *   простым разбором строки или через библиотеку (Gson/Jackson).
 *
 * ПОДСКАЗКИ:
 *   - вынесите отправку запроса в отдельный метод, возвращающий
 *     HttpResponse<String>;
 *   - для query-параметров просто стройте URL: ".../posts?userId=" + id;
 *   - DELETE: HttpRequest.newBuilder().uri(...).DELETE().build();
 *   - оборачивайте send в try-catch (IOException/InterruptedException);
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Task07 {
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== КОНСОЛЬНЫЙ КЛИЕНТ JSONPLACEHOLDER API ===\n");
        System.out.println("Доступные команды:");
        System.out.println("  1 - Получить пост по ID");
        System.out.println("  2 - Получить посты пользователя");
        System.out.println("  3 - Создать новый пост");
        System.out.println("  4 - Удалить пост");
        System.out.println("  0 - Выход");
        System.out.println();

        boolean running = true;
        while (running) {
            System.out.print("Выберите действие: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> getPostById();
                case 2 -> getPostsByUser();
                case 3 -> createPost();
                case 4 -> deletePost();
                case 0 -> {
                    running = false;
                    System.out.println("👋 До свидания!");
                }
                default -> System.out.println("❌ Неверный ввод. Пожалуйста, выберите 0-4.");
            }
            System.out.println();
        }
        scanner.close();
    }

    // ============ ОСНОВНЫЕ МЕТОДЫ ============

    private static void getPostById() {
        System.out.print("Введите ID поста: ");
        int id = readInt();

        if (id <= 0) {
            System.out.println("❌ ID должен быть положительным числом.");
            return;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/posts/" + id))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = sendRequest(request);

            if (response.statusCode() == 200) {
                System.out.println("✅ Пост найден:");
                printPrettyJson(response.body());
            } else if (response.statusCode() == 404) {
                System.out.println("❌ Пост с ID " + id + " не найден.");
            } else {
                System.out.println("❌ Ошибка: HTTP " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении запроса: " + e.getMessage());
        }
    }

    private static void getPostsByUser() {
        System.out.print("Введите ID пользователя: ");
        int userId = readInt();

        if (userId <= 0) {
            System.out.println("❌ ID должен быть положительным числом.");
            return;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/posts?userId=" + userId))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = sendRequest(request);

            if (response.statusCode() == 200) {
                String body = response.body();
                int count = countPosts(body);
                System.out.println("✅ Найдено постов: " + count);
                if (count > 0) {
                    System.out.println("📋 Первые 3 заголовка:");
                    printFirstTitles(body, 3);
                }
            } else {
                System.out.println("❌ Ошибка: HTTP " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении запроса: " + e.getMessage());
        }
    }

    private static void createPost() {
        System.out.println("📝 Создание нового поста");
        System.out.print("  Заголовок: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Заголовок не может быть пустым.");
            return;
        }

        System.out.print("  Тело поста: ");
        String body = scanner.nextLine().trim();
        if (body.isEmpty()) {
            System.out.println("❌ Тело поста не может быть пустым.");
            return;
        }

        System.out.print("  ID пользователя (1-10): ");
        int userId = readInt();
        if (userId < 1 || userId > 10) {
            System.out.println("❌ ID пользователя должен быть от 1 до 10.");
            return;
        }

        String json = String.format(
                "{\"userId\":%d,\"title\":\"%s\",\"body\":\"%s\"}",
                userId, escapeJson(title), escapeJson(body)
        );

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/posts"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = sendRequest(request);

            System.out.println("📊 Код ответа: " + response.statusCode());

            if (response.statusCode() == 201) {
                System.out.println("✅ Пост успешно создан!");
                System.out.println("📋 Ответ сервера:");
                printPrettyJson(response.body());
                System.out.println("💡 Примечание: API имитирует создание, фактически данные не сохраняются.");
            } else {
                System.out.println("❌ Ошибка создания поста.");
                if (response.body() != null && !response.body().isEmpty()) {
                    System.out.println("  Ответ: " + response.body());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении запроса: " + e.getMessage());
        }
    }

    private static void deletePost() {
        System.out.print("Введите ID поста для удаления: ");
        int id = readInt();

        if (id <= 0) {
            System.out.println("❌ ID должен быть положительным числом.");
            return;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/posts/" + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = sendRequest(request);

            int statusCode = response.statusCode();
            System.out.println("📊 Код ответа: " + statusCode);

            if (statusCode == 200 || statusCode == 204) {
                System.out.println("✅ Пост с ID " + id + " успешно удален.");
                System.out.println("💡 Примечание: API имитирует удаление, фактически данные не удаляются.");
            } else if (statusCode == 404) {
                System.out.println("❌ Пост с ID " + id + " не найден.");
            } else {
                System.out.println("❌ Ошибка удаления. HTTP " + statusCode);
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении запроса: " + e.getMessage());
        }
    }

    // ============ ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ============

    private static HttpResponse<String> sendRequest(HttpRequest request)
            throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static int readInt() {
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

    private static int countPosts(String json) {
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

    private static void printFirstTitles(String json, int limit) {
        int count = 0;
        String search = "\"title\":\"";
        int index = 0;

        while (count < limit && (index = json.indexOf(search, index)) != -1) {
            index += search.length();
            int end = json.indexOf("\"", index);
            if (end != -1) {
                String title = json.substring(index, end);
                if (!title.isEmpty()) {
                    System.out.println("  - " + title);
                    count++;
                }
                index = end;
            }
        }
    }

    private static void printPrettyJson(String json) {
        // Простой pretty-print для JSON
        if (json == null || json.isEmpty()) {
            System.out.println("  (пустой ответ)");
            return;
        }

        // Если это массив, показываем первый элемент для примера
        if (json.trim().startsWith("[")) {
            System.out.println("  [массив из " + countPosts(json) + " элементов]");
            // Показываем первый элемент
            int firstBrace = json.indexOf("{");
            if (firstBrace != -1) {
                int matchingBrace = findMatchingBrace(json, firstBrace);
                if (matchingBrace != -1) {
                    String firstItem = json.substring(firstBrace, matchingBrace + 1);
                    System.out.println("  Первый элемент:");
                    printJsonObject(firstItem, "    ");
                }
            }
        } else {
            printJsonObject(json, "  ");
        }
    }

    private static void printJsonObject(String json, String indent) {
        // Упрощенный вывод JSON
        String[] pairs = json.replace("{", "").replace("}", "").split(",");
        for (String pair : pairs) {
            String trimmed = pair.trim();
            if (trimmed.contains(":")) {
                String[] keyValue = trimmed.split(":", 2);
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim().replace("\"", "");
                if (value.length() > 50) {
                    value = value.substring(0, 50) + "...";
                }
                System.out.println(indent + key + ": " + value);
            }
        }
    }

    private static int findMatchingBrace(String json, int start) {
        int depth = 0;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
