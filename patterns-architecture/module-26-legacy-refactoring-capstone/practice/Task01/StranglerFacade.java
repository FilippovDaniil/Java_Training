import java.util.Set;

// Фасад «душитель»: перенаправляет на новое для мигрированных id, иначе на старое.
class StranglerFacade implements OrderApi {
    // TODO: поля final OrderApi legacy, final OrderApi modern, final Set<String> migrated + конструктор

    @Override
    public String process(String orderId) {
        // TODO: migrated.contains(orderId) ? modern.process(orderId) : legacy.process(orderId)
        return "";
    }
}
