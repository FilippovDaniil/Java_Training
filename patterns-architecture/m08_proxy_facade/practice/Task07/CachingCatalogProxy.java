package m08_proxy_facade.practice.task07;

import java.util.HashMap;
import java.util.Map;

// Кеширующий прокси над сервисом цен.
class CachingCatalogProxy implements CatalogService {
    // TODO: поля CatalogService real и Map<String,Long> cache + конструктор

    @Override
    public long priceOf(String sku) {
        // TODO: если есть в cache — вернуть; иначе real.priceOf, закешировать, вернуть
        return 0;
    }
}
