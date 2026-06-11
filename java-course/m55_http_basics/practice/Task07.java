package m55_http_basics.practice;

/**
 * Задача 07 — Модуль 55: Мини-проект — Полный контракт API «Заметки»
 *
 * ЗАДАНИЕ:
 *   Спроектируйте полный HTTP-контракт REST API микросервиса «Заметки».
 *
 *   Требования к контракту:
 *     1. CRUD для заметок: создать, получить одну, получить список,
 *        обновить, удалить.
 *     2. Список с пагинацией: параметры page и size в query string.
 *     3. Вложенный ресурс «теги» (tags): добавить тег, получить теги,
 *        удалить тег.
 *     4. Для каждого эндпоинта: метод, URL, заголовки запроса,
 *        пример тела запроса (JSON), статус успеха,
 *        пример тела успешного ответа, коды ошибок.
 *     5. Раздел «Ошибки»: стандартный формат тела ошибки (errorCode,
 *        message, details).
 *     6. Пагинация: формат ответа (items, page, size, totalPages).
 *
 *   Контракт оформите в блоке String ниже, заменив TODO.
 *   Затем реализуйте метод sendSampleRequest() — он должен выполнить
 *   GET /posts?_limit=3 на jsonplaceholder и вывести первые 3 «заметки»
 *   (имитация пагинированного списка).
 *
 * ПОДСКАЗКА:
 *   Стандартный формат ошибки:
 *     { "errorCode": "NOTE_NOT_FOUND", "message": "Заметка не найдена",
 *       "details": "id=999 не существует" }
 *   Пагинация (ответ):
 *     { "items": [...], "page": 0, "size": 20, "totalPages": 5 }
 *   query-параметры пагинации: ?page=0&size=3
 *   jsonplaceholder имитирует пагинацию через ?_page=1&_limit=3
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task07 {

    // ── Контракт API ────────────────────────────────────────────────────────

    static final String CONTRACT = """
            ╔══════════════════════════════════════════════════════════════════════╗
            ║              API КОНТРАКТ — Микросервис «Заметки» v1               ║
            ╚══════════════════════════════════════════════════════════════════════╝

            Base URL: https://api.example.com/v1

            ── 1. CRUD «Заметки» ───────────────────────────────────────────────────

            [А] Создать заметку
              Метод:   TODO
              URL:     TODO
              Заголовки запроса:
                Content-Type: application/json
                Authorization: Bearer <token>
              Тело запроса (пример):
                TODO — напишите пример JSON
              Статус успеха: TODO
              Заголовок ответа: TODO (Location: ...)
              Тело ответа (пример):
                TODO — напишите пример JSON с полем id

            [Б] Получить список заметок (с пагинацией)
              Метод:   TODO
              URL:     TODO (включите параметры ?page=&size=)
              Заголовки запроса:
                Accept: application/json
                Authorization: Bearer <token>
              Тело запроса: нет
              Статус успеха: TODO
              Тело ответа (пример):
                TODO — напишите обёртку с items, page, size, totalPages

            [В] Получить одну заметку
              Метод:   TODO
              URL:     TODO
              Статус успеха: TODO   Статус «не найдено»: TODO
              Тело ответа: TODO

            [Г] Полная замена заметки (PUT)
              Метод:   TODO
              URL:     TODO
              Тело запроса: TODO
              Статус успеха: TODO

            [Д] Частичное обновление (PATCH) — только title или done
              Метод:   TODO
              URL:     TODO
              Тело запроса: TODO
              Статус успеха: TODO

            [Е] Удалить заметку
              Метод:   TODO
              URL:     TODO
              Статус успеха: TODO   Тело ответа: нет

            ── 2. Вложенный ресурс «Теги» ─────────────────────────────────────────

            [Ж] Получить теги заметки
              Метод:   TODO
              URL:     TODO
              Статус: TODO

            [З] Добавить тег к заметке
              Метод:   TODO
              URL:     TODO
              Тело: TODO
              Статус: TODO

            [И] Удалить тег с заметки
              Метод:   TODO
              URL:     TODO
              Статус: TODO

            ── 3. Стандартный формат ошибки ────────────────────────────────────────

              {
                "errorCode": "TODO — например NOTE_NOT_FOUND",
                "message":   "TODO — человекочитаемое сообщение",
                "details":   "TODO — техническое описание"
              }

              Маппинг кодов ошибок:
                NOTE_NOT_FOUND       → TODO статус
                VALIDATION_ERROR     → TODO статус
                UNAUTHORIZED         → TODO статус
                FORBIDDEN            → TODO статус
                INTERNAL_ERROR       → TODO статус

            ── 4. Контрольные вопросы ──────────────────────────────────────────────

              a) Почему список должен возвращать обёртку (с totalPages),
                 а не просто массив JSON?
                 // TODO: ответ

              b) Когда нужен статус 204, а когда 200 при обновлении?
                 // TODO: ответ

              c) Чем опасно использование POST вместо PUT для обновления?
                 // TODO: ответ
            """;

    // ── Практика: имитация пагинированного GET-запроса ───────────────────

    static void sendSampleRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // TODO: отправьте GET на https://jsonplaceholder.typicode.com/posts?_page=1&_limit=3
        //       выведите статус и тело (3 «заметки» — имитация пагинации)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts?_page=1&_limit=3"))
                .GET()
                .header("Accept", "application/json")
                .build();

        // TODO: выполните запрос и выведите статус + тело
        // HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println("Статус: " + response.statusCode());
        // System.out.println("Тело (3 поста = имитация page=1, size=3):");
        // System.out.println(response.body());

        System.out.println("(реализуйте отправку и вывод ответа)");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(CONTRACT);
        System.out.println("=== Практика: пагинированный GET /posts?_page=1&_limit=3 ===");
        sendSampleRequest();
    }
}
