package m59_spring_core_intro.practice.task01;

import java.util.List;

/** Реализация в памяти — «заглушка» для разработки. */
class InMemoryProductRepository implements ProductRepository {
    @Override
    public List<String> findAll() {
        return List.of("Ноутбук", "Мышь", "Клавиатура");
    }
}
