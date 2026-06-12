package m06_prototype_pool.practice.task07;

// Реестр прототипов заказов: create(key) отдаёт копию образца.
class TemplateRegistry {
    // TODO: приватное поле Map<String, OrderTemplate> templates

    public void register(String key, OrderTemplate prototype) {
        // TODO: сохранить образец
    }

    public OrderTemplate create(String key) {
        // TODO: вернуть templates.get(key).copy();
        //       если нет ключа — IllegalArgumentException
        return null;
    }
}
