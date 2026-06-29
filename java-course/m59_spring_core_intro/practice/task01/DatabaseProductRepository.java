package m59_spring_core_intro.practice.task01;

import java.util.List;

/** Альтернативная реализация — «БД». Для задачи просто заглушка. */
class DatabaseProductRepository implements ProductRepository {
    @Override
    public List<String> findAll() {
        // В реальности — запрос к БД
        return List.of("Монитор", "Наушники");
    }
}
