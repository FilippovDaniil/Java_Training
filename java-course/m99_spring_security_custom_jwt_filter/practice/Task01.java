package m99_spring_security_custom_jwt_filter.practice;

/**
 * Задача 01 — Модуль 99: извлечение Bearer-токена из заголовка Authorization
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: нет (чистая Java) — это разминка перед фильтром.
 *
 * ЗАДАНИЕ:
 *   Реализуйте утилиту разбора заголовка Authorization.
 *     1) extractBearer(String header):
 *          - header == null            → вернуть null;
 *          - не начинается с "Bearer " → вернуть null (это не наш токен);
 *          - иначе                     → вернуть header.substring(7) (отрезать префикс "Bearer ").
 *     2) В main проверьте на примерах (см. ОЖИДАЕМЫЙ ВЫВОД).
 *
 * ЦЕЛЬ: понять, как фильтр достаёт токен из строки заголовка, прежде чем его проверять.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   extractBearer("Bearer eyJ.abc.sig") = eyJ.abc.sig
 *   extractBearer("Basic dXNlcjpwYXNz") = null
 *   extractBearer(null)                 = null
 *
 * ПОДСКАЗКА: префикс "Bearer " — ровно 7 символов (включая пробел).
 */
public class Task01 {
    public static void main(String[] args) {
        System.out.println("extractBearer(\"Bearer eyJ.abc.sig\") = " + extractBearer("Bearer eyJ.abc.sig"));
        System.out.println("extractBearer(\"Basic dXNlcjpwYXNz\") = " + extractBearer("Basic dXNlcjpwYXNz"));
        System.out.println("extractBearer(null)                 = " + extractBearer(null));
    }

    static String extractBearer(String header) {
        // TODO: if (header == null || !header.startsWith("Bearer ")) return null;
        // TODO: return header.substring(7);
        return null;
    }
}
