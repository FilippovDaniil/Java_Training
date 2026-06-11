package m52_hibernate_relationships.practice.task02;

/**
 * Задача 02 — Модуль 52: Двунаправленная связь и владелец (owning side)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Класс Post уже содержит @ManyToOne к Author (владелец связи).
 *   Класс Author уже содержит @OneToMany с mappedBy = "author" (обратная сторона).
 *
 *   1) В методе main выполните следующий эксперимент:
 *      а) Создайте author и post. Установите ТОЛЬКО author.getPosts().add(post)
 *         — НЕ вызывайте post.setAuthor(author).
 *         Сохраните. Проверьте в БД: есть ли author_id у поста? Почему?
 *      б) Повторите, но теперь установите ТОЛЬКО post.setAuthor(author).
 *         Проверьте: сохранился ли FK? Почему?
 *      в) Реализуйте вспомогательный метод author.addPost(Post post),
 *         который синхронизирует обе стороны. Используйте его.
 *
 *   2) Добавьте вспомогательный метод removePost(Post post) в класс Author.
 *
 * ОЖИДАЕМЫЙ ВЫВОД эксперимента б):
 *   Пост сохранён с author_id = 1 (FK записан, т.к. Post — владелец).
 *   author.getPosts() в памяти пуст — нет синхронизации обратной стороны!
 *
 * ПОДСКАЗКА:
 *   Владелец связи — тот, у кого @JoinColumn.
 *   Hibernate смотрит ТОЛЬКО на поле-владельца при генерации SQL INSERT/UPDATE.
 *   Обратная сторона (mappedBy) — только для навигации в Java, на SQL не влияет.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)
        // TODO: эксперимент а) — только обратная сторона, проверить результат
        // TODO: эксперимент б) — только владелец, проверить результат
        // TODO: эксперимент в) — использовать author.addPost(post)
    }
}
