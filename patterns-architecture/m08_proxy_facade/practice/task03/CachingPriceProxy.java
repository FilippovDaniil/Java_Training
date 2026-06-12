package m08_proxy_facade.practice.task03;

import java.util.HashMap;
import java.util.Map;

// Кеширующий прокси: для известного sku не обращается к реальному сервису.
class CachingPriceProxy implements PriceService {
    // TODO: поля PriceService real и Map<String,Long> cache + конструктор
    //       CachingPriceProxy(PriceService real)

    @Override
    public long priceOf(String sku) {
        // TODO: если sku есть в cache — вернуть из кеша;
        //       иначе real.priceOf(sku), положить в cache, вернуть
        return 0;
    }
}
