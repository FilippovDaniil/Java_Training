package m59_spring_core_intro.practice.task01;

/** Реализация в памяти — «заглушка» для разработки. */
class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }
}
