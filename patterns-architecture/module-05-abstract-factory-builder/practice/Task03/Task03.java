/**
 * Задача 03 — Тема 05: Builder для объекта с множеством опций
 *
 * ЗАДАНИЕ:
 *   Реализуйте HttpRequest (файл HttpRequest.java) — неизменяемое описание
 *   запроса с многими опциональными параметрами:
 *     - обязательные: method (GET/POST/...), url;
 *     - опциональные: timeoutMs (по умолч. 1000), body (по умолч. ""),
 *       заголовки header(name, value) — можно добавлять по одному (накопление
 *       в Map);
 *     - build() проверяет, что method и url заданы.
 *   В main соберите два разных запроса: простой GET без тела и POST с телом и
 *   парой заголовков. Выведите их.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   GET https://ops/orders timeout=1000 headers={} body=''
 *   POST https://ops/orders timeout=2000 headers={Content-Type=application/json, X-Trace=42} body='{...}'
 *
 * ТРЕБОВАНИЯ:
 *   - один и тот же Builder собирает запросы с разным набором опций;
 *   - метод header(name, value) можно вызывать несколько раз (накопление);
 *   - HttpRequest неизменяем; копия заголовков защищена (Map.copyOf или unmodifiable).
 *
 * ПОДСКАЗКА:
 *   Так устроен реальный java.net.http.HttpRequest.newBuilder()...build().
 *   Заголовки храните в Map<String,String>, в build() оберните в неизменяемую копию.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: соберите GET без тела и POST с телом и заголовками; выведите оба
    }
}
