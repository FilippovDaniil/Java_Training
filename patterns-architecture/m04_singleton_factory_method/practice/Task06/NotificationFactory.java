package m04_singleton_factory_method.practice.task06;

import java.util.function.Supplier;

// Открыта для расширения через register(...), закрыта для модификации (нет switch).
class NotificationFactory {
    // TODO: приватное поле Map<String, Supplier<Notification>> registry

    public void register(String key, Supplier<Notification> supplier) {
        // TODO: положить supplier по ключу
    }

    public Notification create(String key) {
        // TODO: взять Supplier по ключу и вызвать .get();
        //       если ключа нет — IllegalArgumentException
        return null;
    }
}
