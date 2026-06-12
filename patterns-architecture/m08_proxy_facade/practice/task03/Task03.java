package m08_proxy_facade.practice.task03;

/**
 * Задача 03 — Тема 08: Proxy (кеширующий)
 *
 * ЗАДАНИЕ:
 *   Реализуйте кеширующий прокси поверх «дорогого» сервиса цен:
 *     - интерфейс PriceService (файл PriceService.java): long priceOf(String sku);
 *     - RealPriceService (файл RealPriceService.java): priceOf печатает
 *       "запрос к БД: <sku>" и возвращает цену (например, длину sku * 1000);
 *     - CachingPriceProxy (файл CachingPriceProxy.java) реализует PriceService,
 *       хранит RealPriceService и Map<String,Long> кеш; для нового sku идёт в
 *       реальный сервис и кеширует, для известного — возвращает из кеша БЕЗ
 *       обращения к реальному сервису.
 *   В main: запросите цену одного sku ДВАЖДЫ (второй раз — из кеша) и другого sku.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   запрос к БД: BOOK
 *   BOOK -> 4000
 *   BOOK -> 4000   (из кеша, без запроса к БД)
 *   запрос к БД: PEN
 *   PEN -> 3000
 *
 * ТРЕБОВАНИЯ:
 *   - повторный запрос того же sku НЕ обращается к RealPriceService;
 *   - кеш живёт в прокси, RealPriceService о нём не знает;
 *   - прокси и сервис реализуют один интерфейс PriceService.
 *
 * ПОДСКАЗКА:
 *   cache.computeIfAbsent(sku, real::priceOf) — лаконичный способ; или вручную
 *   через containsKey/get/put.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: через CachingPriceProxy запросите один sku дважды и другой sku
    }
}
