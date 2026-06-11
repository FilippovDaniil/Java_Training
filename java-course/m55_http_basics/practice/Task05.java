package m55_http_basics.practice;

/**
 * Задача 05 — Модуль 55: Идемпотентность и безопасность методов
 *
 * ЗАДАНИЕ:
 *   Часть 1 — Аналитическая:
 *     Для каждого HTTP-метода заполните таблицу: является ли он
 *     безопасным (safe) и/или идемпотентным (idempotent).
 *     Добавьте краткое обоснование.
 *
 *   Часть 2 — Практическая:
 *     Отправьте два одинаковых запроса к тестовому API
 *     и сравните результаты:
 *       A) Два PUT-запроса с одинаковым телом на один и тот же ID
 *          → ожидается: оба вернут одинаковый результат (идемпотентно)
 *       Б) Два POST-запроса с одинаковым телом на /posts
 *          → ожидается: каждый создаст новый ресурс с новым ID
 *
 *     Используйте https://jsonplaceholder.typicode.com
 *
 * ПОДСКАЗКА:
 *   Идемпотентный: f(f(x)) = f(x). Повторный вызов не меняет результат.
 *   Безопасный: не изменяет состояние сервера (только чтение).
 *
 *   PUT /posts/1  с телом → сервер «обновляет» пост 1 (фейковый API)
 *   POST /posts   с телом → сервер «создаёт» новый пост, возвращает новый id
 *
 *   Сравните поле "id" в двух ответах POST — они должны быть разными
 *   (у jsonplaceholder это всегда 101, но в реальной системе были бы разными).
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task05 {
    public static void main(String[] args) throws Exception {

        // ── Часть 1: Таблица свойств методов ────────────────────────────────
        System.out.println("=== Часть 1: Идемпотентность и безопасность ===");
        System.out.println();
        String table = """
                ┌──────────┬────────────┬───────────────┬──────────────────────────────────┐
                │ Метод    │ Безопасный │ Идемпотентный │ Обоснование                      │
                ├──────────┼────────────┼───────────────┼──────────────────────────────────┤
                │ GET      │ TODO       │ TODO          │ TODO                             │
                │ POST     │ TODO       │ TODO          │ TODO                             │
                │ PUT      │ TODO       │ TODO          │ TODO                             │
                │ PATCH    │ TODO       │ TODO          │ TODO                             │
                │ DELETE   │ TODO       │ TODO          │ TODO                             │
                └──────────┴────────────┴───────────────┴──────────────────────────────────┘
                """;
        System.out.println(table);

        // ── Часть 2: Практика — PUT (идемпотентный) ─────────────────────────
        System.out.println("=== Часть 2A: Два PUT-запроса (идемпотентность) ===");

        HttpClient client = HttpClient.newHttpClient();
        String putBody = "{\"id\":1,\"title\":\"Обновлённая заметка\",\"body\":\"текст\",\"userId\":1}";

        // TODO: создайте HttpRequest для PUT на https://jsonplaceholder.typicode.com/posts/1
        //       с заголовком Content-Type: application/json и телом putBody
        //       Отправьте его ДВАЖДЫ, выведите статус и тело обоих ответов
        //       Вопрос: совпадают ли тела ответов?

        // HttpRequest putRequest = HttpRequest.newBuilder()
        //         .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
        //         .header("Content-Type", "application/json")
        //         .PUT(HttpRequest.BodyPublishers.ofString(putBody))
        //         .build();

        // HttpResponse<String> put1 = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        // HttpResponse<String> put2 = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        // System.out.println("PUT #1 статус: " + put1.statusCode() + "  тело: " + put1.body());
        // System.out.println("PUT #2 статус: " + put2.statusCode() + "  тело: " + put2.body());
        // System.out.println("Тела одинаковы: " + put1.body().equals(put2.body()));

        System.out.println("(реализуйте PUT-запросы выше)");
        System.out.println();

        // ── Часть 2: Практика — POST (НЕ идемпотентный) ────────────────────
        System.out.println("=== Часть 2Б: Два POST-запроса (не идемпотентность) ===");
        String postBody = "{\"title\":\"Новая заметка\",\"body\":\"текст\",\"userId\":1}";

        // TODO: создайте HttpRequest для POST на https://jsonplaceholder.typicode.com/posts
        //       Отправьте его ДВАЖДЫ, выведите статусы и тела
        //       Вопрос: одинаковы ли поля "id" в обоих ответах? Почему?

        // HttpRequest postRequest = HttpRequest.newBuilder()
        //         .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
        //         .header("Content-Type", "application/json")
        //         .POST(HttpRequest.BodyPublishers.ofString(postBody))
        //         .build();

        // HttpResponse<String> post1 = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        // HttpResponse<String> post2 = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        // System.out.println("POST #1 статус: " + post1.statusCode() + "  тело: " + post1.body());
        // System.out.println("POST #2 статус: " + post2.statusCode() + "  тело: " + post2.body());

        System.out.println("(реализуйте POST-запросы выше)");
        System.out.println();
        System.out.println("Вывод: в чём практическая разница между PUT и POST для клиента?");
        System.out.println("// TODO: напишите вывод здесь");
    }
}
