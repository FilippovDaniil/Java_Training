/**
 * Задача 04 — Тема 17: замена driven-адаптера без правок ядра
 *
 * ЗАДАНИЕ:
 *   Покажите, что разные реализации выходного порта подставляются в ядро без
 *   его изменения:
 *     - выходной порт PriceProvider (файл ...): long priceOf(String sku);
 *     - StaticPriceProvider (sku.length() * 100) и DiscountedPriceProvider
 *       (половина от StaticProvider, например sku.length() * 50);
 *     - ЯДРО QuoteService (файл QuoteService.java): зависит от PriceProvider;
 *       quote(sku) возвращает цену через порт.
 *   В main создайте QuoteService сначала со StaticPriceProvider, затем с
 *   DiscountedPriceProvider — код QuoteService один и тот же.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   static: 400
 *   discounted: 200
 *
 * ТРЕБОВАНИЯ:
 *   - QuoteService не меняется при смене провайдера цен;
 *   - оба провайдера реализуют один выходной порт PriceProvider;
 *   - выбор адаптера происходит в точке сборки (main), не в ядре.
 *
 * ПОДСКАЗКА:
 *   Это ключевая выгода гексагона: «поменять БД/источник» = новый driven-адаптер,
 *   реализующий тот же порт. Ядро не трогаем.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: QuoteService со StaticPriceProvider и с DiscountedPriceProvider; quote("BOOK")
    }
}
