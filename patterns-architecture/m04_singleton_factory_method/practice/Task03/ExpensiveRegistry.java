package m04_singleton_factory_method.practice.task03;

class ExpensiveRegistry {

    // TODO: приватный конструктор, печатающий "Registry создан"
    // TODO: приватное поле-хранилище ключей (например, Set<String>)

    // Ленивая потокобезопасная инициализация через вложенный класс-держатель.
    private static class Holder {
        // TODO: static final ExpensiveRegistry INSTANCE = new ExpensiveRegistry();
    }

    public static ExpensiveRegistry getInstance() {
        // TODO: вернуть Holder.INSTANCE
        return null;
    }

    public void register(String key) {
        // TODO: добавить ключ
    }

    public boolean contains(String key) {
        // TODO: проверить наличие ключа
        return false;
    }
}
