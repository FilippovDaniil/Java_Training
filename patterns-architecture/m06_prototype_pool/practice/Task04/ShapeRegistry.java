package m06_prototype_pool.practice.task04;

// Хранилище образцов. create(key) отдаёт КОПИЮ образца, не сам образец.
class ShapeRegistry {
    // TODO: приватное поле Map<String, Shape> prototypes

    public void register(String key, Shape prototype) {
        // TODO: сохранить образец по ключу
    }

    public Shape create(String key) {
        // TODO: взять образец по ключу и вернуть его copy();
        //       если ключа нет — IllegalArgumentException
        return null;
    }
}
