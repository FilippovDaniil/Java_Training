package m16_dependency_injection_ioc.practice.task07;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// IoC-контейнер: собирает граф зависимостей по зарегистрированным рецептам.
class Container {
    private final Map<Class<?>, Supplier<?>> recipes = new HashMap<>();

    public <T> void register(Class<T> type, Supplier<T> recipe) {
        // TODO: положить recipe по type
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> type) {
        // TODO: взять Supplier по type и вызвать .get()
        return null;
    }
}
