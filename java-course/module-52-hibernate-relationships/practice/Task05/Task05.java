/**
 * Задача 05 — Модуль 52: cascade и orphanRemoval (Post — Comment)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) В классе Post5 настройте @OneToMany к Comment5:
 *      - cascade = CascadeType.ALL
 *      - orphanRemoval = true
 *      - mappedBy = "post"
 *   2) В классе Comment5 настройте @ManyToOne к Post5:
 *      - FetchType.LAZY, @JoinColumn(name = "post_id")
 *   3) В методе main выполните три сценария:
 *
 *   Сценарий А — каскадное сохранение:
 *      Создайте Post5 с 3 комментариями. Сохраните ТОЛЬКО пост (em.persist(post)).
 *      Убедитесь, что все 3 комментария появились в БД.
 *
 *   Сценарий Б — каскадное удаление:
 *      Удалите пост (em.remove(post)). Убедитесь, что комментарии тоже удалены.
 *
 *   Сценарий В — orphanRemoval:
 *      Создайте новый пост с 2 комментариями. Сохраните.
 *      Удалите один комментарий из post.getComments(), сделайте em.merge(post).
 *      Убедитесь, что комментарий удалён из БД (без явного em.remove).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   [А] Комментариев в БД: 3
 *   [Б] Комментариев после удаления поста: 0
 *   [В] Комментариев после orphan removal: 1
 *
 * ПОДСКАЗКА:
 *   Для проверки выполните COUNT-запрос через JPQL:
 *     em.createQuery("SELECT COUNT(c) FROM Comment5 c", Long.class).getSingleResult()
 *   Каскадное удаление — следствие CascadeType.REMOVE (входит в ALL).
 *   orphanRemoval удаляет объект, исключённый из коллекции, даже без REMOVE-каскада.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Task05 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)

        // Сценарий А
        // TODO: создать Post5 "Про каскады"
        // TODO: добавить 3 комментария через post.addComment(...)
        // TODO: em.persist(post) — убедиться, что CASCADE сохранит комментарии
        // TODO: вывести COUNT комментариев через JPQL

        // Сценарий Б
        // TODO: em.remove(post) — убедиться, что комментарии удалены
        // TODO: вывести COUNT комментариев (должно быть 0)

        // Сценарий В
        // TODO: создать новый Post5 с 2 комментариями, сохранить
        // TODO: post.getComments().remove(0) — убрать первый
        // TODO: em.merge(post), em.flush()
        // TODO: вывести COUNT (должно быть 1)
    }
}
