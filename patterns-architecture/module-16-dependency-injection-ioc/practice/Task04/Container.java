import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Минимальный IoC-контейнер: реестр «рецептов создания».
class Container {
    private final Map<Class<?>, Supplier<?>> recipes = new HashMap<>();

    public <T> void register(Class<T> type, Supplier<T> recipe) {
        // TODO: положить recipe по type
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> type) {
        // TODO: взять Supplier по type и вызвать .get(); привести к (T)
        return null;
    }
}
