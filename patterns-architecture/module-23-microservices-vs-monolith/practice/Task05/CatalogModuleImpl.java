import java.util.HashMap;
import java.util.Map;

// Модуль «Каталог» владеет своими данными (sku → название).
class CatalogModuleImpl implements CatalogModule {
    private final Map<String, String> titles = new HashMap<>();

    public void seed(String sku, String title) {
        // TODO: положить название по sku
    }

    @Override
    public String titleOf(String sku) {
        // TODO: вернуть название по sku
        return "";
    }
}
