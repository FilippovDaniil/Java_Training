package m16_dependency_injection_ioc.practice.task05;

import java.util.HashMap;
import java.util.Map;

// Глобальный реестр зависимостей. Объекты сами достают из него нужное.
class ServiceLocator {
    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void register(Class<T> type, T instance) {
        // TODO: положить instance по type
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> type) {
        // TODO: вернуть (T) services.get(type)
        return null;
    }
}
